package zombies.scene.monstruos;

import java.util.ArrayList;
import java.util.List;

import sound.SoundZombie;
import zombie.scene.rules.ChoqueBarricadaRule;
import zombie.scene.rules.ChoqueParedRule;
import zombie.scene.rules.ColisionRule;
import zombie.scene.rules.DesplazamientoLibreRule;
import zombies.scene.components.Mapa;
import zombies.scene.components.ZombiesRule;
import zombies.scene.scenes.ZombiesScene;
import zombies.scene.zombiesappearences.FastZombieAppearences;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

public class FastZombie extends Monstruo {
	
	private List<ZombiesRule> rules = new ArrayList<ZombiesRule>();
	private DesplazamientoLibreRule desplazamientoRule;
	private ColisionRule colisionRule;
	private ChoqueParedRule colisionParedRule;
	private ChoqueParedRule choqueParedRule;
	private ChoqueBarricadaRule choqueBarricadeRule;
	private SoundZombie sonidoDeSalida1 ;
	private SoundZombie sonidoDeSalida2 ;
	private SoundZombie sonidoDeSalida3 ;
	private SoundZombie sonidoDeSalida4 ;
	
	private SoundZombie sonidoGolpeandoBarricada1 ;
	private SoundZombie sonidoGolpeandoBarricada2;
	private SoundZombie sonidoGolpeandoBarricada3 ;
	private AnimationLowCost imagenCaminando;
	private AnimationLowCost imagenGolpeando;
	private AnimationLowCost imagenGolpeandoPersonaje;
	private AnimationLowCost callendoDespacioAnimation;
	private AnimationLowCost enElPisoAnimationDespacio;
	private AnimationLowCost quedarseTiradoEnElPisoAnimation;
	private SoundZombie sonidoDeMuerte1;
	private SoundZombie sonidoDeMuerte2;
	private SoundZombie sonidoDeMuerte3;
	private AnimationLowCost imagenImpactado;
	
	public FastZombie( double xInicial, double yInicial, ZombiesScene scena,double puntoAtaqueX,double puntoAtaqueY, Mapa mapa) {
		super(xInicial //x inicial
				, yInicial // y inicial
				,puntoAtaqueX // punto de ataque x
				,puntoAtaqueY // punto de ataque x
				,mapa // mapa
				,300 // velocidad
				,20 //puntos o exp
				,0.1 // tiempo De Recuperacion De Impacto
				,10 // danio
				,50 //vida
				,0.5 // tiempo de golpe
				,scena);// escena
		
		this.quedarseTiradoEnElPisoAnimation = new AnimationLowCost(FastZombieAppearences.getInstance().getQuedarseTiradoEnElPisoAnimation());
		this.imagenCaminando =  new AnimationLowCost(FastZombieAppearences.getInstance().getImagenCaminando());
		this.imagenGolpeando = new AnimationLowCost(FastZombieAppearences.getInstance().getImagenGolpeando());
		this.imagenGolpeandoPersonaje = new AnimationLowCost(FastZombieAppearences.getInstance().getImagenGolpeandoPersonaje());	
		this.imagenImpactado = new AnimationLowCost(FastZombieAppearences.getInstance().getImagenImpactado());
		
		this.sonidoDeSalida1 = FastZombieAppearences.getInstance().getSonidoDeSalida1();
		this.sonidoDeSalida2 = FastZombieAppearences.getInstance().getSonidoDeSalida2();
		this.sonidoDeSalida3 = FastZombieAppearences.getInstance().getSonidoDeSalida3();
		this.sonidoDeSalida4 = FastZombieAppearences.getInstance().getSonidoDeSalida4();
		
		this.sonidoGolpeandoBarricada1 = FastZombieAppearences.getInstance().getSonidoGolpeandoBarricada1();
		this.sonidoGolpeandoBarricada2= FastZombieAppearences.getInstance().getSonidoGolpeandoBarricada2();
		this.sonidoGolpeandoBarricada3 = FastZombieAppearences.getInstance().getSonidoGolpeandoBarricada3();
		
		this.sonidoDeMuerte1 = FastZombieAppearences.getInstance().getSonidoDeMuerte1();
		this.sonidoDeMuerte2 = FastZombieAppearences.getInstance().getSonidoDeMuerte2();
		this.sonidoDeMuerte3 = FastZombieAppearences.getInstance().getSonidoDeMuerte3();
		
		this.initState();
		this.initRules();
	}

