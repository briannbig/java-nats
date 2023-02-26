package brianbig.cash.payer;

import io.nats.client.*;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import io.nats.client.support.JsonUtils;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Brian Barasa
 */
public class NatsConnection {

    private static NatsConnection INSTANCE = null;
    static final String natsHostUrl = "127.0.0.1:4222";
    static Optional<Connection> optionalConnection = Optional.empty();
    static JetStreamManagement jetStreamManagement;
    static JetStream jetStream;
    private NatsConnection() {}

    public static NatsConnection getInstance() {
        if (INSTANCE == null){
            if (optionalConnection.isEmpty()){
                System.out.println("-------> Connecting to nats Server <------");
                try{
                    optionalConnection = Optional.ofNullable(Nats.connect(natsHostUrl));
                    jetStreamManagement =optionalConnection.get().jetStreamManagement();
                    System.out.println("=============> Connected server info <=============");

                    StreamConfiguration streamConfig = StreamConfiguration.builder()
                            .name("cash-payer")
                            .subjects("loan.repay","loan.apply")
                            .storageType(StorageType.Memory)
                            .build();
                    StreamInfo streamInfo = jetStreamManagement.addStream(streamConfig);
                    JsonUtils.printFormatted(streamInfo);
                    jetStream = optionalConnection.get().jetStream();
                    System.out.println("===================================================");

                } catch (IOException | InterruptedException | JetStreamApiException e) {
                    System.out.println("************Connection Error**********");
                    e.printStackTrace();
                }
            }
            INSTANCE = new NatsConnection();
        }
        return INSTANCE;
    }
    public JetStream getJetStream(){
        return jetStream;
    }

    public Connection getNC() {
        return optionalConnection.get();
    }
}
