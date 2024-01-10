package PruebaTecnicaN4.PruebaTecnicaN4.controller;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.ReservaVueloDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaVuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IReservaVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/agency")
public class ReservaVueloController {

    @Autowired
    private IReservaVueloService reservaVueloService;


    @Operation(summary = "Crear una reserva de vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva realizada"),
            @ApiResponse(responseCode = "400", description = "La reserva no puede ser en el pasado o el vuelo ya esta reservado")
    })
    @PostMapping("/flight-booking/new")
    public ResponseEntity<?> crearReservaVuelo(@RequestBody ReservaVueloDto reservaVueloDt0) {
        ReservaVuelo reservaVuelo = reservaVueloService.crearReservaVuelo(reservaVueloDt0);

        if (reservaVuelo == null) {
            return ResponseEntity.badRequest().body("La reserva no puede ser en el pasado o el vuelo ya esta reservado");
        }
        double precio = reservaVuelo.getVuelo().getPrecioVuelo();
        return ResponseEntity.ok().body("El precio total del vuelo es :" + precio * reservaVueloDt0.getPeopleQ());
    }

}
