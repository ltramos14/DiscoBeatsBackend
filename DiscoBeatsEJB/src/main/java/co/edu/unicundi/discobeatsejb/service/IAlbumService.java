package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.dto.AlbumDto;
import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.views.AlbumView;
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
public interface IAlbumService {
    
    public List<Album> listarAlbumes();
    
    public List<AlbumView> listarAlbumesPorVentas();
    
    public AlbumView ventasAlbum(Integer id) throws ResourceNotFoundException;
    
     public List<Album> obtenerPorId(Integer id) throws ResourceNotFoundException;
    
    public List<Album> obtenerAlbumesArtista(Integer id) throws ResourceNotFoundException;
    
    public Album listarAlbumPorId(Integer id) throws ResourceNotFoundException;
    
    public void guardarAlbum(AlbumDto albumNuevo)  throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void editarAlbum(AlbumDto albumEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void eliminarAlbum(Integer id) throws ResourceNotFoundException;
    
}
