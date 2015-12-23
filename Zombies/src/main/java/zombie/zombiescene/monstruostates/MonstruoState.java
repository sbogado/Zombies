package zombie.zombiescene.monstruostates;

import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

import com.uqbar.vainilla.DeltaState;

public abstract class MonstruoState {
	
	private Monstruo monstruo;

	public MonstruoState(Monstruo monstruo){
		this.setMonstruo(monstruo);
		AnimationLowCost animation = this.getImage();
		animation.rotate(monstruo.getRotation());
		monstruo.setAppearance(animation);
		
	}
	
	public void setMonstruo(Monstruo monstruo){
		this.monstruo = monstruo;
	}

	public Monstruo  getMonstruo(){
		return this.monstruo;
	}
	
	public abstract void accionar(DeltaState delta);
	
	public void modoGolpeando() {
		this.getMonstruo().setMonstruoState(new MonstruoStateGolpeando(this.getMonstruo()));
	}
	
	public void modoCaminando() {
		this.getMonstruo().setMonstruoState(new MonstruoStateCaminando(this.getMonstruo()));
		
	}
	
	
	public void modoImpactado(MonstruoState state) {
		this.getMonstruo().setMonstruoState(new MonstruoStateImpactado(this.getMonstruo(),state));
	}
	
	
	public void modoEntrando() {
		this.getMonstruo().setMonstruoState(new MonstruoStateEntrando(this.getMonstruo()));
	}
	
	public void modoGolpeandoBarricada() {
		this.getMonstruo().setMonstruoState(new MonstruoStateGolpeandoBarricada(this.getMonstruo()));
	}
	
	public abstract AnimationLowCost getImage();
}
