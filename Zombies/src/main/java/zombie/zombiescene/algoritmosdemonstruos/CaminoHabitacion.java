package zombie.zombiescene.algoritmosdemonstruos;

import java.awt.Point;
import java.util.List;

public class CaminoHabitacion {

	private List<Point> points;
	private Habitacion habitacion;

	public CaminoHabitacion(List<Point> points, Habitacion habitacion){
		this.points = points;
		this.habitacion = habitacion;
	}

	public List<Point> getPoints() {
		return points;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	
	
}
