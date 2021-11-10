package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.AlbumDto;
import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IAlbumRepo;
import co.edu.unicundi.discobeatsejb.service.IAlbumService;
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
public class AlbumServiceImpl implements IAlbumService{

    @EJB
    private IAlbumRepo repo;
    
    @Override
    public List<Album> listarAlbumes() {
        if (!repo.listarTodos().isEmpty()) {
            return repo.listarTodos();
        } else {
            return null;
        }
    }

    @Override
    public Album listarAlbumPorId(Integer id) throws ResourceNotFoundException {
        Long count = repo.validarExistenciaPorId(id);

        if (count > 0) {
            Album album = repo.listarPorId(id);
            return album;
        } else {
            throw new ResourceNotFoundException("El album no fue encontrado");
        }
    }
    
    @Override
     public void guardarAlbum(AlbumDto albumNuevo) throws ConflictException {
      
       Artista artista = new Artista();
       artista.setId(albumNuevo.getIdArtista());
       
       GeneroMusical genero = new GeneroMusical();
       genero.setId(albumNuevo.getIdGeneroMusical());
       
       Album album = new Album();
       
       album.setArtista(artista);
       album.setGeneroMusical(genero);
       album.setNombre(albumNuevo.getNombre());
       album.setDescripcion(albumNuevo.getDescripcion());
       album.setFechaLanzamiento(albumNuevo.getFechaLanzamiento());
       album.setImagen(albumNuevo.getImagen());
       album.setPrecio(albumNuevo.getPrecio());

       Long contarExistenciaPorNombreAlbumDeArtista = repo.validarExistenciaAlbumDeArtista(albumNuevo.getIdArtista(), albumNuevo.getNombre());
       
       if(contarExistenciaPorNombreAlbumDeArtista == 0) {
           repo.guardar(album);
       } else {
           throw new ConflictException("El artista " + artista.getNombreArtistico() + "ya tiene un album llamado" + albumNuevo.getNombre());
       }   
    }

    @Override
    public void editarAlbum(Album albumEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
            
       Album album = listarAlbumPorId(albumEditado.getId());
       
       album.setNombre(albumEditado.getNombre());
       album.setDescripcion(albumEditado.getDescripcion());
       album.setFechaLanzamiento(albumEditado.getFechaLanzamiento());
       album.setImagen(albumEditado.getImagen());
       album.setPrecio(albumEditado.getPrecio());   
        
        if (albumEditado.getId() != null) {
            Long count = repo.validarExistenciaPorId(albumEditado.getId());
            if (count > 0) {
                Album albumDB = this.listarAlbumPorId(albumEditado.getId());
                AlbumDto albumDto = new AlbumDto();
                if (albumEditado.getId().equals(albumDB.getId())) {
                    count = repo.validarExistenciaAlbumDeArtista(albumDto.getIdArtista(), albumEditado.getNombre());
                    if (count == 0 || albumEditado.getNombre().equalsIgnoreCase(albumDB.getNombre())) {
                        this.repo.editar(album);
                    } else {
                        throw new ConflictException("El artista ya tiene un album llamado " + albumEditado.getNombre());
                    }
                } else {
                    throw new LogicBusinessException("El id enviado es diferente al id del album que va a editar");
                }
            } else {
                throw new ResourceNotFoundException("Album no encontrado");
            }
        } else {
            throw new LogicBusinessException("El id del album no puede ser nulo");
        }    
    }

    @Override
    public void eliminarAlbum(Integer id) throws ResourceNotFoundException {
       Long count = repo.validarExistenciaPorId(id);

        if (count > 0) {
            repo.eliminar(id);
        } else {
            throw new ResourceNotFoundException("Album no encontrado");
        }
    }
    
}
