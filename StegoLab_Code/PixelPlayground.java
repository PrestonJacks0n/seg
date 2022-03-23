import java.awt.Color;
public class PixelPlayground {

    //remove all blue from every pixel
    public static Picture zeroBlue(Picture p)
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length; column++){
                pixels[row][column].setBlue(0);
            }
        }

        return newPic;
    }

    public static Picture keepOnlyBlue(Picture p)
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length; column++){
                pixels[row][column].setRed(0);
                pixels[row][column].setGreen(0);
            }
        }

        return newPic;
    }

    public static Picture Negate(Picture p)
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length; column++){
                pixels[row][column].setRed(255 - pixels[row][column].getRed());
                pixels[row][column].setGreen(255 - pixels[row][column].getGreen());
                pixels[row][column].setBlue(255 - pixels[row][column].getBlue());
            }
        }

        return newPic;
    }

    public static Picture Grayscale(Picture p)
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        int average; 
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length; column++){
                average = (pixels[row][column].getRed() + pixels[row][column].getBlue() + pixels[row][column].getGreen())/3;
                pixels[row][column].setRed(average);
                pixels[row][column].setGreen(average);
                pixels[row][column].setBlue(average);
            }
        }

        return newPic;
    }

    public static Picture fixUnderwater(Picture p)
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        int average; 
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length; column++){
                average = (pixels[row][column].getRed() + pixels[row][column].getBlue() + pixels[row][column].getGreen())/3;
                pixels[row][column].setRed(average);
                pixels[row][column].setGreen(average);
                pixels[row][column].setBlue(average);
            }
        }

        return newPic;
    }

    public static Picture mirrorImageHoriz1(Picture p) //mirrors left to right side
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length/2; column++){
                pixels[row][pixels[0].length -1 - column].setColor(pixels[row][column].getColor());
            }
        }
        return newPic;
    }

    public static Picture mirrorImageHoriz2(Picture p) //mirrors right to left side
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels[0].length/2; column++){
                pixels[row][column].setColor(pixels[row][pixels[0].length -1 - column].getColor());
            }
        }
        return newPic;
    }

    public static Picture mirrorImageVer1(Picture p) //mirrors bottom to top
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length/2; row++){
            for (int column = 0; column < pixels[0].length; column++){
                pixels[pixels.length -1 - row][column].setColor(pixels[row][column].getColor());
            }
        }
        return newPic;
    }

    public static Picture mirrorImageVer2(Picture p) //mirrors bottom to top
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length/2; row++){
            for (int column = 0; column < pixels[0].length; column++){
                pixels[row][column].setColor(pixels[pixels.length -1 - row][column].getColor());
            }
        }
        return newPic;
    }

    public static Picture mirrorImageDiagonal(Picture p) //mirrors bottom to top
    {
        Picture newPic = new Picture(p); //copy p so as not to destroy persistent data
        //to be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for (int row = 0; row < pixels.length; row++){
            for (int column = 0; column < pixels.length; column++){
                pixels[row][column].setColor(pixels[column][row].getColor());
            }
        }
        return newPic;
    }


    

    public static void main(String[] args)
    {
        Picture beachPic = new Picture("beach.jpg");
        beachPic.explore();

        //Picture waterPic = new Picture("water.jpg");
        //waterPic.explore();

        //Zero Blue Call
        //Picture beachPicNoBlue = zeroBlue(beachPic);
        //beachPicNoBlue.explore();

        //Only Blue Call
        //Picture beachPickeepOnlyBlue = keepOnlyBlue(beachPic);
        //beachPickeepOnlyBlue.explore();

        //Negate Call
        //Picture beachPicNegate = Negate(beachPic);
        //beachPicNegate.explore();

        //Grayscale Call
        //Picture beachPicGrayscale = Grayscale(beachPic);
        //beachPicGrayscale.explore();

        //Fix Underwater Call (doesn't work)
        //Picture waterPicfixUnderwater = fixUnderwater(waterPic);
        //waterPicfixUnderwater.explore();

        //Mirror Right to Left
        //Picture beachPicHor1 = mirrorImageHoriz1(beachPic);
        //beachPicHor1.explore();

        //Mirror Left to Right
        //Picture beachPicHor2 = mirrorImageHoriz2(beachPic);
        //beachPicHor2.explore();

        //Mirror Top to Bottom 
        //Picture beachPicV1 = mirrorImageVer1(beachPic);
        //beachPicV1.explore();

        //Mirror Bottom to Top
        //Picture beachPicV2 = mirrorImageVer2(beachPic);
        //beachPicV2.explore();

        //Mirror Diagonal 
        Picture beachPicDiag = mirrorImageDiagonal(beachPic);
        beachPicDiag.explore();
    }
    
}
