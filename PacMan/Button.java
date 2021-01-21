/***************************
 * Nicholas Parise, 
 * Sile Keenan, 
 * ICS4U, 
 * Tetris
 ****************************/


//This class deals with the clickable buttons
public class Button {

	public static Boolean isButtonHover(int x, int y, int buttonx, int buttony, int buttonWidth, int buttonHeight) {
		// if the mosue is within the bounds of the button return true
		if (x > buttonx && x < buttonWidth) {

			if (y > buttony && y < buttonHeight) {
				return true;
			}
		}

		return false;
	}

}
