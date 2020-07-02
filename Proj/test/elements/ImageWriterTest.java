package elements;

import renderer.ImageWriter;

import java.awt.*;

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
    public void writeToImage() {
        ImageWriter image = new ImageWriter("firstTest", 1600, 1000, 800, 500);
        for (int col = 0; col < 500; col++) {
            for (int row = 0; row < 800; row++) {
                if (col % 50 == 0 || row % 50 == 0) {
                    image.writePixel(row, col, java.awt.Color.RED);
                }
                else {
                    image.writePixel(row, col, Color.WHITE);
                }
            }
        }
        image.writeToImage();//create image
    }

}