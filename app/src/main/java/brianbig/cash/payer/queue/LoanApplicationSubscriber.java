package brianbig.cash.payer.queue;

import brianbig.cash.payer.model.Loan;
import brianbig.cash.payer.worker.LoanApplicationProcessor;
import io.nats.client.JetStreamApiException;

import java.io.IOException;

/**
 * @author Brian Barasa
 */
public class LoanApplicationSubscriber extends Subscriber{

    final LoanApplicationProcessor processor = new LoanApplicationProcessor();



    final String subject = "loan.apply";

    public LoanApplicationSubscriber() throws JetStreamApiException, IOException {
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
