package PruebaTecnicaN4.PruebaTecnicaN4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"reservaVuelos"})
public class Vuelo {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String numeroVuelo;

    private String origen;

    private String destino;

    private String tipoDeAsiento;

    private double precioVuelo;

    private LocalDate fecha;

    private boolean estado;


    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL)
    private List<ReservaVuelo> reservaVuelos = new ArrayList<>();
}
