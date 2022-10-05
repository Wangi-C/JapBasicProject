package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.User;

import java.time.LocalDateTime;

public class UserSaveMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        /**db 연동을 위한 기반을 만든다. 실제 db연동은 entityManager를 활용**/
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            /**   persist : 객체 insert
            User user = new User("user@user.com", "user", LocalDateTime.now());
            1. entityManager.persist(user);
            1. 파라미터로 전달된 객체가 db에 저장이 된다.
             **/


            /**   find : 객체 조회
             2. User user = entityManager.find(User.class, "whdhks@user.com");
            if (user == null) {
                System.out.println("user = 없음");
            } else {
                System.out.printf("user email=%s, name=%s, createDate=%s\n", user.getEmail(), user.getName(), user.getCreateDate());
            }**/


            /**changeName : update**/
            User user = entityManager.find(User.class, "user@user.com");
            if (user == null) {
                System.out.println("User 없음");
            } else {
                String newName = "이름" + (System.currentTimeMillis() % 100);
                user.changeName(newName);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();
    }
}
