package PruebaTecnicaN4.PruebaTecnicaN4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"reservaHoteles", "Habitaciones"})
public class Hotel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String codigoHotel;

    private String nombre;

    private String ubicacion;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("hotel")
    private List<ReservaHotel> reservaHoteles = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("hotel")
    private List<Habitacion> habitaciones = new ArrayList<>();
}
