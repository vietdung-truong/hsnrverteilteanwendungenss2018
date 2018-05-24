package noobchain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;

public class Receive extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblUri;
	private JLabel lblAdress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Receive dialog = new Receive();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Receive() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblUri = new JLabel("URI");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblUri, 70, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblUri, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblUri);
		}
		{
			lblAdress = new JLabel("Adress");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblAdress, 39, SpringLayout.SOUTH, lblUri);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblAdress, 0, SpringLayout.WEST, lblUri);
			contentPanel.add(lblAdress);
		}
		{
			textField = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblUri);
			sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 80, SpringLayout.EAST, lblUri);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblAdress);
			sl_contentPanel.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Receive");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
