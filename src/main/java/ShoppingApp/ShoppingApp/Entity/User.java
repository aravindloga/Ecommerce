package ShoppingApp.ShoppingApp.Entity;


import ShoppingApp.ShoppingApp.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(nullable = false)
    private String userName;   // email

    @Column(nullable = false)
    private String password;

    private LocalDate createdAt = LocalDate.now();

    private Role role = Role.USER;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;



}
