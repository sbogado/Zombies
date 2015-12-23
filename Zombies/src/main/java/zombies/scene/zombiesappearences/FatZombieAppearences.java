package zombies.scene.zombiesappearences;

import sound.SoundBuilderZombie;
import sound.SoundZombie;
import ar.edu.unq.games.vainillautils.AnimationRotateMovedLowCost;
import ar.edu.unq.games.vainillautils.SpriteMoved;

public class FatZombieAppearences {

	
	private AnimationRotateMovedLowCost callendoDespacioAnimation;
	private AnimationRotateMovedLowCost enElPisoAnimationDespacio;
	private AnimationRotateMovedLowCost quedarseTiradoEnElPisoAnimation;
	private AnimationRotateMovedLowCost imagenCaminando;
	private AnimationRotateMovedLowCost imagenGolpeando;
	private AnimationRotateMovedLowCost imagenGolpeandoPersonaje;
	private SoundZombie sonidoDeSalida1;
	private SoundZombie sonidoDeSalida2;
	private SoundZombie sonidoDeSalida3;
	private SoundZombie sonidoDeSalida4;
	private SoundZombie sonidoGolpeandoBarricada1;
	private SoundZombie sonidoGolpeandoBarricada2;
	private SoundZombie sonidoGolpeandoBarricada3;
	private SoundZombie sonidoDeMuerte1;
	private SoundZombie sonidoDeMuerte2;
	private SoundZombie sonidoDeMuerte3;
	private AnimationRotateMovedLowCost imagenImpactado;

	public static final FatZombieAppearences INSTANCE = new FatZombieAppearences();
	
