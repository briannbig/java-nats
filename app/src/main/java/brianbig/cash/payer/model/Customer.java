package brianbig.cash.payer.model;

import brianbig.cash.payer.repo.AppRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brian Barasa
 */
public class Customer {

    private int id;
    private String email;
    private double accBalance = 0.0;

    public void setId(int id) {
        this.id = id;
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


    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }

    public double deductAccountBalance(double amount) {
        if (amount <= 0){
            System.out.println("Cant deduct amount less than 1");
            return -1;
        }
        return accBalance -= amount;

    }

    public double topUpBalance(double amount){
        if (amount <= 0){
            System.out.println("Cant top up amount less than 1");
            return -1;
        }
        return accBalance += amount;
    }


    public List<Loan> fetchLoans() {
        return AppRepository.getInstance().getLoans()
                .stream().filter(l -> l.getCustomer().getId() == id)
                .toList();
    }

    public List<Loan> getActiveLoans() {
        return AppRepository.getInstance().getLoans().stream()
                .filter(l -> l.status != LoanStatus.REPAID &&
                                (l.status == LoanStatus.DISBURSED || l.status == LoanStatus.PAYING)
                ).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id= '" + id +
                "' email='" + email + '\'' + '}';
    }
}
