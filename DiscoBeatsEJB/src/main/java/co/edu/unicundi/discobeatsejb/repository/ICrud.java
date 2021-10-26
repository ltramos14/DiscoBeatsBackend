package co.edu.unicundi.discobeatsejb.repository;

import java.util.List;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ICrud<T, ID> {
    
    public List<T> listarTodos();
    
    public T listarPorId(ID id);
    
    public void guardar(T obj);
    
    public void editar(T obj);
    
    public void eliminar(ID id);
}
