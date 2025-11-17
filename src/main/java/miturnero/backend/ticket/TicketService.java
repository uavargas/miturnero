package miturnero.backend.ticket;



import lombok.RequiredArgsConstructor;
import miturnero.backend.api.dto.TicketResponseDTO;
import miturnero.backend.person.Person;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final AtomicInteger counter = new AtomicInteger(10);

    public TicketResponseDTO createTicket(final Person person) {
        char prefix = 'A';

        while(true){
            String code = prefix + String.valueOf(counter.getAndIncrement());
            try {
                Ticket ticket = Ticket.builder()
                        .code(code)
                        .status(TicketStatus.CREATED)
                        .createdAt(Instant.now())
                        .person(person)
                        .build();
                return buildTicketResponseDTO(ticketRepository.save(ticket));
        }catch (DataIntegrityViolationException e) {
                // retry  with a different code
            }
        }
    }
    private TicketResponseDTO buildTicketResponseDTO(Ticket ticket) {
        return  new TicketResponseDTO(
                ticket.getId(),
                ticket.getCode(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getCalledAt(),
                ticket.getServedAt(),
                ticket.getModuleNumber(),
                ticket.getPerson().getName() + " " + ticket.getPerson().getLastName()
        );
    }
}
