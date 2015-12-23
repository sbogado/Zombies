package zombie.zombiescene.monstruostates;

import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

import com.uqbar.vainilla.DeltaState;

public class MonstruoStateGolpeandoBarricada extends MonstruoState {

		public MonstruoStateGolpeandoBarricada(Monstruo monstruo) {
			super(monstruo);
		}


		@Override
		public void accionar(DeltaState deltaState) {
			this.getMonstruo().setTiempoMuertoDeGolpe(this.getMonstruo().getTiempoMuertoDeGolpe() - deltaState.getDelta());
			if(this.getMonstruo().getTiempoMuertoDeGolpe() <= 0){
				this.modoEntrando();
			}
		}

		@Override
		public void modoGolpeando() {
		}
		
		@Override
		public AnimationLowCost getImage() {
			return this.getMonstruo().getImagenGolpeandoBarricada();
		}
}
