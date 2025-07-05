package com.fidelidad.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class FechaUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu")
        .withResolverStyle(ResolverStyle.STRICT);

    public static LocalDate parsear(String fechaTexto) {
        if (fechaTexto == null) {
            throw new IllegalArgumentException("Fecha inválida, use formato dd-MM-aaaa");
        }
        try {
            return LocalDate.parse(fechaTexto, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida, use formato dd-MM-aaaa", e);
        }
    }
}