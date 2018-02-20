package com.amandin.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.amandin.controller.MainController;
import com.amandin.managers.lang.LanguagesEnum;

/**
 * Language listener
 * @author Alexis Mandin
 *
 */
public class LanguageActionListener implements ActionListener {
	
	private MainController mainController;
	private JMenuItem languageMenuItem;

	
	/**
	 * Constructeur
	 * 
	 * @param mainController	Instance of MainController
	 * @param englishMenuItem	SubMenu item of the language selected
	 */
	public LanguageActionListener(MainController mainController,  JMenuItem menuItem) {
		this.mainController = mainController;
		this.languageMenuItem = menuItem;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean languageChanged = false; 
		
		if (languageMenuItem.getText() == mainController.getLanguageSelected().getEnglish()) {
			if (mainController.getApplicationConfig().getLanguageSelected() != LanguagesEnum.ENGLISH) {
				mainController.getApplicationConfig().setLanguageSelected(LanguagesEnum.ENGLISH);
				mainController.getConfigManager().saveConfig(mainController.getApplicationConfig());
				
				languageChanged = true;
			}
		}
		else if (languageMenuItem.getText() == mainController.getLanguageSelected().getFrench()) {
			if (mainController.getApplicationConfig().getLanguageSelected() != LanguagesEnum.FRENCH) {
				mainController.getApplicationConfig().setLanguageSelected(LanguagesEnum.FRENCH);
				mainController.getConfigManager().saveConfig(mainController.getApplicationConfig());
				
				languageChanged = true;
			}
		}
		else if (languageMenuItem.getText() == mainController.getLanguageSelected().getGermain()) {
			if (mainController.getApplicationConfig().getLanguageSelected() != LanguagesEnum.GERMAIN) {
				mainController.getApplicationConfig().setLanguageSelected(LanguagesEnum.GERMAIN);
				mainController.getConfigManager().saveConfig(mainController.getApplicationConfig());
				
				languageChanged = true;
			}
		}
		else {
			if (mainController.getApplicationConfig().getLanguageSelected() != LanguagesEnum.SPANISH) {
				mainController.getApplicationConfig().setLanguageSelected(LanguagesEnum.SPANISH);
				mainController.getConfigManager().saveConfig(mainController.getApplicationConfig());
				
				languageChanged = true;	
			}
		}
		
		if (languageChanged) {
			mainController.getMainWindow().getMainMenuBar().setLanguageSelectedInMenu();
			if (mainController.getApplicationConfig().getLanguageSelected() != mainController.getLanguageSelected().getLangagueEnum())
				mainController.getMainWindow().displayInfoErrorPopup(mainController.getLanguageSelected().getRestartToUpdateLanguage());
		}
	}	
}
