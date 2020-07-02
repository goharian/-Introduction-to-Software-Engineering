package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing list of geometries
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class GeometriesTest {

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}
     */
    @Test
    public void findIntsersections() {

        Geometries g = new Geometries();
        List<Intersectable.GeoPoint> result;

        // ============ Equivalence Partitions Tests ==============

        //TC01: intersection with some geometries
        result = g.findIntersections(new Ray(new Point3D(6, 7, -0.5),
                new Vector(-5, -5, 0)));
        assertEquals("Wrong number of points", 3, result.size());


        // =============== Boundary Values Tests ==================
        // TC02: empty collection
        result = g.findIntersections(new Ray(new Point3D(2, 2, 1),
                new Vector(2, -2, -1)));
        assertEquals("Wrong number of points", null, result);

        //TC03: no intersections
        g.add(new Polygon(new Point3D(0, 0, 0), new Point3D(4, 0, 0), new Point3D(4, 4, 0), new Point3D(0, 4, 0)),
                new Plane(new Point3D(1, 1, 1), new Vector(2, 0, 0)),
                new Sphere(2, new Point3D(1, 1, 1)),
                new Triangle(new Point3D(0, 0, 0), new Point3D(4, 0, 0), new Point3D(0, 4, 0)));
        result = g.findIntersections(new Ray(new Point3D(-3, -3, -3),
                new Vector(-2, -2, -1)));
        assertEquals("Wrong number of points", null, result);

        //TC04: intersection with one geometry only
        result = g.findIntersections(new Ray(new Point3D(6, 6, 6),
                new Vector(-1, 1, 1)));
        assertEquals("Wrong number of points", 1, result.size());

        //TC05: intersection with all the geometries
        result = g.findIntersections(new Ray(new Point3D(0.5, 1.2, -0.5),
                new Vector(0.5, 0.8, 2.5)));
        assertEquals("Wrong number of points", 4, result.size());

    }
}