package zombie.zombiescene.algoritmosdemonstruos;

import java.util.List;

public abstract class BusquedaPorHabitacion extends AlgoritmoDeBusqueda {

	List<Habitacion> habitaciones;
	
	public BusquedaPorHabitacion(List<Habitacion> habitaciones){
		this.habitaciones = habitaciones;
	}
	
	public Habitacion buscarHabitacionContenedora(int x, int y){
		Habitacion habitacionContenedora = null;
		
		for(Habitacion habitacion : this.habitaciones){
			if(habitacion.containsPoint(x, y)){
				habitacionContenedora = habitacion;
				break;
			}
		}
		
		return habitacionContenedora;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

}
