/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import pppkDZ3.DAO.SQL.HibernateFactory;

/**
 *
 * @author THEMAN
 */
@Entity
@Table(name = "Movie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = HibernateFactory.SELECT_MOVIES, query = "SELECT m FROM Movie m")
    , @NamedQuery(name = HibernateFactory.SELECT_MOVIES_BY_ID, query = "SELECT m FROM Movie m WHERE m.movieID = :movieID")
    , @NamedQuery(name = HibernateFactory.SELECT_MOVIES_BY_TITLE, query = "SELECT m FROM Movie m WHERE m.title = :title")
    , @NamedQuery(name = HibernateFactory.SELECT_MOVIES_BY_ACTORS, query = "SELECT m FROM Movie m WHERE m.actors = :actors")
    , @NamedQuery(name = HibernateFactory.SELECT_MOVIES_BY_GANRE, query = "SELECT m FROM Movie m WHERE m.ganre = :ganre")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MovieID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieID;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @Column(name = "Actors")
    private String actors;
    @Basic(optional = false)
    @Column(name = "Ganre")
    private String ganre;
    @Lob
    @Column(name = "Picture")
    private byte[] picture;
    @JoinColumn(name = "Director", referencedColumnName = "DirectorID")
    @ManyToOne(optional = false)
    private Director director;

    public Movie() {
    }

    public Movie(Integer movieID) {
        this.movieID = movieID;
    }

    public Movie(Integer movieID, String title, String actors, String ganre) {
        this.movieID = movieID;
        this.title = title;
        this.actors = actors;
        this.ganre = ganre;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGanre() {
        return ganre;
    }

    public void setGanre(String ganre) {
        this.ganre = ganre;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieID != null ? movieID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieID == null && other.movieID != null) || (this.movieID != null && !this.movieID.equals(other.movieID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pppkDZ3.Models.Movie[ movieID=" + movieID + " ]";
    }
    public Movie(Movie data) {
        updateDetails(data);
    }
    
    public void updateDetails(Movie data) {
        movieID=data.movieID;
        title=data.title;
        actors=data.actors;
        director=data.director;
        ganre=data.ganre;
        picture=data.picture;
        
    }
}
