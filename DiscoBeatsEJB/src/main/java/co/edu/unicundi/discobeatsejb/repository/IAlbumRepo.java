package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Album;
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
public interface IAlbumRepo extends ICrud<Album, Integer> {
    
    public Long validarExistenciaPorId(Integer id);
    
    public Long validarExistenciaAlbumDeArtista(String nombreAlbum, Integer idArtista);
    
}
