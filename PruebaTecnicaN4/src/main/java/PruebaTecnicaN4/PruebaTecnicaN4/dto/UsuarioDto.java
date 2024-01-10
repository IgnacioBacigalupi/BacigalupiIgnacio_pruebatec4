package PruebaTecnicaN4.PruebaTecnicaN4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDto {

    private String nombre;

    private String apellido;

    private String correo;

    private String pasaporte;

    private int edad;


}
