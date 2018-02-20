package com.amandin.ui.windows;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.amandin.controller.MainController;


/**
 * UI Options windows
 * 
 * @author Alexis Mandin
 * @version
 */
public class OptionWindow extends JPanel {

	
	private MainController mainController;
	
	private JLabel	apiKeyLabel,
					vlcLocationLabel;
	
	private JTextField	apiKeyTextField,
						vlcLocationTextField;
		
	
	/**
	 * Constructor 
	 * 
	 * @param mainController	Instance of MainController
	 */
	public OptionWindow(MainController mainController) {
		
		this.mainController = mainController;
		
		createOptionWindows();
	}


	/**
	 * Create the options window
	 */
	private void createOptionWindows() {
		
		apiKeyLabel = new JLabel(mainController.getLanguageSelected().getImdbApiKey());
		vlcLocationLabel = new JLabel(mainController.getLanguageSelected().getVlcLocation());

		apiKeyTextField = new JTextField(mainController.getApplicationConfig().getImdbApiKey());
		apiKeyTextField.setPreferredSize(new Dimension(250, 20));
		
		vlcLocationTextField = new JTextField(mainController.getApplicationConfig().getVlcLocation());
		vlcLocationTextField.setPreferredSize(new Dimension(250, 20));
		vlcLocationTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser folderChooser = new JFileChooser(vlcLocationTextField.getText());
				folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int state = folderChooser.showOpenDialog(mainController.getMainWindow());
				if(state == JFileChooser.APPROVE_OPTION){
					String filePath = folderChooser.getSelectedFile().getAbsolutePath();
					
					if ( filePath != null)
						vlcLocationTextField.setText(filePath);
				}
			}
			
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
		});
		
		placeElements();
	}
	
	
	/**
	 * Place the different elements on the JPanel
	 */
	private void placeElements() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 2;
		gbc.ipady = 5;
		gbc.ipadx = 15;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// 1st column
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(apiKeyLabel, gbc);
		
		gbc.gridy = 1;
		this.add(vlcLocationLabel, gbc);
		
		// 2nd column
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(apiKeyTextField, gbc);
		
		gbc.gridy = 1;
		this.add(vlcLocationTextField, gbc);
	}


	// GETTERS
	public JTextField getApiKeyTextField() {
		return apiKeyTextField;
	}
	
	public JTextField getVlcLocationTextField() {
		return vlcLocationTextField;
	}
}
