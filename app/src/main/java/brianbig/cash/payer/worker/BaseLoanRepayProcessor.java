package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.repo.AppRepository;

/**
 * @author Brian Barasa
 */
public interface BaseLoanRepayProcessor {
    AppRepository appRepo = AppRepository.getInstance();
    int process(Loan loan, double amount);
}
