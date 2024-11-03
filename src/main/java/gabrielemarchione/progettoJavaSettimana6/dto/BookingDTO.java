package gabrielemarchione.progettoJavaSettimana6.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BookingDTO(@Size(max = 100, message = "Le note possono contenere massimo 100 caratteri.")
                          String preferences,
                         @NotNull(message = "L'UUID è obbligatorio!")
                          @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
                                  message = "L'UUID fornito non è valido.")
                          String trip_id)
{
}
