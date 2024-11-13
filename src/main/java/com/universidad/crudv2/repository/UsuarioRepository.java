package com.universidad.crudv2.repository;
import com.universidad.crudv2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta para encontrar todos los usuarios activos
    @Query("SELECT u FROM Usuario u WHERE u.isActive = true")
    List<Usuario> findAllActive();

    // Consulta para encontrar un usuario activo por ID
    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.isActive = true")
    Optional<Usuario> findActiveById(@Param("id") long id);

    // Consulta para encontrar un usuario activo por referencia
    @Query("SELECT u FROM Usuario u WHERE u.reference = :reference AND u.isActive = true")
    Optional<Usuario> findActiveByReference(@Param("reference") String reference);

    // Consulta para encontrar un usuario activo por email
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.isActive = true")
    Optional<Usuario> findActiveByEmail(@Param("email") String email);
}


