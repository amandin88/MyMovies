package com.amandin.ui.windowElements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
 * UI NewEdit Section
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainNewEditSection extends JPanel {
	
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
	private URL 	posterURL;
	
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
	
	private JButton 	addButton,
						editButton,
						cancelButton,
						getMovieFromImdbButton, 
						editFromImdbButton;
	
	private	JTextField 	imdbIdTextfield,
						imdbTitleTextfield,
						imdbReleaseYearTextfield,
						posterUrlTextfield;
	
	private JPanel boxOptionsImdb;
	
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 * @param contentSize		Size of the contentPane
	 */
	public MainNewEditSection(MainController mainController, Dimension contentSize) {
		
		this.mainController = mainController;
		this.contentSize = contentSize;
		
		languageSelected = mainController.getLanguageSelected();
	}

	
	/**
	 * Create the NewEditSection
	 */
	public void createNewEditSection() {
		
		setLayout(new BorderLayout());
		createTopPart();
		createBottomPart();
	}
	

	/**
	 *  Create the top part
	 */
	public void createTopPart() {
		topPart = new JPanel();
		topPart.setPreferredSize(new Dimension( (int)contentSize.getWidth()-50, 325));
		topPart.setBackground(Color.DARK_GRAY);
				
		/*
		 * top Left part
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
		topRightPart.setLayout(new BoxLayout(topRightPart, BoxLayout.Y_AXIS));
		topRightPart.setPreferredSize(new Dimension(((int) topPart.getPreferredSize().getWidth()-25)/2, 315));
		topRightPart.setBackground(Color.DARK_GRAY);
		
		TitledBorder synopTitleBorder = new TitledBorder(languageSelected.getSynopsis());
		synopTitleBorder.setTitleColor(Color.WHITE);
		synopTitleBorder.setTitleJustification(TitledBorder.CENTER);
		topRightPart.setBorder(synopTitleBorder);
		
		synopsisValue = new JTextArea();
		synopsisValue.setLineWrap(true);
		synopsisValue.setWrapStyleWord(true);
		synopsisValue.setMargin(new Insets(5, 5, 5, 10));
		synopsisValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		synopsisValue.setBackground(Color.GRAY);
		synopsisValue.setForeground(Color.WHITE);
		
		JScrollPane synopsisScroll = new JScrollPane(synopsisValue);
		synopsisScroll.setPreferredSize(new Dimension((int) topRightPart.getPreferredSize().width-15, 250));
		synopsisScroll.setBorder(BorderFactory.createEmptyBorder());
		synopsisScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		synopsisScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topRightPart.add(synopsisScroll);
		
		JPanel separatorPanel = new JPanel();
		separatorPanel.setBackground(Color.DARK_GRAY);
		topRightPart.add(separatorPanel);
		
		posterUrlTextfield = new JTextField(languageSelected.getClickToChoosePoster());
		posterUrlTextfield.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		posterUrlTextfield.setBackground(Color.GRAY);
		posterUrlTextfield.setForeground(Color.WHITE);
		topRightPart.add(posterUrlTextfield);
		
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
		bottomLeftPart.add(posterImg);
		
		bottomPart.add(bottomLeftPart, BorderLayout.WEST);
		
		
		/*
		 * right bottom part
		 */
		bottomRightPart = new JPanel();
		bottomRightPart.setBorder(new EmptyBorder(new Insets(35, 5, 5, 5)));
		bottomRightPart.setPreferredSize(
				new Dimension(((int) topPart.getPreferredSize().getWidth()-25)/3*2, (int)contentSize.getHeight()-465));
		bottomRightPart.setBackground(Color.DARK_GRAY);
		
		addButton = new JButton(languageSelected.getAdd());
		addButton.setBackground(Color.GRAY);
		addButton.setForeground(Color.WHITE);
		
		editButton = new JButton(languageSelected.getEdit());
		editButton.setBackground(Color.GRAY);
		editButton.setForeground(Color.WHITE);
		editButton.setVisible(false);
		
		cancelButton = new JButton(languageSelected.getCancel());
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		
		getMovieFromImdbButton = new JButton(languageSelected.getFillMovieFromImdb());
		getMovieFromImdbButton.setBackground(Color.GRAY);
		getMovieFromImdbButton.setForeground(Color.WHITE);
		
		editFromImdbButton = new JButton(languageSelected.getUpdateFromImdb());
		editFromImdbButton.setBackground(Color.GRAY);
		editFromImdbButton.setForeground(Color.WHITE);
		editFromImdbButton.setVisible(false);
		editFromImdbButton.setEnabled(false); 			// TO DELETE
	
		// visible when adding a movie
		bottomRightPart.add(addButton);
		bottomRightPart.add(getMovieFromImdbButton);
	
		// visible when editing a movie
		bottomRightPart.add(editButton);
		bottomRightPart.add(editFromImdbButton);
		
		bottomRightPart.add(cancelButton);
		
		bottomPart.add(bottomRightPart);
		
		addActionsListenerToButton();
		
		add(bottomPart, BorderLayout.CENTER);
	}
	
	
	/**
	 * Add actionsListeners to buttons
	 */
	private void addActionsListenerToButton() {
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!titleValue.getText().equals(""))
					mainController.addMovie();
				else 
					mainController.getMainWindow().displayInfoErrorPopup(languageSelected.getAddTitlePlease());
			}
		});
		
		getMovieFromImdbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// ask for the mode of research : ID or title
				// if ID and Title are filled, title is ignored
				createBoxOptionsImdb();
				int result = JOptionPane.showConfirmDialog(null, boxOptionsImdb, languageSelected.getImdbIdOrTitle(), JOptionPane.OK_CANCEL_OPTION);
				
			    if (result == JOptionPane.OK_OPTION) {
			    	
			    	if (!imdbIdTextfield.getText().equals(languageSelected.getImdbIDAsk()))
			    		mainController.getMovieFromImdbById(imdbIdTextfield.getText());
			    	else {
			    		if (!imdbTitleTextfield.getText().equals(languageSelected.getImdbTitleAsk()) 
			    				&& !imdbReleaseYearTextfield.getText().equals(languageSelected.getImdbReleaseYearAsk()))
			    			mainController.getMovieFromImdbByTitle(imdbTitleTextfield.getText(), imdbReleaseYearTextfield.getText());
			    		else
			    			mainController.getMovieFromImdbByTitle(imdbTitleTextfield.getText(), "");
			    	}			 
			    }
			}

			
		});
		
		editButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						mainController.editMovie();
					}
				});
		
		
		editFromImdbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.getMainWindow().displayDetailSection(true);
			}
		});
		
		
		localUrlValue.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser(localUrlValue.getText());
				int state = fileChooser.showOpenDialog(mainController.getMainWindow());
				if(state == JFileChooser.APPROVE_OPTION){
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					localUrlValue.setText(filePath);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}			
		});
		
				
		posterUrlTextfield.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser(posterUrlTextfield.getText());
				int state = fileChooser.showOpenDialog(mainController.getMainWindow());
				if(state == JFileChooser.APPROVE_OPTION){
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					
					if ( filePath.substring(filePath.length()-3, filePath.length()).toLowerCase().equals("jpg"))
						posterUrlTextfield.setText(filePath);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}			
		});
	}
	
	
	/**
	 * Create a dialog box to ask the Imdb ID or the title
	 */
	public void createBoxOptionsImdb() {
		boxOptionsImdb = new JPanel();
		boxOptionsImdb.setLayout(new BoxLayout(boxOptionsImdb, BoxLayout.Y_AXIS));
		boxOptionsImdb.setBackground(Color.DARK_GRAY);
		
		imdbIdTextfield = new JTextField(languageSelected.getImdbIDAsk());	
		imdbIdTextfield.setBackground(Color.GRAY);
		imdbIdTextfield.setForeground(Color.WHITE);
		imdbIdTextfield.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				imdbIdTextfield.setBackground(Color.GRAY);
				imdbIdTextfield.setForeground(Color.WHITE);
				if (imdbIdTextfield.getText().equals("")) {
					imdbIdTextfield.setText(languageSelected.getImdbIDAsk());
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (imdbIdTextfield.getText().equals(languageSelected.getImdbIDAsk()))
					imdbIdTextfield.setText("");
				
				imdbIdTextfield.setBackground(Color.YELLOW);
				imdbIdTextfield.setForeground(Color.BLACK);
			}
		});
		
		imdbTitleTextfield = new JTextField(languageSelected.getImdbTitleAsk());
		imdbTitleTextfield.setBackground(Color.GRAY);
		imdbTitleTextfield.setForeground(Color.WHITE);
		imdbTitleTextfield.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				imdbTitleTextfield.setBackground(Color.GRAY);
				imdbTitleTextfield.setForeground(Color.WHITE);
				if (imdbTitleTextfield.getText().equals("")) {
					imdbTitleTextfield.setText(languageSelected.getImdbTitleAsk());
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (imdbTitleTextfield.getText().equals(languageSelected.getImdbTitleAsk()))
					imdbTitleTextfield.setText("");
				
				imdbTitleTextfield.setBackground(Color.YELLOW);
				imdbTitleTextfield.setForeground(Color.BLACK);
			}
		});
		
		imdbReleaseYearTextfield = new JTextField(languageSelected.getImdbReleaseYearAsk());
		imdbReleaseYearTextfield.setBackground(Color.GRAY);
		imdbReleaseYearTextfield.setForeground(Color.WHITE);
		imdbReleaseYearTextfield.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				imdbReleaseYearTextfield.setBackground(Color.GRAY);
				imdbReleaseYearTextfield.setForeground(Color.WHITE);
				if (imdbReleaseYearTextfield.getText().equals("")) {
					imdbReleaseYearTextfield.setText(languageSelected.getImdbReleaseYearAsk());
				}	
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (imdbReleaseYearTextfield.getText().equals(languageSelected.getImdbReleaseYearAsk()))
					imdbReleaseYearTextfield.setText("");
				
				imdbReleaseYearTextfield.setBackground(Color.YELLOW);
				imdbReleaseYearTextfield.setForeground(Color.BLACK);
			}
		});
		
		boxOptionsImdb.add(imdbIdTextfield);
		JLabel orLabel = new JLabel(languageSelected.getOr());
		orLabel.setForeground(Color.WHITE);
		boxOptionsImdb.add(orLabel);
		boxOptionsImdb.add(imdbTitleTextfield);
		boxOptionsImdb.add(imdbReleaseYearTextfield);
	}


	/**
	 * Create the elements of the top left part
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
		
		titleValue = new JTextField();
		titleValue.setBorder(BorderFactory.createEmptyBorder());
		titleValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		titleValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		titleValue.setBackground(Color.GRAY);
		titleValue.setForeground(Color.WHITE);
		
		typeValue = new JTextField();
		typeValue.setBorder(BorderFactory.createEmptyBorder());
		typeValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		typeValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		typeValue.setBackground(Color.GRAY);
		typeValue.setForeground(Color.WHITE);
				
		directorValue = new JTextField();
		directorValue.setBorder(BorderFactory.createEmptyBorder());
		directorValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		directorValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		directorValue.setBackground(Color.GRAY);
		directorValue.setForeground(Color.WHITE);
		
		lengthValue = new JTextField();
		lengthValue.setBorder(BorderFactory.createEmptyBorder());
		lengthValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		lengthValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		lengthValue.setBackground(Color.GRAY);
		lengthValue.setForeground(Color.WHITE);
		
		releaseYearValue = new JTextField();
		releaseYearValue.setBorder(new EmptyBorder(0,0,0,0));
		releaseYearValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		releaseYearValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		releaseYearValue.setBackground(Color.GRAY);
		releaseYearValue.setForeground(Color.WHITE);
		
		actorsValue = new JTextField();	
		actorsValue.setBorder(BorderFactory.createEmptyBorder());
		actorsValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		actorsValue.setPreferredSize(new Dimension(textAreaWidth, 25));	
		actorsValue.setBackground(Color.GRAY);
		actorsValue.setForeground(Color.WHITE);
				
		imdbRateValue = new JTextField();
		imdbRateValue.setBorder(BorderFactory.createEmptyBorder());
		imdbRateValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		imdbRateValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		imdbRateValue.setBackground(Color.GRAY);
		imdbRateValue.setForeground(Color.WHITE);
		
		viewsCounterValue = new JTextField();;
		viewsCounterValue.setBorder(BorderFactory.createEmptyBorder());
		viewsCounterValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		viewsCounterValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		viewsCounterValue.setBackground(Color.GRAY);
		viewsCounterValue.setForeground(Color.WHITE);
				
		localUrlValue = new JTextField(languageSelected.getClickToChooseAMovie());
		localUrlValue.setBorder(BorderFactory.createEmptyBorder());
		localUrlValue.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		localUrlValue.setPreferredSize(new Dimension(textAreaWidth, 25));
		localUrlValue.setBackground(Color.GRAY);
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
	 * Hide or show the corresponding buttons and display the NewEditSection
	 */
	public void setCreateMode(boolean createMode) {
		if (createMode) {
			addButton.setVisible(true);
			getMovieFromImdbButton.setVisible(true);
			
			editButton.setVisible(false);
			editFromImdbButton.setVisible(false);
		}
		else {
			addButton.setVisible(false);
			getMovieFromImdbButton.setVisible(false);
			
			editButton.setVisible(true);
			editFromImdbButton.setVisible(true);
		}
		
		this.setVisible(true);
	}


	/**
	 * Fill the textFields with the movie selected
	 * 
	 * @param	movie			Movie to add from IMDB or to edit
	 * @param	isInEditMode	Is used to create the poster URL
	 */
	public void fillTextfields(Movie movie, boolean isInEditMode) {
		
		if (movie == null) {
			JOptionPane.showMessageDialog(this, languageSelected.getMovieNotFound());
		}
		else {
			titleValue.setText(movie.getTitle());
			
			if (movie.getType() != null)
				typeValue.setText(movie.getType());
			
			if (movie.getDirector() != null)
				directorValue.setText(movie.getDirector());
			
			if (movie.getReleaseYear() != null)
				releaseYearValue.setText(movie.getReleaseYear());
			
			if (movie.getLength() != null)
				lengthValue.setText(movie.getLength());
			
			if (movie.getImdbRate() != null)
				imdbRateValue.setText(movie.getImdbRate());
			
			if (movie.getViewsCounter() != null)
				viewsCounterValue.setText(movie.getViewsCounter());
			
			if (movie.getSynopsis() != null) {
				synopsisValue.setText(movie.getSynopsis());
				synopsisValue.setCaretPosition(0);
			}
			
			if (movie.getActors() != null) {
				actorsValue.setText(movie.getActors());
			}
			
			if (movie.getLocalUrl() != null && !movie.getLocalUrl().equals(""))
				localUrlValue.setText(movie.getLocalUrl());
			else 
				localUrlValue.setText(languageSelected.getClickToChooseAMovie());
			
			/*
			 *  id Edit, display the saved poster
			 *  else display the poster from the URL
			 */
			if (movie.getPosterUrl() != null) {
				try {

					BufferedImage posterImgBuff = null;
					if (!isInEditMode) {
						posterURL = new URL(movie.getPosterUrl());
						posterImgBuff = ImageIO.read(posterURL);
					}
					else {
						posterImgBuff = ImageIO.read(new File(movie.getPosterUrl()));
						posterUrlTextfield.setText(movie.getPosterUrl());
					}
					int posterWidth = (int)posterImg.getPreferredSize().getWidth();
					int posterHeight = (int)posterImg.getPreferredSize().getHeight();
					posterImg.setIcon(new ImageIcon(posterImgBuff.getScaledInstance(posterWidth, posterHeight, Image.SCALE_FAST)));
				} catch (IOException e) {
					posterImg.setForeground(Color.WHITE);
					posterImg.setText(languageSelected.getPosterNotFound());
				}
			}
		}
	}


	/**
	 * Clear all the textFields when create new movie
	 */
	public void clearTextfields() {
	
		titleValue.setText("");
		typeValue.setText("");
		directorValue.setText("");
		releaseYearValue.setText("");
		lengthValue.setText("");
		imdbRateValue.setText("");
		viewsCounterValue.setText("");
		synopsisValue.setText("");
		actorsValue.setText("");
		localUrlValue.setText(languageSelected.getClickToChooseAMovie());
		posterUrlTextfield.setText(languageSelected.getClickToChoosePoster());
		posterImg.setText("");
		posterImg.setIcon(null);
	}

	
	/*
	 * GETTERS
	 */
	public JTextField getTitleValue() {
		return titleValue;
	}

	public JTextField getTypeValue() {
		return typeValue;
	}

	public JTextField getDirectorValue() {
		return directorValue;
	}
	
	public JTextField getReleaseYearValue() {
		return releaseYearValue;
	}
	
	public JTextField getLengthValue() {
		return lengthValue;
	}

	public JTextField getActorsValue() {
		return actorsValue;
	}

	public JTextField getImdbRateValue() {
		return imdbRateValue;
	}
	
	public JTextField getViewsCounterValue() {
		return viewsCounterValue;
	}
	
	public JTextField getLocalUrlValue() {
		return localUrlValue;
	}
	
	public JTextArea getSynopsisValue() {
		return synopsisValue;
	}


	public URL getPosterURL() {
		return posterURL;
	}


	public JTextField getPosterUrlTextfield() {
		return posterUrlTextfield;
	}
}


	
