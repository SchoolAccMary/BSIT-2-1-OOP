package atm;

public class InsufficientFundsException extends Exception {
    private final String shortfall;

    public InsufficientFundsException(String shortfall) {
        super("Insufficient funds. You are short by PHP " + String.format("%.2f", shortfall));

        this.shortfall = shortfall;
    }

    public String getShortfall() {
        return shortfall;
    }
}
