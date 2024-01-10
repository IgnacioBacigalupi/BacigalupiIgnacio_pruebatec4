package PruebaTecnicaN4.PruebaTecnicaN4.repositories;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Hotel findByCodigoHotel(String codigoHotel);


}
