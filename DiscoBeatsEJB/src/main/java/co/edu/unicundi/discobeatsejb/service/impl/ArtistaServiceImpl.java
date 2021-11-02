package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
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

        if (!repo.listarTodos().isEmpty()) {
            return repo.listarTodos();
        } else {
            return null;
        }
    }

    @Override
    public Artista listarArtistaPorId(Integer id) throws ResourceNotFoundException {

        Long count = repo.validarExistenciaPorId(id);

        if (count > 0) {
            Artista artista = repo.listarPorId(id);
            return artista;
        } else {
            throw new ResourceNotFoundException("Artista no encontrado");
        }
    }

    @Override
    public void guardarArtista(Artista artistaNuevo) throws ConflictException {
        Long count = repo.validarExistenciaPorNombre(artistaNuevo.getNombreArtistico());
        if (count == 0) {
            repo.guardar(artistaNuevo);
        } else {
            throw new ConflictException("Ya existe un artista con el nombre: " + artistaNuevo.getNombreArtistico());
        }
    }

    @Override
    public void editarArtista(Artista artistaEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        if (artistaEditado.getId() != null) {
            Long count = repo.validarExistenciaPorId(artistaEditado.getId());
            if (count > 0) {
                Artista artista = this.listarArtistaPorId(artistaEditado.getId());
                if (artistaEditado.getId().equals(artista.getId())) {
                    count = repo.validarExistenciaPorNombre(artistaEditado.getNombreArtistico());
                    if (count == 0 || artistaEditado.getNombreArtistico().equalsIgnoreCase(artista.getNombreArtistico())) {
                        this.repo.editar(artistaEditado);
                    } else {
                        throw new ConflictException("Ya existe un artista con el nombre: " + artistaEditado.getNombreArtistico());
                    }
                } else {
                    throw new LogicBusinessException("El id enviado es diferente al id del artista que va a editar");
                }
            } else {
                throw new ResourceNotFoundException("Artista no encontrado");
            }
        } else {
            throw new LogicBusinessException("El id del artista no puede ser nulo");
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
