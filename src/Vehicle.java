public class Vehicle {
    // Fields shared by ALL vehicles.
    //'protected'; means subclasses can use them too.
    protected String brand;
    protected int year;
    // Constructor: runs when a new Vehicle is created
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    // A behavior shared by all vehicles
    public void displayInfo() {
        System.out.println("Vehicle: " + brand + "( " + year + ")");
    }
}