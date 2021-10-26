/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.repository;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author Camilo Preciado
 */
@Stateless
@Local
public interface IArtistaRepo extends ICrud<Artista, Integer>{
    
    
}
