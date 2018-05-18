package noobchain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Send extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblAmount;
	private JLabel lblRecipient;
	private JTextField textField_2;
	private JLabel lblDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Send dialog = new Send();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Send() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblAmount = new JLabel("Amount");
			contentPanel.add(lblAmount);
		}
		{
			lblRecipient = new JLabel("Recipient");
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblRecipient, 17, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblAmount, 33, SpringLayout.SOUTH, lblRecipient);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblAmount, 0, SpringLayout.WEST, lblRecipient);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblRecipient, 38, SpringLayout.NORTH, contentPanel);
			contentPanel.add(lblRecipient);
		}
		{
			lblDescription = new JLabel("Description");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDescription, 40, SpringLayout.SOUTH, lblAmount);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, lblAmount);
			contentPanel.add(lblDescription);
		}
		{
			textField = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblRecipient);
			sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 54, SpringLayout.EAST, lblRecipient);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblAmount);
			sl_contentPanel.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		
		textField_2 = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_2, -3, SpringLayout.NORTH, lblDescription);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Send");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
