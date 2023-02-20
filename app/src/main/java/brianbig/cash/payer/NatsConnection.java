package brianbig.cash.payer;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Brian Barasa
 */
public class NatsConnection {
    static final String natsHostUrl = "127.0.0.1:4222";
    static Optional<Connection> optionalConnection = Optional.empty();

    public static Connection natsConnection() {
        if (optionalConnection.isEmpty()){
            try {
                optionalConnection = Optional.ofNullable(Nats.connect(natsHostUrl));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                optionalConnection = Optional.empty();
            }
        }
        return optionalConnection.get();
    }
}
