package zombies.scene;

import java.util.List;

import zombies.scene.monstruos.Monstruo;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public class EnviadorDeOleada extends GameComponent<ZombiesScene>{

	private List<Monstruo> monstruos;
	private ZombiesScene scene;
	
	double contadorDeTiempo = 0;
	private double tiempoEntreCadaMonstruo;
	
	public EnviadorDeOleada(List<Monstruo> monstruos,ZombiesScene scene,double tiempoEntreCadaMonstruo){
		super();
		this.monstruos = monstruos;
		this.scene = scene;
		this.tiempoEntreCadaMonstruo = tiempoEntreCadaMonstruo;
	}
	
	@Override
	public void update(DeltaState deltaState){
		
		if(this.monstruos.size() <= 0){
			this.destroy();
			this.scene.addComponent(new DetectorDeFinDeOleada(this.scene));
		}
		
		if(this.contadorDeTiempo == 0){
			Monstruo monstruo = this.monstruos.remove(0);
			this.scene.addMonstruo(monstruo);
			monstruo.sonidoDeSalida();
		}
		
		this.contadorDeTiempo = this.contadorDeTiempo + deltaState.getDelta();
		
		if(this.contadorDeTiempo >= this.tiempoEntreCadaMonstruo){
			this.contadorDeTiempo = 0;
		}
		

		
	}
	
}
