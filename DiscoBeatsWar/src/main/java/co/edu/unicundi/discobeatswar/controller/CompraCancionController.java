/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatswar.controller;

import co.edu.unicundi.discobeatsejb.dto.CompraAlbumDto;
import co.edu.unicundi.discobeatsejb.dto.CompraCancionDto;
import co.edu.unicundi.discobeatsejb.entity.CompraAlbum;
import co.edu.unicundi.discobeatsejb.entity.CompraCancion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.service.ICompraCancionService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Camilo Preciado
 */
@Stateless
@Path("/comprasC")
public class CompraCancionController {
    @EJB
    ICompraCancionService compraCancionService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComprasCancion() throws Exception {

        List<CompraCancion> listaComprasCancion = this.compraCancionService.listarComprasCancion();

        if (listaComprasCancion == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.OK).entity(listaComprasCancion).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCompraPorId(@PathParam("id") Integer id) throws ResourceNotFoundException {

        CompraCancion compraA = this.compraCancionService.listarComprasCancionPorId(id);

        return Response.status(Response.Status.OK).entity(compraA).build();
    }
    
    @GET
    @Path("/usuarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComprasUsuario(@PathParam("id") Integer id) throws Exception {

        List<CompraCancion> listaComprasUsuarios = this.compraCancionService.obtenerComprasUsuario(id);
        if (listaComprasUsuarios == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(listaComprasUsuarios).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarCompra(CompraCancionDto compraANuevo) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        this.compraCancionService.guardarCompraCanion(compraANuevo);

        return Response.status(Response.Status.CREATED).build();
    }
}
