package mx.edu.uacm.metrica.metricadesoftware.repository;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombre(String nombre);

    //@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
   // List<Usuario> findByEmail(String email);

    Page<Usuario> findAll(Pageable pageable);

    Optional<Usuario> findById(long id);
    
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario getUsuariorBycorreo(@Param("email") String email);
}
