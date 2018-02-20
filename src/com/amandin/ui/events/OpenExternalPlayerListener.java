package com.amandin.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.amandin.controller.MainController;


/**
 * Open external video player listener
 * 
 * @author Alexis Mandin
 */
public class OpenExternalPlayerListener implements ActionListener {
	
	private MainController mainController;

	
	/**
	 * Constructor
	 * 
	 * @param mainController	Instance of MainController
	 */
	public OpenExternalPlayerListener(MainController mainController) {
		this.mainController = mainController;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainController.getMoviesManager().readMovieWithExternalPlayer(mainController.getMovieSelected());
		mainController.updateViewsCounter();
		
		if (mainController.getMainWindow().getDetailSection().getPreviewPlayer().isPlaying())
			mainController.getMainWindow().getDetailSection().getPreviewPlayer().pauseMovie();
	}

}
