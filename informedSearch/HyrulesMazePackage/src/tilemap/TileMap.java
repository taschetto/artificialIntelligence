package tilemap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileMap {
	
	//player position
	public int player_x, player_y;
	
	// position
	private double x;
	private double y;
	
	
	private double tween;
	
	// map
	public int[][] map;
	private int tileSize;
	public int numRows;
	public int numCols;
	private int width;
	private int height;
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[] tiles;
	
	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize;
		numColsToDraw = GamePanel.WIDTH / tileSize;
		tween = 0.07;
	}
	
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	
	public void loadTiles(String s) {
		
		try {

			tileset = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[numTilesAcross];
			
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(
							col * tileSize,
							0,
							tileSize,
							tileSize
						);
				
				tiles[col] = new Tile(subimage, col == 1 ? Tile.BLOCKED : Tile.NORMAL);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			player_x = Integer.parseInt(br.readLine());
			player_y = Integer.parseInt(br.readLine());
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			String delims = " ";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				
				
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
					//System.out.println(row + " " + col + " - " + tokens[col]);
				}
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
		
	}
	
	public int getTileSize() { return tileSize; }
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		return rc == 1 ? Tile.BLOCKED : Tile.NORMAL;
	}
	
	public void setPosition(double x, double y) {
		
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	
	public void draw(Graphics2D g) {
		
		for(int row = 0; row < numRowsToDraw; row++){
			
			for(int col = 0; col< numColsToDraw; col++){
				int tileToDraw = map[row][col];
				
				g.drawImage(
						tiles[tileToDraw].getImage(),
						(int)x + col * tileSize,
						(int)y + row * tileSize,
						null
					);
			}
		}	
	}
	
	
}
