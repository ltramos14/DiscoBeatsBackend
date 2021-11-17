package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.dto.AuthDto;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.IArtistaService;
import co.edu.unicundi.discobeatsejb.service.IUsuarioService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
@Path("/auth")
public class AuthController {
    
    @EJB
    IUsuarioService authService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Valid AuthDto auth) throws ResourceNotFoundException, LogicBusinessException {

        AuthDto token = this.authService.login(auth);
        return Response.status(Response.Status.OK).entity(token).build();
    }
}
