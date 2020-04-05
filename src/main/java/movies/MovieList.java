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
            System.err.println(movie);
        }
    }
}
