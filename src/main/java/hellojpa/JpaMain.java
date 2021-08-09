package hellojpa;

import javax.persistence.*;

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
            member.setUserName("member");
            member.setTeam(team); //set을 통해 연관관계 매핑.
            em.persist(member);


            //member의 소속 팀 찾기....흠..
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            //멤버의 팀 변경
            Team newTeam = em.find(Team.class, 100L);
            member.setTeam(newTeam); //FK 변경됨.

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
