package com.amandin.managers.movie;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.amandin.controller.MainController;
import com.amandin.managers.FileManager;
import com.amandin.managers.PosterManager;
import com.amandin.ui.windowElements.MainNewEditSection;


/**
 * Movie manager
 * 
 * @author Alexis Mandin
 * @version
 */
public class MoviesManager {
	
	private MainController mainController;
	private HashMap <String, Movie> moviesList;
	private FileManager fileManager;
	private PosterManager posterManager;

	
	/**
	 * Constructor
	 * 
	 * @param mainController	Instance of MainController
	 */
	public MoviesManager(MainController mainController) {
		
		this.mainController = mainController;
		fileManager = new FileManager(mainController);
		posterManager = new PosterManager();
	}

	
	/**
	 * Load the movies list from a file
	 * 
	 * @param urlFile	URL of the file to load from
	 * @return			Movies list
	 */
	public HashMap <String, Movie> loadMoviesFromFile(String urlFile) {
		
		moviesList = fileManager.readMoviesList(urlFile);
		
		return moviesList;
	}
	
	
	/**
	 * Save the movies list to a file
	 * 
	 * @param urlFile		URL of the file to save to
	 * @param moviesList	Movies list to save
	 */
	public void saveMoviesToFile(String urlFile, HashMap<String, Movie> moviesList) {
		
		fileManager.saveMoviesList(moviesList, urlFile);
	}


	/**
	 * Create a movie from the NewEditSection
	 * 
	 * @return An object Movie
	 */
	public Movie createNewMovie() {
		
		Movie movie = new Movie();
		MainNewEditSection newEditSection = mainController.getMainWindow().getNewEditSection();
		
		// title
		movie.setTitle(newEditSection.getTitleValue().getText());
		// Type
		if (!newEditSection.getTypeValue().getText().equals(""))
			movie.setType(newEditSection.getTypeValue().getText());
		// Director
		if (!newEditSection.getDirectorValue().getText().equals(""))
			movie.setDirector(newEditSection.getDirectorValue().getText());
		// Length
		if (!newEditSection.getLengthValue().getText().equals(""))
			movie.setLength(newEditSection.getLengthValue().getText());
		// Release Year
		if (!newEditSection.getReleaseYearValue().getText().equals(""))
			movie.setReleaseYear(newEditSection.getReleaseYearValue().getText());
		// actors
		if (!newEditSection.getActorsValue().getText().equals("")) {
			movie.setActors(newEditSection.getActorsValue().getText());
		}
		// IMDB Rate		
		if (!newEditSection.getImdbRateValue().getText().equals(""))
			movie.setImdbRate(newEditSection.getImdbRateValue().getText());
		// Views counters
		if (!newEditSection.getViewsCounterValue().getText().equals(""))
			movie.setViewsCounter(newEditSection.getViewsCounterValue().getText());
		else 
			movie.setViewsCounter("0");
		// Local url
		if (!newEditSection.getLocalUrlValue().getText().equals(mainController.getLanguageSelected().getClickToChooseAMovie())
				&& !newEditSection.getLocalUrlValue().getText().equals(""))
			movie.setLocalUrl(newEditSection.getLocalUrlValue().getText());
		
		// Synopsis
		if (!newEditSection.getSynopsisValue().getText().equals(""))
			movie.setSynopsis(newEditSection.getSynopsisValue().getText());
		
		// Poster from file selection
		if (!newEditSection.getPosterUrlTextfield().getText().equals(mainController.getLanguageSelected().getClickToChoosePoster())
				&& !newEditSection.getPosterUrlTextfield().getText().equals("")) {
			movie.setPosterUrl(newEditSection.getPosterUrlTextfield().getText());
		}
		// Poster downloaded from IMDB
		else if (newEditSection.getPosterURL() != null) {
			byte [] poster = posterManager.downloadImage(newEditSection.getPosterURL());
			
			if (poster != null) {
				String urlDestination = "ressources/posters/"+movie.getTitle()+".jpg";
				posterManager.saveImage(poster, urlDestination);
				
				movie.setPosterUrl(urlDestination);
			}
		}
			
		return movie;
	}

	
	/**
	 * Call the deletion of a poster
	 * 
	 * @param posterUrl	URL to the poster to delete
	 */
	public void removePosterFromLocal(String posterUrl) {
		posterManager.removePoster(posterUrl);	
	}
	
	
	/**
	 * Call the default application to read the movie
	 * 
	 * @param movie Movie to read
	 */
	public void readMovieWithExternalPlayer(Movie movie) {
		
		File movieUrl = new File(movie.getLocalUrl());
		
		try {
			if (Desktop.isDesktopSupported())
				Desktop.getDesktop().open(movieUrl);
		} catch (IOException e) {
			mainController.getMainWindow().displayInfoErrorPopup("");
		}
	}
}
