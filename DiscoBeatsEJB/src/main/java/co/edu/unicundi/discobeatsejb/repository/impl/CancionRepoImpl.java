package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.repository.ICancionRepo;
import co.edu.unicundi.discobeatsejb.views.CancionView;
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
public class CancionRepoImpl implements ICancionRepo{
    
    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;
    
    @Override
    public List<Cancion> listarTodos() {
        TypedQuery<Cancion> query = this.em.createNamedQuery("Cancion.ListarTodos", Cancion.class);
        return query.getResultList();
    }

    @Override
    public Cancion listarPorId(Integer id) {
        TypedQuery<Cancion> query = this.em.createNamedQuery("Cancion.ObtenerPorId", Cancion.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void guardar(Cancion objCancion) {
        this.em.persist(objCancion);
    }

    @Override
    public void editar(Cancion objCancion) {
        this.em.merge(objCancion);
    }

    @Override
    public void eliminar(Integer id) {
        Query query = this.em.createNamedQuery("Cancion.EliminarPorId", Cancion.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Long validarCancionDeAlbum(String nombreCancion, Integer idAlbum) {
        Query query = this.em.createNamedQuery("Cancion.ContarCancionAlbum");
        query.setParameter(1, nombreCancion);
        query.setParameter(2, idAlbum);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long validarExistenciaPorId(Integer id) {
        Query query = this.em.createNamedQuery("Cancion.ContarPorId", Cancion.class);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<CancionView> listarCancionesPorVentas() {
        TypedQuery<CancionView> query = this.em.createNamedQuery("CancionView.VentasCanciones", CancionView.class);
        return query.getResultList();
    }

    @Override
    public List<Cancion> obtenerPorId(Integer id) {
        TypedQuery<Cancion> query = this.em.createNamedQuery("Cancion.ObtenerPorId", Cancion.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Cancion> obtenerCancionesArtista(Integer id) {
        TypedQuery<Cancion> query = this.em.createNamedQuery("Cancion.ObtenerCancionesArtista", Cancion.class);
        query.setParameter("idartista", id);
        return query.getResultList();
    }

    @Override
    public List<Cancion> obtenerCancionesAlbum(Integer id) {
        TypedQuery<Cancion> query = this.em.createNamedQuery("Cancion.ObtenerCancionesAlbum", Cancion.class);
        query.setParameter("idalbum", id);
        return query.getResultList();
    }

    @Override
    public CancionView ventasCancion(Integer id) {
        return this.em.find(CancionView.class, id);
    }
    
}
