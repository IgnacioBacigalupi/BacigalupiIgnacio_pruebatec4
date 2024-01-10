package PruebaTecnicaN4.PruebaTecnicaN4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HabitacionDto {

    private String tipoDeHabitacion;

    private Double precio;

    private LocalDate disponibleDesde;

    private LocalDate disponibleHasta;

    private HotelDto hotel;


}
