package miturnero.backend.api.dto;

import miturnero.backend.ticket.TicketStatus;

import java.time.Instant;

public record TicketResponseDTO(Long id,
                                String code,
                                TicketStatus status,
                                Instant createdAt,
                                Instant calledAt,
                                Instant servedAt,
                                Integer moduleNumber,
                                String fullName) {
}
