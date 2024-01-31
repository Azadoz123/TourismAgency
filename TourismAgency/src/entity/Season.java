package entity;

import java.util.Date;

public class Season {
    private int id;
    private Date startTime;
    private Date finishTime;
    private Hotel hotel;
    private int hotel_id;

    public Season() {
    }

    public Season(Date startTime, Date finishTime, int hotel_id) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.hotel_id = hotel_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
}
