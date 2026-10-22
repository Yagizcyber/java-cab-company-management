public class Bus extends Vehicle {

    private String route;

    public Bus(String id, String route) {
        super(id);
        this.route = route;
    }

    public String getType() {
        return "Bus";
    }

    public String getDriverOrRoute() {
        return route;
    }

    public String toString() {
        return "Bus " + getId() + " has the route " + route;
    }
}