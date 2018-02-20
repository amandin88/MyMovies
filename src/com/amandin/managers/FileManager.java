package com.amandin.managers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.amandin.controller.MainController;
import com.amandin.managers.movie.Movie;


/**
 *	Save and load the movies list from a file
 * 
 * 	@author Alexis Mandin
 *	@version
 */
public class FileManager {
	
	private MainController mainController;
	
	
	/**
	 * 	Contructor 
	 * 
	 * 	@param mainController	Instance of MainController
	 */
	public FileManager(MainController mainController) {
		this.mainController = mainController;
	}
	
	
	/**
	 * 	Save the movies list
	 *
	 *	@param	moviesList	Movies list to save
	 *	@param	urlFile		URL from the file to save into
	 */
	public void saveMoviesList(HashMap<String, Movie> moviesList, String urlFile) {
		ObjectOutputStream objOutputStream;
	
		try {	
			File outPutFile = new File(urlFile);
			if (!outPutFile.exists())
				outPutFile.createNewFile();
			
			objOutputStream = new ObjectOutputStream(
								new BufferedOutputStream(
										new FileOutputStream(outPutFile)));

			objOutputStream.writeObject(moviesList);;			
			objOutputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}
	
	
	/**
	 * 	Read and load the movies List
	 * 
	 * 	@param	urlFile	URL of the file to read
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Movie> readMoviesList(String urlFile) {
		
		HashMap <String, Movie> moviesList = null;
		ObjectInputStream objInpuStream;
		
		try {	      	
			objInpuStream = new ObjectInputStream(
								new BufferedInputStream(
										new FileInputStream(
												new File(urlFile))));

			try {
				moviesList = (HashMap<String, Movie>) objInpuStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			objInpuStream.close();
    	
		} catch (FileNotFoundException e) {
			mainController.getMainWindow().getFootbarSection().displayStatus(mainController.getLanguageSelected().getNoMoviesToLoad());
		} catch (IOException e) {
			e.printStackTrace();
		}    
				
		return moviesList;
	}
}
