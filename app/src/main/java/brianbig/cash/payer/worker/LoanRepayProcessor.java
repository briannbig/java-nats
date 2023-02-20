package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.model.LoanStatus;

import java.util.Optional;

/**
 * @author Brian Barasa
 */
public class LoanRepayProcessor implements BaseLoanRepayProcessor{
    @Override
    public int process(Loan loan, double amount) {
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
        double balance = loan_.getAmountLend() - loan_.getAmountRepaid();
        System.out.println("Loan------> Lend amount: "+ loan_.getAmountLend() + " Balance: " + balance);
        if (amount < balance){
            loan_.setAmountRepaid(loan_.getAmountRepaid() + amount);
            System.out.println("\tRepaid " + amount + "new Balance: " + (loan_.getAmountLend()-loan_.getAmountRepaid()));
            appRepo.getLoans().remove(optionalLoan.get());
            appRepo.getLoans().add(loan_);
            // TODO: 20/02/2023 push back to queue
            return 1;
        }
        if (amount >= balance){
            loan_.setAmountRepaid(loan.getAmountRepaid() + balance);
            System.out.println("\tRepaid " + amount + "new Balance: " + (loan_.getAmountLend()-loan_.getAmountRepaid()));
            appRepo.getLoans().remove(optionalLoan.get());
            appRepo.getLoans().add(loan_);
            return 0;
        }
        return 0;
    }


}
