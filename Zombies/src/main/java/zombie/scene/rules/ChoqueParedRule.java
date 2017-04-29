package zombie.scene.rules;


import zombies.scene.components.Barricada;
import zombies.scene.components.Pared;
import zombies.scene.components.Personaje;
import zombies.scene.monstruos.Monstruo;

public interface ChoqueParedRule {
	
	public abstract void checkChoquePared(Pared pared);

	public abstract void colisionIndividuoPared(Pared pared);
	
	public abstract void applyY(Monstruo mostro, Barricada barricada);
	
	public abstract void applyX(Monstruo mostro, Barricada barricada);
	
	public abstract void applyY(Personaje personaje, Pared pared);
	
	public abstract void applyX(Personaje personaje, Pared pared);
}
