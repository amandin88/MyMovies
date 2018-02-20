package com.amandin.ui.windowElements;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * UI Footbar section
 * 
 * @author Alexis Mandin
 * @version
 */
public class MainFootbarSection extends JPanel {

	private Dimension screenResolution;
	private JLabel	statusLabel;
	
	
	/**
	 * Constructor 
	 * 
	 * @param screenResolution	Screen resolution
	 */
	public MainFootbarSection(Dimension screenResolution) {
		
		this.screenResolution = screenResolution;
	}

	/**
	 * Create the footbar section
	 */
	public void createFootbarSection() {
		setPreferredSize(new Dimension((int)screenResolution.getWidth(), 30));	
		setBorder(BorderFactory.createLoweredBevelBorder());
		setBackground(Color.GRAY);
		
		statusLabel = new JLabel();
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setPreferredSize(new Dimension((int)screenResolution.getWidth()-20, 15));	
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(statusLabel);
	}
	
	
	/**
	 * Display the application status 
	 * 
	 * @param status Status to display
	 */
	public void displayStatus(String status) {
		statusLabel.setText(status);
	}
}
