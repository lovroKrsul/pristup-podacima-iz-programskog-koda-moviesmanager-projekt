/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.DAO.SQL;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author THEMAN
 */
public class HibernateFactory {
     private HibernateFactory() {
    }
    
    public static final String SELECT_MOVIES = "Movie.findAll";
    public static final String SELECT_MOVIES_BY_ID = "Movie.findByMovieID";
    public static final String SELECT_MOVIES_BY_TITLE = "Movie.findByTitle";
    public static final String SELECT_MOVIES_BY_ACTORS = "Movie.findByActors";
    public static final String SELECT_MOVIES_BY_GANRE = "Movie.findByGanre";
    public static final String SELECT_DIRECTORS = "Director.findAll";
    public static final String SELECT_DIRECTORS_BY_NAME = "Director.findByName";
    public static final String SELECT_DIRECTORS_BY_ID = "Director.findByDirectorID";
    
    private static final String PERSISTENT_UNIT = "moviesmanagerPU";
    
    private static final EntityManagerFactory EMF 
            = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
    
    public static EntityManagerWrapper getEntityManager() {
        return new EntityManagerWrapper(EMF.createEntityManager());
    }
      
    public static void release() {
        EMF.close();
    }
    
}
