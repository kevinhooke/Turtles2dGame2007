package kh.gameengine.ui;

/**
 *
 * @author kevinhooke
 */
public class RiverCrossingPlayerSprite extends PlayerSprite{
    

    private boolean carryingLuggage;
    private boolean playerInRiver;
    private boolean inRiverAnimationComplete = false;

    private int currentPosition; // 0 to 5, 0=left bank, 5=right bank

    private int animateFrameX = 0;
    private int animateFrameY = 0;

    private static final int ANIMATE_X_PIXELS = 10;
    private static final int ANIMATE_Y_PIXELS = 10;
    private static final int ANIMATE_TOTAL_FRAMES_HORIZONTAL = 4;
    private static final int ANIMATE_TOTAL_FRAMES_VERTICAL = 5;
    private static final int ANIMATE_VERTICAL_FRAME_MIDPOINT = 2;

    //dropping into river
    private int animateFrameYRiverDrop = 0;
    private int animateFrameRiverDropPause = 0;
    private static final int ANIMATE_TOTAL_FRAMES_DROP_INTO_RIVER = 14;
    private static final int ANIMATE_TOTAL_FRAMES_DROP_INTO_RIVER_PAUSE = 20;
    
    public RiverCrossingPlayerSprite(String fileName1, String fileName2) throws Exception {
        super(fileName1, fileName2);
        this.setVisible(true);
    }
    
    public  void moveUp() {
        y--;
    }
    
    public  void moveDown() {
        y++;
    }
    
    public  void moveLeft() {
        if(animateFrameX < ANIMATE_TOTAL_FRAMES_HORIZONTAL) {
            x-= ANIMATE_X_PIXELS;
            animateFrameX++;
            if(animateFrameX == ANIMATE_TOTAL_FRAMES_HORIZONTAL)
                animateFrameX = 0;
        }
        
        if(animateFrameY < ANIMATE_TOTAL_FRAMES_VERTICAL) {
            
            //check midpoint of animation
            if( animateFrameY < ANIMATE_VERTICAL_FRAME_MIDPOINT ) {
                y-= ANIMATE_Y_PIXELS;
            } else if( animateFrameY == ANIMATE_VERTICAL_FRAME_MIDPOINT ) {
                //no vertical change at midpoint
            } else {
                y+= ANIMATE_Y_PIXELS;
            }
            
            
            animateFrameY++;
            if(animateFrameY == ANIMATE_TOTAL_FRAMES_VERTICAL) {
                animateFrameY = 0;
                setPlayerMovingLeft(false);
                currentPosition--;
            }
        }
        
    }
    
    public  void moveRight() {
        if(animateFrameX < ANIMATE_TOTAL_FRAMES_HORIZONTAL ) //&& animateFrameX % 2 == 0
        {
            x+= ANIMATE_X_PIXELS;
            animateFrameX++;
            if(animateFrameX == ANIMATE_TOTAL_FRAMES_HORIZONTAL)
                animateFrameX = 0;
        }
        
        if(animateFrameY < ANIMATE_TOTAL_FRAMES_VERTICAL ) //&& animateFrameY % 2 == 0
        {
            
            //check midpoint of animation
            if( animateFrameY < ANIMATE_VERTICAL_FRAME_MIDPOINT ) {
                y-= ANIMATE_Y_PIXELS;
            } else if( animateFrameY == ANIMATE_VERTICAL_FRAME_MIDPOINT ) {
                //no vertical change at midpoint
            } else {
                y+= ANIMATE_Y_PIXELS;
            }
            
            
            animateFrameY++;
            if(animateFrameY == ANIMATE_TOTAL_FRAMES_VERTICAL) {
                animateFrameY = 0;
                setPlayerMovingRight(false);
                currentPosition++;
            }
        }
        
    }

    void checkPlayerInRiverAnimationFrames() {
        if(animateFrameRiverDropPause < ANIMATE_TOTAL_FRAMES_DROP_INTO_RIVER_PAUSE)
        {
            animateFrameRiverDropPause++;
        }
        else
        {
            this.inRiverAnimationComplete = true;
            this.playerInRiver = false;
            animateFrameRiverDropPause = 0;
        }

   }

    void dropIntoRiver() {
        if(animateFrameYRiverDrop < ANIMATE_TOTAL_FRAMES_DROP_INTO_RIVER) {
            y+= ANIMATE_Y_PIXELS;
            animateFrameYRiverDrop++;

        }
        else
        {
            animateFrameYRiverDrop = 0;
        }
    }

    
    public boolean isPlayerMovingLeft() {
        return playerMovingLeft;
    }
    
    public void setPlayerMovingLeft(boolean playerMovingLeft) {
        this.playerMovingLeft = playerMovingLeft;
    }
    
    public boolean isPlayerMovingRight() {
        return playerMovingRight;
    }
    
    public boolean isPlayerMovingUp() {
        return playerMovingUp;
    }
    
    public boolean isPlayerMovingDown() {
        return playerMovingDown;
    }
    
    public void setPlayerMovingRight(boolean playerMovingRight) {
        this.playerMovingRight = playerMovingRight;
    }
    
    public void setPlayerMovingUp(boolean playerMovingUp) {
        this.playerMovingUp = playerMovingUp;
    }
    
    public void setPlayerMovingDown(boolean playerMovingDown) {
        this.playerMovingDown = playerMovingDown;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * @return the carryingPackage
     */
    public boolean isCarryingLuggage() {
        return carryingLuggage;
    }

    /**
     * @param carryingPackage the carryingPackage to set
     */
    public void setCarryingLuggage(boolean carryingLuggage) {
        this.carryingLuggage = carryingLuggage;
    }

    /**
     * @return the playerInRiver
     */
    public boolean isPlayerInRiver() {
        return playerInRiver;
    }

    /**
     * @param playerInRiver the playerInRiver to set
     */
    public void setPlayerInRiver(boolean playerInRiver) {
        this.playerInRiver = playerInRiver;
//        if(!this.inRiverAnimationComplete)
//        {
//            this.inRiverAnimationComplete = true;
//        }
    }

    /**
     * @return the currentlyAnimatingDropIntoRiver
     */
    public boolean isCurrentlyAnimatingDropIntoRiver() {
        return inRiverAnimationComplete;
    }

    /**
     * @param inRiverAnimationComplete
     */
    public void setInRiverAnimationComplete(boolean inRiverAnimationComplete) {
        this.inRiverAnimationComplete = inRiverAnimationComplete;
    }

    public boolean isInRiverAnimationComplete() {
        return this.inRiverAnimationComplete;
    }


}
