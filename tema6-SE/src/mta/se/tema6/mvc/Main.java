package mta.se.tema6.mvc;

import mta.se.tema6.mvc.controllers.MeteoController;
import mta.se.tema6.mvc.model.MeteoModel;
import mta.se.tema6.mvc.views.MeteoView;

/**
 * @author petru scurtu
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Instantiate the MVC elements
		MeteoModel model = new MeteoModel();
		MeteoView view = new MeteoView();
		MeteoController controller = new MeteoController();

		// Attach the view to the model
		model.addModelListener(view);

		// Tell the view about the model and the controller
		view.addModel(model);
		view.addController(controller);

		// Tell the controller about the model and the view
		controller.addModel(model);
		controller.addView(view);

		// Display the view
		view.setVisible(true);
	}

}
