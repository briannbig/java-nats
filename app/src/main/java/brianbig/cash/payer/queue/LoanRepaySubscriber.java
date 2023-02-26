package brianbig.cash.payer.queue;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.worker.LoanRepayProcessor;
import io.nats.client.JetStreamApiException;

import java.io.IOException;

/**
 * @author Brian Barasa
 */
public class LoanRepaySubscriber extends Subscriber {

    final LoanRepayProcessor processor = new LoanRepayProcessor();

    final String subject = "loan.repay";

    public LoanRepaySubscriber() throws JetStreamApiException, IOException {
    }

    @Override
    public String subject() {
        return subject;
    }


    @Override
    public void process(Loan loan) {
        processor.process(loan);

    }

}