	public void mitosis() {
	}


	@Override
	public void destroy() {
		super.destroy();
		this.getScene().removeFastZombie(this);
	}
	
	public List<ZombiesRule> getRules() {
		return rules;
	}

	public void setRules(List<ZombiesRule> rules) {
		this.rules = rules;
	}

	public ColisionRule getColisionRule() {
		return colisionRule;
	}

	public void setColisionRule(ColisionRule colisionRule) {
		this.colisionRule = colisionRule;
	}

	@Override
	public DesplazamientoLibreRule getDesplazamientoLibreRule() {
		return this.desplazamientoRule;
	}


	@Override
	public void setDesplazamientoRule(DesplazamientoLibreRule desplazamientoLibreRule) {
		this.desplazamientoRule = desplazamientoLibreRule;
	}


	@Override
	public void addRule(ZombiesRule rule) {
		this.rules.add(rule);
		
	}



	@Override
	public AnimationLowCost getCallendoDespacioAnimation() {
		return this.callendoDespacioAnimation;
	}

	@Override
	public AnimationLowCost getEnElPisoAnimation() {
		return this.enElPisoAnimationDespacio;
	}

	@Override
	public AnimationLowCost getQuedarseTiradoEnElPisoAnimation() {
		return this.quedarseTiradoEnElPisoAnimation;
	}
	
	@Override
	public AnimationLowCost getImagenCaminando() {
		return this.imagenCaminando;
	}

	@Override
	public AnimationLowCost getImagenGolpeandoBarricada() {
		return this.imagenGolpeando;
	}
	
	@Override
	public AnimationLowCost getImagenGolpeandoPersonaje() {
		return this.imagenGolpeandoPersonaje;
	}
	
	@Override
	public AnimationLowCost getImagenImpactado() {
		return this.imagenImpactado;
	}



	@Override
	public ChoqueParedRule getColisionParedRule() {
		return this.colisionParedRule;
	}

	@Override
	public void setColisionParedRule(ChoqueParedRule choqueParedRule) {
		this.colisionParedRule = choqueParedRule;
	}

	@Override
	public ChoqueParedRule getChoqueParedRule() {
		return this.choqueParedRule;
	}

	@Override
	public void setChoqueParedRule(ChoqueParedRule choqueParedRule) {
		this.choqueParedRule = choqueParedRule;
	}

	

	@Override
	public void setColisionBarricadeRule(ChoqueBarricadaRule colisionRule) {
		this.choqueBarricadeRule = colisionRule;
	}

	@Override
	public ChoqueBarricadaRule getColisionBarricadeRule() {
		return this.choqueBarricadeRule;
	}






	@Override
	public void sonidoDeSalida() {
		double random = Math.random();
		if(random < 0.25){
			this.sonidoDeSalida1.play();
		}else
		{
			if(random < 0.5){
				this.sonidoDeSalida2.play();
			}
			else{
				if(random < 0.75){
					this.sonidoDeSalida3.play();
				}
				else{
					this.sonidoDeSalida4.play();
				}
			}
		}
	}

	@Override
	public void sonidoGolpeandoBarricada() {
		double random = Math.random();
		if(random < 0.33){
			this.sonidoGolpeandoBarricada1.play();
		}else
		{
			if(random < 0.66){
				this.sonidoGolpeandoBarricada2.play();
			}
			else{
				this.sonidoGolpeandoBarricada3.play();

			}
		}

	}

	@Override
	public void sonidoDeMuerte() {
		double random = Math.random();
		if(random < 0.33){
			this.sonidoDeMuerte1.play(5);
		}else
		{
			if(random < 0.66){
				this.sonidoDeMuerte2.play(5);
			}
			else{
				this.sonidoDeMuerte3.play(5);

			}
		}
	}

}
