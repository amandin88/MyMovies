package com.amandin.ui.windowElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.amandin.controller.MainController;
import com.amandin.managers.lang.Language;


/**
 * UI Filters section
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainFilterSection extends JPanel {

	private MainController mainController;
	
	private JLabel 	titleLabel,
					typeLabel,
					directorLabel,
					releaseYearLabel;
	
	private JTextField titleFilterJTextfield; 
	private JComboBox<String> 	typeSelectorList,
								directorSelectorList,
								releaseYearSelectorList;
	
	private JButton applyFilterButton,
					removeFilterButton;
	
	private Language languageSelected;
	private Dimension contentSize;
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 * @param contentSize		Size of the contentPane
	 */
	public MainFilterSection(MainController mainController, Dimension contentSize) {
		
		this.mainController = mainController;
		this.contentSize = contentSize;
		languageSelected = this.mainController.getLanguageSelected();
	}

	
	/**
	 * Create the filter section
	 */
	public void createFilterSection() {
		
		setLayout(new GridLayout(5, 2, 0, 5));
		setPreferredSize(new Dimension((int)contentSize.getWidth()-25, 175));
		setBackground(Color.DARK_GRAY);
		
		
		TitledBorder titleBorder = BorderFactory.createTitledBorder(languageSelected.getFilter());
		titleBorder.setTitleColor(Color.WHITE);
		titleBorder.setTitleJustification(TitledBorder.RIGHT);
		setBorder(titleBorder);
		
		// Filters labels
		titleLabel = new JLabel(languageSelected.getTitle());
		typeLabel = new JLabel(languageSelected.getType());
		directorLabel = new JLabel(languageSelected.getDirector());
		releaseYearLabel = new JLabel(languageSelected.getReleaseYear());
		
		titleLabel.setForeground(Color.WHITE);
		typeLabel.setForeground(Color.WHITE);
		directorLabel.setForeground(Color.WHITE);
		releaseYearLabel.setForeground(Color.WHITE);
		
		// Filters values
		titleFilterJTextfield = new JTextField("");
		typeSelectorList = new JComboBox<>();
		directorSelectorList = new JComboBox<>();
		releaseYearSelectorList = new JComboBox<>();
		
		titleFilterJTextfield.setBackground(Color.GRAY);
		titleFilterJTextfield.setForeground(Color.WHITE);
		titleFilterJTextfield.setBorder(BorderFactory.createEmptyBorder());
		
		typeSelectorList.setBackground(Color.GRAY);
		typeSelectorList.setForeground(Color.WHITE);
		typeSelectorList.setBorder(BorderFactory.createEmptyBorder());
		
		directorSelectorList.setBackground(Color.GRAY);
		directorSelectorList.setForeground(Color.WHITE);
		directorSelectorList.setBorder(BorderFactory.createEmptyBorder());
		
		releaseYearSelectorList.setBackground(Color.GRAY);
		releaseYearSelectorList.setForeground(Color.WHITE);
		releaseYearSelectorList.setBorder(BorderFactory.createEmptyBorder());
		
		
		JPanel filterButtonPanel = new JPanel();
		filterButtonPanel.setBackground(Color.DARK_GRAY);
		
		applyFilterButton = new JButton(languageSelected.getOk());
		applyFilterButton.setPreferredSize(new Dimension(35, 20));
		applyFilterButton.setMargin(new Insets(1, 1, 1, 1));
		applyFilterButton.setBackground(Color.GRAY);
		applyFilterButton.setForeground(Color.WHITE);
		
		removeFilterButton = new JButton(languageSelected.getDelete());
		removeFilterButton.setPreferredSize(new Dimension(75, 20));
		removeFilterButton.setMargin(new Insets(1, 1, 1, 1));
		removeFilterButton.setEnabled(false);
		removeFilterButton.setBackground(Color.GRAY);
		removeFilterButton.setForeground(Color.WHITE);
		
		filterButtonPanel.add(applyFilterButton);
		filterButtonPanel.add(removeFilterButton);
		
		add(titleLabel);
		add(titleFilterJTextfield);
		add(typeLabel);
		add(typeSelectorList);
		add(directorLabel);
		add(directorSelectorList);
		add(releaseYearLabel);
		add(releaseYearSelectorList);
		add(new JLabel());
		add(filterButtonPanel);
		
		
		
		/*
		 * Add actionListeners
		 */
		
		//Call the filter manager if at least 1 filter is selected 
		applyFilterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!titleFilterJTextfield.getText().equals("") || !typeSelectorList.getSelectedItem().toString().equals("-")
						|| !directorSelectorList.getSelectedItem().toString().equals("-") || !releaseYearSelectorList.getSelectedItem().toString().equals("-")) {
				
					if (mainController.applyFilters())
						removeFilterButton.setEnabled(true);
				}
			}
		});
		
		
		// Remove all filters and set the filterSection as original state
		removeFilterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.removeFilters(false);
				removeFilterButton.setEnabled(false);
				
				titleFilterJTextfield.setText("");
				typeSelectorList.setSelectedIndex(0);
				directorSelectorList.setSelectedIndex(0);
				releaseYearSelectorList.setSelectedIndex(0);
			}
		});
	}
	

	/**
	 * Update the values the filters selectors
	 */
	public void updateFiltersLists() {
		
		typeSelectorList.removeAllItems();
		typeSelectorList.addItem("-");
		for (String title : mainController.getMoviesFilterManager().getMoviesTypesList()) {
			typeSelectorList.addItem(title);
		}
		
		directorSelectorList.removeAllItems();
		directorSelectorList.addItem("-");
		for (String title : mainController.getMoviesFilterManager().getMoviesDirectorsList()) {
			directorSelectorList.addItem(title);
		}
		
		releaseYearSelectorList.removeAllItems();
		releaseYearSelectorList.addItem("-");
		for (String title : mainController.getMoviesFilterManager().getMoviesReleaseYearsList()) {
			releaseYearSelectorList.addItem(title);
		}
		
		if (removeFilterButton.isEnabled())
			removeFilterButton.setEnabled(false);
	}

	
	/*
	 * GETTERS
	 */

	public JTextField getTitleFilterJTextfield() {
		return titleFilterJTextfield;
	}

	public JComboBox<String> getTypeSelectorList() {
		return typeSelectorList;
	}

	public JComboBox<String> getDirectorSelectorList() {
		return directorSelectorList;
	}

	public JComboBox<String> getReleaseYearSelectorList() {
		return releaseYearSelectorList;
	}	
	
}

