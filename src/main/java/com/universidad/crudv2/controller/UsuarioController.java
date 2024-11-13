package com.universidad.crudv2.controller;
import com.universidad.crudv2.exception.BadRequestException;
import com.universidad.crudv2.exception.NotFoundException;
import com.universidad.crudv2.model.Usuario;
import com.universidad.crudv2.service.UsuarioService;
import com.universidad.crudv2.validation.CodigoError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")

public class UsuarioController {

    // Servicio para manejar las operaciones de los usuarios
    private final UsuarioService usuarioService;

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de todos los usuarios registrados.")
            @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Usuario.class))),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                            content = @Content)
            })

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        log.info("Se obtuvo una lista de {} usuarios.", usuarios.size());
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario a obtener.
     * @return Detalles del usuario.
     */
    @Operation(summary = "Obtener un usuario por su ID", description = "Retorna los detalles de un usuario específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(
        @Parameter(description = "ID del usuario a obtener", required = true, example = "1")
        @PathVariable Long id) {
    Usuario usuario = usuarioService.obtenerPorId(id)
            .orElseThrow(() ->
                    new NotFoundException(
                            CodigoError.USUARIO_NO_ENCONTRADO.getCodigo(),
                            CodigoError.USUARIO_NO_ENCONTRADO.getDescripcion(id)));
    log.info("Se obtuvo el usuario con ID {}: {}", id, usuario);
    return ResponseEntity.ok(usuario);
    }

    /**
     * Obtiene una lista de todos los usuarios activos.
     *
     * @return Lista de usuarios activos.
     */
    @Operation(summary = "Obtener todos los usuarios activos", description =
            "Retorna una lista de todos los usuarios que están activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios activos obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosActivos() {
        List<Usuario> usuariosActivos = usuarioService.obtenerUsuariosActivos();
        log.info("Se obtuvo una lista de {} usuarios activos.",
                usuariosActivos.size());
        return ResponseEntity.ok(usuariosActivos);
    }

    /**
     * Obtiene un usuario activo por su ID.
     *
     * @param id ID del usuario activo a obtener.
     * @return Detalles del usuario activo.
     */
    @Operation(summary = "Obtener un usuario activo por su ID", description = "Retorna los detalles de un usuario específico que está activo basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario activo obtenido correctamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario activo no encontrado", content = @Content)
    })

    @GetMapping("/activos/{id}")
    public ResponseEntity<Usuario> obtenerPorIdUsuarioActivo(
        @Parameter(description = "ID del usuario activo a obtener", required = true, example = "1")
        @PathVariable Long id) {
    Usuario usuarioActivo = usuarioService.obtenerPorIdUsuarioActivo(id)
            .orElseThrow(() ->
                    new NotFoundException(
                            CodigoError.USUARIO_POR_ID_ACTIVO_NO_ENCONTRADO.getCodigo(),
                            CodigoError.USUARIO_POR_ID_ACTIVO_NO_ENCONTRADO.getDescripcion(id)));
    log.info("Se obtuvo el usuario activo con ID {}: {}", id, usuarioActivo);
    return ResponseEntity.ok(usuarioActivo);
    }

    /**
     * Obtiene un usuario por su referencia.
     *
     * @param reference Referencia del usuario a obtener.
     * @return Detalles del usuario.
     */
    @Operation(summary = "Obtener un usuario por su referencia", description =
            "Retorna los detalles de un usuario específico basado en su referencia única.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario por referencia no encontrado", content = @Content)
    })

    @GetMapping("/referencia/{reference}")
    public ResponseEntity<Usuario> obtenerUsuarioPorReferencia(
            @Parameter(description = "Referencia única del usuario a obtener",
                    required = true, example = "REF12345")
            @PathVariable String reference) {
        Usuario usuario = usuarioService.obtenerUsuarioPorReferencia(reference)
                .orElseThrow(() ->
                        new NotFoundException(
                                CodigoError.USUARIO_POR_REFERENCIA_NO_ENCONTRADO.getCodigo(),
                                CodigoError.USUARIO_POR_REFERENCIA_NO_ENCONTRADO.getDescripcion(reference)));
        log.info("Se obtuvo el usuario con referencia {}: {}", reference, usuario);
        return ResponseEntity.ok(usuario);
    }

    /**
     * Obtiene un usuario por su email.
     *
     * @param email Email del usuario a obtener.
     * @return Detalles del usuario.
     */
    @Operation(summary = "Obtener un usuario por su email", description = "Retorna los detalles de un usuario específico basado en su email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
    @ApiResponse(responseCode = "404", description = "Usuario por email no encontrado", content = @Content)
    })

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(
        @Parameter(description = "Email del usuario a obtener", required =
                true, example = "usuario@ejemplo.com")
        @PathVariable String email) {
    Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email)
            .orElseThrow(() ->
                    new NotFoundException(
                            CodigoError.USUARIO_POR_EMAIL_NO_ENCONTRADO.getCodigo(),
                            CodigoError.USUARIO_POR_EMAIL_NO_ENCONTRADO.getDescripcion(email)));
    log.info("Se obtuvo el usuario con email {}: {}", email, usuario);
    return ResponseEntity.ok(usuario);

    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario Datos del usuario a crear.
     * @return Detalles del usuario creado.
     */
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(
        @Parameter(description = "Datos del usuario a crear", required = true)
        @RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.crearUsuario(usuario)
            .orElseThrow(() ->
                    new BadRequestException(
                            CodigoError.USUARIO_NO_PUDO_SER_CREADO.getCodigo(),
                            CodigoError.USUARIO_NO_PUDO_SER_CREADO.getDescripcion(usuario.getEmail())));
    log.info("Se creó el usuario con email {} correctamente.",
            usuario.getEmail());
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /**
     * Actualiza un usuario existente completamente.
     *
     * @param id ID del usuario a actualizar.
     * @param usuario Datos actualizados del usuario.
     * @return Detalles del usuario actualizado.
     */
    @Operation(summary = "Actualizar un usuario existente", description =
            "Actualiza completamente los datos de un usuario existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),

            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @Parameter(description = "ID del usuario a actualizar", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados del usuario", required = true)
            @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario)
                .orElseThrow(() ->
                        new NotFoundException(
                                CodigoError.USUARIO_NO_PUDO_SER_ACTUALIZADO.getCodigo(),
                                CodigoError.USUARIO_NO_PUDO_SER_ACTUALIZADO.getDescripcion(id)));
        log.info("Se actualizó el usuario con email {} correctamente.",
                usuario.getEmail());
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * Actualiza parcialmente un usuario existente.
     *
     * @param id ID del usuario a actualizar.
     * @param usuario Datos parciales del usuario a actualizar.
     * @return Detalles del usuario actualizado.
     */

    @Operation(summary = "Actualizar parcialmente un usuario existente",
            description = "Actualiza parcialmente los datos de un usuario existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado parcialmente correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),

            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioParcial(
        @Parameter(description = "ID del usuario a actualizar", required =
                true, example = "1")
        @PathVariable Long id,
        @Parameter(description = "Datos parciales del usuario a actualizar", required = true) @RequestBody Usuario usuario) {

        Usuario usuarioActualizado = usuarioService.actualizarUsuarioParcial(id, usuario)
            .orElseThrow(() ->
                    new NotFoundException(
                            CodigoError.USUARIO_NO_PUDO_SER_ACTUALIZADO.getCodigo(),
                            CodigoError.USUARIO_NO_PUDO_SER_ACTUALIZADO.getDescripcion(id)));
        log.info("Se actualizó parcialmente el usuario con email {} correctamente.", usuario.getEmail());
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * Elimina un usuario existente.
     *
     * @param id ID del usuario a eliminar.
     * @return Mensaje de confirmación.
     */
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente", content = @Content(mediaType = "application/json")),

            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),

            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarUsuario(
            @Parameter(description = "ID del usuario a eliminar", required = true, example = "1")
            @PathVariable Long id) {

        // Verificar si el usuario existe
        Usuario usuarioEliminado = usuarioService.obtenerPorId(id)
                .orElseThrow(() -> new BadRequestException(
                        CodigoError.USUARIO_NO_PUDO_SER_BORRADO.getCodigo(),
                        CodigoError.USUARIO_NO_PUDO_SER_BORRADO.getDescripcion(id)));

        // Eliminar el usuario
        usuarioService.eliminarUsuario(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", String.format("El usuario con ID %d ha sido eliminado", usuarioEliminado.getId()));
        log.info("Se eliminó el usuario con ID {} correctamente.", usuarioEliminado.getId());

        return ResponseEntity.ok(respuesta);
    }


}

