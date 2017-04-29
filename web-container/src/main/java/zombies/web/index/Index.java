package zombies.web.index;

import javax.faces.bean.ManagedBean;

import com.uqbar.vainilla.DesktopGameLauncher;

import zombie.DesktopGameLauncherNoVisible;
import zombie.SonidoAparte;
import zombie.Zombies;

@ManagedBean
public class Index {

	    public void start() {
			Zombies game = new Zombies();
			SonidoAparte sonido = new SonidoAparte();
			new DesktopGameLauncherNoVisible(sonido).launch();
			
			Zombies.soundPlayer = sonido.getSoundPlayer();
			
			new DesktopGameLauncher(game).launch();
	    }
	 
	  
}
