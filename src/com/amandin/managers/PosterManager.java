package com.amandin.managers;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Manage the movie's poster
 * 
 * @author Alexis Mandin
 * @version
 */
public class PosterManager {
	
	
	/**
	 * Download an image from an URL
	 * 
	 * @param url	URL of the image to download
	 * @return		The image as an array of bytes
	 */
	public byte[] downloadImage(URL url) {
		InputStream in;
		byte[] response = null;
		
		try {
			in = new BufferedInputStream(url.openStream());
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			
			while (-1!=(n=in.read(buf))) {
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			
			response = out.toByteArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	/**
	 * Save an image to a selected destination
	 * 
	 * @param image				Image as an array of bytes to save 
	 * @param urlDestination	URL of the destination to save to
	 */
	public void saveImage(byte[] image, String urlDestination) {
		
		FileOutputStream fos;
		
		try {
			fos = new FileOutputStream(urlDestination);
			try {
				fos.write(image);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Delete a poster from local
	 * 
	 * @param posterUrl	URL of the poster to delete
	 */
	public void removePoster(String posterUrl) {
		
		try {
			File file = new File(posterUrl);

			if (file.exists())
				file.delete();
		} catch (Exception e) {
			System.err.println("Problem while deleting the poster : " + posterUrl);
		}
		
	}
}
