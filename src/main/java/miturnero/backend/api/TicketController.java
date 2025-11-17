package miturnero.backend.api;

import lombok.RequiredArgsConstructor;
import miturnero.backend.api.dto.PersonResponseDTO;
import miturnero.backend.api.dto.TicketResponseDTO;
import miturnero.backend.person.Person;
import miturnero.backend.person.PersonRepository;
import miturnero.backend.ticket.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    //Inyeccion de dependencias
    private final TicketService ticketService;
    private final PersonRepository personRepository;

    /**
     * Creates a new ticket for a person. If the person doesn't exist, creates a new person record first.
     * 
     * @param personResponseDTO DTO containing person information (documentNumber, name, lastName)
     * @return TicketResponseDTO containing the created ticket details
     * 
     * @apiNote This endpoint expects a JSON body with the following structure:
     * {
     *   "documentNumber": "string",
     *   "name": "string",
     *   "lastName": "string"
     * }
     * 
     * @example
     * Request: POST /api/tickets
     * Body: {"documentNumber": "12345678", "name": "John", "lastName": "Vargas"}
     * 
     * Response: {
     *   "id": 1,
     *   "code": "A10",
     *   "status": "CREATED",
     *   "createdAt": "2025-11-17T21:50:00Z",
     *   "calledAt": null,
     *   "servedAt": null,
     *   "moduleNumber": null,
     *   "fullName": "John Doe"
     * }
     */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TicketResponseDTO createTicket(@RequestBody final PersonResponseDTO personResponseDTO) {
        // Check if person exists, if not create a new one
        Person person = personRepository.findByDocumentNumber(personResponseDTO.documentNumber())
            .orElseGet(() -> {
                Person newPerson = new Person();
                newPerson.setDocumentNumber(personResponseDTO.documentNumber());
                newPerson.setName(personResponseDTO.name());
                newPerson.setLastName(personResponseDTO.lastName());
                return personRepository.save(newPerson);
            });
            
        return ticketService.createTicket(person);
    }


}
