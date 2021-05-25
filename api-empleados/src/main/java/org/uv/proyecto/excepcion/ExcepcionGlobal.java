package org.uv.proyecto.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExcepcionGlobal extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<?> recursoNoEncontrado (RecursoNoEncontrado ex, WebRequest request) {

        DetalleError detalle = new DetalleError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalle, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorGlobal (RecursoNoEncontrado ex, WebRequest request) {

        DetalleError detalle = new DetalleError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalle, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public static class DetalleError {

        private Date timestamp;
        private String mensaje;
        private String detalle;

        public DetalleError(Date timestamp, String mensaje, String detalle) {
            super();
            this.timestamp = timestamp;
            this.mensaje = mensaje;
            this.detalle = detalle;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getDetalle() {
            return detalle;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }
    }

}

