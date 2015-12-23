package ar.edu.unq.games.vainillautils;

/**
 * inmutable
 * @author leo
 *
 */
public class Vector2D {

	private double x;
	private double y;
	private double module;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
		this.module = -1;
	}

	@Override
	public String toString() {
		return "("+ x + "," + y + ")";
	}

	public double getModule() {
		if(this.module < 0) {
			this.module = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		}
		return module;
	}
	
	public Vector2D asVersor(){
		//TODO, usar un delta?
		return this.getModule() != 1 ? new Vector2D(this.x/this.getModule(), this.y/this.getModule()) : this;
	}
	
	public Vector2D producto(double valor){
		return new Vector2D(x * valor , y * valor);
	}

	public Vector2D suma(Vector2D vector2d) {
		return new Vector2D(this.x + vector2d.getX(), this.y + vector2d.getY());
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(module);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(module) != Double
				.doubleToLongBits(other.module))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	public void rotate(double anguloRadianes){
		
		double xRotado = this.x*Math.cos(anguloRadianes)- this.y*Math.sin(anguloRadianes);
		double yRotado = this.x*Math.sin(anguloRadianes)+ this.y*Math.cos(anguloRadianes);
		
		
		this.x = xRotado;
		this.y = yRotado;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	

}
