package PruebaTecnicaN4.PruebaTecnicaN4.repositories;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    @Query("SELECT DISTINCT vue" +
            " from Vuelo vue" +
            " where vue.fecha between :fecha1 and :fecha2 and vue.origen = :origen and vue.destino = :destino")
    List<Vuelo> obtenerTodosLosVuelosPorFecha(LocalDate fecha1, LocalDate fecha2, String origen, String destino);

    Vuelo findByNumeroVuelo(String codigoVuelo);


}
