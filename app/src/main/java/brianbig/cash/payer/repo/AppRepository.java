package brianbig.cash.payer.repo;

import brianbig.cash.payer.model.Customer;
import brianbig.cash.payer.model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Brian Barasa
 */
public class AppRepository {

    private static AppRepository INSTANCE = null;

    List<Customer> customers = new ArrayList<>();
    List<Loan> loans = new ArrayList<>();

    private AppRepository() {

    }
    public static AppRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppRepository();
        }
        return INSTANCE;
    }


    public List<Loan> getLoans() {
        return loans;
    }
    public Optional<Loan> getLoanById(int id) {
        return loans.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }
    public Loan findLoanById(int id) {
        return loans.stream()
                .filter(l -> l.getId() == id)
                .findFirst().get();
    }
}
