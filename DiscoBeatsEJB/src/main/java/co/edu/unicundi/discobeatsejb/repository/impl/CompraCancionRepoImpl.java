/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository.impl;


import co.edu.unicundi.discobeatsejb.entity.CompraCancion;
import co.edu.unicundi.discobeatsejb.repository.ICompraCancionRepo;
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
public class CompraCancionRepoImpl implements ICompraCancionRepo{

    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;
    
    
    @Override
    public List<CompraCancion> listarTodos() {
        TypedQuery<CompraCancion> query = this.em.createNamedQuery("CompraCancion.ListarTodos", CompraCancion.class);
        return query.getResultList();
    }
    
    @Override
    public Long validarExistenciaPorId(Integer id) {
        Query query = this.em.createNamedQuery("CompraCancion.ContarPorId", CompraCancion.class);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public CompraCancion listarPorId(Integer id) {
        return this.em.find(CompraCancion.class, id);
    }

    @Override
    public void guardar(CompraCancion obj) {
        this.em.persist(obj);
    }

    @Override
    public Long validarExistenciaCompra(Integer idUsuario, Integer idCancion) {
        Query query = this.em.createNamedQuery("CompraCancion.ContarPorUsuario", CompraCancion.class);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("idCancion", idCancion);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<CompraCancion> obtenerComprasUsuario(Integer id) {
        TypedQuery<CompraCancion> query = this.em.createNamedQuery("CompraCancion.ObtenerComprasUsuario", CompraCancion.class);
        query.setParameter("idusuario", id);
        return query.getResultList();
    }
    
}
