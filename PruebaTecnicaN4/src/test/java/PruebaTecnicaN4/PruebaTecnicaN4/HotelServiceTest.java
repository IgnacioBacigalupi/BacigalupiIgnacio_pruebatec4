package PruebaTecnicaN4.PruebaTecnicaN4;


import PruebaTecnicaN4.PruebaTecnicaN4.model.Hotel;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HotelRepository;
import PruebaTecnicaN4.PruebaTecnicaN4.services.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;
    @InjectMocks
    private HotelService hotelService;

    @Test
    void testObtenerHotelConMockito() {
        Hotel objetoSimulado = new Hotel(1L, "HA-5246", "Hotel Alvear", "Madrid", null, null);
        Hotel esperado = new Hotel(1L, "HA-5246", "Hotel Alvear", "Madrid", null, null);
        Mockito.when(hotelRepository.findById(1L))
                .thenReturn(Optional.of(objetoSimulado));
        final Hotel resultado = hotelService.obtenerHotelPorId(1L);
        Assertions.assertEquals(esperado, resultado);
        Mockito.verify(hotelRepository).findById(1L);

    }

    @Test
    void testObtenerHotelNoExistenteConMockito() {
        Mockito.when(hotelRepository.findById(2L))
                .thenReturn(Optional.empty());

        final Hotel resultado = hotelService.obtenerHotelPorId(2L);

        Assertions.assertNull(resultado); // Verificar que el resultado sea null
        Mockito.verify(hotelRepository).findById(2L);
    }
}
