package com.universidad.crudv2.service;
import com.universidad.crudv2.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> obtenerUsuarios();

    Optional<Usuario> obtenerPorId(Long id);

    List<Usuario> obtenerUsuariosActivos();

    Optional<Usuario> obtenerPorIdUsuarioActivo(Long id);

    Optional<Usuario> obtenerUsuarioPorReferencia(String reference);

    Optional<Usuario> obtenerUsuarioPorEmail(String email);

    Optional<Usuario> crearUsuario(Usuario usuario);

    Optional<Usuario> actualizarUsuario(Long id, Usuario usuario);

    void eliminarUsuario(Long id);

    Optional<Usuario> actualizarUsuarioParcial(Long id, Usuario usuario);

}

