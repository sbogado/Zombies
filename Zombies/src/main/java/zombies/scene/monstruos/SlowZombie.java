package zombies.scene.monstruos;

import java.util.ArrayList;
import java.util.List;

import sound.SoundZombie;
import zombie.scene.rules.ChoqueBarricadaRule;
import zombie.scene.rules.ChoqueParedRule;
import zombie.scene.rules.ColisionRule;
import zombie.scene.rules.DesplazamientoLibreRule;
import zombies.scene.Mapa;
import zombies.scene.ZombiesRule;
import zombies.scene.ZombiesScene;
import zombies.scene.zombiesappearences.SlowZombieAppearences;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

public class SlowZombie extends Monstruo {
	
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
	
	public SlowZombie( double xInicial, double yInicial, ZombiesScene scena,double puntoAtaqueX,double puntoAtaqueY, Mapa mapa) {
		super(xInicial //x inicial
				, yInicial // y inicial
				,puntoAtaqueX // punto de ataque x
				,puntoAtaqueY // punto de ataque x
				,mapa // mapa
				,150 // velocidad
				,10 //puntos o exp
				,0.3 // tiempo De Recuperacion De Impacto
				,30 // danio
				,200 //vida
				,1 // tiempo de golpe
				,scena);// escena
		
		this.quedarseTiradoEnElPisoAnimation = new AnimationLowCost(SlowZombieAppearences.getInstance().getQuedarseTiradoEnElPisoAnimation());
		this.imagenCaminando =  new AnimationLowCost(SlowZombieAppearences.getInstance().getImagenCaminando());
		this.imagenGolpeando = new AnimationLowCost(SlowZombieAppearences.getInstance().getImagenGolpeando());
		this.imagenGolpeandoPersonaje = new AnimationLowCost(SlowZombieAppearences.getInstance().getImagenGolpeandoPersonaje());	
		this.imagenImpactado = new AnimationLowCost(SlowZombieAppearences.getInstance().getImagenImpactado());
		
		this.sonidoDeSalida1 = SlowZombieAppearences.getInstance().getSonidoDeSalida1();
		this.sonidoDeSalida2 = SlowZombieAppearences.getInstance().getSonidoDeSalida2();
		this.sonidoDeSalida3 = SlowZombieAppearences.getInstance().getSonidoDeSalida3();
		this.sonidoDeSalida4 = SlowZombieAppearences.getInstance().getSonidoDeSalida4();
		
		this.sonidoGolpeandoBarricada1 = SlowZombieAppearences.getInstance().getSonidoGolpeandoBarricada1();
		this.sonidoGolpeandoBarricada2= SlowZombieAppearences.getInstance().getSonidoGolpeandoBarricada2();
		this.sonidoGolpeandoBarricada3 = SlowZombieAppearences.getInstance().getSonidoGolpeandoBarricada3();
		
		this.sonidoDeMuerte1 = SlowZombieAppearences.getInstance().getSonidoDeMuerte1();
		this.sonidoDeMuerte2 = SlowZombieAppearences.getInstance().getSonidoDeMuerte2();
		this.sonidoDeMuerte3 = SlowZombieAppearences.getInstance().getSonidoDeMuerte3();
		
		this.initState();
		this.initRules();
	}

	public void mitosis() {
	}


	@Override
	public void destroy() {
		super.destroy();
		this.getScene().removeSlowZombie(this);
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
	
