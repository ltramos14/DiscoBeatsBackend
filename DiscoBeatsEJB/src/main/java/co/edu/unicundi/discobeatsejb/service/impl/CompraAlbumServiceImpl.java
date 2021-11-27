/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.CompraAlbumDto;
import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.entity.CompraAlbum;
import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IAlbumRepo;
import co.edu.unicundi.discobeatsejb.repository.ICompraAlbumRepo;
import co.edu.unicundi.discobeatsejb.repository.IUsuarioRepo;
import co.edu.unicundi.discobeatsejb.service.ICompraAlbumService;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Camilo Preciado
 */
@Stateless
public class CompraAlbumServiceImpl implements ICompraAlbumService{

    @EJB
    private ICompraAlbumRepo repo;
    
    @EJB
    private IAlbumRepo albumrepo;
    
    @EJB
    private IUsuarioRepo usuariorepo;

    @Override
    public List<CompraAlbum> listarComprasAlbum() {
        if (!repo.listarTodos().isEmpty()) {
            return repo.listarTodos();
        } else {
            return null;
        }
    }

    @Override
    public CompraAlbum listarComprasAlbumPorId(Integer id) throws ResourceNotFoundException{
        Long count = repo.validarExistenciaPorId(id);

        if (count > 0) {
            CompraAlbum compra = repo.listarPorId(id);
            return compra;
        } else {
            throw new ResourceNotFoundException("Compra de album no encontrado");
        }
    }

    @Override
    public void guardarCompraAlbum(CompraAlbumDto compraNueva)throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        if (compraNueva.getId() != null) {
            throw new LogicBusinessException("El id de la compra es asignado automáticamente");
        }
        
        //Validaciones del album
        if (compraNueva.getIdAlbum()== null) {
            throw new LogicBusinessException("El id del album el obligatorio");
        }     
        
        Long validarExistenciaAlbum = albumrepo.validarExistenciaPorId(compraNueva.getIdAlbum());
        if (validarExistenciaAlbum == 0) {
            throw new ResourceNotFoundException("El album no existe");
        }
        
        //Validaciones del usuario
        if (compraNueva.getIdUsuario()== null) {
            throw new LogicBusinessException("El id del usuario el obligatorio");
        }
        
        Long validarExistenciaUsuario = usuariorepo.validarExistenciaPorId(compraNueva.getIdUsuario());
        if (validarExistenciaUsuario == 0) {
            throw new ConflictException("El usuario no existe");
        }
        
        Long validarExistenciaCompra = repo.validarExistenciaCompra(compraNueva.getIdUsuario(), compraNueva.getIdAlbum());
        if(validarExistenciaCompra >0){
            throw new ResourceNotFoundException("Este usuario ya compro este album");
        }
        CompraAlbum compraA = convertToEntity(compraNueva);

        repo.guardar(compraA);
    }

    
     private CompraAlbum convertToEntity(CompraAlbumDto compraAlbumDto){
        Album album = new Album();
        Usuario usuario = new Usuario();
        
        album.setId(compraAlbumDto.getIdAlbum());
        usuario.setId(compraAlbumDto.getIdUsuario());
        
        CompraAlbum compraAEntity = new CompraAlbum();
        
        compraAEntity.setAlbum(album);
        compraAEntity.setUsuario(usuario);        
        compraAEntity.setPrecioTotal(compraAlbumDto.getPrecioTotal());
        
        
        compraAEntity.setFechaCompra(new Date(new java.util.Date().getTime()));
        
        return compraAEntity;
    }

    @Override
    public List<CompraAlbum> obtenerComprasUsuario(Integer idUsuario) throws ResourceNotFoundException {
        Long validarUsuario = usuariorepo.validarExistenciaPorId(idUsuario);
        if(validarUsuario==0){
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        List<CompraAlbum> comprasUsuario = this.repo.obtenerComprasUsuario(idUsuario);
        if (!comprasUsuario.isEmpty()) {
            return comprasUsuario;
        }
        return null;
    }

    
}
