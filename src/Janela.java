import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame implements Runnable{

    JFrame janela = new JFrame("teste - StopWatch");
    JPanel painel = new JPanel(new FlowLayout());
    JTextField textTimer = new JTextField(10);
    JButton start = new JButton("START");
    JButton end = new JButton("END");
    JButton reset = new JButton("RESET");
    Thread th = null;

    public Janela(){

        janela.setSize(600,250);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel.setSize(300,300);

        textTimer.setText("0");

        start.setPreferredSize(new Dimension(100,100));
        end.setPreferredSize(new Dimension(100,100));
        reset.setPreferredSize(new Dimension(100,100));

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(th==null){
                    th = new Thread(Janela.this::run);
                    th.start();
                }
            }
        });

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(th!=null){
                    th = null;
                }
            }
        });


        painel.add(textTimer);
        painel.add(start);
        painel.add(end);
        painel.add(reset);

        janela.add(painel);

        janela.setVisible(true);

    }

    @Override
    public void run() {
        int i;

        while(th!= null){
            i = Integer.parseInt(textTimer.getText());

            try{
                th.sleep(1000);
                if(th==null) break;
            }
            catch(InterruptedException e){
                break;
            }
            textTimer.setText(Integer.toString(i+1));
        }
    }

    public static void main(String[] args) {
        new Janela();
    }
}
