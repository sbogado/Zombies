package zombies.scene;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import zombie.Zombies;
import zombie.zombiescene.algoritmosdemonstruos.Dijkstra;
import zombie.zombiescene.algoritmosdemonstruos.Node;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;




public class Mapa extends GameComponent<ZombiesScene>{
	
	private List< List< Node > > adyacentes = new ArrayList< List< Node > >();
	private int verticesPorFila;
	private int cantidadDeFilas; 	
	private int cantidadDePixelesPorVerticeX;
	private int cantidadDePixelesPorVerticeY;
	private int pesoDelPisoComunEnGrafo;
	
	public Mapa(int verticesPorFila,int cantidadDeFilas,int pixelesPorVertice) {
		super(Sprite.fromImage("farmHouse2.png"), 1500, 1500);
		this.setZ(-5);
		this.pesoDelPisoComunEnGrafo = 20;
		this.verticesPorFila = verticesPorFila;
		this.cantidadDeFilas = cantidadDeFilas;
		this.setCantidadDePixelesPorVerticeX(pixelesPorVertice); 
		this.setCantidadDePixelesPorVerticeY(pixelesPorVertice); 
		int cantidadDeVertices = this.verticesPorFila * this.cantidadDeFilas ;
		for( int i = 0 ; i <= cantidadDeVertices ; ++i ) adyacentes.add(new ArrayList<Node>());
	}
	
	public double getX() {
		return this.getScene().getDesplazamientoDePantallaX()-1455;
	}
	
	public double getY() {
		return this.getScene().getDesplazamientoDePantallaY()-1640;
	}
	
	
	// Builds para el grafo que usa el algoritmo de dijkstra
	
	public List<List<Node>> buildGrafoMap(){
		this.buildRoomGrafo(1, this.verticesPorFila, this.cantidadDeFilas);
		return this.adyacentes;
	}
	
	private void buildRoomGrafo( int verticeInicial, int AnchoDeHabitacion, int LargoDeHabitacion){
		
		this.addEsquinaSuperiorIzquierdaGrafo(verticeInicial, AnchoDeHabitacion);	
		for( int i = verticeInicial + 1 ; i < verticeInicial + AnchoDeHabitacion - 1 ; ++i ){
			this.addVerticeJuntoAParedSuperiorGrafo(i, AnchoDeHabitacion);
		}
		this.addEsquinaSuperiorDerechaGrafo(verticeInicial + AnchoDeHabitacion - 1, AnchoDeHabitacion);

		
		int verticeInicialMedio = verticeInicial + AnchoDeHabitacion;
		for( int i = 0 ; i < LargoDeHabitacion - 2 ; ++i ){
			this.buildMedioRoomGrafo(verticeInicialMedio, AnchoDeHabitacion);
			verticeInicialMedio = verticeInicialMedio + AnchoDeHabitacion;
			
		}	
			
		this.addEsquinaInferiorIzquierdaGrafo(AnchoDeHabitacion *  LargoDeHabitacion - AnchoDeHabitacion + 1, AnchoDeHabitacion);	
		for( int i = AnchoDeHabitacion *  LargoDeHabitacion - AnchoDeHabitacion + 2 ; i < AnchoDeHabitacion *  LargoDeHabitacion ; ++i ){
			this.addVerticeJuntoAParedInferiorGrafo(i, AnchoDeHabitacion);
		}
		this.addEsquinaInferiorDerechaGrafo(AnchoDeHabitacion *  LargoDeHabitacion, AnchoDeHabitacion);		
	}
	
	private void buildMedioRoomGrafo( int rangoDeVerticesInicial, int AnchoDeHabitacion){
		this.addVerticeJuntoAParedIzquierdaGrafo(rangoDeVerticesInicial, AnchoDeHabitacion);
		for( int i = rangoDeVerticesInicial + 1 ; i < rangoDeVerticesInicial + AnchoDeHabitacion - 1 ; ++i ){
			this.addVerticeEnElMedioGrafo(i, AnchoDeHabitacion);
		}
		this.addVerticeJuntoAParedDerechaGrafo(rangoDeVerticesInicial + AnchoDeHabitacion -1, AnchoDeHabitacion);
	}
	
	private void addEsquinaGrafo(int vertice, int verticeOtraLinea, int verticeMismaLinea){
		this.adyacentes.get( vertice ).add( new Node( vertice+ verticeMismaLinea , this.pesoDelPisoComunEnGrafo ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+verticeOtraLinea , this.pesoDelPisoComunEnGrafo ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+verticeOtraLinea+verticeMismaLinea , this.pesoDelPisoComunEnGrafo ) );
		
	}
	
	private void addEsquinaSuperiorIzquierdaGrafo(int vertice, int cantidadDeVerticesPorFila){
		this.addEsquinaGrafo(vertice, cantidadDeVerticesPorFila, +1);
	}
	
