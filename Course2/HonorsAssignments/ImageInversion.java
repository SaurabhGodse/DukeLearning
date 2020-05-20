import edu.duke.*;
import java.io.*;
/**
 * Write a description of ImageInversion here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class ImageInversion {
    public ImageResource makeInvert(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInvert(inImage);
            String name = inImage.getFileName();
            String newName = "Images/invert-" + name;
            outImage.setFileName(newName);
            outImage.draw();
            outImage.save();
            
        }
    }


}
