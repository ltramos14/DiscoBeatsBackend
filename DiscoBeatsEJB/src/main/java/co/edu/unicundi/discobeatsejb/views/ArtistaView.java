package co.edu.unicundi.discobeatsejb.views;

import co.edu.unicundi.discobeatsejb.entity.Cancion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */

@Entity
@Table(name = "vista_artista")
public class ArtistaView implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombreArtistico;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
    private String nacionalidad;
    
    private String ocupacion; 
    
    private String imagen;
    
    private String generoMusical;
    
    private String descripcion;
    
    private Integer numeroCanciones;
    
    private Cancion cancionMasReproducida;

    public ArtistaView() {
    }

    public ArtistaView(Integer id, String nombreArtistico, Date fechaNacimiento, String nacionalidad, String ocupacion, String imagen, String generoMusical, String descripcion, Integer numeroCanciones, Cancion cancionMasReproducida) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
        this.imagen = imagen;
        this.generoMusical = generoMusical;
        this.descripcion = descripcion;
        this.numeroCanciones = numeroCanciones;
        this.cancionMasReproducida = cancionMasReproducida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(Integer numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public Cancion getCancionMasReproducida() {
        return cancionMasReproducida;
    }

    public void setCancionMasReproducida(Cancion cancionMasReproducida) {
        this.cancionMasReproducida = cancionMasReproducida;
    }
   
}
