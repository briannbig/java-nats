package brianbig.cash.payer;

import brianbig.cash.payer.model.Loan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

/**
 * @author Brian Barasa
 */
public final class DRY {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static String LoanToJSon(Loan loan) {
        try {
            return MAPPER.writeValueAsString(loan);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    public static Loan LoanFromJSon(String json) {
        try {
            return MAPPER.readValue(json, Loan.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateRandomInt() {
        Random rand = new Random();
        int uBound = 100;
        return rand.nextInt(uBound);

    }

}
