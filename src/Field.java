import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Field {
    
    
    public void create() {
        ActionListener bomba = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton botaoClicado = (JButton)e.getSource();

                botaoClicado.setIcon(new ImageIcon("C:/Users/a11711BCC011/Desktop/bomba.png"));
            }
        };

        JFrame frame  = new JFrame("My frame");
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(10,10));

        frame.setSize(240,300);


        for(int i=0; i<10*10; i++) {
            JButton button = new JButton();//new JButton(new ImageIcon("C:/Users/a11711BCC011/Desktop/bomba.png"));
            button.setBackground(Color.BLACK);
            button.addActionListener(bomba);
            panel.add(button);
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}
