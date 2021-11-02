/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.service;

import co.edu.unicundi.discobeatsejb.entity.Artista;
import co.edu.unicundi.discobeatsejb.entity.Cancion;
import co.edu.unicundi.discobeatsejb.exception.ConflictException;

/**
 *
 * @author Camilo Preciado
 */
public interface ICancionService {
    public void guardarCancion(Cancion cancionNueva);
}
