/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.dto.CancionDto;
import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.views.CancionView;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Local
public interface ICancionService {
    
    public List<Cancion> listarCanciones();
    
    public List<CancionView> listarCancionesPorVentas();
    
    public CancionView ventasCancion(Integer id) throws ResourceNotFoundException;
    
    public List<Cancion> listarCancionPorId(Integer id) throws ResourceNotFoundException;
    
    public List<Cancion> obtenerCancionesArtista(Integer id) throws ResourceNotFoundException;
    
    public List<Cancion> obtenerCancionesAlbum(Integer id) throws ResourceNotFoundException;   
    
    public void guardarCancion(CancionDto cancionNueva) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void editarCancion(CancionDto artistaEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void eliminarCancion(Integer id) throws ResourceNotFoundException;
}
