package Interface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//import java.util.Timer;

public class Field {
    private JPanel painelField;
    private char[][] field;
    private boolean[][] isflag,visited;
    private int n_bomba,n_coluna,n_linha,fieldSizeX,fieldSizeY, firstX, firstY, flaged, n_flag;
    private Timer timer;
    private Counter counter;

    public Field(int n_linha, int n_coluna, int n_bomba,  int fieldSizeX, int fieldSizeY, Timer timer, Counter counter) {
        timer.timer_reset();
        timer.timer_start();


        this.n_linha = n_linha;
        this.n_coluna = n_coluna;        this.n_bomba = n_bomba;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.timer = timer;
        this.counter = counter;
        n_flag=0;

        painelField = new JPanel();
        field = new char[n_linha][n_coluna];
        visited = new boolean[n_linha][n_coluna];
        isflag =  new boolean[n_linha][n_coluna];
        painelField.setLayout(new GridLayout(n_linha,n_coluna));
        init();
        firstX = firstY = (n_linha == n_coluna && n_linha == 1)?0:-1;
        if(firstX == 0) field[0][0]= 'b';
        set_button();
    }

    public boolean valid(int i, int j) {
        return !(i<0 || j<0 || i>=n_linha || j>=n_coluna);
    }

    public void create_bomb() {
        for(int k=0; k<n_bomba; k++) {
            int i =  (new Random()).nextInt(n_linha);
            int j =  (new Random()).nextInt(n_coluna);

            if(field[i][j] == 'b' || (i == firstX && j == firstY)){
                k--;
                continue;
            }

            field[i][j] = 'b';
        }
    }

    public void set_adjacente() {
        int[] dr = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};

