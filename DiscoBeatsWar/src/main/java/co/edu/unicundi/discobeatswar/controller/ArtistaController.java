package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import javax.ejb.Stateless;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
@Path("/artistas")
public class ArtistaController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistas() {

        return Response.status(Response.Status.OK).entity(null).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistaPorId(@PathParam("id") @Pattern(regexp = "^([0-9])*$") Integer id)
            throws ResourceNotFoundException, Exception {

        return Response.status(Response.Status.OK).entity(id).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarArtista() throws ResourceNotFoundException, ConflictException, Exception {

        return Response.status(Response.Status.CREATED).entity(null).build();

    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarArtista() throws ResourceNotFoundException, ConflictException, Exception {

        return Response.status(Response.Status.OK).entity(null).build();

    }

    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarArtista(@PathParam("id") @Pattern(regexp = "^([0-9])*$") Integer id)
                                throws ResourceNotFoundException, Exception {

        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
