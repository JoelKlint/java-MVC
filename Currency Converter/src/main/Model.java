package main;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

public class Model extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -663558561209691994L;
	private double amount;
	private String currencyFrom, currencyTo;
	private HashMap<String, HashMap<String, Double>> currencies;

	public Model() {
		this.currencies = new HashMap<>();
		HashMap<String, Double> rates = new HashMap<>();
		rates.put("EUR", 0.1075);
		rates.put("USD", 0.1115);
		rates.put("SEK", 1.0);
		currencies.put("SEK", rates);

		rates = new HashMap<>();
		rates.put("SEK", 9.3);
		rates.put("USD", 0.0);
		rates.put("EUR", 1.0);
		currencies.put("EUR", rates);

		rates = new HashMap<>();
		rates.put("SEK", 8.54);
		rates.put("EUR", 0.0);
		rates.put("USD", 1.0);
		currencies.put("USD", rates);

	}

	public Set<String> getNameOfAllCurrencies() {
		return currencies.keySet();
	}

	public void convert() {
		//Make sure not converting to same currency as converting from
//		if(currencyTo == null || currencyTo.equals(currencyFrom))	{
//			setChanged();
//			notifyObservers(new Message("Please do not pick the same currencies"));
//			return;
//		}
		HashMap<String, Double> exchangeRates = null;
		try {
			exchangeRates = currencies.get(currencyFrom);
		} catch (NullPointerException e) {
			setChanged();
			notifyObservers(new Message("Make sure to pick a currency to exchange from"));
		}
		double rate = 0;
		try {
			rate = exchangeRates.get(currencyTo);
		} catch (NullPointerException e) {
			setChanged();
			notifyObservers(new Message("Make sure to pick a currency to exchange to"));
		}
		double conversion = 0;
		try {
			conversion = amount * rate;
		} catch (NullPointerException e) {
			setChanged();
			notifyObservers(new Message("Make sure to enter an amount"));
		}
		setChanged();
		notifyObservers(new Message(amount + " " + currencyFrom + " = " + conversion + " " + currencyTo));
	}

	public void updateExchangeRates() {
		// TODO: Update currencies for real
		setChanged();
		notifyObservers(currencies.keySet());
	}

	public void setFromCurrency(String pickedCurrency) {
		currencyFrom = pickedCurrency;
		setChanged();
		notifyObservers();
	}

	public void setToCurrency(String pickedCurrency) {
		currencyTo = pickedCurrency;
		setChanged();
		notifyObservers();
	}

	public void setAmount(double amount) {
		this.amount = amount;
		setChanged();
		notifyObservers();
	}

}
