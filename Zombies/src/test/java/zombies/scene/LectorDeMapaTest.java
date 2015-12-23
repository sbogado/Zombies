package zombies.scene;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unq.games.vainillautils.LectorDeMapa;

public class LectorDeMapaTest {

	
	@Test
	public void testColorMapaBlue(){
		LectorDeMapa lector = new LectorDeMapa("mapaPackman.png");
		Assert.assertEquals(lector.getPixel(50, 50),Color.blue);
		
	}
}
