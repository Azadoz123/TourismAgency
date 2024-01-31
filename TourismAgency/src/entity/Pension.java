package entity;

public class Pension {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private Type type;
    public enum Type {
        ULTRA_ALL_INCLUDE,
        ALL_INCLUDE,
        ROOM_BREAKFAST,
        FULL_PENSION,
        HALF_PENSION,
        ONLY_BED,
        FULL_CREDIT_NOT_INCLUDING_ALCOHOL
    }

    public Pension() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

/*public class Pension {
    private int id;
    private boolean ultraAllInclude;
    private boolean allIncule;
    private boolean roomBreakfast;
    private boolean fullPension;
    private boolean halfPension;
    private boolean onlyBad;
    private boolean fullCreditNotIncludingAlcohol;
    private int hotel_id;
    private Hotel hotel;
    //    private PensionType type;
//    public enum PensionType{
//        ULTRA_ALL_INCLUDE,
//        ALL_INCLUDE,
//        ROOM_BREAKFAST,
//        FULL_PENSION,
//        HALF_PENSION,
//        ONLY_BED,
//        FULL_CREDIT_NOT_INCLUDING_ALCOHOL
//    }

    public Pension() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUltraAllInclude() {
        return ultraAllInclude;
    }

    public void setUltraAllInclude(boolean ultraAllInclude) {
        this.ultraAllInclude = ultraAllInclude;
    }

    public boolean isAllIncule() {
        return allIncule;
    }

    public void setAllIncule(boolean allIncule) {
        this.allIncule = allIncule;
    }

    public boolean isRoomBreakfast() {
        return roomBreakfast;
    }

    public void setRoomBreakfast(boolean roomBreakfast) {
        this.roomBreakfast = roomBreakfast;
    }

    public boolean isFullPension() {
        return fullPension;
    }

    public void setFullPension(boolean fullPension) {
        this.fullPension = fullPension;
    }

    public boolean isHalfPension() {
        return halfPension;
    }

    public void setHalfPension(boolean halfPension) {
        this.halfPension = halfPension;
    }

    public boolean isOnlyBad() {
        return onlyBad;
    }

    public void setOnlyBad(boolean onlyBad) {
        this.onlyBad = onlyBad;
    }

    public boolean isFullCreditNotIncludingAlcohol() {
        return fullCreditNotIncludingAlcohol;
    }

    public void setFullCreditNotIncludingAlcohol(boolean fullCreditNotIncludingAlcohol) {
        this.fullCreditNotIncludingAlcohol = fullCreditNotIncludingAlcohol;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}*/
