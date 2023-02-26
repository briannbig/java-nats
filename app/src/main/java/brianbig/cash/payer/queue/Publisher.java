package brianbig.cash.payer.queue;

import brianbig.cash.payer.NatsConnection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;

import java.io.IOException;

/**
 * @author Brian Barasa
 */
public interface Publisher<T>  {
    JetStream natsConnection = NatsConnection.getInstance().getJetStream();

    void publish(T model) throws JetStreamApiException, IOException;
}
