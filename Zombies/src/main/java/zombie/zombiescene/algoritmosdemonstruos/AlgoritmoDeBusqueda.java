package zombie.zombiescene.algoritmosdemonstruos;

import java.awt.Point;
import java.util.List;

public abstract class AlgoritmoDeBusqueda {

	public  abstract List<Point> getCamino( int xinicial, int yinicial,int xdestino, int ydestino);
}
