import java.awt.*;
import javax.swing.*;

/***************************
 * Nicholas Parise, 
 * ICS4U, 
 * Tetris
 ****************************/


// This class deals with the rendering window
public class Render extends JPanel {

	// Private var

	// colors
	private Color colors[] = { new Color(80, 80, 80), new Color(255, 255, 255), new Color(255, 50, 50),
			new Color(50, 255, 50), new Color(50, 50, 255), new Color(225, 225, 50), new Color(225, 50, 225),
			new Color(50, 225, 225), new Color(250, 170, 0), new Color(255, 50, 50, 50), new Color(50, 255, 50, 50),
			new Color(50, 50, 255, 50), new Color(225, 225, 50, 50), new Color(225, 50, 225, 50),
			new Color(50, 225, 225, 50), new Color(250, 170, 0, 50), new Color(0, 0, 0, 0) };
		
			// Normal: black0,white1,red2,green3,blue4,yellow5,purple6,cyan7,orange8,
		// Lighter: red9,green10,blue11,yellow12,purple13,cyan14,orange15,
		// clear 16


	private static int DeltaTitle = 0;
	private static int SplashFall = 0;

	private int[] ColorWave = new int[6];

	private int[] ColorWave1 = { 2, 3, 4, 5, 6, 7 };
	private int[] ColorWave2 = { 7, 2, 3, 4, 5, 6 };
	private int[] ColorWave3 = { 6, 7, 2, 3, 4, 5 };
	private int[] ColorWave4 = { 5, 6, 7, 2, 3, 4 };
	private int[] ColorWave5 = { 4, 5, 6, 7, 2, 3 };
	private int[] ColorWave6 = { 3, 4, 5, 6, 7, 2 };

	private int[][] TempMatrix = new int[5][3];

	// public var

	public static JFrame frame = new JFrame();
	public static KeyBoard keyboard = new KeyBoard();
	public static Mouse mouse = new Mouse();

	// ----------------------------

