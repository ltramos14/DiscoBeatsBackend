package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.dto.AuthDto;
import co.edu.unicundi.discobeatsejb.dto.UsuarioDto;
import co.edu.unicundi.discobeatsejb.entity.Rol;
import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.IUsuarioService;
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
@Path("/usuarios")
public class UsuarioController {
    
    @EJB
    private IUsuarioService usuarioService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuarios() throws Exception {

        List<Usuario> listaUsuarios = this.usuarioService.listarUsuarios();
        if (listaUsuarios == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(listaUsuarios).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuariosPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {
        List<Usuario> usuario = this.usuarioService.obtenerUsuarioPorId(id);
        return Response.status(Response.Status.OK).entity(usuario).build();
    }
    
    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRoles() {
        List<Rol> roles = this.usuarioService.obtenerRoles();
        if (roles == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(roles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarUsuario(@Valid UsuarioDto usuarioNuevo) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        this.usuarioService.registrarUsuario(usuarioNuevo);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarUsuario(@Valid UsuarioDto usuario) throws ResourceNotFoundException, ConflictException, LogicBusinessException {
        AuthDto relogin = this.usuarioService.editarUsuario(usuario);
        
        return Response.status(Response.Status.OK).entity(relogin).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("id") Integer id) throws ResourceNotFoundException {
        this.usuarioService.eliminarUsuario(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
