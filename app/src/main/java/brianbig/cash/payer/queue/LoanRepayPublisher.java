package brianbig.cash.payer.queue;

import brianbig.cash.payer.DRY;
import brianbig.cash.payer.model.Loan;
import io.nats.client.JetStreamApiException;
import io.nats.client.impl.NatsMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * @author Brian Barasa
 */
public class LoanRepayPublisher implements Publisher<Loan> {
    @Override
    public void publish(Loan loan) {

        NatsMessage loanMsg =
                NatsMessage.builder()
                        .subject("loan.repay")
                        .data(DRY.LoanToJSon(loan), StandardCharsets.UTF_8)
                        .build();
        try {
            System.out.println("Publishing loan Repayment..... "+DRY.LoanToJSon(loan));
            natsConnection.publish(loanMsg);

        } catch (IOException | JetStreamApiException e) {
            System.out.println("--------<Error>-------");
            e.printStackTrace();
        }
    }
}
