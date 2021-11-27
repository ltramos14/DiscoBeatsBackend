/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.CompraAlbum;
import co.edu.unicundi.discobeatsejb.repository.ICompraAlbumRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CompraAlbumRepoImpl implements ICompraAlbumRepo{

    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;

    @Override
    public List<CompraAlbum> listarTodos() {
        TypedQuery<CompraAlbum> query = this.em.createNamedQuery("CompraAlbum.ListarTodos", CompraAlbum.class);
        return query.getResultList();
    }    

    @Override
    public Long validarExistenciaPorId(Integer id) {
        Query query = this.em.createNamedQuery("CompraAlbum.ContarPorId", CompraAlbum.class);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public CompraAlbum listarPorId(Integer id) {
        return this.em.find(CompraAlbum.class, id);
    }

    @Override
    public void guardar(CompraAlbum objCompra) {
          this.em.persist(objCompra);
    }

    @Override
    public Long validarExistenciaCompra(Integer idUsuario, Integer idAlbum) {
        Query query = this.em.createNamedQuery("CompraAlbum.ContarPorUsuario", CompraAlbum.class);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("idAlbum", idAlbum);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<CompraAlbum> obtenerComprasUsuario(Integer id) {
        TypedQuery<CompraAlbum> query = this.em.createNamedQuery("CompraAlbum.ObtenerComprasUsuario", CompraAlbum.class);
        query.setParameter("idusuario", id);
        return query.getResultList();
    }
 
}
