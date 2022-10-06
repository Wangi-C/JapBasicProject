package jpabasic.reserve.app;

import jakarta.persistence.EntityManager;
import jpabasic.reserve.domain.User;
import jpabasic.reserve.jpa.EMF;

public class GetUserService {
    public User getUser(String email) {
        EntityManager em = EMF.createEntityManager();
        try {
            User user = em.find(User.class, email);
            // find 를 사용시 주의할 점, null인지 체크를 해봐야한다.
            if (user == null) {
                throw new NoUserException();
            }
            return user;
        } finally {
            em.close();
        }
    }
}
