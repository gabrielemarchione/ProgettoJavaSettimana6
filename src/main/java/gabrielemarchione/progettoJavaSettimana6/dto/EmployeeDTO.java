package gabrielemarchione.progettoJavaSettimana6.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeDTO(
        @NotEmpty(message = "Username è obbligatorio")
                          @Size(min = 4, max = 16, message = "Username deve contenere da 4 a 16 caratteri")
                          String username,
                          @NotEmpty(message = "Il nome è obbligatorio!")
                          @Size(min = 2, max = 20, message = "Il nome deve contentere dai 2 ai 20 caratteri")
                          String name,
                          @NotEmpty(message = "Il cognome è obbligatorio!")
                          @Size(min = 2, max = 20, message = "Il nome deve contentere dai 2 ai 20 caratteri")
                          String surname,
                          @NotEmpty(message = "L'email è obbligatoria!")
                          @Email(message = "L'email non è un indirizzo corretto!")
                          String email) {

}