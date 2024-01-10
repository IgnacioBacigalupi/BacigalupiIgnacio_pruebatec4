package PruebaTecnicaN4.PruebaTecnicaN4.services;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Usuario;
import PruebaTecnicaN4.PruebaTecnicaN4.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


}
