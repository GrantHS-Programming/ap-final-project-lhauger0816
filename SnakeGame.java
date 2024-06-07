import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;





public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    int boardwidth;
    int boardheight;
    int tileSize = 25;

    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    Tile food;
    Random random;

    int velocityX;
    int velocityY;
    Timer gameLoop;

    boolean gameOver = false;

    SnakeGame(int boardwidth, int boardheight) {
        this.boardwidth = boardwidth;
        this.boardheight = boardheight;
        setPreferredSize(new Dimension(this.boardwidth, this.boardheight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5,5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10,10);
        random = new Random();
        placeFood();


        gameLoop = new Timer(100,this);
        gameLoop.start();


    }

    public void placeFood() {
        food.x = random.nextInt(boardwidth/tileSize);
        food.y = random.nextInt(boardheight/tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){
        if (collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        for (int i = snakeBody.size()-1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if ( i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
        }
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

         for (int i = 0; i < snakeBody.size(); i++){
             Tile snakePart = snakeBody.get(i);
         }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        for (int i = 0; i < boardwidth/tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, boardheight);
            g.drawLine(0, i*tileSize, boardwidth, i*tileSize);
        }
        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);


        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y *tileSize, tileSize, tileSize);

        for (int i = 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver){
            g.setColor(Color.red);
            g.drawString("Game over: " + String.valueOf(snakeBody.size()), tileSize -16, tileSize);
        }

    }
}
