import java.io.File;
import edu.duke.*;

// TODO: create a grayscale version of image
// TODO: Let user select multiple images files
// TODO: Save the grayscale image with 'gray-' prefix

public class BatchImg {
    public void test(){
        selectAndProcessImgs("inv-");
    }

    public ImageResource grayscale(ImageResource imgIn){
        int average = 0;
        for(Pixel pixel : imgIn.pixels()){ 
            average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return imgIn;
    }

    public ImageResource inverse(ImageResource imgIn){
        int R = 0;
        int G = 0;
        int B = 0;
        for(Pixel pixel : imgIn.pixels()){ 
            R = 255 - pixel.getRed();
            G = 255 - pixel.getGreen();
            B = 255 - pixel.getBlue();
            pixel.setRed(R);
            pixel.setGreen(G);
            pixel.setBlue(B);
        }

        return imgIn;
    }

    public void saveImg(ImageResource img, String prefix){
        String newName = prefix + img.getFileName();
        img.setFileName(newName);

        img.draw();
        img.save();
    }

    public void selectAndProcessImgs(String prefix){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource imgIn = new ImageResource(f);
            if("gray-".equals(prefix)){
                imgIn = this.grayscale(imgIn);
            }
            else if("inv-".equals(prefix)){
                imgIn = this.inverse(imgIn);
            }
            this.saveImg(imgIn, prefix);
        }
    }
}