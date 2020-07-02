package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;


/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class PlaneTest {

    /**
     * Test method for
     * {@link geometries.Plane#Plane(Point3D, Point3D, Point3D)}.
     */
    @org.junit.Test
    public void testConstructor() {
        Plane p1 = new Plane(new Point3D(-1, 1, 2), new Point3D(-4, 2, 2), new Point3D(-2, 1, 5));

        // ============ Equivalence Partitions Tests ==============
        //TC01: Normal result
        assertEquals("ERROR: Normal is wrong", (new Vector(3, 9, 1)).normalize(), p1.getNormal());
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @org.junit.Test
    public void getNormal() {
        Plane p = new Plane(new Point3D(1, 1, 1), new Vector(2, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        //TC01: result
        assertEquals("Plane.getNormal() result is wrong", p.getNormal(new Point3D(1, 1, 1)),
                new Vector(1, 0, 0));
    }

    // ============ Test FindIntersection ==============

    /**
     * Test method for {@link Plane# findIntersections(Ray)}
     */
    @org.junit.Test
    public void testFindIntersections() {
        //create plane: Z=2
        Plane p1 = new Plane(new Point3D(0, 0, 2), new Point3D(2, 3, 2), new Point3D(4, 1, 2));
        // ============ Equivalence Partitions Tests ==============
        //TCO1: ray intersects with plane
        //checks the amount of points returned
        List<Intersectable.GeoPoint> intersect = p1.findIntersections(new Ray(new Vector(0, 0, 2), new Point3D(0, 2, 1)));
        if (intersect == null || intersect.size() != 1)
            fail("invalid amount of points returned");
        //checks that points are correct
        assertEquals("Error! Function does not find ray intersection", new Point3D(0, 2, 2), intersect.get(0));

        //TCO2: ray doesn't intersect with plane
        assertEquals("Error! Function finds intersection when there is none",
                p1.findIntersections(new Ray(new Vector(0, 0, 1), new Point3D(0, 0, 10))), null);

        // ============ Boundary Value Tests ==============
        //TCO3: ray is parallel to the plane and in plane
        assertEquals("invalid point for ray parallel to plane",
                p1.findIntersections(new Ray(new Vector(1, 2, 0), new Point3D(1, 1, 2))), null);

        //TCO4: ray is parallel to the plane and out of the plane
        assertEquals("invalid point for ray parallel to plane",
                p1.findIntersections(new Ray(new Vector(1, 5, 0), new Point3D(1, 1, -2))), null);

        //TCO5: ray is orthogonal to the plane and starts in the plane
        assertEquals("invalid point for ray orthogonal to plane",
                p1.findIntersections(new Ray(new Vector(0, 1, 0), new Point3D(1, 1, 2))), null);

        //TCO6: ray is orthogonal to the plane and starts above it and hits it
        assertEquals("invalid point for ray orthogonal to plane",
                new Point3D(1, 1, 2),
                p1.findIntersections(new Ray(new Vector(0, 0, -3), new Point3D(1, 1, 4))).get(0));

        //TCO7: ray is orthogonal to the plane and starts above it and doesn't hit it
        assertEquals("invalid point for ray orthogonal to plane",
                p1.findIntersections(new Ray(new Vector(0, 0, 2), new Point3D(1, 1, 4))), null);

        //TCO8: ray is orthogonal to the plane and starts below the plane
        assertEquals("invalid point for ray orthogonal to plane",
                p1.findIntersections(new Ray(new Vector(0, 0, -3), new Point3D(1, 1, 1))), null);

        //TCO9: ray starts in the plane and is not orthogonal and parallel
        assertEquals("invalid point for ray starting in plane",
                p1.findIntersections(new Ray(new Vector(1, 2, 3), new Point3D(1, 1, 2))), null);

        //TC10: ray begins in plane's point of reference
        assertEquals("invalid point for ray starting in plane's point of reference",
                p1.findIntersections(new Ray(new Vector(1, 2, 3), new Point3D(0, 0, 2))), null);


    }
}
