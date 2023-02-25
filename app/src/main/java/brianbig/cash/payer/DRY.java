package brianbig.cash.payer;

import brianbig.cash.payer.model.Loan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Brian Barasa
 */
public final class DRY {
    public static final Gson gson = new GsonBuilder().create();
    public static String LoanToJSon(Loan loan) {
        return gson.toJson(loan);

    }
    public static Loan LoanFromJSon(String json) {
        return gson.fromJson(json, Loan.class);
    }
    public static String LoanRepayRequestToJSon(LoanRepayRequest loanRepayRequest) {
        return gson.toJson(loanRepayRequest);

    }
    public static LoanRepayRequest LoanRepayRequestFromJSon(String json) {
        return gson.fromJson(json, LoanRepayRequest.class);
    }

}
