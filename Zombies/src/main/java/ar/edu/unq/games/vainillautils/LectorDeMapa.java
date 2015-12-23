package ar.edu.unq.games.vainillautils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class LectorDeMapa {
	
	BufferedImage bufferedImage;
	
	public LectorDeMapa(String nombreDeMapa){
		this.bufferedImage = SpriteMoved.fromImage(nombreDeMapa).getImage();
	}

	public Color getPixel(int x, int y){
		
		return new Color(this.bufferedImage.getRGB(x, y));
		
	}
	
}
