package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "albumes")
@NamedQueries({
    @NamedQuery(name = "Album.ListarTodos", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.AlbumDto" 
            + "(a.id, a.artista.id, a.generoMusical.id, a.artista.nombreArtistico, a.generoMusical.nombreGeneroMusical, a.nombre, a.descripcion, a.fechaLanzamiento, a.imagen, a.precio) FROM Album a ORDER BY a.id"),
    @NamedQuery(name = "Album.ObtenerPorId", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.AlbumDto" 
            + "(a.id, a.artista.id, a.generoMusical.id, a.nombre, a.descripcion, a.fechaLanzamiento, a.imagen, a.precio) FROM Album a WHERE a.id = :id"),
    @NamedQuery(name = "Album.ObtenerAlbumesArtista", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.AlbumDto" 
            + "(a.id, a.generoMusical.id, a.artista.id, a.nombre, a.descripcion, a.fechaLanzamiento, a.imagen, a.precio) FROM Album a WHERE a.artista.id = :idartista"),
    @NamedQuery(name = "Album.ContarPorId", query = "SELECT COUNT(l) FROM Album l WHERE l.id=:id"),
    @NamedQuery(name = "Album.EliminarAlbum", query = "DELETE FROM Album b WHERE b.id=:id")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Album.ContarPorNombre", query = "SELECT COUNT(*) FROM albumes WHERE UPPER(nombre) = UPPER(?) AND id_artista = ?")
})
public class Album implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false )
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false )
    private GeneroMusical generoMusical;
    
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    
    @Column(name = "descripcion", nullable = false, columnDefinition = "text")
    private String descripcion;
    
    @Column(name = "fecha_lanzamiento", nullable = false)
    private Date fechaLanzamiento;
    
    @Column(name = "imagen", nullable = true, columnDefinition = "text")
    private String imagen;
    
    @Column(name = "precio", nullable = false)
    private Integer precio;
        
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> listaCanciones;
    
    @OneToMany(mappedBy = "albumCompra", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraAlbum> listaCompras;
    
    public Album() {
    }

    public Album(Artista artista, GeneroMusical generoMusical, String nombre, String descripcion, Date fechaLanzamiento, String imagen, Integer precio, List<Cancion> listaCanciones, List<CompraAlbum> listaCompras) {
        this.artista = artista;
        this.generoMusical = generoMusical;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.precio = precio;
        this.listaCanciones = listaCanciones;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<CompraAlbum> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<CompraAlbum> listaCompras) {
        this.listaCompras = listaCompras;
    }
}
