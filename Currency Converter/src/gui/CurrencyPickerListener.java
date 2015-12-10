package gui;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import main.Model;

public class CurrencyPickerListener implements ItemListener {
	
	private GUI gui;
	private Model mon;

	public CurrencyPickerListener(GUI gui, Model mon) {
		this.gui = gui;
		this.mon = mon;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(gui.currencyFrom))	{
			switch (e.getStateChange()) {
			case ItemEvent.SELECTED:
				mon.setFromCurrency(e.getItem().toString());
				mon.convert();
				break;
			case ItemEvent.DESELECTED:
				break;
			default:
				break;
			}
		}
		else if(e.getSource().equals(gui.currencyTo))	{
			switch (e.getStateChange()) {
			case ItemEvent.SELECTED:
				mon.setToCurrency(e.getItem().toString());
				mon.convert();
				break;
			case ItemEvent.DESELECTED:
				break;
			default:
				break;
			}
		}
	}

}
