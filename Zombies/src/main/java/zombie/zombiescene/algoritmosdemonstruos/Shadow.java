package zombie.zombiescene.algoritmosdemonstruos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shadow extends AlgoritmoDeBusqueda {

	@Override
	public List<Point> getCamino(int xinicial, int yinicial, int xdestino,int ydestino) {
		List<Point> puntos = new ArrayList<Point>();
		puntos.add(new Point(xdestino,ydestino));
		return puntos;
	}



}
