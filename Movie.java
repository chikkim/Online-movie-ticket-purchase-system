public class Movie{
    public static enum MovieStatus {PLAYINGNOW, PLAYINGSOON, RELEASEDSOON};

    public String photoLocation;
    public String title;
    public String description;
    public int duration; //Duration in hours
    public int movieID;
    public Showtime[] movieShowTime;
    public MovieStatus movieStatus;

    public String  movieShowTimeDetails;

    public Movie(String title, String description, int duration, int movieID, String posterImageLocation) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.movieID = movieID;
        this.photoLocation = posterImageLocation;
    }

    public Movie(String title, String description, int duration, int movieID, String posterImageLocation, String movieShowTimeDetails) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.movieID = movieID;
        this.photoLocation = posterImageLocation;
        this.movieShowTimeDetails = movieShowTimeDetails;
    }

    public Movie (String title, String description) {
        this.title = title;
        this.description = description;
    }
    public Movie(String title) {
        this.title = title;
    }

    public String getMovieShowTimeDetails() {
        return movieShowTimeDetails;
    }
    public String getPhoto() {
        return "./images/" + photoLocation;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setPhoto(String photo) {
        this.photoLocation = photo;
    }

    public void assignShowtime(Showtime[] showTimes) {
        this.movieShowTime = showTimes;
    }
    public Showtime[] getMovieShowTime() {
        return movieShowTime;
    }
    public Showtime getShow(String date, String time) {
        for (Showtime i : this.movieShowTime) {
            if (date.equals(i.getDate()) && time.equals(i.time)) {
                return i;
            }
        }

        return null;
    }

    public void showMovieTime() {
        for (Showtime i : this.movieShowTime) {
            System.out.println(i.date + " " + i.time);
        }
    }

    public MovieStatus getMovieStatus() {
        return this.movieStatus;
    }

    public void setMovieStatus(MovieStatus status) {
        this.movieStatus = status;
    }

    public String toString() {
        return this.title + ", " + this.duration;
    }

}