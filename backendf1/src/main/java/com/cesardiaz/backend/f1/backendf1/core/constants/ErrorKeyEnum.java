package com.cesardiaz.backend.f1.backendf1.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorKeyEnum {

    BAD_REQUEST("error.msg.bad.request","El la peticion es incorrect."),
    // Recurso no encontrado
    NOT_FOUND("error.msg.not.found","El recurso solicitado no pudo ser encontrado."),
    NOT_FOUND_RESOURCE("error.msg.not.found.resource","No se pudo encontrar el recurso especificado: %s."),
    NOT_FOUND_BEAN("error.msg.not.found.bean","No se encontró el componente necesario: %s."),

    // Errores generales
    UNKNOWN_ERROR("error.msg.unknown","Ha ocurrido un error desconocido. Por favor, inténtelo de nuevo."),
    DATA_DUPLICATED("error.msg.data.duplicated","El recurso a registrar ya está en el sistema."),

    // Formato incorrecto
    BAD_PHONE_FORMAT("error.msg.bad.phone.format","El número de teléfono proporcionado no cumple con el formato internacional esperado (Ej: +52551234567): %s."),

    RESET_PASSWORD_VALIDATION("error.msg.reset.password","La contraseña es incorrecta."),
    RESET_PASSWORD_SIMILAR("error.msg.reset.password","La nueva contraseña es igual a la anterior.");

    private String key;
    private String message;
}
