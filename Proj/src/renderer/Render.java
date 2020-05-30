package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import elements.*;
import geometries.*;
import primitives.*;
import java.util.List;

/**
 * class that renders color pictures in scene.
 * creates matrix of colors for picture from the scene
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Render
{
    private ImageWriter _imageWriter;
    private Scene _scene;

    /**
     *
     * @param _scene creates render with scene recieved
     */
    public Render(Scene _scene) {
        this._scene = _scene;
    }

    /**
     *
     * @param imageWriter pixel color matrix information class
     * @param scene scene to render based on
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    public Scene get_scene() {
        return _scene;
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < height; ++row) {
            for (int column = 0; column < width; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                }
                else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }

    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double columns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                if (column % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(column, row, colorsep);
                }
            }
        }
    }

    /**
     * Finding the closest point to the P0 of the camera.
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */

    /**
     *
     * @param intersectionPoints points of intersection in scene
     * @return closest intersection point to viewplane
     */
    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
        Point3D result = null;
        double startValue = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (Point3D point3D : intersectionPoints) {
            double distance = p0.distance(point3D);
            if (distance < startValue) {
                startValue = distance;
                result = point3D;
            }
        }
        return result;
    }

    /**
     * colors the scene and creates a jpg file based on the information in image writer
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     *
     * @param point point in scene
     * @return light on that point
     */
    private Color calcColor(Point3D point) {
        return _scene.getAmbientLight().getIntensity();
    }
}