/**
 * 
 */
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/**
 * 
 */
public class Player extends Entity {

	GamePanel gamePanel;
	KeyHandler keyHandler;

	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		xPos = 355;
		yPos = 140;
		entitySpeed = 3;
		direction = "down";
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true
				|| keyHandler.rightPressed == true) {

			// Vertical movement: up vs. down
			if (keyHandler.upPressed && !keyHandler.downPressed) {
				direction = "up";
				yPos -= entitySpeed;
			} else if (keyHandler.downPressed && !keyHandler.upPressed) {
				direction = "down";
				yPos += entitySpeed;
			}

			// Horizontal movement: left vs. right
			if (keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = "left";
				xPos -= entitySpeed;
			} else if (keyHandler.rightPressed && !keyHandler.leftPressed) {
				direction = "right";
				xPos += entitySpeed;
			}

			spriteCounter++;
			if (spriteCounter > 20) {
				if (spriteNumber == 1) {
					spriteNumber = 2;
				} else if (spriteNumber == 2) {
					spriteNumber = 1;
				}
				spriteCounter = 0;
			}
		}

	}

	public void draw(Graphics2D g2D) {
//		g2D.setColor(Color.white);
//		g2D.fillRect(xPos, yPos, gamePanel.tileSize, gamePanel.tileSize);

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNumber == 1) {
				image = up1;
			}
			if (spriteNumber == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNumber == 1) {
				image = down1;
			}
			if (spriteNumber == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNumber == 1) {
				image = left1;
			}
			if (spriteNumber == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNumber == 1) {
				image = right1;
			}
			if (spriteNumber == 2) {
				image = right2;
			}
			break;
		}

		g2D.drawImage(image, xPos, yPos, gamePanel.tileSize, gamePanel.tileSize, null);
	}
}
