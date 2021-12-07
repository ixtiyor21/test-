package set.up.model.auth;

import lombok.*;
import set.up.enums.Role;
import set.up.model.BaseId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"username"})
public class User extends BaseId {
    private String username;
    private String password;
    private Role role;
}
