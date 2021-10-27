package co.edu.unicundi.discobeatswar.exception;

import java.util.Date;
import javax.ws.rs.core.Response;

/**
 * Clase que se usa como una envoltura para manejar las respuestas de los
 * servicios
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @version 2.0.1
 * @since 1.0.0
 */
public class ExceptionWrapper {
    
    /**
     * Variable de tipo entera que almacena el código de la respuesta
     */
    private int code;

    /**
     * Variable de tipo Response.Status que almacena el estado de la respusta
     */
    private Response.Status status;

    /**
     * Variable de tipo String que almacena el mensaje que se enviará en la
     * respuesta
     */
    private String error;

    /**
     * Variable de tipo String que almacena la fecha de la respuesta
     */
    private String date;

    /**
     * Variable de tipo String la URL del endpoint del servicio pedido
     */
    private String url;

    /**
     * Contructor vacío de la clase MessageWrapper
     */
    public ExceptionWrapper() {
    }

    /**
     * Constructor sobrecargado de la clase MessageWrapper
     *
     * @param code
     * @param status
     * @param error
     * @param url
     */
    public ExceptionWrapper(int code, Response.Status status, String error, String url) {
        this.code = code;
        this.status = status;
        this.error = error;
        this.date = new Date().toString();
        this.url = url;
    }

    /**
     * Método que obtiene el código de la envoltura
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * Método que asigna el código a la envoltura
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Método que obtiene el estado de la envoltura
     *
     * @return
     */
    public Response.Status getStatus() {
        return status;
    }

    /**
     * Método que asigna el estado a la envoltura
     *
     * @param status
     */
    public void setStatus(Response.Status status) {
        this.status = status;
    }

    /**
     * Método que obtiene el mensaje de la envoltura
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * Método que asigna el mensaje a la envoltura
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Método que obtiene la fecha de la envoltura
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Método que asigna la fecha a la envoltura
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Método que obtiene la URL de la envoltura
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Método que asigna la URL a la envoltura
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
