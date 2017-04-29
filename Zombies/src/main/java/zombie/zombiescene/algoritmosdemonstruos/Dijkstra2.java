package zombie.zombiescene.algoritmosdemonstruos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import zombies.scene.components.Mapa;

public class Dijkstra2 extends AlgoritmoDeBusqueda {
	
	public Node nodoTrabajo;
	public  Integer max;
	public  Integer inf;
	private List< List< Node > > ady ; //lista de adyacencia
	private Integer[] distancia;         //distancia[ u ] distancia de vértice inicial a vértice con ID = u
	private boolean visitado[ ];   //para vértices visitados
	private ColaDeNodosParaDijsktra Q; //priority queue propia de Java, usamos el comparador definido para que el de menor valor este en el tope
	private Integer numeroDeVertices;                                      //numero de vertices
	private Integer previo[];             //para la impresion de caminos
	private Mapa mapa;
	private List<Integer> listaObtenidaDeVertices;
	private List<Point> puntosARecorrer;
	
	
	public Dijkstra2(Integer max,Integer inf,Mapa mapa ){
		this.nodoTrabajo = new Node(0,0);
		this.max = max;
		this.inf = inf;
		this.ady = mapa.buildGrafoMap();
		this.distancia = new Integer[ this.max ];          //distancia[ u ] distancia de vértice inicial a vértice con ID = u
		this.visitado = new boolean[ this.max ];   //para vértices visitados
		this.Q = new ColaDeNodosParaDijsktra(); //priority queue propia de Java, usamos el comparador definido para que el de menor valor este en el tope
		this.numeroDeVertices = max-1;                                    //numero de vertices
		this.previo = new Integer[ this.max ];   
		this.mapa = mapa;
		this.listaObtenidaDeVertices = new ArrayList<Integer>();
		this.puntosARecorrer = new ArrayList<Point>();
	}
	
	//función de inicialización
	public void init(){
	    for( int i = 0 ; i <= numeroDeVertices ; ++i ){
	        distancia[ i ] = this.inf ;  //inicializamos todas las distancias con valor infinito
	        visitado[ i ] = false; //inicializamos todos los vértices como no visitados
	        previo[ i ] = -1;      //inicializamos el previo del vertice i con -1
	    }
	}



	//Impresion del camino mas corto desde el vertice inicial y final ingresados
	public void print( int destino ){
	    if( previo[ destino ] != -1 ){    //si aun poseo un vertice previo
	        print( previo[ destino ] );  //recursivamente sigo explorando
	    System.out.printf("%d " , destino );   //terminada la recursion imprimo los vertices recorridos
	    }
	    
	}

	public  List<Point> getCamino(int xinicial, int yinicial,int xdestino, int ydestino){
		int verticeZombie = this.calcularVerticeConXeY(xinicial,yinicial);

		int verticePersonaje = this.calcularVerticeConXeY(xdestino, ydestino);
		
		this.listaObtenidaDeVertices.clear();
 		this.calcularDistanciasATodosLosVertices(verticeZombie);
 		getCaminoCorto(verticePersonaje, listaObtenidaDeVertices);
 		
 		this.puntosARecorrer.clear();
 		
 		for(Integer vertice : listaObtenidaDeVertices){
 			puntosARecorrer.add(new Point(this.calcularXConVertice(vertice),this.calcularYConVertice(vertice)));
 		}
 		return puntosARecorrer;
	}

	//Paso de relajacion
	public void relajacion( int actual , int adyacente , int peso ){
	    //Si la distancia del origen al vertice actual + peso de su arista es menor a la distancia del origen al vertice adyacente
	    if( distancia[ actual ] + peso < distancia[ adyacente ] ){
	        distancia[ adyacente ] = distancia[ actual ] + peso;  //relajamos el vertice actualizando la distancia
	        previo[ adyacente ] = actual;                         //a su vez actualizamos el vertice previo
	        Q.addNodeData(adyacente , distancia[ adyacente ] ); //agregamos adyacente a la cola de prioridad
	    }
	}
	
