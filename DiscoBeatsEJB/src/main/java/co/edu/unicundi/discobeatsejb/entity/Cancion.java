
package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "canciones")
@NamedQueries({
    @NamedQuery(name = "Cancion.ListarTodos", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.CancionDto" 
            + "(a.id, a.artista.id,  a.generoMusical.id, a.album.id, a.nombre, a.duracion, a.reproducciones, a.fechaLanzamiento, a.precio, a.imagen) FROM Cancion a ORDER BY a.id"),
    @NamedQuery(name = "Cancion.ObtenerPorId", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.CancionDto" 
            + "(a.id, a.artista.id,  a.generoMusical.id, a.album.id, a.nombre, a.duracion, a.reproducciones, a.fechaLanzamiento, a.precio, a.imagen) FROM Cancion a WHERE a.id = :id"),
    @NamedQuery(name = "Cancion.ObtenerCancionesArtista", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.CancionDto" 
            + "(a.id, a.artista.id,  a.generoMusical.id, a.album.id, a.nombre, a.duracion, a.reproducciones, a.fechaLanzamiento, a.precio, a.imagen) FROM Cancion a WHERE a.artista.id = :idartista"),
    @NamedQuery(name = "Cancion.ObtenerCancionesAlbum", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.CancionDto" 
            + "(a.id, a.artista.id,  a.generoMusical.id, a.album.id, a.nombre, a.duracion, a.reproducciones, a.fechaLanzamiento, a.precio, a.imagen) FROM Cancion a WHERE a.album.id = :idalbum"),
    @NamedQuery(name = "Cancion.ContarPorId", query = "SELECT COUNT(c) FROM Cancion c WHERE c.id = :id"),
    @NamedQuery(name = "Cancion.ObtenerCancionPorId", query = "SELECT c FROM Cancion c JOIN c.album alb JOIN c.artista art WHERE c.id = :id"),
    @NamedQuery(name = "Cancion.EliminarPorId", query = "DELETE FROM Cancion c WHERE c.id = :id"),
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Cancion.ContarCancionAlbum", query = "SELECT COUNT(*) FROM canciones WHERE UPPER(nombre) = UPPER(?) AND id_album = ?")
})
public class Cancion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false )
    private Artista artista;
    
    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private GeneroMusical generoMusical;
    
    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;
       
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    
    @Column(name = "duracion", nullable = false)
    private Time duracion;
    
    @Column(name = "reproducciones", nullable = false)
    private Integer reproducciones;
    
    @Column(name = "fecha_lanzamiento", nullable = false)
    private Date fechaLanzamiento;
    
    @Column(name = "precio", nullable = false)
    private Integer precio;
    
    @Column(name = "imagen", nullable = true, columnDefinition = "text")
    private String imagen;
    
    @OneToMany(mappedBy = "cancionCompra", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraCancion> listaCompras;
    
    public Cancion() {
    }

    public Cancion(Artista artista, GeneroMusical generoMusical, Album album, String nombre, Time duracion, Integer reproducciones, Date fechaLanzamiento, Integer precio, String imagen, List<CompraCancion> listaCompras) {
        this.artista = artista;
        this.generoMusical = generoMusical;
        this.album = album;
        this.nombre = nombre;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
        this.imagen = imagen;
        this.listaCompras = listaCompras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @JsonIgnore
    @XmlTransient
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @JsonIgnore
    @XmlTransient
    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }
    
    @JsonIgnore
    @XmlTransient
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public Integer getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Integer reproducciones) {
        this.reproducciones = reproducciones;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<CompraCancion> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<CompraCancion> listaCompras) {
        this.listaCompras = listaCompras;
    }

}
