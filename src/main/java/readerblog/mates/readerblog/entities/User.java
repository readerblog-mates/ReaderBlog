package readerblog.mates.readerblog.entities;

/**
 * @author Sergey Petukhov
 */

import readerblog.mates.readerblog.enums.StatusOfUser;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nick_name", "email"})}
)
public class User {
    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String first_name;
    private String last_name;
    private String nick_name;
    private String password;
    private String email;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    private StatusOfUser status;

    // получение информации о пользователе по nick
    public Map<String, String> getUserInfo(){
        Map<String, String> info = new HashMap<>();
        info.put("first_name",first_name);
        info.put("last_name",last_name);
        info.put("nick_name",nick_name);
        info.put("email",email);
        info.put("role",StatusOfUser.valueOf("name").toString());
        return info;
    }

// методы изменения статуса, изменения роли, никнейма и пароля
// - это обычные сеттеры, т.к. у нас подключен Lombok, не объевлены в классе

}

