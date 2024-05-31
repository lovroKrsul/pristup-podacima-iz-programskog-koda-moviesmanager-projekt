/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.Models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pppkDZ3.DAO.SQL.HibernateFactory;

/**
 *
 * @author THEMAN
 */
@Entity
@Table(name = "Director")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = HibernateFactory.SELECT_DIRECTORS, query = "SELECT d FROM Director d")
    , @NamedQuery(name = HibernateFactory.SELECT_DIRECTORS_BY_ID, query = "SELECT d FROM Director d WHERE d.directorID = :directorID")
    , @NamedQuery(name = HibernateFactory.SELECT_DIRECTORS_BY_NAME, query = "SELECT d FROM Director d WHERE d.name = :name")})
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DirectorID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer directorID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "director")
    private Collection<Movie> movieCollection;

    public Director() {
    }

    public Director(Integer directorID) {
        this.directorID = directorID;
    }

    public Director(Integer directorID, String name) {
        this.directorID = directorID;
        this.name = name;
    }

    public Integer getDirectorID() {
        return directorID;
    }

    public void setDirectorID(Integer directorID) {
        this.directorID = directorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Movie> getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(Collection<Movie> movieCollection) {
        this.movieCollection = movieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (directorID != null ? directorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        if ((this.directorID == null && other.directorID != null) || (this.directorID != null && !this.directorID.equals(other.directorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pppkDZ3.Models.Director[ directorID=" + directorID + " ]";
    }
    
     public Director(Director data) {
        updateDetails(data);
    }
    
    public void updateDetails(Director data) {
        name = data.name;
        directorID = data.directorID;
        
    }
    
}
