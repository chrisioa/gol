package gol.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameCell {

	private BooleanProperty isAliveProperty = new SimpleBooleanProperty(true);
	
	private double x;
	private double y;
	
	public GameCell(double height, double width, boolean isAlive){
		setY(height);
		setX(width);
		setAlive(isAlive);
	}
	
	public boolean isAlive(){
		return isAliveProperty.get();
	}
	public void setAlive(boolean isAlive) {
		System.out.println("Set alive cell with height: " + y + " width: " + x);
		isAliveProperty.set(isAlive);
		
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}

	public BooleanProperty getIsAlive() {
		
		return isAliveProperty;
	}

	
}
