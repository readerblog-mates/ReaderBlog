package readerblog.mates.readerblog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

/**
 * @author Sergey Petukhov
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = -7483309127820484301L;

    /* TODO почему поле называется role_id если в базе у нас id
       TODO зачем нам здесь @OneToOne(optional = false, mappedBy = "role")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne(optional = false, mappedBy = "role")
    private Long role_id;
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
