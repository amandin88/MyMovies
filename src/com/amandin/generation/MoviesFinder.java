package com.amandin.generation;

import java.io.File;
import java.util.ArrayList;


/**
 * 
 * 
 * @author Alexis Mandin
 *
 */
public class MoviesFinder {
	
	private ArrayList<File>	moviesListFound;
	
	
	/**
	 * 
	 */
	public MoviesFinder() {
		
		moviesListFound = new ArrayList<>();
	}

	
	/**
	 * 
	 * @param location 
	 */
	public void findMoviesFromLocation(File location) {
	
		for (File file : location.listFiles()) {
			if (file.isDirectory()) {
				findMoviesFromLocation(file);
			} 
			else {
				String extensionFile = file.getAbsolutePath().substring(file.getAbsolutePath().length()-3, file.getAbsolutePath().length());
				extensionFile = extensionFile.toLowerCase();
				
				for (MoviesExtensionEnum extension : MoviesExtensionEnum.values()) {
					if (extensionFile.equals(extension.toString().toLowerCase()) && !moviesListFound.contains(file)) {
						
						moviesListFound.add(file);
						break;
					}
				}
			}
		}
	}
	
	
	/* ****GETTERS  ******/
	public ArrayList<File> getMoviesListFound() {
		return moviesListFound;
	}

}
