package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Timer implements Runnable {

    private JPanel painelTimer;
    private JLabel textTimer;
    private Thread th;
    private Font fonte7;

    public Timer(int timerSizeX, int timerSizeY,int fontSize){
        painelTimer = new JPanel(new FlowLayout());
        textTimer = new JLabel();
        th = null;
        fonte7 = new Font("DS-DIGITAL",Font.BOLD,fontSize);

        set_painelTimer(timerSizeX,timerSizeY);
    }

    public void set_Timer(){
        textTimer.setFont(fonte7);
        textTimer.setText("0");
        textTimer.setForeground(Color.RED);
    }

    public void set_button(){
        timer_start();
        timer_reset();
    }

    public void set_painelTimer(int x, int y){

        painelTimer.setPreferredSize(new Dimension(x,y));
        painelTimer.setBorder(new EmptyBorder(3,3,3,3));
        painelTimer.setBackground(Color.BLACK);
        painelTimer.add(textTimer);
        set_button();
        set_Timer();
    }

    public void timer_reset(){
        textTimer.setText("0");
        th=null;
    }

    public void timer_stop(){
        th=null;
    }

    public void timer_start(){
        if(th==null){
            th = new Thread(Timer.this::run);
            th.start();
        }
    }

    public JPanel get_painelTimer(){
        return painelTimer;
    }

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

}

