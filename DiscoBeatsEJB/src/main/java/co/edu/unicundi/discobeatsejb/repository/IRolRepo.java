package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Rol;
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
public interface IRolRepo {
    
    public List<Rol> obtenerRoles();
    
    public Long validarExistenciaRol(Integer id);
    
    public Rol obtenerRol(Integer idRol);
    
}
