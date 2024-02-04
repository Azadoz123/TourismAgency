package entity;

public class Room {
    private int id;
    private int stock;
    private Double childPrice;
    private Double adultPrice;
    private int numberOfBed;
    private int areaOfRoom;
    private boolean TV;
    private boolean bar;
    private boolean gameConsole;
    private boolean moneyCase;
    private boolean projection;
    private Hotel hotel;
    private int hotelId;
    private Season season;
    private int seasonId;
    private Pension pension;
    private int pensionId;
    private Type type;
    public enum Type {
        SINGLE_ROOM,
        DOUBLE_ROOM,
        JUNIOR_ROOM,
        SUITE_ROOM
    }

    public Room() {
    }

    public Room(int stock, Double childPrice, Double adultPrice, int numberOfBed, int areaOfRoom, boolean TV, boolean bar, boolean gameConsole, boolean moneyCase, boolean projection, Hotel hotel, int hotelId, Season season, int seasonId, Pension pension, int pensionId, Type type) {
        this.stock = stock;
        this.childPrice = childPrice;
        this.adultPrice = adultPrice;
        this.numberOfBed = numberOfBed;
        this.areaOfRoom = areaOfRoom;
        this.TV = TV;
        this.bar = bar;
        this.gameConsole = gameConsole;
        this.moneyCase = moneyCase;
        this.projection = projection;
        this.hotel = hotel;
        this.hotelId = hotelId;
        this.season = season;
        this.seasonId = seasonId;
        this.pension = pension;
        this.pensionId = pensionId;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Double childPrice) {
        this.childPrice = childPrice;
    }

    public Double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public int getAreaOfRoom() {
        return areaOfRoom;
    }

    public void setAreaOfRoom(int areaOfRoom) {
        this.areaOfRoom = areaOfRoom;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public boolean isBar() {
        return bar;
    }

    public void setBar(boolean bar) {
        this.bar = bar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isMoneyCase() {
        return moneyCase;
    }

    public void setMoneyCase(boolean moneyCase) {
        this.moneyCase = moneyCase;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
