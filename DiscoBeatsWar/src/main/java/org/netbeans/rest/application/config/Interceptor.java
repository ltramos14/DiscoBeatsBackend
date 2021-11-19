/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import co.edu.unicundi.discobeatsejb.entity.Usuario;
import co.edu.unicundi.discobeatswar.exception.ExceptionWrapper;
import co.edu.unicundi.discobeatsejb.service.IUsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Provider
@PreMatching
public class Interceptor implements ContainerRequestFilter {

    @EJB
    private IUsuarioService usuarioService;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        ExceptionWrapper msg;

        String url =  requestContext.getUriInfo().getAbsolutePath().toString();
        String metodo = requestContext.getMethod();

        // Urls que no necesitan de token
        if(url.contains("/auth/login") || metodo.equalsIgnoreCase("GET") ||  (url.contains("/usuarios") && metodo.equalsIgnoreCase("POST"))) {
            return;
        }
        
        String token = requestContext.getHeaderString("token");
        
        // Se valida de que el token venga en los headers de la petición
        if (token == null) {
            
            msg = new ExceptionWrapper(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED, "La petición necesita token", url);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON).entity(msg).build());
            return;
            
        } else {
            
            String key = "gcn0%I46jY^Njx0gEacNa9";
            
            try {
                
                // Se decodifica el token
                Claims claims = Jwts.parser()
                                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                                    .parseClaimsJws(token).getBody();
                
                // Se convierten los claims a un objeto Map
                Map<String, String> mapClaims = convertToMap(claims.toString());
                
                // Se valida el token en base de datos
                Usuario usuario;
                usuario = usuarioService.obtenerUsuarioPorId(Integer.parseInt(mapClaims.get("sub")));

                if (usuario.getToken() == null || !usuario.getToken().equals(token.trim())) {
                    
                    msg = new ExceptionWrapper(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED, "Token no válido", url);
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON).entity(msg).build());
                    return;
                    
                }
                
                // Empezar con las validaciones
                if (url.contains("/artistas") && mapClaims.get("rol").equalsIgnoreCase("Administrador")) {
                    return;
                } else if (url.contains("/artistas") && mapClaims.get("rol").equalsIgnoreCase("Usuario")) {
                    msg = new ExceptionWrapper(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED, "El usuario no tiene permisos para las operaciones de Artista", url);
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON).entity(msg).build());
                    return; 
                } else if (url.contains("/generos") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } else if (url.contains("/albumes") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } else if (url.contains("/canciones") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } else if (url.contains("/usuarios") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } else if (url.contains("/auth/logout") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } else if (url.contains("/compras") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } /*eelse if (url.contains("/compras") && (mapClaims.get("rol").equalsIgnoreCase("Administrador") || mapClaims.get("rol").equalsIgnoreCase("Usuario"))) {
                    return;
                } */ else {
                    msg = new ExceptionWrapper(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED, "No tiene permisos para hacer esta operación", url);
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON).entity(msg).build());
                    return; 
                }
             
            } catch(ExpiredJwtException ex) {
                // Si el token enviado tiene la fecha expirada
                msg = new ExceptionWrapper(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED, "Token caducado", url);
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON).entity(msg).build());
                return; 
            } catch(Exception ex) {
                // Si ocurre un error a la hora de decodificar el token
                msg = new ExceptionWrapper(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR, "Error al descifrar el token", url);
                requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type(MediaType.APPLICATION_JSON).entity(msg).build());
            }
            
        }
        
        
    }
    
    /**
     * Método que conviente los claims de JWT a un objeto de tipo Map
     * @param claims
     * @return objeto Map 
     */
    private Map<String, String> convertToMap(String claims) {
        claims = claims.substring(1, claims.length() - 1);
        String [] keys = claims.split(",");
        
        Map<String, String> map = new HashMap<>();
        
        for(String pair: keys) {
            String[] entry = pair.split("=");
            map.put(entry[0].trim(), entry[1].trim());
        }
        return map;
    }
    
}
