package ShoppingApp.ShoppingApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;      // 1 to 5
    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private LocalDateTime createdAt;

}
