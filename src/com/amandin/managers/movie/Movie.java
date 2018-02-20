package com.amandin.managers.movie;

import java.io.Serializable;


/**
 * Representation of a movie 
 * 
 * @author alex
 * @version
 */
public class Movie implements Serializable {

	private String 	title,
					director,
					type,
					synopsis,
					localUrl,
					length,
					releaseYear,
					viewsCounter,
					imdbRate,
					posterUrl,
					actors;
	
	
	/**
	 *	Default constructor 
	 */
	public Movie() {}
	
	
	/**
	 * Constructor
	 * 
	 * @param title			Movie's title
	 * @param director		Movie's director
	 * @param type			Movie's type/genre
	 * @param synopsis		Movie's synopsis
	 * @param url			Movie's URL on local or external device
	 * @param length		Movie's runtime
	 * @param imdbRate		Movie's IMDB Rate
	 * @param viewsCounter	Movie's views counter
	 * @param releaseYear	Movie's release year
	 * @param actors		Movie's actors
	 * @param posterUrl		URL of the movie's poster
	 */
	public Movie(String title, String director, String type, String synopsis, 
					String url, String length, String imdbRate, String viewsCounter, 
					String releaseYear, String actors, String posterUrl) {

		this.title = title;
		this.director = director;
		this.type = type;
		this.synopsis = synopsis;
		this.localUrl = url;
		this.length = length;
		this.imdbRate = imdbRate;
		this.viewsCounter = viewsCounter;
		this.releaseYear = releaseYear;
		this.actors = actors;
		this.posterUrl = posterUrl;
	}

	

	
	/* ******* GETTERS  &  SETTERS  ****** */
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	public String getLocalUrl() {
		return localUrl;
	}


	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}


	public String getLength() {
		return length;
	}


	public void setLength(String length) {
		this.length = length;
	}


	public String getImdbRate() {
		return imdbRate;
	}


	public void setImdbRate(String imdbRate) {
		this.imdbRate = imdbRate;
	}


	public String getViewsCounter() {
		return viewsCounter;
	}


	public void setViewsCounter(String viewsCounter) {
		this.viewsCounter = viewsCounter;
	}


	public String getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}


	public String getActors() {
		return actors;
	}


	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
}
