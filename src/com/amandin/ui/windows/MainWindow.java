package com.amandin.ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import com.amandin.controller.MainController;
import com.amandin.managers.movie.Movie;
import com.amandin.ui.windowElements.MainDetailSection;
import com.amandin.ui.windowElements.MainFilterSection;
import com.amandin.ui.windowElements.MainFootbarSection;
import com.amandin.ui.windowElements.MainMenuBar;
import com.amandin.ui.windowElements.MainMoviesList;
import com.amandin.ui.windowElements.MainNewEditSection;


/**
 * UI Main window
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainWindow extends JFrame {

	private String applicationName;
	private MainController mainController;
	
	private Container contentPane;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	
	private MainFilterSection filterSection;
	private MainMoviesList moviesListSection;
	private MainDetailSection detailSection;
	private MainNewEditSection newEditSection;
	private MainFootbarSection footbarSection;
	private MainMenuBar mainMenuBar;
	
	private Dimension screenResolution;				
	
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 * @param applicationName	Name of the application
	 */
	public MainWindow(MainController mainController, String applicationName) {
		
		this.applicationName = applicationName;
		this.mainController = mainController;
		
		screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Change some colours	
		UIManager.put("OptionPane.background", Color.DARK_GRAY);
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("Panel.background", Color.DARK_GRAY);
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		UIManager.put("Label.foreground", Color.WHITE);
		UIManager.put("MenuItem.acceleratorForeground", Color.LIGHT_GRAY);
	}

	/**
	 * Initialise the main window
	 */
	public void initialize() {
		
		setTitle(applicationName);
		
		// get the taskBar height
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarHeight = scnMax.bottom+5;
		
		setSize((int)screenResolution.getWidth(), (int)screenResolution.getHeight()-taskBarHeight);
		setMinimumSize(this.getSize());
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		
		/*
		 * create the MenuBar and the ToolBar
		 */
		mainMenuBar = new MainMenuBar(mainController);
		menuBar = mainMenuBar.createMenuBar();
		toolBar = mainMenuBar.createToolBar();
		
		setJMenuBar(menuBar);
		if (toolBar != null)	
			contentPane.add(toolBar, BorderLayout.NORTH);
		
		
		/*
		 * create the filter and the movies list section on the left
		 */
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(this.getWidth()/4, this.getHeight()));
		leftPanel.setBackground(Color.DARK_GRAY);
		
		filterSection = new MainFilterSection(mainController, leftPanel.getPreferredSize());
		filterSection.createFilterSection();
		leftPanel.add(filterSection);
			
		moviesListSection = new MainMoviesList(mainController, leftPanel.getPreferredSize());
		moviesListSection.createMoviesListSection();
		leftPanel.add(moviesListSection);
		
		
		/*
		 * create the detail section on the right
		 */
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(this.getWidth()/4*3, this.getHeight()));
		rightPanel.setBackground(Color.DARK_GRAY);
		
		// detailSection : is hidden when newEditSection is called
		detailSection = new MainDetailSection(mainController, rightPanel.getPreferredSize());
		detailSection.createDetailSection();
		rightPanel.add(detailSection);
		
		
		/*
		 *  newEditSection : hidden at start
		 */
		newEditSection = new MainNewEditSection(mainController, rightPanel.getPreferredSize());
		newEditSection.createNewEditSection();
		newEditSection.setVisible(false);
		rightPanel.add(newEditSection);
		
		
		/*
		 *  add the different sections to MainWindow
		 */
		JSplitPane centerSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		centerSplitPanel.setEnabled(false);
		centerSplitPanel.setDividerLocation(this.getWidth()/4+15);
		contentPane.add(centerSplitPanel, BorderLayout.CENTER);
			
		// splitPane divider colour
		centerSplitPanel.setUI(new BasicSplitPaneUI() {
            @SuppressWarnings("serial")
			public BasicSplitPaneDivider createDefaultDivider() {
	            return new BasicSplitPaneDivider(this) {
	                @Override
	                public void paint(Graphics g) {
	                g.setColor(Color.LIGHT_GRAY);
	                g.fillRect(0, 0, getSize().width, getSize().height);
	                    super.paint(g);
	                }
	            };
            }
        });
		
		
		/*
		 * create the footBar
		 */
		footbarSection = new MainFootbarSection(screenResolution);
		footbarSection.createFootbarSection();
		contentPane.add(footbarSection, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	

	/**
	 * Display the detailSection or the newEditSection
	 * 
	 * @param	isDisplayed		Set if the detailSection is displayed
	 */
	public void displayDetailSection(boolean isDisplayed) {
		if (isDisplayed) {
			detailSection.setVisible(true);
			newEditSection.setVisible(false);
		} else {
			detailSection.setVisible(false);
			newEditSection.setVisible(true);
		}
	}
	
	
	/**
	 * Fill and display the UI movies list section
	 * 
	 * @param	moviesTitlesList	Movies list to displayed
	 */
	public void updateMovies(String [] moviesTitlesList) {
		if (moviesTitlesList != null) 
			moviesListSection.updateMoviesList(moviesTitlesList);
	}

	/**
	 * Update and display the selected movie into the detail section 
	 * 
	 * @param	movieSelected	Selected movie to update and displayed
	 */
	public void updateDetailSection(Movie movieSelected) {
		detailSection.displaySelectedMovie(movieSelected);
	}
	
	
	/**
	 * Fill the textFieds of the newEditSection with a movie from IMDB or a saved movie
	 * 
	 * @param	movie			Movie to fill with
	 * @param	isInEditMode	Indicate if the movie will be edited or not
	 */
	public void fillNewEditSection(Movie movie, boolean isInEditMode) {
		newEditSection.fillTextfields(movie, isInEditMode);
	}

	
	/**
	 * Update and display the values into the UI Filters
	 */
	public void updateFilters() {
		filterSection.updateFiltersLists();
	}
	
	
	/**
	 * Display a warning inside a Popup
	 */
	public void displayInfoErrorPopup(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, mainController.getLanguageSelected().getWarning(), JOptionPane.WARNING_MESSAGE);
	}


	/*
	 * GETTERS
	 */
	public MainFilterSection getFilterSection() {
		return filterSection;
	}

	public MainNewEditSection getNewEditSection() {
		return newEditSection;
	}

	public MainMoviesList getMoviesListSection() {
		return moviesListSection;
	}

	public MainFootbarSection getFootbarSection() {
		return footbarSection;
	}

	public MainMenuBar getMainMenuBar() {
		return mainMenuBar;
	}

	public MainDetailSection getDetailSection() {
		return detailSection;
	}
}
