public class Car extends Vehicle {
// TODO 1: Add a field only cars have:
    private int numberOfDoors;
// TODO 2: Create the constructor:
    public Car(String brand, int year, int numberOfDoors) {
    super(brand, year); // reuse Vehicle's constructor
    this.numberOfDoors = numberOfDoors;
    }
// TODO 3: Override displayInfo() with @Override so it prints:
//Car: <brand> (<year>) - <numberOfDoors> doors
    @Override
    public void displayInfo() {
        System.out.println("Car: " + brand + " (" + year + ") - " + numberOfDoors + " doors");
    }
}