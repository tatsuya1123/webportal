package jp.ac.hcs.s3a127.user;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserForm {

    @NotBlank (message = "{require_check}")
    @Email (message = "{email_check}")
    private String user_id;

    @NotBlank (message = "{require_check}")
    @Length(min = 4, max = 100, message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "{pattern_check}")
    private String password;

    @NotBlank (message = "{require_check}")
    private String user_name;

    //@NotBlank (message = "{require_check}")
    private boolean darkmode;

    @NotBlank (message = "{require_check}")
    private String role;

    private boolean enabled;
}
