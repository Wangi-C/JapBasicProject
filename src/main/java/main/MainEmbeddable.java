package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.common.Address;
import jpabasic.reserve.domain.Grade;
import jpabasic.reserve.domain.Hotel;
import jpabasic.reserve.jpa.EMF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainEmbeddable {
    private static Logger logger = LoggerFactory.getLogger(MainEmbeddable.class);

    public static void main(String[] args) {
        EMF.init();
        saveHotel();
        printHotel();
        EMF.close();
    }

    private static void saveHotel() {
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Address address = new Address("주소1", "주소2", "12334");
            Hotel hotel = new Hotel("H00", "HN", 2022, Grade.S7, address);
            entityManager.persist(hotel);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    private static void printHotel() {
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Hotel hotel = entityManager.find(Hotel.class, "H00");
            if (hotel != null) {
                logger.info("주소 : {}", hotel.getAddress());
            }
        } catch(Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}
