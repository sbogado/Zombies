package zombies.player;

import com.uqbar.vainilla.DeltaState;

import ar.edu.unq.games.vainillautils.AnimationRotateMoved;
import zombies.scene.Personaje;
import zombiescene.strategies.ControlDelJugador;

public class PersonajeMultiplayer extends Personaje{

	private Long idMultiplayer;
	private Boolean isShooting;
	private Boolean idDiying;
	private Boolean idChangingWweaponLeft;
	private Boolean idChangingWweaponRight;
	private Boolean isRecharging;
	
	
	
	public PersonajeMultiplayer(Long id,AnimationRotateMoved imagenParado, double x,
			double y, double xMin, double xMax, double yMin, double yMax,
			ControlDelJugador strategy) {
		super(imagenParado, x, y, xMin, xMax, yMin, yMax, strategy);
		this.idMultiplayer = id;
	}
	
	@Override
	public void update(DeltaState delta) {
		
		if(isShooting){
			this.disparar(delta);
		}
		
		if(idDiying){
			this.die(delta);
		}
		
		if(idChangingWweaponLeft){
			this.setPreviousWeapon();
		}
		
		if(idChangingWweaponRight){
			this.setNextWeapon();
		}
		
		if(isRecharging){
			this.recargar();
		}
		
		
		
		this.getAppearance().update(delta.getDelta());
	}

	public Long getIdMultiplayer() {
		return idMultiplayer;
	}
	
	
}
