import java.util.*;
import java.nio.file.*;
import java.io.*;

public class CabCompany {

    private Map<String, Vehicle> vehicles;

    public CabCompany(ArrayList<Vehicle> theVehicles) {
        vehicles = new HashMap<>();
        for (Vehicle v : theVehicles) {
            vehicles.put(v.getId(), v);
        }
    }

    public void listAllVehicles() {
        for (Vehicle v : vehicles.values()) {
            System.out.println(v);
        }
        System.out.println();
    }

    public void readBookings(String bookingsFile) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(bookingsFile));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Booking b = new Booking(parts[0], parts[1], parts[2]);
                    Vehicle v = vehicles.get(parts[0]);
                    if (v != null) v.addBooking(b);
                } else {
                    System.out.println("Skipping invalid booking line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading bookings file");
        }
    }

    public void listAllBookings() {
        for (Vehicle v : vehicles.values()) {
            System.out.println(v.getType() + " " + v.getId() + " bookings:");
            List<Booking> bs = v.getBookings();
            if (bs.isEmpty()) {
                System.out.println("  No bookings");
            } else {
                for (Booking b : bs) {
                    System.out.println("  " + b);
                }
            }
            System.out.println();
        }
    }

    public Map<String, Integer> calculateDynamicTakings() {
        Map<String, Integer> totals = new HashMap<>();
        for (Vehicle v : vehicles.values()) {
            int total = 0;
            for (Booking b : v.getBookings()) {
                if (v.getType().equals("Cab")) total += 30;
                if (v.getType().equals("Bus")) total += 10;
            }
            totals.put(v.getId(), total);
        }
        return totals;
    }

    public void printTakings() {
        System.out.println("Vehicle takings:");
        Map<String, Integer> totals = calculateDynamicTakings();
        for (Vehicle v : vehicles.values()) {
            int total = totals.getOrDefault(v.getId(), 0);
            System.out.println(v.getType() + " " + v.getId() + " took £" + total);
        }
        System.out.println("=======================================");
    }

    public void writeReport(String reportFile) {
        try {
            PrintWriter writer = new PrintWriter(reportFile);
            writer.println("Cab Company Report");
            writer.println();

            int totalTakings = 0;

            for (Vehicle v : vehicles.values()) {
                int journeys = v.getBookings().size();
                int takings = 0;
                if (v.getType().equals("Cab")) takings = journeys * 30;
                if (v.getType().equals("Bus")) takings = journeys * 10;
                totalTakings += takings;

                if (v.getType().equals("Cab")) {
                    writer.println("Cab " + v.getId() + " driven by " + v.getDriverOrRoute() +
                            " had " + journeys + " journeys and made £" + takings);
                } else {
                    writer.println("Bus " + v.getId() + " had " + journeys + " journeys and made £" + takings);
                }
            }

            writer.println();
            writer.println("Total takings for the day £" + totalTakings);

            writer.close();

            System.out.println("Report written to " + reportFile);
        } catch (Exception e) {
            System.out.println("Error writing report");
        }
    }
}
