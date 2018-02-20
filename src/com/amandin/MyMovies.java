package com.amandin;

import com.amandin.controller.MainController;

public class MyMovies {

	static String applicationName = "MyMovies - Alpha v0.2";
	static MainController mainController;
	
	public static void main(String[] args) {

		mainController = new MainController(applicationName);	
		
		if (mainController != null) {
			mainController.startApplication();
		}
		else {
			System.err.println("The application failed to start.");
			System.exit(-1);
		} 
	} 
}
