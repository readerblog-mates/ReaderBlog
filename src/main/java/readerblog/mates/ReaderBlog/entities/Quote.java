package readerblog.mates.readerblog.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import readerblog.mates.readerblog.enums.StatusOfQueryOrFeedback;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author haroldEkb@mail.ru
 */

@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"book", "user"})
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusOfQueryOrFeedback status;

    @Column(name = "rating")
    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 10, message = "Rating should not be greater than 10")
    private Double rating;

}