package example.route;

import example.processor.MessageProcessor;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TcpRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TcpRoute.class);
    final MessageProcessor messageProcessor;

    @Autowired
    public TcpRoute(MessageProcessor messageProcessor) {
        logger.info("setup");
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void configure() {
        from("netty4:tcp://0.0.0.0:8081?textline=true?maximumPoolSize=5")
          .routeId("nettyServer")

          .log(LoggingLevel.INFO, "message consumed.")
          .choice()
          .when().xpath("/one")
            .log(LoggingLevel.INFO, " message consumed.")
            .to("seda:ONE_QUEUE")
          .when().xpath("/two")
            .log(LoggingLevel.INFO, " message consumed.")
            .to("seda:TWO_QUEUE")
          .when().xpath("/heartbeat")
            .log(LoggingLevel.INFO, "heartbeat message consumed.")
            .to("seda:HEARTBEAT_QUEUE")
          .otherwise()
            .log(LoggingLevel.ERROR, "should never get here.")
          .endChoice()
        .end();

        from("seda:ONE_QUEUE?size=1000&concurrentConsumers=5&blockWhenFull=true")
                .process(messageProcessor)
                .log(LoggingLevel.INFO, " message sent.")
                .to("netty4:tcp://localhost:8082?sync=true")
        .end();

        from("seda:TWO_QUEUE?size=1000&concurrentConsumers=1&blockWhenFull=true")
                .log(LoggingLevel.INFO, " message sent.")
                .to("netty4:tcp://localhost:8082?sync=true")
        .end();

        //client route

        from("netty4:tcp://0.0.0.0:8082")
          .log(LoggingLevel.INFO, "message received.")
        .end();
    }

    //ConnectTimeoutException: connection timed out: localhost/127.0.0.1:8082
    //java.io.IOException: Connection reset by peer
}
