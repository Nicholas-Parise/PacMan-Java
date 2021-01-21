import java.util.concurrent.TimeUnit;

/***************************
 * Nicholas Parise, ICS4U, Pacman
 ****************************/

public class Main {

	public static void main(String[] args) {

		// Delta time is the time between frames, i use the time between frames as a way
		// to regulate game speed
		// this way the game could be going 10 fps or 1000 fps either way the block will
		// fall every 0.500 seconds (on the first level anyway)

		float last_time = System.nanoTime();
		float time = 0;
		float delta_time = 0;

		int deltaMove = 0;

		for (int i = 0; i < 31; i++) {
			System.out.print("{");
			for (int j = 0; j < 28; j++) {
				System.out.print("0,");

			}
			System.out.println("},");
		}

		// Sets up the game by
		// init the render window
		Render.construc();
		// reseting game matrix


		for (int i = 0; i < 31; i++) {
			
			for (int j = 0; j < 28; j++) {
				
			 if (AssetManager.GameMatrix[i][j] == 0){

				AssetManager.DotsMatrix[i][j] = 1;
			 }else if (AssetManager.GameMatrix[i][j] == 8){

				AssetManager.DotsMatrix[i][j] = 2;
			 }


			}
		}
		

		do {
			// changes delta time
			time = System.nanoTime();
			delta_time = (int) ((time - last_time) / 1000000);
			last_time = time;

			// Player.FindCol();
			// Player.FindRow();
			Player.CanDir();
			Player.FutureKey();
			Player.HitTest();

			
			if (Player.Canmove) {
				/*
				 * if(Player.CurrentDir == 0 && Player.CanUp){
				 * 
				 * //16 pixel every 1 second / if(deltaMove>1.6){ //up Player.Y -= 1; deltaMove
				 * = 0; } }else if(Player.CurrentDir == 2 && Player.CanDown){
				 * 
				 * if(deltaMove>1.6){ //down deltaMove = 0; Player.Y += 1; } }else
				 * if(Player.CurrentDir == 1 && Player.CanRight){
				 * 
				 * if(deltaMove>1.6){ //right Player.X += 1; deltaMove = 0; } }else
				 * if(Player.CurrentDir == 3 && Player.CanLeft){
				 * 
				 * if(deltaMove>1.6){ //left Player.X -= 1; deltaMove = 0; } }
				 */

				if (Player.CurrentDir == 0 && Player.CanUp) {

					// 16 pixel every 1 second

					if (deltaMove > 160) {
						// up

						Player.CurrentRow--;
						deltaMove = 0;
						Player.Xoff = 10;
						Player.Yoff = 10;
					}

					// multiples of 10
				//	if ((int)deltaMove % 10 == 0 ||(int)deltaMove % 10 == 5) {

					//	Player.Yoff --;
				//	}

				if(Player.Yoff <-16){
					Player.Yoff = -16;
				}

				} else if (Player.CurrentDir == 2 && Player.CanDown) {

					if (deltaMove > 160) {
						// down

						Player.CurrentRow++;
						deltaMove = 0;
						Player.Xoff = 10;
						Player.Yoff = 10;
					}
					// multiples of 10
			//		if ((int)deltaMove % 10 == 0 ||(int)deltaMove % 10 == 5) {
					//	Player.Yoff ++;


						if(Player.Yoff >26){
							Player.Yoff = 26;
						}
				//	}
				} else if (Player.CurrentDir == 1 && Player.CanRight) {

					if (deltaMove > 160) {
						// right

						Player.CurrentCol++;
						deltaMove = 0;
						Player.Xoff = 10;
						Player.Yoff = 10;
					}
					// multiples of 10
				//	if ((int)deltaMove % 10 == 0 ||(int)deltaMove % 10 == 5) {
				//		Player.Xoff++;
				//	}

				if(Player.Xoff>26){
					Player.Xoff = 26;
				}

				} else if (Player.CurrentDir == 3 && Player.CanLeft) {

					if (deltaMove > 160) {
						// left

						Player.CurrentCol--;
						deltaMove = 0;

						Player.Xoff = 10;
						Player.Yoff = 10;
					}
					// multiples of 10
				//	if ((int)deltaMove % 10 == 0 ||(int)deltaMove % 10 == 5) {
					//	Player.Xoff--;
				//	}

				if(Player.Xoff <-16){
					Player.Xoff = -16;
				}

				}

				
				Player.X = 16 * (Player.CurrentCol) + Player.Xoff;
				Player.Y = 16 * (Player.CurrentRow) + (16 * 3) + Player.Yoff;
			}



			if (Player.CurrentDir == 3 &&Player.CurrentRow == 14 && Player.CurrentCol==0) {
				// going left
				Player.CurrentCol=27;
			}

			if (Player.CurrentDir == 1 && Player.CurrentRow == 14 && Player.CurrentCol==27) {
				// going right


				Player.CurrentCol = 0;

			}


			if (Player.oldRow != Player.CurrentRow || Player.oldCol != Player.CurrentCol) {

				Player.oldCol = Player.CurrentCol;
				Player.oldRow = Player.CurrentRow;

				// int[] temp =

				 Maze.SolveMaze( Player.CurrentRow,Player.CurrentCol, 1,1,Player.CurrentDir);

				// Maze.SolveMaze(Player.CurrentCol, Player.CurrentRow,
				// 29,26,Player.CurrentDir);

				///	Maze.SolveMaze(20, 1, 29, 26, 5);

				// Maze.SolveMaze(29, 26, 1,1,5);

				/*
				 * System.out.println(); System.out.println();
				 * 
				 * for (int i = 0; i < 32; i++) { for (int j = 0; j < 30; j++) {
				 * 
				 * System.out.print(Maze.EnemyMazeSolution[i][j]); } System.out.println(); }
				 * System.out.println(); System.out.println();
				 */
			}
			

			

			System.out.println(Player.CurrentRow + " " + Player.CurrentCol + " " + Player.CurrentDir);
			// System.out.println(temp[0]+" "+temp[1]);

			deltaMove += delta_time;

			// for some reason .sleep must be in a try catch or else there is a compiler
			// error
			try {

				// update render window
				Render.Update();

				// wait 5 MILLISECONDS
				// people on the internet said this would help stop crashing
				// and since it doesn't crash it's here
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (Exception e) {
			}
			// change back
		} while (true);
	}
}
