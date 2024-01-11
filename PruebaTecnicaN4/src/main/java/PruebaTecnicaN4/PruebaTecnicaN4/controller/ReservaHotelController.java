package PruebaTecnicaN4.PruebaTecnicaN4.controller;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.ReservaHotelDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaHotel;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IHabitacionService;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IReservaHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency")
public class ReservaHotelController {

    @Autowired
    private IReservaHotelService reservaHotelService;

    @Autowired
    private IHabitacionService habitacionService;

    @Operation(summary = "Crear una reserva de hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva realizada"),
            @ApiResponse(responseCode = "400", description = "Fecha de reserva invalida o habitacion no disponible")
    })
    //Metodo para realizar una reserca de hotel
    @PostMapping("/hotel-booking/new")
    public ResponseEntity<?> crearReservaHotel(@RequestBody ReservaHotelDto reservaHotelDto) {
        ReservaHotel reservaHotel = reservaHotelService.crearReservaHotel(reservaHotelDto);
        if (reservaHotel == null) {
            return ResponseEntity.badRequest().body("Fecha de reserva invalida , habitacion no disponible o usuario ya registrado");
        }

        return ResponseEntity.ok().body("El precio total de la reserva es :" + reservaHotel.getPrecio() + " €");
    }

    @Operation(summary = "Eliminar una reserva de hotel por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "No se encontro una reserva con ese id")
    })
    @DeleteMapping("/hotel-booking/delete/{id}")
    public ResponseEntity<?> eliminarReservaHotel(@PathVariable Long id) {
        ReservaHotel reservaEliminada = reservaHotelService.eliminarHotelReservaPorId(id);

        if (reservaEliminada != null) {
            return new ResponseEntity<>(reservaEliminada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la reserva de hotel con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }
    }
}
