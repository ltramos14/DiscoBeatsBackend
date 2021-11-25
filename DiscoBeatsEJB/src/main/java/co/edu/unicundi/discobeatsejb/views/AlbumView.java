/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.views;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author Camilo Preciado
 */
@Entity
@Immutable
@Table(name = "view_ventas_album")
@NamedQueries({
    @NamedQuery(name = "AlbumView.VentasAlbumes", query = "SELECT av FROM AlbumView av")
})
public class AlbumView implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombrealbum", insertable = false, updatable = false, length = 25 )
    private String NombreAlbum;
    
    @Column(name = "ventas", insertable = false, updatable = false, length = 25 )
    private Integer Ventas;

    public AlbumView() {
    }

    public AlbumView(Integer id, String NombreAlbum, Integer Ventas) {
        this.id = id;
        this.NombreAlbum = NombreAlbum;
        this.Ventas = Ventas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreAlbum() {
        return NombreAlbum;
    }

    public void setNombreAlbum(String NombreAlbum) {
        this.NombreAlbum = NombreAlbum;
    }

    public Integer getVentas() {
        return Ventas;
    }

    public void setVentas(Integer Ventas) {
        this.Ventas = Ventas;
    }
    
    
}
