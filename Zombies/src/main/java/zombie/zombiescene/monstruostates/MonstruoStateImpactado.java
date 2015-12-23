package zombie.zombiescene.monstruostates;

import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

import com.uqbar.vainilla.DeltaState;

public class MonstruoStateImpactado extends MonstruoState{

	double tiempoImpactado;
	private MonstruoState estadoAnterior;
	
	public MonstruoStateImpactado(Monstruo monstruo, MonstruoState state) {
		super(monstruo);
		this.tiempoImpactado = 0;
		this.estadoAnterior = state;
	}

	@Override
	public void accionar(DeltaState delta) {
		this.tiempoImpactado = this.tiempoImpactado + delta.getDelta();
		if(this.tiempoImpactado >= this.getMonstruo().getTiempoDeRecuperacionDeImpacto() ){
			this.getMonstruo().setMonstruoState(estadoAnterior);
			this.getMonstruo().setAppearance(estadoAnterior.getImage());
		}	
	}


	@Override
	public AnimationLowCost getImage() {
		return this.getMonstruo().getImagenImpactado();
	}
	
}
