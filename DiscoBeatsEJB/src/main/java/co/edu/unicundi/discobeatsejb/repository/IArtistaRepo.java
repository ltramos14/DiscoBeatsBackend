package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Artista;
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
public interface IArtistaRepo extends ICrud<Artista, Integer> {

    public Long validarExistenciaPorId(Integer id);
    
    public Long validarExistenciaPorNombre(String nombre);
    
}
