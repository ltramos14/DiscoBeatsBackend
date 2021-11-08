package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.repository.IGeneroMusicalRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class GeneroMusicalRepoImpl implements IGeneroMusicalRepo {

    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;
    
    @Override
    public List<GeneroMusical> listarTodos() {
       TypedQuery<GeneroMusical> query = this.em.createNamedQuery("GeneroMusical.ListarTodos", GeneroMusical.class);
       return query.getResultList();
    }

    @Override
    public GeneroMusical listarPorId(Integer id) {
       return this.em.find(GeneroMusical.class, id);
    }

    @Override
    public void guardar(GeneroMusical obj) {
      this.em.persist(obj);
    }

    @Override
    public void editar(GeneroMusical obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer id) {
       Query query = this.em.createNamedQuery("GeneroMusical.EliminarGenero", GeneroMusical.class);
       query.setParameter("id", id);
       query.executeUpdate();
    }

    @Override
    public Long validarExistenciaPorId(Integer id) {
       Query query = this.em.createNamedQuery("GeneroMusical.ContarPorId", GeneroMusical.class);
       query.setParameter("id", id);
       return (Long)query.getSingleResult();
    }

    @Override
    public Long validarExistenciaPorNombre(String nombre) {
       Query query = this.em.createNativeQuery("GeneroMusical.ContarPorNombre", GeneroMusical.class);
       return (Long)query.getSingleResult();
    }
    
}
