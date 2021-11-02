/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
import co.edu.unicundi.discobeatsejb.repository.ICancionRepo;
import co.edu.unicundi.discobeatsejb.service.ICancionService;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Camilo Preciado
 */
@Stateless
public class CancionServiceImpl implements ICancionService {

    @EJB
    private ICancionRepo repo;
    
    @Override
    public void guardarCancion(Cancion cancionNueva) {
        repo.guardar(cancionNueva);
    }
    
}
