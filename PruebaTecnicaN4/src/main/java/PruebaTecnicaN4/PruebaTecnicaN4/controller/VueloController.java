package PruebaTecnicaN4.PruebaTecnicaN4.controller;

import PruebaTecnicaN4.PruebaTecnicaN4.dto.VueloDto;
import PruebaTecnicaN4.PruebaTecnicaN4.model.ReservaVuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.model.Vuelo;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.ReservaVueloRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.services.IVueloService;
import PruebaTecnicaN4.PruebaTecnicaN4.services.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class VueloController {


    @Autowired
    private IVueloService IVueloService;

    @Autowired
    private ReservaVueloRepository reservaVueloRepository;

    @Autowired
    private VueloService vueloService;


    @Operation(summary = "Crea un nuevo vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se creó el vuelo correctamente"),
            @ApiResponse(responseCode = "400", description = "El vuelo no puede ser creado en el pasado")
    })
    // Metodo para crear un nuevo vuelo
    @PostMapping("/flight/new")
    public ResponseEntity<?> crearVuelo(@RequestBody VueloDto vueloDto) {
        Vuelo vuelo = IVueloService.crearVuelo(vueloDto);
        if (vuelo == null) {
            return ResponseEntity.badRequest().body("El vuelo no puede ser creado en el pasado");
        }
        return ResponseEntity.ok().body("Vuelo creado ");
    }


    @Operation(summary = "Obtener vuelos filtrados por parametros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la lista de los vuelos filtrados"),
            @ApiResponse(responseCode = "400", description = "Error en la invocacion por parametros incorrectos")
    })
    //Metodo para obtener vuelos segun fechas , destino y origen
    @GetMapping("/flights/search")
    public List<Vuelo> obtenerTodosLosVuelosPorFechaYLugar(@RequestParam LocalDate fechaDesde,
                                                           @RequestParam LocalDate fechaHasta,
                                                           @RequestParam String origen,
                                                           @RequestParam String destino) {
        return IVueloService.obtenerTodosLosVuelosPorFechaYLugar(fechaDesde, fechaHasta, origen, destino);
    }


    @Operation(summary = "Obtener todos los vuelos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de todos los vuelos"),
            @ApiResponse(responseCode = "400", description = "Error en la invocaccion por parametros incorrectos")
    })
    //obtenerTodosLosVuelos
    @GetMapping("/flights")
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloService.obtenerTodosLosVuelos();
    }


    @Operation(summary = "Borrado de un vuelo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina correctamente el vuelo"),
            @ApiResponse(responseCode = "400", description = "El ID del vuelo no está registrado,No se puede borrar el vuelo. Tiene una reserva activa. ")
    })
    //Metodo para borrar vuelo por id
    @DeleteMapping("/flight/delete/{id}")
    public ResponseEntity<?> borrarVueloPorId(@PathVariable Long id) {
        Vuelo vueloBorrado = vueloService.borrarVueloPorId(id);
        if (vueloBorrado == null) {
            // Verificar si el vuelo está reservado
            Vuelo vuelo = vueloService.obtenerVueloPorId(id);
            if (vuelo != null) {
                List<ReservaVuelo> reservasVuelo = reservaVueloRepository.findReservasByVuelo(vuelo);
                boolean hayReservasActivas = reservasVuelo.stream().anyMatch(ReservaVuelo::isReservado);
                if (hayReservasActivas) {
                    return ResponseEntity.badRequest().body("No se puede borrar el vuelo. Tiene una reserva activa.");
                }
            }
            return ResponseEntity.badRequest().body("El ID del vuelo no está registrado.");
        }
        return ResponseEntity.ok().body("Vuelo borrado");
    }


    @Operation(summary = "Buscar un vuelo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve el vuelo por id"),
            @ApiResponse(responseCode = "400", description = "El ID del vuelo no está registrado. ")
    })
    // Endpoint para obtener un vuelo por su ID
    @GetMapping("/flight/{id}")
    public ResponseEntity<?> obtenerVueloPorId(@PathVariable Long id) {
        Vuelo vuelo = vueloService.obtenerVueloPorId(id);
        if (vuelo != null) {
            return ResponseEntity.ok().body(vuelo);
        } else {
            String mensaje = "No hay ningún vuelo registrado con el ID proporcionado: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }


    @Operation(summary = "Editar un vuelo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vuelo modificado"),
            @ApiResponse(responseCode = "400", description = "No se puede modificar el vuelo. Tiene reservas activas, No existe ningún vuelo con el ID proporcionado: ")
    })
    //Metodo para editar un vuelo por id
    @PutMapping("/flights/edit/{id}")
    public ResponseEntity<?> modificarVueloPorId(@PathVariable Long id, @RequestBody VueloDto vueloDto) {
        Vuelo vueloModificado = vueloService.modificarVuelo(id, vueloDto);

        if (vueloModificado != null) {
            return ResponseEntity.ok().body(vueloModificado);
        } else {
            Vuelo vueloExistente = vueloService.obtenerVueloPorId(id);
            if (vueloExistente != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede modificar el vuelo. Tiene reservas activas.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún vuelo con el ID proporcionado: " + id);
            }
        }
    }


}
