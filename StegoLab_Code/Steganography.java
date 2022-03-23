import java.awt.Color;
import java.security.PolicySpi;
import java.util.ArrayList;
public class Steganography {
/** 
* Clear the lower (rightmost) two bits in a pixel.
*/ 
public static void ClearLow(Pixel p) 
{
    int redTemp = p.getRed()/4*4;
    p.setRed(redTemp);
    int blueTemp = p.getBlue()/4*4;
    p.setBlue(blueTemp);
    int greenTemp = p.getGreen()/4*4;
    p.setGreen(greenTemp);
}

public static Picture testClearLow(Picture p) 
{
Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
Pixel[][] pixels = newPic.getPixels2D();
for (int row = 0; row < pixels.length; row++){
    for (int column = 0; column < pixels[0].length; column++){
        ClearLow(pixels[row][column]);
    }
}
return newPic;
}

public static void setLow (Pixel p, Color c) {
    p.setRed(p.getRed() / 4 * 4 + c.getRed()/64);
    p.setBlue(p.getBlue() / 4 * 4 + c.getBlue()/64);
    p.setGreen(p.getGreen() / 4 * 4 + c.getGreen()/64);
}

public static Picture testSetLow(Picture p, Color c) 
{
Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
Pixel[][] pixels = newPic.getPixels2D();
for (int row = 0; row < pixels.length; row++){
    for (int column = 0; column < pixels[0].length; column++){
        setLow(pixels[row][column],c);
    }
}
return newPic;
}

/** 
* Sets the highest two bits of each pixel's colors to the lowest two bits of each pixel's colors 
*/ 
public static Picture revealPicture(Picture hidden) 
{ 
    Picture copy = new Picture(hidden); 
    Pixel[][] pixels = copy.getPixels2D();
    Pixel[][] source = hidden.getPixels2D(); 
    for (int r = 0; r < pixels.length; r++)
    { 
    for (int c = 0; c < pixels[0].length; c++)
    { 	
    Color col = source[r][c].getColor();
    pixels[r][c].setRed(col.getRed() % 4 * 64);
    pixels[r][c].setBlue(col.getBlue() % 4 * 64);
    pixels[r][c].setGreen(col.getGreen() % 4 * 64);
    /* To be implemented */ 
    }
    }
    return copy; 
}

/*
* Determines whether secret can be hidden in source, which is true if source and secret are the same dimensions. 
  @param source is not null 
* @param secret is not null 
* @return true if secret can be hidden in source, false otherwise. 
*/
public static boolean canHide(Picture source, Picture secret) {
    if (source.getWidth() >= secret.getWidth() && source.getHeight() >= secret.getHeight()) {
        return true;
    }
    return false;
}

/** 
* Creates a new Picture with data from secret hidden in data from source * @param source is not null 
* @param secret is not null 
* @return combined Picture with secret hidden in source 
* precondition: source is same width and height as secret 
*/
public static Picture hidePicture(Picture source, Picture secret) {
    Picture newSource = new Picture(source);
    Picture newSecret = new Picture(secret);
    Pixel[][] sourcePixels = newSource.getPixels2D();
    Pixel[][] secretPixels = newSecret.getPixels2D();
    for (int i = 0; i < secretPixels.length; i++) {
        for (int j = 0; j < secretPixels[0].length; j++) {
            setLow(sourcePixels[i][j], secretPixels[i][j].getColor());
            //sourcePixels[i][j].setRed(sourcePixels[i][j].getRed() / 4*4 + secretPixels[i][j].getRed() / 64);
            //sourcePixels[i][j].setGreen(sourcePixels[i][j].getGreen() / 4*4 + secretPixels[i][j].getGreen() / 64);
            //sourcePixels[i][j].setBlue(sourcePixels[i][j].getBlue() / 4*4 + secretPixels[i][j].getBlue() / 64);
        }
    }
    return newSource;
}

public static Picture hidePicture2(Picture source, Picture secret, int startRow, int startColumn) {
    Picture newSource = new Picture(source);
    Picture newSecret = new Picture(secret);
    Pixel[][] sourcePixels = newSource.getPixels2D();
    Pixel[][] secretPixels = newSecret.getPixels2D();
    for (int i = Math.max(startRow, 0); i < secretPixels.length + startRow && i < sourcePixels.length; i++) {
        for (int j = Math.max(startColumn, 0); j < secretPixels[0].length + startColumn && j < sourcePixels[0].length; j++) {
            setLow(sourcePixels[i][j], secretPixels[i - startRow][j - startColumn].getColor());
            //sourcePixels[i][j].setRed(sourcePixels[i][j].getRed() / 4*4 + secretPixels[i][j].getRed() / 64);
            //sourcePixels[i][j].setGreen(sourcePixels[i][j].getGreen() / 4*4 + secretPixels[i][j].getGreen() / 64);
            //sourcePixels[i][j].setBlue(sourcePixels[i][j].getBlue() / 4*4 + secretPixels[i][j].getBlue() / 64);
        }
    }
    return newSource;
}

public static boolean isSame(Picture source, Picture secret) {
    Picture newSource = new Picture(source);
    Picture newSecret = new Picture(secret);
    Pixel[][] sourcePixels = newSource.getPixels2D();
    Pixel[][] secretPixels = newSecret.getPixels2D();
    for (int i = 0; i < secretPixels.length; i++) {
        for (int j = 0; j < secretPixels[0].length; j++) {
            if (!sourcePixels[i][j].getColor().equals(secretPixels[i][j].getColor()))
            {return false;}
        }
    }
    return true;
}

public static ArrayList<String> findDifferences(Picture p1, Picture p2) {
    Picture copy1 = new Picture(p1);
    Picture copy2 = new Picture(p2);
    Pixel[][] pixels1 = copy1.getPixels2D();
    Pixel[][] pixels2 = copy2.getPixels2D();
    ArrayList<String> differences = new ArrayList<String>();
    for (int r = 0; r < pixels1.length; r++) {
        for (int c = 0; c < pixels1[0].length; c++) {
            if (!pixels1[r][c].getColor().equals(pixels2[r][c].getColor())) {
                differences.add(r + " " + c);
            }
        }
    }
    return differences;
} 

public static void main(String[] args){
Picture beach = new Picture("beach.jpg"); 
beach.explore(); 
Picture blueMotorcycle = new Picture("blueMotorcycle.jpg"); 
blueMotorcycle.explore(); 
Picture flower1 = new Picture("flower1.jpg"); 

//Picture copy = testClearLow(beach); 
//copy.explore(); 
//Picture copy2 = testSetLow(beach, Color.PINK); 
//copy2.explore(); 
//Picture copy3 = revealPicture(copy2);
//copy3.explore(); 

if (canHide(beach, blueMotorcycle)) {
    Picture secret = hidePicture2(beach, flower1, 100, 100);
    secret.explore();
    Picture revealed = revealPicture(secret);
    revealed.explore();
}
System.out.println("First Image and Second Image are the same: " + isSame(beach, blueMotorcycle));
System.out.println("First Image and Second Image number of different pixels: " + findDifferences(beach, beach).size());
}
}