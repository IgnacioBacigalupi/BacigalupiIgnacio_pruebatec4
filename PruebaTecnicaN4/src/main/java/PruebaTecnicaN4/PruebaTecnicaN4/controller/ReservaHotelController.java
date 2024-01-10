package PruebaTecnicaN4.PruebaTecnicaN4.controller;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.ReservaHotelDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaHotel;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IHabitacionService;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IReservaHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
