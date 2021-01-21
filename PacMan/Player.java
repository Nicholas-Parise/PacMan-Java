//extends Entity
public class Player {

    public static int Xoff = 10;
    public static int Yoff = 10;

    public static int X = 16 * 1 + Xoff;
    public static int Y = 16 * 1 + (16 * 3) + Yoff;

    public static int CurrentRow = 1;
    public static int CurrentCol = 6;

    public static int oldRow = 0;
    public static int oldCol = 0;

    public static Boolean moving = false;
    public static Boolean Canmove = true;

    public static Boolean CanUp = false;
    public static Boolean CanRight = false;
    public static Boolean CanDown = false;
    public static Boolean CanLeft = false;

    public static int CurrentDir = 4;

    public static int FutureMove = 0;

    public static int DotsEaten = 0;

    public static Boolean PoweredUp = false;


    public static void FutureKey() {

        if (FutureMove == 37) {
            // left arrow key
        
            if (Player.CanLeft) {
                Player.CurrentDir = 3;
            }

        }
        if (FutureMove == 39) {
            // right arrow key

            if (Player.CanRight) {
                Player.CurrentDir = 1;
            }

        }
        if (FutureMove == 38) {
            // up arrow key
      
            if (Player.CanUp) {
                Player.CurrentDir = 0;
            }
        }
        if (FutureMove == 40) {
            // down arrow key

            if (Player.CanDown) {
                Player.CurrentDir = 2;
            }
        }

    }


    public static void HitTest() {

        if (AssetManager.DotsMatrix[CurrentRow][CurrentCol] == 1) {
            // on Dot
            DotsEaten++;
            AssetManager.DotsMatrix[CurrentRow][CurrentCol] = 0;
		}

		if (AssetManager.DotsMatrix[CurrentRow][CurrentCol] == 2) {
            // on Power up Dot
            DotsEaten++;
            AssetManager.DotsMatrix[CurrentRow][CurrentCol] = 0;
		}
    }


    public static void CanDir() {

        if (AssetManager.GameMatrix[CurrentRow - 1][CurrentCol] != 1) {

            CanUp = true;

        } else {
            CanUp = false;
        }

        if (AssetManager.GameMatrix[CurrentRow + 1][CurrentCol] != 1) {

            CanDown = true;

        } else {
            CanDown = false;
        }

        if (CurrentCol <27 && AssetManager.GameMatrix[CurrentRow][CurrentCol + 1] != 1) {

            CanRight = true;

        } else {
            CanRight = false;
        }
     
        if (CurrentCol >0&&AssetManager.GameMatrix[CurrentRow][CurrentCol - 1] != 1) {

            CanLeft = true;

        } else {
            CanLeft = false;
        }

    }

    public static int FindRow() {

        int tempRow = 0;

        for (int j = 0; j < 28; j++) {

            int Testx = 16 * j;
            int Testx2 = 16 * (j + 1);

            if ((X) >= Testx && (X) <= Testx2) {

                tempRow = j;
            }
        }
        CurrentRow = tempRow;
        return tempRow;
    }

    public static int FindCol() {

        int tempCol = 0;

        for (int i = 0; i < 31; i++) {

            int Testy = 16 * i + (16 * 3);
            int Testy2 = 16 * (i + 1) + (16 * 3);

            if ((Y) >= Testy && (Y) <= Testy2) {

                tempCol = i;
            }
        }
        CurrentCol = tempCol;
        return tempCol;
    }

}