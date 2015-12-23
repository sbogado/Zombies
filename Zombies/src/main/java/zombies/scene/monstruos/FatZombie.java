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
import zombies.scene.zombiesappearences.FastZombieAppearences;
import zombies.scene.zombiesappearences.FatZombieAppearences;
import ar.edu.unq.games.vainillautils.AnimationLowCost;

public class FatZombie extends Monstruo {
	
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
	
	public FatZombie( double xInicial, double yInicial, ZombiesScene scena,double puntoAtaqueX,double puntoAtaqueY, Mapa mapa) {
		super(xInicial //x inicial
				, yInicial // y inicial
				,puntoAtaqueX // punto de ataque x
				,puntoAtaqueY // punto de ataque x
				,mapa // mapa
				,100 // velocidad
				,50 //puntos o exp
				,0.1 // tiempo De Recuperacion De Impacto
				,50 // danio
				,500 //vida
				,1 // tiempo de golpe
				,scena);// escena
		
		this.quedarseTiradoEnElPisoAnimation = new AnimationLowCost(FatZombieAppearences.getInstance().getQuedarseTiradoEnElPisoAnimation());
		this.imagenCaminando =  new AnimationLowCost(FatZombieAppearences.getInstance().getImagenCaminando());
		this.imagenGolpeando = new AnimationLowCost(FatZombieAppearences.getInstance().getImagenGolpeando());
		this.imagenGolpeandoPersonaje = new AnimationLowCost(FatZombieAppearences.getInstance().getImagenGolpeandoPersonaje());	
		this.imagenImpactado = new AnimationLowCost(FatZombieAppearences.getInstance().getImagenImpactado());
		
		this.sonidoDeSalida1 = FatZombieAppearences.getInstance().getSonidoDeSalida1();
		this.sonidoDeSalida2 = FatZombieAppearences.getInstance().getSonidoDeSalida2();
		this.sonidoDeSalida3 = FatZombieAppearences.getInstance().getSonidoDeSalida3();
		this.sonidoDeSalida4 = FatZombieAppearences.getInstance().getSonidoDeSalida4();
		
		this.sonidoGolpeandoBarricada1 = FatZombieAppearences.getInstance().getSonidoGolpeandoBarricada1();
		this.sonidoGolpeandoBarricada2= FatZombieAppearences.getInstance().getSonidoGolpeandoBarricada2();
		this.sonidoGolpeandoBarricada3 = FatZombieAppearences.getInstance().getSonidoGolpeandoBarricada3();
		
		this.sonidoDeMuerte1 = FatZombieAppearences.getInstance().getSonidoDeMuerte1();
		this.sonidoDeMuerte2 = FatZombieAppearences.getInstance().getSonidoDeMuerte2();
		this.sonidoDeMuerte3 = FatZombieAppearences.getInstance().getSonidoDeMuerte3();
		
		this.initState();
		this.initRules();
	}

	public void mitosis() {
	}


	@Override
	public void destroy() {
		super.destroy();
		this.getScene().removeFatZombie(this);
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
