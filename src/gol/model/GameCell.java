package gol.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameCell {

	private BooleanProperty isAliveProperty = new SimpleBooleanProperty(true);
	
	private double x;
	private double y;
	
	public GameCell(double width, double height,  boolean isAlive){
		x = width;
		y = height;
		setAlive(isAlive);
	}
	
	public boolean isAlive(){
		return isAliveProperty.get();
	}
	public void setAlive(boolean isAlive) {
		System.out.println("Set alive X: " + x + " Y: " + y);
		isAliveProperty.set(isAlive);
		
	}


	public double getY() {
		return y;
	}


	public double getX() {
		return x;
	}


	public BooleanProperty getIsAlive() {
		
		return isAliveProperty;
	}
	
}
