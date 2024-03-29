package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.CancionDto;
import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IAlbumRepo;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
import co.edu.unicundi.discobeatsejb.repository.ICancionRepo;
import co.edu.unicundi.discobeatsejb.repository.IGeneroMusicalRepo;
import co.edu.unicundi.discobeatsejb.service.ICancionService;
import co.edu.unicundi.discobeatsejb.views.CancionView;
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
public class CancionServiceImpl implements ICancionService {

    @EJB
    private ICancionRepo localRepo;
    
    @EJB
    private IArtistaRepo artistaRepo;
    
    @EJB
    private IAlbumRepo albumRepo;
    
    @EJB
    private IGeneroMusicalRepo generoRepo;
    
    @Override
    public List<Cancion> listarCanciones() {
        
        List<Cancion> listaCanciones = this.localRepo.listarTodos();
        if (!listaCanciones.isEmpty()) {
            return listaCanciones;
        }
        return null;
        
    }

    @Override
    public List<Cancion> listarCancionPorId(Integer id) throws ResourceNotFoundException {
        
       Long validarCancion = localRepo.validarExistenciaPorId(id);
        
        if(validarCancion==0){
            throw new ResourceNotFoundException("Cancion no encontrada");
        }
        
        List<Cancion> cancion = this.localRepo.obtenerPorId(id);
        if (!cancion.isEmpty()) {
            return cancion;
        }
        return null;
        
    }

    @Override
    public void guardarCancion(CancionDto cancionNueva) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        if (cancionNueva.getId() != null) {
            throw new LogicBusinessException("El id de la cancion se asigna automáticamente");
        }

        // Validación de artista
        if (cancionNueva.getIdArtista() == null) {
            throw new LogicBusinessException("El id del artista el obligatorio");
        } 
        
        Long validarExistenciaArtista = artistaRepo.validarExistenciaPorId(cancionNueva.getIdArtista());
        if (validarExistenciaArtista == 0) {
            throw new ResourceNotFoundException("El artista no existe");
        }
        
        // Validación de álbum
        if (cancionNueva.getIdAlbum() == null) {
            throw new LogicBusinessException("El id del album es obligatorio");
        } 
        
        Long validarExistenciaAlbum = albumRepo.validarExistenciaPorId(cancionNueva.getIdAlbum());
        if (validarExistenciaAlbum == 0) {
            throw new ResourceNotFoundException("El album no existe");
        }
        
        // Validación de género musical
        if (cancionNueva.getIdGeneroMusical() == null) {
            throw new LogicBusinessException("El id del género musical es obligatorio");
        } 
        Long validarExistenciaGenero = generoRepo.validarExistenciaPorId(cancionNueva.getIdGeneroMusical());
        if (validarExistenciaGenero == 0) {
            throw new ResourceNotFoundException("El género musical no existe");
        }
        
       Long contarExistenciaPorNombreAlbumDeArtista = localRepo.validarCancionDeAlbum(cancionNueva.getNombre(), cancionNueva.getIdAlbum());
        
