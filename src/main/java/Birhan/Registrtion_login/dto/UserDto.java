package Birhan.Registrtion_login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private Long id;

    @NotEmpty
    private String FirstName;

    @NotEmpty
    private String LateName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    private String password;


}
