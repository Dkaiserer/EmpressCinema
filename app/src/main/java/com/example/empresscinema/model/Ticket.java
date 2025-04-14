package com.example.empresscinema.model;

public class Ticket {
    private String id;
    private String userId;
    private String movieId;
    private String showtime;
    private int seatNumber;

    public Ticket() {}

    public Ticket(String id, String userId, String movieId, String showtime, int seatNumber) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.showtime = showtime;
        this.seatNumber = seatNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

}
