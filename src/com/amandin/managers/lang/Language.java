package com.amandin.managers.lang;

public class Language {
	
	private LanguagesEnum langagueEnum;
	
	// Related to Movie object
	private String title;
	private String type;
	private String director;
	private String releaseYear;
	private String length;
	private String imdbRate; 
	private String viewsCounter;
	private String actors;
	private String poster;
	private String localUrl;
	private String synopsis;
	
	// Related to sections titles	
	private String filter;
	private String availableMovies;
	private String information;
	private String videoPlayer; 
	
	// divers informations
	private String ok;
	private String or;
	private String unknown;
	private String warning;
	private String confirmation;
	private String viewsCounterDescription;
	private String restartToUpdateLanguage;
	
	// related to warning messages
	private String filmNotFound;
	private String posterNotFound;
	private String noMoviesToLoad;
	private String movieDeletedOrMoved;
	private String movieNotFound;
	private String movieSameName;
	private String vlcNotFound;
	
	// related to message requiring an user interaction
	private String addTitlePlease;
	private String clickToChoosePoster;
	private String clickToChooseAMovie;
	private String sureToDelete;
	private String deletePosterConfirmation;
			
	// related to video player
	private String play;
	private String pause;
	private String stop;
	private String fastForward;
	private String fastBackward;
	
	// related to buttons and menu
	private String file;
	private String tools;
	private String help;
	private String add;
	private String edit;
	private String delete;
	private String cancel;
	private String addMovie;
	private String editMovie;
	private String deleteMovie;
	private String preferences;
	private String watchWithExternalPlayer;
	private String closeApplication;
	
	private String languages;
	private String english;
	private String french;
	private String germain;
	private String spanish;

	// related to IMDB
	private String imdbIDAsk;
	private String imdbTitleAsk;
	private String imdbReleaseYearAsk;
	private String imdbIdOrTitle;
	private String fillMovieFromImdb;
	private String updateFromImdb;
	
	// related to footBar status
	private String applicationStartedSucces;
	
	// option windows
	private String imdbApiKey;
	private String vlcLocation;
	
