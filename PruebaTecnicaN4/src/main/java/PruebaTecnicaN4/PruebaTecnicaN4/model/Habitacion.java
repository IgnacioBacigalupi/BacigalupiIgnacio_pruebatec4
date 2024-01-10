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
public class Habitacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String tipoDeHabitacion;

    private Double precio;

    private LocalDate disponibleDesde;

    private LocalDate disponibleHasta;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
