package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.entity.Artista;
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
    
    public void listarArtistas();
    
    public void listarArtistaPorId();
    
    public void guardarArtista();
    
    public void editarArtista();
    
    public void eliminarArtista();
    
}
