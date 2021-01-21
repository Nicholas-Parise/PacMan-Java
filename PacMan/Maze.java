/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// This class creates, solves and draws the maze
public class Maze {

	// return bigger number - smaller number
	private static int SmallestDistance(int Start, int Solution) {

		if (Start > Solution) {

			return Start - Solution;
		} else {
			return Solution - Start;
		}
	}

	public static ArrayList<Integer> ColQueue;
	public static ArrayList<Integer> RowQueue;

	public static int[][] EnemyMazeSolution = new int[32][30];
	public static int[][] QuickMazeSolution = new int[32][30];

	// Loop method of pathfinding
	public static int[] SolveMaze(int row, int col, int solutionRow, int solutionCol, int CurrentDir) {

		int oldDir = CurrentDir;

		Boolean firstTime = true;

		Boolean FoundExit = false;

		int[][] VisitedCell = new int[32][30];

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 30; j++) {

				EnemyMazeSolution[i][j] = 0;
				QuickMazeSolution[i][j] = 0;
				VisitedCell[i][j] = 0;
			}
		}

		int[] RowCol = new int[2];

		int StopCrash = 0;

		ArrayList<Integer> EnemyColQueue = new ArrayList<>();
		ArrayList<Integer> EnemyRowQueue = new ArrayList<>();

		if ((row == solutionRow) && (col == solutionCol)) {
			// if destination is reached, maze is solved

			RowCol[0] = solutionRow;
			RowCol[1] = solutionCol;

			FoundExit = true;
		} else {

			do {

				StopCrash++;

				if (EnemyRowQueue.size() - 1 > 1) {
					RowCol[0] = EnemyRowQueue.get(1);
					RowCol[1] = EnemyColQueue.get(1);
				} else if (EnemyRowQueue.size() - 1 == 1) {

					RowCol[0] = EnemyRowQueue.get(0);
					RowQueue = EnemyRowQueue;

					RowCol[1] = EnemyColQueue.get(0);
					ColQueue = EnemyColQueue;

				}

				if ((row == solutionRow) && (col == solutionCol)) {
					// if destination is reached, maze is solved

					VisitedCell[row][col] = 1;
					EnemyMazeSolution[row][col] = 1;

					EnemyRowQueue.add(row);
					EnemyColQueue.add(col);

					RowCol[0] = EnemyRowQueue.get(1);
					RowCol[1] = EnemyColQueue.get(1);

					// return RowCol;
					FoundExit = true;
				}

				// checking if we can visit this cell
				// and is making sure that the cell is not already visited

				if (row >= 0 && col >= 0 && row < 32 && col < 29) {

					if (VisitedCell[row][col] == 0) {
						EnemyRowQueue.add(row);
						EnemyColQueue.add(col);
					}

					VisitedCell[row][col] = 1;
					EnemyMazeSolution[row][col] = 1;

					// Marked as visited

					// checking distance to exit from each direction
					int RigthRow = SmallestDistance(row, solutionRow);
					int RigthCol = SmallestDistance(col + 1, solutionCol);
					int RigthScore = (RigthRow * RigthRow) + (RigthCol * RigthCol);

					int LeftRow = SmallestDistance(row, solutionRow);
					int LeftCol = SmallestDistance(col - 1, solutionCol);
					int LeftScore = (LeftRow * LeftRow) + (LeftCol * LeftCol);

					int UpRow = SmallestDistance(row - 1, solutionRow);
					int UpCol = SmallestDistance(col, solutionCol);
					int UpScore = (UpRow * UpRow) + (UpCol * UpCol);

					int DownRow = SmallestDistance(row + 1, solutionRow);
					int DownCol = SmallestDistance(col, solutionCol);
					int DownScore = (DownRow * DownRow) + (DownCol * DownCol);

					// creating and sorting a list of directions
					int[] AllScores = { UpScore, LeftScore, DownScore, RigthScore };
					int[] AllScoresTemp = AllScores;

					Arrays.sort(AllScoresTemp);

					int lowest = AllScoresTemp[0];
					int SecondLowest = AllScoresTemp[1];
					int ThridLowest = AllScoresTemp[2];
					int Highest = AllScoresTemp[3];

					boolean CanGoDown = false;
					boolean CanGoUp = false;
					boolean CanGoLeft = false;
					boolean CanGoRight = false;

					int index = EnemyRowQueue.size() - 1;

					if (index > 1) {
						CurrentDir += 10;
					} else {
						CurrentDir = oldDir;
					}

					// finds the lowest distance
					for (int i = 0; i < 4; i++) {

						if (AllScores[i] == lowest) {
							lowest = i;
							break;
						}
					}

					for (int i = 0; i < 4; i++) {

						if (AllScores[i] == SecondLowest) {

							if (i != lowest) {
								SecondLowest = i;
								break;
							}
						}
					}

					for (int i = 0; i < 4; i++) {

						if (AllScores[i] == ThridLowest) {
							if (i != SecondLowest) {
								ThridLowest = i;
								break;
							}
						}
					}

					for (int i = 0; i < 4; i++) {

						if (AllScores[i] == Highest) {
							if (i != ThridLowest) {
								Highest = i;
								break;
							}
						}
					}

					// Checking avaliable moves

					if (row > 0) {
						if (VisitedCell[row - 1][col] == 0 && AssetManager.GameMatrix[row - 1][col] != 1
								&& CurrentDir != 2) {
							CanGoUp = true;
						}
					}

					if (col > 0) {
						if (VisitedCell[row][col - 1] == 0 && AssetManager.GameMatrix[row][col - 1] != 1
								&& CurrentDir != 1) {
							CanGoLeft = true;
						}
					}
					if (row < 30) {
						if (VisitedCell[row + 1][col] == 0 && AssetManager.GameMatrix[row + 1][col] != 1
								&& CurrentDir != 0) {
							CanGoDown = true;
						}
					}

					if (col < 27) {
						if (VisitedCell[row][col + 1] == 0 && AssetManager.GameMatrix[row][col + 1] != 1
								&& CurrentDir != 3) {
							CanGoRight = true;
						}
					}

					// if can go a direction and it is the closest direction to the end goal go
					// there

					if (CanGoUp == true && lowest == 0) {

						// CurrentDir = 0;
						row = row - 1;

					} else if (CanGoLeft == true && lowest == 1) {

						col = col - 1;
						// CurrentDir = 3;
					} else if (CanGoDown == true && lowest == 2) {
						// CurrentDir = 2;
						row = row + 1;

					} else if (CanGoRight == true && lowest == 3) {
						// CurrentDir = 1;
						col = col + 1;

					} else {

						// if it can't go to the closest direction then go to the second closest

						if (CanGoUp == true && SecondLowest == 0) {
							// CurrentDir = 0;
							row = row - 1;

						} else if (CanGoLeft == true && SecondLowest == 1) {
							// CurrentDir = 3;
							col = col - 1;

						} else if (CanGoDown == true && SecondLowest == 2) {
							// CurrentDir = 2;
							row = row + 1;

						} else if (CanGoRight == true && SecondLowest == 3) {
							// CurrentDir = 1;
							col = col + 1;

						} else {
							// if it can't go to the closest direction then go to the third closest

							if (CanGoUp == true && ThridLowest == 0) {
								// CurrentDir = 0;
								row = row - 1;

							} else if (CanGoLeft == true && ThridLowest == 1) {
								// CurrentDir = 3;
								col = col - 1;

							} else if (CanGoDown == true && ThridLowest == 2) {
								// CurrentDir = 2;
								row = row + 1;

							} else if (CanGoRight == true && ThridLowest == 3) {
								// CurrentDir = 1;
								col = col + 1;

							} else {

								// if it can't go to the closest direction then go to the furthest closest

								if (CanGoUp == true && Highest == 0) {
									// CurrentDir = 0;
									row = row - 1;

								} else if (CanGoLeft == true && Highest == 1) {
									// CurrentDir = 3;
									col = col - 1;

								} else if (CanGoDown == true && Highest == 2) {
									// CurrentDir = 2;
									row = row + 1;

								} else if (CanGoRight == true && Highest == 3) {
									// CurrentDir = 1;
									col = col + 1;

								} else {

									// if it can't go to any prefered direction then go in any avaliable directions

									// if it can't go in any direction
									// backtrace

									// int index = EnemyRowQueue.size() - 1;

									// System.out.println("BackTrace " + index);

									EnemyMazeSolution[row][col] = 0;

									if (index > 1) {

										EnemyRowQueue.remove(index);
										EnemyColQueue.remove(index);

										index = EnemyRowQueue.size() - 1;

										row = EnemyRowQueue.get(index);
										col = EnemyColQueue.get(index);

									}

									else {
										StopCrash = 10000000;

									}
								}
							}
						}
					}
				} else {

					// if out of bounds backtrace

					int index = EnemyRowQueue.size() - 1;

					// System.out.println("BackTrace " + index);

					EnemyMazeSolution[row][col] = 0;

					if (index > 1) {

						EnemyRowQueue.remove(index);
						EnemyColQueue.remove(index);

						index = EnemyRowQueue.size() - 1;

						row = EnemyRowQueue.get(index);
						col = EnemyColQueue.get(index);

					}

					else {
						StopCrash = 10000000;
						// System.out.println("Stop Crash");

					}

				}

				// System.out.println(EnemyRowQueue.size() - 1);

				// System.out.println();

				try {
					// Render.Update();
					// TimeUnit.MILLISECONDS.sleep(1);
					// TimeUnit.NANOSECONDS.sleep(1);
				} catch (Exception e) {
				}

				// Make sure the program does not go on a forever loop if it can't find the end
			} while (StopCrash < 1000 && FoundExit == false);

			StopCrash = 0;

			// System.out.println("End");

			// do {

			StopCrash++;

			if ((EnemyRowQueue.size() - 1) > 0) {

				for (int i = 1; i < EnemyRowQueue.size() - 1; i++) {

					int ColChange = 0;

					row = EnemyRowQueue.get(i);
					col = EnemyColQueue.get(i);

					Boolean EndFirst = false;
					Boolean EndSecond = false;

					int FirstCol = 0;
					int SecondCol = 0;

					Boolean CantFirst = false;
					Boolean CantSecond = false;

					int EndLeft = 0;
					int EndRight = 0;

					while (ColChange < 29) {

						ColChange++;

						if ((col + ColChange) < 28 && !EndFirst && !CantFirst && ColChange > 1) {

							if (AssetManager.GameMatrix[row][col + ColChange] != 1) {
								if (EnemyMazeSolution[row][col + ColChange] == 1) {

									for (int k = 0; k < EnemyRowQueue.size() - 1; k++) {

										if (row == EnemyRowQueue.get(k) && col + ColChange == EnemyColQueue.get(k)) {
											// System.out.println("Found-Quicker-Path++");

											EndRight = k;

											// QuickMazeSolution[row][col + ColChange] = 1;
											QuickMazeSolution[row][col] = 2;

											FirstCol = ColChange;

											EndFirst = true;
											break;
										}
									}

								}
							} else {
								CantFirst = true;
							}
						}

						if ((col - ColChange) > -1 && !EndSecond && !CantSecond && ColChange > 1) {

							if (AssetManager.GameMatrix[row][col - ColChange] != 1) {

								if (EnemyMazeSolution[row][col - ColChange] == 1) {

									for (int k = 0; k < EnemyRowQueue.size() - 1; k++) {

										if (row == EnemyRowQueue.get(k) && col - ColChange == EnemyColQueue.get(k)) {
											// System.out.println("Found-Quicker-Path--");

											EndLeft = k;

											// QuickMazeSolution[row][col - ColChange] = 1;
											QuickMazeSolution[row][col] = 2;

											SecondCol = ColChange;

											EndSecond = true;
											break;
										}
									}

								}
							} else {
								CantSecond = true;

							}
						}

						// && !EndFirst && !EndSecond

						if ((CantSecond && CantFirst)) {// ||EndSecond || EndFirst
							break;
						}

					}

					if (EndLeft < EndRight && !CantFirst) { // && EndRight > 0

						for (int j = i + 1; j < EndRight; j++) {

							EnemyMazeSolution[EnemyRowQueue.get(j)][EnemyColQueue.get(j)] = 0;

							// EnemyRowQueue.remove(j);
							// EnemyColQueue.remove(j);

						}
						/*
						 * 
						 * 
						 * for (int j = EndRight + 1; j < i; j++) {
						 * 
						 * EnemyMazeSolution[EnemyRowQueue.get(j)][EnemyColQueue.get(j)] = 0;
						 * 
						 * // EnemyRowQueue.remove(j); // EnemyColQueue.remove(j); }
						 */

						i = EndRight;
						// add line

						for (int l = 1; l < FirstCol; l++) {

							EnemyMazeSolution[row][col + l] = 1;

						}

						// System.out.println("Low Right");

					} else if (EndLeft > EndRight && !CantSecond) { // && EndLeft > 0

						// System.out.println("Low Left");

						for (int j = i + 1; j < EndLeft; j++) {

							EnemyMazeSolution[EnemyRowQueue.get(j)][EnemyColQueue.get(j)] = 0;

							// EnemyRowQueue.remove(j);
							// EnemyColQueue.remove(j);

						}
						/*
						 * for (int j = EndLeft + 1; j < i; j++) {
						 * 
						 * EnemyMazeSolution[EnemyRowQueue.get(j)][EnemyColQueue.get(j)] = 0;
						 * 
						 * // EnemyRowQueue.remove(j); // EnemyColQueue.remove(j);
						 * 
						 * }
						 */

						i = EndLeft;
						// add line
						for (int l = 1; l < SecondCol; l++) {

							EnemyMazeSolution[row][col - l] = 1;

						}

					}

					// System.out.println("Left: " + EndLeft + " Right: " + EndRight);

					// System.out.println("At index: " + i + " in " + ColChange);

					// System.out.println();

					try {
						// Render.Update();

						// TimeUnit.NANOSECONDS.sleep(10000);
						// TimeUnit.MILLISECONDS.sleep(1);
					} catch (Exception e) {
					}

				}

			}

		}

		// System.out.println("End");
		return RowCol;
	}

}
