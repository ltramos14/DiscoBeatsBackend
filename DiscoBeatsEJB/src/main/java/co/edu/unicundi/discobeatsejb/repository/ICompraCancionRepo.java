/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.CompraCancion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author tatia
 */
@Local
public interface ICompraCancionRepo {
    public Long validarExistenciaPorId(Integer id);
    
    public Long validarExistenciaCompra(Integer idUsuario, Integer idCancion);
    
    public List<CompraCancion> listarTodos();
    
    public CompraCancion listarPorId(Integer id);
    
    public void guardar(CompraCancion obj);
}
