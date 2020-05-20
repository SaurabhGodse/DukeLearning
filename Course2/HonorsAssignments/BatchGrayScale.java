import edu.duke.*;
import java.io.*;

/**
 * Write a description of BatchGrayScale here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class BatchGrayScale {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
    return outImage;
    }
    
    public void testGray(){
        ImageResource inImage = new ImageResource();
        ImageResource outImage = makeGray(inImage);
        outImage.draw();
    }

}
