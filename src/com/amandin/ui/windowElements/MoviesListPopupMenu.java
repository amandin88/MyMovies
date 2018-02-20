package com.amandin.ui.windowElements;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import com.amandin.controller.MainController;
import com.amandin.managers.lang.LanguagesEnum;
import com.amandin.ui.events.AddMovieActionListener;
import com.amandin.ui.events.DeleteMovieActionListener;
import com.amandin.ui.events.EditMovieActionListener;
import com.amandin.ui.events.OpenExternalPlayerListener;

public class MoviesListPopupMenu extends JPopupMenu {

	
	private MainController mainController;
	
	private JMenuItem	addMovieMenuItem,
						editMovieMenuItem,
						deleteMovieMenuItem,
						readExternalMenuItem;
	
	
	public MoviesListPopupMenu(MainController mainController) {
		
		this.mainController = mainController;
		
		buildMovieListPopupMenu();
	}

	
	private void buildMovieListPopupMenu() {
		
		String urlFolderMenuIcons = "ressources/icons/16/";
		
		addMovieMenuItem = new JMenuItem(mainController.getLanguageSelected().getAddMovie());
		addMovieMenuItem.addActionListener(new AddMovieActionListener(mainController));
		addMovieMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "addVideoIcon.png"));
		this.add(addMovieMenuItem);
		this.addSeparator();
		
		editMovieMenuItem = new JMenuItem(mainController.getLanguageSelected().getEditMovie());
		editMovieMenuItem.addActionListener(new EditMovieActionListener(mainController));
		editMovieMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "editVideoIcon.png"));
		this.add(editMovieMenuItem);
		
		deleteMovieMenuItem = new JMenuItem(mainController.getLanguageSelected().getDeleteMovie());
		deleteMovieMenuItem.addActionListener(new DeleteMovieActionListener(mainController));
		deleteMovieMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "deleteVideoIcon.png"));
		this.add(deleteMovieMenuItem);
		this.addSeparator();
		
		readExternalMenuItem = new JMenuItem(mainController.getLanguageSelected().getWatchWithExternalPlayer());
		readExternalMenuItem.addActionListener(new OpenExternalPlayerListener(mainController));
		readExternalMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "playVideoIcon.png"));
		this.add(readExternalMenuItem);
		
		
		// set shortcuts
		if (mainController.getLanguageSelected().getLangagueEnum() == LanguagesEnum.ENGLISH) {
			addMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			editMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			readExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		}
		else if (mainController.getLanguageSelected().getLangagueEnum() == LanguagesEnum.FRENCH) {
			addMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			editMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			readExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		}
		/*
		else if (mainController.getLanguageSelected().getLangagueEnum() == LanguagesEnum.GERMAIN) {
			addMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
			editMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			readExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		}
		else {
			addMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
			editMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMovieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			readExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		}
		*/
	}
}
