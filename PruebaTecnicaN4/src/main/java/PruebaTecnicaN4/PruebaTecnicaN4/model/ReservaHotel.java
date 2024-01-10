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
public class ReservaHotel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String codigoHotel;

    private LocalDate fechaDesde;

    private LocalDate fechaHasta;

    private int noches;

    private int numeroHuespedes;

    private double precio;

    private String tipoDeHabitacion;


    @ManyToOne
    @JoinColumn(name = "userId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

}