	public void loadLanguage(LanguagesEnum languageSelected) {
		
		langagueEnum = languageSelected;
		
		if (langagueEnum == LanguagesEnum.ENGLISH) {

			title = "Title";
			type = "Genre";
			director = "Director";
			releaseYear = "Year of release";
			length = "Length";
			imdbRate = "IMDB Rate";
			viewsCounter = "Number of views";
			actors = "Actors";
			poster = "Poster";
			localUrl = "URL";
			synopsis = "Synopsis";
					
			//--------------
			filter = "Filters";
			availableMovies = "Movies available";
			information = "Informations";
			videoPlayer = "Videos player";
			
			//--------------
			ok = "OK";
			or = "OR";
			warning = "Warning";
			unknown = "Unknown";
			confirmation = "Confirmation";
			viewsCounterDescription = "The counter is increased when the movie is read in an external player.";
			restartToUpdateLanguage = "The new selected language will be applyied at the next start.";
			
			//--------------
			posterNotFound = "No poster found";
			filmNotFound = "No film found with these filters.";
			noMoviesToLoad = "There is no movie to load.";
			movieDeletedOrMoved = "Movie deleted, moved or incorrect URL";
			movieNotFound = "Movie not found. Check the ID or the title.";	
			movieSameName = "There is already a movie with this title";
			vlcNotFound = "\nMovies can't be played : VLC Media Player can't be found. "
					+ "\n\nPlease install the 64bits version or configure it in the option windows.";
			
			//--------------
			addTitlePlease = "Please add a title";
			clickToChoosePoster = "Click here to choose a poster (.jpg)";
			clickToChooseAMovie = "Click here to choose a movie";
			sureToDelete = "Are you sure to delete the movie : ";
			deletePosterConfirmation = "Do you want to delete the poster ?";
			
			//--------------
			play = "Play";
			pause = "Pause";
			stop = "Stop";
			fastForward = "Skip forwards";
			fastBackward = "Skip backwards";
					
			//--------------
			file = "File";
			tools = "Tools";
			help = "Help";
			add = "Add";
			edit = "Edit";
			delete = "Delete";
			cancel = "Cancel";
			addMovie = "Add a movie";
			editMovie = "Edit a movie";
			deleteMovie = "Delete a movie";
			preferences = "Preferences";
			watchWithExternalPlayer = "Watch with external player";
			closeApplication = "Close application";
			
			languages = "Languages";
			english = "English";
			french = "French";
			germain = "Germain";
			spanish = "Spanish";
			
			//--------------
			imdbIDAsk = "Enter the IMDB ID";
			imdbTitleAsk = "Enter the title";
			imdbReleaseYearAsk = "Enter the release year (optional)";
			imdbIdOrTitle = "Enter the IMDB Id or the Title";
			fillMovieFromImdb = "Fill from IMDB";
			updateFromImdb = "Update from IMDB";
			
			//--------------
			applicationStartedSucces = "Application started successfully.";
			
			//-------------
			imdbApiKey = "IMDB api key";
			vlcLocation = "VLC url";
		}
		else if (langagueEnum == LanguagesEnum.FRENCH) {
			
			title = "Titre";
			type = "Genre";
			director = "Réalisateur";
			releaseYear = "Date de sortie";
			length = "Durée";
			imdbRate = "Note IMDB";
			viewsCounter = "Nombre de vues";
			actors = "Acteurs";
			poster = "Affiche";
			localUrl = "URL";
			synopsis = "Synopsis";
			
			//--------------			
			filter = "Filtres";
			availableMovies = "Films disponibles";
			information = "Informations";
			videoPlayer = "Lecteur vidéos";
			
			//--------------
			ok = "OK";
			or = "OU";
			warning = "Attention";	
			unknown = "Inconnu";
			confirmation = "Confirmation";
			viewsCounterDescription = "Le compteur est incrémenté lorsque le film est lu dans un lecteur externe.";
			restartToUpdateLanguage = "La nouvelle langue sélectionnée sera appliquée au prochain démarrage.";
			
			//--------------
			filmNotFound = "Aucun film trouvé avec ces filtres.";
			posterNotFound = "Aucun poster trouvé";
			noMoviesToLoad = "Il n'y a aucun film à charger.";
			movieDeletedOrMoved = "Film effacé, déplacé ou URL incorrecte";
			movieNotFound = "Film non trouvé. Vérifier l'ID ou le titre.";
			movieSameName = "Il exite déjà un film avec ce titre";
			vlcNotFound = "\nLecture impossible: VLC Media Player est absent."
					+ "\n\nVeuillez installer la version 64bits ou paramétré le dans la fenêtre d'option.";
			
			//--------------
			addTitlePlease = "Veuillez ajouter un titre";
			clickToChoosePoster = "Cliquer ici pour sélectionner un poster  (.jpg)";
			clickToChooseAMovie = "Cliquer ici pour sélectionner un film";
			sureToDelete = "Êtes-vous sûr de vouloir supprimer le film : ";
			deletePosterConfirmation = "Désirez-vous supprimer le poster ?";
			
			//--------------
			play = "Lecture";
			pause = "Pause";
			stop = "Stop";
			fastForward = "Avance rapide";
			fastBackward = "Retour rapide";
			
			//--------------
			file = "Fichier";
			tools = "Outils";
			help = "Aide";
			add = "Ajouter";
			edit = "Editer";
			delete = "Supprimer";
			cancel = "Annuler";
			addMovie = "Ajouter un film";
			editMovie = "Editer un film";
			deleteMovie = "Supprimer un film";
			preferences = "Préférences";
			watchWithExternalPlayer = "Lire avec un lecteur externe";
			closeApplication = "Fermer l'application";
			
			languages = "Langues";
			english = "Anglais";
			french = "Français";
			germain = "Allemand";
			spanish = "Espagnol";
			
			//--------------
			imdbIDAsk = "Entrer l'ID IMDB";
			imdbTitleAsk = "Entrer le titre";
			imdbReleaseYearAsk = "Entrer l'année de sortie (optionnel)";
			imdbIdOrTitle = "Entre l'id IMDBB ou le titre";
			fillMovieFromImdb = "Remplir depuis IMDB";
			updateFromImdb = "Editer depuis IMDB";
			
			//--------------
			applicationStartedSucces = "L'application a démarré avec succès.";		
			
			//--------------
			imdbApiKey = "Clé api IDMB";
			vlcLocation = "URL de VLC";
		}
	}



