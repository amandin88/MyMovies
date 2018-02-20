package com.amandin.ui.windowElements;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.amandin.controller.MainController;
import com.amandin.managers.lang.Language;
import com.amandin.managers.lang.LanguagesEnum;
import com.amandin.ui.events.AddMovieActionListener;
import com.amandin.ui.events.DeleteMovieActionListener;
import com.amandin.ui.events.EditMovieActionListener;
import com.amandin.ui.events.LanguageActionListener;
import com.amandin.ui.events.OpenExternalPlayerListener;

/**
 * UI Menubar and Toolbar
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainMenuBar {
	
	private JMenuBar menuBar;
	private JToolBar toolBar;
	
	private JMenu 	fileMenu, toolsMenu, helpMenu, languageMenu;
	
	private JMenuItem 	addMenuItem,
						editMenuItem, 
						deleteMenuItem ,
						closeApplicationMenuItem, 
						readMovieExternalMenuItem, 
						englishMenuItem,
						frenchMenuItem,		
						germainMenuItem,
						spanishMenuItem,	
						preferencesMenuItem; 
	
	private JButton 	addToolButton, 
						editToolButton,
						deleteToolButton, 
						readMovieExternalButton;	
				
	private MainController mainController;
	private Language languageSelected;
	private String 	urlFolderMenuIcons,
					urlFolderButtonIcons;
	
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 */
	public MainMenuBar(MainController mainController) {
		this.mainController = mainController;
		this.languageSelected = mainController.getLanguageSelected();
		urlFolderMenuIcons = "ressources/icons/16/";
		urlFolderButtonIcons = "ressources/icons/24/";
	}
	
	
	/**
	 * Create the MenuBar
	 * 
	 * @return	A MenuBar
	 */
	public JMenuBar createMenuBar() {
		
		menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		menuBar.setBackground(Color.DARK_GRAY);
		
		
		// File menu
		fileMenu = new JMenu(languageSelected.getFile());
		fileMenu.setForeground(Color.WHITE);
		
		addMenuItem = new JMenuItem(languageSelected.getAddMovie());
		addMenuItem.addActionListener(new AddMovieActionListener(mainController));
		addMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "addVideoIcon.png"));		
		addMenuItem.setBackground(Color.GRAY);
		addMenuItem.setForeground(Color.WHITE);
		fileMenu.add(addMenuItem);
		
		editMenuItem = new JMenuItem(languageSelected.getEditMovie());
		editMenuItem.addActionListener(new EditMovieActionListener(mainController));
		editMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "editVideoIcon.png"));
		editMenuItem.setBackground(Color.GRAY);
		editMenuItem.setForeground(Color.WHITE);
		fileMenu.add(editMenuItem);
		
		deleteMenuItem = new JMenuItem(languageSelected.getDeleteMovie());
		deleteMenuItem.addActionListener(new DeleteMovieActionListener(mainController));
		deleteMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "deleteVideoIcon.png"));
		deleteMenuItem.setBackground(Color.GRAY);
		deleteMenuItem.setForeground(Color.WHITE);
		fileMenu.add(deleteMenuItem);
		
		fileMenu.addSeparator();
		readMovieExternalMenuItem = new JMenuItem(languageSelected.getWatchWithExternalPlayer());
		readMovieExternalMenuItem.addActionListener(new OpenExternalPlayerListener(mainController));
		readMovieExternalMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "playVideoIcon.png"));
		readMovieExternalMenuItem.setBackground(Color.GRAY);
		readMovieExternalMenuItem.setForeground(Color.WHITE);
		fileMenu.add(readMovieExternalMenuItem);
		
		fileMenu.addSeparator();
		closeApplicationMenuItem = new JMenuItem(languageSelected.getCloseApplication());
		closeApplicationMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "exitIcon.png"));
		closeApplicationMenuItem.setBackground(Color.GRAY);
		closeApplicationMenuItem.setForeground(Color.WHITE);
		closeApplicationMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.exitApplication();
			}
		});
		
		// set shortcuts
		if (languageSelected.getLangagueEnum() == LanguagesEnum.ENGLISH) {
			addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			editMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			readMovieExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
			closeApplicationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK)); 
		}
		else if (languageSelected.getLangagueEnum() == LanguagesEnum.FRENCH) {
			addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			editMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			readMovieExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
			closeApplicationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK)); 
		}
		/*
		else if (languageSelected.getLangagueEnum() == LanguagesEnum.GERMAIN) {
			addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
			editMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			readMovieExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
			closeApplicationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); 
		}
		else {
			addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
			editMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			readMovieExternalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
			closeApplicationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); 
		}
		*/
		
		fileMenu.add(closeApplicationMenuItem);
		
		menuBar.add(fileMenu);
		
		
		// Tools menu
		toolsMenu = new JMenu(languageSelected.getTools());
		toolsMenu.setBackground(Color.GRAY);
		toolsMenu.setForeground(Color.WHITE);
		
		preferencesMenuItem = new JMenuItem(languageSelected.getPreferences());
		preferencesMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "configIcon.png"));
		preferencesMenuItem.setBackground(Color.GRAY);
		preferencesMenuItem.setForeground(Color.WHITE);
		preferencesMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectionValue = JOptionPane.showConfirmDialog(null, mainController.getOptionWindow(), languageSelected.getPreferences(), JOptionPane.OK_CANCEL_OPTION);
				
				if (selectionValue == JOptionPane.OK_OPTION) {
					mainController.applyAndSaveConfig();
				}
			}
		});
		toolsMenu.add(preferencesMenuItem);
		
		menuBar.add(toolsMenu);
		
		
		// Languages
		languageMenu = new JMenu(mainController.getLanguageSelected().getLanguages());
		languageMenu.setBackground(Color.GRAY);
		languageMenu.setForeground(Color.WHITE);
		
		englishMenuItem = new JMenuItem(mainController.getLanguageSelected().getEnglish());
		englishMenuItem.addActionListener(new LanguageActionListener(mainController, englishMenuItem));
		englishMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "englishIcon.png"));
		englishMenuItem.setBackground(Color.GRAY);
		englishMenuItem.setForeground(Color.WHITE);
		
		frenchMenuItem = new JMenuItem(mainController.getLanguageSelected().getFrench());
		frenchMenuItem.addActionListener(new LanguageActionListener(mainController, frenchMenuItem));
		frenchMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "frenchIcon.png"));
		frenchMenuItem.setBackground(Color.GRAY);
		frenchMenuItem.setForeground(Color.WHITE);
		
		germainMenuItem = new JMenuItem(mainController.getLanguageSelected().getGermain());
		germainMenuItem.addActionListener(new LanguageActionListener(mainController, germainMenuItem));
		germainMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "germainIcon.png"));
		germainMenuItem.setBackground(Color.GRAY);
		germainMenuItem.setForeground(Color.WHITE);
		
		spanishMenuItem = new JMenuItem(mainController.getLanguageSelected().getSpanish());
		spanishMenuItem.addActionListener(new LanguageActionListener(mainController, spanishMenuItem));
		spanishMenuItem.setIcon(new ImageIcon(urlFolderMenuIcons + "spanishIcon.png"));
		spanishMenuItem.setBackground(Color.GRAY);
		spanishMenuItem.setForeground(Color.WHITE);
		
		germainMenuItem.setEnabled(false);
		spanishMenuItem.setEnabled(false);
		
		languageMenu.add(englishMenuItem);
		languageMenu.add(frenchMenuItem);
		languageMenu.add(germainMenuItem);
		languageMenu.add(spanishMenuItem);
		setLanguageSelectedInMenu();
		
		menuBar.add(languageMenu);
		
		
		// Help menu
		helpMenu = new JMenu(languageSelected.getHelp());
		helpMenu.setForeground(Color.WHITE);
		
		menuBar.add(helpMenu);
		
		return menuBar;
	}

	
	/**
	 * Create a ToolBar
	 * 
	 * @return A ToolBar
	 */
	public JToolBar createToolBar() {
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		toolBar.setBackground(Color.DARK_GRAY);

		// new movie button
		toolBar.addSeparator();
		addToolButton = new JButton();
		addToolButton.addActionListener(new AddMovieActionListener(mainController));
		addToolButton.setIcon(new ImageIcon(urlFolderButtonIcons + "addVideoIcon.png"));
		addToolButton.setToolTipText(mainController.getLanguageSelected().getAddMovie());
		addToolButton.setBackground(Color.LIGHT_GRAY);
		toolBar.add(addToolButton);
		
		// edit movie button
		editToolButton = new JButton();
		editToolButton.addActionListener(new EditMovieActionListener(mainController));
		editToolButton.setIcon(new ImageIcon(urlFolderButtonIcons + "editVideoIcon.png"));
		editToolButton.setToolTipText(mainController.getLanguageSelected().getEditMovie());
		editToolButton.setBackground(Color.LIGHT_GRAY);
		toolBar.add(editToolButton);
		
		// delete movie button
		deleteToolButton = new JButton();
		deleteToolButton.addActionListener(new DeleteMovieActionListener(mainController));
		deleteToolButton.setIcon(new ImageIcon(urlFolderButtonIcons + "deleteVideoIcon.png"));
		deleteToolButton.setToolTipText(mainController.getLanguageSelected().getDeleteMovie());
		deleteToolButton.setBackground(Color.LIGHT_GRAY);
		toolBar.add(deleteToolButton);
		
		// read movie with external button
		toolBar.addSeparator();
		readMovieExternalButton = new JButton();
		readMovieExternalButton.addActionListener(new OpenExternalPlayerListener(mainController));
		readMovieExternalButton.setIcon(new ImageIcon(urlFolderButtonIcons + "playVideoIcon.png"));
		readMovieExternalButton.setToolTipText(mainController.getLanguageSelected().getWatchWithExternalPlayer());
		readMovieExternalButton.setBackground(Color.LIGHT_GRAY);
		toolBar.add(readMovieExternalButton);
		
		return toolBar;
	}
	
	
	/**
	 * Enable or disable Edit, Delete and ReadMovie buttons and menu
	 * @param status
	 */
	public void enableButton(boolean status) {
		editMenuItem.setEnabled(status);
		deleteMenuItem.setEnabled(status);
		readMovieExternalMenuItem.setEnabled(status);
		
		editToolButton.setEnabled(status);
		deleteToolButton.setEnabled(status);
		readMovieExternalButton.setEnabled(status);
	}
	
	
	/**
	 * Display the language selected in the subMenu
	 */
	public void setLanguageSelectedInMenu() {
		if (mainController.getApplicationConfig().getLanguageSelected() == LanguagesEnum.ENGLISH) {
			englishMenuItem.setForeground(Color.BLUE);
			frenchMenuItem.setForeground(Color.WHITE);
			germainMenuItem.setForeground(Color.WHITE);
			spanishMenuItem.setForeground(Color.WHITE);
		}
		else if (mainController.getApplicationConfig().getLanguageSelected() == LanguagesEnum.FRENCH) {
			englishMenuItem.setForeground(Color.WHITE);
			frenchMenuItem.setForeground(Color.BLUE);
			germainMenuItem.setForeground(Color.WHITE);
			spanishMenuItem.setForeground(Color.WHITE);
		}
		else if (mainController.getApplicationConfig().getLanguageSelected() == LanguagesEnum.GERMAIN) {
			englishMenuItem.setForeground(Color.WHITE);
			frenchMenuItem.setForeground(Color.WHITE);
			germainMenuItem.setForeground(Color.BLUE);
			spanishMenuItem.setForeground(Color.WHITE);
		}
		else {
			englishMenuItem.setForeground(Color.WHITE);
			frenchMenuItem.setForeground(Color.WHITE);
			germainMenuItem.setForeground(Color.WHITE);
			spanishMenuItem.setForeground(Color.BLUE);
		}
	}
}
