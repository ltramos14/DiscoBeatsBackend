package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "generos_musicales")
@NamedQueries({
    @NamedQuery(name = "GeneroMusical.ListarTodos", query = "SELECT g FROM GeneroMusical g"),
    @NamedQuery(name = "GeneroMusical.EliminarGenero", query = "DELETE FROM GeneroMusical g WHERE g.id=:id"),
    @NamedQuery(name = "GeneroMusical.ContarPorId", query = "SELECT COUNT(g) FROM GeneroMusical g WHERE g.id=:id"),})
@NamedNativeQueries({
    @NamedNativeQuery(name = "GeneroMusical.ContarPorNombre", query = "SELECT COUNT(*) FROM generos_musicales WHERE UPPER(nombre_genero_musical) = UPPER(?)")
})
public class GeneroMusical implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_genero_musical", nullable = false, length = 15)
    private String nombreGeneroMusical;

    @OneToMany(mappedBy = "generoMusical", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artista> listaArtistas;

    @OneToMany(mappedBy = "generoMusical", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> listaCanciones;

    @OneToMany(mappedBy = "generoMusical", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> listaAlbumes;

    public GeneroMusical() {
    }

    public GeneroMusical(String nombreGeneroMusical, List<Artista> listaArtistas, List<Cancion> listaCanciones, List<Album> listaAlbumes) {
        this.nombreGeneroMusical = nombreGeneroMusical;
        this.listaArtistas = listaArtistas;
        this.listaCanciones = listaCanciones;
        this.listaAlbumes = listaAlbumes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreGeneroMusical() {
        return nombreGeneroMusical;
    }

    public void setNombreGeneroMusical(String nombreGeneroMusical) {
        this.nombreGeneroMusical = nombreGeneroMusical;
    }

    public List<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<Album> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(List<Album> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }

}
