package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import main.Message;
import main.Model;

public class GUI implements Observer {

	JButton getCurrenciesButton;
	JComboBox<String> currencyFrom, currencyTo;
	JTextField inputField;
	JLabel toLabel, outputField;
	Panel topPanel, bottomPanel;

	public GUI() {
		JFrame frame = new JFrame("Currency Converter");
		frame.setPreferredSize(new Dimension(500, 300));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Top area
		topPanel = new Panel();
		frame.add(topPanel, BorderLayout.NORTH);
		getCurrenciesButton = new JButton("Update currencies");
		getCurrenciesButton.setActionCommand("update-currencies");
		topPanel.add(getCurrenciesButton);

		// Bottom area
		bottomPanel = new Panel(new GridBagLayout());
		frame.add(bottomPanel, BorderLayout.SOUTH);

		GridBagConstraints c = new GridBagConstraints();
		inputField = new JTextField();
		inputField.setActionCommand("input");
		//TODO: Only allow numbers

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		bottomPanel.add(inputField, c);

		currencyFrom = new JComboBox<>();
		currencyFrom.setActionCommand("currency-from");
		bottomPanel.add(currencyFrom);

		toLabel = new JLabel("to");
		bottomPanel.add(toLabel);

		currencyTo = new JComboBox<String>();
		currencyTo.setActionCommand("currency-to");
		bottomPanel.add(currencyTo);

		// Center area
		outputField = new JLabel();
		outputField.setHorizontalAlignment(SwingConstants.CENTER);
		Font outputFont = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
		outputField.setFont(outputFont);
		frame.add(outputField, BorderLayout.CENTER);

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void output(String text) {
		outputField.setText(text);
	}

	public void setButtonListener(ActionListener listener) {
		getCurrenciesButton.addActionListener(listener);
	}

	public void setCurrencyPickerListener(ItemListener listener) {
		currencyFrom.addItemListener(listener);
		currencyTo.addItemListener(listener);
	}

	public void setKeyboardListener(KeyListener listener) {
		inputField.addKeyListener(listener);
		// convertButton.addKeyListener(listener);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Model) {
			if (arg instanceof Set<?>) {
				currencyTo.removeAllItems();
				currencyFrom.removeAllItems();
				Iterator<String> it = ((Set<String>) arg).iterator();
				while (it.hasNext()) {
					String currency = it.next().toString();
					currencyTo.addItem(currency);
					currencyFrom.addItem(currency);
				}
			} else if (arg instanceof Message) {
				outputField.setText(((Message) arg).getMessage());
			}
		}
	}
}
