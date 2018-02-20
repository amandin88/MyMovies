package com.amandin.ui.windowElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.amandin.controller.MainController;


/**
 * UI Movies list
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainMoviesList extends JPanel {

	private MainController mainController;
	private Dimension contentSize;
	private JList<String> moviesList;
	private MoviesListPopupMenu movieListPopupMenu;
	
	
	/**
	 * Constructor
	 * 
	 * @param	mainController	Instance of MainController
	 * @param	contentSize		Size of the contentPane
	 */
	public MainMoviesList(MainController mainController, Dimension contentSize) {
		
		this.mainController = mainController;
		this.contentSize = contentSize;
		
		movieListPopupMenu = new MoviesListPopupMenu(mainController);
	}
	

	/**
	 * Create the view movies list section
	 */
	public void createMoviesListSection() {
		
		setPreferredSize(
						new Dimension((int)contentSize.getHeight()-25, (int)contentSize.getHeight()-315));
		setBackground(Color.DARK_GRAY);
		
		moviesList = new JList<>();
		moviesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		moviesList.setBorder(BorderFactory.createLoweredBevelBorder());
		moviesList.setBackground(Color.GRAY);
		moviesList.setForeground(Color.WHITE);
		addMoviesListListeners();
	
		// adding vertical scroll bar as needed		
		JScrollPane moviesListScroll = new JScrollPane(moviesList);
		moviesListScroll.setBackground(Color.DARK_GRAY);
		moviesListScroll.setPreferredSize(
				new Dimension((int)contentSize.getWidth()-25, (int)contentSize.getHeight()-325));
		
		moviesListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		moviesListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		TitledBorder titleBorder = BorderFactory.createTitledBorder(mainController.getLanguageSelected().getAvailableMovies());
		titleBorder.setTitleColor(Color.WHITE);
		titleBorder.setTitleJustification(TitledBorder.RIGHT);
		moviesListScroll.setBorder(titleBorder);
		
		add(moviesListScroll);
	}

	
	/**
	 * Update and display the movies list 
	 * 
	 * @param	moviesTitlesList	Movies list to display
	 */
	public boolean updateMoviesList(String[] moviesTitlesList) {
		if (moviesTitlesList != null && moviesTitlesList.length > 0) {
			moviesList.setListData(moviesTitlesList);
		
			moviesList.setSelectedIndex(0);
			
			return true;
		}
		
		return false;
	}
	

	/**
	 * Select the movie previously selected before his edition
	 * 
	 * @param	movieNameSelected	Name of the movies previously selected
	 */
	public void setSelectMovie(String movieNameSelected) {
		int selectionIndex = 0;
		boolean movieFound = false;
		
		while (!movieFound && selectionIndex < moviesList.getModel().getSize()) {
			
			if (moviesList.getModel().getElementAt(selectionIndex).equals(movieNameSelected)) {
				movieFound = true;
				moviesList.setSelectedIndex(selectionIndex);
			}
			else 
				selectionIndex++;				
		}
	}
	
		
	/**
	 * Add listeners to the list
	 */
	public void addMoviesListListeners() {
		
		moviesList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.isMetaDown()) {

					// update the selection from the right click
		            moviesList.setSelectedIndex(moviesList.locationToIndex(e.getPoint()));
		            mainController.setMovieNameSelected(moviesList.getSelectedValue());
					mainController.updateSelectedMovie();
					
					movieListPopupMenu.show(e.getComponent(), e.getX()+15, e.getY());
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}	
		});
		
		
		moviesList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					mainController.setMovieNameSelected(moviesList.getSelectedValue());
					mainController.updateSelectedMovie();
				}
			}
		});
		
		moviesList.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
					mainController.setMovieNameSelected(moviesList.getSelectedValue());
					mainController.updateSelectedMovie();
				}
			}
			
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
	}
}
