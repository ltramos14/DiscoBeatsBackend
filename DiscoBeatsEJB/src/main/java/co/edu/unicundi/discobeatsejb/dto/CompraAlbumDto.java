package co.edu.unicundi.discobeatsejb.dto;

import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tatiana Ramos Villanueva
 * @author Nicolás Nieto Cárdenas
 * @author Camilo Preciado Rojas
 * @version 1.0.0
 * @since 1.0.0
 */
public class CompraAlbumDto implements Serializable{
    
    private Integer id;
    
    @NotNull(message = "La compra debe tener un album obligatoriamente")
    private Integer idAlbum;
    
    @NotNull(message = "La compra debe pertenecer a un usuario obligatoriamente")
    private Integer idUsuario;
     
    @NotNull(message = "El precio total es obligatorio")
    private Integer precioTotal;

    private String nombreUsuario;
    
    private String album;
    
    private Date fechaCompra;
    
    
    public CompraAlbumDto() {
    }

    public CompraAlbumDto(Integer id, Integer idAlbum, Integer idUsuario, Integer precioTotal) {
        this.id = id;
        this.idAlbum = idAlbum;
        this.idUsuario = idUsuario;
        this.precioTotal = precioTotal;
    }
    
     public CompraAlbumDto(Integer id, Integer idAlbum, Integer idUsuario, String album, String nombreUsuario, Integer precioTotal, Date fechaCompra) {
        this.id = id;
        this.idAlbum = idAlbum;
        this.idUsuario = idUsuario;
        this.album = album;
        this.nombreUsuario = nombreUsuario;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }  

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
   
    
    
}
