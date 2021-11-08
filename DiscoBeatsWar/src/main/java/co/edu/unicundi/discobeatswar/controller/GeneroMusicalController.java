package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.IGeneroMusicalService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
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
@Path("/generos")
public class GeneroMusicalController {
    
    @EJB
    IGeneroMusicalService generoMusicalService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerGenerosMusicales() throws Exception {

        List<GeneroMusical> listaGeneros = this.generoMusicalService.listarGenerosMusicales();
        if (listaGeneros == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(listaGeneros).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerGeneroMusicalPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {
        GeneroMusical genero = this.generoMusicalService.listarGeneroMusicalPorId(id);
        return Response.status(Response.Status.OK).entity(genero).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarGeneroMusical(@Valid GeneroMusical generoNuevo) throws ConflictException {
        this.generoMusicalService.guardarGeneroMusical(generoNuevo);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarGeneroMusical(GeneroMusical genero) throws ResourceNotFoundException, ConflictException, LogicBusinessException {
        this.generoMusicalService.editarGeneroMusical(genero);
        return Response.status(Response.Status.OK).entity(genero).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarGeneroMusical(@PathParam("id") Integer id) throws ResourceNotFoundException {
        this.generoMusicalService.eliminarGeneroMusical(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
