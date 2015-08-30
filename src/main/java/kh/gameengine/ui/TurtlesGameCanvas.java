package kh.gameengine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Main game canvas and game animation logic.
 * 
 * @author kevinhooke
 */
public class TurtlesGameCanvas extends GameCanvas
{

    private static final int PLAYER_LEFT_BANK_XPOS = 105;
    private static final int PLAYER_LEFT_BANK_YPOS = 118;
    private FishSprite fish1;
    private FishSprite fish2;
    private FishSprite fish3;
    private FishSprite fish4;
    private Random randomNumber = new Random();

    private static final int LEFT_BANK = 0;
    private static final int RIGHT_BANK = 5;

    /** Creates a new instance of MainWindow */
    public TurtlesGameCanvas()
    {
        super();
    }

    protected RiverCrossingPlayerSprite getPlayer()
    {
    	return (RiverCrossingPlayerSprite)super.getPlayer();
    }
    
    @Override
    public void checkPlayerPositionOutOfBoundsConditions()
    {
    	this.checkIfPlayerInRiver();
    }
    
    /**
     * Checks if player has dropped into river.
     *
     */
    private void checkIfPlayerInRiver()
    {
        //handle dropping in river
        Sprite sprite = (Sprite) spritesArray[1][getPlayer().getCurrentPosition()];
        TurtleSprite turtle = null;

        if (sprite instanceof TurtleSprite)
        {
            turtle = (TurtleSprite) sprite;
            if (turtle.isDiving() && !getPlayer().isPlayerInRiver())
            {
                System.out.println("debug: life lost");
                getPlayer().setPlayerInRiver(true);
            }

            if (getPlayer().isPlayerInRiver())
            {
            	getPlayer().checkPlayerInRiverAnimationFrames();
            }

            if (getPlayer().isInRiverAnimationComplete())
            {
                //lost a life
                this.lives--;
                
                //restart on left bank
                setPlayerOnLeftBank();
                getPlayer().setCarryingLuggage(true);
                getPlayer().setInRiverAnimationComplete(false);

            }
        }

    }

    /**
     * Drop luggage off on other bank.
     */
    private void dropLuggage()
    {
    	getPlayer().setCarryingLuggage(false);
    	getPlayer().setDisplayAlternativeImage(false);
    }

    /**
     * Pick up new luggage from left bank.
     */
    private void getLuggage()
    {
    	getPlayer().setCarryingLuggage(true);
    	getPlayer().setDisplayAlternativeImage(true);

    }

    /**
     * Increments player score.
     */
    @Override
    protected void incrementScore()
    {
        this.score += 1;
    }

    /**
     * Initialize game and initial sprite positions.
     */
    @Override
    public void init()
    {
        //valid sprite locations is a 6 columns by 5 rows game grid
        this.spritesArray = new Object[5][6];
        try
        {
            //set up starting positions
            setPlayer( new RiverCrossingPlayerSprite("char1.gif", "char_right_with_luggage.gif") );
            setPlayerOnLeftBank();
            spritesArray[0][0] = getPlayer();

            Sprite leftBank = new TestSolidSprite("bank_left.gif");
            leftBank.setX(100);
            leftBank.setY(150);

            Sprite rightBank = new TestSolidSprite("bank_right.gif");
            rightBank.setX(350);
            rightBank.setY(150);

            Sprite leftWall = new TestSolidSprite("bank_wall.gif");
            leftWall.setX(100);
            leftWall.setY(170);

            Sprite rightWall = new TestSolidSprite("bank_wall_right.gif");
            rightWall.setX(345);
            rightWall.setY(169);

            Sprite riverfloor = new TestSolidSprite("river_floor.gif");
            riverfloor.setX(139);
            riverfloor.setY(339);

            Sprite turtle1 = new TurtleSprite("turtle1.gif");
            turtle1.setX(150);
            turtle1.setY(150);

            Sprite turtle2 = new TurtleSprite("turtle1.gif");
            turtle2.setX(200);
            turtle2.setY(150);

            Sprite turtle3 = new TurtleSprite("turtle1.gif");
            turtle3.setX(250);
            turtle3.setY(150);

            Sprite turtle4 = new TurtleSprite("turtle1.gif");
            turtle4.setX(300);
            turtle4.setY(150);

            spritesArray[1][0] = leftBank;
            spritesArray[1][1] = turtle1;
            spritesArray[1][2] = turtle2;
            spritesArray[1][3] = turtle3;
            spritesArray[1][4] = turtle4;
            spritesArray[1][5] = rightBank;
            spritesArray[2][0] = leftWall;
            spritesArray[2][1] = riverfloor;
            spritesArray[2][5] = rightWall;

            fish1 = new FishSprite("fish.gif");
            fish2 = new FishSprite("fish.gif");
            fish3 = new FishSprite("fish.gif");
            fish4 = new FishSprite("fish.gif");

            spritesArray[4][1] = fish1;
            fish1.setX(150);
            fish1.setY(FishSprite.BOTTOM_RIVER_POS);
            spritesArray[4][2] = fish2;
            fish2.setX(200);
            fish2.setY(FishSprite.BOTTOM_RIVER_POS);

            spritesArray[4][3] = fish3;
            fish3.setX(250);
            fish3.setY(FishSprite.BOTTOM_RIVER_POS);

            spritesArray[4][4] = fish4;
            fish4.setX(300);
            fish4.setY(FishSprite.BOTTOM_RIVER_POS);


        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void calculateSpritePositions()
    {
    	this.calculateFishPositions();
    
    	this.calculateTurtlePositions();
    }
    
    @Override
    protected void showIntroScreen(Graphics2D g)
    {
    	g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
    	g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 26));
        g.drawString("Turtles!", 210, 50);
        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g.drawString("Carry your luggage from one side of the river to the other by", 110, 70);
        g.drawString("jumping on the turtles backs. Avoid falling in the water when", 110, 90);
        g.drawString("they dive for fish!", 110, 110);
        g.drawString("Use left and right keys to jump.", 110, 130);
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString("Click to start!", 200, 170);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString("� 2012 Kevin Hooke", 110, 240);
        
    }
    