       if(contarExistenciaPorNombreAlbumDeArtista == 0) {
           Cancion cancion = convertToEntity(cancionNueva);
           localRepo.guardar(cancion);
       } else {
           throw new ConflictException("La canción " + cancionNueva.getNombre() + " ya existe en el album con id " + cancionNueva.getIdAlbum());
       }  
       
    }
    
    @Override
    public void editarCancion(CancionDto cancionEditada) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
       
        //Validacion del id
        if(cancionEditada.getId() == null) {
            throw new LogicBusinessException("El id de la canción a editar es obligatorio");
        }
        
        // Validación de existencia de cancion de base de datos
        Long validarExistenciaCancion = localRepo.validarExistenciaPorId(cancionEditada.getId());
        if (validarExistenciaCancion == 0) {
            throw new ResourceNotFoundException("La cancion no existe");
        }
        
        if(cancionEditada.getIdArtista() != null) {
            throw new LogicBusinessException("No se puede cambiar el artista de esta canción");
        }
        
        if(cancionEditada.getIdAlbum()!= null) {
            throw new LogicBusinessException("No se puede cambiar el album de esta canción");
        }
        
        // Validación de género musical
        if (cancionEditada.getIdGeneroMusical() == null) {
            throw new LogicBusinessException("El id del genero es obligatorio");
        } 
        Long validarExistenciaGenero = generoRepo.validarExistenciaPorId(cancionEditada.getIdGeneroMusical());
        if (validarExistenciaGenero == 0) {
            throw new ResourceNotFoundException("El genero musical no existe");
        }
       
        Cancion cancion = this.localRepo.listarPorId(cancionEditada.getId());
        Long contarExistenciaPorNombreAlbumDeArtista = localRepo.validarCancionDeAlbum(cancionEditada.getNombre(), cancion.getAlbum().getId());
        if(contarExistenciaPorNombreAlbumDeArtista == 0 || cancionEditada.getNombre().equalsIgnoreCase(cancion.getNombre())) {
            GeneroMusical genero = new GeneroMusical();
            genero.setId(cancionEditada.getIdGeneroMusical());
            cancion.setGeneroMusical(genero);
            cancion.setNombre(cancionEditada.getNombre());
            cancion.setDuracion(cancionEditada.getDuracion());
            cancion.setReproducciones(cancionEditada.getReproducciones());
            cancion.setFechaLanzamiento(cancionEditada.getFechaLanzamiento());
            cancion.setPrecio(cancionEditada.getPrecio());
            cancion.setImagen(cancionEditada.getImagen());
            localRepo.editar(cancion);
       } else {
           throw new ConflictException("La canción " + cancionEditada.getNombre() + " ya existe en el album con id " + cancion.getAlbum().getId());
       }  
     
    }

    @Override
    public void eliminarCancion(Integer id) throws ResourceNotFoundException {
        
        Long count = localRepo.validarExistenciaPorId(id);
        if (count > 0) {
            localRepo.eliminar(id);
        } else {
            throw new ResourceNotFoundException("Canción no encontrada");
        }
        
    }
     
    private Cancion convertToEntity(CancionDto cancionDto) {
        
        Artista artiste = new Artista();
        Album album = new Album();
        GeneroMusical genero = new GeneroMusical();
        
        artiste.setId(cancionDto.getIdArtista());
        album.setId(cancionDto.getIdAlbum());
        genero.setId(cancionDto.getIdGeneroMusical());
        
        Cancion cancionEntity = new Cancion();
        cancionEntity.setNombre(cancionDto.getNombre());
        cancionEntity.setDuracion(cancionDto.getDuracion());
        cancionEntity.setFechaLanzamiento(cancionDto.getFechaLanzamiento());
        cancionEntity.setPrecio(cancionDto.getPrecio());
        cancionEntity.setReproducciones(cancionDto.getReproducciones());
        cancionEntity.setImagen(cancionDto.getImagen());
        cancionEntity.setArtista(artiste);
        cancionEntity.setAlbum(album);
        cancionEntity.setGeneroMusical(genero);
        
        return cancionEntity;
    }

    @Override
    public List<CancionView> listarCancionesPorVentas() {
        if (!localRepo.listarCancionesPorVentas().isEmpty()) {
            return localRepo.listarCancionesPorVentas();
        } else {
            return null;
        }
    }

    @Override
    public List<Cancion> obtenerCancionesArtista(Integer idArtista) throws ResourceNotFoundException {
        Long validarArtista = artistaRepo.validarExistenciaPorId(idArtista);
        if(validarArtista==0){
            throw new ResourceNotFoundException("Artista no encontrado");
        }
        List<Cancion> cancionesArtista = this.localRepo.obtenerCancionesArtista(idArtista);
        if (!cancionesArtista.isEmpty()) {
            return cancionesArtista;
        }
        return null;
    }

    @Override
    public List<Cancion> obtenerCancionesAlbum(Integer idAlbum) throws ResourceNotFoundException {
        Long validarAlbum = albumRepo.validarExistenciaPorId(idAlbum);
        if(validarAlbum==0){
            throw new ResourceNotFoundException("Album no encontrado");
        }
        List<Cancion> cancionesAlbum = this.localRepo.obtenerCancionesAlbum(idAlbum);
        if (!cancionesAlbum.isEmpty()) {
            return cancionesAlbum;
        }
        return null;
    }

    @Override
    public CancionView ventasCancion(Integer id) throws ResourceNotFoundException {
        Long validarCancion = localRepo.validarExistenciaPorId(id);
        if (validarCancion == 0)
            throw new ResourceNotFoundException("La canción no existe");
        
        return localRepo.ventasCancion(id);
    }
    
}
