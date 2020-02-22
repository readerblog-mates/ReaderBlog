package readerblog.mates.readerblog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Petukhov
 * @author @mikovic5 
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nick_name", "email"})}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nick_name", nullable = false)
    private String nickName;
    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "email_verified")
    private Boolean emailVerified = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    private String imageUrl;


    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    /* TODO автор точно уверен в этой конструкции? */
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    /*TODO поля нет в БД так и должно быть?*/
//    private StatusOfUser status;

    /**
     * @return - information about user by nick
     */
    public Map<String, String> getUserInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("first_name", firstName);
        info.put("last_name", lastName);
        info.put("nick_name", nickName);
        info.put("email", email);
        /*info.put("role", StatusOfUser.valueOf("name").toString());*/
        return info;
    }

// методы изменения статуса, изменения роли, никнейма и пароля

}
