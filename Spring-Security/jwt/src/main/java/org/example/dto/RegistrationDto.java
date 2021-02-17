package org.example.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("firstname")
    @NotNull
    private String firstName;

    @JsonProperty("lastname")
    @NotNull
    private String lastName;

}
