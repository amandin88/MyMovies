package com.amandin.managers.imdb;


/**
 * IMDB Api requests managers 
 * 
 * @author Alexis Mandin
 * @version
 */
public class Request {
	
	private String	imdbMovieId,
					title,
					releaseYear;

	
	/**
	 * Constructor by ID
	 * 
	 * @param imdbMovieId	Movie's IMDB ID
	 */
	public Request(String imdbMovieId) {
		this.imdbMovieId = imdbMovieId;
	}
	
	
	/**
	 * Constructor by Title
	 * 
	 * @param	title		Movie's title
	 * @param	releaseYear	Movie's release year
	 */
	public Request(String title, String releaseYear) {
		this.title = title;
		this.releaseYear = releaseYear;
	}
	
	
	/**
	 * Create the request to be used by IMDB Api
	 * 
	 * @param	imdbApiKey	IMDB Api key to used
	 * @return	A request to be executed by IMDB Api
	 */
	public String createImdbRequest(String imdbApiKey) {
		String request = "http://www.omdbapi.com/?apikey=" + imdbApiKey;
		String parameters = "&r=xml&plot=full";
		
		if (imdbMovieId != null) {
			request = request + parameters + "&i=" + imdbMovieId;
		}
		else {
			// title required
			if (title != null && !title.equals("")) 
				parameters = parameters + "&t=" + title;
			
			// releaseYear optional
			if (releaseYear != null && !releaseYear.equals(""))
				parameters = parameters + "&y=" + releaseYear;
			
			request = request + parameters;
		}		
		// replace all space by '+' character
		request = request.replace(" ", "+");
		
		return request;
	}
}
