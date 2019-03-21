package Interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.SystemColor.menu;

public class Interface extends JFrame{

    private JFrame janela;
    private Field field;
    private JPanel painel,painel_game;
    private Timer timer;
    private Counter counter;
    private int n_bomba;

    private JMenu newAction,menu;
    private JMenuItem babyAction,exitAction,easyAction,mediumAction,hardAction,infernalAction;
    private JMenuBar menubar;

    public void create_menu(){
        menubar = new JMenuBar();
        setJMenuBar(menubar);

        menu = new JMenu("Menu");
        newAction = new JMenu("New");

        menubar.add(menu);

        babyAction = new JMenuItem("Baby");
        easyAction = new JMenuItem("Easy");
        exitAction = new JMenuItem("Exit");
        mediumAction = new JMenuItem("Medium");
        hardAction = new JMenuItem("Hard");
        infernalAction = new JMenuItem("Infernal");
        set_babyAction();
        set_easyAction();
        set_mediumAction();
        set_hardAction();
        set_infernalAction();
        set_exitAction();
        set_Menu();
    }

    public void set_Menu(){
        set_newAction();
        menu.add(newAction);
        menu.addSeparator();
        menu.add(exitAction);
    }

    public void set_newAction(){
        newAction.add(babyAction);
        newAction.add(easyAction);
        newAction.add(mediumAction);
        newAction.add(hardAction);
        newAction.add(infernalAction);
    }

    public void set_exitAction(){
        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void set_icon(){
        Toolkit tk = Toolkit.getDefaultToolkit();
         //Image img = tk.getImage( "C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/bomberman2.png");
         Image img = tk.getImage( "C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/maia.png");
        janela.setIconImage(img);
    }

    public void set_babyAction(){
        babyAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = null;
                counter = null;
                timer = null;
                painel_game.removeAll();
                janela.setResizable(true);
                create_game(painel_game,1,1,1,30,30);
                janela.pack();
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);
            }
        });
    }

    public void set_easyAction(){
        easyAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = null;
                counter = null;
                timer = null;
                painel_game.removeAll();
                janela.setResizable(true);
                create_game(painel_game,10,15,15,30,30);
                janela.pack();
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);
            }
        });
    }

    public void set_mediumAction(){
        mediumAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = null;
                counter = null;
                timer = null;
                painel_game.removeAll();
                create_game(painel_game,15,25,56,30,30);
                janela.pack();
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);
            }
        });
    }

    public void set_hardAction(){
        hardAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = null;
                counter = null;
                timer = null;
                painel_game.removeAll();
                create_game(painel_game,20,35,140,30,30);
                janela.pack();
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);
            }
        });
    }

    public void set_infernalAction(){
        infernalAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = null;
                counter = null;
                timer = null;
                painel_game.removeAll();
                create_game(painel_game,40,85,1500,16,16);
                janela.pack();
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);
            }
        });
    }

    public void create_game (JPanel painel_game,int n_linha,int n_coluna,int n_bomba,int fieldSizeX,int fieldSizeY){

        counter = new Counter(n_bomba,60,50,40);
        timer = new Timer(60,50,40);
        field = new Field(n_linha,n_coluna,n_bomba,fieldSizeX,fieldSizeY, timer, counter);

        JPanel aux = new JPanel();
        aux.add(field.get_panelField());
        aux.setBackground(Color.BLACK);

        JPanel aux2 = new JPanel();

        aux2.add(counter.get_painelCounter());
        aux2.add(timer.get_painelTimer());

        painel_game.add(BorderLayout.CENTER,aux);
        painel_game.add(BorderLayout.SOUTH,aux2);

        painel.add(BorderLayout.NORTH, menubar);
        painel.add(BorderLayout.CENTER,painel_game);
    }

    public Interface(){
        janela = new JFrame("teste - StopWatch");
        painel = new JPanel(new BorderLayout());
        set_icon();
        create_menu();
        painel_game = new JPanel(new BorderLayout());
        painel.add(BorderLayout.CENTER,painel_game);

        create_game(painel_game,9,12,1,30,30);

        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setVisible(true);
    }


    public static void main(String[] args) {
        new Interface();
    }
}

