package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.ReservaVueloDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaVuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Vuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.ReservaVueloRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaVueloService implements IReservaVueloService {

    @Autowired
    private ReservaVueloRepository reservaVueloRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private VueloService vueloService;


    public ReservaVuelo crearReservaVuelo(ReservaVueloDto reservaVueloDto) {
        ReservaVuelo reservaVuelo = new ReservaVuelo();


        //Comprobamos que el vuelo existe
        Vuelo vueloExiste = vueloRepository.findByNumeroVuelo(reservaVueloDto.getCodigoVuelo());
        if (vueloExiste == null) {

            return null;
        }

        // Verificamos si el vuelo ya está reservado
        List<ReservaVuelo> reservasExistentes = reservaVueloRepository.findReservasByVuelo(vueloExiste);
        if (!reservasExistentes.isEmpty()) {

            return null;
        }


        //Verificamos la fecha
        LocalDate fechaActual = LocalDate.now();

        if (reservaVueloDto.getFecha().isAfter(fechaActual) || reservaVueloDto.getFecha().isEqual(fechaActual)) {
            //La fecha de reserva es valida

            reservaVuelo.setCodigoVuelo(vueloExiste.getNumeroVuelo());
            reservaVuelo.setOrigen(vueloExiste.getOrigen());
            reservaVuelo.setDestino(vueloExiste.getDestino());
            reservaVuelo.setPeopleQ(reservaVueloDto.getPeopleQ());
            reservaVuelo.setTipoDeAsiento(reservaVueloDto.getTipoDeAsiento());
            reservaVuelo.setFecha(reservaVueloDto.getFecha());
            reservaVuelo.setPrecio(vueloExiste.getPrecioVuelo() * reservaVueloDto.getPeopleQ());
            reservaVuelo.setVuelo(vueloExiste);
            reservaVuelo.setReservado(true);


            vueloService.actualizarEstadoVuelo(vueloExiste);
            return reservaVueloRepository.save(reservaVuelo);
        } else {

            return null;
        }

    }


}
