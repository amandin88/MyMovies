package com.amandin.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.amandin.controller.MainController;


/**
 * Edit movie listener
 * 
 * @author Alexis Mandin
 */
public class EditMovieActionListener implements ActionListener {

	private MainController mainController;
	
	
	/**
	 * Constructor
	 * 
	 * @param mainController	Instance of MainController
	 */
	public EditMovieActionListener(MainController mainController) {
		this.mainController = mainController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainController.displayNewEditSection(false);
	}

}
