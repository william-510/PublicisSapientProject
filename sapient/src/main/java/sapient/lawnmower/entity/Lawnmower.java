package sapient.lawnmower.entity;

public class Lawnmower {
	
	private char orientation;
	private int coorX;
	private int coorY;
	private String way;
	
	public Lawnmower() {
		//constructor
	}
	
	public Lawnmower(int coorX, int coorY, char orientation) {
		this.coorX = coorX;
		this.coorY = coorY;
		this.orientation = orientation;
	}
	
	public char getOrientation() {
		return orientation;
	}
	
	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}
	
	public int getCoorX() {
		return coorX;
	}
	
	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}
	
	public int getCoorY() {
		return coorY;
	}
	
	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}
	
	public void setPosition(int coorX, int coorY) {
		this.coorX = coorX;
		this.coorY = coorY;
	}
	
	public String getWay() {
		return way;
	}
	
	public void setWay(String way) {
		this.way = way;
	}
			

}
