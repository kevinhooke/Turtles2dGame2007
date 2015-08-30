package kh.gameengine.ui;

/**
 * The fish sprite.
 * 
 * @author kevinhooke
 */
public class FishSprite extends Sprite{
    
    /**
     * Creates a new instance of FishSprite
     */
    public FishSprite(String name) throws Exception {
        super(name, null);
    }

    public void moveUp() {
        frameCount++;
        
        if(frameCount == ANIMATION_INTERVAL_SLOW)
        {
            if(getPosition() < TURTLE_FOOD)
            {
                
                setY(RIVER_DEPTH_POSITION[ getPosition() ]);
                position++;
            }
            //reset frame count
            frameCount=0;
        }
    }

    public void moveDown() {
    }

    public void moveLeft() {
    }

    public void moveRight() {
    }
    
    private int position = 0; //0 = lowest position, 1 = level 1, 2 = level 2,  3= turtle ready to eat
    public static final int LOWEST = 0;
    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;

    public static final int TURTLE_FOOD = 4;
    public static final int BOTTOM_RIVER_POS = 300; 
    public static final int LEVEL1_RIVER_POS = 260;
    public static final int LEVEL2_RIVER_POS = 220;
    public static final int TOP_RIVER_POS = 180;

    public static final int[] RIVER_DEPTH_POSITION = {BOTTOM_RIVER_POS, LEVEL1_RIVER_POS,
    LEVEL2_RIVER_POS, TOP_RIVER_POS  };

    private int frameCount = 0;
    private static final int ANIMATION_INTERVAL_SLOW = 30;
    
    
    public void setVisible(boolean newVisible) {
        this.visible = newVisible;
        
        if(this.visible)
        {
            //reset position to bottom of river
            this.setY( BOTTOM_RIVER_POS );
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
}
