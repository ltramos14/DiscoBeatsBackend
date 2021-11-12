/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public Cancion listarCancionPorId(Integer id) throws ResourceNotFoundException {
        
        Long count = this.localRepo.validarExistenciaPorId(id);
        if (count > 0) {
            Cancion cancionEntity = localRepo.listarPorId(id);
            return cancionEntity;
        } else {
            throw new ResourceNotFoundException("La canción no fue encontrada");
        }
        
    }

    @Override
    public void guardarCancion(CancionDto cancionNueva) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        if (cancionNueva.getId() != null) {
            throw new LogicBusinessException("El id de la cancion se asigna automáticamente");
        }

        // Validación de artista
        if (cancionNueva.getArtista().getId() == null) {
            throw new LogicBusinessException("El id del artista el obligatorio");
        } 
        Long validarExistenciaArtista = artistaRepo.validarExistenciaPorId(cancionNueva.getArtista().getId());
        if (validarExistenciaArtista < 0) {
            throw new ResourceNotFoundException("El artista no existe");
        }
        
        // Validación de álbum
        if (cancionNueva.getAlbum().getId() == null) {
            throw new LogicBusinessException("El id del album es obligatorio");
        } 
        Long validarExistenciaAlbum = albumRepo.validarExistenciaPorId(cancionNueva.getAlbum().getId());
        if (validarExistenciaAlbum < 0) {
            throw new ResourceNotFoundException("El album no existe");
        }
        
        // Validación de género musical
        if (cancionNueva.getGeneroMusical().getId() == null) {
            throw new LogicBusinessException("El id del album es obligatorio");
        } 
        Long validarExistenciaGenero = generoRepo.validarExistenciaPorId(cancionNueva.getGeneroMusical().getId());
        if (validarExistenciaGenero < 0) {
            throw new ResourceNotFoundException("El album no existe");
        }
        
       Long contarExistenciaPorNombreAlbumDeArtista = localRepo.validarCancionDeAlbum(cancionNueva.getNombre(), cancionNueva.getAlbum().getId());
        
       if(contarExistenciaPorNombreAlbumDeArtista == 0) {
           Cancion cancion = convertToEntity(cancionNueva);
           localRepo.guardar(cancion);
       } else {
           throw new ConflictException("La canción " + cancionNueva.getNombre() + " ya existe en el album con id " + cancionNueva.getAlbum().getId());
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
        
        if(cancionEditada.getArtista() != null) {
            throw new LogicBusinessException("No se puede cambiar el artista de esta canción");
        }
        
        if(cancionEditada.getAlbum()!= null) {
            throw new LogicBusinessException("No se puede cambiar el album de esta canción");
        }
        
        // Validación de género musical
        if (cancionEditada.getGeneroMusical().getId() == null) {
            throw new LogicBusinessException("El id del genero es obligatorio");
        } 
        Long validarExistenciaGenero = generoRepo.validarExistenciaPorId(cancionEditada.getGeneroMusical().getId());
        if (validarExistenciaGenero < 0) {
            throw new ResourceNotFoundException("El genero musical no existe");
        }
        Cancion cancion = this.localRepo.listarPorId(cancionEditada.getId());
        Long contarExistenciaPorNombreAlbumDeArtista = localRepo.validarCancionDeAlbum(cancionEditada.getNombre(), cancion.getAlbum().getId());
        if(contarExistenciaPorNombreAlbumDeArtista == 0) {
            GeneroMusical genero = new GeneroMusical();
            genero.setId(cancionEditada.getGeneroMusical().getId());
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
     
//    private static CancionDto convertirACancionDto(Cancion cancion) {
//        
//        ModelMapper modelMapper = new ModelMapper();
//        
//        PropertyMap<Cancion, CancionDto> orderMap = new PropertyMap<Cancion, CancionDto>() {
//            @Override
//            protected void configure() {
//                skip(destination.getListaCompras());
//                
//            }
//        };
//        modelMapper.addMappings(orderMap);
//        
//        return modelMapper.map(cancion, CancionDto.class);
//        
//    }
//    
//    private static List<CancionDto> convertirAListaCancionDto(List<Cancion> cancionesEntity) {
//        List<CancionDto> listaCancionesDto = new ArrayList<>();
//        for (Cancion c : cancionesEntity) {
//            listaCancionesDto.add(convertirACancionDto(c));
//        }
//        return listaCancionesDto;
//    }
    
    private Cancion convertToEntity(CancionDto cancionDto) {
        
        Artista artiste = new Artista();
        Album album = new Album();
        GeneroMusical genero = new GeneroMusical();
        
        artiste.setId(cancionDto.getArtista().getId());
        album.setId(cancionDto.getAlbum().getId());
        genero.setId(cancionDto.getGeneroMusical().getId());
        
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
    
}
