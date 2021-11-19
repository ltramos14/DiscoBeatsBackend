/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author tatia
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.unicundi.discobeatswar.controller.AlbumController.class);
        resources.add(co.edu.unicundi.discobeatswar.controller.ArtistaController.class);
        resources.add(co.edu.unicundi.discobeatswar.controller.AuthController.class);
        resources.add(co.edu.unicundi.discobeatswar.controller.CancionController.class);
        resources.add(co.edu.unicundi.discobeatswar.controller.GeneroMusicalController.class);
        resources.add(co.edu.unicundi.discobeatswar.controller.UsuarioController.class);
        resources.add(co.edu.unicundi.discobeatswar.exception.ExceptionHandler.class);
        resources.add(co.edu.unicundi.discobeatswar.exception.ValidationExceptionHandler.class);
        resources.add(org.netbeans.rest.application.config.CorsFilter.class);
        resources.add(org.netbeans.rest.application.config.Interceptor.class);
    }
    
}
