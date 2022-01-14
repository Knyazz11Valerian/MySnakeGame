package Snake;

import Snake.Objects.Apple;
import Snake.Objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeMain extends JPanel implements ActionListener {

    public static JFrame jFrame;

    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT =20;

    public static int speed=8;
    public static int appleScore=0;

    Timer timer=new Timer(1000/speed,this);

    Snake snake=new Snake(5,6,5,5);

    Apple apple=new Apple((int)(Math.random()*WIDTH-1),(int)(Math.random()*HEIGHT-1));

    public SnakeMain(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics graphics){
        graphics.setColor(Color.GREEN);
        graphics.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);

        for (int x = 0; x < WIDTH*SCALE; x+=SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(x,0,x,HEIGHT*SCALE);
        }

        for (int y = 0; y < HEIGHT*SCALE; y+=SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0,y,WIDTH*SCALE,y);
        }

        for (int i = 0; i < snake.length; i++) {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(snake.snakeX[i]*SCALE+2,
                              snake.snakeY[i]*SCALE+2,
                           SCALE-3, SCALE-3);
            graphics.setColor(Color.GRAY);
            graphics.fillRect(snake.snakeX[0]*SCALE+2,
                    snake.snakeY[0]*SCALE+2,
                    SCALE-3, SCALE-3);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(apple.posX*SCALE +4,
                apple.posY*SCALE+4,SCALE-8, SCALE-8);
    }

    public static void main(String[] args) {
        jFrame=new JFrame("Snake" );
        jFrame.setSize(WIDTH*SCALE+19,HEIGHT*SCALE+48);
        jFrame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeMain());
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
         repaint();

         if (snake.snakeX[0]==apple.posX&&snake.snakeY[0]==apple.posY){
             apple.setRandomPosition();
             snake.length++;
             appleScore++;
             speed++;
         }

        for (int i = 1; i < snake.length; i++) {
            if (snake.snakeX[i]==apple.posX&&snake.snakeY[i]==apple.posY)  apple.setRandomPosition();

            if (snake.snakeX[0]==snake.snakeX[i]&&snake.snakeY[0]==snake.snakeY[i]){
                timer.stop();
                JOptionPane.showMessageDialog(null, "Ай! Как же ты так? Яблок съедено всего "+appleScore+". \nПопробуй ещё раз!");
                jFrame.setVisible(false);
                snake.length=2;
                snake.direction=0;
                appleScore=0;
                speed=8;
                apple.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();

            }
        }
    }

    public class KeyBoard extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent event) {

            int key=event.getKeyCode();

            if (key==KeyEvent.VK_W&&snake.direction!=2) snake.direction=0;
            if (key==KeyEvent.VK_D&&snake.direction!=3) snake.direction=1;
            if (key==KeyEvent.VK_S&&snake.direction!=0) snake.direction=2;
            if (key==KeyEvent.VK_A&&snake.direction!=1) snake.direction=3;


        }
    }

}
