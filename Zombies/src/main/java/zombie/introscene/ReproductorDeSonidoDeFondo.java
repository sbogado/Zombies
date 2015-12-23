package zombie.introscene;

import sound.SoundPlayerZombie;
import sound.SoundZombie;
import sound.SoundBuilderZombie;
import zombie.Zombies;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class ReproductorDeSonidoDeFondo extends GameComponent<GameScene> {
	
	private SoundZombie sonidoFondo ;
	private double tiempoDeReproduccion = 25;
	
	public ReproductorDeSonidoDeFondo(){
		SoundZombie sound= (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("sonido de fondo.wav")).getSound();
		this.sonidoFondo = sound;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		Game game = this.getScene().getGame();
		if(game != null){
			if(!((Zombies) game).isPaused()){	
				if(tiempoDeReproduccion >= 25){
					sonidoFondo.play(5);
					tiempoDeReproduccion = 0;
				}
				this.tiempoDeReproduccion = this.tiempoDeReproduccion + deltaState.getDelta();
			}
		}
	}
		
	public void reanudar(){	
		SoundPlayerZombie.reanudar();		
	}
	
	public void pausar(){	
		SoundPlayerZombie.pausar();		
	}
	

}
