package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Album;
import co.edu.unicundi.discobeatsejb.entity.CompraAlbum;
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
public interface ICompraAlbumRepo {
    
    public Long validarExistenciaPorId(Integer id);
    
    public Long validarExistenciaCompra(Integer idAlbum, Integer idUsuario);
    
    public List<CompraAlbum> listarTodos();
    
    public CompraAlbum listarPorId(Integer id);
    
    public void guardar(CompraAlbum obj);
    
    public List<CompraAlbum> obtenerComprasUsuario(Integer id);
    
}
