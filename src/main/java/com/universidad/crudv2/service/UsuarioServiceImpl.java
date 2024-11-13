package com.universidad.crudv2.service;
import com.universidad.crudv2.model.Usuario;
import com.universidad.crudv2.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findAllActive();
    }

    @Override
    public Optional<Usuario> obtenerPorIdUsuarioActivo(Long id) {
        return usuarioRepository.findActiveById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorReferencia(String reference) {
        return usuarioRepository.findActiveByReference(reference);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findActiveByEmail(email);
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario) {
        Usuario usuarioParaGuardar = Usuario
                .builder()
                .reference(UUID.randomUUID().toString())
                .password(usuario.getPassword())
                .firstName(usuario.getFirstName())
                .lastName(usuario.getLastName())
                .nickname(usuario.getNickname())
                .email(usuario.getEmail())
                .isActive(true)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .version(usuario.getVersion())
                .build();
        return Optional.of(usuarioRepository.save(usuarioParaGuardar));
    }

    @Override
    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioViejo = usuarioRepository.findById(id);
        if (usuarioViejo.isPresent()) {
            Usuario usuarioParaActualizar = usuarioViejo.get();
            usuarioParaActualizar.setFirstName(usuario.getFirstName());
            usuarioParaActualizar.setLastName(usuario.getLastName());
            usuarioParaActualizar.setNickname(usuario.getNickname());
            usuarioParaActualizar.setPassword(usuario.getPassword());
            usuarioParaActualizar.setUpdatedAt(ZonedDateTime.now());
            return Optional.of(usuarioRepository.save(usuarioParaActualizar));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> actualizarUsuarioParcial(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioParaActualizar = usuarioExistente.get();
            // Actualizar solo los campos proporcionados en el objeto `usuario`
            if (usuario.getFirstName() != null) usuarioParaActualizar.setFirstName(usuario.getFirstName());
            if (usuario.getLastName() != null) usuarioParaActualizar.setLastName(usuario.getLastName());
            if (usuario.getEmail() != null) usuarioParaActualizar.setEmail(usuario.getEmail());
            // Agrega más campos según sea necesario...
            usuarioParaActualizar.setUpdatedAt(ZonedDateTime.now());
            return Optional.of(usuarioRepository.save(usuarioParaActualizar));
        }
        return Optional.empty();
    }

}
