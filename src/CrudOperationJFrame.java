import javax.swing.*;
import java.awt.event.*;



public class CrudOperationJFrame extends JFrame {

  /**
   * Cosntrutor que isntancia um JPanel e o adiciona a este JFrame.
   */
  public CrudOperationJFrame(CrudOperation operation, GUObject object) {

    this.setTitle(object.getTableName());

    this.setSize(600, 800);
    
    //Adiciona a capacidade de fechar a janela
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    //Instancia um novo JPanel
    CrudOperationJPanel panel = new CrudOperationJPanel(object, operation);
    panel.setLayout(null);
    
    //adicona o JPanel a este JFrame
    this.getContentPane().add(panel); 
    
    //manda mostrar o JFrame
    this.show(); 
  }
}
