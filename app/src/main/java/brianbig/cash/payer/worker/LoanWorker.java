package brianbig.cash.payer.worker;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.repo.AppRepository;

/**
 * @author Brian Barasa
 */
public interface LoanWorker {

    final AppRepository appRepo = AppRepository.getInstance();

    int process(Loan loan);
}
