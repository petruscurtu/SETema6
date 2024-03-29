package mta.se.tema6.mvc.interfaces;

/**
 * Created by Scurtu Petru on 22/11/2014.
 * 
 * The interface made public to the controller (observer pattern) - goes both
 * ways
 */
public interface IModelListener {

	/**
	 * Notifies the listeners that an update occurred in the model
	 */
	public void onUpdate();
}
