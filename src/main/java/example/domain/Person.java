package example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @JsonProperty(value = "first_name")
    String firstName;
    @JsonProperty(value = "middle_name", access = JsonProperty.Access.WRITE_ONLY)
    //@JsonProperty(value = "middle_name")
    String middleName;
    @JsonProperty(value =  "last_name")
    String lastName;
}