        for(int i=0; i<n_linha; i++) {
            for(int j=0; j<n_coluna; j++) {
                if(field[i][j] == 'b') continue;
                for(int k=0; k<8; k++) {
                    if(!valid(i+dr[k], j+dc[k])) continue;

                    if(field[i+dr[k]][j+dc[k]] == 'b') {
                        field[i][j]++;
                    }
                }
            }
        }
    }

    public void fill(JButton[][] button, int i, int j) {
        if(!valid(i,j)|| isflag[i][j]) return;
        if(visited[i][j] || field[i][j] != '0') return;

        button[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        button[i][j].setBackground(Color.GRAY);

        int[] dr = {-1, 0, 0, 1,-1, -1,  1, 1}; // [4,8] Diagonais
        int[] dc = { 0,-1, 1, 0,-1,  1, -1, 1};

        visited[i][j] = true;

        for(int k=0; k<4; k++) {
            fill(button, i+dr[k], j+dc[k]);
        }

    }

    public void init() {
        for(int i=0; i<n_linha; i++) {
            for(int j=0; j<n_coluna; j++) {
                field[i][j] = '0';
            }
        }
    }

    void set_imagem(JButton button[][], int x, int y) {
        char c = field[x][y];

        if(visited[x][y]) return;
        if(isflag[x][y]) {
            button[x][y].setIcon(new ImageIcon("/C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/flag.png"));
            return;
        }
        if(c == 'B') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/bomba_explode.png"));
        else if(c == 'b') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/bomba.png"));
        else if(c == '0') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/0.png"));
        else if(c == '1') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/1.png"));
        else if(c == '2') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/2.png"));
        else if(c == '3') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/3.png"));
        else if(c == '4') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/4.png"));
        else if(c == '5') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/5.png"));
        else if(c == '6') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/6.png"));
        else if(c == '7') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/7.png"));
        else if(c == '8') button[x][y].setIcon(new ImageIcon("C:/Users/Lucas/Desktop/CampoMinadoFinal/CampoMinado/img/8.png"));
        button[x][y].setDisabledIcon(button[x][y].getIcon());
        button[x][y].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        button[x][y].setEnabled(false);
    }

    public void set_button() {

        JButton[][] button = new JButton[n_linha][n_coluna];
        MouseListener mouse = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int x=0,y=0;
                JButton aux = (JButton)e.getSource();
                for(int i=0;i<n_linha;i++) {
                    for(int j=0;j<n_coluna;j++){
                        if(button[i][j]==aux){
                            x=i;
                            y=j;
                            i=j=n_coluna+n_linha;
                        }
                    }
                }
                if(visited[x][y]) return;
                if(e.getButton()==MouseEvent.BUTTON3){
                    if(isflag[x][y]) {
                       isflag[x][y] = false;
                       button[x][y].setIcon(null);
                       counter.att_counter(1);
                       n_flag--;
                    }
                    else if(n_flag != n_bomba) {
                        n_flag++;
                        isflag[x][y] = true;
                        set_imagem(button,x,y);
                        button[x][y].setDisabledIcon(button[x][y].getIcon());
                        counter.att_counter(-1);
                    }

                    flaged=0;
                    for(int i=0; i<n_linha; i++) {
                        for(int j=0; j<n_coluna; j++) {
                            if(visited[i][j]) flaged++;
                        }
                    }
                    if(flaged == n_linha*n_coluna-n_bomba) {
                        timer.timer_stop();
                        for(int i=0; i<n_linha; i++) {
                            for(int j=0; j<n_coluna; j++) {
                                button[i][j].setEnabled(false);
                                visited[i][j] = true;
                            }
                        }
                    }
                }
                else if(e.getButton()==MouseEvent.BUTTON1){
                        JButton botaoClicado = button[x][y];

                        if(isflag[x][y]) return;
                        if(firstX == -1) {
                            firstX = x;
                            firstY = y;
                            create_bomb();
                            set_adjacente();
                        }
                        if(field[x][y] == 'b') {
                            timer.timer_stop();
                            for(int i=0; i<n_linha; i++) {
                                for(int j=0; j<n_coluna; j++) {

                                    if (field[i][j] == 'b') {
                                        if(i == x && j == y) field[i][j] = 'B';
                                        set_imagem(button, i, j);
                                        button[i][j].setDisabledIcon(button[i][j].getIcon());
                                    }
                                    visited[i][j] = true;
                                    button[i][j].setEnabled(false);
                                    button[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                                }
                            }
                        }

                        else if(field[x][y] == '0'){
                            fill(button, x, y);
                            int[] dr = {-1, -1, -1,  0, 0,  1, 1, 1};
                            int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};

                            for(int i=0; i<n_linha; i++) {
                                for(int j=0; j<n_coluna; j++) {
                                    if(visited[i][j] && field[i][j] == '0'){
                                        for(int k=0; k<8; k++) {
                                            if(valid(i+dr[k], j+dc[k]) && field[i+dr[k]][j+dc[k]] >= '1' && field[i+dr[k]][j+dc[k]] <= '8') {
                                                if(isflag[i+dr[k]][j+dc[k]]) continue;
                                                set_imagem(button, i+dr[k], j+dc[k]);
                                                visited[i+dr[k]][j+dc[k]] = true;
                                            }   
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            set_imagem(button, x, y);
                            visited[x][y] = true;
                        }

                        flaged=0;
                        for(int i=0; i<n_linha; i++) {
                            for(int j=0; j<n_coluna; j++) {
                                if(visited[i][j]) flaged++;
                            }
                        }
                        if(flaged == n_linha*n_coluna-n_bomba) {
                            timer.timer_stop();
                            for(int i=0; i<n_linha; i++) {
                                for(int j=0; j<n_coluna; j++) {
                                    button[i][j].setEnabled(false);
                                    visited[i][j] = true;
                                }
                            }
                        }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
                 
        for(int i=0; i<n_linha; i++) {
            for(int j=0; j<n_coluna; j++) {
                button[i][j] = new JButton();
                button[i][j].setPreferredSize(new Dimension(fieldSizeX,fieldSizeY));
                button[i][j].setBackground(Color.BLACK);
                button[i][j].addMouseListener(mouse);
                painelField.add(button[i][j]);
            }
        }
    }

    public JPanel get_panelField() {
        return painelField;
    }
}
