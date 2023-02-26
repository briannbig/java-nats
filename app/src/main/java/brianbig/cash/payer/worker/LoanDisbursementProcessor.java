package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.model.LoanStatus;

import java.util.Optional;

/**
 * @author Brian Barasa
 */
public class LoanDisbursementProcessor implements LoanWorker{
    @Override
    public int process(Loan loan) {
        Optional<Loan> optionalLoan = appRepo.getLoanById(loan.getId());
        if (optionalLoan.isEmpty()){
            System.out.println("Loan not found from db");
            return -1;
        }
        var applicant = loan.getCustomer();
        var loans = applicant.getActiveLoans();
        if (loans.size() > 2){
            System.out.println("Customer already has more than two active loans");
            return -1;
        }
        try {
            System.out.println("Disbursing...");
            Thread.sleep(3000);
            var loan_ = optionalLoan.get();
            loan_.setStatus(LoanStatus.DISBURSED);
            appRepo.save(loan_);
            System.out.println("Loan: " + loan_ + "disbursement complete");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
