package jpabasic.reserve.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.User;
import jpabasic.reserve.jpa.EMF;

public class ChangeNameService {
    public void changeName(String email, String name) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            User user = em.find(User.class, email);
            if (user == null) {
                throw new NoUserException();
            }
            user.changeName(name);
            transaction.commit();
        } catch(Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
