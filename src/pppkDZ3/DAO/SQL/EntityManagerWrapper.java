/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.DAO.SQL;

import javax.persistence.EntityManager;

/**
 *
 * @author THEMAN
 */
public class EntityManagerWrapper implements AutoCloseable {
      private final EntityManager entityManager;

    public EntityManagerWrapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager get() {
        return entityManager;
    }
    
    @Override
    public void close() throws Exception {
        if (entityManager != null) {
            entityManager.close();
        }
    }
    
    
}
