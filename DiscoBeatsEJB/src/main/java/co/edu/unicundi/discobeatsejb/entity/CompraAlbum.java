package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
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
@Table(name = "compras_albumes")
@NamedQueries({
    @NamedQuery(name = "CompraAlbum.ListarTodos", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.CompraAlbumDto" 
            + "(a.id, a.albumCompra.id, a.usuarioAlbum.id, a.precioTotal) FROM CompraAlbum a ORDER BY a.id"),
    @NamedQuery(name = "CompraAlbum.ObtenerComprasUsuario", query = "SELECT NEW co.edu.unicundi.discobeatsejb.dto.CompraAlbumDto" 
            + "(a.id, a.albumCompra.id, a.usuarioAlbum.id, a.precioTotal) FROM CompraAlbum a WHERE a.usuarioAlbum.id = :idusuario"),
    @NamedQuery(name = "CompraAlbum.ContarPorId", query = "SELECT COUNT(t) FROM CompraAlbum t WHERE t.id=:id"),
    @NamedQuery(name = "CompraAlbum.ContarPorUsuario", query = "SELECT COUNT(u) FROM CompraAlbum u WHERE u.usuarioAlbum.id=:idUsuario AND u.albumCompra.id=:idAlbum")
})

public class CompraAlbum implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    
    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false )
    private Album albumCompra;    
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false )
    private Usuario usuarioAlbum;     
    
    @Column(name = "fecha_compra", nullable = true)
    private Date fechaCompra;
    
    @Column(name = "precio_total", nullable = false, length = 25)
    private Integer precioTotal;

    public CompraAlbum() {
    }

    public CompraAlbum(Album albumCompra, Usuario usuarioAlbum, Date fechaCompra, Integer precioTotal) {
        this.albumCompra = albumCompra;
        this.usuarioAlbum = usuarioAlbum;
        this.fechaCompra = fechaCompra;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @XmlTransient
    public Album getAlbum() {
        return albumCompra;
    }

    public void setAlbum(Album albumCompra) {
        this.albumCompra = albumCompra;
    }

    @JsonIgnore
    @XmlTransient
    public Usuario getUsuario() {
        return usuarioAlbum;
    }

    public void setUsuario(Usuario usuarioAlbum) {
        this.usuarioAlbum = usuarioAlbum;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}
