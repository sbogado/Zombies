package ar.edu.unq.games.vainillautils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.exceptions.GameException;
import com.uqbar.vainilla.utils.ResourceProvider;

public class SpriteMoved extends Sprite {

	BufferedImage image;
	
	public SpriteMoved(BufferedImage image) {
		super(image);
		this.image = image;
	}
	
	@Override
	public void render(GameComponent<?> component, Graphics2D graphics) {
		this.renderAt( (int)(component.getX()- this.getWidth()/2), (int)( component.getY() - this.getHeight()/2), graphics);
	}

	public static SpriteMoved fromImage(String imageFileName) {
		return fromImage(imageFileName, defaultResourceProvider);
	}

	public static SpriteMoved fromImage(String imageFileName, ResourceProvider provider) {
		BufferedImage image;

		try {
			image = ImageIO.read(provider.getResource(imageFileName));
		} catch (Exception e) {
			throw new GameException("The resource '" + imageFileName
					+ "' was not found using " + provider.getClass().getName(), e);
		}

		return new SpriteMoved(image);
	}
	
	public SpriteMoved rotate(double radians) {
		BufferedImage newImage = new BufferedImage((int) this.getWidth(),
				(int) this.getHeight(), this.getImage().getType());

		Graphics2D graphics = newImage.createGraphics();
		graphics.rotate(radians, this.getWidth() / 2, this.getHeight() / 2);
		graphics.drawImage(this.getImage(), null, 0, 0);
		graphics.dispose();

		return new SpriteMoved(newImage);
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

	public void setImage(BufferedImage currentImage) {
		this.image = currentImage;
	}
	
}
