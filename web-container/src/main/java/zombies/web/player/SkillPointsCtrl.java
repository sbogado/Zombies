package zombies.web.player;

import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import zombies.model.model.Player;
import zombies.model.service.PlayerService;
import zombies.web.AbstractController;
import zombies.web.auth.AuthenticationCtrl;

@ManagedBean
@ViewScoped
public class SkillPointsCtrl extends AbstractController {
	
	@ManagedProperty(value = "#{playerServiceImpl}")
	private PlayerService playerService;
	
	@ManagedProperty(value = "#{authenticationCtrl}")
	private AuthenticationCtrl authenticationCtrl;

	private Integer skillPointsLeft;
	
	private Integer totalLifeSkillPointsToAdd;
	private Integer hitRecoverySkillPointsToAdd;
	private Integer speedRechargeSkillPointsToAdd;
	private Integer movementSkillPointsToAdd;
	private Integer aimSkillPointsToAdd;
	
	@PostConstruct
	public void initialize(){
		resetSkillPoints();
		setSkillPointsLeft(getPlayer().getSkillPoints());
	}
	
	public void calculateSkillPointsLeft(){
		setSkillPointsLeft(getPlayer().getSkillPoints() 
				- getHitRecoverySkillPointsToAdd()
				- getTotalLifeSkillPointsToAdd()
				- getSpeedRechargeSkillPointsToAdd()
				- getMovementSkillPointsToAdd()
				- getAimSkillPointsToAdd());
	}
	
	public void saveOperation(){
		getPlayer().addSkillPointToHitRecovery(getHitRecoverySkillPointsToAdd());
		getPlayer().addSkillPointToMovement(getMovementSkillPointsToAdd());
		getPlayer().addSkillPointToSpeedRecharge(getSpeedRechargeSkillPointsToAdd());
		getPlayer().addSkillPointToTotalLife(getTotalLifeSkillPointsToAdd());
		getPlayer().addSkillPointToAim(getAimSkillPointsToAdd());
		try {
			setPlayer(getPlayerService().update(getPlayer()));
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Could not update player",e);
		}
		resetSkillPoints();
	}
	


	public void resetSkillPoints() {
		setHitRecoverySkillPointsToAdd(0);
		setSpeedRechargeSkillPointsToAdd(0);
		setTotalLifeSkillPointsToAdd(0);
		setMovementSkillPointsToAdd(0);
		setAimSkillPointsToAdd(0);
		setSkillPointsLeft(getPlayer().getSkillPoints());
	}

	public void addSkillPointToTotalLife(){
		if(hasSkillPoints()){
			setTotalLifeSkillPointsToAdd((getTotalLifeSkillPointsToAdd() + 1));
			useSkillPoint();
		}
	}
	
	public void addSkillPointToHitRecovery(){
		if(hasSkillPoints()){
			setHitRecoverySkillPointsToAdd((getHitRecoverySkillPointsToAdd() + 1));
			useSkillPoint();
		}
	}
	
	public void addSkillPointToSpeedRecharge(){
		if(hasSkillPoints()){
			setSpeedRechargeSkillPointsToAdd((getSpeedRechargeSkillPointsToAdd() + 1));
			useSkillPoint();
		}
	}
	
	public void addSkillPointToMovement(){
		if(hasSkillPoints()){
			setMovementSkillPointsToAdd((getMovementSkillPointsToAdd() + 1));
			useSkillPoint();
		}
	}
	
	public void addSkillPointToAim(){
		if(hasSkillPoints()){
			setAimSkillPointsToAdd((getAimSkillPointsToAdd() + 1));
			useSkillPoint();
		}
	}
	
	private void useSkillPoint() {
		setSkillPointsLeft((getSkillPointsLeft() - 1));
	}
	
	public Boolean hasSkillPoints( ) {
		return getSkillPointsLeft() > 0;
	}
	
	public Integer getSkillPointsLeft() {
		return skillPointsLeft;
	}

	public void setSkillPointsLeft(Integer skillPointsLeft) {
		this.skillPointsLeft = skillPointsLeft;
	}

	public Integer getHitRecoverySkillPointsToAdd() {
		return hitRecoverySkillPointsToAdd;
	}

	public void setHitRecoverySkillPointsToAdd(Integer hitRecoverySkillPointsToAdd) {
		this.hitRecoverySkillPointsToAdd = hitRecoverySkillPointsToAdd;
	}

	public Integer getTotalLifeSkillPointsToAdd() {
		return totalLifeSkillPointsToAdd;
	}

	public void setTotalLifeSkillPointsToAdd(Integer totalLifeSkillPointsToAdd) {
		this.totalLifeSkillPointsToAdd = totalLifeSkillPointsToAdd;
	}

	public Integer getSpeedRechargeSkillPointsToAdd() {
		return speedRechargeSkillPointsToAdd;
	}

	public void setSpeedRechargeSkillPointsToAdd(Integer speedRechargeSkillPointsToAdd) {
		this.speedRechargeSkillPointsToAdd = speedRechargeSkillPointsToAdd;
	}

	public Integer getMovementSkillPointsToAdd() {
		return movementSkillPointsToAdd;
	}

	public void setMovementSkillPointsToAdd(Integer movementSkillPointsToAdd) {
		this.movementSkillPointsToAdd = movementSkillPointsToAdd;
	}

	public AuthenticationCtrl getAuthenticationCtrl() {
		return authenticationCtrl;
	}

	public void setAuthenticationCtrl(AuthenticationCtrl authenticationCtrl) {
		this.authenticationCtrl = authenticationCtrl;
	}

	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public Player getPlayer() {
		return getAuthenticationCtrl().getUser().getPlayer();
	}
	
	private void setPlayer(Player player) {
		getAuthenticationCtrl().getUser().setPlayer(player);
	}

	public Integer getAimSkillPointsToAdd() {
		return aimSkillPointsToAdd;
	}

	public void setAimSkillPointsToAdd(Integer aimSkillPointsToAdd) {
		this.aimSkillPointsToAdd = aimSkillPointsToAdd;
	}

}
