package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
import co.edu.unicundi.discobeatsejb.service.IArtistaService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class ArtistaServiceImpl implements IArtistaService {

    @EJB
    private IArtistaRepo repo;
    
    @Override
    public List<Artista> listarArtistas() {
        try {
            return repo.listarTodos();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Artista listarArtistaPorId(Integer id) throws ResourceNotFoundException {

            Artista artista = repo.listarPorId(id);
            
            if (artista != null) {
                return artista;
            } else {
                throw new ResourceNotFoundException("Artista no encontrado");
            }

    }

    @Override
    public void guardarArtista(Artista artistaNuevo) {
        try {
        repo.guardar(artistaNuevo);  
        } catch (Exception ex) {
            throw ex;
    }
    }

    @Override
    public void editarArtista(Artista artistaEditado) throws ResourceNotFoundException {
        if(artistaEditado.getId() != null){
            Artista artista = this.listarArtistaPorId(artistaEditado.getId());
            this.repo.editar(artistaEditado);
        }else{
            // Excepcion 400
        }
    }

    @Override
    public void eliminarArtista(Integer id) throws ResourceNotFoundException {
        Long count = repo.validarExistenciaPorId(id);

        if (count > 0) {
            repo.eliminar(id); 
        } else {
            throw new ResourceNotFoundException("Artista no encontrado");
        }
    }
    
}
