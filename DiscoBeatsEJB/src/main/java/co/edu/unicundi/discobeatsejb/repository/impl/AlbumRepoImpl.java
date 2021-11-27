package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.repository.IAlbumRepo;
import co.edu.unicundi.discobeatsejb.views.AlbumView;
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
public class AlbumRepoImpl implements IAlbumRepo {

    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;
    
     @Override
    public List<Album> listarTodos() {
        TypedQuery<Album> query = this.em.createNamedQuery("Album.ListarTodos", Album.class);
        return query.getResultList();
    }

    @Override
    public Album listarPorId(Integer id) {
        return this.em.find(Album.class, id);
    }
    @Override
    public void guardar(Album objAlbum) {
       this.em.persist(objAlbum);
    }

    @Override
    public void editar(Album objAlbum) {
        this.em.merge(objAlbum);
    }
    
    @Override
    public void eliminar(Integer id) {
        Query query = this.em.createNamedQuery("Album.EliminarAlbum", Album.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Long validarExistenciaPorId(Integer id) {
        Query query = this.em.createNamedQuery("Album.ContarPorId", Album.class);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long validarExistenciaAlbumDeArtista(String nombreAlbum, Integer idArtista) {
        Query query = this.em.createNamedQuery("Album.ContarPorNombre");
        query.setParameter(1, nombreAlbum);
        query.setParameter(2, idArtista);
        return (Long)query.getSingleResult();
    }

    @Override
    public List<AlbumView> listarAlbumesPorVentas() {
        TypedQuery<AlbumView> query = this.em.createNamedQuery("AlbumView.VentasAlbumes", AlbumView.class);
        return query.getResultList();
    }

    @Override
    public List<Album> obtenerPorId(Integer id) {
        TypedQuery<Album> query = this.em.createNamedQuery("Album.ObtenerPorId", Album.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Album> obtenerAlbumesArtista(Integer id) {
        TypedQuery<Album> query = this.em.createNamedQuery("Album.ObtenerAlbumesArtista", Album.class);
        query.setParameter("idartista", id);
        return query.getResultList();
    }
     
}
