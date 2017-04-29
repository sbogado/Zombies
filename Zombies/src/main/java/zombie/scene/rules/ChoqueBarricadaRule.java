package zombie.scene.rules;

import zombies.scene.components.Barricada;
import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ChoqueBarricadaRule {
	
	
	public boolean mustApplyBarricade(Barricada barricada,Monstruo monstruo , Vector2D nuevaPosicionMonstruo) {
		return this.colisionaCirculoRectangulo(barricada , monstruo, nuevaPosicionMonstruo) ;
	}
	
	private boolean colisionaCirculoRectangulo(Barricada rectangulo,GameComponent<?> circulo,
			Vector2D nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				circulo.getX(), circulo.getY(), circulo
						.getAppearance().getWidth() / 4, rectangulo.getX(),
						rectangulo.getY(), rectangulo.getWidth(), rectangulo.getHeight());
	}
	
	public void applyMonstruoBarricada(Monstruo mostro, Barricada barricada, double tiempoMuertoDeGolpe) {
		mostro.setYBack();	
		mostro.setXBack();	
		barricada.recibirImpacto(mostro.getDanio());
		mostro.golpear(barricada);
		
	}

}
