package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Address homeAddress = new Address("city", "street", "321654");

            Member member = new Member();
            member.setUserName("member1");
            member.setAddress(homeAddress);
            em.persist(member);

            Address newAddress = new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode());
            member.setAddress(newAddress);
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();

    }

   
}
