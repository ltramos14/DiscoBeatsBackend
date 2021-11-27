package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.dto.CancionDto;
import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.ICancionService;
import co.edu.unicundi.discobeatsejb.views.CancionView;
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
@Path("/canciones")
public class CancionController {
    
    @EJB
    ICancionService cancionService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCanciones() {
        List<Cancion> canciones = this.cancionService.listarCanciones();
        if (canciones == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(canciones).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistaPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {
        List<Cancion> cancion = this.cancionService.listarCancionPorId(id);
        return Response.status(Response.Status.OK).entity(cancion).build();
    }
    
    @GET
    @Path("/artista/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCancionesArtista(@PathParam("id") Integer id) throws Exception {

        List<Cancion> listaCancionesArtista = this.cancionService.obtenerCancionesArtista(id);
        if (listaCancionesArtista == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(listaCancionesArtista).build();
    }
    
    @GET
    @Path("/album/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCancionesAlbum(@PathParam("id") Integer id) throws Exception {

        List<Cancion> listaCancionesAlbum = this.cancionService.obtenerCancionesAlbum(id);
        if (listaCancionesAlbum == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(listaCancionesAlbum).build();
    }
    
    @GET
    @Path("/vista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVentasCanciones() throws Exception {

        List<CancionView> listaVentasCanciones = this.cancionService.listarCancionesPorVentas();

        if (listaVentasCanciones == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(listaVentasCanciones).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarCancion(@Valid CancionDto cancionNueva) throws ResourceNotFoundException, LogicBusinessException, ConflictException  {
        this.cancionService.guardarCancion(cancionNueva);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarCancion(@Valid CancionDto cancion) throws ResourceNotFoundException, ConflictException, LogicBusinessException {
        this.cancionService.editarCancion(cancion);
        return Response.status(Response.Status.OK).entity(cancion).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCancion(@PathParam("id") Integer id) throws ResourceNotFoundException {
        this.cancionService.eliminarCancion(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
