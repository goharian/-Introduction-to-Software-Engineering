package elements;

import primitives.Color;
import renderer.ImageWriter;

/**
 * testing image writer
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class ImageWriterTest {

    /**
     * Test method for image writer
     */
    @org.junit.Test
    public void buildPicture(){
        ImageWriter iw = new ImageWriter("first squares", 1000, 1600, 500, 800);
        for (int i = 0 ; i<iw.getNx(); i++)
            for (int j = 0 ; j<iw.getNy(); j++)// loops over places
            {
                if (i % (800/16) == 0 || j % (500/10) == 0) //creates grid in relevant pixels
                {
                    iw.writePixel(i,j, new Color(20,200,0).getColor());
                }
                else {
                    iw.writePixel(i,j, new Color(220,0,120).getColor());
                }

            }
        iw.writeToImage();//create image
    }

}