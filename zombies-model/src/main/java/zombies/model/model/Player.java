package zombies.model.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import zombies.web.persistence.PersistentPlayer;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable,PersistentPlayer {
	
	private static final int SPEED_RECHARGE_PER_SKILL_POINT = 1;

	private static final int HIT_RECOVERY_PER_SKILL_POINT = 1;

	private static final int LIFE_PER_SKILL_POINT = 20;

	private static final long serialVersionUID = 1300776856528206676L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAYER_ID", nullable = false)
	private Long id;
	
	@Column(name = "PLAYER_NAME")
	private String name;
	
	@Column(name = "PLAYER_EXPERIENCE")
	private Integer experience;
	
	@Column(name = "PLAYER_MONEY")
	private Integer money;
	
	@Column(name = "PLAYER_ESCENE")
	private Integer scene;
	
	@Column(name = "PLAYER_AIM")
	private Integer aim;
	
	@Column(name = "PLAYER_MOVEMENT")
	private Integer movement;
	
	@Column(name = "PLAYER_RECHARGE")
	private Integer speedRecharge;
	
	@Column(name = "PLAYER_HIT_RECOVERY")
	private Integer hitRecovery;
	
	@Column(name = "PLAYER_TOTAL_LIFE")
	private Integer totalLife;

	@Column(name = "PLAYER_SKILL_POINTS")
	private Integer skillPoints;
	
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "PLAYER_LEVEL_ID", nullable = false)
	private PlayerLevel playerLevel;
	
	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(name = "PLAYER_MISSION", joinColumns = @JoinColumn(name = "PLAYER_ID"), inverseJoinColumns = @JoinColumn(name = "PLAYER_ID"))
	private List<PlayerMission> missions;
	
	public void addSkillPointToTotalLife(){
		if(hasSkillPoints()){
			setTotalLife(getTotalLife() + LIFE_PER_SKILL_POINT);
		}
	}
	
	public void addSkillPointToHitRecovery(){
		if(hasSkillPoints()){
			setHitRecovery(getHitRecovery() + HIT_RECOVERY_PER_SKILL_POINT);
		}
	}
	
	public void addSkillPointToSpeedRecharge(){
		if(hasSkillPoints()){
			setSpeedRecharge(getSpeedRecharge() + SPEED_RECHARGE_PER_SKILL_POINT);
		}
	}

	private boolean hasSkillPoints() {
		return getSkillPoints() > 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	@Override
	public void addExperience(Integer experience) {
		setExperience(getExperience() + experience);
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer escene) {
		this.scene = escene;
	}

	public Integer getAim() {
		return aim;
	}

	public void setAim(Integer aim) {
		this.aim = aim;
	}

	public Integer getHitRecovery() {
		return hitRecovery;
	}

	public void setHitRecovery(Integer hitRecovery) {
		this.hitRecovery = hitRecovery;
	}

	public Integer getTotalLife() {
		return totalLife;
	}

	public void setTotalLife(Integer totalLife) {
		this.totalLife = totalLife;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getMovement() {
		return movement;
	}

	public void setMovement(Integer movement) {
		this.movement = movement;
	}

	public Integer getSpeedRecharge() {
		return speedRecharge;
	}

	public void setSpeedRecharge(Integer speedRecharge) {
		this.speedRecharge = speedRecharge;
	}
	
	public Integer getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(Integer skillPoints) {
		this.skillPoints = skillPoints;
	}

	public PlayerLevel getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(PlayerLevel playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	public List<PlayerMission> getMissions() {
		return missions;
	}

	public void setMissions(List<PlayerMission> missions) {
		this.missions = missions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	

	
}
