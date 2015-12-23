package zombie.scene.rules;

import zombies.scene.Barricada;
import zombies.scene.Pared;
import zombies.scene.Personaje;
import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.RectangleColision;

import com.uqbar.vainilla.colissions.CollisionDetector;

public class ChoqueParedRulePersonaje implements ChoqueParedRule{
	
	private Personaje personaje;

	public ChoqueParedRulePersonaje(Personaje personaje) {
		super();
		this.personaje = personaje;
	}

	@Override
	public void checkChoquePared(Pared pared) {
		this.colisionIndividuoPared(pared) ;
	}

	@Override
	public void colisionIndividuoPared(Pared pared) {

		double 	pointTopX 		= personaje.getX()-17;
		double	pointTopY 		= this.personaje.getY()-20;
		
		double	largoVertical 	= 25;
		
		double  pointLeftX 		= personaje.getX()-20;
		double 	pointLeftY 		= this.personaje.getY()-17;
		
		double  largoHorizontal = 25;
		
		
		boolean  verticalMidleLineCollidesAgainstWall = CollisionDetector.INSTANCE.collidesRectAgainstRect(pared.getX(), pared.getY(),((int) pared.getWidth()), ((int)pared.getHeight()),
				pointTopX, pointTopY, 20, ((int) largoVertical));
		
		boolean  horizontalMidleLineCollidesAgainstWall =CollisionDetector.INSTANCE.collidesRectAgainstRect(pared.getX(), pared.getY(),((int) pared.getWidth()), ((int)pared.getHeight()),
				pointLeftX , pointLeftY,((int) largoHorizontal),20);
		
		
		if( horizontalMidleLineCollidesAgainstWall){
			this.applyX(this.personaje, pared);
		}

		if( verticalMidleLineCollidesAgainstWall){
			this.applyY(this.personaje, pared);
		}
		if(this.colisionaRectanguloRectanguloColision(pared, buildRectangle(this.personaje.getPosicionAnterior().getX(),this.personaje.getPosicionAnterior().getY(),this.personaje.getX(),this.personaje.getY()))){
			this.applyX(this.personaje, pared);
			this.applyY(this.personaje, pared);
		}

		
	}

	public void applyY(Personaje personaje, Pared pared) {
		personaje.setYBack();	
	}
	
	public void applyX(Personaje personaje, Pared pared) {
		personaje.setXBack();
	}

	@Override
	public void applyY(Monstruo mostro, Barricada barricada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyX(Monstruo mostro, Barricada barricada) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean colisionaRectanguloRectanguloColision (Pared rectangle1,RectangleColision rectangle2) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(
				rectangle2.getX(), rectangle2.getY(),(int)rectangle2.getWidth(),(int)rectangle2.getHeight(),
				rectangle1.getX(),rectangle1.getY(), (int)rectangle1.getWidth(),(int) rectangle1.getHeight());
	}
	
	public RectangleColision buildRectangle(double x1 , double y1 , double x2 ,double y2){
		double xMin = Math.min(x1,x2);
		double yMin = Math.min(y1, y2);
		
		double xMax = Math.max(x1,x2);
		double yMax= Math.max(y1, y2);
		
		return new RectangleColision(xMin,yMin,xMax -xMin, yMax- yMin);
	}

}
