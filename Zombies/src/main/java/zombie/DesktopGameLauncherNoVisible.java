package zombie;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class DesktopGameLauncherNoVisible extends DesktopGameLauncher {

	public DesktopGameLauncherNoVisible(Game game) {
		super(game);

	}
	
	public void launch() {
		this.setLocationRelativeTo(null);
	}

}
