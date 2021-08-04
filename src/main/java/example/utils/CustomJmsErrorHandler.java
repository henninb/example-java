package example.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
@Slf4j
public class CustomJmsErrorHandler implements ErrorHandler {
    public void handleError(Throwable t) {
        System.out.println("Runtime JMS error:" + t.getMessage());
    }
}