	public static void construc() {
		// inits variables

		frame.getContentPane().add(new Render());

		frame.setTitle("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.red);
		frame.setLocationRelativeTo(null);
		frame.setSize(464, 624);
		frame.setVisible(true);

		keyboard.addKeyListener(keyboard);
		keyboard.setFocusable(true);

		mouse.addMouseListener(mouse);
		mouse.addMouseMotionListener(mouse);
		mouse.setFocusable(true);

		frame.add(keyboard);
		frame.add(mouse);
		frame.getKeyListeners();

	}

	private void ArrayBuilder(Graphics g, int xOff, int yOff, int blockSize, int color, int colors2, int color3,
			int[][] arr) {
		// Takes x,y size, color and 2d arr and draws it to the screen

		int numberOfColumns = arr[0].length;
		int numberOfRows = arr.length;

		for (int j = 0; j < numberOfColumns; j++) {
			for (int i = 0; i < numberOfRows; i++) {

				int x = blockSize * j + xOff;
				int y = blockSize * i + yOff;

				if (arr[i][j] == 1) {
					g.setColor(colors[color]);
				} else if (arr[i][j] == 2) {
					g.setColor(colors[colors2]);
				} else if (arr[i][j] == 0) {
					g.setColor(colors[color3]);
				}

				g.fillRect(x, y, blockSize, blockSize);
			}
		}
	}

	private void WireBox(Graphics g, int xOff, int yOff, int Width, int Height, int color) {
		// Takes x,y, color, width, hight and draws a square wire frame to the screen
		g.setColor(colors[color]);

		g.drawLine(xOff, yOff + Width - 1, xOff, yOff);
		g.drawLine(xOff, yOff, xOff + Height - 1, yOff);
		g.drawLine(xOff + 1, yOff + Width - 1, xOff + Height - 1, yOff + Width - 1);
		g.drawLine(xOff + Height - 1, yOff + Width - 1, xOff + Height - 1, yOff + 1);
	}

	public static void Delta(int time) {
		// uses delta-time to :

		// and add to title screen
		if (DeltaTitle > 2400) {
			DeltaTitle = 0;
		}
		DeltaTitle += time;

		// add to the splash screen animation
		SplashFall += time;
	}

	// actually draws stuff
	public void paint(Graphics g) {

		// -------------

		// background
		g.setColor(colors[0]);
		g.fillRect(0, 0, 1000, 1000);


				for(int i = 0; i<31; i++) {
					for(int j = 0; j<28; j++) {

				int x = 16 * j + 0;
				int y = 16 * i + (16*3);
				int width = 16;
				int height = 16;

				var color = colors[0];

				if (AssetManager.DrawMatrix[i][j] != 0) {

					if (AssetManager.DrawMatrix[i][j] == 1) {

					ArrayBuilder(g, x, y, 1, 4, 0, 0, AssetManager.VertWallLeft);
						
					} else if (AssetManager.DrawMatrix[i][j] == 2) {
					ArrayBuilder(g, x, y, 1, 4, 0, 0, AssetManager.VertWallRight);
						
					} else if (AssetManager.DrawMatrix[i][j] == 3) {

						ArrayBuilder(g, x, y, 1, 4, 0, 0, AssetManager.HorzWallDown);
					} else if (AssetManager.DrawMatrix[i][j] == 4) {

					ArrayBuilder(g, x, y, 1, 4, 0, 0, AssetManager.HorzWallUp);
					} else if (AssetManager.DrawMatrix[i][j] == 5) {
						

					} else if (AssetManager.DrawMatrix[i][j] == 6) {
					

					} else if (AssetManager.DrawMatrix[i][j] == 7) {
					
					}
				} 
			}
		}




/*
for(int i = 0; i<31; i++) {
	for(int j = 0; j<28; j++) {

int x = 16 * j + 0;
int y = 16 * i + (16*3);
int width = 16;
int height = 16;

		var color = colors[0];

		if (AssetManager.GameMatrix[i][j] != 0) {

			if (AssetManager.GameMatrix[i][j] == 1) {

				color = colors[2];
			} else if (AssetManager.GameMatrix[i][j] == 2) {
				color = colors[3];
			} else if (AssetManager.GameMatrix[i][j] == 3) {
				color = colors[4];
			} else if (AssetManager.GameMatrix[i][j] == 4) {
				color = colors[5];
			} else if (AssetManager.GameMatrix[i][j] == 5) {
				color = colors[6];
			} else if (AssetManager.GameMatrix[i][j] == 6) {
				color = colors[7];
			} else if (AssetManager.GameMatrix[i][j] == 7) {
				color = colors[8];
			}

		}
		g.setColor(color);
		g.fillRect(x, y, width, height);

	}
}
*/


for(int i = 0; i<31; i++) {
	for(int j = 0; j<28; j++) {

	int x = 16 * j + 4;
	int y = 16 * i + (16*3)+4;
	
	
	int width = 4;
	int height = 4;


	if (AssetManager.DotsMatrix[i][j] == 1) {
	
		g.setColor(colors[1]);
		g.fillRect(x, y, width, height);
	}


	if (AssetManager.DotsMatrix[i][j] == 2) {
	
		g.setColor(colors[5]);
		g.fillRect(x-2, y-2, width*2, height*2);
	}



	}
}







	//	col = EnemyColQueue.get(index);
		//ColQueue,RowQueue


		ArrayBuilder(g,0, (16*3), 16, 2, 11, 16, Maze.EnemyMazeSolution);


		ArrayBuilder(g,0, (16*3), 16, 4, 7, 16, Maze.QuickMazeSolution);


		


		ArrayBuilder(g, Player.X-15, Player.Y-15, 1, 16, 16, 5, AssetManager.PacArrClosed);

		ArrayBuilder(g, Player.X-15, Player.Y-15, 1, 16, 16, 2, AssetManager.Ghost1);

		


		g.setColor(colors[2]);
		g.fillRect(Player.X, Player.Y, 1, 1);


/*

		if (GameStateManager.CurrentState != "GameOver") {

			if (GameStateManager.CurrentState == "Game" || GameStateManager.CurrentState == "Pause") {

				// Game matrix

				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 10; j++) {

						int x = 25 * j + (j + 25);
						int y = 25 * i + (i + 25);
						int width = 25;
						int height = 25;

						var color = colors[0];

						if (Blocks.GameMatrix[i][j] != 0) {

							if (Blocks.GameMatrix[i][j] == 1) {

								color = colors[2];
							} else if (Blocks.GameMatrix[i][j] == 2) {
								color = colors[3];
							} else if (Blocks.GameMatrix[i][j] == 3) {
								color = colors[4];
							} else if (Blocks.GameMatrix[i][j] == 4) {
								color = colors[5];
							} else if (Blocks.GameMatrix[i][j] == 5) {
								color = colors[6];
							} else if (Blocks.GameMatrix[i][j] == 6) {
								color = colors[7];
							} else if (Blocks.GameMatrix[i][j] == 7) {
								color = colors[8];
							}

						} else {

							if (Blocks.FuturePosition[i][j] == 1) {
								color = colors[9];
							} else if (Blocks.FuturePosition[i][j] == 2) {
								color = colors[10];
							} else if (Blocks.FuturePosition[i][j] == 3) {
								color = colors[11];
							} else if (Blocks.FuturePosition[i][j] == 4) {
								color = colors[12];
							} else if (Blocks.FuturePosition[i][j] == 5) {
								color = colors[13];
							} else if (Blocks.FuturePosition[i][j] == 6) {
								color = colors[14];
							} else if (Blocks.FuturePosition[i][j] == 7) {
								color = colors[15];
							} else {
								color = colors[1];
							}

						}
						g.setColor(color);
						g.fillRect(x, y, width, height);

					}
				}

				/////

				// score and high score
				String HighscoreString = Integer.toString(ScoreManager.Highscore);
				String ScoreString = Integer.toString(ScoreManager.score);

				int[][] ScoreArr = AssetManager.AssetCreater(ScoreString);
				int[][] HighScoreArr = AssetManager.AssetCreater(HighscoreString);

				ArrayBuilder(g, 300, 250, 5, 1, 0, 0, ScoreArr);

				ArrayBuilder(g, 300, 400, 5, 1, 0, 0, HighScoreArr);

				ArrayBuilder(g, 288, 20, 5, 1, 0, 0, AssetManager.NextPieceText);

				ArrayBuilder(g, 288, 200, 5, 2, 0, 0, AssetManager.ScoreText);

				ArrayBuilder(g, 288, 350, 5, 2, 0, 0, AssetManager.HighScoreText);

				// Box

				WireBox(g, 300, 50, 100, 100, 1);

				// Next block
				for (int j = 0; j < 4; j++) {
					for (int i = 0; i < 4; i++) {

						int x = 20 * j + (j + 310);
						int y = 20 * i + (i + 60);
						int width = 20;
						int height = 20;

						var color = colors[0];

						if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] != 0) {

							if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 1) {

								color = colors[2];
							} else if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 2) {
								color = colors[3];
							} else if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 3) {
								color = colors[4];
							} else if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 4) {
								color = colors[5];
							} else if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 5) {
								color = colors[6];
							} else if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 6) {
								color = colors[7];
							} else if (Blocks.GetCurrent(Blocks.BlockQueue[Blocks.BlockQueuePlace])[i][j] == 7) {
								color = colors[8];
							}

							g.setColor(color);
							g.fillRect(x, y, width, height);
						}
					}
				}
				// ------------

				if (GameStateManager.CurrentState == "Pause") {

					// pause blocks

					for (int j = 0; j < 6; j++) {
						for (int i = 0; i < 4; i++) {

							int x = 25 * j + (j + 25 + 50 + 2);
							int y = 25 * i + (i + 25 + 150 + 6);
							int width = 25;
							int height = 25;

							if (AssetManager.Pause[i][j] != 0) {

								g.setColor(colors[0]);
								g.fillRect(x, y, width, height);
							}
						}
					}

				}

			} else if (GameStateManager.CurrentState == "Menu") {

				// Title screen

				// Title animation timer
				if (DeltaTitle > 2000) {
					ColorWave = ColorWave6;
				} else if (DeltaTitle > 1600) {
					ColorWave = ColorWave5;
				} else if (DeltaTitle > 1200) {
					ColorWave = ColorWave4;
				} else if (DeltaTitle > 800) {
					ColorWave = ColorWave3;
				} else if (DeltaTitle > 400) {
					ColorWave = ColorWave2;
				} else {
					ColorWave = ColorWave1;
				}

				// Title screen
				for (int j = 0; j < 23; j++) {
					for (int i = 0; i < 5; i++) {

						int x = 20 * j + 10;
						int y = 20 * i + 100;
						int width = 20;
						int height = 20;

						var color = colors[2];

						if (j < 3) {
							color = colors[ColorWave[0]];
						} else if (j > 3 && j < 7) {
							color = colors[ColorWave[1]];
						} else if (j > 6 && j < 11) {
							color = colors[ColorWave[2]];
						} else if (j > 11 && j < 16) {
							color = colors[ColorWave[3]];
						} else if (j > 15 && j < 20) {
							color = colors[ColorWave[4]];
						} else if (j > 19) {
							color = colors[ColorWave[5]];
						}

						if (AssetManager.Title[i][j] != 0) {

							g.setColor(color);
							g.fillRect(x, y, width, height);
						}
					}
				}
				// ------

				// ArrayBuilder(g, 35, 300, 10, ColorWave[0], 0, 0, AssetManager.PressStart);

				// start Button
				if (Button.isButtonHover(mouse.x, mouse.y, 120, 280, 350, 370)) {
					// if hovering change color of button
					ArrayBuilder(g, 120, 280, 10, ColorWave[0], 1, ColorWave[0], AssetManager.StartButton);
				} else {
					ArrayBuilder(g, 120, 280, 10, ColorWave[0], 1, 0, AssetManager.StartButton);
				}

				// Help Button
				if (Button.isButtonHover(mouse.x, mouse.y, 140, 450, 350, 540)) {
					// if hovering change color of button
					ArrayBuilder(g, 140, 450, 10, 4, 1, 4, AssetManager.HelpButton);
				} else {
					ArrayBuilder(g, 140, 450, 10, 4, 1, 0, AssetManager.HelpButton);
				}

				// ---------------------------

			} else if (GameStateManager.CurrentState == "Help") {
				// Help

				// Back Button
				if (Button.isButtonHover(mouse.x, mouse.y, 140, 450, 350, 540)) {
					// if hovering change color of button
					ArrayBuilder(g, 140, 450, 10, 4, 1, 4, AssetManager.BackButton);
				} else {
					ArrayBuilder(g, 140, 450, 10, 4, 1, 0, AssetManager.BackButton);
				}

				//// ------------

				// draws the Key bindings

				ArrayBuilder(g, 5, 20, 10, ColorWave[0], 0, 0, AssetManager.KeyBindings);

				/// -------------

				ArrayBuilder(g, 30, 100, 5, 1, 0, 0, AssetManager.Arrow);
				ArrayBuilder(g, 100, 110, 5, ColorWave[0], 0, 0, AssetManager.TextCRotation);
				// up arrow
				/// -------------

				ArrayBuilder(g, 30, 150, 5, 1, 0, 0, AssetManager.ArrowDown);
				ArrayBuilder(g, 100, 160, 5, ColorWave[0], 0, 0, AssetManager.TextRotation);
				// down arrow
				/// --------------

				ArrayBuilder(g, 30, 200, 5, 1, 0, 0, AssetManager.ArrowLeft);
				ArrayBuilder(g, 100, 210, 5, ColorWave[0], 0, 0, AssetManager.LeftText);
				// left arrow
				/// -------------
				ArrayBuilder(g, 30, 250, 5, 1, 0, 0, AssetManager.ArrowRight);
				ArrayBuilder(g, 100, 260, 5, ColorWave[0], 0, 0, AssetManager.RightText);
				// right arrow
				// ----------

				ArrayBuilder(g, 30, 300, 5, 1, 0, 0, AssetManager.Enter);
				ArrayBuilder(g, 150, 310, 5, ColorWave[0], 0, 0, AssetManager.DownText);
				// enter key
				// --------------

				ArrayBuilder(g, 30, 350, 5, 1, 0, 0, AssetManager.Esc);
				ArrayBuilder(g, 120, 360, 5, ColorWave[0], 0, 0, AssetManager.PauseText);
				// esc key
				// --------------

			} else if (GameStateManager.CurrentState == "Splash") {
				// splash screen

				// credits and company name
				ArrayBuilder(g, 50, 450, 10, 4, 0, 0, AssetManager.CreditNick);

				ArrayBuilder(g, 300, 450, 10, 6, 0, 0, AssetManager.CreditSile);

				ArrayBuilder(g, 5, 250, 5, 1, 0, 0, AssetManager.Company);

				// falling animation

				// splahs screen animation Timing
				if (SplashFall < 200) {
					TempMatrix = AssetManager.SplashMatrix;
				} else if (SplashFall > 200 && SplashFall < 400) {
					TempMatrix = AssetManager.SplashMatrix1;
				} else if (SplashFall > 400 && SplashFall < 600) {
					TempMatrix = AssetManager.SplashMatrix2;
				} else if (SplashFall > 600 && SplashFall < 800) {
					TempMatrix = AssetManager.SplashMatrix3;
				} else if (SplashFall > 800) {
					TempMatrix = AssetManager.SplashMatrix4;
				}

				for (int j = 0; j < 3; j++) {
					for (int i = 0; i < 5; i++) {

						int x = 30 * j + 200;
						int y = 30 * i + 80;
						int height = 30;

						if (TempMatrix[i][j] == 0) {
							g.setColor(colors[1]);
						} else if (TempMatrix[i][j] == 1) {
							g.setColor(colors[2]);
						} else if (TempMatrix[i][j] == 2) {
							g.setColor(colors[3]);
						}

						g.drawLine(x, y + height - 1, x, y);
						g.drawLine(x, y, x + height - 1, y);
						g.drawLine(x + 1, y + height - 1, x + height - 1, y + height - 1);
						g.drawLine(x + height - 1, y + height - 1, x + height - 1, y + 1);
					}
				}

				// ----------

			} else if (GameStateManager.CurrentState == "Next") {

				ArrayBuilder(g, 50, 200, 10, 1, 0, 0, AssetManager.NextLevel);

				// level
				String Level = Integer.toString(Blocks.level);

				int[][] LevelArr = AssetManager.AssetCreater(Level);

				ArrayBuilder(g, 150, 300, 10, 1, 0, 0, LevelArr);
				// -------------
			}

		} else {
			// Game Over

			ArrayBuilder(g, 13, 100, 13, 2, 0, 0, AssetManager.GameOverText);
			// say game over
			if (ScoreManager.NewHighScore) {
				// if there is a new high score
				// say there is a new high score

				ArrayBuilder(g, 25, 200, 8, 2, 0, 0, AssetManager.NewHighScoreText);

				ArrayBuilder(g, 50, 300, 8, 2, 0, 0, AssetManager.EnterName);

				ArrayBuilder(g, 40, 500, 5, 2, 0, 0, AssetManager.EnterToEnd);

				int[][] InputArr = AssetManager.AssetCreater(ScoreManager.InputString);

				ArrayBuilder(g, 50, 400, 10, 1, 0, 0, InputArr);

				String str = ScoreManager.InputString.substring(0, ScoreManager.CurrentLetter + 1);

				String str2 = ScoreManager.InputString.substring(0, ScoreManager.CurrentLetter);

				int Temp1 = AssetManager.ArrLength(str);

				int Temp2 = AssetManager.ArrLength(str2);

				int Temp4 = AssetManager.ArrLength(ScoreManager.InputString);

				WireBox(g, ((Temp2 * 10) + 50), 400, 53, (Temp1 - Temp2) * 10 - 10, 4);

				ArrayBuilder(g, 10, 410, 3, 1, 0, 0, AssetManager.ArrowLeft);

				ArrayBuilder(g, (Temp4 * 10) + 50, 410, 3, 1, 0, 0, AssetManager.ArrowRight);

				ArrayBuilder(g, ((Temp2 * 10) + 50), 370, 3, 1, 0, 0, AssetManager.Arrow);

				ArrayBuilder(g, ((Temp2 * 10) + 50), 455, 3, 1, 0, 0, AssetManager.ArrowDown);

			} else {

				int[][] InputArr = AssetManager.AssetCreater(ScoreManager.HighScoreHolder);
				String HighscoreString = Integer.toString(ScoreManager.Highscore);
				int[][] HighScoreArr = AssetManager.AssetCreater(HighscoreString);

				ArrayBuilder(g, 50, 250, 10, 1, 0, 0, AssetManager.HighScoreText);

				ArrayBuilder(g, 50, 350, 10, 1, 0, 0, InputArr);

				ArrayBuilder(g, 50, 450, 10, 1, 0, 0, HighScoreArr);

			}

		}


*/

	}

	public static void Update() {
		// updates the render window
		frame.validate();
		frame.repaint();

	}

}