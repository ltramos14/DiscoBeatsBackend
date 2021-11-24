/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.CompraCancionDto;
import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.entity.CompraCancion;
import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.ICancionRepo;
import co.edu.unicundi.discobeatsejb.repository.ICompraCancionRepo;
import co.edu.unicundi.discobeatsejb.repository.IUsuarioRepo;
import co.edu.unicundi.discobeatsejb.service.ICompraCancionService;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Camilo Preciado
 */
@Stateless
public class CompraCancionServiceImpl implements ICompraCancionService{

    @EJB
    private ICompraCancionRepo repo;
    
    @EJB
    private ICancionRepo cancionrepo;
    
    @EJB
    private IUsuarioRepo usuariorepo;
    
    @Override
    public List<CompraCancion> listarComprasCancion() {
        if (!repo.listarTodos().isEmpty()) {
            return repo.listarTodos();
        } else {
            return null;
        }
    }

    @Override
    public CompraCancion listarComprasCancionPorId(Integer id) throws ResourceNotFoundException{
        Long count = repo.validarExistenciaPorId(id);

        if (count > 0) {
            CompraCancion compra = repo.listarPorId(id);
            return compra;
        } else {
           throw new ResourceNotFoundException("Compra de cancion no encontrado");
        }
    }

    @Override
    public void guardarCompraCanion(CompraCancionDto compraNueva) throws ResourceNotFoundException, LogicBusinessException, ConflictException  {
        if (compraNueva.getId() != null) {
            throw new LogicBusinessException("El id de la compra es asignado automáticamente");
        }
        
        //Validaciones de la cancion
        if (compraNueva.getIdCancion()== null) {
            throw new LogicBusinessException("El id de la cancion el obligatorio");
        }
        
        Long validarExistenciaAlbum = cancionrepo.validarExistenciaPorId(compraNueva.getIdCancion());
        if (validarExistenciaAlbum == 0) {
            throw new ResourceNotFoundException("La cancion no existe");
        }
        
        //Validaciones del usuario
        if (compraNueva.getIdUsuario()== null) {
            throw new LogicBusinessException("El id del usuario el obligatorio");
        }
        
        Long validarExistenciaUsuario = usuariorepo.validarExistenciaPorId(compraNueva.getIdUsuario());
        if (validarExistenciaUsuario == 0) {
            throw new ConflictException("El usuario no existe");
        }
        
        Long validarExistenciaCompra = repo.validarExistenciaCompra(compraNueva.getIdUsuario(), compraNueva.getIdCancion());
        if(validarExistenciaCompra >0){
            throw new ConflictException("Este usuario ya compro esta cancion");
        }
        CompraCancion compraC = convertToEntity(compraNueva);
        repo.guardar(compraC);
    }
    
    private CompraCancion convertToEntity(CompraCancionDto compraCancionDto){
        Cancion cancion = new Cancion();
        Usuario usuario = new Usuario();
        
        cancion.setId(compraCancionDto.getIdCancion());
        usuario.setId(compraCancionDto.getIdUsuario());
        
        CompraCancion compraCEntity = new CompraCancion();
        
        compraCEntity.setUsuario(usuario);
        compraCEntity.setCancion(cancion);
        compraCEntity.setFechaCompra(new Date(new java.util.Date().getTime()));
        compraCEntity.setPrecioTotal(compraCancionDto.getPrecioTotal());
        
        
        return compraCEntity;
    }
    
}
