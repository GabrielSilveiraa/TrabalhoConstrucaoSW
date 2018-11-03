import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;


public class MainJPanel  extends JPanel implements ActionListener {
  /**
   * Componente de label
   */
  private JComboBox<GUObject> combobox;
  private JButton createButton;
  private JButton readButton;
  private JButton updateButton;
  private JButton deleteButton;

  public MainJPanel()  {
    //Instancia um novo label e um novo botão
    createButton = new JButton("Create");
    readButton = new JButton("Read");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    combobox = new JComboBox<>();
    
    //seta os limites dos componentes
    
    Pessoa bruno = new Pessoa();
    bruno.cpf = "11122233345";
    bruno.nome = "Bruno Cardoso";
    bruno.setId(1);
    bruno.setTableName("Pessoa");
    
    combobox.addItem(bruno);
    
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
	  GUObject selectedObject = (GUObject) combobox.getSelectedItem();
	  
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
