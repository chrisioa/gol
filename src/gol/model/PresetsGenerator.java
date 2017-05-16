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
	
	public void gosperGliderGun(){
		//line 1
		control.activateGameCell(35,11);
		//line 2
		control.activateGameCell(33, 12);
		control.activateGameCell(35, 12);
		//line 3
		control.activateGameCell(23, 13);
		control.activateGameCell(24, 13);
		control.activateGameCell(31, 13);
		control.activateGameCell(32, 13);
		control.activateGameCell(45, 13);
		control.activateGameCell(46, 13);
		
		//line 4
		control.activateGameCell(22, 14);
		control.activateGameCell(26, 14);
		control.activateGameCell(31, 14);
		control.activateGameCell(32, 14);
		control.activateGameCell(45, 14);
		control.activateGameCell(46, 14);
		//line 5
		control.activateGameCell(11, 15);
		control.activateGameCell(12, 15);
		control.activateGameCell(21, 15);
		control.activateGameCell(27, 15);
		control.activateGameCell(31, 15);
		control.activateGameCell(32, 15);
		//line 6
		control.activateGameCell(11, 16);
		control.activateGameCell(12, 16);
		control.activateGameCell(21, 16);
		control.activateGameCell(25, 16);
		control.activateGameCell(27, 16);
		control.activateGameCell(28, 16);
		control.activateGameCell(33, 16);
		control.activateGameCell(35, 16);
		//line 7
		control.activateGameCell(21, 17);
		control.activateGameCell(27, 17);
		control.activateGameCell(35, 17);
		//line 8
		control.activateGameCell(22, 18);
		control.activateGameCell(26, 18);
		//line9
		control.activateGameCell(23, 19);
		control.activateGameCell(24, 19);
				
		
	}

}
