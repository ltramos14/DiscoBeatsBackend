/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.discobeatsejb.dto;

import java.util.List;

/**
 *
 * @author nicon
 */
public class GeneroMusicalDto {
    
    private Integer id;

    private String nombreGeneroMusical;
    
    private List<ArtistaDto> listaArtistas;
    
    private List<CancionDto> listaCanciones;
    
    private List<AlbumDto> listaAlbumes;

    public GeneroMusicalDto() {
    }

    public GeneroMusicalDto(Integer id, String nombreGeneroMusical, List<ArtistaDto> listaArtistas, List<CancionDto> listaCanciones, List<AlbumDto> listaAlbumes) {
        this.id = id;
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

    public List<ArtistaDto> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<ArtistaDto> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public List<CancionDto> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<CancionDto> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public List<AlbumDto> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(List<AlbumDto> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }
    
}
