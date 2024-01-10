package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.VueloDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Vuelo;

import java.time.LocalDate;
import java.util.List;

public interface IVueloService {

    Vuelo crearVuelo(VueloDto vueloDto);

    List<Vuelo> obtenerTodosLosVuelosPorFechaYLugar(LocalDate disponibleDesde, LocalDate disponiblehasta, String origen, String destino);

    List<Vuelo> obtenerTodosLosVuelos();

    Vuelo borrarVueloPorId(long id);

    Vuelo obtenerVueloPorId(Long id);

    Vuelo modificarVuelo(Long id, VueloDto vueloDto);


}
