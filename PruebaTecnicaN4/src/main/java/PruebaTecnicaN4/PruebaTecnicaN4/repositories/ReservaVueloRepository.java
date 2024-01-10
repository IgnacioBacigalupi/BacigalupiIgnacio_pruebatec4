package PruebaTecnicaN4.PruebaTecnicaN4.repositories;


import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaVuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReservaVueloRepository  extends JpaRepository<ReservaVuelo , Long> {

    @Query("SELECT r FROM ReservaVuelo r WHERE r.vuelo = :vuelo")
    List<ReservaVuelo> findReservasByVuelo(@Param("vuelo") Vuelo vuelo);
}
