
package customComponents;
import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Retaliation
 */
public class PanelSpace extends JPanel{
    private final GridBagLayout layGb;
    private final GridBagConstraints cons;
    public PanelSpace(){
        this.setBackground(new Color(125,145,170));
        layGb = new GridBagLayout();
        cons = new GridBagConstraints();
        cons.ipadx=20;
        cons.ipady=10;
        this.setLayout(layGb);
        this.setSize(1000,1000);
        //this.setLayout(new GridLayout(intUnknowns,intUnknowns+1,4,4));
        //this.setPreferredSize(new Dimension(1000,200));
        
    }
    public void setCmp(Component cmp,int x, int y){
        cons.gridx=x;
        cons.gridy=y;
        cons.gridheight=1;
        cons.gridwidth=1;
        cons.anchor=GridBagConstraints.CENTER;
        cons.insets= new Insets(2,2,2,2);
        layGb.setConstraints(cmp,cons);
        this.add(cmp);
    }
    public void setCmp(Component cmp, int x, int y, int gridwidth, int gridheight){
        cons.gridx=x;
        cons.gridy=y;
        cons.gridwidth=gridwidth;
        cons.gridheight=gridheight;
        cons.anchor=GridBagConstraints.CENTER;
        cons.insets = new Insets(2,2,2,2);
        layGb.setConstraints(cmp, cons);
        this.add(cmp);
    }
}
