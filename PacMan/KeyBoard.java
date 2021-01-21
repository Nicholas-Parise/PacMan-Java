import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/***************************
 * Nicholas Parise, ICS4U, Tetris
 ****************************/

/// Handles Keyboard input

public class KeyBoard extends JPanel implements KeyListener {

	public void keyPressed(KeyEvent evt) {

		int keyCode = evt.getKeyCode();

		Player.FutureMove = keyCode;

		if (keyCode == KeyEvent.VK_LEFT) {
			// left arrow key
			System.out.println("Left");
			if (Player.CanLeft) {
				Player.CurrentDir = 3;
			}

			// if in game over change change which letter can be changed
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			// right arrow key
			System.out.println("Right");
			if (Player.CanRight) {
				Player.CurrentDir = 1;
			}
			// if in game over change change which letter can be changed

		}
		if (keyCode == KeyEvent.VK_UP) {
			// up arrow key
			System.out.println("Up");
			if (Player.CanUp) {
				Player.CurrentDir = 0;
			}

			// change change the current letter
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			// down arrow key
			// change change the current letter
			System.out.println("Down");
			if (Player.CanDown) {
				Player.CurrentDir = 2;
			}
		}

	}

	public void keyTyped(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			// left arrow key

			if (Player.CanLeft) {
				Player.CurrentDir = 3;
			}
			// if in game over change change which letter can be changed
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			// right arrow key

			if (Player.CanRight) {
				Player.CurrentDir = 1;
			}
			// if in game over change change which letter can be changed

		}
		if (keyCode == KeyEvent.VK_UP) {
			// up arrow key

			if (Player.CanUp) {
				Player.CurrentDir = 0;
			}
			// change change the current letter
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			// down arrow key
			// change change the current letter
			if (Player.CanDown) {
				Player.CurrentDir = 2;
			}
		}
	}

	// this funtion is necessary to stop run time error but is unused
	public void keyReleased(KeyEvent e) {
	}

}
