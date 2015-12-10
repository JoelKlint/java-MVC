package gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Model;

public class KeyboardListener implements KeyListener {

	private GUI gui;
	private Model mon;

	public KeyboardListener(GUI gui, Model mon) {
		this.gui = gui;
		this.mon = mon;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyChar();
		if(65 <= c && c <= 90)	{
			gui.outputField.setText("Please only input numbers");
			return;
		}
		else	{
			try {
				mon.setAmount(Double.parseDouble(gui.inputField.getText()));
				mon.setFromCurrency(gui.currencyFrom.getSelectedItem().toString());
				mon.setToCurrency(gui.currencyTo.getSelectedItem().toString());
				mon.convert();
			} catch (NumberFormatException e1) {
				gui.outputField.setText("Please only input numbers");
			}
		}
		
		
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_LESS:
//			gui.outputField.setText("Please only input numbers");
//			break;
//		default:
//			try {
//				mon.setAmount(Double.parseDouble(gui.inputField.getText()));
//				mon.setFromCurrency(gui.currencyFrom.getSelectedItem().toString());
//				mon.setToCurrency(gui.currencyTo.getSelectedItem().toString());
//				mon.convert();
//			} catch (NumberFormatException e1) {
//				gui.outputField.setText("Please only input numbers");
//			}
//			break;
//		}
	}

}
