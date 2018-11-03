import javax.swing.*;
import java.awt.event.*;


public class MainJFrame extends JFrame {

  /**
   * Cosntrutor que isntancia um JPanel e o adiciona a este JFrame.
   */
  public MainJFrame() {

    this.setTitle("CRUD dos guri");

    this.setSize(600, 200);
    
    //Adiciona a capacidade de fechar a janela
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    //Instancia um novo JPanel
    MainJPanel panel = new MainJPanel(); 
    
    //adicona o JPanel a este JFrame
    this.getContentPane().add( panel ); 
    
    //manda mostrar o JFrame
    this.show(); 
  }
}
