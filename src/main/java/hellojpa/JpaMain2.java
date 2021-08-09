package hellojpa;

import javax.persistence.*;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member2 member1 = new Member2();
            member1.setUserName("A");
            Member2 member2 = new Member2();
            member2.setUserName("B");
            Member2 member3 = new Member2();
            member3.setUserName("C");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1.id = " + member1.getId());
            System.out.println("member2.id = " + member2.getId());
            System.out.println("member3.id = " + member3.getId());

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
