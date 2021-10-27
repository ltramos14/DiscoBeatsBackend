package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
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
public class ArtistaRepoImpl implements IArtistaRepo{
    
    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;

    @Override
    public List<Artista> listarTodos() {
        TypedQuery<Artista> query = this.em.createNamedQuery("Artista.ListarTodos", Artista.class);
        return query.getResultList();
    }

    @Override
    public Artista listarPorId(Integer id) {
        return this.em.find(Artista.class, id);
    }

    @Override
    public void guardar(Artista objArtista) {
        this.em.persist(objArtista);
    }

    @Override
    public void editar(Artista obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*public int contarPorNombreArtistico(String nombre) {
        Query query = this.em.createNamedQuery("Artista.ContarPorNombre", Artista.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult().intV;
    }*/

}
