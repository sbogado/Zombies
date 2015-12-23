package ar.edu.unq.games.vainillautils;

import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Sprite;

public class AnimationRotate extends Animation{

		Sprite[] spritesIniciales;
		
		public AnimationRotate(double meantime, Sprite[] sprites) {
			super(meantime, sprites);
			this.spritesIniciales = sprites;
		}

		public void rotate(double radians){
			List<Sprite> spritesRotados = new ArrayList<Sprite>();
			
			for(Sprite sprite : this.spritesIniciales){
				spritesRotados.add(sprite.rotate(radians));
			}
			
			this.setSprites( spritesRotados.toArray(new Sprite[spritesRotados.size()]));
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public AnimationRotate copy() {
			return new AnimationRotate(this.getMeantime(), spritesIniciales);
		}
}
