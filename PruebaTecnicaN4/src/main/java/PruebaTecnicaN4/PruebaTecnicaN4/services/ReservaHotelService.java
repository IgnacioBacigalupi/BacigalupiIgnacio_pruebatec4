package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.ReservaHotelDto;
import PruebaTecnicaN4.PruebaTecnicaN4.dto.UsuarioDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Habitacion;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaHotel;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Usuario;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HabitacionRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HotelRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.ReservaHotelRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaHotelService implements IReservaHotelService {
    @Autowired
    private ReservaHotelRepository reservaHotelRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private IUsuarioService usuarioService;


    @Override
    public ReservaHotel crearReservaHotel(ReservaHotelDto reservaHotelDto) {
        ReservaHotel reservaHotel = new ReservaHotel();


        Usuario usuario = new Usuario();

        Hotel hotelReserva = hotelRepository.findByCodigoHotel(reservaHotelDto.getCodigoHotel());
        if (hotelReserva == null) {
            return null;
        }
        //Traigo las habitaciones

        List<Habitacion> habitaciones = habitacionRepository.findByHotelAndTipoDeHabitacion(hotelReserva, reservaHotelDto.getTipoDeHabitacion());

        //Verifico la fecha
        if (reservaHotelDto.getFechaDesde().isBefore(LocalDate.now()) || habitaciones.isEmpty() ||
                reservaHotelDto.getFechaHasta().isAfter(habitaciones.get(0).getDisponibleHasta()) ||
                reservaHotelDto.getFechaHasta().isBefore(LocalDate.now())) {

            return null;
        }


        //seteo de datos
        reservaHotel.setCodigoHotel(reservaHotelDto.getCodigoHotel());
        reservaHotel.setFechaDesde(reservaHotelDto.getFechaDesde());
        reservaHotel.setFechaHasta(reservaHotelDto.getFechaHasta());

        //calculamos las noches con las fechas ingresadas
        int noches = (int) ChronoUnit.DAYS.between(reservaHotelDto.getFechaDesde(), reservaHotelDto.getFechaHasta());
        reservaHotel.setNoches(noches);
        reservaHotel.setTipoDeHabitacion(habitaciones.get(0).getTipoDeHabitacion());
        reservaHotel.setNumeroHuespedes(reservaHotelDto.getNumeroHuespedes());

        Usuario usuarioCorreo = usuarioRepository.findByCorreo(reservaHotelDto.getHuesped().getCorreo());
        if (usuarioCorreo != null) {
            return null;
        }
        //Asociamos el usuarios a la reserva
        usuario.setNombre(reservaHotelDto.getHuesped().getNombre());
        usuario.setApellido(reservaHotelDto.getHuesped().getApellido());
        usuario.setCorreo(reservaHotelDto.getHuesped().getCorreo());
        usuario.setPasaporte(reservaHotelDto.getHuesped().getPasaporte());
        usuario.setEdad(reservaHotelDto.getHuesped().getEdad());
        // Creo o actualizo al usuario
        usuarioService.crearUsuario(usuario);
        //Asociamos el hotelReserva y usuario a la reserva

        reservaHotel.setHotel(hotelReserva);
        reservaHotel.setUsuario(usuario);


        double precioTotal = noches * habitaciones.get(0).getPrecio();


        reservaHotel.setPrecio(precioTotal);
        reservaHotel.setNoches(noches);
        reservaHotelRepository.save(reservaHotel);

        return reservaHotel;


    }


}
