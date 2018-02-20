package com.amandin.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.amandin.controller.MainController;

/**
 * Delete movie listener
 * 
 * @author Alexis Mandin
 *
 */
public class DeleteMovieActionListener implements ActionListener {

	private MainController mainController;
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 */
	public DeleteMovieActionListener(MainController mainController) {
		this.mainController = mainController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int deleteConfirmed = JOptionPane.showConfirmDialog(mainController.getMainWindow(), 
				mainController.getLanguageSelected().getSureToDelete() + mainController.getMovieSelected().getTitle(), 
				mainController.getLanguageSelected().getConfirmation(), JOptionPane.YES_NO_OPTION);

		if (deleteConfirmed == JOptionPane.OK_OPTION) {
		
		int deletePoster = JOptionPane.showConfirmDialog(mainController.getMainWindow(), 
						mainController.getLanguageSelected().getDeletePosterConfirmation(), 
						mainController.getLanguageSelected().getConfirmation(), JOptionPane.YES_NO_OPTION);
		
		if (deletePoster == JOptionPane.OK_OPTION) {
		String posterUrl = mainController.getMovieSelected().getPosterUrl();
		mainController.getMoviesManager().removePosterFromLocal(posterUrl);
		}
		
		mainController.deleteMovie();
		}
	}
}