    /**
     * Player has lost all lives - show end of game message.
     * @param g
     */
    @Override
    protected void showEndOfGameScreen(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 26));
        g.drawString("Game Over!", 185, 130);
        g.drawString("Click to play again!", 135, 160);
        
    }

    @Override
    protected void calculatePlayerPosition(PlayerSprite player)
    {
    	RiverCrossingPlayerSprite riverCrosser = (RiverCrossingPlayerSprite)player;
        if (riverCrosser.isPlayerInRiver())
        {
        	riverCrosser.dropIntoRiver();
        }

        if (riverCrosser.isPlayerMovingLeft() && ableToMoveLeft(riverCrosser) && !riverCrosser.isPlayerInRiver())
        {
        	riverCrosser.moveLeft();
        }
        if (riverCrosser.isPlayerMovingRight() && ableToMoveRight(riverCrosser) && !riverCrosser.isPlayerInRiver())
        {
        	riverCrosser.moveRight();
        }

        if (isPlayerOnLeftBank() && !isPlayerCarryingLuggage())
        {
            //TODO: switch player sprite when carrying luggage
            getLuggage();
        }

        if (isPlayerOnRightBank() && isPlayerCarryingLuggage())
        {
            dropLuggage();
            incrementScore();
        }
    }

    /**
     * Is player able to move to the left?
     */
    @Override
    protected boolean ableToMoveLeft(Sprite theSpriteToMove)
    {
    	//if player is not on left bank then player can move left
        return !this.isPlayerOnLeftBank();
    }

    @Override
    protected boolean ableToMoveRight(Sprite theSpriteToMove)
    {
    	//if player is not on the right bank, then player can move right
        return !this.isPlayerOnRightBank();

    }


    /**
     * Calculates fish visible and the frequency based on current score - the higher the current score results
     * in a higher probability that a fish will appear and swim up to be eaten.
     */
    private void calculateFishPositions()
    {
        int randomFish = 0;

        moveCurrentlyVisibleFish();

        if (score < 10)
        {
            randomFish = randomNumber.nextInt(40);
        } else if (score >= 10 || score < 20)
        {
            randomFish = randomNumber.nextInt(20);

        } else if (score >= 20 || score < 30)
        {
            randomFish = randomNumber.nextInt(10);

        } else if (score >= 30 || score < 40)
        {
            randomFish = randomNumber.nextInt(8);

        } else
        {
            randomFish = randomNumber.nextInt(3);

        }
        if (randomFish == 1)
        {
            //pick one of the 4 fish
            int fish = randomNumber.nextInt(4) + 1;
            FishSprite fishSprite = ((FishSprite) spritesArray[4][fish]);
            if (fishSprite != null && !fishSprite.isVisible())
            {
                fishSprite.setVisible(true);
            }
        }
    }

    /**
     * Is the player currently carrying luggage?
     * @return
     */
    private boolean isPlayerCarryingLuggage()
    {
        return getPlayer().isCarryingLuggage();
    }

    private boolean isPlayerOnLeftBank()
    {
        if (getPlayer().getCurrentPosition() == LEFT_BANK)
        {
            return true;
        } else
        {
            return false;
        }
    }

    private boolean isPlayerOnRightBank()
    {
        if (getPlayer().getCurrentPosition() == RIGHT_BANK)
        {
            return true;
        } else
        {
            return false;
        }
    }

    private void moveCurrentlyVisibleFish()
    {
        for (int i = 0; i < 4; i++)
        {
            FishSprite fish = (FishSprite) spritesArray[4][i + 1];
            if (fish.isVisible())
            {
                fish.moveUp();
            }
        }
    }

    private void calculateTurtlePositions()
    {


        for (int i = 0; i < 4; i++)
        {
            TurtleSprite turtle = (TurtleSprite) spritesArray[1][i + 1];

            turtle.checkDivingAnimation();

            FishSprite fish = (FishSprite) spritesArray[4][i + 1];

            if (fish.getPosition() == FishSprite.TURTLE_FOOD)
            {
                fish.setVisible(false);
                //TODO: change this from bottom_river_pos to lowest - check this works

                fish.setPosition(FishSprite.LOWEST);
                turtle.moveDown();
            }
        }

    }

    private void setPlayerOnLeftBank()
    {
    	getPlayer().setX(PLAYER_LEFT_BANK_XPOS);
    	getPlayer().setY(PLAYER_LEFT_BANK_YPOS);
    	getPlayer().setCurrentPosition(LEFT_BANK);
    }

    @Override
    protected void showLives(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Lives: " + Integer.toString(this.lives), 50, 50); //

    }

    @Override
    protected void showScore(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + Integer.toString(this.score), 300, 50); //
    }
}
