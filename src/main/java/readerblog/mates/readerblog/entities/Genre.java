package readerblog.mates.readerblog.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author @ivanleschinsky
 * @author mzheldin@yandex.ru
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres")
@EqualsAndHashCode(exclude = "books")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<Book> books;

}
