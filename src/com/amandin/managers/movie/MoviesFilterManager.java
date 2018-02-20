package com.amandin.managers.movie;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.amandin.controller.MainController;
import com.amandin.ui.windowElements.MainFilterSection;


/**
 * Manage the filters from the movies list
 * 
 * @author Alexis Mandin
 * @version
 */
public class MoviesFilterManager {

	private MainController mainController;
	
	private String [] 	moviesTypesList,
						moviesDirectorsList,
						moviesReleaseYearsList;
	
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 */
	public MoviesFilterManager(MainController mainController) {
	
		this.mainController = mainController;		
	}

	
	/**
	 * Load the values of the different filters
	 * 
	 * @param moviesListSorted	Movies list to get the values from
	 */
	public void loadFilterChoices(Map<String, Movie> moviesListSorted) {
		
		moviesTypesList = loadMoviesTypesList(moviesListSorted);
		moviesDirectorsList = loadMoviesDirectorsList(moviesListSorted);
		moviesReleaseYearsList = loadMoviesReleaseYearsList(moviesListSorted);
	}


	/**
	 * Load the values for the release year filter
	 * 
	 * @param moviesListSorted	Movies list to get the values from
	 * @return	Array of release years
	 */
	private String [] loadMoviesReleaseYearsList(Map<String, Movie> moviesListSorted) {
		
		TreeSet<String> releaseYearSet = new TreeSet<>();
		for (Movie movie : moviesListSorted.values()) {
			if (movie.getReleaseYear() != null )
				releaseYearSet.add(movie.getReleaseYear());
		}
		
		return releaseYearSet.toArray(new String[releaseYearSet.size()]);
	}

	
	/**
	 * Load the values for the director filter
	 * 
	 * @param moviesListSorted	Movies list to get the values from
	 * @return	Array of directors
	 */	
	private String [] loadMoviesDirectorsList(Map<String, Movie> moviesListSorted) {
		
		TreeSet<String> directorSet = new TreeSet<>();
		for (Movie movie : moviesListSorted.values()) {
			if (movie.getDirector() != null)
				directorSet.add(movie.getDirector());
		}
		
		return directorSet.toArray(new String[directorSet.size()]);
	}

	
	/**
	 * Load the values for the type filter
	 * 
	 * @param moviesListSorted	Movies list to get the values from
	 * @return	Array of types
	 */
	private String [] loadMoviesTypesList(Map<String, Movie> moviesListSorted) {
		
		TreeSet<String> typeSet = new TreeSet<>();
		for (Movie movie : moviesListSorted.values()) {
			if (movie.getType() != null)
				if (movie.getType().contains(","))
					for (String type : movie.getType().split(",")) {
						typeSet.add(type.replace(" ", ""));
					}
				else 
					typeSet.add(movie.getType());
		}
		
		return typeSet.toArray(new String[typeSet.size()]);
	}

	
	/**
	 * Load the movies list depending on the filters applied
	 * 
	 * @param moviesList	Movies list to be filtered
	 * @return	Movies list filtered
	 */
	public HashMap<String, Movie> applyFilters(HashMap<String, Movie> moviesList) {
	
		MainFilterSection filterUiSection = mainController.getMainWindow().getFilterSection();
		
		String 	titleFilter = filterUiSection.getTitleFilterJTextfield().getText().toUpperCase(),
				typeFilter = filterUiSection.getTypeSelectorList().getSelectedItem().toString(),
				directorFilter = filterUiSection.getDirectorSelectorList().getSelectedItem().toString(),
				releaserYearFilter = filterUiSection.getReleaseYearSelectorList().getSelectedItem().toString();
		
		HashMap<String, Movie> moviesListFiltered = new HashMap<>();
		
				
		// title + type + director + year
		if (!titleFilter.equals("") && !typeFilter.equals("-") && !directorFilter.equals("-") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter) 
						&& movie.getType() != null && movie.getType().contains(typeFilter) 
						&& movie.getDirector() != null && movie.getDirector().contains(directorFilter) 
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// title + type + director 
		else if (!titleFilter.equals("") && !typeFilter.equals("-") && !directorFilter.equals("-")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter) 
						&& movie.getType() != null && movie.getType().contains(typeFilter) 
						&& movie.getDirector() != null && movie.getDirector().contains(directorFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// title + type + year
		else if (!titleFilter.equals("") && !typeFilter.equals("-") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter) 
						&& movie.getType() != null && movie.getType().contains(typeFilter)  
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// title + director + year
		else if (!titleFilter.equals("") && !directorFilter.equals("-") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter)  
						&& movie.getDirector() != null && movie.getDirector().contains(directorFilter) 
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}	
		// type + director + year
		else if (!typeFilter.equals("-") && !directorFilter.equals("-") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getType() != null && movie.getType().contains(typeFilter) 
						&& movie.getDirector() != null && movie.getDirector().contains(directorFilter) 
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// title + type
		else if (!titleFilter.equals("") && !typeFilter.equals("-")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter) 
						&& movie.getType() != null && movie.getType().contains(typeFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// title + director
		else if (!titleFilter.equals("") && !directorFilter.equals("-")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter) 
						&& movie.getDirector() != null && movie.getDirector().contains(directorFilter) ) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// title + year
		else if (!titleFilter.equals("") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter) 
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// type + director 
		else if (!typeFilter.equals("-") && !directorFilter.equals("-")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getType() != null && movie.getType().contains(typeFilter) 
						&& movie.getDirector() != null && movie.getDirector().contains(directorFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		// type + year
		else if (!typeFilter.equals("-") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getType() != null && movie.getType().contains(typeFilter) 
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}			
		// director + year
		else if (!directorFilter.equals("-") && !releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getDirector() != null && movie.getDirector().contains(directorFilter) 
						&& movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}				
		//** title	
		else if (!titleFilter.equals("")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getTitle().toUpperCase().contains(titleFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		//** type
		else if (!typeFilter.equals("-")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getType() != null && movie.getType().contains(typeFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		//** director 
		else if (!directorFilter.equals("-")) {
			for (Movie movie : moviesList.values()) {
				if (movie.getDirector() != null && movie.getDirector().contains(directorFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}
		//** year
		else if (!releaserYearFilter.equals("-") ) {
			for (Movie movie : moviesList.values()) {
				if (movie.getReleaseYear() != null && movie.getReleaseYear().contains(releaserYearFilter)) {
					
					moviesListFiltered.put(movie.getTitle(), movie);
				}
			}
		}			
		
		return moviesListFiltered;
	}

	
	
	
	/*
	 * ****GETTERS
	 */


	public String[] getMoviesTypesList() {
		return moviesTypesList;
	}


	public String[] getMoviesDirectorsList() {
		return moviesDirectorsList;
	}


	public String[] getMoviesReleaseYearsList() {
		return moviesReleaseYearsList;
	}
}
