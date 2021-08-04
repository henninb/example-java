package example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.domain.Person;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelay = 500000)
    public void runMe() throws JsonProcessingException {

        String payload = "{\n" +
                "    \"first_name\": \"brian\",\n" +
                "    \"middle_name\": \"middle\",\n" +
                "    \"last_name\": \"henning\"\n" +
                "}";

        Person person = objectMapper.readValue(payload, Person.class);
//        Person person = new Person();
//        person.setFirstName("brian");
//        person.setMiddleName("z");
//        person.setLastName("Henning");
        System.out.println(person.getMiddleName());

        System.out.println(objectMapper.writeValueAsString(person));
    }
}
