package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //em.find를 통해 가져온 member는 영속상태
            Member member = em.find(Member.class, 500L);
            member.setName("wow");

            //준영속 상태로 바꿈
            //em.detach(member);
            em.clear();
            //commit 할 때 member의 업데이트 반영 안됨.
            Member member2 = em.find(Member.class, 500L);
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
