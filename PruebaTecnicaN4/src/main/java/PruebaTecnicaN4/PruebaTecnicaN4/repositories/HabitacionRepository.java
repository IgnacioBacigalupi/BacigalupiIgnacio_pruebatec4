package PruebaTecnicaN4.PruebaTecnicaN4.repositories;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Habitacion;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {


    List<Habitacion> findByHotelAndTipoDeHabitacion(Hotel hotelReserva, String tipoDeHabitacion);

    @Query("SELECT p FROM Habitacion p , Hotel h WHERE p.disponibleDesde <= :fechaDesde and p.disponibleHasta >= :fechaHasta and :fechaDesde <= :fechaHasta and  h.ubicacion = :ubicacion and h.id = p.hotel.id ")
    List<Habitacion> encontrarHabitacionDisponibleAndUbicacion(@Param("fechaDesde") LocalDate disponibleDesde, @Param("fechaHasta") LocalDate disponibleHasta, @Param("ubicacion") String ubicacion);
}
