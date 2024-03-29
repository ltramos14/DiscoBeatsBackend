package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.dto.ArtistaDto;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.Ocupacion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.IArtistaService;
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
@Path("/artistas")
public class ArtistaController {

    @EJB
    IArtistaService artistaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistas() throws Exception {

        List<Artista> listaArtistas = this.artistaService.listarArtistas();

        if (listaArtistas == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(listaArtistas).build();
    }
    

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistaPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {

        List<Artista> artista = this.artistaService.listarArtistaPorId(id);

        return Response.status(Response.Status.OK).entity(artista).build();
    }
    
    
    @GET
    @Path("/ocupaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerOcupaciones() throws Exception {

        List<Ocupacion> listaOcupaciones = this.artistaService.listarOcupaciones();

        if (listaOcupaciones == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(listaOcupaciones).build();
    }
    
    @GET
    @Path("/ocupaciones/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerOcupaciones(@PathParam("id") Integer id) throws Exception {

        Ocupacion ocupacion = this.artistaService.obtenerOcupacionPorId(id);

        if (ocupacion == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(ocupacion).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarArtista(@Valid ArtistaDto artistaNuevo) throws ConflictException, ResourceNotFoundException, LogicBusinessException {

        this.artistaService.guardarArtista(artistaNuevo);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarArtista(ArtistaDto artista) throws ResourceNotFoundException, ConflictException, LogicBusinessException {
        this.artistaService.editarArtista(artista);
        return Response.status(Response.Status.OK).entity(artista).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarArtista(@PathParam("id") Integer id) throws ResourceNotFoundException {
        this.artistaService.eliminarArtista(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
