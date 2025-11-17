package miturnero.backend.api;

import lombok.RequiredArgsConstructor;  // ← Agregado
import miturnero.backend.api.dto.PersonResponseDTO;
import miturnero.backend.person.Person;
import miturnero.backend.person.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/searchByDocumentNumber/{documentNumber}")
    public PersonResponseDTO searchByDocumentNumber(
            @PathVariable(name = "documentNumber") final String documentNumber) {


        if (!documentNumber.matches("\\d{10}")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Document number must be 10 digits"
            );
        }

        Person person = personRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Person with document number " + documentNumber + " not found"
                ));

        return new PersonResponseDTO(
                person.getDocumentNumber(),
                person.getName(),
                person.getLastName()
        );
    }
}