package zombies.scene;


import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public abstract class ParteDelMapa extends GameComponent<ZombiesScene>{

	public abstract int getPesoEnGrafo();
	public abstract int getHeight();
	public abstract int getWidth();
	
	public ParteDelMapa(Rectangle rectangle, double x, double y) {
		super(rectangle, x, y);
	}
	
	public void setDistanciasDeParedEnGrafo(){
		int sumaDePixeles = 0;
		
		while(this.getHeight() > sumaDePixeles){
			this.setDistanciasDeParedEnGrafoAncho(this.getY()+sumaDePixeles);
			sumaDePixeles = sumaDePixeles + this.getScene().getCantidadDePixelesPorVerticeY();	
		}
	}

	public void setDistanciasDeParedEnGrafoAncho(double y){
		int sumaDePixeles = 0;
		
		while(this.getWidth() > sumaDePixeles){
			int verticeActual = this.getScene().getMapa().calcularVerticeConXeY(this.getX()+sumaDePixeles,y);
			this.setDistanciasEnGrafoDeUnVertice(verticeActual);
			sumaDePixeles = sumaDePixeles + this.getScene().getCantidadDePixelesPorVerticeX();	
		}
	}
	


	public void setDistanciasEnGrafoDeUnVertice(int vertice){
		this.getScene().getMapa().modificarPesoDeVertice(vertice, this.getScene().getCantidadDeVerticesPorFilaGrafo(), this.getPesoEnGrafo());
		
	}

}
