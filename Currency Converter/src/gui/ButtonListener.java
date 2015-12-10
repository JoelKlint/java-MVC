package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Model;

public class ButtonListener implements ActionListener {

	private GUI gui;
	private Model mon;

	public ButtonListener(GUI gui, Model mon) {
		this.gui = gui;
		this.mon = mon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "update-currencies":
			System.out.println("Update button pressed");
			mon.updateExchangeRates();
			break;
		default:
			System.out.println("Unknown button pressed");
			break;
		}
	}

}
