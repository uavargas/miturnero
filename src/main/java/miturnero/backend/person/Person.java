package miturnero.backend.person;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person", indexes = {@Index(columnList = "documentNumber", unique = true)})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false, unique = true)
    private String documentNumber;
    private String name;
    private String lastName;


}
