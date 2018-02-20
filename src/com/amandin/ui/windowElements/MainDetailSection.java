package com.amandin.ui.windowElements;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.amandin.controller.MainController;
import com.amandin.managers.lang.Language;
import com.amandin.managers.movie.Movie;


/**
 * UI of the detail section
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainDetailSection extends JPanel {

	private MainController mainController;
	private Dimension contentSize;
	private Language languageSelected;
	
	private JPanel 	topPart,
					topLeftpart,
					topRightPart,
					bottomPart,
					bottomLeftPart,
					bottomRightPart;

	private JLabel 	titleLabel,
					typeLabel,
					directorLabel,
					releaseYearLabel,
					actorsLabel,
					lengthLabel,
					imdbRateLabel,
					viewsCounterLabel,
					localUrlLabel;
	
	private JLabel 	posterImg;
	
	private MainDetailPreviewPlayer previewPlayer;
	
	private JTextField 	titleValue,
						typeValue,
						directorValue,
						releaseYearValue,
						lengthValue,
						actorsValue,
						imdbRateValue,
						viewsCounterValue,
						localUrlValue;
		
	private JTextArea	synopsisValue;
		
	
	/**
	 * Constructor
	 * 
	 * @param mainController	Instance of MainController
	 * @param contentSize		Size of the contentPane
	 */
	public MainDetailSection(MainController mainController, Dimension contentSize) {
		
		this.mainController = mainController;
		this.contentSize = contentSize;
		languageSelected = mainController.getLanguageSelected();
	}

	
	/**
	 * Create the detail section
	 */
	public void createDetailSection() {
		
		setLayout(new BorderLayout());
		createTopPart();
		createBottomPart();
	}

	
	/**
	 * Create the top part
	 */
	public void createTopPart() {
		topPart = new JPanel();
		topPart.setPreferredSize(new Dimension( (int)contentSize.getWidth()-50, 325));
		topPart.setBackground(Color.DARK_GRAY);
				
		/*
		 * top left part
		 */
		topLeftpart = new JPanel();
		topLeftpart.setPreferredSize(new Dimension(((int) topPart.getPreferredSize().getWidth())/2, 315));
		topLeftpart.setBackground(Color.DARK_GRAY);
		
		TitledBorder infoTitleBorder = new TitledBorder(languageSelected.getInformation());
		infoTitleBorder.setTitleColor(Color.WHITE);
		infoTitleBorder.setTitleJustification(TitledBorder.CENTER);
		topLeftpart.setBorder(infoTitleBorder);
		
		initialiseTopLeftElements();
		placeTopLeftElement();
		topPart.add(topLeftpart);
		
		
		/*
		 * top right part
		 */
		topRightPart = new JPanel();
		topRightPart.setPreferredSize(new Dimension(((int) topPart.getPreferredSize().getWidth()-25)/2, 315));
		topRightPart.setBackground(Color.DARK_GRAY);
		
		TitledBorder synopTitleBorder = new TitledBorder(languageSelected.getSynopsis());
		synopTitleBorder.setTitleColor(Color.WHITE);
		synopTitleBorder.setTitleJustification(TitledBorder.CENTER);
		topRightPart.setBorder(synopTitleBorder);
		
		synopsisValue = new JTextArea("-");
		synopsisValue.setEditable(false);
		synopsisValue.setLineWrap(true);
		synopsisValue.setWrapStyleWord(true);
		synopsisValue.setMargin(new Insets(5, 5, 5, 10));
		synopsisValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		synopsisValue.setBackground(Color.GRAY);
		synopsisValue.setForeground(Color.WHITE);
		
		JScrollPane synopsisScroll = new JScrollPane(synopsisValue);
		synopsisScroll.setPreferredSize(new Dimension((int) topRightPart.getPreferredSize().width-15, 280));
		synopsisScroll.setBorder(BorderFactory.createEmptyBorder());
		synopsisScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		synopsisScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topRightPart.add(synopsisScroll);
		
		topPart.add(topRightPart);
		
		add(topPart, BorderLayout.NORTH);
	}
	
	
	/**
	 * Create the bottom part
	 */
	public void createBottomPart() {
		bottomPart = new JPanel();
		bottomPart.setPreferredSize(
					new Dimension( (int)contentSize.getWidth()-50, (int)contentSize.getHeight()-465));
		bottomPart.setLayout(new BorderLayout());	
		bottomPart.setBackground(Color.DARK_GRAY);
		
		/*
		 * left bottom part : the poster
		 */
		bottomLeftPart = new JPanel();
		bottomLeftPart.setPreferredSize(
				new Dimension(((int) bottomPart.getPreferredSize().getWidth()-25)/3, (int)contentSize.getHeight()-465));
		bottomLeftPart.setBackground(Color.DARK_GRAY);
		
		TitledBorder posterTitleBorder = new TitledBorder(languageSelected.getPoster());
		posterTitleBorder.setTitleJustification(TitledBorder.CENTER);
		posterTitleBorder.setTitleColor(Color.WHITE);
		bottomLeftPart.setBorder(posterTitleBorder);
		
		// create the poster
		posterImg = new JLabel();
		posterImg.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
		posterImg.setPreferredSize(new Dimension(
										(int)bottomLeftPart.getPreferredSize().getWidth()-60,
										(int)bottomLeftPart.getPreferredSize().getHeight()-30));
		posterImg.setHorizontalAlignment(SwingConstants.CENTER);
		posterImg.setForeground(Color.WHITE);
		bottomLeftPart.add(posterImg);
		
		bottomPart.add(bottomLeftPart, BorderLayout.WEST);
		
		
		/*
		 * right bottom part : the video player
		 */
		bottomRightPart = new JPanel();
		bottomRightPart.setPreferredSize(
				new Dimension(((int) topPart.getPreferredSize().getWidth()-25)/3*2, (int)contentSize.getHeight()-465));
		bottomRightPart.setBackground(Color.DARK_GRAY);
		
		TitledBorder previewTitleBorder = new TitledBorder(languageSelected.getVideoPlayer());
		previewTitleBorder.setTitleJustification(TitledBorder.CENTER);
		previewTitleBorder.setTitleColor(Color.WHITE);
		bottomRightPart.setBorder(previewTitleBorder);
	
		// create the video player
		previewPlayer = new MainDetailPreviewPlayer(mainController, bottomRightPart.getPreferredSize());
		
		bottomRightPart.add(previewPlayer);
		bottomPart.add(bottomRightPart, BorderLayout.EAST);
		
		add(bottomPart, BorderLayout.CENTER);
	}
	
	
	/**
	 * Create the element of the top left part
	 */
	private void initialiseTopLeftElements() {
		
		titleLabel = new JLabel(languageSelected.getTitle());
		typeLabel = new JLabel(languageSelected.getType());
		directorLabel = new JLabel(languageSelected.getDirector());
		lengthLabel = new JLabel(languageSelected.getLength()+" (mn)");
		releaseYearLabel = new JLabel(languageSelected.getReleaseYear());
		actorsLabel = new JLabel(languageSelected.getActors());
		imdbRateLabel = new JLabel(languageSelected.getImdbRate());
		viewsCounterLabel = new JLabel(languageSelected.getViewsCounter());
		localUrlLabel = new JLabel(languageSelected.getLocalUrl());
		
		titleLabel.setForeground(Color.WHITE);
		typeLabel.setForeground(Color.WHITE);
		directorLabel.setForeground(Color.WHITE);
		lengthLabel.setForeground(Color.WHITE);
		releaseYearLabel.setForeground(Color.WHITE);
		actorsLabel.setForeground(Color.WHITE);
		imdbRateLabel.setForeground(Color.WHITE);
		viewsCounterLabel.setForeground(Color.WHITE);
		localUrlLabel.setForeground(Color.WHITE);
		
		int textAreaWidth = (int) topLeftpart.getPreferredSize().getWidth()-125;
		
		titleValue = new JTextField("-");
		titleValue.setEditable(false);
		titleValue.setBackground(null);
		titleValue.setBorder(BorderFactory.createEmptyBorder());
		titleValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		titleValue.setForeground(Color.WHITE);
		
		typeValue = new JTextField("-");
		typeValue.setEditable(false);
		typeValue.setBackground(null);
		typeValue.setBorder(BorderFactory.createEmptyBorder());
		typeValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		typeValue.setForeground(Color.WHITE);
				
		directorValue = new JTextField("-");
		directorValue.setEditable(false);
		directorValue.setBackground(null);
		directorValue.setBorder(BorderFactory.createEmptyBorder());
		directorValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		directorValue.setForeground(Color.WHITE);
		
		lengthValue = new JTextField("-");
		lengthValue.setEditable(false);
		lengthValue.setBackground(null);
		lengthValue.setBorder(BorderFactory.createEmptyBorder());
		lengthValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		lengthValue.setForeground(Color.WHITE);
		
		releaseYearValue = new JTextField("-");
		releaseYearValue.setEditable(false);
		releaseYearValue.setBackground(null);
		releaseYearValue.setBorder(new EmptyBorder(0,0,0,0));
		releaseYearValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		releaseYearValue.setForeground(Color.WHITE);
		
		actorsValue = new JTextField("-");	
		actorsValue.setEditable(false);
		actorsValue.setBackground(null);
		actorsValue.setBorder(BorderFactory.createEmptyBorder());
		actorsValue.setPreferredSize(new Dimension(textAreaWidth, 25));		
		actorsValue.setForeground(Color.WHITE);
				
		imdbRateValue = new JTextField("-");
		imdbRateValue.setEditable(false);
		imdbRateValue.setBackground(null);
		imdbRateValue.setBorder(BorderFactory.createEmptyBorder());
		imdbRateValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		imdbRateValue.setForeground(Color.WHITE);
		
		viewsCounterValue = new JTextField("-");
		viewsCounterValue.setEditable(false);
		viewsCounterValue.setBackground(null);
		viewsCounterValue.setBorder(BorderFactory.createEmptyBorder());
		viewsCounterValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		viewsCounterValue.setToolTipText(languageSelected.getViewsCounterDescription());
		viewsCounterValue.setForeground(Color.WHITE);
				
		localUrlValue = new JTextField("-");
		localUrlValue.setEditable(false);
		localUrlValue.setBackground(null);
		localUrlValue.setBorder(BorderFactory.createEmptyBorder());
		localUrlValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		localUrlValue.setForeground(Color.WHITE);
	}

	/**
	 * Place the elements of the top left part
	 */
	private void placeTopLeftElement() {
		
		topLeftpart.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 2;
		gbc.ipadx = 15;
		gbc.fill = GridBagConstraints.HORIZONTAL;
			
		// 1st column
		gbc.gridx = 0;
		gbc.gridy = 0;
		topLeftpart.add(titleLabel, gbc);
		
		gbc.gridy = 1;
		topLeftpart.add(typeLabel, gbc);
		
		gbc.gridy = 2;
		topLeftpart.add(directorLabel, gbc);
		
		gbc.gridy = 3;
		topLeftpart.add(releaseYearLabel, gbc);
		
		gbc.gridy = 4;
		topLeftpart.add(lengthLabel, gbc);
		
		gbc.gridy = 5;
		topLeftpart.add(actorsLabel, gbc);
		
		gbc.gridy = 6;
		topLeftpart.add(imdbRateLabel, gbc);
		
		gbc.gridy = 7;
		topLeftpart.add(viewsCounterLabel, gbc);
		
		gbc.gridy = 8;
		topLeftpart.add(localUrlLabel, gbc);
		
		// 2nd column
		gbc.ipadx = 0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		topLeftpart.add(titleValue, gbc);
		
		gbc.gridy = 1;
		topLeftpart.add(typeValue, gbc);
		
		gbc.gridy = 2;
		topLeftpart.add(directorValue, gbc);
		
		gbc.gridy = 3;
		topLeftpart.add(releaseYearValue, gbc);

		gbc.gridy = 4;
		topLeftpart.add(lengthValue, gbc);
		
		gbc.gridy = 5;
		topLeftpart.add(actorsValue, gbc);
		
		gbc.gridy = 6;
		topLeftpart.add(imdbRateValue, gbc);
		
		gbc.gridy = 7;
		topLeftpart.add(viewsCounterValue, gbc);
		
		gbc.gridy = 8;
		topLeftpart.add(localUrlValue, gbc);
	}

	
	/**
	 * Fill the detail section with the selected movie 
	 * 
	 * @param movieSelected	Movie to display
	 */
	public void displaySelectedMovie(Movie movieSelected) {
		
		// Stop in case a movie is still playing
		previewPlayer.stopMovie();
		
		
		if (movieSelected != null) {
			// title
			titleValue.setText(movieSelected.getTitle());
			
			
			// Type
			if (movieSelected.getType() != null)
				typeValue.setText(movieSelected.getType());
			else 
				typeValue.setText(languageSelected.getUnknown());
			
			
			// Director
			if (movieSelected.getDirector() != null) 
				directorValue.setText(movieSelected.getDirector());
			else 
				directorValue.setText(languageSelected.getUnknown());
			
			
			// Release Year
			if (movieSelected.getReleaseYear() != null) 
				releaseYearValue.setText(movieSelected.getReleaseYear());
			else 
				releaseYearValue.setText(languageSelected.getUnknown());
			
			
			// Length
			if (movieSelected.getLength() != null) 
				lengthValue.setText(movieSelected.getLength());
			else 
				lengthValue.setText(languageSelected.getUnknown());
			
			
			// Imdb Rate
			if (movieSelected.getImdbRate() != null) 
				imdbRateValue.setText(movieSelected.getImdbRate());
			else 
				imdbRateValue.setText(languageSelected.getUnknown());
			
			
			// Views counter
			if (movieSelected.getViewsCounter() != null) 
				viewsCounterValue.setText(movieSelected.getViewsCounter());
			else 
				viewsCounterValue.setText(languageSelected.getUnknown());
			
			
			// Local URL
			if (movieSelected.getLocalUrl() != null) 
				localUrlValue.setText(movieSelected.getLocalUrl());
			else 
				localUrlValue.setText(languageSelected.getUnknown());
			
			
			// Synopsis
			if (movieSelected.getSynopsis() != null) {
				synopsisValue.setText(movieSelected.getSynopsis());
				synopsisValue.setCaretPosition(0);
			}
			else 
				synopsisValue.setText(languageSelected.getUnknown());
			
			
			// Actors
			if (movieSelected.getActors() != null) 
				actorsValue.setText(movieSelected.getActors());
			else 
				actorsValue.setText(languageSelected.getUnknown());
			
			
			// Poster
			if (movieSelected.getPosterUrl() != null) {
				posterImg.setIcon(null);
				try {
					BufferedImage posterImgBuff = ImageIO.read(new File(movieSelected.getPosterUrl()));
					int posterWidth = (int)posterImg.getPreferredSize().getWidth();
					int posterHeight = (int)posterImg.getPreferredSize().getHeight();
					posterImg.setIcon(new ImageIcon(posterImgBuff.getScaledInstance(posterWidth, posterHeight, Image.SCALE_FAST)));
				} catch (IOException e) {
					posterImg.setText(languageSelected.getPosterNotFound());
				}
			}
			else {
				posterImg.setIcon(null);
				posterImg.setText(languageSelected.getUnknown());
			}
			
			
			//check if the movie exists and is playable 
			if (localUrlValue.getText().contains("http")) {
				try {
					URL movieURL = new URL(localUrlValue.getText());
					try {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(movieURL.openStream()));
						
					} catch (IOException e) {
						System.err.println("Movie can't be read from : " + movieURL);
					}
			 
					localUrlValue.setForeground(Color.WHITE);
					localUrlValue.setToolTipText(null);
					
					if (previewPlayer != null)
						previewPlayer.setMoviePlayable(true);
					
				} catch (MalformedURLException e) {
					localUrlValue.setForeground(Color.RED);
					localUrlValue.setToolTipText(mainController.getLanguageSelected().getMovieDeletedOrMoved());
					
					if (previewPlayer != null)
						previewPlayer.setMoviePlayable(false);					
				}
	
			} else 	{	
				File movie = new File(localUrlValue.getText());
				if(movie.exists() && !movie.isDirectory()) { 
					
					localUrlValue.setForeground(Color.WHITE);
					localUrlValue.setToolTipText(null);
					
					if (previewPlayer != null)
						previewPlayer.setMoviePlayable(true);
				}
				else {
					localUrlValue.setForeground(Color.RED);
					localUrlValue.setToolTipText(mainController.getLanguageSelected().getMovieDeletedOrMoved());
					
					if (previewPlayer != null)
						previewPlayer.setMoviePlayable(false);
				}
			}
		}
	}


	public MainDetailPreviewPlayer getPreviewPlayer() {
		return previewPlayer;
	}
}
