package zombies.scene.components;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import zombies.scene.monstruos.Monstruo;
import zombies.scene.scenes.ZombiesScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Oleada extends GameComponent<ZombiesScene>{

	private List<Monstruo> monstruos;
	double contadorDeTiempo = 0;
	private ZombiesScene scene;
	private double tiempoEntreCadaMonstruo;
	private boolean cartelSeteado = false;
	private Sprite numeroOleada;
	private Sprite numeroOleada2;
	private int cantidadDeBichos;

	public Oleada(ZombiesScene scene,int numeroOleada, int numeroOleada2,double tiempoEntreCadaMonstruo,int cantidadDeBichos){
		super( scene.getDimension().getWidth()/3,scene.getDimension().getHeight()/3);
		this.monstruos = new ArrayList<Monstruo>();
		this.cantidadDeBichos = cantidadDeBichos;
		this.scene = scene;
		this.tiempoEntreCadaMonstruo = tiempoEntreCadaMonstruo;
		this.numeroOleada = Sprite.fromImage(numeroOleada+" oleada.png");
		if(numeroOleada2 != -1){
			this.numeroOleada2 = Sprite.fromImage(numeroOleada2+" oleada.png");
		}
	}
	
	@Override
	public void update(DeltaState deltaState){
		this.contadorDeTiempo = this.contadorDeTiempo + deltaState.getDelta();
		
		if(!this.cartelSeteado){
			this.setAppearance(this.getCartelDeOleada());
			this.scene.getNuevaOleadaSound().getSound().play(2);
			this.cartelSeteado = true;
		}
		
		
		if(this.contadorDeTiempo >= 5){
			this.destroy();
			this.monstruos = scene.getSlowZombies(this.cantidadDeBichos/2);
			this.monstruos.addAll(scene.getFastZombies(this.cantidadDeBichos/2));
			this.monstruos.addAll(scene.getFatZombies(this.cantidadDeBichos/4));
			this.scene.addComponent(new EnviadorDeOleada(this.monstruos,this.scene,this.tiempoEntreCadaMonstruo));
		}
		
	}

	public void render(Graphics2D graphics){
		super.render(graphics);
		this.numeroOleada.renderAt((int)this.getX()+200, (int)this.getY(), graphics);
		if(this.numeroOleada2 != null){
			this.numeroOleada2.renderAt((int)this.getX()+250, (int)this.getY(), graphics);
		}
	}
	
	public Sprite getCartelDeOleada(){
		return Sprite.fromImage("oleada.png");
	}

	public List<Monstruo> getMonstruos() {
		return monstruos;
	}
	
	
}
