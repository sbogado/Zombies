package zombie.zombiescene.monstruostates;

import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

import com.uqbar.vainilla.DeltaState;


public class MonstruoStateGolpeando extends MonstruoState {

	public MonstruoStateGolpeando(Monstruo monstruo) {
		super(monstruo);
	}


	@Override
	public void accionar(DeltaState deltaState) {
		this.getMonstruo().setTiempoMuertoDeGolpe(this.getMonstruo().getTiempoMuertoDeGolpe() - deltaState.getDelta());
		if(this.getMonstruo().getTiempoMuertoDeGolpe() <= 0){
			this.modoCaminando();
		}
	}

	@Override
	public void modoGolpeando() {
	}
	
	@Override
	public AnimationLowCost getImage() {
		return this.getMonstruo().getImagenGolpeandoPersonaje();
	}

}