	private void addEsquinaSuperiorDerechaGrafo(int vertice, int cantidadDeVerticesPorFila){
		this.addEsquinaGrafo(vertice, cantidadDeVerticesPorFila, -1);
	}
	
	private void addEsquinaInferiorIzquierdaGrafo(int vertice, int cantidadDeVerticesPorFila){
		this.addEsquinaGrafo(vertice, -cantidadDeVerticesPorFila, +1);
	}
	
	private void addEsquinaInferiorDerechaGrafo(int vertice, int cantidadDeVerticesPorFila){
		this.addEsquinaGrafo(vertice, -cantidadDeVerticesPorFila, -1);
	}
	
	private void addVerticeJuntoAParedGrafo(int vertice, int verticeOtraLinea, int verticeMismaLinea){		
		this.adyacentes.get( vertice ).add( new Node( vertice-verticeOtraLinea , this.pesoDelPisoComunEnGrafo ) );
		this.adyacentes.get( vertice ).add( new Node( vertice-verticeOtraLinea+verticeMismaLinea ,this.pesoDelPisoComunEnGrafo ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+verticeMismaLinea , this.pesoDelPisoComunEnGrafo ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+verticeOtraLinea , this.pesoDelPisoComunEnGrafo ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+verticeOtraLinea+verticeMismaLinea ,this.pesoDelPisoComunEnGrafo ) );

	}
	
	private void addVerticeJuntoAParedSuperiorGrafo(int vertice, int cantidadDeVerticesPorFila){	
		this.addVerticeJuntoAParedGrafo(vertice, 1, cantidadDeVerticesPorFila);
	}
	
	private void addVerticeJuntoAParedInferiorGrafo(int vertice, int cantidadDeVerticesPorFila){	
		this.addVerticeJuntoAParedGrafo(vertice, 1, -cantidadDeVerticesPorFila);
	}
	
	private void addVerticeJuntoAParedIzquierdaGrafo(int vertice, int cantidadDeVerticesPorFila){	
		this.addVerticeJuntoAParedGrafo(vertice, cantidadDeVerticesPorFila, 1);
	}
	
	private void addVerticeJuntoAParedDerechaGrafo(int vertice, int cantidadDeVerticesPorFila){	
		this.addVerticeJuntoAParedGrafo(vertice, cantidadDeVerticesPorFila, -1);
	}
	
	private void addVerticeEnElMedioGrafo(int vertice, int cantidadDeVerticesPorFila ){		
		this.addVerticeEnElMedioGrafoConPeso(vertice, cantidadDeVerticesPorFila, this.pesoDelPisoComunEnGrafo);

	}
	
	public void addVerticeEnElMedioGrafoConPesoReemplazandoElAnterior(int vertice, int cantidadDeVerticesPorFila,int peso ){
		this.setVerticeVacio(vertice);
		this.addVerticeEnElMedioGrafoConPeso( vertice,  cantidadDeVerticesPorFila, peso );
	}
	
	public void addVerticeEnElMedioGrafoConPeso(int vertice, int cantidadDeVerticesPorFila,int peso ){		
		this.adyacentes.get( vertice ).add( new Node( vertice-cantidadDeVerticesPorFila-1 , peso ) );
		this.adyacentes.get( vertice ).add( new Node( vertice-cantidadDeVerticesPorFila ,peso ) );
		this.adyacentes.get( vertice ).add( new Node( vertice-cantidadDeVerticesPorFila+1 , peso ) );
		
		this.adyacentes.get( vertice ).add( new Node( vertice-1, peso ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+1 , peso ) );
		
		this.adyacentes.get( vertice ).add( new Node( vertice+cantidadDeVerticesPorFila-1 , peso ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+cantidadDeVerticesPorFila ,peso ) );
		this.adyacentes.get( vertice ).add( new Node( vertice+cantidadDeVerticesPorFila+1 ,peso ) );
	}
	
