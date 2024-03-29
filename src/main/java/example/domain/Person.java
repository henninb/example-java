package example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    Long id;
    @JsonProperty(value = "first_name")
    String firstName;
    @JsonProperty(value = "middle_name", access = JsonProperty.Access.WRITE_ONLY)
    //@JsonProperty(value = "middle_name")
    String middleName;
    @JsonProperty(value =  "last_name")
    String lastName;
}