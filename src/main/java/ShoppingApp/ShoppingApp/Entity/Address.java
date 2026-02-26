package ShoppingApp.ShoppingApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Home / Office
    private String fullName;
    private String phone;
    private String pincode;
    private String city;
    private String state;
    private String street;
    private boolean isDefault;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
