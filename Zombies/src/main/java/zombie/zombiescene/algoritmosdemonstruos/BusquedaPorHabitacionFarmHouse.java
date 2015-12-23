package zombie.zombiescene.algoritmosdemonstruos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class BusquedaPorHabitacionFarmHouse extends BusquedaPorHabitacion {

	private Habitacion h1;
	private Habitacion h2;
	private Habitacion h3;
	private Habitacion h4;
	private Habitacion h5;
	private Habitacion h6;
	private Habitacion h7;
	
	public static final BusquedaPorHabitacionFarmHouse INSTANCE = new BusquedaPorHabitacionFarmHouse();
	
	private BusquedaPorHabitacionFarmHouse() {
		super(new ArrayList<Habitacion>());

		this.h1 = new Habitacion(0 , 300 , 365 ,700);
		this.h2 = new Habitacion(325, 340 , 700 , 600);
		this.h3 = new Habitacion(376 , 10 , 700 , 320);
		this.h4 = new Habitacion(730 , 340 , 1070 , 600);
		this.h5 = new Habitacion(730 , 10 ,  1070 , 350);
		this.h6 = new Habitacion(699 , 50 , 731 , 150);
		this.h7 = new Habitacion(699 , 400 ,  731 , 515);
		
		//Habitacion 1 Caminos
		List<Point> caminoch1ch2 = new ArrayList<Point>();
		caminoch1ch2.add(new Point(300 , 546));
		caminoch1ch2.add(new Point(400,546));
		
		List<Point> caminoch1ch3 = new ArrayList<Point>();
		caminoch1ch3.add(new Point(300 , 546));
		caminoch1ch3.add(new Point(400,546));
		
		List<Point> caminoch1ch4 = new ArrayList<Point>();
		caminoch1ch4.add(new Point(300 , 546));
		caminoch1ch4.add(new Point(400,546));
		caminoch1ch4.add(new Point(660 , 460));
		caminoch1ch4.add(new Point(783 ,460));
		
		List<Point> caminoch1ch5 = new ArrayList<Point>();
		caminoch1ch5.add(new Point(300 , 546));
		caminoch1ch5.add(new Point(400,546));
		caminoch1ch5.add(new Point(665 ,108));
		caminoch1ch5.add(new Point(776  ,108));
		
		List<Point> caminoch1ch6 = new ArrayList<Point>();
		caminoch1ch6.add(new Point(300 , 546));
		caminoch1ch6.add(new Point(400,546));
		caminoch1ch6.add(new Point(665 ,108));

		
		List<Point> caminoch1ch7 = new ArrayList<Point>();
		caminoch1ch7.add(new Point(300 , 546));
		caminoch1ch7.add(new Point(400,546));
		caminoch1ch7.add(new Point(660 , 460));
		
		CaminoHabitacion ch1h2 = new CaminoHabitacion(caminoch1ch2,this.h2);
		CaminoHabitacion ch1h3 = new CaminoHabitacion(caminoch1ch3,this.h3);
		CaminoHabitacion ch1h4 = new CaminoHabitacion(caminoch1ch4,this.h4);
		CaminoHabitacion ch1h5 = new CaminoHabitacion(caminoch1ch5,this.h5);
		CaminoHabitacion ch1h6 = new CaminoHabitacion(caminoch1ch6,this.h6);
		CaminoHabitacion ch1h7 = new CaminoHabitacion(caminoch1ch7,this.h7);
		this.h1.getCaminos().add(ch1h2); 
		this.h1.getCaminos().add(ch1h3);
		this.h1.getCaminos().add(ch1h4); 
		this.h1.getCaminos().add(ch1h5); 
		this.h1.getCaminos().add(ch1h6); 
		this.h1.getCaminos().add(ch1h7); 
		
		//Habitacion 2 Caminos
		List<Point> caminoch2ch1 = new ArrayList<Point>();
		caminoch2ch1.add(new Point(400,546));
		caminoch2ch1.add(new Point(300,546));
		
		List<Point> caminoch2ch4 = new ArrayList<Point>();
		caminoch2ch4.add(new Point(660 , 460));
		caminoch2ch4.add(new Point(783 ,460));
		
		List<Point> caminoch2ch5 = new ArrayList<Point>();
		caminoch2ch5.add(new Point(665 ,108));
		caminoch2ch5.add(new Point(776  ,108));
		
		List<Point> caminoch2ch6 = new ArrayList<Point>();
		caminoch2ch6.add(new Point(665 ,108));
		
		List<Point> caminoch2ch7 = new ArrayList<Point>();
		caminoch2ch7.add(new Point(660 , 460));
		
		CaminoHabitacion ch2h1 = new CaminoHabitacion(caminoch2ch1,this.h1);
		CaminoHabitacion ch2h4 = new CaminoHabitacion(caminoch2ch4,this.h4);
		CaminoHabitacion ch2h5 = new CaminoHabitacion(caminoch2ch5,this.h5);
		CaminoHabitacion ch2h6 = new CaminoHabitacion(caminoch2ch6,this.h6);
		CaminoHabitacion ch2h7 = new CaminoHabitacion(caminoch2ch7,this.h7);
		
		this.h2.getCaminos().add(ch2h1); 
		this.h2.getCaminos().add(ch2h4); 
		this.h2.getCaminos().add(ch2h5); 
		this.h2.getCaminos().add(ch2h6); 
		this.h2.getCaminos().add(ch2h7); 		
		//Habitacion 3 Caminos
		List<Point> caminoch3ch1 = new ArrayList<Point>();
		caminoch3ch1.add(new Point(400,546));
		caminoch3ch1.add(new Point(300,546));
			
		
		List<Point> caminoch3ch4 = new ArrayList<Point>();
		caminoch3ch4.add(new Point(660 , 460));
		caminoch3ch4.add(new Point(783 ,460));
		
		List<Point> caminoch3ch5 = new ArrayList<Point>();
		caminoch3ch5.add(new Point(665 ,108));
		caminoch3ch5.add(new Point(776  ,108));
		
		CaminoHabitacion ch3h1 = new CaminoHabitacion(caminoch3ch1,this.h1);
		CaminoHabitacion ch3h4 = new CaminoHabitacion(caminoch3ch4,this.h4);
		CaminoHabitacion ch3h5 = new CaminoHabitacion(caminoch3ch5,this.h5);
		this.h3.getCaminos().add(ch3h1); 
		this.h3.getCaminos().add(ch3h4); 
		this.h3.getCaminos().add(ch3h5); 
		
		//Habitacion 4 Caminos
		List<Point> caminoch4ch1 = new ArrayList<Point>();
		caminoch4ch1.add(new Point(783 ,460));
		caminoch4ch1.add(new Point(660 , 460));
		caminoch4ch1.add(new Point(400,546));
		caminoch4ch1.add(new Point(300 , 546));
		
		List<Point> caminoch4ch2 = new ArrayList<Point>();
		caminoch4ch2.add(new Point(783 ,460));
		caminoch4ch2.add(new Point(660 , 460));
	
		
		List<Point> caminoch4ch3 = new ArrayList<Point>();
		caminoch4ch3.add(new Point(896 , 375));
		caminoch4ch3.add(new Point(896 , 267));
		caminoch4ch3.add(new Point(776 , 108));
		caminoch4ch3.add(new Point(665 ,108));
		
		List<Point> caminoch4ch5 = new ArrayList<Point>();
		caminoch4ch5.add(new Point(896 , 375));
		caminoch4ch5.add(new Point(896 , 267));
		
		List<Point> caminoch4ch6 = new ArrayList<Point>();
		caminoch4ch6.add(new Point(896 , 375));
		caminoch4ch6.add(new Point(896 , 267));
		caminoch4ch6.add(new Point(776 , 108));
		
		List<Point> caminoch4ch7 = new ArrayList<Point>();
		caminoch4ch7.add(new Point(783 ,460));
		
		CaminoHabitacion ch4h1 = new CaminoHabitacion(caminoch4ch1,this.h1);
		CaminoHabitacion ch4h2 = new CaminoHabitacion(caminoch4ch2,this.h2);
		CaminoHabitacion ch4h3 = new CaminoHabitacion(caminoch4ch3,this.h3);
		CaminoHabitacion ch4h5 = new CaminoHabitacion(caminoch4ch5,this.h5);
		CaminoHabitacion ch4h6 = new CaminoHabitacion(caminoch4ch6,this.h6);
		CaminoHabitacion ch4h7 = new CaminoHabitacion(caminoch4ch7,this.h7);
		this.h4.getCaminos().add(ch4h1); 
		this.h4.getCaminos().add(ch4h2); 
		this.h4.getCaminos().add(ch4h3); 
		this.h4.getCaminos().add(ch4h5); 
		this.h4.getCaminos().add(ch4h6); 
		this.h4.getCaminos().add(ch4h7); 
		
		//Habitacion 5 Caminos
		List<Point> caminoch5ch1 = new ArrayList<Point>();
		caminoch5ch1.add(new Point(776 , 108));
		caminoch5ch1.add(new Point(665 ,108));
		caminoch5ch1.add(new Point(400,546));
		caminoch5ch1.add(new Point(300 , 546));
		
		List<Point> caminoch5ch2 = new ArrayList<Point>();
		caminoch5ch2.add(new Point(896 , 267));
		caminoch5ch2.add(new Point(896 , 375));
		caminoch5ch2.add(new Point(783 ,460));
		caminoch5ch2.add(new Point(660 , 460));
	
		
		List<Point> caminoch5ch3 = new ArrayList<Point>();
		caminoch5ch3.add(new Point(776 , 108));
		caminoch5ch3.add(new Point(665 ,108));
		
		List<Point> caminoch5ch4 = new ArrayList<Point>();
		caminoch5ch4.add(new Point(896 , 267));
		caminoch5ch4.add(new Point(896 , 375));
		
		List<Point> caminoch5ch6 = new ArrayList<Point>();
		caminoch5ch6.add(new Point(776 , 108));
		
		List<Point> caminoch5ch7 = new ArrayList<Point>();
		caminoch5ch7.add(new Point(896 , 267));
		caminoch5ch7.add(new Point(896 , 375));
		caminoch5ch7.add(new Point(776 , 108));
		
		CaminoHabitacion ch5h1 = new CaminoHabitacion(caminoch5ch1,this.h1);
		CaminoHabitacion ch5h2 = new CaminoHabitacion(caminoch5ch2,this.h2);
		CaminoHabitacion ch5h3 = new CaminoHabitacion(caminoch5ch3,this.h3);
		CaminoHabitacion ch5h4 = new CaminoHabitacion(caminoch5ch4,this.h4);
		CaminoHabitacion ch5h6 = new CaminoHabitacion(caminoch5ch3,this.h6);
		CaminoHabitacion ch5h7 = new CaminoHabitacion(caminoch5ch4,this.h7);
		
		this.h5.getCaminos().add(ch5h1); 
		this.h5.getCaminos().add(ch5h2); 
		this.h5.getCaminos().add(ch5h3); 
		this.h5.getCaminos().add(ch5h4); 
		this.h5.getCaminos().add(ch5h6); 
		this.h5.getCaminos().add(ch5h7); 
		
		
		//Habitacion 6 Caminos
		List<Point> caminoch6ch1 = new ArrayList<Point>();
		caminoch6ch1.add(new Point(665 ,108));
		caminoch6ch1.add(new Point(400,546));
		caminoch6ch1.add(new Point(300 , 546));
		
		List<Point> caminoch6ch2 = new ArrayList<Point>();
		caminoch6ch2.add(new Point(665 ,108));
	
		
		List<Point> caminoch6ch3 = new ArrayList<Point>();
		caminoch6ch3.add(new Point(665 ,108));
		
		List<Point> caminoch6ch4 = new ArrayList<Point>();
		caminoch6ch4.add(new Point(776 , 108));
		caminoch6ch4.add(new Point(896 , 267));
		caminoch6ch4.add(new Point(896 , 375));
		
		List<Point> caminoch6ch5 = new ArrayList<Point>();
		caminoch6ch5.add(new Point(776 , 108));
		
		List<Point> caminoch6ch7 = new ArrayList<Point>();
		caminoch6ch7.add(new Point(665 ,108));
		caminoch6ch7.add(new Point(660 , 460));
		
		CaminoHabitacion ch6h1 = new CaminoHabitacion(caminoch6ch1,this.h1);
		CaminoHabitacion ch6h2 = new CaminoHabitacion(caminoch6ch2,this.h2);
		CaminoHabitacion ch6h3 = new CaminoHabitacion(caminoch6ch3,this.h3);
		CaminoHabitacion ch6h4 = new CaminoHabitacion(caminoch6ch4,this.h4);
		CaminoHabitacion ch6h5 = new CaminoHabitacion(caminoch6ch5,this.h5);
		CaminoHabitacion ch6h7 = new CaminoHabitacion(caminoch6ch7,this.h7);
		
		this.h6.getCaminos().add(ch6h1); 
		this.h6.getCaminos().add(ch6h2); 
		this.h6.getCaminos().add(ch6h3); 
		this.h6.getCaminos().add(ch6h4); 
		this.h6.getCaminos().add(ch6h5); 
		this.h6.getCaminos().add(ch6h7); 
		
		
		//Habitacion 7 Caminos
		List<Point> caminoch7ch1 = new ArrayList<Point>();
		caminoch7ch1.add(new Point(660 , 460));
		caminoch7ch1.add(new Point(400,546));
		caminoch7ch1.add(new Point(300 , 546));
		
		List<Point> caminoch7ch2 = new ArrayList<Point>();
		caminoch7ch2.add(new Point(660 , 460));
	
		
		List<Point> caminoch7ch3 = new ArrayList<Point>();
		caminoch7ch3.add(new Point(660 , 460));
		
		List<Point> caminoch7ch4 = new ArrayList<Point>();
		caminoch7ch4.add(new Point(783 ,460));
		
		List<Point> caminoch7ch5 = new ArrayList<Point>();
		caminoch7ch5.add(new Point(783 ,460));
		caminoch7ch5.add(new Point(896 , 375));
		caminoch7ch5.add(new Point(896 , 267));
		
		List<Point> caminoch7ch6 = new ArrayList<Point>();
		caminoch7ch6.add(new Point(660 , 460));
		caminoch7ch6.add(new Point(665 ,108));
		
		CaminoHabitacion ch7h1 = new CaminoHabitacion(caminoch7ch1,this.h1);
		CaminoHabitacion ch7h2 = new CaminoHabitacion(caminoch7ch2,this.h2);
		CaminoHabitacion ch7h3 = new CaminoHabitacion(caminoch7ch3,this.h3);
		CaminoHabitacion ch7h4 = new CaminoHabitacion(caminoch7ch4,this.h4);
		CaminoHabitacion ch7h5 = new CaminoHabitacion(caminoch7ch5,this.h5);
		CaminoHabitacion ch7h6 = new CaminoHabitacion(caminoch7ch6,this.h6);
		
		this.h7.getCaminos().add(ch7h1); 
		this.h7.getCaminos().add(ch7h2); 
		this.h7.getCaminos().add(ch7h3); 
		this.h7.getCaminos().add(ch7h4); 
		this.h7.getCaminos().add(ch7h5); 
		this.h7.getCaminos().add(ch7h6); 
		
		
		this.getHabitaciones().add(this.h1);
		this.getHabitaciones().add(this.h2);
		this.getHabitaciones().add(this.h3);
		this.getHabitaciones().add(this.h4);
		this.getHabitaciones().add(this.h5);
		this.getHabitaciones().add(this.h6);
		this.getHabitaciones().add(this.h7);
	}

	@Override
	public List<Point> getCamino(int xinicial, int yinicial, int xdestino,int ydestino) {
		List<Point> camino = new ArrayList<Point>(); 
		
		Habitacion habitacionInicio = this.buscarHabitacionContenedora(xinicial, yinicial);
		Habitacion habitacionDestino = this.buscarHabitacionContenedora(xdestino, ydestino);
		
		for(Habitacion habitacion : this.getHabitaciones()){
			if(habitacion == habitacionInicio){
				camino.addAll(habitacion.getCaminoAHabitacion(habitacionDestino));
				break;
			}
		}
		
		camino.add(new Point(xdestino,ydestino));
		
		return camino;
	}
	

		
	

}
