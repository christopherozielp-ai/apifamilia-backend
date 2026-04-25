package com.familia.apifamilia.exception;

public class MiembroNotFoundException extends RuntimeException {
    public MiembroNotFoundException(Long id) {
        super("Miembro con ID " + id + " no encontrado");
    }
}
