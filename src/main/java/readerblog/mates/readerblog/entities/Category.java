package readerblog.mates.readerblog.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author @ivanleschinsky
 * @author mzheldin@yandex.ru
 * @author @tetyaezhik
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@EqualsAndHashCode(exclude = "books")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_books",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
