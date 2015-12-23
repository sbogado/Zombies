package zombie.scene.rules;


import zombie.zombiescene.monstruostates.MonstruoState;
import zombies.scene.Bala;
import zombies.scene.Barricada;
import zombies.scene.GolpeDeMonstruo;
import zombies.scene.Pared;
import zombies.scene.Personaje;
import zombies.scene.ZombiesScene;
import zombies.scene.monstruos.Monstruo;
import ar.edu.unq.games.vainillautils.RectangleColision;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;


public class ColisionRule  {

	private GameComponent<?> component;
		
	public ColisionRule(GameComponent<?> component,String soundFile) {
		super();
		this.component = component;
	}
	
	public ColisionRule(GameComponent<?> component) {
		super();
		this.component = component;
	}
	
	public ColisionRule() {
		super();
	}

	public boolean mustApply(GameComponent<?> comp, Vector2D nuevaPosicion,
			ZombiesScene scene) {
		return this.colisionaCirculoRectangulo(this.component, comp, nuevaPosicion) ;
	}
	
	public boolean mustApplyFixBarricade(Personaje personaje, Barricada barricada,ZombiesScene scene) {
		return this.colisionaCirculoRectangulo(barricada, personaje) ;
	}
	
	public boolean mustApplyCirculoPersonaje(GameComponent<?> comp,Personaje personaje) {
		return this.colisionaCirculoPersonaje(comp,personaje) ;
	}
	
	public boolean mustApplyCirculoMonstruo(GameComponent<?> comp,Monstruo monstruo) {
		return this.colisionaCirculoMonstruo(comp,monstruo) ;
	}
	

		
	public boolean mustApplyPerson(GameComponent<?> comp, Vector2D nuevaPosicion,
			ZombiesScene scene) {
		return this.colisionaCirculoRectangulo(comp , this.component, nuevaPosicion) ;
	}
	
	public boolean mustApplyChoquePared(GameComponent<?> comp,Pared pared) {
		return this.colisionaCirculoRectangulo(pared ,comp) ;
	}
	
	public boolean mustApplyMonstruoPersonaje(Monstruo monstruo,Personaje personaje) {
		return this.colisionaMonstruoPersonaje(monstruo,personaje) ;
	}
	
	public boolean mustApplyBalaMonstruo(Bala bala,Monstruo monstruo,DeltaState delta) {
		boolean colisiona = false;
		double xIni = bala.getDireccion().getX();
		double yIni = bala.getDireccion().getY();
		for(int x= 10; x >= 1; x-- ){
			Vector2D nuevaPosicion = bala.nuevaPosicion(delta.getDelta(),(int) bala.getVelocidad()/x);
			if(CollisionDetector.INSTANCE.collidesCircleAgainstCircle(nuevaPosicion.getX(), nuevaPosicion.getY(), 5, monstruo.getX(),monstruo.getY(),(int) monstruo.getWidth()/4)){
			
				this.apply(bala,monstruo,monstruo.getScene(), nuevaPosicion);
				colisiona= true;
				break;
			}
			else{
				bala.getDireccion().setX(xIni);
				bala.getDireccion().setY(yIni);
			}
		}
		return colisiona;
	}
	
	public boolean mustApplyBalaPared(Bala bala,Pared pared, DeltaState deltaState) {
		return this.colisionaBalaPared(pared, bala, deltaState) ;
	}
	
