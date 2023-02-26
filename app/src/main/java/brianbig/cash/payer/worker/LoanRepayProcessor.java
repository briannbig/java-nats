package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.model.LoanStatus;
import brianbig.cash.payer.queue.LoanRepayPublisher;

import java.util.Optional;

/**
 * @author Brian Barasa
 */
public class LoanRepayProcessor implements BaseLoanRepayProcessor{

    final LoanRepayPublisher publisher = new LoanRepayPublisher();
    @Override
    public int process(Loan loan) {
        Optional<Loan> optionalLoan = appRepo.getLoanById(loan.getId());
        if (optionalLoan.isEmpty()){
            System.out.println("Loan not found from db");
            return -1;
        }
        if (optionalLoan.get().getStatus() == LoanStatus.REPAID){
            System.out.println("Loan already repaid in full");
            return -1;
        }
        var loan_ = optionalLoan.get();
        double amount = loan_.getCustomer().getAccBalance();

        double balance = loan_.getAmountLend() - loan_.getAmountRepaid();
        System.out.println("Loan------> Lend amount: "+ loan_.getAmountLend() + " Balance: " + balance);
        if (amount <= 0) {
            System.out.println("Account Balance zero");
            publisher.publish(loan);
            return -1;
        }
        if (amount < balance){
            loan_.setAmountRepaid(loan_.getAmountRepaid() + amount);
            System.out.println("\tRepaid " + balance + "new Balance: " + (loan_.getAmountLend()-loan_.getAmountRepaid()));
            var customer = loan_.getCustomer();
            customer.deductAccountBalance(amount);
            appRepo.save(customer);
            appRepo.save(loan_);
            publisher.publish(loan);
            return -1;
        }
        if (amount >= balance){
            loan_.setAmountRepaid(loan.getAmountRepaid() + balance);
            System.out.println("\tRepaid " + balance + "new Balance: " + (loan_.getAmountLend()-loan_.getAmountRepaid()));
            System.out.println("\tNew Account Balance: " + loan_.getCustomer().getAccBalance());
            var customer = loan_.getCustomer();
            customer.deductAccountBalance(amount);
            appRepo.save(customer);
            appRepo.save(loan_);
            return 0;
        }
        return 0;
    }


}
