package example.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        Message message = exchange.getIn();
        String payload = message.getBody(String.class);
        payload = payload.replace("\n", "").replace("\r", "");

        System.out.println("payload = " + payload);
        message.setBody(payload);
    }
}
