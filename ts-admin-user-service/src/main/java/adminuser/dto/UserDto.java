package adminuser.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String userName;

    private String password;

    private int gender;

    private int documentType;

    private String documentNum;

    private String email;
}
