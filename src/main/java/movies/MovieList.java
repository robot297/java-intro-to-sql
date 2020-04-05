package movies;

import java.util.List;

import static input.InputUtils.*;

public class MovieList {
    static final String db = "jdbc:sqlite:movie_watchlist.sqlite";

    private static Database database;

    public static void main(String[] args) {
        database = new Database(db);
        addNewMovies();
        displayAllMovies();
        checkIfWatchedAndRate();
        deletWatchedMovies();
        displayAllMovies();
    }

    private static void addNewMovies(){
        do{
            String movieName = stringInput("Enter the movie name.");
            Movie movie = new Movie(movieName);
            database.addNewMovie(movie);

        } while (yesNoInput("Add a movie to the wish list database?"));
    }

    private static void displayAllMovies(){
        List<Movie> allMovies = database.getAllMovies();
        for(Movie movie: allMovies){
            System.err.println(movie.name);
        }
    }

    private static void checkIfWatchedAndRate(){
        List<Movie> unwatchedMovies = database.getAllMoviesByWatched(false);

        for(Movie movie: unwatchedMovies){
            boolean hasWatched = yesNoInput("Have you watched " + movie.name + "?");
            if(hasWatched){
                int stars = intInput("What is your rating for " + movie.name + " out of 5 stars?");
                movie.watched = true;
                movie.stars = stars;
                database.updateMovie(movie);
            }
        }
    }

    private static void deletWatchedMovies(){
        System.out.println("Here are the movies you have watched.");

        List<Movie> watchedMovies = database.getAllMoviesByWatched(true);

        for(Movie movie: watchedMovies){
            boolean delete = yesNoInput("Delete " + movie.name + "?");
            if(delete){
                database.deleteMovie(movie);
            }
        }
    }
}
