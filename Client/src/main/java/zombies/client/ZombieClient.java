package zombies.client;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import zombies.movements.MovementReadBuilder;
import zombies.movements.MovementReadProperties;
import zombies.scene.ZombiesScene;

import multiplayer.framework.ClientAbstract;
import multiplayer.implementation.Client;

public class ZombieClient extends Client {

	private static final Logger logger = Logger.getLogger( ZombieClient.class.getName() );
	
	private MovementReadBuilder movementReadBuilder;
	


	public ZombieClient(Socket socket) {
		super(socket);
		
	}
	
	public void sendMove(double positionX, double positionY){
		try {
			this.write(this.movementReadBuilder.sendMoveBuild(positionX, positionY));
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un Move: ",e);
		}
	}
	
	public void sendShot(double positionX, double positionY,double dirPersonajeX , double dirPersonajeY ,double rotation){
		try {
			this.write(this.movementReadBuilder.sendShotBuild(positionX, positionY, dirPersonajeX, dirPersonajeY, rotation));
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un Shot: ",e);
		}
	}
	
	public void sendDie(){
		try {
			this.write(this.movementReadBuilder.sendDieBuild());
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un Die: ",e);
		}
	}
	public void sendRotation(double rotation){
		try {
			this.write(this.movementReadBuilder.sendRotationBuild(rotation));
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un Rotation: ",e);
		}
	}
	
	public void sendRecharge(){
		try {
			this.write(this.movementReadBuilder.sendRechargeBuild());
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un Recharge: ",e);
		}
	}
	
	public void sendWeaponChangeLeft(){
		try {
			this.write(this.movementReadBuilder.sendWeaponChangeLeftBuild());
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un WeaponChangeLeft: ",e);
		}
	}
	
	public void sendWeaponChangeRight(){
		try {
			this.write(this.movementReadBuilder.sendWeaponChangeRightBuild());
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error al escribir desde el cliente un WeaponChangeRight: ",e);
		}
	}
	
	public void readAllData(ZombiesScene scene ){
	
		String message;
		try {
			message = this.read();
			while(!message.equals("")){
				this.parseMessage(message,scene);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	private void parseMessage(String message, ZombiesScene scene) {
		
		String action = message.substring(0);
		int messageLastIndex = message.length() - 1;
		
		if(action.equals(MovementReadProperties.MOVE)){
			 this.movementReadBuilder.parseMoveMessage(message.substring(1,messageLastIndex),scene);
			 return;
		}
		
		if(action.equals(MovementReadProperties.SHOT)){
			this.movementReadBuilder.parseShotMessage(message.substring(1,messageLastIndex),scene); 
			return;
		}
		
		if(action.equals(MovementReadProperties.DIE)){
			this.movementReadBuilder.parseDieMessage(message.substring(1,messageLastIndex),scene); 
			return;
		}
		
		if(action.equals(MovementReadProperties.ROTATION)){
			this.movementReadBuilder.parseRotationMessage(message.substring(1,messageLastIndex),scene); 
			return;
		}
		
		if(action.equals(MovementReadProperties.RECHARGE)){
			this.movementReadBuilder.parseRechargeMessage(message.substring(1,messageLastIndex),scene); 
		}
		
		if(action.equals(MovementReadProperties.WEAPON_CHANGE_LEFT)){
			this.movementReadBuilder.parseWeaponChangeLeftMessage(message.substring(1,messageLastIndex),scene); 
			return;
		}
		
		if(action.equals(MovementReadProperties.WEAPON_CHANGE_RIGHT)){
			this.movementReadBuilder.parseWeaponChangeRightMessage(message.substring(1,messageLastIndex),scene); 
			return;
		}
		
		if(action.equals(MovementReadProperties.PLAYER_ADDED)){
			 this.movementReadBuilder.parsePlayerAddedMessage(message.substring(1,messageLastIndex),scene);
			 return;
		}
	}

	public MovementReadBuilder getMovementReadBuilder() {
		return movementReadBuilder;
	}

	public void setMovementReadBuilder(MovementReadBuilder movementReadBuilder) {
		this.movementReadBuilder = movementReadBuilder;
	}
	
	
	

}
