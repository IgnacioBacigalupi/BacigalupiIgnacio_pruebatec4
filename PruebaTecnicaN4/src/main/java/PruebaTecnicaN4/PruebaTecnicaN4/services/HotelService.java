package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.HotelDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Habitacion;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaHotel;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HabitacionRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HotelRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.ReservaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private ReservaHotelRepository reservaHotelRepository;

    private Random random = new Random();

    @Override
    public Hotel crearHotel(HotelDto hotelDto) {

        Hotel hotel = new Hotel();

        hotel.setNombre(hotelDto.getNombre());
        hotel.setUbicacion(hotelDto.getUbicacion());


        String[] partesNombre = hotel.getNombre().split(" ");
        String[] partesNombreScript = hotel.getNombre().split("-");
        String[] palabraSola = hotel.getNombre().split("[^\\p{L}]+");

        StringBuilder codigoHotel = new StringBuilder();

        if (partesNombreScript.length >= 2) {

            for (String part : partesNombreScript) {

                if (part.matches("[\\p{L}]+")) {
                    String letrasSolo = Normalizer.normalize(part, Normalizer.Form.NFD).replaceAll("[^\\p{L}]", "");
                    codigoHotel.append(letrasSolo.length() >= 1 ? letrasSolo.substring(0, 1) : "");

                }
            }
        } else if (palabraSola.length == 1 && palabraSola[0].matches("[\\p{L}]+")) {

            String letrasSolo = Normalizer.normalize(palabraSola[0], Normalizer.Form.NFD).replaceAll("[^\\p{L}]", "");
            codigoHotel.append(letrasSolo.substring(0, Math.min(3, letrasSolo.length())));


        } else if (partesNombre.length >= 2) {

            for (String part : partesNombre) {
                if (part.matches("[\\p{L}]+")) {

                    String letrasSolo = Normalizer.normalize(part, Normalizer.Form.NFD).replaceAll("[^\\p{L}]", "");
                    codigoHotel.append(letrasSolo.length() >= 1 ? letrasSolo.substring(0, 1) : "");

                }
            }
        }


        codigoHotel.append("-")
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10));


        hotel.setCodigoHotel(codigoHotel.toString().toUpperCase());
        return hotelRepository.save(hotel);
    }


    @Override
    public List<Hotel> obtenerHotelPorFechayLugar(LocalDate disponibleDesde, LocalDate disponibleHasta, String ubicacion) {
        List<Habitacion> habitaciones = habitacionRepository.encontrarHabitacionDisponibleAndUbicacion(disponibleDesde, disponibleHasta, ubicacion);
        return habitaciones.stream().map(Habitacion::getHotel).toList();
    }

    @Override
    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelRepository.findAll();
    }

    //Metodo para buscar un hotel por id
    @Override
    public Hotel obtenerHotelPorId(Long id) {
        return hotelRepository.findById(id).orElse(null);

    }

    //Borrar hotel por id
    @Override
    public Hotel borrarHotelPorId(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();

            // Verificar si hay reservas asociadas a este hotel
            List<ReservaHotel> reservasHotel = reservaHotelRepository.findByHotel(hotel);
            if (!reservasHotel.isEmpty()) {

                return null;
            }

            hotelRepository.delete(hotel);
            return hotel;
        } else {

            return null;
        }
    }


    //Metodo para saber si el hotel tiene alguna reserva activa
    public boolean tieneReservasActivas(Hotel hotel) {
        List<ReservaHotel> reservasHotel = reservaHotelRepository.findByHotel(hotel);
        for (ReservaHotel reserva : reservasHotel) {
            if (reserva.getFechaHasta().isAfter(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Hotel editarHotelPorId(Long id, HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();

            // Verificar si hay reservas asociadas a este hotel
            boolean tieneReservasActivas = tieneReservasActivas(hotel);

            if (!tieneReservasActivas) {
                // No tiene reservas activas, se puede editar
                hotel.setNombre(hotelDto.getNombre());
                hotel.setUbicacion(hotelDto.getUbicacion());


                return hotelRepository.save(hotel);

            } else {

                return null;
            }
        } else {

            return null;
        }
    }


}
