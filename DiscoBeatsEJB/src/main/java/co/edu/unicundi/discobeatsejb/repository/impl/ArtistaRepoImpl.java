package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.Ocupacion;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
import co.edu.unicundi.discobeatsejb.repository.IOcupacionRepo;
import co.edu.unicundi.discobeatsejb.views.ArtistaView;
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
    public void editar(Artista objArtista) {
        this.em.merge(objArtista);
    }

    @Override
    public void eliminar(Integer id) {
        Query query = this.em.createNamedQuery("Artista.EliminarArtista", Artista.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    
    @Override
    public Long validarExistenciaPorId(Integer id) {
        Query query = this.em.createNamedQuery("Artista.ContarPorId", Artista.class);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long validarExistenciaPorNombre(String nombre) {
        Query query = this.em.createNamedQuery("Artista.ContarPorNombre", Artista.class);
        query.setParameter(1, nombre);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<ArtistaView> listarArtistasConCanciones() {
        TypedQuery<ArtistaView> query = this.em.createNamedQuery("ArtistaView.ArtistasConCanciones", ArtistaView.class);
        return query.getResultList();
    } 

}
