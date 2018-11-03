package frameworkUI;
import framework.TableObject;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrudOperationJPanel extends JPanel implements ActionListener {
	 
	Map<String, JTextField> textFields;
	JButton mainButton;
	CrudOperation operation;
	
	  public CrudOperationJPanel(TableObject object, CrudOperation operation)  {
		  this.textFields = new HashMap<>();
		  this.mainButton = new JButton(operation.getName());
		  this.operation = operation;
		  
		  Map<String, Object> dicObject = object.convertToDict();
		  
		  int yPosition = 10;
		  
		  for ( Map.Entry<String, Object> entry : dicObject.entrySet() ) {
			    String key = entry.getKey();
//			    Object value = entry.getValue();
			    JTextField textField = new JTextField(key);
			    
			    textField.setBounds(200, yPosition, 100, 30);
			    
			    this.textFields.put(key, textField);
			    this.add(textField);
			    if((operation != CrudOperation.Create || operation != CrudOperation.Update) && key != "id") {
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
	    		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
	    			String key = entry.getKey();
		    	}
	    		break;
	    	case Read:
	    		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
	    			String key = entry.getKey();
	    			if (key == "id") {
	    				JTextField idTextField = entry.getValue();
	    				String id = idTextField.getText();
	    				System.out.println(id);
	    			}
		    	}
	    		//	    		busca(id);
	    		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
	    			JTextField textField = entry.getValue();
    				textField.show();
	    		}
	    		break;
	    	case Update:
	    		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
	    			String key = entry.getKey();
	    			
		    	}
	    		break;
	    	case Delete:
	    		String id;
	    		for ( Map.Entry<String, JTextField> entry : this.textFields.entrySet() ) {
	    			String key = entry.getKey();
	    			if (key == "id") {
	    				JTextField idTextField = entry.getValue();
	    				id = idTextField.getText();
	    				System.out.println(id);
	    			}
		    	}
//	    		delete(id);
	    		break;
	    	}
	    	
	    	
	    	
	    }
	  }
}
