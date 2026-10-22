public record Booking(String vehicleId, String pickup, String destination) {

    public String toString() {
        return "Vehicle: " + vehicleId + " from " + pickup + " to " + destination;
    }

}