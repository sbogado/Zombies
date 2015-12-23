package zombie.zombiescene.monstruostates;

import zombie.Zombies;
import zombies.scene.Barricada;
import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.AnimationLowCost;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;

public class MonstruoStateEntrando extends MonstruoState{

		public MonstruoStateEntrando(Monstruo monstruo) {
			super(monstruo);
			double rotation = Zombies.calculateRotation(this.getMonstruo().getX(),this.getMonstruo().getY(),this.getMonstruo().getPuntoDeAtaque().getX() ,this.getMonstruo().getPuntoDeAtaque().getY());	
			this.getMonstruo().rotate(rotation-this.getMonstruo().getRotation());
		}


		@Override
		public void accionar(DeltaState delta) {
			double puntoDeAtauqeX = this.getMonstruo().getPuntoDeAtaque().getX();
			double puntoDeAtauqeY = this.getMonstruo().getPuntoDeAtaque().getY();
			Vector2D nuevaPosicion = this.getMonstruo().nuevaPosicion(delta);
			
			
			this.getMonstruo().getDesplazamientoLibreRule().apply(this.getMonstruo(), nuevaPosicion, this.getMonstruo().getScene());
			if((this.getMonstruo().getX() >= puntoDeAtauqeX-50 && this.getMonstruo().getX() <= puntoDeAtauqeX + 50) && (this.getMonstruo().getY() >= puntoDeAtauqeY-10  && this.getMonstruo().getY() <= puntoDeAtauqeY +10)){
				this.modoCaminando();
			}
			
			for (Barricada barricada : this.getMonstruo().getScene().getBarricadas()) {
				if(barricada.isNotBroken() && this.getMonstruo().getColisionBarricadeRule().mustApplyBarricade(barricada,this.getMonstruo(),nuevaPosicion)){
					this.modoGolpeandoBarricada();
					this.getMonstruo().getColisionBarricadeRule().applyMonstruoBarricada(this.getMonstruo(), barricada, this.getMonstruo().getTiempoMuertoDeGolpe());
					break;
				}
			}
			
		}

		
		@Override
		public AnimationLowCost getImage() {
			return this.getMonstruo().getImagenCaminando();
		}

}
