public class Theater {
    public String name;
    public String location;
    public Movie[] movies;
    private int theaterID;

    public Theater(String name, String location, Movie[] movies, int id) {
        this.name = location;
        this.location = location;
        this.movies = movies;
        this.theaterID = id;
    }

    public Theater(String name, String location, int id) {
        this.name = location;
        this.location = location;
        this.theaterID = id;
    }

    public Theater() {

    }

    public void removeMovie(String title) {
        int len = (this.movies).length;
        Movie[] removeMovie = new Movie[len - 1];
        int i = 0;
        for (Movie m : this.movies) {
            if (!title.equals(m.title)) {
                removeMovie[i] = m;
                i++;
            }
        }
        this.movies = removeMovie;
    }

    public void addMovie(Movie m) {
        int len = (this.movies).length;
        Movie[] addMovie = new Movie[len + 1]; //New larger array
        for (int i=0; i < len; i++) {
            addMovie[i] = (this.movies)[i];
        }
        addMovie[len] = m;      //The added movie
        this.movies = addMovie;
    }

    private void initializeMovieList() {

    }

    public Movie[] getMoviesList() {
        return this.movies;
    }

    public Movie getMovie(int movieID) {
        for (Movie m : this.movies) {
            if (movieID == m.movieID) {
                return m;
            }
        }
        return null;
    }

    public int getTheaterID() {
        return this.theaterID;
    }

    public void setTheaterID(int id) {
        this.theaterID = id;
    }

    public String getLocation() {
        return this.location;
    }
}