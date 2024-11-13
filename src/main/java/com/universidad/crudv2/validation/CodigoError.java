package com.universidad.crudv2.validation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import static java.lang.String.format;
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum CodigoError {

    USUARIO_NO_ENCONTRADO("Usuario con id %d no encontrado"),
    USUARIO_POR_ID_ACTIVO_NO_ENCONTRADO("Usuario activo con id %d no encontrado"),
    USUARIO_POR_REFERENCIA_NO_ENCONTRADO("Usuario con referencia %s no encontrado"),
    USUARIO_POR_EMAIL_NO_ENCONTRADO("No se encontró ningún usuario con el email %s"),
    USUARIO_NO_PUDO_SER_CREADO("Usuario con el email %s no pudo ser creado"),
    USUARIO_NO_PUDO_SER_ACTUALIZADO("Usuario con id %d no pudo ser actualizado"),
    USUARIO_NO_PUDO_SER_BORRADO("Usuario con id %d no pudo ser borrado");

    private final String descripcion;
    public String getCodigo() {
        return this.name();
    }
    public String getDescripcion(Object... params) {
        return format(descripcion, params);
    }
}