	public FatZombieAppearences(){
	
//		SpriteMoved[] sprites4 = {(SpriteMoved) SpriteMoved.fromImage("fat zombie callendo despacio 1.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 2.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 3.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 4.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 5.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 6.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 7.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 8.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 9.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 10.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 11.png"),
//			SpriteMoved.fromImage("fat zombie callendo despacio 12.png")};
//		this.callendoDespacioAnimation =  new AnimationRotateMovedLowCost(.05,sprites4);
		
		
//		SpriteMoved[] sprites5 = {SpriteMoved.fromImage("fat zombie muerto despacio 1.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 1.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 1.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 1.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 1.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 1.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 2.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 5.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 3.png"),
//				SpriteMoved.fromImage("fat zombie muerto despacio 4.png")};
//		this.enElPisoAnimationDespacio = new AnimationRotateMovedLowCost(.1,sprites5);
		
		SpriteMoved[] sprites6 = {SpriteMoved.fromImage("fat zombie muerto violento.png")};
		this.quedarseTiradoEnElPisoAnimation = new AnimationRotateMovedLowCost(.5,sprites6);
		
		SpriteMoved[] sprites1 = {SpriteMoved.fromImage("fat zombie caminando.png"),
				SpriteMoved.fromImage("fat zombie caminando1.png"),
				SpriteMoved.fromImage("fat zombie caminando2.png"),
				SpriteMoved.fromImage("fat zombie caminando1.png"),
				SpriteMoved.fromImage("fat zombie caminando.png"),
				SpriteMoved.fromImage("fat zombie caminando3.png"),
				SpriteMoved.fromImage("fat zombie caminando4.png"),
				SpriteMoved.fromImage("fat zombie caminando3.png")};
		this.imagenCaminando =  new AnimationRotateMovedLowCost(.4, sprites1);
		
		SpriteMoved[] sprites2 = {SpriteMoved.fromImage("fatZombieGolpeando1.png"),
				SpriteMoved.fromImage("fatZombieGolpeando2.png"),
				SpriteMoved.fromImage("fatZombieGolpeando3.png"),
				SpriteMoved.fromImage("fatZombieGolpeando4.png")};
		this.imagenGolpeando =  new AnimationRotateMovedLowCost(.4, sprites2);
		
		SpriteMoved[] sprites3 = {SpriteMoved.fromImage("fatZombieGolpeandoPersonaje5.png"),
				SpriteMoved.fromImage("fatZombieGolpeandoPersonaje4.png"),
				SpriteMoved.fromImage("fatZombieGolpeandoPersonaje3.png"),
				SpriteMoved.fromImage("fatZombieGolpeandoPersonaje2.png"),
				SpriteMoved.fromImage("fatZombieGolpeandoPersonaje1.png"),
				SpriteMoved.fromImage("fatZombieGolpeandoPersonaje2.png")};
		this.imagenGolpeandoPersonaje =  new AnimationRotateMovedLowCost(.4, sprites3);
		
		
		SpriteMoved[] sprites7 = {SpriteMoved.fromImage("fat zombie caminando.png"),
				SpriteMoved.fromImage("fat zombie caminando.png"),
				SpriteMoved.fromImage("fat zombie caminando.png")};
		this.imagenImpactado = new AnimationRotateMovedLowCost(.4, sprites7);
		
		this.sonidoDeSalida1 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("slowZombiePartiendo1.wav")).getSound();
		this.sonidoDeSalida2 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("slowZombiePartiendo2.wav")).getSound();
		this.sonidoDeSalida3 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("slowZombiePartiendo3.wav")).getSound();
		this.sonidoDeSalida4 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("slowZombiePartiendo4.wav")).getSound();
		
		this.sonidoGolpeandoBarricada1 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("golpeando puerta1.wav")).getSound();
		this.sonidoGolpeandoBarricada2= (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("golpeando puerta2.wav")).getSound();
		this.sonidoGolpeandoBarricada3 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("golpeando puerta3.wav")).getSound();
	
		this.sonidoDeMuerte1 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("zombieMuriendo1.wav")).getSound();
		this.sonidoDeMuerte2 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("zombieMuriendo2.wav")).getSound();
		this.sonidoDeMuerte3 = (SoundZombie) new SoundBuilderZombie(this.getClass().getClassLoader().getResourceAsStream("zombieMuriendo3.wav")).getSound();
		
	}

	public AnimationRotateMovedLowCost getCallendoDespacioAnimation() {
		return callendoDespacioAnimation;
	}

	public AnimationRotateMovedLowCost getEnElPisoAnimationDespacio() {
		return enElPisoAnimationDespacio;
	}

	public AnimationRotateMovedLowCost getQuedarseTiradoEnElPisoAnimation() {
		return quedarseTiradoEnElPisoAnimation;
	}

	public AnimationRotateMovedLowCost getImagenCaminando() {
		return imagenCaminando;
	}

	public AnimationRotateMovedLowCost getImagenGolpeando() {
		return imagenGolpeando;
	}

	public AnimationRotateMovedLowCost getImagenGolpeandoPersonaje() {
		return imagenGolpeandoPersonaje;
	}

	public AnimationRotateMovedLowCost getImagenImpactado() {
		return this.imagenImpactado;
	}
	
	public SoundZombie getSonidoDeSalida1() {
		return sonidoDeSalida1;
	}

	public SoundZombie getSonidoDeSalida2() {
		return sonidoDeSalida2;
	}

	public SoundZombie getSonidoDeSalida3() {
		return sonidoDeSalida3;
	}

	public SoundZombie getSonidoDeSalida4() {
		return sonidoDeSalida4;
	}

	public SoundZombie getSonidoGolpeandoBarricada1() {
		return sonidoGolpeandoBarricada1;
	}

	public SoundZombie getSonidoGolpeandoBarricada2() {
		return sonidoGolpeandoBarricada2;
	}

	public SoundZombie getSonidoGolpeandoBarricada3() {
		return sonidoGolpeandoBarricada3;
	}

	public static FatZombieAppearences getInstance() {
		return INSTANCE;
	}

	public SoundZombie getSonidoDeMuerte1() {
		return sonidoDeMuerte1;
	}

	public SoundZombie getSonidoDeMuerte2() {
		return sonidoDeMuerte2;
	}

	public SoundZombie getSonidoDeMuerte3() {
		return sonidoDeMuerte3;
	}

}
