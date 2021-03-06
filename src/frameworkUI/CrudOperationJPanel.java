package frameworkUI;

import framework.TableObject;
import framework.SqlConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
	JButton secondaryButton;
	CrudOperation operation;
	SqlConnection connection;
	TableObject object;

	public CrudOperationJPanel(TableObject object, CrudOperation operation, SqlConnection connection) {
		this.textFields = new HashMap<>();
		this.mainButton = new JButton(operation.getName());
		this.operation = operation;
		this.connection = connection;
		this.object = object;

		Map<String, Object> dicObject = object.convertToDict();

		int yPosition = 10;

		for (Map.Entry<String, Object> entry : dicObject.entrySet()) {
			String key = entry.getKey();
			JTextField textField = new JTextField();
			JLabel label = new JLabel(key.substring(0, 1).toUpperCase() + key.substring(1));
						
			label.setToolTipText(dicObject.get(key).getClass().getSimpleName());
			
			//Caso seja uma primary key, ira adicionar a mensagem na label
			if(object.getPrimaryKey().contains(key))
				label.setToolTipText(label.getToolTipText() + " - PRIMARY KEY ");
			
			//Caso seja uma chave estrangeira adiciona a mensagem na label
			if(object.getForeignKey().keySet().contains(key))
			label.setToolTipText(label.getToolTipText() + "- FOREIGN KEY: Class:" + object.getForeignKey().keySet() 
					+ " Column: " + object.getForeignKey().values());
			
			textField.setBounds(250, yPosition, 100, 30);
			label.setBounds(100, yPosition, 120, 30);

			this.textFields.put(key, textField);

			this.add(textField);
			this.add(label);
			if ((operation != CrudOperation.Create && operation != CrudOperation.Update) && key != "id") {
				textField.hide();
			}
			yPosition = yPosition + 50;
		}
		
//		for (Map.Entry<String, HashMap<String, String>> entry : object.getForeignKey().entrySet()) {
//			String key = entry.getKey();
//			HashMap<String, String> value = entry.getValue();
//			
//			JTextField textField = new JTextField();
//			JLabel label = new JLabel(key.substring(0, 1).toUpperCase() + key.substring(1));
//			
//			label.setToolTipText("int");
//			
//			//Caso seja uma chave estrangeira adiciona a mensagem na label
//				label.setToolTipText(label.getToolTipText() + "- FOREIGN KEY: Class:" + value.keySet() 
//						+ " Column: " + value.values());
//			
//			
//			textField.setBounds(250, yPosition, 100, 30);
//			label.setBounds(100, yPosition, 120, 30);
//
//			this.textFields.put(key, textField);
//
//			this.add(textField);
//			this.add(label);
//			
//			if ((operation != CrudOperation.Create && operation != CrudOperation.Update) && key != "id") {
//				textField.hide();
//			}
//			yPosition = yPosition + 50;
//		}
		
		mainButton.setBounds(250, yPosition, 100, 30);
		this.add(mainButton);
		this.mainButton.addActionListener(this);
		
		if(operation == CrudOperation.Update) {
			secondaryButton = new JButton("Get");
			secondaryButton.setBounds(100, yPosition, 80, 30);
			this.add(secondaryButton);
			this.secondaryButton.addActionListener(this);
			this.mainButton.setEnabled(false);
		}
		
	}

	/**
	 * Método que trata quando uma ação é executada
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.mainButton) {
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
		} else if (e.getSource() == this.secondaryButton) {
			if (this.read()) {
				this.mainButton.setEnabled(true);
				this.secondaryButton.setEnabled(false);
			}
		}
	}

	public void create() {
		HashMap<String, Object> properties = new HashMap<>();

		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue().getText();
			

			//Usuario n�o precisa preencher o ID, se ele nao preencher o banco autoincrementa sozinho
			if (key == "id") {
				if (value.equals(""))
					value = 0;
			}

			// Adiciona aspas simples nas STRINGS (VARCHAR), pois o banco n�o aceita sem
			// -- famosa gambeta
			if(object.convertToDict().get(key).getClass().getSimpleName().equals("String"))
				value = "'" + value + "'";

			properties.put(key, value);
		}

		try {
			object.setProperties(properties);
		} catch (NumberFormatException e) {
			System.out.println(e);
			showError("Algum dos campos foi preenchido erroneamente! ");
			return;
		}

		try {

			connection.createObject(object);
			showMessage(object.getClass().getSimpleName() + " criado com sucesso");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showError("Nao foi possivel criar o objeto " + object.getClass().getSimpleName() + "\n" + e.getMessage());

		}

	}

	public boolean read() {
		try {
			String id = "";
			for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
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
			for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
				String objectProperty = objectProperties.get(entry.getKey()).toString();
				JTextField textField = entry.getValue();
				textField.setText(objectProperty);
				textField.show();
			}
		} catch (NullPointerException e) {
			showMessage("Objeto nao encontrado");
			return false;
		} catch (Exception e) {
			showMessage(e.toString());
			return false;
		}
		return true;
	}

	public void update() {
		String id = "";
		HashMap<String, Object> properties = new HashMap<>();
		properties = new HashMap<>();
		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue().getText();

			if (key == "id")
				id = value.toString();		
			
			// Adiciona aspas simples nas STRINGS (VARCHAR), pois o banco n�o aceita sem
			// -- famosa gambeta
			if(object.convertToDict().get(key).getClass().getSimpleName().equals("String"))
				value = "'" + value + "'";

			properties.put(key, value);
		}
		object.setProperties(properties);
		if (connection.updateObject(object, Integer.parseInt(id))) {
			showMessage(object.getClass().getSimpleName() + " alterado com sucesso");
		} else {
			showMessage("Não foi possível alterar o objeto " + object.getClass().getSimpleName());
		}
	}

	public void delete() {
		String id = "";
		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
			String key = entry.getKey();
			if (key == "id") {
				JTextField idTextField = entry.getValue();
				id = idTextField.getText();
				object.setId(Integer.parseInt(id));
				if (connection.deleteObject(object)) {
					showMessage(object.getClass().getSimpleName() + " removido com sucesso");
				} else {
					showMessage("Não foi remover o objeto " + object.getClass().getSimpleName());
				}
			}
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERRO", JOptionPane.ERROR_MESSAGE);
	}
}
