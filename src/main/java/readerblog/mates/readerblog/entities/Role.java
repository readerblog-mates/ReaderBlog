package readerblog.mates.readerblog.entities;

/**
 * @author Sergey Petukhov
 */

import javax.persistence.*;

import java.io.Serializable;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @OneToOne(optional = false, mappedBy="role")
    private Long role_id;

    String name;

    public Role(){}
}
