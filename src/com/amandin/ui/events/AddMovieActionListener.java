package com.amandin.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.amandin.controller.MainController;

/**
 * Add movie listener
 * 
 * @author Alexis Mandin
 *
 */
public class AddMovieActionListener implements ActionListener {
	
	private MainController mainController;

	/**
	 * Constructor
	 * 
	 * @param mainController	Instance of MainController
	 */
	public AddMovieActionListener(MainController mainController) {
		this.mainController = mainController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainController.displayNewEditSection(true);
	}
}
