public class Cab extends Vehicle {

    private String driver;

    public Cab(String id, String driver) {
        super(id);
        this.driver = driver;
    }

    public String getType() {
        return "Cab";
    }

    public String getDriverOrRoute() {
        return driver;
    }

    public String toString() {
        return "Cab " + getId() + " driven by " + driver;
    }
}