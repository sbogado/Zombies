package zombie.zombiescene.monstruostates;

import java.awt.Point;

import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

import com.uqbar.vainilla.DeltaState;

public class MonstruoStateCaminando extends MonstruoState{

	private int indiceCamino;



	public MonstruoStateCaminando(Monstruo monstruo) {
		super(monstruo);
		this.getMonstruo().obtenerCaminoHaciaPersonaje(this.getMonstruo().getScene().getPersonaje());
		this.indiceCamino = 0;
	}


	@Override
	public void accionar(DeltaState deltaState) {
		
		if(this.getMonstruo().getCaminoHaciaPersonaje().size() > this.indiceCamino){
			Point point = this.getMonstruo().getCaminoHaciaPersonaje().get(this.indiceCamino);

			this.getMonstruo().moverAlaSiguientePosicion((int)point.getX(),(int)point.getY(), deltaState);

		}
		else{
			this.getMonstruo().obtenerCaminoHaciaPersonaje(this.getMonstruo().getScene().getPersonaje());
			this.indiceCamino = 0;
		}
		
//		if(this.getMonstruo().getTiempoDePersecucion() <= 0){
//			this.getMonstruo().setTiempoDePersecucion(2);
//			this.getMonstruo().obtenerCaminoHaciaPersonaje(this.getMonstruo().getScene().getPersonaje());
//		}
//		else
//		{
//			this.getMonstruo().setTiempoDePersecucion(this.getMonstruo().getTiempoDePersecucion() -deltaState.getDelta());
//		}
		
		if(this.getMonstruo().getColisionRule().mustApplyMonstruoPersonaje(this.getMonstruo(),this.getMonstruo().getScene().getPersonaje())){
			this.modoGolpeando();
			this.getMonstruo().getColisionRule().applyPerson(this.getMonstruo(),this.getMonstruo().getScene().getPersonaje(),deltaState);
		}
		
	}


	@Override
	public void modoCaminando() {
		
	}
	

	public void aumentarIndiceCamino(){
		this.indiceCamino = this.indiceCamino + 1;
	}

	@Override
	public AnimationLowCost getImage() {
		return this.getMonstruo().getImagenCaminando();
	}

}
