package co.edu.unicundi.discobeatsejb.service.impl;

import co.edu.unicundi.discobeatsejb.dto.ArtistaDto;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.entity.Ocupacion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IArtistaRepo;
import co.edu.unicundi.discobeatsejb.repository.IGeneroMusicalRepo;
import co.edu.unicundi.discobeatsejb.repository.IOcupacionRepo;
import co.edu.unicundi.discobeatsejb.service.IArtistaService;
import java.sql.Date;
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
    
    @EJB
    private IOcupacionRepo ocupacionrepo;
    
    @EJB
    private IGeneroMusicalRepo generorepo;

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
    public void guardarArtista(ArtistaDto artistaNuevo) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
        
        if (artistaNuevo.getId() != null) {
            throw new LogicBusinessException("El id del artista es asignado automáticamente");
        }
        
       //Ocupacion
       Long validarExistenciaOcupacion = ocupacionrepo.validarExistenciaOcupacionPorId(artistaNuevo.getIdOcupacion());
        if (validarExistenciaOcupacion == 0) {
            throw new ResourceNotFoundException("La ocupacion no existe");
        } 
       
        if (artistaNuevo.getIdOcupacion()== null) {
            throw new LogicBusinessException("El id de la ocupacion es obligatoria");
        }
       
        //Genero
        Long validarExistenciaGenero = generorepo.validarExistenciaPorId(artistaNuevo.getIdGeneroMusical());
        if (validarExistenciaGenero == 0) {
            throw new ResourceNotFoundException("El genero musical no existe");
        } 
       
        if (artistaNuevo.getIdGeneroMusical() == null) {
            throw new LogicBusinessException("El id del género musical es obligatorio");
        }
        
        Long contarexistenciaArtista = repo.validarExistenciaPorNombre(artistaNuevo.getNombreArtistico());
        if (contarexistenciaArtista == 0) {
            Artista artista = convertToEntity(artistaNuevo);
            repo.guardar(artista);
        } else {
            throw new ConflictException("Ya existe un artista con el nombre: " + artistaNuevo.getNombreArtistico());
        }
    }

    @Override
    public void editarArtista(ArtistaDto artistaEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {

        Long contId = repo.validarExistenciaPorId(artistaEditado.getId());
        if(contId == 0){
            throw new ResourceNotFoundException("EL id del artista no existe");
        }
        
        if (artistaEditado.getId() == null) {
         throw new LogicBusinessException("El id del artista a editar es obligatorio");
        }
        
        Long validarExistenciaOcupacion = ocupacionrepo.validarExistenciaOcupacionPorId(artistaEditado.getIdOcupacion());
        if (validarExistenciaOcupacion == 0) {
            throw new ResourceNotFoundException("La ocupacion no existe");
        } 
       
        if (artistaEditado.getIdOcupacion()== null) {
            throw new LogicBusinessException("El id de la ocupacion es obligatoria");
        }        
        
        Long validarExistenciaGenero = generorepo.validarExistenciaPorId(artistaEditado.getIdGeneroMusical());
        if (validarExistenciaGenero == 0) {
            throw new ResourceNotFoundException("El genero musical no existe");
        }
        
        if (artistaEditado.getIdGeneroMusical() == null) {
            throw new LogicBusinessException("El id del genero musical es obligatorio");
        }      
        
        Artista artista = this.repo.listarPorId(artistaEditado.getId());
        Long count = repo.validarExistenciaPorId(artistaEditado.getId());
        if (count > 0) {
            if (artistaEditado.getId().equals(artista.getId())) {
                count = repo.validarExistenciaPorNombre(artistaEditado.getNombreArtistico());
                if (count == 0 || artistaEditado.getNombreArtistico().equalsIgnoreCase(artista.getNombreArtistico())) {
                    GeneroMusical genero = new GeneroMusical();
                    Ocupacion ocupacion = new Ocupacion();
                    genero.setId(artistaEditado.getIdGeneroMusical());
                    ocupacion.setId(artistaEditado.getIdOcupacion());

                    artista.setNombreArtistico(artistaEditado.getNombreArtistico());
                    artista.setFechaNacimiento(artistaEditado.getFechaNacimiento());
                    artista.setNacionalidad(artistaEditado.getNacionalidad());
                    artista.setImagen(artistaEditado.getImagen());
                    artista.setDescripcion(artistaEditado.getDescripcion());
                    artista.setCanciones(artistaEditado.getCanciones());
                    artista.setGeneroMusical(genero);
                    artista.setOcupacion(ocupacion);
                    
                    repo.editar(artista);
                } else {
                    throw new ConflictException("Ya existe un artista con el nombre: " + artistaEditado.getNombreArtistico());
                }
            } else {
                throw new LogicBusinessException("El id enviado es diferente al id del artista que va a editar");
            }
        } else {
            throw new ResourceNotFoundException("Artista no encontrado");
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

    private Artista convertToEntity(ArtistaDto artistaDto){
        GeneroMusical genero = new GeneroMusical();
        Ocupacion ocupacion = new Ocupacion();
        
        genero.setId(artistaDto.getIdGeneroMusical());
        ocupacion.setId(artistaDto.getIdOcupacion());
        
        Artista artistaEntity = new Artista();
        
        artistaEntity.setNombreArtistico(artistaDto.getNombreArtistico());
        artistaEntity.setFechaNacimiento(artistaDto.getFechaNacimiento());
        artistaEntity.setNacionalidad(artistaDto.getNacionalidad());
        artistaEntity.setImagen(artistaDto.getImagen());
        artistaEntity.setDescripcion(artistaDto.getDescripcion());
        artistaEntity.setGeneroMusical(genero);
        artistaEntity.setOcupacion(ocupacion);
        
        return artistaEntity;
    }

}
