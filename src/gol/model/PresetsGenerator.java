package gol.model;

import gol.control.GameController;

public class PresetsGenerator {

	private GameController control;
	
	public PresetsGenerator(GameController gameController) {
		control = gameController;
	}
	
	public void koksGalaxy(){
		//line1
		control.activateGameCell(6, 6);
		control.activateGameCell(7, 6);
		for(int i = 9; i<15; i++){
			control.activateGameCell(i, 6);				
		}
		//line2
		control.activateGameCell(6, 7);
		control.activateGameCell(7, 7);
		for(int i = 9; i<15; i++){
			control.activateGameCell(i, 7);				
		}
		
		//line3
		control.activateGameCell(6, 8);
		control.activateGameCell(7, 8);
		
		//line4
		control.activateGameCell(6, 9);
		control.activateGameCell(7, 9);
		control.activateGameCell(13, 9);
		control.activateGameCell(14, 9);
		//line5
		control.activateGameCell(6, 10);
		control.activateGameCell(7, 10);
		control.activateGameCell(13, 10);
		control.activateGameCell(14, 10);
		//line6
		control.activateGameCell(6, 11);
		control.activateGameCell(7, 11);
		control.activateGameCell(13, 11);
		control.activateGameCell(14, 11);
		//line7
		control.activateGameCell(13, 12);
		control.activateGameCell(14, 12);
		//line8
		for(int i = 6; i<12;i++){
			control.activateGameCell(i, 13);
		}
		control.activateGameCell(13, 13);
		control.activateGameCell(14, 13);
		//line9
		for(int i = 6; i<12;i++){
			control.activateGameCell(i, 14);
		}
		control.activateGameCell(13, 14);
		control.activateGameCell(14, 14);
		
	}

}
