package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Habitacion;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService implements IHabitacionService {


    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public Habitacion createHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }


}
