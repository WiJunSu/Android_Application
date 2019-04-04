package com.example.qqw;

public class Movie {
    int movieID;
    String movieTitle;
    String movieDirector;
    String movieActor;
    String movieStory;
    String moviekind;

    public Movie(int movieID, String movieTitle, String movieDirector, String movieActor, String movieStory, String moviekind) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDirector = movieDirector;
        this.movieActor = movieActor;
        this.movieStory = movieStory;
        this.moviekind = moviekind;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieStory() {
        return movieStory;
    }

    public void setMovieStory(String movieStory) {
        this.movieStory = movieStory;
    }

    public String getMoviekind() {
        return moviekind;
    }

    public void setMoviekind(String moviekind) {
        this.moviekind = moviekind;
    }
}
