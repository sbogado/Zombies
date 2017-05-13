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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import zombies.web.persistence.PersistentPlayer;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable,PersistentPlayer {
	
	/***************************************** INITIAL STATS *************************/
	
	public static final int INITIAL_PLAYER_LEVEL = 1;

	public static final int INITIAL_SKILL_POINTS = 0;

	public static final int INITIAL_SPEED_RECHARGE = 1;

	public static final int INITIAL_MOVEMENT = 200;

	public static final int INITIAL_MONEY = 0;

	public static final int INITIAL_TOTAL_LIFE = 100;

	public static final int INITIAL_HIT_RECOVERY = 1;

	public static final int INITIAL_EXPERIENCE = 0;

	public static final int INITIAL_SCENE = 1;

	public static final int INITIAL_AIM = 1;
	
	/***************************************** INITIAL STATS *************************/
	
	/*****************************************  STATS PER POINT*************************/
	
	private static final int MOVEMENT_PER_SKILL_POINT = 50;

	private static final int SPEED_RECHARGE_PER_SKILL_POINT = 1;

	private static final int HIT_RECOVERY_PER_SKILL_POINT = 1;
	
	private static final int AIM_PER_SKILL_POINT = 1;

	private static final int LIFE_PER_SKILL_POINT = 20;
	
	/*****************************************  STATS PER POINT*************************/

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
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "id.playerId")
	private List<PlayerMission> missions;
	
	public Integer pointsAddedToTotalLife(){
		return (getTotalLife() - INITIAL_TOTAL_LIFE) / LIFE_PER_SKILL_POINT;
	}
	
	public void addSkillPointToTotalLife(Integer amountOfPoint){
		if(hasSkillPoints(amountOfPoint)){
			setTotalLife(getTotalLife() + (amountOfPoint * LIFE_PER_SKILL_POINT));
			useSkillPoints(amountOfPoint);
		}
	}
	
	public Integer pointsAddedToHitRecovery(){
		return (getHitRecovery() - INITIAL_HIT_RECOVERY) / HIT_RECOVERY_PER_SKILL_POINT;
	}
	
	public void addSkillPointToHitRecovery(Integer amountOfPoint){
		if(hasSkillPoints(amountOfPoint)){
			setHitRecovery(getHitRecovery() + (amountOfPoint * HIT_RECOVERY_PER_SKILL_POINT));
			useSkillPoints(amountOfPoint);
		}
	}
	
	public Integer pointsAddedToSpeedRecharge(){
		return (getSpeedRecharge() - INITIAL_SPEED_RECHARGE) / SPEED_RECHARGE_PER_SKILL_POINT;
	}
	
	public void addSkillPointToSpeedRecharge(Integer amountOfPoint){
		if(hasSkillPoints(amountOfPoint)){
			setSpeedRecharge(getSpeedRecharge() + (amountOfPoint * SPEED_RECHARGE_PER_SKILL_POINT));
			useSkillPoints(amountOfPoint);
		}
	}
	
	
	public Integer pointsAddedToMovement(){
		return (getMovement() - INITIAL_MOVEMENT) / MOVEMENT_PER_SKILL_POINT;
	}
	
	public void addSkillPointToMovement(Integer amountOfPoint){
		if(hasSkillPoints(amountOfPoint)){
			setMovement(getMovement() + (amountOfPoint * MOVEMENT_PER_SKILL_POINT )); 
			useSkillPoints(amountOfPoint);
		}
	}
	
	public Integer pointsAddedToAim(){
		return (getAim() - INITIAL_AIM) / AIM_PER_SKILL_POINT;
	}
	
	public void addSkillPointToAim(Integer amountOfPoint){
		if(hasSkillPoints(amountOfPoint)){
			setAim(getAim() + (amountOfPoint * AIM_PER_SKILL_POINT )); 
			useSkillPoints(amountOfPoint);
		}
	}
	
	public void addSkillPoints(Integer amountOfPoints){
		setSkillPoints(getSkillPoints() + amountOfPoints);
	}
	
	private void useSkillPoints(Integer amountOfPoint) {
		setSkillPoints(getSkillPoints() - amountOfPoint);
	}

	private boolean hasSkillPoints(Integer amountOfPoint ) {
		return getSkillPoints() >= amountOfPoint;
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
