/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.repository.ICancionRepo;
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
        TypedQuery<Cancion> query = this.em.createNamedQuery("Cancion.ListarTodas", Cancion.class);
        return query.getResultList();
    }

    @Override
    public Cancion listarPorId(Integer id) {
        return this.em.find(Cancion.class, id);
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
    
}
