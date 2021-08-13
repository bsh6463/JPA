package hellojpa;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUserName("member1");
            //address 값타입
            member.setHomeAddress(new Address("homeCity", "street", "sdd2d"));

            //값타입 컬렉션
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피쟈");

            member.getAddressHistory().add(new AddressEntity("city", "street", "zipcode"));
            member.getAddressHistory().add(new AddressEntity("city2", "street", "zipcode"));

            em.persist(member);

            em.flush();
            em.clear();

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
