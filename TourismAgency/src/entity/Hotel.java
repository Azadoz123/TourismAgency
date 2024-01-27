package entity;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String star;
    private boolean car_parking;
    private boolean wifi;
    private boolean pool;
    private boolean fitness_center;
    private boolean concierge;
    private boolean spa;
    private boolean room_service;

    public Hotel() {
    }

    public Hotel(int id, String name, String address, String phoneNumber, String star, boolean car_parking, boolean wifi,
                 boolean pool, boolean fitness_center, boolean concierge, boolean spa, boolean room_service) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.star = star;
        this.car_parking = car_parking;
        this.wifi = wifi;
        this.pool = pool;
        this.fitness_center = fitness_center;
        this.concierge = concierge;
        this.spa = spa;
        this.room_service = room_service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isCar_parking() {
        return car_parking;
    }

    public void setCar_parking(boolean car_parking) {
        this.car_parking = car_parking;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness_center() {
        return fitness_center;
    }

    public void setFitness_center(boolean fitness_center) {
        this.fitness_center = fitness_center;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoom_service() {
        return room_service;
    }

    public void setRoom_service(boolean room_service) {
        this.room_service = room_service;
    }
}
