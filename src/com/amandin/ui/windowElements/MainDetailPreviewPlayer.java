package com.amandin.ui.windowElements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.sun.jna.NativeLibrary;

import com.amandin.controller.MainController;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * Application's videos player using VLCJ library
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainDetailPreviewPlayer extends JPanel {

	private String 	nativeVlcLivrary;
	
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	private JPanel controlsPanel;
	
	private JButton pauseButton,
					playButton,
					stopButton,
					fastForward,
					fastBackward;
	
	private MainController mainController;
	
	private Dimension contentSize;
	
	private String 	urlFolderButtonIcons;
	
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 * @param contentSize		Size of contentPane
	 */
	public MainDetailPreviewPlayer(MainController mainController, Dimension contentSize) {
	
		this.mainController = mainController;
		this.contentSize = contentSize;
		urlFolderButtonIcons = "ressources/icons/24/";
		
		createPreviewPlayer();
	}

	/**
	 * Creation of the videos player
	 */
	private void createPreviewPlayer() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension((int) contentSize.getWidth()-10, (int) contentSize.getHeight()-30));
		setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
		setBackground(Color.DARK_GRAY);
		
		
		if (!new NativeDiscovery().discover()) {		
			nativeVlcLivrary = mainController.getApplicationConfig().getVlcLocation();
			NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), nativeVlcLivrary);
		}	
		
		try {
			mediaPlayerComponent = createVlcMediaPLayer();
			add(mediaPlayerComponent, BorderLayout.CENTER);
		} catch (Exception e) {
			
			JTextPane vlcNotFoundText = new JTextPane();
			SimpleAttributeSet attribs = new SimpleAttributeSet();
			StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
			StyleConstants.setForeground(attribs, Color.RED);

			vlcNotFoundText.setParagraphAttributes(attribs, true);
			vlcNotFoundText.setBackground(null);
			vlcNotFoundText.setEditable(false);
			vlcNotFoundText.setText(mainController.getLanguageSelected().getVlcNotFound());
						
			add(vlcNotFoundText);
		} 
		
		// buttons to control the video
		controlsPanel = new JPanel();
		controlsPanel.setBackground(Color.DARK_GRAY);
		
		playButton = new JButton();
		playButton.setIcon(new ImageIcon(urlFolderButtonIcons + "playPlayerIcon.png"));
		playButton.setToolTipText(mainController.getLanguageSelected().getPlay());
		playButton.setEnabled(false);
		playButton.setBackground(Color.DARK_GRAY);
		playButton.setBorder(BorderFactory.createEmptyBorder());
		controlsPanel.add(playButton);
		
		pauseButton = new JButton();
		pauseButton.setIcon(new ImageIcon(urlFolderButtonIcons + "pausePlayerIcon.png"));
		pauseButton.setToolTipText(mainController.getLanguageSelected().getPause());
		pauseButton.setEnabled(false);
		pauseButton.setBackground(Color.DARK_GRAY);
		pauseButton.setBorder(BorderFactory.createEmptyBorder());
		controlsPanel.add(pauseButton);
		
		stopButton = new JButton();
		stopButton.setIcon(new ImageIcon(urlFolderButtonIcons + "stopPlayerIcon.png"));
		stopButton.setToolTipText(mainController.getLanguageSelected().getStop());
		stopButton.setEnabled(false);
		stopButton.setBackground(Color.DARK_GRAY);
		stopButton.setBorder(BorderFactory.createEmptyBorder());
		controlsPanel.add(stopButton);
		
		fastBackward = new JButton();
		fastBackward.setIcon(new ImageIcon(urlFolderButtonIcons + "backwardsPlayerIcon.png"));
		fastBackward.setToolTipText(mainController.getLanguageSelected().getFastBackward() + " : -30s");
		fastBackward.setEnabled(false);
		fastBackward.setBackground(Color.DARK_GRAY);
		fastBackward.setBorder(BorderFactory.createEmptyBorder());
		controlsPanel.add(fastBackward);
		
		fastForward = new JButton();
		fastForward.setIcon(new ImageIcon(urlFolderButtonIcons + "forwardsPlayerIcon.png"));
		fastForward.setToolTipText(mainController.getLanguageSelected().getFastForward() + " : +30s");
		fastForward.setEnabled(false);
		fastForward.setBackground(Color.DARK_GRAY);
		fastForward.setBorder(BorderFactory.createEmptyBorder());
		controlsPanel.add(fastForward);
		
		
		add(controlsPanel, BorderLayout.SOUTH);
		
		addButtonsActionsListeners();        	
	}
	
	
	/**
	 * Create a EmbeddedMediaPLayer if VLC Media player is found
	 * 
	 * @return A EmbeddedMediaPLayer instance
	 * @throws Exception	Display a error message if there is an Exception
	 */
	private EmbeddedMediaPlayerComponent createVlcMediaPLayer() throws Exception {
		return new EmbeddedMediaPlayerComponent();
	}
	

	/**
	 * Add actionListeners to the control buttons
	 */
	public void addButtonsActionsListeners () {
		
		pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	pauseMovie();           
            }
        });
          
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (mediaPlayerComponent.getMediaPlayer().getMediaPlayerState().toString().equalsIgnoreCase("libvlc_Paused"))   
            		mediaPlayerComponent.getMediaPlayer().pause();  
            	else if (!mediaPlayerComponent.getMediaPlayer().getMediaPlayerState().toString().equalsIgnoreCase("libvlc_Playing"))            	
            		playMovie();                      
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	stopMovie();
            }
        });	
        
        fastForward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (mediaPlayerComponent.getMediaPlayer().getTime()+30000 < mediaPlayerComponent.getMediaPlayer().getLength())
            		mediaPlayerComponent.getMediaPlayer().skip(30000);
            	else 
            		mediaPlayerComponent.getMediaPlayer().stop();
            }
        });
        
        fastBackward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mediaPlayerComponent.getMediaPlayer().skip(-30000);
            }
        });
	}
		
	
	/**
	 * Read and play the selected movie
	 */
	public void playMovie() {
		if (mediaPlayerComponent != null) {
			String movieUrl = mainController.getMovieSelected().getLocalUrl();	
			mediaPlayerComponent.getMediaPlayer().playMedia(movieUrl);
		}
	}
	
	
	/**
	 * Pause the selected movie
	 */
	public void pauseMovie() {
		if (mediaPlayerComponent != null) 
			mediaPlayerComponent.getMediaPlayer().pause();
	}

	
	/**
	 * Stop the movie which is being watched
	 */
	public void stopMovie() {
		if (mediaPlayerComponent != null)
			mediaPlayerComponent.getMediaPlayer().stop();
	}
	
	
	/**
	 * Indicate if a movie is playing
	 * @return
	 */
	public boolean isPlaying() {
		if (mediaPlayerComponent != null)
			return mediaPlayerComponent.getMediaPlayer().isPlaying();
		
		return false;
	}
	
	
	/**
	 * Enable the control button when the movie is playable
	 */
	public void setMoviePlayable(boolean status) {
		if (mediaPlayerComponent != null) {
			playButton.setEnabled(status);
			pauseButton.setEnabled(status);
			stopButton.setEnabled(status);
			fastForward.setEnabled(status);
			fastBackward.setEnabled(status);
		}
	}
}
