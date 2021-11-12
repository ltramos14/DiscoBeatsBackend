package co.edu.unicundi.discobeatsejb.service.impl;
import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.GeneroMusical;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;
import co.edu.unicundi.discobeatsejb.exception.LogicBusinessException;
import co.edu.unicundi.discobeatsejb.exception.ResourceNotFoundException;
import co.edu.unicundi.discobeatsejb.repository.IGeneroMusicalRepo;
import co.edu.unicundi.discobeatsejb.service.IGeneroMusicalService;
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
public class GeneroMusicalServiceImpl implements IGeneroMusicalService {

    @EJB
    private IGeneroMusicalRepo repo;
    
    @Override
    public List<GeneroMusical> listarGenerosMusicales() {
        if (!repo.listarTodos().isEmpty()) {
            return repo.listarTodos();
        } else {
            return null;
        }
    }

    @Override
    public GeneroMusical listarGeneroMusicalPorId(Integer id) throws ResourceNotFoundException {
        Long cont = repo.validarExistenciaPorId(id);
        
        if(cont > 0) {
            GeneroMusical genero = repo.listarPorId(id);
            return genero;
        } else {
            throw new ResourceNotFoundException("El genero musical no fue encontrado");
        }
    }

    @Override
    public void guardarGeneroMusical(GeneroMusical generoMusicalNuevo) throws ConflictException {
       Long contarExistenciaPorNombre = repo.validarExistenciaPorNombre(generoMusicalNuevo.getNombreGeneroMusical());
       
       if(contarExistenciaPorNombre == 0) {
           repo.guardar(generoMusicalNuevo);
       } else {
           throw new ConflictException("Ya existe el genero musical " + generoMusicalNuevo.getNombreGeneroMusical());
       }    
    }

    @Override
    public void editarGeneroMusical(GeneroMusical generoMusicalEditado) throws ResourceNotFoundException, LogicBusinessException, ConflictException {
         //Validacion del id
        if(generoMusicalEditado.getId() == null) {
            throw new LogicBusinessException("El id del género musical a editar es obligatorio");
        }
        //Validación de existencia de género musical por id
        Long validarExistenciaGenero = this.repo.validarExistenciaPorId(generoMusicalEditado.getId());
        if (validarExistenciaGenero < 0) {
            throw new ResourceNotFoundException("El genero musical no existe");
        }
        //Validación de existencia de género musical por nombre
        Long count = this.repo.validarExistenciaPorNombre(generoMusicalEditado.getNombreGeneroMusical());
        if(count == 0) {
            GeneroMusical genero = this.repo.listarPorId(generoMusicalEditado.getId());
            genero.setNombreGeneroMusical(generoMusicalEditado.getNombreGeneroMusical());
            this.repo.editar(genero);
       } else {
           throw new ConflictException("La el género musical " + generoMusicalEditado.getNombreGeneroMusical() + "ya existe en DiscoBeats");
       }  
    }

    @Override
    public void eliminarGeneroMusical(Integer id) throws ResourceNotFoundException { 
        Long contarExistenciaPorId = repo.validarExistenciaPorId(id);
        if(contarExistenciaPorId > 0) 
            repo.eliminar(id);
        else
            throw new ResourceNotFoundException("Genero Musical no encontrado");
       
    }
     
}
