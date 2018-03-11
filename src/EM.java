
import emFrames.frameMain;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Retaliation
 */
public class EM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      
        try {
          boolean b = false;
          for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
              b = true;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
          }
          if(!b){
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          }
        // TODO code application logic here
         frameMain fM = new frameMain();
         fM.setVisible(true);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
           System.out.println("Error at start up: " + ex.getMessage());
           JOptionPane.showMessageDialog(null,"Start up error","Fatal Error",JOptionPane.ERROR_MESSAGE);
        }
  }
}