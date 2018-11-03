package frameworkUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import framework.TableObject;

import javax.swing.JComboBox;


public class MainJPanel  extends JPanel implements ActionListener {
  /**
   * Componente de label
   */
  private JComboBox<String> combobox;
  private JButton createButton;
  private JButton readButton;
  private JButton updateButton;
  private JButton deleteButton;
  private List<TableObject> tables;
  
  public MainJPanel(List<TableObject> tables)  {
    //Instancia um novo label e um novo botão
    createButton = new JButton("Create");
    readButton = new JButton("Read");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    combobox = new JComboBox<>();
    this.tables = tables;
    
    //seta os limites dos componentes
    
    for (TableObject table : this.tables) {
    		combobox.addItem(table.getTableName());
    }
    
    //Adiciona componentes a este Panel
    this.add(this.combobox);
    this.add(this.createButton);
    this.add(this.readButton);
    this.add(this.updateButton);
    this.add(this.deleteButton);
    
    //adiciona ao um botão um "escutador", responsável por tratar seus cliques.
    this.createButton.addActionListener(this);
    this.readButton.addActionListener(this);
    this.updateButton.addActionListener(this);
    this.deleteButton.addActionListener(this);
  }

  /**
   * Método que trata quando uma ação é executada 
   */
  public void actionPerformed(ActionEvent e)  {
	  //Pega o objeto selecionado no combobox
	  String selectedTableName = (String) combobox.getSelectedItem();
	  TableObject selectedObject = null;
	  
	  for (TableObject table : this.tables) {
  		if(selectedTableName.equals(table.getTableName())) {
  			selectedObject = table;
  			break;
  		}
	  }
	  
    //Verifica qual botão foi acionado
    if (e.getSource()==this.createButton) {
    		new CrudOperationJFrame(CrudOperation.Create, selectedObject);
    } else if (e.getSource()==this.readButton) {
    		new CrudOperationJFrame(CrudOperation.Read, selectedObject);
    } else if (e.getSource()==this.updateButton) {
    		new CrudOperationJFrame(CrudOperation.Update, selectedObject);
    } else if (e.getSource()==this.deleteButton){
    		new CrudOperationJFrame(CrudOperation.Delete, selectedObject);
    }
  }

}
