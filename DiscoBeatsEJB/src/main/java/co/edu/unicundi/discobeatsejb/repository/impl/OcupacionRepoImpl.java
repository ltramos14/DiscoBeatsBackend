/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository.impl;

import co.edu.unicundi.discobeatsejb.entity.Ocupacion;
import co.edu.unicundi.discobeatsejb.repository.IOcupacionRepo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Camilo Preciado
 */
@Stateless
public class OcupacionRepoImpl implements IOcupacionRepo{
    @PersistenceContext(unitName = "conexionPostgresql")
    private EntityManager em;
    
    @Override
    public Long validarExistenciaOcupacionPorId(Integer id) {
        Query query = this.em.createNamedQuery("Ocupacion.ContarPorId", Ocupacion.class);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }
}
