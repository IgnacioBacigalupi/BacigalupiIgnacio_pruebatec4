package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.VueloDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaVuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Vuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.ReservaVueloRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class VueloService implements IVueloService {

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private ReservaVueloRepository reservaVueloRepository;

    private Random rand = new Random();

    @Override
    public Vuelo crearVuelo(VueloDto vueloDto) {
        LocalDate fechaActual = LocalDate.now();
        if (vueloDto.getFecha().isAfter(fechaActual) || vueloDto.getFecha().isEqual(fechaActual)) {


            Vuelo vuelo = new Vuelo();

            vuelo.setOrigen(vueloDto.getOrigen());
            vuelo.setDestino(vueloDto.getDestino());

            String letrasOrigen = vuelo.getOrigen().isEmpty() ? "00" : vuelo.getOrigen().substring(0, Math.min(2, vuelo.getOrigen().length()));
            String letrasDestino = vuelo.getDestino().isEmpty() ? "DD" : vuelo.getDestino().substring(0, Math.min(2, vuelo.getDestino().length()));


            String numeroFormateado = String.format("%04d", rand.nextInt(10000));

            String codigoVuelo = letrasOrigen + letrasDestino + "-" + numeroFormateado;
            vuelo.setNumeroVuelo(codigoVuelo.toUpperCase());
            vuelo.setTipoDeAsiento(vueloDto.getTipoDeAsiento());
            vuelo.setPrecioVuelo(vueloDto.getPrecioVuelo());

            vuelo.setFecha(vueloDto.getFecha());
            vuelo.setEstado(false);

            return vueloRepository.save(vuelo);
        } else {

            return null;
        }


    }

    public void actualizarEstadoVuelo(Vuelo vuelo) {
        vuelo.setEstado(true);
        vueloRepository.save(vuelo);
    }

    @Override
    public List<Vuelo> obtenerTodosLosVuelosPorFechaYLugar(LocalDate disponibleDesde, LocalDate disponiblehasta, String origen, String destino) {
        return vueloRepository.obtenerTodosLosVuelosPorFecha(disponibleDesde, disponiblehasta, origen, destino);
    }

    @Override
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepository.findAll();
    }


    @Override
    public Vuelo borrarVueloPorId(long id) {
        Optional<Vuelo> optionalVuelo = vueloRepository.findById(id);
        if (optionalVuelo.isEmpty()) {

            return null;
        }
        Vuelo vuelo = optionalVuelo.get();

        List<ReservaVuelo> reservasVuelo = reservaVueloRepository.findReservasByVuelo(vuelo);
        if (!reservasVuelo.isEmpty()) {
            // Verificar si hay reservas asociadas a este vuelo
            boolean hayReservasActivas = reservasVuelo.stream().anyMatch(ReservaVuelo::isReservado);
            if (hayReservasActivas) {
                // Si hay reservas activas, no se puede borrar

                return null;
            }
        }

        vueloRepository.delete(vuelo);
        return vuelo;

    }

    @Override
    public Vuelo obtenerVueloPorId(Long id) {

        return vueloRepository.findById(id).orElse(null);
    }

    @Override
    public Vuelo modificarVuelo(Long id, VueloDto vueloDto) {
        Vuelo vuelo = vueloRepository.findById(id).orElse(null);
        if (vuelo == null) {
            return null;
        }
        List<ReservaVuelo> reservasVuelo = reservaVueloRepository.findReservasByVuelo(vuelo);
        if (!reservasVuelo.isEmpty()) {
            boolean hayReservasActivas = reservasVuelo.stream().anyMatch(ReservaVuelo::isReservado);
            if (hayReservasActivas) {
                // Si hay reservas activas, no se permite la actualización

                return null;
            }
        }

        // Actualiza los campos del vuelo con los datos del DTO
        vuelo.setOrigen(vueloDto.getOrigen());
        vuelo.setDestino(vueloDto.getDestino());
        vuelo.setTipoDeAsiento(vueloDto.getTipoDeAsiento());
        vuelo.setPrecioVuelo(vueloDto.getPrecioVuelo());
        vuelo.setFecha(vueloDto.getFecha());

        // Guarda el vuelo actualizado
        return vueloRepository.save(vuelo);

    }


}
