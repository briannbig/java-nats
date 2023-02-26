package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Customer;
import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.queue.LoanRepayPublisher;
import brianbig.cash.payer.repo.AppRepository;

/**
 * @author Brian Barasa
 */
public class TopUpProcessor {

    private final AppRepository appRepository = AppRepository.getInstance();
    LoanRepayPublisher repayPublisher = new LoanRepayPublisher();


    public int topUpAmount(Customer customer, double amount) {
        if (customer.topUpBalance(amount) <= 0) {
            return -1;
        }
        appRepository.save(customer);
        System.out.println("Top up success! repaying loans...");
        System.out.println("\t active loans: " + customer.getActiveLoans().size());
        for (Loan loan:
                customer.getActiveLoans()) {
            repayPublisher.publish(loan);
        }
        return 0;
    }
}
