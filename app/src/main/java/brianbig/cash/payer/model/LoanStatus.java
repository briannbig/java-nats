package brianbig.cash.payer.model;

/**
 * @author Brian Barasa
 */
public final class LoanStatus {

    public static final int PENDING = 1;
    public static final int DISBURSED = 2;
    public static final int PAYING = 3;
    public static final int REPAID = 4;

    public static String getLabel(int status) {
        return switch (status) {
            case PENDING -> "PENDING";
            case DISBURSED -> "DISBURSED";
            case PAYING -> "PAYING";
            case REPAID -> "REPAID";
            default -> "UNKNOWN";
        };
    }

}
