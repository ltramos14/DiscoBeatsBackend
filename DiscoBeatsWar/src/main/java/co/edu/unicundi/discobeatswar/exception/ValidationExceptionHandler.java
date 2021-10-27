/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatswar.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    /**
     * Objeto que obtiene la información del request de la petición realizada
     */
    @Context
    private HttpServletRequest request;
    
    @Override

    public Response toResponse(ConstraintViolationException exception) {

        String mensaje = "";
        String endpoint = request.getRequestURI();

        for (ConstraintViolation constraint : exception.getConstraintViolations()) {
         
            mensaje += constraint.getPropertyPath().toString() + ": " + constraint.getMessage() + ". ";
            
        }

        
        ExceptionWrapper wrapper = new ExceptionWrapper(Response.Status.BAD_REQUEST.getStatusCode(),
                         Response.Status.BAD_REQUEST, mensaje, endpoint);
        
        return Response.status(Response.Status.BAD_REQUEST).entity(wrapper).build();
        
    }

}
