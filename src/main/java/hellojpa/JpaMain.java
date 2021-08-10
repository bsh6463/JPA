package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); //team이 영속상태 되면 id값이 들어감.

            Member member = new Member();
            member.setUserName("member1");
            member.changeTeam(team);
            em.persist(member);

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> memberList = findTeam.getMemberList();
            System.out.println("==================");
            for (Member m : memberList) {

                System.out.println("member1 = " + m.getUserName());

            }
            System.out.println("==================");

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
