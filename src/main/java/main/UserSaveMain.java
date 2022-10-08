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
        // persistence.xml 파일에 정의한 영속 단위 기준으로 초기화. -> 단 한번만 생성.
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


            /**changeName : update
            User user = entityManager.find(User.class, "user@user.com");
            if (user == null) {
                System.out.println("User 없음");
            } else {
                String newName = "이름" + (System.currentTimeMillis() % 100);
                user.changeName(newName);
            }**/

            User user = new User("user2@user.com", "user", LocalDateTime.now());
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();
    }
    // 기초02 : 영속 컨텍스트 persistence context
    // 객체를 insert하는 persist , 객체를 update하는 change~ 에 대한 query문은 commit시점에 실행된다.
    // 이것은 영속컨텍스트 라는 것 때문이다
    // 영속 컨테스트는 응용프로그램을 통한 변함 또는 db를 통해 조회된 객체를 미리 저장해두는 저장메모리이다.
    // 커밋 시점에 변경 내역을 db에 반영 (변경 쿼리 실행)
    // 엔티티 객체를 미리 영속컨텍스트에 저장을 하고 해당 엔티티에 변화가 발생하는지 추저을 해서 발생하면 commit시점에 db에 반영
}
