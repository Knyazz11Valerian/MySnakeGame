package Snake.Objects;

import Snake.SnakeMain;

public class Apple {

    public int posX;
    public int posY;

    public Apple(int x, int y){
        posX=x;
        posY=y;
    }

    public void setRandomPosition(){
        posX=(int)(Math.random()*SnakeMain.WIDTH-1);
        posY=(int)(Math.random()*SnakeMain.HEIGHT-1);
    }

}
