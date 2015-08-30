package kh.gameengine.ui;

/**
 *
 * @author kevinhooke
 */
public class TurtleSprite extends Sprite {


    private int frameCount = 0;
    private boolean diving;


    /** Creates a new instance of TurtleSprite */
    public TurtleSprite(String fileName) throws Exception
    {
        super(fileName, null);
        setVisible(true);
    }

    /**
     * Surfaces the turtle.
     */
    public void moveUp() {
    }

    /**
     * 'Dives' the turtle.
     */
    public void moveDown() {
        setDiving(true);
        setY(180);
    }

    public void moveLeft() {
    }

    public void moveRight() {
    }

    public boolean isDiving() {
        return diving;
    }

    public void setDiving(boolean diving) {
        this.diving = diving;
    }

    void checkDivingAnimation() {
        if(isDiving())
        {
            if(frameCount == 20)
            {
                frameCount = 0;
                diving = false;
                setY(150);
            }
            else
            {
                frameCount++;
            }
        }
    }
}
