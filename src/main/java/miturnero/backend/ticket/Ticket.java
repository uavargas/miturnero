package miturnero.backend.ticket;


import jakarta.persistence.*;
import lombok.*;
import miturnero.backend.person.Person;

import java.time.Instant;


@Entity
@Table(name = "ticket", indexes = {@Index(columnList = "status"),@Index(columnList = "calledAt")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private Instant createdAt;
    private Instant calledAt;
    private Instant servedAt;
    private Integer moduleNumber;

    @ManyToOne //Relacion con la persona
    private Person person;


}