	public void getCaminoCorto( int destino,List<Integer> camino ){
		if( previo[ destino ] != -1 ){  	
	    	getCaminoCorto( previo[ destino ],camino ); 
	    	camino.add(destino);
	    }    
	}
	
	
	public void calcularDistanciasATodosLosVertices( int inicial ){
	    init(); //inicializamos nuestros arreglos
	    Q.addNodeData( inicial , 0  ); //Insertamos el vértice inicial en la Cola de Prioridad
	    distancia[ inicial ] = 0;      //Este paso es importante, inicializamos la distancia del inicial como 0
	    int actual , adyacente , peso;
	   
	    while( !Q.isEmpty()){                   //Mientras cola no este vacia
	        actual = Q.getNodoConMenorPeso(this.nodoTrabajo).first;            //Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
	        System.out.println(this.nodoTrabajo.getFirst());
	        System.out.println(this.nodoTrabajo.getSecond());
	        if( visitado[ actual ] ) continue; //Si el vértice actual ya fue visitado entonces sigo sacando elementos de la cola
	        visitado[ actual ] = true;         //Marco como visitado el vértice actual
	        
	        for( int i = 0 ; i < ady.get( actual ).size() ; ++i ){ //reviso sus adyacentes del vertice actual
	            adyacente = ady.get( actual ).get( i ).first;   //id del vertice adyacente
	            peso = ady.get( actual ).get( i ).second;        //peso de la arista que une actual con adyacente ( actual , adyacente )
	            if( !visitado[ adyacente ] ){        //si el vertice adyacente no fue visitado
	                relajacion( actual , adyacente , peso ); //realizamos el paso de relajacion
	            }
	        }
	    }
	   


//	    System.out.printf( "Distancias mas cortas iniciando en vertice %d\n" , inicial );
//	    for( int i = 1 ; i <= V ; ++i ){
//	    	System.out.printf("Vertice %d , distancia mas corta = %d\n" , i , distancia[ i ] );
//	    }

	    //System.out.println("\n**************Impresion de camino mas corto**************");
	    //System.out.printf("Ingrese vertice destino: ");
//	    int destino;
	   // destino = sc.nextInt();
	    //print( destino );
	    //System.out.printf("\n");
	}
	public int getV() {
		return this.numeroDeVertices;
	}
	
	public void setV(int cantidadDeVertices) {
		this.numeroDeVertices = cantidadDeVertices;
	}
	
	public List<List<Node>> getAdy() {
		return this.ady;
	}
	
	public void setMax(Integer cantidad) {
		this.max = cantidad;
	}
	
	public void setAdy(List<List<Node>> buildGrafoMap) {
		this.ady = buildGrafoMap;
	}
	
	public int calcularXConVertice(int vertice){
		int x = (vertice % this.mapa.getVerticesPorFila());
		if(x == 0){
			return this.mapa.getVerticesPorFila() * this.mapa.getCantidadDePixelesPorVerticeX();
		}
		else{
			return x * this.mapa.getCantidadDePixelesPorVerticeX();
		}
	}
	
	public int calcularYConVertice(int vertice){
		int y = (vertice % this.mapa.getVerticesPorFila());
		if(y == 0){
			return (vertice /this.mapa.getVerticesPorFila()) * this.mapa.getCantidadDePixelesPorVerticeY();
		}
		else{
			return ((vertice /this.mapa.getVerticesPorFila())+1)  * this.mapa.getCantidadDePixelesPorVerticeY();
		}
	}
	
	public int calcularVerticeConXeY(double x, double y){
		int yReal =  (int) (y/this.mapa.getCantidadDePixelesPorVerticeY());
		int xReal = (int) (x/this.mapa.getCantidadDePixelesPorVerticeX())+1;
		
		if(y%this.mapa.getCantidadDePixelesPorVerticeY() == 0 ){
			yReal = yReal -1;
		}
		
		if(x%this.mapa.getCantidadDePixelesPorVerticeX() == 0 ){
			xReal = xReal - 1;
		}

		return   xReal + this.mapa.getVerticesPorFila() * yReal;
	}
	
	
	public static void main(String[] args) {
//		Dijkstra dijkstra = new Dijkstra();
//		
//		int E , origen, destino , peso , inicial;
//		System.out.print("Ingrese cantidad de vertices: \n");
//		V = sc.nextInt();
//		System.out.print("Ingrese cantidad de anyacentes: \n");
//		E = sc.nextInt();
//		for( int i = 0 ; i <= V ; ++i ) dijkstra.getAdy().add(new ArrayList<Node>()) ; //inicializamos lista de adyacencia
//		for( int i = 0 ; i < E ; ++i ){
//			System.out.print("Eliga numero de vertice origen: \n");
//			origen = sc.nextInt();
//			
//			System.out.print("Ingrese numero de vertice adyacente: \n");
//			destino = sc.nextInt();
//			
//			System.out.print("Ingrese el costo de llegada al vertice adyacente: \n");
//			peso = sc.nextInt();
//			dijkstra.getAdy().get( origen ).add( new Node( destino , peso ) );    //grafo diridigo
//			//ady.get( destino ).add( new Node( destino , peso ) ); //no dirigido
//		}
//		System.out.print("Ingrese el vertice inicial: \n");
//	    inicial = sc.nextInt();
//	    dijkstra.calcularDistanciasATodosLosVertices( inicial );
		
		System.out.println(1.0/10);
		System.out.println(1.0%10);
		
	}


}