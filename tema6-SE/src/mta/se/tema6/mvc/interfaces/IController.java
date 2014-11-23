package mta.se.tema6.mvc.interfaces;

import java.awt.event.ActionListener;

/**
 * Created by Scurtu Petru on 22/11/2014.
 * 
 * The interface implemented by the controller and made public so that all views
 * can use it
 */
public interface IController extends ActionListener {
	public static final String ACTION_GET_TEMPERATURE = "TEMPERATURE";
	public static final String ACTION_GET_WIND_SPEED = "WIND_SPEED";
}
