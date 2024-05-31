/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.DAL;


import pppkDZ3.DAO.SQL.HibernateRepo;

/**
 *
 * @author daniel.bele
 */
public class RepoFactory {

    private RepoFactory() {
    }
    
    private static final Repo REPOSITORY = new HibernateRepo();
    
    public static Repo getRepository() {
        return REPOSITORY;
      
    }
    
    
    
}