	private boolean colisionaMonstruoPersonaje(Monstruo circulo1,Personaje circulo2) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstCircle(
				circulo1.getX(), circulo1.getY(), (int)circulo1.getWidth()/2
				, circulo2.getX(),circulo2.getY(),(int) circulo2.getWidth()/2);
	}
	
	private boolean colisionaCirculoPersonaje(GameComponent<?> circulo1,Personaje circulo2) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstCircle(
				circulo1.getX(), circulo1.getY(), (int)circulo1.getAppearance().getWidth()/2
				, circulo2.getX(),circulo2.getY(),(int) circulo2.getWidth()/2);
	}
	
	private boolean colisionaCirculoMonstruo(GameComponent<?> circulo1,Monstruo circulo2) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstCircle(
				circulo1.getX(), circulo1.getY(), (int)circulo1.getAppearance().getWidth()/2
				, circulo2.getX(),circulo2.getY(),(int) circulo2.getWidth()/2);
	}

	
	private boolean colisionaCirculoRectangulo(GameComponent<?> rectangulo,GameComponent<?> circulo,
			Vector2D nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nuevaPosicion.getX(), nuevaPosicion.getY(), circulo
						.getAppearance().getWidth() / 2, rectangulo.getX(),
						rectangulo.getY(), rectangulo.getAppearance().getWidth(), rectangulo
						.getAppearance().getHeight());
	}

	private boolean colisionaBalaPared(Pared pared,Bala bala, DeltaState deltaState) {
		boolean colisiona = false;
		double xIni = bala.getDireccion().getX();
		double yIni = bala.getDireccion().getY();
		for(int x= 10; x >= 1; x-- ){
			
			Vector2D nuevaPosicion = bala.nuevaPosicion(deltaState.getDelta(),(int) bala.getVelocidad()/x);
			if(this.rectangleConstainsPoint(pared.getX(), pared.getY(), pared.getWidth(),pared.getHeight(), nuevaPosicion.getX(),nuevaPosicion.getY())){
				this.applyBalaPared(bala, pared, nuevaPosicion);
				colisiona= true;
				break;
			}
			else{
				bala.getDireccion().setX(xIni);
				bala.getDireccion().setY(yIni);
			}
		}
		return colisiona;
	}
	
	public boolean rectangleConstainsPoint(double xR, double yR,double width, double height, double x ,double y){
		return xR < x && xR+width > x && yR < y && yR+height>y;
	}
	
	private boolean colisionaCirculoRectangulo(Barricada rectangulo,Personaje circulo) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				circulo.getX(), circulo.getY(),circulo.getAppearance().getWidth()/2,
				rectangulo.getX()+ 20,rectangulo.getY()+20, rectangulo.getWidth()+40, rectangulo.getHeight()+40);
	}
	
	private boolean colisionaCirculoRectangulo(Pared rectangulo,GameComponent<?> circulo) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				circulo.getX() + circulo.getAppearance().getWidth()/3, circulo.getY() + circulo.getAppearance().getHeight()/3,20,
				rectangulo.getX(),rectangulo.getY(), rectangulo.getWidth(), rectangulo.getHeight());
	}
	
	public boolean colisionaRectanguloRectanguloColision (Pared rectangle1,RectangleColision rectangle2) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(
				rectangle2.getX(), rectangle2.getY(),(int)rectangle2.getWidth(),(int)rectangle2.getHeight(),
				rectangle1.getX(),rectangle1.getY(), (int)rectangle1.getWidth(),(int) rectangle1.getHeight());
	}
	

	public void applyBalaPared(Bala bala, Pared pared,
			Vector2D nuevaPosicion) {
		bala.explotarPared(nuevaPosicion);
		
	}
	
	
	public void apply(Bala bala, Monstruo monstruo, ZombiesScene scene,Vector2D nuevaPosicion) {
		scene.getPuntaje().blockDistroyed(monstruo.getPoints());
		monstruo.recibirImpactoDeBala(bala);
		MonstruoState  state = monstruo.getMonstruoState();
		state.modoImpactado(state);
		bala.explotarZombie(nuevaPosicion);
	}
	
	public void applyPerson(Monstruo monstruo,Personaje personaje,DeltaState deltaState) {
		monstruo.moverAlaSiguientePosicionSimple((int)personaje.getX(),(int)personaje.getY(), deltaState);
		monstruo.golpear(personaje);
		
	}



	public void applyPersonDamage(Personaje personaje,GolpeDeMonstruo golpe) {
		personaje.recibirImpacto(golpe.getDanio());
		golpe.setGolpea();
	}
	
	public void applyBarricadaDamage(Barricada barricada,GolpeDeMonstruo golpe) {
		barricada.recibirImpacto(golpe.getDanio());
		golpe.setGolpea();
	}
	
	public RectangleColision buildRectangle(double x1 , double y1 , double x2 ,double y2){
		double xMin = Math.min(x1,x2);
		double yMin = Math.min(y1, y2);
		
		double xMax = Math.max(x1,x2);
		double yMax= Math.max(y1, y2);
		
		return new RectangleColision(xMin,yMin,xMax -xMin, yMax- yMin);
	}



}
