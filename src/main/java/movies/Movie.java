package movies;

public class Movie {

    String name;
    int stars;
    boolean watched;

    Movie(String name){
        this.name = name;
    }

    Movie(String name, int stars, boolean watched){
        this.name = name;
        this.stars = stars;
        this.watched = watched;
    }
}
