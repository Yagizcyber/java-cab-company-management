import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String vehiclesFilename = "vehicles.csv";
        String bookingsFilename = "bookings.csv";
        String reportFilename = "report.txt";

        ArrayList<Vehicle> theVehicles = readVehicleDetails(vehiclesFilename);

        CabCompany cabCompany = new CabCompany(theVehicles);

        cabCompany.listAllVehicles();

        cabCompany.readBookings(bookingsFilename);

        cabCompany.listAllBookings();

        cabCompany.printTakings();

        cabCompany.writeReport(reportFilename);

        System.out.println("Test part 5: Results of writing the report.");
        try {
            java.util.List<String> reportLines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(reportFilename));
            for (String line : reportLines) {
                System.out.println(line);
            }
        } catch (java.io.IOException e) {
            System.out.println("Error reading report file");
        }
    }

    public static ArrayList<Vehicle> readVehicleDetails(String filename) {

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            java.util.List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filename));
            for (String line : lines) {
                Vehicle v = decodeVehicleDetails(line);
                if (v != null) vehicles.add(v);
            }
        } catch (java.io.IOException e) {
            System.out.println("Error reading vehicles file");
        }

        return vehicles;
    }

    public static Vehicle decodeVehicleDetails(String line) {
        String[] parts = line.split(",");

        if (parts[0].equals("C")) return new Cab(parts[1], parts[2]);
        if (parts[0].equals("B")) return new Bus(parts[1], parts[3]);
        return null;
    }
}