package PruebaTecnicaN4.PruebaTecnicaN4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaVueloDto {

    private LocalDate fecha;

    private String origen;

    private String destino;

    private String codigoVuelo;

    private String tipoDeAsiento;

    private int peopleQ;

    private String[] pasajeros;

    private boolean reservado;

}
