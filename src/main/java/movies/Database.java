package movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String dataBasePath;

    Database(String dataBasePath){
        this.dataBasePath = dataBasePath;

        try(Connection connection = DriverManager.getConnection(dataBasePath);
            Statement statement = connection.createStatement()){

            statement.execute("CREATE TABLE IF NOT EXISTS movies (name TEXT, stars INTEGER, watched BOOLEAN)");

        } catch (SQLException sqle){
            System.err.println("Error creating the movie DB table because " + sqle.getMessage());
        }
    }

    public void addNewMovie(Movie movie){
        try(Connection connection = DriverManager.getConnection(dataBasePath);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movies VALUES (?,?,?)")){

            preparedStatement.setString(1, movie.name);
            preparedStatement.setInt(2, movie.stars);
            preparedStatement.setBoolean(3, movie.watched);
            preparedStatement.executeUpdate();

        } catch (SQLException sqle){
            System.err.println("Error adding movie because " + sqle.getMessage());
        }
    }

    public List<Movie> getAllMovies(){

        try(Connection connection = DriverManager.getConnection(dataBasePath);
        Statement statement = connection.createStatement()){

            ResultSet movieResults = statement.executeQuery("SELECT * FROM movies ORDER BY name");

            List<Movie> movies = new ArrayList<>();

            while(movieResults.next()){
                String name = movieResults.getString("name");
                int stars = movieResults.getInt("stars");
                boolean watched = movieResults.getBoolean("watched");

                Movie movie = new Movie(name, stars, watched);
                movies.add(movie);
            }
            return movies;
        } catch (SQLException sqle){
            System.err.println("Error querying movie DB table because " + sqle);
            return null;
        }


    }
}
