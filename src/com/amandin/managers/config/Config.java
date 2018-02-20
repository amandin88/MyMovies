package com.amandin.managers.config;

import java.io.Serializable;

import com.amandin.managers.lang.LanguagesEnum;


/**
 * Config object used to save the application preference
 * 
 * @author Alexis Mandin
 */
public class Config implements Serializable {

	private LanguagesEnum languageSelected;
	
	
	private String imdbApiKey;
	private String urlSavedFile;
	private String vlcLocation;
	
	
	/**
	 * Create a config 
	 * 
	 * @param languageSelected	Language selected
	 */
	public void createConfig(LanguagesEnum languageSelected) {
		
		//imdbApiKey = "6bdd1683";
		//vlcLocation = "C:\\Program Files\\VideoLAN\\VLC";
		urlSavedFile = "datas/moviesList.data";
		
		this.languageSelected = languageSelected;
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */
	public LanguagesEnum getLanguageSelected() {
		return languageSelected;
	}
	

	public void setLanguageSelected(LanguagesEnum languageSelected) {
		this.languageSelected = languageSelected;
	}

	public String getImdbApiKey() {
		return imdbApiKey;
	}

	public String getUrlSavedFile() {
		return urlSavedFile;
	}

	public String getVlcLocation() {
		return vlcLocation;
	}

	
	public void setImdbApiKey(String imdbApiKey) {
		this.imdbApiKey = imdbApiKey;
	}

	public void setUrlSavedFile(String urlSavedFile) {
		this.urlSavedFile = urlSavedFile;
	}

	public void setVlcLocation(String vlcLocation) {
		this.vlcLocation = vlcLocation;
	}
	
	
}
