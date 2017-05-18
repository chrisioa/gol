package gol.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import gol.model.GameModel;
import gol.model.GameTask;
import gol.model.PresetsGenerator;
import gol.view.GameStage;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Label;

public class GameController {

	private final GameModel model;
	private final GameStage view;
	private final Integer cellSize;

	private GameTask runGame;
	private ExecutorService exService;
	private int gameSpeed = 10;
	private boolean running = false;

	public GameController(GameModel model) {
		this.model = model;
		view = new GameStage(this);
		cellSize = view.getCellSize();
		model.setCellSize(cellSize);
		setupBindings();
	}

	public void startShow() {
		view.setTitle("Game of Life");
		view.show();
	}

	public int getNumberOfCells(int i) {
		if (i == 0) {
			System.out.println("Height = " + model.getGameheight());
			return model.getGameheight();
		} else if (i == 1) {
			System.out.println("Width = " + model.getGamewidth());
			return model.getGamewidth();
		} else {

			System.err.println("getCellNumber 0 or 1!");
			return 0000;
		}

	}

	public boolean checkAlive(int x, int y) {
		return model.isAlive(y, x);
	}

	public void deleteCell(int x, int y) {

		model.deactivateGameCell(x, y);
	}

	public void activateGameCell(int layoutX, int layoutY) {
		model.activateGameCell(layoutX, layoutY);
	}

	public void setupBindings() {
		BooleanProperty[][] gameCells = model.getGameCells();
		Label[][] labels = view.getLabels();

		System.out.println("Labels: " + labels.length + "\n" + "Cells: " + gameCells.length);

		for (int i = 0; i < gameCells.length; i++) {
			for(int j = 0; j < gameCells[i].length;j++){
				
			Label label = labels[i][j];

			BooleanProperty activeProperty = label.visibleProperty();
			BooleanProperty gameCellAlive = gameCells[i][j];

			gameCellAlive.bindBidirectional(activeProperty);
			}
		}
	}

	public void resetGame() {
		model.deactivateAllGameCells();
	}

	public void startGame() {
		// Check if already running or not!
		if (!running) {
			setRunning(true);
			exService = Executors.newCachedThreadPool();
			runGame = new GameTask(this);
			System.out.println("Run!");
			exService.execute(runGame);
		}

	}

	public void pauseGame() {
		// Check if game is running -> If not, no need to stop executor
		if (running) {
			setRunning(false);
			runGame.pause();
			exService.shutdown();
			try {
				exService.awaitTermination(1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BooleanProperty[][] getGameCells() {
		return model.getGameCells();
	}

	public void performOneStep() {
		GameTask game = new GameTask(this);
		game.performStep();
	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(double gameSpeed) {
		System.out.println("New Game Speed: " + gameSpeed);
		this.gameSpeed = (int) gameSpeed;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void loadPreset(int preset) {
		PresetsGenerator presetsGenerator = new PresetsGenerator(this);
		switch (preset) {
		case 1:
			presetsGenerator.koksGalaxy();
			break;
		case 2:
			presetsGenerator.gosperGliderGun();
		default:
			break;
		}
		
	}
}
