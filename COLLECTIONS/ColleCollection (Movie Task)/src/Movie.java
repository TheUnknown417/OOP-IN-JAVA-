import java.util.Objects;

public class Movie {

    private String name;
    private String release_date;
    private double rating;
    private String director;

    public Movie(String director, double rating, String release_date, String name) {
        this.director = director;
        this.rating = rating;
        this.release_date = release_date;
        this.name = name;
    }

    public double getRating() {
        return rating;
    }


    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {

        return String.format("Movie : %s \nDirector : %s \nRelease Date : %s \nRating : %s\n", name, director, release_date, rating);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(rating, movie.rating) == 0 && Objects.equals(name, movie.name) && Objects.equals(release_date, movie.release_date) && Objects.equals(director, movie.director);
    }
}

