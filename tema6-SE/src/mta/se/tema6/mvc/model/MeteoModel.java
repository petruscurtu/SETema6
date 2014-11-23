package mta.se.tema6.mvc.model;


import java.util.ArrayList;
import java.util.List;

import mta.se.tema6.mvc.exceptions.CustomException;
import mta.se.tema6.mvc.interfaces.IModelListener;

public class MeteoModel {
	
	// Constants
		public static final String INITIAL_VALUE = "0";

		// Member variable defining state of calculator, the total current value
		private float temp;
		private float wind;

		private List<IModelListener> mListeners;

		/**
		 * Constructor
		 */
		public MeteoModel() {
			temp=Float.parseFloat(INITIAL_VALUE);
			wind=Float.parseFloat(INITIAL_VALUE);
		}

		/**
		 * Set the temperature value.
		 * 
		 * @param value
		 *            New value that should be used for the temperature.
		 */
		public void setTempValue(String value) throws CustomException {
			try {
				temp=Float.parseFloat(value);
				notifyListeners();
			} catch (NumberFormatException e) {
				throw new CustomException(value, e.getMessage());
			}
		}
		
		/**
		 * Set the wind speed value.
		 * 
		 * @param value
		 *            New value that should be used for the wind speed.
		 */
		public void setWindValue(String value) throws CustomException {
			try {
				wind=Float.parseFloat(value);
				notifyListeners();
			} catch (NumberFormatException e) {
				throw new CustomException(value, e.getMessage());
			}
		}

		/**
		 * Return current temperature
		 */
		public String getTempValue() {
			return Float.toString(temp);
		}
		
		/**
		 * Return current wind speed
		 */
		public String getWindValue() {
			return Float.toString(wind);
		}

		/**
		 * Adds the view listener to the list
		 * 
		 * @param listener
		 *            The model event listener
		 */
		public void addModelListener(IModelListener listener) {
			if (mListeners == null) {
				mListeners = new ArrayList<IModelListener>();
			}

			mListeners.add(listener);
		}

		/**
		 * Notifies the views listeners of the changed state (value)
		 */
		private void notifyListeners() {
			if (mListeners != null && !mListeners.isEmpty()) {
				for (IModelListener listener : mListeners)
					listener.onUpdate();
			}
		}

}
