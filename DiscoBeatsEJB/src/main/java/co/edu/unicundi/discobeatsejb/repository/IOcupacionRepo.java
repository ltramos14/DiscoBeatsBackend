/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Ocupacion;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author Camilo Preciado
 */
@Local
public interface IOcupacionRepo{    
    public Long validarExistenciaOcupacionPorId(Integer id);
    
    public List<Ocupacion> obtenerOcupaciones();
    
    public Ocupacion obtenerPorId(Integer id);
}
