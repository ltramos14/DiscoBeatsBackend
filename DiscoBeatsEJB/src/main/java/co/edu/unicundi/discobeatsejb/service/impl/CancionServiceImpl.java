/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.ICancionRepo;
import co.edu.unicundi.discobeatsejb.service.ICancionService;
import java.util.List;
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
    public List<Cancion> listarCanciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cancion listarCancionPorId(Integer id) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardarCancion(Cancion cancionNueva) {
        repo.guardar(cancionNueva);
    }
    
    @Override
    public void editarCancion(Cancion artistaEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCancion(Integer id) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
