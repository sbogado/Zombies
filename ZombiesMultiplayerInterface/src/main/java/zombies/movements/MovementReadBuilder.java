package zombies.movements;

import zombies.scene.ZombiesScene;

public class MovementReadBuilder {

	
	
	/********************************************************* SENDERS ********************************************************************/
	
	public String sendMoveBuild(double positionX, double positionY){
			return
			MovementReadProperties.MOVE+
			MovementReadProperties.SEPARATOR+positionX+
			MovementReadProperties.SEPARATOR+positionY;

	}
	
	public String sendShotBuild(double positionX, double positionY,double dirPersonajeX , double dirPersonajeY ,double rotation){
		return MovementReadProperties.SHOT+
					MovementReadProperties.SEPARATOR+positionX+
					MovementReadProperties.SEPARATOR+positionY+
					MovementReadProperties.SEPARATOR+dirPersonajeX+
					MovementReadProperties.SEPARATOR+dirPersonajeY+
					MovementReadProperties.SEPARATOR+rotation;

	}
	
	public String sendDieBuild(){
		return MovementReadProperties.DIE;
	}
	public String sendRotationBuild(double rotation){
		return MovementReadProperties.ROTATION+rotation;
	}
	
	public String sendRechargeBuild(){
		return MovementReadProperties.RECHARGE;
	}
	
	public String sendWeaponChangeLeftBuild(){
		return MovementReadProperties.WEAPON_CHANGE_LEFT;
	}
	
	public String sendWeaponChangeRightBuild(){
		return MovementReadProperties.WEAPON_CHANGE_RIGHT;
	}
	
	
	/********************************************************* PARSERS ********************************************************************/
	
	public void parsePlayerAddedMessage(String substring, ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseWeaponChangeRightMessage(String substring,
			ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseWeaponChangeLeftMessage(String substring,
			ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseRechargeMessage(String substring, ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseRotationMessage(String substring, ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseDieMessage(String substring, ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseShotMessage(String substring, ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}

	public void parseMoveMessage(String substring, ZombiesScene scene) {
		// TODO Auto-generated method stub
		
	}
	
	
}
