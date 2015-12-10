package main;
import javax.swing.SwingUtilities;

import gui.ButtonListener;
import gui.CurrencyPickerListener;
import gui.GUI;
import gui.KeyboardListener;

public class Main {

	public static void main(String[] args) {
		
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run()	{
//				
//			}
//		});
		
		GUI gui = new GUI();
		Model mon = new Model();
		mon.addObserver(gui);
		ButtonListener bListener = new ButtonListener(gui, mon);
		gui.setButtonListener(bListener);
		CurrencyPickerListener iListener = new CurrencyPickerListener(gui, mon);
		gui.setCurrencyPickerListener(iListener);
		KeyboardListener kListener = new KeyboardListener(gui, mon);
		gui.setKeyboardListener(kListener);
		
		mon.updateExchangeRates();
	}

}
