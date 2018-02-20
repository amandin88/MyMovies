package com.amandin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.amandin.managers.OptionManager;
import com.amandin.managers.config.Config;
import com.amandin.managers.config.ConfigManager;
import com.amandin.managers.imdb.ImdbManager;
import com.amandin.managers.lang.Language;
import com.amandin.managers.movie.Movie;
import com.amandin.managers.movie.MoviesFilterManager;
import com.amandin.managers.movie.MoviesManager;
import com.amandin.ui.windows.AboutWindows;
import com.amandin.ui.windows.MainWindow;
import com.amandin.ui.windows.OptionWindow;


/**
 * Allow the interactions between the UI and the datas
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainController {
	
	/*
	 * UI Elements
	 */
	private MainWindow mainWindow;
	private OptionWindow optionWindow;
	private AboutWindows aboutWindow;
	
	/*
	 * Managers
	 */
	private MoviesManager moviesManager;
	private MoviesFilterManager moviesFilterManager;
	private ImdbManager imdbManager;
	private OptionManager optionManager;
	private ConfigManager configManager;
	
	/*
	 * Configuration
	 */
	private Config applicationConfig;
	private Language languageSelected;
	
	/*
	 * Elements related to Movie
	 */
	private HashMap <String, Movie> moviesList;
	private String movieNameSelected;
	private Movie movieSelected;
	
		
	/**
	 * Constructor
	 * 
	 * @param applicationName	Name of the application
	 */
	public MainController(String applicationName) {
		
		configManager = new ConfigManager(this, "datas/config.cfg");
		applicationConfig = configManager.loadConfig();
		
		loadLanguage(applicationConfig);
		
		mainWindow = new MainWindow(this, applicationName);
		optionWindow = new OptionWindow(this);
		aboutWindow = new AboutWindows(this);
		
		moviesManager = new MoviesManager(this);
		moviesFilterManager = new MoviesFilterManager(this);
		optionManager = new OptionManager(this);	
		imdbManager = new ImdbManager(applicationConfig.getImdbApiKey());
	}

	
	/**
	 *	 Load all the preferences set by user in the options windows
	 */
	private void loadLanguage(Config config) {
		languageSelected = new Language();	
		languageSelected.loadLanguage(config.getLanguageSelected());
	}


	/** 
	 * 	Start the application 
	 */
	public void startApplication() {

		// create the mainWindow
		mainWindow.initialize();
		
		// load and display the movies from the saved file
		moviesList = moviesManager.loadMoviesFromFile(applicationConfig.getUrlSavedFile());
		if (moviesList != null)
			displayMoviesList(moviesList, true);
		else {
			moviesList = new HashMap<>();
			mainWindow.getMainMenuBar().enableButton(false);
		}
		
		mainWindow.getFootbarSection().displayStatus(languageSelected.getApplicationStartedSucces());
	}

	
	/**
	 * 	If there is any modification, the configuration is saved
	 */
	public void applyAndSaveConfig() {
		
		boolean needToSave = false;
		
		if (!optionWindow.getApiKeyTextField().getText().equals("")) {
			applicationConfig.setImdbApiKey(optionWindow.getApiKeyTextField().getText());
			needToSave = true;
		}
		
		if (!optionWindow.getVlcLocationTextField().getText().equals("")) {
			applicationConfig.setVlcLocation(optionWindow.getVlcLocationTextField().getText());
			needToSave = true;
		}
		
		if (needToSave)
			configManager.saveConfig(applicationConfig);
	}
	
	
	/**
	 * 	Fill and display the movies list into the UI movie list section
	 * 
	 * 	@param 	moviesList			List of movies to display
	 * 	@param	isFiltersEmpty		Indicate if there is any filter used
	 */
	public void displayMoviesList(HashMap<String, Movie> moviesList, boolean isFiltersEmpty) {
		
		/*
		 *  Sort the movies list by A-Z then fill the UI movies list
		 *  Fill/Update the details section with the first movie
		 *  Initialise the filters if needed
		 */
		Map<String, Movie> moviesListSorted = new TreeMap<String, Movie>(moviesList);
		
		if (moviesListSorted != null && !moviesListSorted.isEmpty()) {
			String [] moviesTitlesList = getMoviesName(moviesListSorted);
			mainWindow.updateMovies(moviesTitlesList);
			
			if (moviesTitlesList.length > 0) {
				movieSelected = moviesList.get(moviesTitlesList[0]);
				mainWindow.updateDetailSection(movieSelected);
				mainWindow.getMainMenuBar().enableButton(true);
			}
			else 
				mainWindow.getMainMenuBar().enableButton(false);
			
			
			if (isFiltersEmpty) {
				moviesFilterManager.loadFilterChoices(moviesListSorted);
				mainWindow.updateFilters();
			}
		}
	}
	
	
	/**
	 * 	Display the NewEditSection 
	 * 
	 * 	@param	isInCreationMode	Indicate if the NewEditSection is display in creation or edit mode 
	 */
	public void displayNewEditSection(boolean isInCreationMode) {
		
		if (!mainWindow.getNewEditSection().isVisible()) {
		
			/*
			 * In creation mode : all textFieds are emptied
			 * In edit mode	: fill the textFieds with the selected movie informations
			 */
			if (isInCreationMode) {
				mainWindow.getNewEditSection().clearTextfields();
				mainWindow.getNewEditSection().setCreateMode(true);
			}
			else {
				mainWindow.getNewEditSection().fillTextfields(movieSelected, true);
				mainWindow.getNewEditSection().setCreateMode(false);
			}
			
			mainWindow.displayDetailSection(false);
		}
	}
	
	
	/**
	 * 	Update the detail section with the selected movie
	 */
	public void updateSelectedMovie() {
		if (movieNameSelected != null) {
			movieSelected = moviesList.get(movieNameSelected);
			mainWindow.updateDetailSection(movieSelected);
		}
	}
	
	/**
	 * 	Apply filters to the movies List
	 * 
	 * 	@return	true if at least 1 movie is found
	 */
	public boolean applyFilters() {
		
		/*
		 * Load the movies list depending on the filters selected then update the UI list
		 */
		HashMap<String, Movie> moviesListFilter = moviesFilterManager.applyFilters(moviesList);
		
		if (!moviesListFilter.isEmpty()) {
			displayMoviesList(moviesListFilter, false);
			return true;
		}
		
		mainWindow.displayInfoErrorPopup(languageSelected.getFilmNotFound());
		
		return false;
	}
	
	/**
	 * 	Remove all filters applied to the movies List
	 * 
	 * 	@param	isFilterEmpty	Indicate if there is any filters used
	 */
	public void removeFilters(boolean isFilterEmpty) {
		displayMoviesList(moviesList, isFilterEmpty);
	}

	
	/**
	 * 	get a movie from IMDB api by ID
	 * 
	 * 	@param	imdbId	ID of the movie on IMDB website
	 */
	public void getMovieFromImdbById(String imdbId) {
		mainWindow.fillNewEditSection(imdbManager.getMovieFromImdbById(imdbId), false);
	}
	

	/**
	 * 	get a movie from IMDB api by title
	 * 
	 * 	@param	movieTitle	Title of the movie
	 * 	@param	releaseYear	Release year of the movie
	 */
	public void getMovieFromImdbByTitle(String movieTitle, String releaseYear) {
		mainWindow.fillNewEditSection(imdbManager.getMovieFromImdbByTitle(movieTitle, releaseYear), false);
	}
	
	
	/**
	 * 	Add a new movie to the existing movies list
	 * 
	 */
	public void addMovie() {
		Movie newMovie = moviesManager.createNewMovie();
		
		/*
		 * If the new movie doesn't exist already
		 */
		if (!moviesList.containsKey(newMovie.getTitle())) {
			
			moviesList.put(newMovie.getTitle(), newMovie);
			
			// save into the movies list file
			moviesManager.saveMoviesToFile(applicationConfig.getUrlSavedFile(), moviesList);
			
			// refresh the moviesListSection
			mainWindow.displayDetailSection(true);
			displayMoviesList(moviesList, false);
		}
		else
			mainWindow.displayInfoErrorPopup(languageSelected.getMovieSameName());
		
	}
	

	/**
	 * 	Validate and edit a movie from the existing movies list
	 */
	public void editMovie() {
		Movie newMovie = moviesManager.createNewMovie();	
		
		moviesList.replace(newMovie.getTitle(), newMovie);
		
		// save into the file
		moviesManager.saveMoviesToFile(applicationConfig.getUrlSavedFile(), moviesList);

		// remove the filter section and reload the full movie list
		removeFilters(true);
		
		// select the edit movie and refresh the detailSection
		mainWindow.getMoviesListSection().setSelectMovie(movieNameSelected);
		updateSelectedMovie();

		mainWindow.displayDetailSection(true);
	}
	
	
	/**
	 * Validate and remove the selected movie
	 */
	public void deleteMovie() {
		
		if (moviesList.containsKey(movieNameSelected)) {

			moviesList.remove(movieNameSelected);
			moviesManager.saveMoviesToFile(applicationConfig.getUrlSavedFile(), moviesList);
			
			// refresh the moviesListSection
			mainWindow.displayDetailSection(true);
			displayMoviesList(moviesList, false);
		}
	}
	
	
	/**
	 * Update the views counter
	 */
	public void updateViewsCounter() {
		int viewsCounter = Integer.parseInt(movieSelected.getViewsCounter()) + 1;
		movieSelected.setViewsCounter(Integer.toString(viewsCounter));
		
		moviesList.replace(movieSelected.getTitle(), movieSelected);
		
		// save into the file
		moviesManager.saveMoviesToFile(applicationConfig.getUrlSavedFile(), moviesList);

		// remove the filter section and reload the full movie list
		removeFilters(true);
		
		// select the edit movie and refresh the detailSection
		mainWindow.getMoviesListSection().setSelectMovie(movieNameSelected);
		updateSelectedMovie();

		mainWindow.displayDetailSection(true);
	}
	
	
	/**
	 * 	Extract the titles of the movies represented by the key of Map into a Array used by a JList
	 *
	 *	@param	moviesListSorted	Movies list sorted by A-Z
	 *	@return	Movies's titles list
	 */
	private String[] getMoviesName(Map<String, Movie> moviesListSorted) {
		
		if (moviesListSorted != null) {
			String [] moviesTitles = new String[moviesListSorted.size()];
			
			int cpt = 0;
			for (String movie : moviesListSorted.keySet()) {
				moviesTitles[cpt] = movie;
				cpt++;
			}
			
			return moviesTitles;
		}
		
		return null;
	}


	/**
	 * 	Stop and exit the application
	 * 
	 */
	public void exitApplication() {		
		System.exit(0);
	}
	
	
	
	/* ***** GETTERS  ***** */
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public OptionWindow getOptionWindow() {
		return optionWindow;
	}

	public AboutWindows getAboutWindow() {
		return aboutWindow;
	}

	public MoviesManager getMoviesManager() {
		return moviesManager;
	}

	public MoviesFilterManager getMoviesFilterManager() {
		return moviesFilterManager;
	}

	public ImdbManager getImdbManager() {
		return imdbManager;
	}

	public OptionManager getOptionManager() {
		return optionManager;
	}

	public Language getLanguageSelected() {
		return languageSelected;
	}

	public void setMovieNameSelected(String movieNameSelected) {
		this.movieNameSelected = movieNameSelected;
	}

	public Movie getMovieSelected() {
		return movieSelected;
	}

	public Config getApplicationConfig() {
		return applicationConfig;
	}


	public ConfigManager getConfigManager() {
		return configManager;
	}


}


