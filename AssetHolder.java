

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AssetHolder {
	static AssetHolder instance;
	Image[] blackPieces;
	Image[] whitePieces;
	private AssetHolder()
	{
		blackPieces = new Image[6];
		whitePieces = new Image[6];
		readImages();
		
	}
	public static AssetHolder getInstance()
	{
		if(instance == null)
		{
			instance = new AssetHolder();
		}
		return instance;
	}
	public Image getPiece(String strSide,String strKind)
	{
		int side = convertStringSideToInt(strSide);
		int kind = converStringKindToInt(strKind);
		if(side == 0)
		{
			return whitePieces[kind-1];
		}
		else
		{
			return blackPieces[kind-1];
		}
	}
	void readImages()
	{
		try {
			//reading white pieces
			whitePieces[0] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\BeyazSah.png"));
			whitePieces[1] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\BeyazVezir.png"));
			whitePieces[2] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\BeyazFil.png"));
			whitePieces[3] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\BeyazAt.png"));
			whitePieces[4] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\BeyazKale.png"));
			whitePieces[5] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\BeyazPiyon.png"));
			//reading black pieces
			blackPieces[0] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\SiyahSah.png"));
			blackPieces[1] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\SiyahVezir.png"));
			blackPieces[2] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\SiyahFil.png"));
			blackPieces[3] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\SiyahAt.png"));
			blackPieces[4] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\SiyahKale.png"));
			blackPieces[5] = ImageIO.read(new File("C:\\Users\\Sinan\\Desktop\\SantracTas\\SiyahPiyon.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error while reading image files");
		}
	}
	int convertStringSideToInt(String SIDE)
	{
		return (SIDE.equalsIgnoreCase("white")?0:1);
	}
	int converStringKindToInt(String KIND)
	{
		int returner = 0;
		switch(KIND)
		{
		case "king":
		{
			returner = 1;
			break;
		}
		case "queen":
		{
			returner = 2;
			break;
		}
		case "bishop":
		{
			returner = 3;
			break;
		}
		case "knight":
		{
			returner = 4;
			break;
		}
		case "rook":
		{
			returner = 5;
			break;
		}
		case "pawn":
		{
			returner = 6;
			break;
		}
		}
		return returner;
	}
}
