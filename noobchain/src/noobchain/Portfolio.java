package noobchain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.List;
import java.awt.Insets;

public class Portfolio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblKontostand;
	private JTextField textField;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Portfolio dialog = new Portfolio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Portfolio() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblKontostand = new JLabel("aktueller Kontostand");
			lblKontostand.setBounds(120, 14, 99, 14);
		}
		contentPanel.setLayout(null);
		{
			table = new JTable();
			table.setBounds(62, 40, 0, 0);
			contentPanel.add(table);
		}
		contentPanel.add(lblKontostand);
		{
			textField = new JTextField();
			textField.setBounds(225, 11, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			List list = new List();
			list.setBounds(10, 53, 414, 165);
			contentPanel.add(list);
		}
		{
			table_1 = new JTable();
			table_1.setBounds(377, 40, 0, 0);
			contentPanel.add(table_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
