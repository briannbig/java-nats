package brianbig.cash.payer.queue;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.worker.LoanRepayProcessor;
import io.nats.client.JetStreamApiException;

import java.io.IOException;

/**
 * @author Brian Barasa
 */
public class LoanRepayJetStreamConsumer extends JetStreamConsumer {

    final LoanRepayProcessor processor = new LoanRepayProcessor();

    final String subject = "loan.repay";

    public LoanRepayJetStreamConsumer() throws JetStreamApiException, IOException {
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
