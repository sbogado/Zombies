package ar.edu.unq.games.vainillautils;

import java.util.ArrayList;
import java.util.List;


import com.uqbar.vainilla.appearances.Animation;



public class AnimationRotateMoved extends Animation{

	SpriteMoved[] spritesIniciales;
	double rotation;
	
	public AnimationRotateMoved(double meantime, SpriteMoved[] sprites) {
		super(meantime, sprites);
		this.spritesIniciales = sprites;
		this.rotation = 0;
	}

	public void rotate(double radians){
		List<SpriteMoved> spritesRotados = new ArrayList<SpriteMoved>();
		
		for(SpriteMoved sprite : this.spritesIniciales){
			spritesRotados.add(sprite.rotate(radians));
		}
		
		this.setSprites( spritesRotados.toArray(new SpriteMoved[spritesRotados.size()]));
		this.setRotation(radians);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public AnimationRotateMoved copy() {
		return new AnimationRotateMoved(this.getMeantime(), spritesIniciales);
	}

	
	
	public SpriteMoved[] getSpritesIniciales() {
		return spritesIniciales;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
}
