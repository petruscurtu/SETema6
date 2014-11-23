package mta.se.tema6.mvc.views;

import mta.se.tema6.mvc.interfaces.IController;
import mta.se.tema6.mvc.interfaces.IModelListener;
import mta.se.tema6.mvc.interfaces.IView;
import mta.se.tema6.mvc.model.MeteoModel;
import mta.se.tema6.mvc.utils.MeteoAction;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MeteoView extends JFrame implements IModelListener, IView {
	private static final long serialVersionUID = -5758555454500685115L;

	// View Components
	private JTextField tempTf = new JTextField(10);
	private JTextField windTf = new JTextField(10);
	private JButton tempBtn = new JButton("Show temperature");
	private JButton windBtn = new JButton("Show wind speed");


	private MeteoModel mModel;

	/**
	 * Constructor
	 */
	public MeteoView() {
		// Initialize components
		windTf.setEditable(false);
		tempTf.setEditable(false);
		
		tempBtn.setAction(new MeteoAction());
		tempBtn.getAction().putValue(IController.ACTION_GET_TEMPERATURE,
				MeteoModel.INITIAL_VALUE);
		tempBtn.setActionCommand(IController.ACTION_GET_TEMPERATURE);
		tempBtn.setText("Show Temperature");
		windBtn.setAction(new MeteoAction());
		windBtn.getAction().putValue(IController.ACTION_GET_WIND_SPEED,
				MeteoModel.INITIAL_VALUE);
		windBtn.setActionCommand(IController.ACTION_GET_TEMPERATURE);
		windBtn.setText("Show wind speed");
		
		// Layout the components.
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		content.add(new JLabel("Temperature"));
		content.add(tempTf);
		content.add(tempBtn);
		content.add(new JLabel("Wind Speed"));
		content.add(windTf);
		content.add(windBtn);

		// Finalize layout
		this.setContentPane(content);
		this.pack();

		this.setTitle("Meteo");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Sets the view's reference to the model - Only get operations allowed
	 * 
	 * @param model
	 *            The calc model
	 */
	public void addModel(MeteoModel model) {
		mModel = model;
		windTf.setText(model.getWindValue()+" KM/H");
		tempTf.setText(model.getTempValue()+" °C");
	}

	/**
	 * Sets the view's event listener - the controller - so that the changes
	 * made by the user in the view, can be reflected in the model
	 * 
	 * @param controller
	 *            The controller (event listener)
	 */
	public void addController(IController controller) {
		tempBtn.setActionCommand(IController.ACTION_GET_TEMPERATURE);
		tempBtn.addActionListener(controller);

		windBtn.setActionCommand(IController.ACTION_GET_WIND_SPEED);
		windBtn.addActionListener(controller);
	}

	@Override
	public void onMessage(boolean isError, String message) {
		if (isError) {
			JOptionPane.showMessageDialog(this, message, "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, message, "Calc MVC",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void onUpdate() {
		windTf.setText(mModel.getWindValue()+" KM/H");
		tempTf.setText(mModel.getTempValue()+" °C");
	}
}