package zombie;

import java.awt.Dimension;

import sound.SoundPlayerZombie;

import com.uqbar.vainilla.Game;

public class SonidoAparte extends Game{

	private SoundPlayerZombie  soundPlayer;
	private Dimension dimension;
	
	public SonidoAparte(){
		super();
		this.soundPlayer =  new SoundPlayerZombie();
	}
	
	@Override
	protected void initializeResources() {
		dimension = new Dimension(0, 0);
	}

	@Override
	protected void setUpScenes() {
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {

		return "Sonido";
	}

	public SoundPlayerZombie getSoundPlayer() {
		return soundPlayer;
	}

	
	
}
