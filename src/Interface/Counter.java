
package Interface;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Counter {
    private JPanel painelCounter;
    private JLabel counter;
    private int n_bombas;
    private Font fonte7;

    public void set_counter(int k, int x, int y){
        counter.setFont(fonte7);
        counter.setText(Integer.toString(k));
        counter.setForeground(Color.RED);
        set_painelCounter(x,y);
        painelCounter.add(counter);
    }

    public Counter(int n_bombas, int counterSizeX, int counterSizeY, int counterSizeFont){
        painelCounter = new JPanel(new FlowLayout());
        counter = new JLabel();
        this.n_bombas = n_bombas;
        fonte7 = new Font("DS-DIGITAL",Font.BOLD,counterSizeFont);
        
        set_counter(n_bombas,counterSizeX,counterSizeY);
    }

    public void set_painelCounter(int x, int y){
        painelCounter.setPreferredSize(new Dimension(x,y));
        painelCounter.setBorder(new EmptyBorder(3,3,3,3));
        painelCounter.setBackground(Color.BLACK);
    }


    public void att_counter(int k){
        n_bombas+=k;
        counter.setText(Integer.toString(n_bombas));
    }

    public JPanel get_painelCounter()
    {
        return painelCounter;
    }
}

