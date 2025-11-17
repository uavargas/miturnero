package miturnero.backend.ticket;

/**
 * Enumerates the possible states of a ticket in the system.
 * 
 * <p>This enum represents the lifecycle of a ticket from creation to completion.</p>
 * 
 * @author Your Name
 * @version 1.0
 * @since 2025-11-17
 */
public enum TicketStatus {
    /**
     * The ticket has been created but not yet called.
     * This is the initial state of a ticket.
     */
    CREATED,
    
    /**
     * The ticket has been called to be served.
     * This state indicates that the ticket is next in line for service.
     */
    CALLED,
    
    /**
     * The ticket has been served.
     * This is a terminal state indicating successful completion.
     */
    SERVED,
    
    /**
     * The ticket has been cancelled.
     * This is a terminal state indicating the ticket will not be served.
     */
    CANCELLED
}
