package com.amandin.managers.imdb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.amandin.managers.movie.Movie;


/**
 * IMDB Api Manager
 * 
 * @author Alexis Mandin
 * @version
 */
public class ImdbManager {

	private String imdbApiKey;
	
	
	/**
	 * Constructor 
	 * 
	 * @param imdbApiKey	Key of the IMDB Api to used
	 */
	public ImdbManager(String imdbApiKey) {
		
		this.imdbApiKey = imdbApiKey;
	}

	
	/**
	 * Find the movie from IMDB with movie ID
	 * 
	 * @param	imdbMovieId	IMDB ID of the movie
	 * @return	A Movie object
	 */
	public Movie getMovieFromImdbById(String imdbMovieId) {
		Movie movieFound = null;
		
		Request request = new Request(imdbMovieId);
		String imdbRequest = request.createImdbRequest(imdbApiKey);
		String imdbResult = "";
		
		try {
			imdbResult = getRequest(imdbRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!imdbResult.equals("")) 
			if (imdbResult.equals("400"))
				return null;
			else
				movieFound = extractMovieInformation(imdbResult);	
		
		return movieFound;
	}

	/**
	 * Find the movie from IMDB with the title
	 * 
	 * @param 	title		Title of the movie
	 * @param	releaseYear	Release year of the movie 
	 * @return	A Movie object
	 */
	public Movie getMovieFromImdbByTitle(String title, String releaseYear) {
		
		Movie movieFound = null;
		
		Request request = new Request(title, releaseYear);
		String imdbRequest = request.createImdbRequest(imdbApiKey);
		String imdbResult = "";
		
		try {
			imdbResult = getRequest(imdbRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!imdbResult.equals("")) 
			if (imdbResult.equals("400"))
				return null;
			else
				movieFound = extractMovieInformation(imdbResult);	
		
		return movieFound;
	}

	/**
	 * Execute the request to the Imdb api
	 * 
	 * @param	imdbRequest	Request to execute
	 * @return	Result in a XML format
	 * 
	 * @exception
	 */
	private String getRequest(String imdbRequest) throws Exception {
		
		URL imdbUrl = new URL(imdbRequest);
		HttpURLConnection connection = (HttpURLConnection) imdbUrl.openConnection();
		connection.setRequestMethod("GET");
		
		if (connection.getResponseCode() != 200) {
			return "";
		}
		else {
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		}
	}

	
	/**
	 * Extract the movie informations from the IMDB's XML result
	 * 
	 * @param	imdbResultXml	XML String to extract from
	 * @return	A Movie object
	 */
	private Movie extractMovieInformation(String imdbResultXml) {
		
		Movie movie = new Movie();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(imdbResultXml));
			
			Document doc = db.parse(is);
			NodeList nodesList = doc.getElementsByTagName("movie");
			Element element = (Element) nodesList.item(0);
			
			if (element != null) {
				movie.setTitle(element.getAttribute("title"));
				movie.setType(element.getAttribute("genre"));
				movie.setDirector(element.getAttribute("director"));		
				movie.setSynopsis(element.getAttribute("plot"));
				movie.setReleaseYear(element.getAttribute("year"));
				movie.setActors(element.getAttribute("actors"));	
				movie.setLength(element.getAttribute("runtime"));
				movie.setImdbRate(element.getAttribute("imdbRating"));
				movie.setPosterUrl(element.getAttribute("poster"));
			}
			else 
				return null;
		}
		catch (Exception e) {
		    e.printStackTrace();
		}

		return movie;
	}
}
