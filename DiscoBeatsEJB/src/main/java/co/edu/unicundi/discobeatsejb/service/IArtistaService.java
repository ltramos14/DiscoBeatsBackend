package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.entity.Artista;
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
public interface IArtistaService {
    
    public List<Artista> listarArtistas();
    
    public Artista listarArtistaPorId(Integer id) throws ResourceNotFoundException;
    
    public void guardarArtista(Artista artistaNuevo);
    
    public void editarArtista(Artista artistaEditado) throws ResourceNotFoundException;
    
    public void eliminarArtista(Integer id) throws ResourceNotFoundException;
    
}
