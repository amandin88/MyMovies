package com.amandin;

import java.io.File;

import com.amandin.generation.MoviesFinder;

public class Test {

	public static void main(String[] args) {

		MoviesFinder mf = new MoviesFinder();
		
		mf.findMoviesFromLocation(new File("E:\\Users_Folders\\Downloads"));
		
		int cpt = 1;
		for (File file : mf.getMoviesListFound()) {
			System.out.println(cpt + " : " + file.getName());
			cpt++;
		}
	}
}