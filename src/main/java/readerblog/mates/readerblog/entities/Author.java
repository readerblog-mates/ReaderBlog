package readerblog.mates.readerblog.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * @author mzheldin@yandex.ru
 * @author @tetyaezhik
 */


@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"books", "rating", "shortBiography"})
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

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "short_biography")
    private String shortBiography;

    @Column(name = "born_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bornDate;

    @Column(name = "rating")
    private Double rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
