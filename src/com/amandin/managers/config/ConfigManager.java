package com.amandin.managers.config;

import com.amandin.managers.config.Config;
import com.amandin.managers.lang.LanguagesEnum;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.amandin.controller.MainController;

/**
 * Config manager
 * 
 * @author Alexis Mandin
 */
public class ConfigManager {

	private MainController mainController;
	private String configFileUrl;
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 * @param string			URL to the config file
	 */
	public ConfigManager(MainController mainController, String configFileUrl) {
		
		this.mainController = mainController;
		this.configFileUrl = configFileUrl;
	}


	/**
	 * Load the Config
	 * 
	 * @return	A saved configuration
	 */
	public Config loadConfig() {
		
		Config config = new Config();
		ObjectInputStream objInpuStream;
		
		try {	      	
			objInpuStream = new ObjectInputStream(
								new BufferedInputStream(
										new FileInputStream(
												new File(configFileUrl))));

			try {
				config = (Config) objInpuStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			objInpuStream.close();
    	
		} catch (FileNotFoundException e) {
			
			config.createConfig(LanguagesEnum.ENGLISH);
			saveConfig(config);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return config;
	}

	
	/**
	 * Save the Config
	 * 
	 * @param The config to save
	 */
	public void saveConfig (Config config) {
		ObjectOutputStream objOutputStream;
				
		try {	
			File outPutFile = new File(configFileUrl);
			if (!outPutFile.exists())
				outPutFile.createNewFile();
			
			objOutputStream = new ObjectOutputStream(
								new BufferedOutputStream(
										new FileOutputStream(outPutFile)));

			objOutputStream.writeObject(config);			
			objOutputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
