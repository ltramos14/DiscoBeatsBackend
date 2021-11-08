package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
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
public interface IGeneroMusicalService {
    
    public List<GeneroMusical> listarGenerosMusicales();
    
    public GeneroMusical listarGeneroMusicalPorId(Integer id) throws ResourceNotFoundException;
    
    public void guardarGeneroMusical(GeneroMusical generoMusicalNuevo) throws ConflictException;
    
    public void editarGeneroMusical(GeneroMusical generoMusicalEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException;
    
    public void eliminarGeneroMusical(Integer id) throws ResourceNotFoundException;
}
