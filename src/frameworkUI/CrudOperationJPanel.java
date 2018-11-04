package frameworkUI;
import framework.TableObject;
import framework.SqlConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrudOperationJPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Map<String, JTextField> textFields;
	JButton mainButton;
	CrudOperation operation;
	SqlConnection connection;
	TableObject object;

	public CrudOperationJPanel(TableObject object, CrudOperation operation, SqlConnection connection)  {
		this.textFields = new HashMap<>();
		this.mainButton = new JButton(operation.getName());
		this.operation = operation;
		this.connection = connection;
		this.object = object;

		Map<String, Object> dicObject = object.convertToDict();

		int yPosition = 10;

		for ( Map.Entry<String, Object> entry : dicObject.entrySet() ) {
			String key = entry.getKey();
			JTextField textField = new JTextField();
			JLabel label = new JLabel(key);

			textField.setBounds(200, yPosition, 100, 30);
			label.setBounds(100, yPosition, 120, 30);

			this.textFields.put(key, textField);
			
			this.add(textField);
			this.add(label);
			if((operation != CrudOperation.Create && operation != CrudOperation.Update) && key != "id") {
				textField.hide();
			}
			yPosition = yPosition + 50;
		}
		mainButton.setBounds(200, yPosition, 100, 30);
		this.add(mainButton);
		this.mainButton.addActionListener(this);
	}

	/**
	 * Método que trata quando uma ação é executada 
	 */
	public void actionPerformed(ActionEvent e)  {
		if (e.getSource()==this.mainButton) {
			switch (this.operation) {
			case Create:
				this.create();
				break;
			case Read:
				this.read();
				break;
			case Update:
				this.update();
				break;
			case Delete:
				this.delete();
				break;
			}
		}
	}

	public void create() {
		HashMap<String, Object> properties = new HashMap<>();
		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
			String key = entry.getKey();
			Object value = entry.getValue().getText();
			
			if(key == "id") {
				value = 0;
			}
			
			//Adiciona aspas simples nas STRINGS (VARCHAR), pois o banco n�o aceita sem -- famosa gambeta
			value = "'" + value + "'";
			
			
			properties.put(key, value);
		}
		object.setProperties(properties);
		if (connection.createObject(object)) {
			showMessage(object.getClass().getSimpleName() + " criado com sucesso");
		} else {
			showMessage("Não foi possível criar o objeto "+ object.getClass().getSimpleName());
		}
	}

	public void read() {
		try {
		String id = "";
		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
			String key = entry.getKey();
			if (key == "id") {
				JTextField idTextField = entry.getValue();
				id = idTextField.getText();
				object.setId(Integer.parseInt(id));
				break;
			}
		}
		TableObject readObject = connection.readObject(object, Integer.parseInt(id));
		Map<String, Object> objectProperties = readObject.convertToDict();
		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
			String objectProperty = objectProperties.get(entry.getKey()).toString();
			JTextField textField = entry.getValue();
			textField.setText(objectProperty);
			textField.show();
		}
		} catch(NullPointerException e) {
			showMessage("Objeto não encontrado");
		} catch(Exception e) {
			showMessage(e.toString());
		}
	}

	public void update() {
		String id= "";
		HashMap<String, Object> properties = new HashMap<>();
		properties = new HashMap<>();
		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
			String key = entry.getKey();
			Object value = entry.getValue().getText();
			properties.put(key, value);
		}
		object.setProperties(properties);
		if (connection.updateObject(object, Integer.parseInt(id))) {
			showMessage(object.getClass().getSimpleName() + " alterado com sucesso");
		} else {
			showMessage("Não foi possível alterar o objeto "+ object.getClass().getSimpleName());
		}
	}

	public void delete() {
		String id = "";
		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
			String key = entry.getKey();
			if (key == "id") {
				JTextField idTextField = entry.getValue();
				id = idTextField.getText();
				object.setId(Integer.parseInt(id));
				if (connection.deleteObject(object)) {
					showMessage(object.getClass().getSimpleName() + " removido com sucesso");
				} else {
					showMessage("Não foi remover o objeto "+ object.getClass().getSimpleName());
				}
			}
		}
	}
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
