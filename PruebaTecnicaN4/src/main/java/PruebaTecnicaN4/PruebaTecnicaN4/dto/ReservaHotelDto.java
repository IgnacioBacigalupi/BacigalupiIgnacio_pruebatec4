package PruebaTecnicaN4.PruebaTecnicaN4.dto;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaHotelDto {

    private LocalDate fechaDesde;

    private LocalDate fechaHasta;

    private String ubicacion;

    private String codigoHotel;

    private int numeroHuespedes;

    private String tipoDeHabitacion;

    private Usuario huesped;


}
