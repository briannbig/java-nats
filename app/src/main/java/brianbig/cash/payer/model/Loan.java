package brianbig.cash.payer.model;

/**
 * @author Brian Barasa
 */
public class Loan {
    private int id;
    Customer customer;
    double amountLend;
    double amountRepaid;
    int status = LoanStatus.PENDING;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmountLend() {
        return amountLend;
    }

    public void setAmountLend(double amountLend) {
        this.amountLend = amountLend;
    }

    public double getAmountRepaid() {
        return amountRepaid;
    }

    public void setAmountRepaid(double amountRepaid) {
        this.amountRepaid = amountRepaid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", customer=" + customer.getId() + customer.getEmail() +
                ", amountLend=" + amountLend +
                ", amountRepaid=" + amountRepaid +
                ", status=" + status +
                '}';
    }
}
