package PruebaTecnicaN4.PruebaTecnicaN4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ReservaVuelo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private LocalDate fecha;

    private String origen;

    private String destino;

    private String codigoVuelo;

    private Integer peopleQ;

    private String tipoDeAsiento;

    private Double precio;

    private boolean reservado;


    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;
}
