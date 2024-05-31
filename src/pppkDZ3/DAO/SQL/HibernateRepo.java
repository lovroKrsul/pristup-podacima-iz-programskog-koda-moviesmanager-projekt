/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.DAO.SQL;

import java.util.List;
import javax.persistence.EntityManager;
import pppkDZ3.DAL.Repo;
import pppkDZ3.Models.Director;
import pppkDZ3.Models.Movie;

/**
 *
 * @author THEMAN
 */
public class HibernateRepo implements Repo {

    @Override
    public int addMovie(Movie data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Movie movie = new Movie(data);
            em.persist(movie);
            em.getTransaction().commit();
            return movie.getMovieID();
        }
    }

    @Override
    public void editMovie(Movie movie) throws Exception {
      try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(Movie.class, movie.getMovieID()).updateDetails(movie);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
       try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(movie) ? movie : em.merge(movie));
            em.getTransaction().commit();
        }
    }

    @Override
    public Movie getMovieByID(int ID) throws Exception {
       try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(Movie.class, ID);
        }
    }

    @Override
    public Movie getMovieByTitle(String title) throws Exception {
       try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(Movie.class, title);
        }
    }

    @Override
    public List<Movie> getMovies() throws Exception {
         try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.SELECT_MOVIES).getResultList();
        }
    }

    @Override
    public int getMovieID(String title) throws Exception {
       Movie m=getMovieByTitle(title);
       return m.getMovieID();
    }

    @Override
    public int addDirector(Director data) throws Exception {
          try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Director dir = new Director(data);
            em.persist(dir);
            em.getTransaction().commit();
            return dir.getDirectorID();
        }
    }

    @Override
    public void editDirector(Director director) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(Director.class, director.getDirectorID()).updateDetails(director);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteDirector(Director director) throws Exception {
       try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(director) ? director : em.merge(director));
            em.getTransaction().commit();
        }
    }

    @Override
    public Director getDirectorByID(int ID) throws Exception {
         try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(Director.class, ID);
        }
    }

    @Override
    public Director getDirectorByName(String name) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(Director.class, name);
        }
    }

    @Override
    public List<Director> getDirectors() throws Exception {
       try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.SELECT_DIRECTORS).getResultList();
        }
    }
  @Override
    public void release() throws Exception {
        HibernateFactory.release();
    }
    
}
