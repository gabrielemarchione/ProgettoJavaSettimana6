package gabrielemarchione.progettoJavaSettimana6.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TripDTO(
        @NotEmpty(message = "La destinazione è obbligatoria!")
        @Size(min = 2, max = 30, message = "La destinazione deve contenere tra 2 e 30 caratteri.")
        String destination,

        @NotNull(message = "La data di andata è obbligatoria!")
        @Future(message = "La data di andata deve essere successiva rispetto a quella attuale.")
        LocalDate data_andata,

        @NotNull(message = "La data di ritorno è obbligatoria!")
        @Future(message = "La data di ritorno deve essere successiva rispetto a quella attuale.")
        LocalDate data_ritorno)
       {

}
