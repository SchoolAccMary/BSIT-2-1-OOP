public class Motorcycle extends Vehicle {
// TODO 4: Add a field only motorcycles have:
    private boolean hasSidecar;
// TODO 5: Create the constructor:
    public Motorcycle(String brand, int year, boolean hasSidecar) {
    super(brand, year);
    this.hasSidecar = hasSidecar;
    }
// TODO 6: Override displayInfo() with @Override.
// If hasSidecar is true, show &quot;with sidecar&quot;, else &quot;no sidecar&quot;.
// Motorcycle: &lt;brand&gt; (&lt;year&gt;) - &lt;sidecar text&gt;
    @Override
    public void displayInfo() {
        String sidecarText = hasSidecar ? "with sidecar" : "no sidecar";
        System.out.println("Motorcycle: " + brand + " (" + year + ") - " + sidecarText);
    }
}