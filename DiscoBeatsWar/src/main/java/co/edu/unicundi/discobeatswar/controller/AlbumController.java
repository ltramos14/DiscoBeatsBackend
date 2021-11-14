package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.dto.AlbumDto;
import co.edu.unicundi.discobeatsejb.dto.CancionDto;
import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.IAlbumService;
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
@Path("/albumes")
public class AlbumController {
    
    @EJB
    IAlbumService albumService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAlbumes() throws Exception {

        List<Album> listaAlbumes = this.albumService.listarAlbumes();
        if (listaAlbumes == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(listaAlbumes).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAlbumPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {
        
        Album album = this.albumService.listarAlbumPorId(id);    
        return Response.status(Response.Status.OK).entity(album).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarAlbum(@Valid AlbumDto albumNuevo) throws ResourceNotFoundException, LogicBusinessException, ConflictException  {
        this.albumService.guardarAlbum(albumNuevo);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarCancion(@Valid AlbumDto album) throws ResourceNotFoundException, ConflictException, LogicBusinessException {
        this.albumService.editarAlbum(album);
        return Response.status(Response.Status.OK).entity(album).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAlbum(@PathParam("id") Integer id) throws ResourceNotFoundException {
        this.albumService.eliminarAlbum(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

