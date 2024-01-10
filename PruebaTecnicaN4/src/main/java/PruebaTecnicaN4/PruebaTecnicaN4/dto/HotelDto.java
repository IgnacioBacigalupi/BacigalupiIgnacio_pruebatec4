package PruebaTecnicaN4.PruebaTecnicaN4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto {

    private String nombre;

    private String ubicacion;

    private List<HabitacionDto> habitaciones;


}
