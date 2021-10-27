package co.edu.unicundi.discobeatswar.exception;

import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
public class ExceptionHandler implements ExceptionMapper<Exception> {

    /**
     * Objeto que obtiene la información del request de la petición realizada
     */
    @Context
    private HttpServletRequest request;

    /**
     * Método que retorna una respuesta de tipo Response en caso de que exista
     * algun tipo de error al momento de hacer la petición
     *
     * @param exception
     * @return una respuesta con error
     */
    @Override
    public Response toResponse(Exception exception) {

        //exception.printStackTrace();
        ExceptionWrapper msg;

        String endpoint = request.getRequestURI();

        Response.Status status = Response.Status.BAD_REQUEST;

        if (exception instanceof ConflictException) {
            status = Response.Status.CONFLICT;
        }
        if (exception instanceof NotAllowedException) {
            status = Response.Status.METHOD_NOT_ALLOWED;
        }
        if (exception instanceof NotSupportedException) {
            status = Response.Status.UNSUPPORTED_MEDIA_TYPE;
        }
        if (exception instanceof InternalServerErrorException) {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }

        msg = new ExceptionWrapper(status.getStatusCode(), status, exception.getMessage(), endpoint);
        return Response.status(status).type(MediaType.APPLICATION_JSON).entity(msg).build();

    }

}
