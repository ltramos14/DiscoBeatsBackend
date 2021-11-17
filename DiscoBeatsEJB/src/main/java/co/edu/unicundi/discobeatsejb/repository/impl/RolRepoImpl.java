package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Rol;
import co.edu.unicundi.discobeatsejb.repository.IRolRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class RolRepoImpl implements IRolRepo {

    @PersistenceContext(unitName = "conexionPostgresql")
    EntityManager em;
    
    @Override
    public Long validarExistenciaRol(Integer id) {
        Query query = this.em.createNamedQuery("Rol.ValidarRol");
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Rol> obtenerRoles() {
        TypedQuery<Rol> query = this.em.createNamedQuery("Rol.ListarRoles", Rol.class);
        return query.getResultList();
    }

    @Override
    public Rol obtenerRol(Integer idRol) {
        TypedQuery<Rol> query = this.em.createNamedQuery("Rol.ObtenerRol", Rol.class);
        query.setParameter("id", idRol);
        
        return query.getSingleResult();
    }

}
