package core;

import models.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class MovieDatabaseImpl implements MovieDatabase {

    private Map<String, Movie> moviesById;
    private  Map<String, Map<String, Movie>> moviesByActors;
    private Map<String , String> actorsByMovies;


    public MovieDatabaseImpl() {
        this.moviesById = new LinkedHashMap<>();
        this.moviesByActors = new LinkedHashMap<>();
        this.actorsByMovies = new LinkedHashMap<>();
    }

    @Override
    public void addMovie(Movie movie) {
        String id = movie.getId();


        this.moviesById.put(id, movie);

        List<String> actors = movie.getActors();
        if(actors != null){
            actors.forEach(actor -> {
                this.actorsByMovies.put(id, actor);
                if(!this.moviesByActors.containsKey(actor)){
                    this.moviesByActors.put(actor, new LinkedHashMap<>());
                }
                this.moviesByActors.get(actor).put(movie.getId(), movie);

            });

        }



    }

    @Override
    public void removeMovie(String movieId) {
        if (!this.moviesById.containsKey(movieId)) {
            throw new IllegalArgumentException();
        }

        this.moviesById.remove(movieId);
        this.moviesByActors.get(actorsByMovies.get(movieId)).remove(movieId);
        this.actorsByMovies.remove(movieId);

    }

    @Override
    public int size() {
        return this.moviesById.size();
    }

    @Override
    public boolean contains(Movie movie) {
        return this.moviesById.containsKey(movie.getId());
    }

    @Override
    public Iterable<Movie> getMoviesByActor(String actorName) {
        if(!moviesByActors.containsKey(actorName)){
            throw new IllegalArgumentException();
        }

        return this.moviesByActors.get(actorName).values().stream().sorted((first, second) -> {
            int result = Double.compare(second.getRating(),first.getRating());


            if(result == 0){
                result = Integer.compare(second.getReleaseYear(), first.getReleaseYear());
            }

            return result;})
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Movie> getMoviesByActors(List<String> actors) {

        return  this.moviesById.values().stream().filter(movie -> movie.getActors().containsAll(actors)).sorted((first, second) -> {
            int result = Double.compare(second.getRating(),first.getRating());


            if(result == 0){
                result = Integer.compare(second.getReleaseYear(), first.getReleaseYear());
            }

            return result;})
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Movie> getMoviesByYear(Integer releaseYear) {
        return this.moviesById.values()
                .stream()
                .filter(movie -> movie.getReleaseYear() == releaseYear)
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Movie> getMoviesInRatingRange(double lowerBound, double upperBound) {
        return this.moviesById.values().stream()
                .filter(h -> h.getRating() >= lowerBound && h.getRating() <= upperBound)
                .sorted((first, second) -> {
                    int result = Double.compare(second.getRating(),first.getRating());


                    if(result == 0){
                        result = Integer.compare(second.getReleaseYear(), first.getReleaseYear());
                    }

                    return result;})
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Movie> getAllMoviesOrderedByActorPopularityThenByRatingThenByYear() {

        return this.moviesById.values().stream()
                .sorted((first, second) -> {
                    int result = this.moviesByActors.get(this.actorsByMovies.get(second.getId())).size()
                            - (this.moviesByActors.get(this.actorsByMovies.get(first.getId())).size());

                    if(result == 0){
                        result = Double.compare(second.getRating(),first.getRating());
                    }

                    if(result == 0){
                        result = Integer.compare(second.getReleaseYear(), first.getReleaseYear());
                    }

                    return result;
                })
                .collect(Collectors.toList());
    }
}
