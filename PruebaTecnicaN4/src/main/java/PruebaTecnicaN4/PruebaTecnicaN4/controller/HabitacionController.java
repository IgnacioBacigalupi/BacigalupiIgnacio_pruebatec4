package PruebaTecnicaN4.PruebaTecnicaN4.controller;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Habitacion;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IHabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
    @Autowired
    private IHabitacionService habitacionService;


    @Operation(summary = "Crear una habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habitacion creada correctamente"),
            @ApiResponse(responseCode = "400", description = "No se puedo crear la habitacion")
    })
    @PostMapping("/new")
    public ResponseEntity<?> crearHabitacion(@RequestBody Habitacion habitacion) {
        habitacionService.createHabitacion(habitacion);
        return ResponseEntity.ok().body("Habitacion creada");
    }


}
