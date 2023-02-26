package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.model.LoanStatus;
import brianbig.cash.payer.queue.LoanApplicationPublisher;

/**
 * @author Brian Barasa
 */
public class LoanApplicationProcessor implements LoanWorker{

    private final LoanDisbursementProcessor disbursementProcessor = new LoanDisbursementProcessor();
    final LoanApplicationPublisher publisher = new LoanApplicationPublisher();
    @Override
    public int process(Loan loan) {
        if (appRepo.getLoanById(loan.getId()).isPresent()){
            System.out.println("Application for loan with similar id: " +  loan.getId() + " exists");
            return -1;
        }
        if (disbursementProcessor.process(loan) != 0)
            return -1;

        var applicant = loan.getCustomer();
        var loans = applicant.getActiveLoans();

        if (loans.size() > 2){
            System.out.println("Customer already has more than two active loans");
            publisher.publish(loan);
            return -1;
        }
        loan.setStatus(LoanStatus.PENDING);
        appRepo.save(loan);
        return 0;
    }
}
