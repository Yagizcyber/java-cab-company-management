import java.util.*;

public abstract class Vehicle {

    private String id;
    private List<Booking> bookings;

    public Vehicle(String id) {
        this.id = id;
        bookings = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public abstract String getType();
    public abstract String getDriverOrRoute();

    public String toString() {
        if (getType().equals("Cab")) {
            return "Cab " + getId() + " driven by " + getDriverOrRoute();
        } else {
            return "Bus " + getId() + " has the route " + getDriverOrRoute();
        }
    }
}