package brianbig.cash.payer.model;

import brianbig.cash.payer.repo.AppRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Barasa
 */
public class Customer {

    private static int id;
    private String email;
    private List<Loan> loans;

    public Customer() {
        id += id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Loan> getLoans() {
        return AppRepository.getInstance().getLoans()
                .stream().filter(l -> l.getCustomer().getId() == id)
                .toList();
    }

    public List<Loan> getActiveLoans() {
        return loans.stream()
                .filter(l -> l.status != LoanStatus.REPAID &&
                                (l.status == LoanStatus.DISBURSED || l.status == LoanStatus.PAYING)
                ).collect(Collectors.toList());
    }

    public void fetchLoans() {
        loans = AppRepository.getInstance().getLoans()
                .stream().filter(l -> l.getCustomer().getId() == id)
                .toList();
    }
}
