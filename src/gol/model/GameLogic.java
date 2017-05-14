package gol.model;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import gol.control.GameController;

public class GameLogic {

	private GameModel model;
	private ArrayList<GameCell> gameCells = new ArrayList<>();
	private Double cellsize;
	private GameController control;
	private boolean gameRunning = true;

	public GameLogic(GameModel gameModel, GameController gameController) {
		this.model = gameModel;
		this.control = gameController;
		gameCells = model.getGameCells();
		cellsize = model.getCellSize();
	}

	// Okay, this should perform a step, maybe change to creating lists with all
	// the cells to be changed
	public void startGame() {
		ExecutorService exService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		GameTask runGame = new GameTask(model);
		System.out.println("Run!");
		exService.submit(runGame);
			
		
		exService.shutdown();
		try {
			exService.awaitTermination(100, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	
	public void pauseGame() {
		gameRunning = false;
	}
}
