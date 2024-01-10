package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface IUsuarioService {

    Usuario crearUsuario(Usuario usuario);

}
