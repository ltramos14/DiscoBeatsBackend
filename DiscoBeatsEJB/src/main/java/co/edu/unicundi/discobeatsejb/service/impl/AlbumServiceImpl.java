package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.AlbumDto;
import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IAlbumRepo;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
import co.edu.unicundi.discobeatsejb.repository.IGeneroMusicalRepo;
import co.edu.unicundi.discobeatsejb.service.IAlbumService;
import co.edu.unicundi.discobeatsejb.views.AlbumView;
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
public class AlbumServiceImpl implements IAlbumService {

    @EJB
    private IAlbumRepo localRepo;

    @EJB
    private IArtistaRepo artistaRepo;

    @EJB
    private IGeneroMusicalRepo generoRepo;

    @Override
    public List<Album> listarAlbumes() {

        List<Album> listaAlbumes = this.localRepo.listarTodos();
        if (!listaAlbumes.isEmpty()) {
            return listaAlbumes;
        }
        return null;
    }

    @Override
    public Album listarAlbumPorId(Integer id) throws ResourceNotFoundException {

        Long count = this.localRepo.validarExistenciaPorId(id);
        if (count > 0) {
            Album albumEntity = localRepo.listarPorId(id);
            return albumEntity;
        } else {
            throw new ResourceNotFoundException("El album no fue encontrado");
        }
    }

    @Override
    public void guardarAlbum(AlbumDto albumNuevo) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        if (albumNuevo.getId() != null) {
            throw new LogicBusinessException("El id de la album es asignado automáticamente");
        }

        // Validación de Artista
        if (albumNuevo.getIdArtista() == null) {
            throw new LogicBusinessException("El id del artista el obligatorio");
        }

        Long validarExistenciaArtista = artistaRepo.validarExistenciaPorId(albumNuevo.getIdArtista());
        if (validarExistenciaArtista == 0) {
            throw new ResourceNotFoundException("El artista no existe");
        }

        // Validación de género musical
        if (albumNuevo.getIdGeneroMusical() == null) {
            throw new LogicBusinessException("El id del género musical es obligatorio");
        }
        Long validarExistenciaGenero = generoRepo.validarExistenciaPorId(albumNuevo.getIdGeneroMusical());
        if (validarExistenciaGenero == 0) {
            throw new ResourceNotFoundException("El género musical no existe");
        }
        
        Artista artistaAlbum = artistaRepo.listarPorId(albumNuevo.getIdArtista());
        Long contarExistenciaPorNombreAlbumDeArtista = localRepo.validarExistenciaAlbumDeArtista(albumNuevo.getNombre(), albumNuevo.getIdArtista());

        if (contarExistenciaPorNombreAlbumDeArtista == 0) {
            Album album = convertToEntity(albumNuevo);
            localRepo.guardar(album);
        } else {
            throw new ConflictException("El artista " + artistaAlbum.getNombreArtistico() + " ya cuenta con un album denominado " + albumNuevo.getNombre());
        }

    }

    @Override
    public void editarAlbum(AlbumDto albumEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        //Validacion del id
        if (albumEditado.getId() == null) {
            throw new LogicBusinessException("El id del album a editar es obligatorio");
        }

        // Validación de existencia del album en la de base de datos
        Long validarExistenciaAlbum = localRepo.validarExistenciaPorId(albumEditado.getId());
        if (validarExistenciaAlbum == 0) {
            throw new ResourceNotFoundException("El album no existe");
        }

        if (albumEditado.getIdArtista() != null) {
            throw new LogicBusinessException("No se puede cambiar el artista de ese album");
        }

        // Validación de género musical
        if (albumEditado.getIdGeneroMusical() == null) {
            throw new LogicBusinessException("El id del genero musical es obligatorio");
        }
       
        Long validarExistenciaGenero = generoRepo.validarExistenciaPorId(albumEditado.getIdGeneroMusical());
        
        if (validarExistenciaGenero == 0) {
            throw new ResourceNotFoundException("El genero musical no existe");
        }
        
        Album album = this.localRepo.listarPorId(albumEditado.getId()); 
        Long contarExistenciaPorNombreAlbumDeArtista = localRepo.validarExistenciaAlbumDeArtista(albumEditado.getNombre(), album.getArtista().getId());

        if (contarExistenciaPorNombreAlbumDeArtista == 0 || albumEditado.getNombre().equalsIgnoreCase(album.getNombre())) {
            GeneroMusical genero = new GeneroMusical();
            genero.setId(albumEditado.getIdGeneroMusical());
            album.setGeneroMusical(genero);
            album.setNombre(albumEditado.getNombre());
            album.setDescripcion(albumEditado.getDescripcion());
            album.setFechaLanzamiento(albumEditado.getFechaLanzamiento());
            album.setPrecio(albumEditado.getPrecio());
            album.setImagen(albumEditado.getImagen());
            localRepo.editar(album);
        } else {
            throw new ConflictException("El artista " + album.getArtista().getNombreArtistico() + " ya cuenta con un album denominado " + albumEditado.getNombre());
        }
    }

    @Override
    public void eliminarAlbum(Integer id) throws ResourceNotFoundException {

        Long count = localRepo.validarExistenciaPorId(id);
        if (count > 0) {
            localRepo.eliminar(id);
        } else {
            throw new ResourceNotFoundException("Album no encontrado");
        }
    }

    private Album convertToEntity(AlbumDto albumDto) {

        Artista artista = new Artista();
        GeneroMusical genero = new GeneroMusical();

        artista.setId(albumDto.getIdArtista());
        genero.setId(albumDto.getIdGeneroMusical());

        Album albumEntity = new Album();

        albumEntity.setNombre(albumDto.getNombre());
        albumEntity.setDescripcion(albumDto.getDescripcion());
        albumEntity.setFechaLanzamiento(albumDto.getFechaLanzamiento());
        albumEntity.setPrecio(albumDto.getPrecio());
        albumEntity.setImagen(albumDto.getImagen());
        albumEntity.setArtista(artista);
        albumEntity.setGeneroMusical(genero);

        return albumEntity;
    }
    
    @Override
    public List<AlbumView> listarAlbumesPorVentas(){
        if (!localRepo.listarAlbumesPorVentas().isEmpty()) {
            return localRepo.listarAlbumesPorVentas();
        } else {
            return null;
        }
    }

    @Override
    public List<Album> obtenerPorId(Integer id) throws ResourceNotFoundException{
        Long validarAlbum = localRepo.validarExistenciaPorId(id);
        
        if(validarAlbum==0){
            throw new ResourceNotFoundException("Album no encontrado");
        }
        
        List<Album> album = this.localRepo.obtenerPorId(id);
        if (!album.isEmpty()) {
            return album;
        }
        return null;
    }

    @Override
    public List<Album> obtenerAlbumesArtista(Integer idArtista) throws ResourceNotFoundException{
        Long validarArtista = artistaRepo.validarExistenciaPorId(idArtista);
        if(validarArtista==0){
            throw new ResourceNotFoundException("Artista no encontrado");
        }
        List<Album> albumesArtista = this.localRepo.obtenerAlbumesArtista(idArtista);
        if (!albumesArtista.isEmpty()) {
            return albumesArtista;
        }
        return null;
    }

    @Override
    public AlbumView ventasAlbum(Integer id) throws ResourceNotFoundException {
        Long count = this.localRepo.validarExistenciaPorId(id);
        if (count > 0) {
            AlbumView albumview = localRepo.ventasAlbum(id);
            return albumview;
        } else {
            throw new ResourceNotFoundException("El album no fue encontrado");
        }
    }
}
