/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.service.ICancionService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Camilo Preciado
 */

@Stateless
@Path("/canciones")
public class CancionController {
    @EJB
    ICancionService cancionService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarCancion(Cancion cancionNueva) {

        this.cancionService.guardarCancion(cancionNueva);

        return Response.status(Response.Status.CREATED).build();
    }
}
