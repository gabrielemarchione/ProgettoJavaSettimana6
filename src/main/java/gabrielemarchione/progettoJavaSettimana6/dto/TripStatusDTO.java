package gabrielemarchione.progettoJavaSettimana6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TripStatusDTO ( @NotBlank(message = "Il campo 'Trip_Status' non può essere vuoto.")
                             @Pattern(regexp = "IN_PROGRAMMA | COMPLETATO",
                                     message = "Il valore di 'Trip_Status' deve essere 'IN_PROGRAMMA' o 'COMPLETATO'.")
                             String Trip_Status)
{
}
