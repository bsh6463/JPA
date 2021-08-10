package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;

        team.getMemberList().add(this);
    }

    @Id
    @GeneratedValue()
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //Team 객체와 Team FK를 매핑
    private Team team;

    //    @Column(name = "TEAM_ID")
//    private Long teamId;
}
