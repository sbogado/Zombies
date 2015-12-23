package zombie.zombiescene.algoritmosdemonstruos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private List<CaminoHabitacion> caminos;
	
	public Habitacion(int x1,int y1,int x2,int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.caminos = new ArrayList<CaminoHabitacion>();
		
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	
	public List<CaminoHabitacion> getCaminos() {
		return caminos;
	}

	public boolean containsPoint(int x, int y){
		return x1 < x && x2 > x && y1 < y && y2 >y;
	}
	
	public List<Point> getCaminoAHabitacion(Habitacion habitacion){
		List<Point> caminoHabitacion = new ArrayList<Point>();
		
		for(CaminoHabitacion camino : this.caminos){
			if(camino.getHabitacion() == habitacion){
				caminoHabitacion = camino.getPoints();
				break;
			}
		}
		
		return caminoHabitacion;
	}
	
}
