package zombies.scene;


import org.junit.Assert;
import org.junit.Test;

import zombie.zombiescene.algoritmosdemonstruos.Dijkstra;
import zombies.scene.components.Mapa;




public class MapaTest {

	@Test
	public void calcularVerticeConXeY1(){
		Mapa mapa = new Mapa(108,71, 10);
		Dijkstra dijkstra = new Dijkstra(108*71,10000,mapa); 
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(1, 1),1);
		
	}
	
	@Test
	public void calcularVerticeConXeY2(){
		Mapa mapa = new Mapa(108,71,10);
		Dijkstra dijkstra = new Dijkstra(108*71,10000,mapa);  
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(10, 10),1);
		
	}
	
	@Test
	public void calcularVerticeConXeY3(){
		Mapa mapa = new Mapa(108,71,10);
		Dijkstra dijkstra = new Dijkstra(108*71,10000,mapa); 
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(20, 10),2);
		
	}
	
	@Test
	public void calcularVerticeConXeY4(){
		Mapa mapa = new Mapa(108,71,10);
		Dijkstra dijkstra = new Dijkstra(108*71,10000,mapa);  
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(20, 20),110);
	}
	
	@Test
	public void calcularVerticeConXeY5(){
		Mapa mapa = new Mapa(108,71, 10);
		Dijkstra dijkstra = new Dijkstra(108*71,10000,mapa); 
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(30, 20),111);
	}
	
	
	@Test
	public void calcularVerticeConXeY6(){
		Mapa mapa = new Mapa(108,71, 10);
		Dijkstra dijkstra = new Dijkstra(108*71,10000,mapa); 
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(10, 40),325);
	}
	
	@Test
	public void calcularVerticeConXeY7(){
		Mapa mapa = new Mapa(160,90, 10);
		Dijkstra dijkstra = new Dijkstra(160*90, 10000,mapa); 
		dijkstra.setAdy(mapa.buildGrafoMap());
		
		Assert.assertEquals(mapa.calcularVerticeConXeY(1, 1),1);
		
	}
	
}
