package mta.se.tema6.mvc.controllers;

import mta.se.tema6.mvc.exceptions.CustomException;
import mta.se.tema6.mvc.interfaces.IController;
import mta.se.tema6.mvc.interfaces.IView;
import mta.se.tema6.mvc.model.MeteoModel;
import mta.se.tema6.mvc.utils.JSONHelper;
import mta.se.tema6.mvc.utils.Weather;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MeteoController implements IController {
	// The Controller needs to interact with both the Model and View.
	private MeteoModel mModel;

	// The list of views that listen for updates
	private List<IView> mViews;

	/**
	 * Constructor
	 */
	public MeteoController() {
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(ACTION_GET_TEMPERATURE)) {
			// Get the temperature
			try {
				JButton source = (JButton) event.getSource();
				if (source != null
						&& source.getAction() != null
						&& source.getAction().getValue(ACTION_GET_TEMPERATURE) != null) {
					getTemperature();
				} else {
					notifyViews(true, "Invalid operation data");
				}
			} catch (CustomException e) {
				notifyViews(true, e.getMessage());
			} catch (ClassCastException ec) {
				notifyViews(true, ec.getMessage());
			}
		} else if (event.getActionCommand().equals(ACTION_GET_WIND_SPEED)) {
			// Get wind speed
			try {
				JButton source = (JButton) event.getSource();
				if (source != null
						&& source.getAction() != null
						&& source.getAction().getValue(ACTION_GET_WIND_SPEED) != null) {
					String userInput = source.getAction()
							.getValue(ACTION_GET_WIND_SPEED).toString();
					getWindSpeed();
				} else {
					notifyViews(true, "Invalid operation data");
				}
			} catch (CustomException e) {
				notifyViews(true, e.getMessage());
			} catch (ClassCastException ec) {
				notifyViews(true, ec.getMessage());
			}
		}
	}

	/**
	 * Adds a view reference in order to interact with it
	 * 
	 * @param view
	 *            The view from the controller will receive events and send
	 *            messages
	 */
	public void addView(IView view) {
		if (mViews == null) {
			mViews = new ArrayList<IView>();
		}

		mViews.add(view);
	}

	/**
	 * Adds a reference to the model, so it can update it
	 * 
	 * @param model
	 *            The data model reference
	 */
	public void addModel(MeteoModel model) {
		mModel = model;
	}

	/**
	 * Notifies the views when an message must be displayed
	 * 
	 * @param isError
	 *            {@code true} if the message is an error, {@code false}
	 *            otherwise
	 * @param message
	 *            The string to be displayed
	 */
	private void notifyViews(boolean isError, String message) {
		if (mViews != null && !mViews.isEmpty()) {
			for (IView view : mViews) {
				view.onMessage(isError, message);
			}
		}
	}

	/**
	 * sets the wind speed
	 * 
	 * @throws CustomException
	 */
	private void getWindSpeed() throws CustomException {
		float speed = 0;
		try {
			// Update the model

			String response = Weather.getWeatherData("lat=44.4325&lon=26.1039");
			speed = JSONHelper.getWindInfo(response);
			if (speed == Float.NEGATIVE_INFINITY)
				throw new NumberFormatException();
			mModel.setWindValue(Float.toString(speed));

		} catch (NumberFormatException e) {
			throw new CustomException(Float.toString(speed), e.getMessage());
		}
	}

	/**
	 * sets the temperature
	 * 
	 * @throws CustomException
	 */
	private void getTemperature() throws CustomException {
		// TODO Auto-generated method stub
		float temp = 0;
		try {
			// Update the model
			String response = Weather.getWeatherData("lat=44.4325&lon=26.1039");
			temp = JSONHelper.getTempInfo(response);
			if (temp == Float.NEGATIVE_INFINITY)
				throw new NumberFormatException();
			mModel.setTempValue(Float.toString(temp));
		} catch (NumberFormatException e) {
			throw new CustomException(Float.toString(temp), e.getMessage());
		}
	}
}
