package elements;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing  Camera Integration
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class CameraIntegrationTest {
    @Test
    /**
     * test for integration of constructing ray and finding ray intersection with a sphere
     */
    public void testConstructRayAndSphereIntersection() {

        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Intersectable.GeoPoint> results;
        Sphere sphere;
        int Nx = 3;
        int Ny = 3;
        int counter;

        //TC01 - 2 intersection points with sphere
        sphere = new Sphere(1, new Point3D(0, 0, 3));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = sphere.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",2, counter);

        //TC02 - 18 intersection points with sphere
        sphere = new Sphere(2.5, new Point3D(0, 0, 2.5));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = sphere.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",18, counter);

        //TC03 - 10 intersection points with sphere
        sphere = new Sphere(2, new Point3D(0, 0, 2));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = sphere.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points", 10, counter);

        //TC04 - 9 intersection points with sphere
        sphere = new Sphere(4, new Point3D(0, 0, 0.5));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = sphere.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",9, counter);

        //TC05 - no intersection points with sphere - sphere behind camera
        sphere = new Sphere(0.5, new Point3D(0, 0, -1));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = sphere.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",0, counter);

    }

    @Test
    /**
     * test for integration of constructing ray and finding ray intersection with a plane
     */
    public void testConstructRayAndPlaneIntersection() {

        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Intersectable.GeoPoint> results;
        Plane plane;
        int Nx = 3;
        int Ny = 3;
        int counter;

        //TC01 - 9 intersection points with plane - plane is paralleled to the view plane
        plane = new Plane(new Point3D(0,0,2), new Vector(0,0,1));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = plane.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",9, counter);

        //TC02 - 9 intersection points with plane
        plane = new Plane(new Point3D(0,0,4), new Point3D(0.5,1,4.2), new Point3D(-0.5,1,3.8));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = plane.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",9, counter);

        //TC03 - 6 intersection points with plane
        plane = new Plane(new Point3D(0,0,4), new Point3D(-0.2,1.5,5), new Point3D(-0.5,1,3.8));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = plane.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",6, counter);

    }

    @Test
    /**
     * test for integration of constructing ray and finding ray intersection with a triangle
     */
    public void testConstructRayAndTriangleIntersection() {

        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Intersectable.GeoPoint> results;
        Triangle triangle;
        int Nx = 3;
        int Ny = 3;
        int counter;

        //TC01 - 1 intersection points with triangle
        triangle = new Triangle(new Point3D(0,-1,2), new Point3D(1,1,2), new Point3D(-1,1,2));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = triangle.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",1, counter);

        //TC02 - 2 intersection points with triangle
        triangle = new Triangle(new Point3D(0,-20,2), new Point3D(1,1,2), new Point3D(-1,1,2));
        counter = 0;
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = triangle.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    counter += results.size();
            }
        }
        assertEquals("Wrong number of points",2, counter);

    }
}
