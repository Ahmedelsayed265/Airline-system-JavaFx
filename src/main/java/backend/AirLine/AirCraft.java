package backend.AirLine;

public class AirCraft extends Model {
    private static int counter;
    private String airCraftType;
    private int capacity;

    public AirCraft(String airCraftType, int capacity) {
        super(++counter);
        this.airCraftType = airCraftType;
        this.capacity = capacity;
    }

    public String getAirCraftType() {
        return airCraftType;
    }

    public void setAirCraftType(String airCraftType) {
        this.airCraftType = airCraftType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //add addAirCraft - editAirCraft - deleteAirCraft - displayAirCraft
}
