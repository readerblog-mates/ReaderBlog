package readerblog.mates.readerblog.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Entity
@Data
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic_name")
    private String patronymicName;

    @Column(name = "short_biography")
    private String shortBiography;

    @Column(name = "born_date")
    private Date bornDate;

    @Column(name = "rating")
    private Double rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
