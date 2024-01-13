package PruebaTecnicaN4.PruebaTecnicaN4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nombre;

    private String apellido;

    private String correo;

    private String pasaporte;

    private int edad;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ReservaHotel> hoteles = new ArrayList<>();

}
