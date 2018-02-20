package com.amandin.generation;

import com.amandin.controller.MainController;

public class MoviesFinderManager {

	
	private MainController mainController;
	private MoviesFinder moviesFinder;
	
	public MoviesFinderManager(MainController mainController) {
		
		this.mainController = mainController;
		
		moviesFinder = new MoviesFinder();
	}
}