	public void modificarPesoDeVerticeYAlrededores(int vertice, int cantidadDeVerticesPorFila,int peso ){		
		int vertice1 =  vertice-cantidadDeVerticesPorFila-1;
		int vertice2 =  vertice-cantidadDeVerticesPorFila;
		int vertice3 = vertice-cantidadDeVerticesPorFila+1; 
		int vertice4 =  vertice-1;
		int vertice5 =  vertice+1;
		int vertice6 =   vertice+cantidadDeVerticesPorFila-1 ;
		int vertice7 = vertice+cantidadDeVerticesPorFila; 
		int vertice8 =  vertice+cantidadDeVerticesPorFila+1 ;
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice1);
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice2);
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice3);
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice4);
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice5);
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice6);
		
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice7);	
		
		modificarPesoDeNodoYDeSuVerticeCorrespondiente(vertice, cantidadDeVerticesPorFila, peso, vertice8);	

	}

	private void modificarPesoDeNodoYDeSuVerticeCorrespondiente(int vertice,int cantidadDeVerticesPorFila, int peso, int verticeDeAlrededor) {
		if( !(verticeDeAlrededor <= 0 && !(verticeDeAlrededor >= this.adyacentes.size()))){
			Node node = this.encontrarNodoEnLista(vertice,this.adyacentes.get(  verticeDeAlrededor));
			if(node != null){ node.setSecond(peso);this.modificarPesoDeVertice(node.getFirst(),cantidadDeVerticesPorFila,peso);}
			
		}
	}
	
	private void modificarPesoDeNodo(int vertice, int peso, int verticeDeAlrededor) {
		if( !(verticeDeAlrededor <= 0) && !(verticeDeAlrededor >= this.adyacentes.size()) ){
			Node node = this.encontrarNodoEnLista(vertice,this.adyacentes.get(  verticeDeAlrededor));
			if(node != null){ node.setSecond(peso);}
		}
	}
	
	public void modificarPesoDeVertice(int vertice, int cantidadDeVerticesPorFila,int peso ){		
		int vertice1 =  vertice-cantidadDeVerticesPorFila-1;
		int vertice2 =  vertice-cantidadDeVerticesPorFila;
		int vertice3 = vertice-cantidadDeVerticesPorFila+1; 
		int vertice4 =  vertice-1;
		int vertice5 =  vertice+1;
		int vertice6 =   vertice+cantidadDeVerticesPorFila-1 ;
		int vertice7 = vertice+cantidadDeVerticesPorFila; 
		int vertice8 =  vertice+cantidadDeVerticesPorFila+1 ;
		
		modificarPesoDeNodo(vertice, peso, vertice1);
		
		modificarPesoDeNodo(vertice, peso, vertice2);
		
		modificarPesoDeNodo(vertice, peso, vertice3);
		
		modificarPesoDeNodo(vertice, peso, vertice4);
		
		modificarPesoDeNodo(vertice, peso, vertice4);
		
		modificarPesoDeNodo(vertice, peso, vertice5);
		
		modificarPesoDeNodo(vertice, peso, vertice6);
		
		
		modificarPesoDeNodo(vertice, peso, vertice7);	
		modificarPesoDeNodo(vertice, peso, vertice8);	

	}
	
	
	public Node encontrarNodoEnLista(int vertice, List<Node> nodos){
		Node node = null;
		
		for(Node nodo : nodos){
			if(nodo.getFirst() == vertice ){
				node = nodo;
			}
		}
		return node;
	}
	
	public int calcularVerticeConXeY(double x, double y){
		int yReal =  (int) (y/this.getCantidadDePixelesPorVerticeY());
		int xReal = (int) (x/this.getCantidadDePixelesPorVerticeX())+1;
		
		if(y%this.getCantidadDePixelesPorVerticeY() == 0 ){
			yReal = yReal -1;
		}
		
		if(x%this.getCantidadDePixelesPorVerticeX() == 0 ){
			xReal = xReal - 1;
		}

		return   xReal + this.getVerticesPorFila() * yReal;
	}
	
	public int calcularXConVertice(int vertice){
		int x = (vertice % this.getScene().getMapa().getVerticesPorFila());
		if(x == 0){
			return this.getScene().getMapa().getVerticesPorFila() * this.getCantidadDePixelesPorVerticeX();
		}
		else{
			return x * this.getCantidadDePixelesPorVerticeX();
		}
	}
	
	public int calcularYConVertice(int vertice){
		int y = (vertice % this.getScene().getMapa().getVerticesPorFila());
		if(y == 0){
			return (vertice /this.getScene().getMapa().getVerticesPorFila()) * this.getCantidadDePixelesPorVerticeY();
		}
		else{
			return ((vertice /this.getScene().getMapa().getVerticesPorFila())+1)  * this.getCantidadDePixelesPorVerticeY();
		}
	}

	public int getVerticesPorFila() {
		return verticesPorFila;
	}

	public void setVerticesPorFila(int verticesPorFila) {
		this.verticesPorFila = verticesPorFila;
	}
	
	public int getCantidadDeFilas(){
		return this.cantidadDeFilas;
	}

	public void setVerticeVacio(int vertice) {
		this.adyacentes.get(vertice).clear();
		
	}
		

	public void imprimirLista(List<Node> nodes){
		for(Node node: nodes){
			System.out.println(node);
		}
	}

	public int getCantidadDePixelesPorVerticeY() {
		return cantidadDePixelesPorVerticeY;
	}

	public void setCantidadDePixelesPorVerticeY(int cantidadDePixelesPorVerticeY) {
		this.cantidadDePixelesPorVerticeY = cantidadDePixelesPorVerticeY;
	}

	public int getCantidadDePixelesPorVerticeX() {
		return cantidadDePixelesPorVerticeX;
	}

	public void setCantidadDePixelesPorVerticeX(int cantidadDePixelesPorVerticeX) {
		this.cantidadDePixelesPorVerticeX = cantidadDePixelesPorVerticeX;
	}
	

}
