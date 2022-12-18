package example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.domain.Person;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelay = 5000)
    public void runMe() throws JsonProcessingException {
        String payload = "{\"first_name\": \"brian\"," +
                "    \"middle_name\": \"z\"," +
                "    \"last_name\": \"henning\"" +
                "}";

        Person person = objectMapper.readValue(payload, Person.class);
        System.out.println("middleName: " + person.getMiddleName());
        System.out.println(objectMapper.writeValueAsString(person));
    }
}
