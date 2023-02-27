package brianbig.cash.payer.queue;

import brianbig.cash.payer.DRY;
import brianbig.cash.payer.NatsConnection;
import brianbig.cash.payer.model.Loan;
import io.nats.client.*;

import java.io.IOException;

/**
 * @author Brian Barasa
 */
public abstract class JetStreamConsumer implements MessageHandler {
    final JetStream js = NatsConnection.getInstance().getJetStream();
    final Connection nc = NatsConnection.getInstance().getNC();
    JetStreamSubscription subscription;
    Dispatcher dispatcher;

    public JetStreamConsumer() throws JetStreamApiException, IOException {
        dispatcher = nc.createDispatcher(this);
        dispatcher.subscribe(subject());
        subscription = js.subscribe(subject(), dispatcher, this, false);
    }

    public abstract String subject();

    @Override
    public void onMessage(Message msg) {
        onMessageReceived(msg);
    }

    public void onMessageReceived(Message msg) {
        try {
            var loan = DRY.MAPPER.readValue(msg.getData(), Loan.class);
            System.out.println("*********** Received Payload: "+loan.toString());
            process(loan);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public abstract void process(Loan loan);


}
