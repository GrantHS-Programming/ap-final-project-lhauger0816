import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;


public class dinasourGame extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 750;
    int boardHeight = 250;

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    //images


    class Block {
        int x;
        int y;
        int width;
        int height;
        Image img;

        Block(int x, int y, int width, int height){
        }


    }

    //dinosour
    int dinosourWidth = 88;
    int dinosourHeight = 94;
    int dinosourX = 50;
    int dinsourY = boardHeight - dinosourHeight;


    Block dinosaur;

    int cactus1width = 34;
    int cactus2width = 69;
    int cactus3width = 102;

    int cactusHeight = 70;
    int cactusX = 700;
    int cactusY = boardHeight - cactusHeight;
    ArrayList<Block> cactusArray;

    //physics
    int velocityX = -12;
    int velocityY = 0;
    int gravity = 1;

    boolean gameOver = false;
    int score = 0;

    Timer gameLoop;
    Timer placeCactusTimer;
    public dinasourGame() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);


        //dinosaur
        dinosaur = new Block(dinosourX,dinsourY,dinosourWidth,dinosourHeight);

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        placeCactusTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeCactus();
            }
        });
        placeCactusTimer.start();
    }

    void placeCactus() {
        if (gameOver){
            return;
        }

        double placeCactusChance = Math.random();
        if(placeCactusChance > .90){
            Block cactus = new Block(cactusX, cactusY, cactus3width, cactusHeight);
            cactusArray.add(cactus);
        }
        else if (placeCactusChance > .70){
            Block cactus = new Block(cactusX, cactusY, cactus2width, cactusHeight);
            cactusArray.add(cactus);
        }
        else if (placeCactusChance > .50) {
            Block cactus = new Block(cactusX, cactusY, cactus1width, cactusHeight);
        }

        if (cactusArray.size() > 10) {
            cactusArray.remove(0);

        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

    }
    public void move(){
        velocityY += gravity;
        dinosaur.y += velocityY;

        if (dinosaur.y > dinosaurY){
            dinosaur.y = dinosaurY;
            velocityY = 0;
        }
    }
}



