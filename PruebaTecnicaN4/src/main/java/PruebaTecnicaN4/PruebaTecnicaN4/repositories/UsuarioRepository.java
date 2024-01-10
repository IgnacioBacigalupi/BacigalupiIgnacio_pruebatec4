package PruebaTecnicaN4.PruebaTecnicaN4.repositories;

import PruebaTecnicaN4.PruebaTecnicaN4.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);
}
