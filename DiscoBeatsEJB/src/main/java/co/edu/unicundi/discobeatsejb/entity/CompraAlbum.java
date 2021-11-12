package co.edu.unicundi.discobeatsejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class CompraAlbum implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "La compra debe tener un album obligatoriamente")
    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false )
    private Album album;
    
    @NotNull(message = "La compra debe pertenecer a un usuario obligatoriamente")
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false )
    private Usuario usuario;
     
    
    @Column(name = "fecha_compra", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCompra;
    
    @NotNull(message = "El precio total es obligatorio")
    @Column(name = "precio_total", nullable = false, length = 25)
    private Integer precioTotal;

    public CompraAlbum() {
    }

    public CompraAlbum(Album album, Usuario usuario, Date fechaCompra, Integer precioTotal) {
        this.album = album;
        this.usuario = usuario;
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
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @JsonIgnore
    @XmlTransient
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
