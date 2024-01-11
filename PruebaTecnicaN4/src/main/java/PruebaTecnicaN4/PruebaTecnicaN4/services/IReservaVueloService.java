package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.ReservaVueloDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaVuelo;



public interface IReservaVueloService {

    ReservaVuelo crearReservaVuelo(ReservaVueloDto vueloDto);

    ReservaVuelo eliminarVueloReservaPorId(Long id);

}
