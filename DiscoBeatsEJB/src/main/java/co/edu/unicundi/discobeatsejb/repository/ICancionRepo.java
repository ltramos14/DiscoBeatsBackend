package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.views.CancionView;
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
public interface ICancionRepo extends ICrud<Cancion, Integer>{
    
    public Long validarCancionDeAlbum(String nombreCancion, Integer idAlbum);
    
    public Long validarExistenciaPorId(Integer id);
    
    public List<CancionView> listarCancionesPorVentas();
    
}
