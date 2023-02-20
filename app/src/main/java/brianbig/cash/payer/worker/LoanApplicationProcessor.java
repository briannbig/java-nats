package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.model.LoanStatus;

/**
 * @author Brian Barasa
 */
public class LoanApplicationProcessor implements LoanWorker{
    @Override
    public int process(Loan loan) {
        if (appRepo.getLoanById(loan.getId()).isPresent()){
            System.out.println("Application for loan with similar ID exists");
            return -1;
        }

        var applicant = loan.getCustomer();
        var loans = applicant.getActiveLoans();

        if (loans.size() > 2){
            System.out.println("Customer already has more than two active loans");
            return -1;
        }
        loan.setStatus(LoanStatus.PENDING);
        appRepo.getLoans().add(loan);
        return 0;
    }
}