	/*
	 * GETTERS 
	 */
	public LanguagesEnum getLangagueEnum() {
		return langagueEnum;
	}
	
	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public String getDirector() {
		return director;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public String getLength() {
		return length;
	}

	public String getImdbRate() {
		return imdbRate;
	}

	public String getViewsCounter() {
		return viewsCounter;
	}

	public String getActors() {
		return actors;
	}

	public String getPoster() {
		return poster;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getFilter() {
		return filter;
	}

	public String getAvailableMovies() {
		return availableMovies;
	}

	public String getInformation() {
		return information;
	}

	public String getVideoPlayer() {
		return videoPlayer;
	}

	public String getOk() {
		return ok;
	}

	public String getOr() {
		return or;
	}

	public String getUnknown() {
		return unknown;
	}

	public String getWarning() {
		return warning;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public String getViewsCounterDescription() {
		return viewsCounterDescription;
	}

	public String getRestartToUpdateLanguage() {
		return restartToUpdateLanguage;
	}

	public String getFilmNotFound() {
		return filmNotFound;
	}

	public String getPosterNotFound() {
		return posterNotFound;
	}

	public String getNoMoviesToLoad() {
		return noMoviesToLoad;
	}

	public String getMovieDeletedOrMoved() {
		return movieDeletedOrMoved;
	}

	public String getMovieNotFound() {
		return movieNotFound;
	}

	public String getMovieSameName() {
		return movieSameName;
	}

	public String getVlcNotFound() {
		return vlcNotFound;
	}

	public String getAddTitlePlease() {
		return addTitlePlease;
	}

	public String getClickToChoosePoster() {
		return clickToChoosePoster;
	}

	public String getClickToChooseAMovie() {
		return clickToChooseAMovie;
	}

	public String getSureToDelete() {
		return sureToDelete;
	}

	public String getDeletePosterConfirmation() {
		return deletePosterConfirmation;
	}

	public String getPlay() {
		return play;
	}

	public String getPause() {
		return pause;
	}

	public String getStop() {
		return stop;
	}

	public String getFastForward() {
		return fastForward;
	}

	public String getFastBackward() {
		return fastBackward;
	}

	public String getFile() {
		return file;
	}

	public String getTools() {
		return tools;
	}

	public String getHelp() {
		return help;
	}
	
	public String getAdd() {
		return add;
	}

	public String getEdit() {
		return edit;
	}

	public String getDelete() {
		return delete;
	}

	public String getCancel() {
		return cancel;
	}
	
	public String getAddMovie() {
		return addMovie;
	}

	public String getEditMovie() {
		return editMovie;
	}

	public String getDeleteMovie() {
		return deleteMovie;
	}

	public String getPreferences() {
		return preferences;
	}

	public String getWatchWithExternalPlayer() {
		return watchWithExternalPlayer;
	}

	public String getCloseApplication() {
		return closeApplication;
	}
	
	public String getLanguages() {
		return languages;
	}

	public String getEnglish() {
		return english;
	}

	public String getFrench() {
		return french;
	}

	public String getGermain() {
		return germain;
	}

	public String getSpanish() {
		return spanish;
	}

	public String getImdbIDAsk() {
		return imdbIDAsk;
	}

	public String getImdbTitleAsk() {
		return imdbTitleAsk;
	}

	public String getImdbReleaseYearAsk() {
		return imdbReleaseYearAsk;
	}

	public String getImdbIdOrTitle() {
		return imdbIdOrTitle;
	}

	public String getFillMovieFromImdb() {
		return fillMovieFromImdb;
	}

	public String getUpdateFromImdb() {
		return updateFromImdb;
	}

	public String getApplicationStartedSucces() {
		return applicationStartedSucces;
	}



	public String getImdbApiKey() {
		return imdbApiKey;
	}



	public String getVlcLocation() {
		return vlcLocation;
	}
}
	