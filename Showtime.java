public class Showtime {
    public static enum Status {CANCELLED, AVAILABLE, FULL};

    public String date;
    public String time;
    public Status status;

    private String theaterLocation;

    private String theaterRoomIDShowtimeIsPlayingAt;

    public Showtime(String date, String time, Status status) {
        this.date = date;
        this.time = time; //Format hr:min(am/pm)
        this.status = status;
    }

    public Showtime(String month, String day, String year, String time, Status status) {
        this.date =  month + " " + day + " " + year;
        this.time = time; //Format hr:min
        this.status = status;
    }

    public Showtime(String month, String day, String year, String time, String theaterLocation, String theaterRoomIDShowtimeIsPlayingAt) {
        this.date =  month + " " + day + " " + year;
        this.time = time; //Format hr:min
        this.theaterLocation = theaterLocation;
        this.theaterRoomIDShowtimeIsPlayingAt = theaterRoomIDShowtimeIsPlayingAt;
    }

    public String getDate() {
        return this.date;
    }

    public String getRoom() {
        return this.theaterRoomIDShowtimeIsPlayingAt;
    }

    public String getTime() {
        return this.time;
    }

    public String getTheater() {
        return this.theaterLocation;
    }

    public void setDate(String month, String day, String year) {
        this.date = month + " " + day + " " + year;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        return this.date + " at " + this.time;
    }

}