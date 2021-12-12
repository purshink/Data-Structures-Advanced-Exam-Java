package core;

import junit.framework.TestCase;
import models.Movie;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.spec.PSource;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieDatabaseImplTest extends TestCase {

    public void testAddMovie() {
        MovieDatabase movieDatabase = new MovieDatabaseImpl();

        Movie Movie = new Movie("asd", "bsd", 2010, 5000, List.of("Pesho", "Gosho"));
        Movie Movie2 = new Movie("dsd", "esd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie3 = new Movie("hsd", "isd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie4 = new Movie("ksd", "lsd", 2013, 4500, List.of("Pesho", "Gosho"));
        Movie Movie5 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));

        movieDatabase.addMovie(Movie);
        movieDatabase.addMovie(Movie2);
        movieDatabase.addMovie(Movie3);
        movieDatabase.addMovie(Movie4);
        movieDatabase.addMovie(Movie5);
        movieDatabase.removeMovie(Movie.getId());

        boolean thrown = false;

        try {
            movieDatabase.removeMovie("b");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    public void testRemoveMovie() {
    }

    public void testSize() {
    }

    public void testContains() {
    }

    public void testGetMoviesByActor() {
        MovieDatabase movieDatabase = new MovieDatabaseImpl();

        Movie Movie = new Movie("asd", "bsd", 2010, 5000, List.of("Pesho", "Gosho"));
        Movie Movie2 = new Movie("dsd", "esd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie3 = new Movie("hsd", "isd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie4 = new Movie("ksd", "lsd", 2013, 4500, List.of("Pesho", "Gosho"));
        Movie Movie5 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));

        movieDatabase.addMovie(Movie);
        movieDatabase.addMovie(Movie2);
        movieDatabase.addMovie(Movie3);
        movieDatabase.addMovie(Movie4);
        movieDatabase.addMovie(Movie5);
         Iterable<models.Movie> list = movieDatabase.getMoviesByActor("Pesho");

        Movie Movie6 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));
    }

    public void testGetMoviesByActors() {
        MovieDatabase movieDatabase = new MovieDatabaseImpl();

        Movie Movie = new Movie("asd", "bsd", 2010, 5000, List.of("Pesho", "Gosho"));
        Movie Movie2 = new Movie("dsd", "esd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie3 = new Movie("hsd", "isd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie4 = new Movie("ksd", "lsd", 2013, 4500, List.of("Pesho", "Gosho"));
        Movie Movie5 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));

        movieDatabase.addMovie(Movie);
        movieDatabase.addMovie(Movie2);
        movieDatabase.addMovie(Movie3);
        movieDatabase.addMovie(Movie4);
        movieDatabase.addMovie(Movie5);
        Iterable<models.Movie> list = movieDatabase.getMoviesByActors(List.of("Pesho", "Gosho"));

            Movie Movie6 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));

    }

    public void testGetMoviesByYear() {
        MovieDatabase movieDatabase = new MovieDatabaseImpl();

        Movie Movie = new Movie("asd", "bsd", 2010, 5000, List.of("Pesho", "Gosho"));
        Movie Movie2 = new Movie("dsd", "esd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie3 = new Movie("hsd", "isd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie4 = new Movie("ksd", "lsd", 2012, 4500, List.of("Pesho", "Gosho"));
        Movie Movie5 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));

        movieDatabase.addMovie(Movie);
        movieDatabase.addMovie(Movie2);
        movieDatabase.addMovie(Movie3);
        movieDatabase.addMovie(Movie4);

        final Iterable<models.Movie> moviesByYear = movieDatabase.getMoviesByYear(2012);
        movieDatabase.addMovie(Movie5);
    }

    public void testGetMoviesInRatingRange() {
        MovieDatabase movieDatabase = new MovieDatabaseImpl();

        Movie Movie = new Movie("asd", "bsd", 2010, 5000, List.of("Pesho", "Gosho"));
        Movie Movie2 = new Movie("dsd", "esd", 2012, 4000, List.of("Sasho", "Tosho"));
        Movie Movie3 = new Movie("hsd", "isd", 2016, 4000, List.of("Sasho", "Tosho"));
        Movie Movie4 = new Movie("ksd", "lsd", 2012, 4500, List.of("Pesho", "Gosho"));
        Movie Movie5 = new Movie("nsd", "osd", 2014, 4500, List.of("Pesho", "Gosho"));

        movieDatabase.addMovie(Movie);
        movieDatabase.addMovie(Movie2);
        movieDatabase.addMovie(Movie3);
        movieDatabase.addMovie(Movie4);

        Iterable<models.Movie> moviesInRatingRange = movieDatabase.getMoviesInRatingRange(4000, 4500);
        movieDatabase.addMovie(Movie5);
    }

    public void testGetAllMoviesOrderedByActorPopularityThenByRatingThenByYear() {
    }


}