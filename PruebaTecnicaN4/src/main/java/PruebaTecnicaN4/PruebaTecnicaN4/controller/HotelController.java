package PruebaTecnicaN4.PruebaTecnicaN4.controller;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.HotelDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HotelRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class HotelController {
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;


    @Operation(summary = "Crea un nuevo hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se creó el hotel correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear un nuevo hotel")
    })
    // Crear un nuevo hotel
    @PostMapping("/hotels/new")
    public String agregarHotel(@RequestBody HotelDto hotelDto) {

        hotelService.crearHotel(hotelDto);
        return "Hotel creado";
    }


    @Operation(summary = "Buscar hoteles por parametros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de hoteles filtrados"),
            @ApiResponse(responseCode = "400", description = "Error al ingresar los parametros")
    })
    //Obtener todos los hoteles por fechas y lugar
    @GetMapping("/hotels/search")
    public List<Hotel> obtenertodosLosHoteles(@RequestParam LocalDate disponibleDesde,
                                              @RequestParam LocalDate disponibleHasta,
                                              @RequestParam String ubicacion) {
        return hotelService.obtenerHotelPorFechayLugar(disponibleDesde, disponibleHasta, ubicacion);
    }

    @Operation(summary = "Obtener todos los hoteles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de todos los hoteles"),
            @ApiResponse(responseCode = "400", description = "Error al devovler la lista")
    })
    //Obtener un listado de todos los hoteles
    @GetMapping("/hotels")
    public List<Hotel> obtenerHoteles() {
        return hotelService.obtenerTodosLosHoteles();
    }


    @Operation(summary = "Obtener un hotel por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve el hotel encontrado"),
            @ApiResponse(responseCode = "400", description = "No hay ningun hotel registrado con el ID proporcionado ")
    })
    //Obtener un hotel por su id
    @GetMapping("/hotels/{id}")
    public ResponseEntity<?> obtenerHotelPorId(@PathVariable Long id) {
        Hotel hotel = hotelService.obtenerHotelPorId(id);
        if (hotel != null) {
            return ResponseEntity.ok().body(hotel);
        } else {
            String mensaje = "No hay ningun hotel registrado con el ID proporcionado :" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @Operation(summary = "Borrar hotel por su  id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "hotel borrado correctamente"),
            @ApiResponse(responseCode = "400", description = "No se puede borrar el hotel. Tiene reservas activas.No existe ningún hotel con el ID proporcionado")
    })
    //Borrar hotel por su id
    @DeleteMapping("/hotels/delete/{id}")
    public ResponseEntity<?> borrarHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.obtenerHotelPorId(id);

        if (hotel != null) {
            boolean tieneReservasActivas = hotelService.tieneReservasActivas(hotel);
            if (!tieneReservasActivas) {
                Hotel hotelBorrado = hotelService.borrarHotelPorId(id);
                if (hotelBorrado != null) {
                    return ResponseEntity.ok().body("Hotel borrado correctamente");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede borrar el hotel. Tiene reservas activas.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún hotel con el ID proporcionado : " + id);
        }
    }


    @Operation(summary = "Editar hotel por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel editado correctamente\""),
            @ApiResponse(responseCode = "400", description = "No se puede editar el hotel. Tiene reservas activas,No existe ningún hotel con el ID proporcionad")
    })
    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<?> editarHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();

            // Verificar si hay reservas asociadas a este hotel
            boolean tieneReservasActivas = hotelService.tieneReservasActivas(hotel);

            if (!tieneReservasActivas) {
                // No tiene reservas activas, se puede editar
                hotel.setNombre(hotelDto.getNombre());
                hotel.setUbicacion(hotelDto.getUbicacion());
                // Actualizar otros campos según sea necesario...

                Hotel hotelEditado = hotelService.editarHotelPorId(id ,  hotelDto);
                if (hotelEditado != null) {
                    return ResponseEntity.ok().body("Hotel editado correctamente");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo editar el hotel");
                }
            } else {
                // Tiene reservas activas, no se puede editar
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede editar el hotel. Tiene reservas activas.");
            }
        } else {
            // El ID del hotel no está registrado en la base de datos
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún hotel con el ID proporcionado : " + id);
        }
    }

}
