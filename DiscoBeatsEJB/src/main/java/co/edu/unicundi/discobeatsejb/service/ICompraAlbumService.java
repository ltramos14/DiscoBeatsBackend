/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.dto.CompraAlbumDto;
import co.edu.unicundi.discobeatsejb.entity.CompraAlbum;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Local
public interface ICompraAlbumService {
    
    public List<CompraAlbum> listarComprasAlbum();
    
    public CompraAlbum listarComprasAlbumPorId(Integer id) throws ResourceNotFoundException;
    
    public void guardarCompraAlbum(CompraAlbumDto compraNueva) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public List<CompraAlbum> obtenerComprasUsuario(Integer id) throws ResourceNotFoundException;
}
