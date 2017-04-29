package zombie.scene.rules;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.colissions.CollisionDetector;

import zombies.scene.components.Barricada;
import zombies.scene.components.Pared;
import zombies.scene.components.Personaje;
import zombies.scene.monstruos.Monstruo;

public class ChoqueParedRuleMonstruo implements ChoqueParedRule{

	private Monstruo mostro;
	
	public ChoqueParedRuleMonstruo(Monstruo mostro) {
		super();
		this.mostro = mostro;
	}

	
	@Override
	public void checkChoquePared(Pared pared) {
		this.colisionIndividuoPared(pared) ;
		
	}

	@Override
	public void colisionIndividuoPared(Pared pared) {
		double 	pointTopX 		= mostro.getX()+this.mostro.getAppearance().getWidth()/2;
		double	pointTopY 		= this.mostro.getY()+this.mostro.getAppearance().getHeight()/3;
		
		double	largoVertical 	= this.mostro.getAppearance().getHeight()/3;
		
		double  pointLeftX 		= mostro.getX()+this.mostro.getAppearance().getWidth()/3;
		double 	pointLeftY 		= this.mostro.getY()+this.mostro.getAppearance().getHeight()/2;
		
		double  largoHorizontal = this.mostro.getAppearance().getWidth()/3;
		
		
		boolean  verticalMidleLineCollidesAgainstWall = CollisionDetector.INSTANCE.collidesRectAgainstRect(pared.getX(), pared.getY(),((int) pared.getWidth()), ((int)pared.getHeight()),
				pointTopX-8, pointTopY, 16, ((int) largoVertical));
		
		boolean  horizontalMidleLineCollidesAgainstWall =CollisionDetector.INSTANCE.collidesRectAgainstRect(pared.getX(), pared.getY(),((int) pared.getWidth()), ((int)pared.getHeight()),
				pointLeftX , pointLeftY-8,((int) largoHorizontal),16);
		
		if( horizontalMidleLineCollidesAgainstWall){
			//this.mostro.setX(((Individuo) this.mostro).getPosicionAnterior().getX());
			applyX(mostro, (Barricada) pared);
		}

		if( verticalMidleLineCollidesAgainstWall){
			//this.mostro.setY(((Individuo) this.mostro).getPosicionAnterior().getY());
			applyY(mostro, (Barricada) pared);
		}	
		
	}


	@Override
	public void applyY(Monstruo mostro, Barricada barricada) {
		mostro.setYBack();	
		barricada.recibirImpacto(mostro.getDanio());
	}

	@Override
	public void applyX(Monstruo mostro, Barricada barricada) {
		mostro.setXBack();	
		barricada.recibirImpacto(mostro.getDanio());
	}

	@Override
	public void applyY(Personaje mostro, Pared pared) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyX(Personaje mostro, Pared pared) {
		// TODO Auto-generated method stub
		
	}



}
