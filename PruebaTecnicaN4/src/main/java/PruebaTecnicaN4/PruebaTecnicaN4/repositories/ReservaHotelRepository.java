package PruebaTecnicaN4.PruebaTecnicaN4.repositories;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaHotelRepository extends JpaRepository<ReservaHotel, Long> {
    List<ReservaHotel> findByHotel(Hotel hotel);
}
