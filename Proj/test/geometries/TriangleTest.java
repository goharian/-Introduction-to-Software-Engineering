package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class TriangleTest {
    /**
     * Test method for
     * {@link.geometries.Triangle#getNormal(geometries.Triangle)}.
     */
    @org.junit.Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: There is a simple single test here

        Triangle triangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);

        assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3),
                triangle.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * Test Method for {@link Triangle#findIntersections(Ray)}
     */
    @org.junit.Test
    public void testFindIntersections()
    {

        Polygon p1 = new Triangle(new Point3D(2,3,2),new Point3D(4,3,2), new Point3D(4,1,2));

        // ============ Equivalence Partitions Tests ==============

        //TCO1: ray intersects with triangle
        List<Point3D> intersects = p1.findIntersections(new Ray(new Vector(0,0,1), new Point3D(3.5,2,1)));
        //checks amount of points returned
        if (intersects == null || intersects.size()!=1 )
            fail("invalid amount of points returned");
        //checks that points are correct
        assertEquals("Error! Function does not find ray intersection",
                new Point3D(3.5,2,2), intersects.get(0));

        //TCO2: ray doesn't intersect
        assertEquals("Error! Function finds intersection when there is none",
                null, p1.findIntersections(new Ray(new Vector(0,0,-1), new Point3D(3,0,2))));

        // ============ Boundary Value Tests ==============
        //TCO3: ray is parallel to the triangle, in plane
        assertEquals("invalid point for ray parallel to plane",
                null, p1.findIntersections(new Ray(new Vector(1,0,0), new Point3D(4,4,2))));

        //TCO4: ray is parallel to the triangle and out of the plane
        assertEquals("invalid point for ray parallel to plane",
                null, p1.findIntersections(new Ray(new Vector(1,5,0), new Point3D(1,1,-2))));

        //TCO5: ray is orthogonal to the plane, starts above it
        assertEquals("invalid point for ray orthogonal to plane",
                null, p1.findIntersections(new Ray(new Vector(0,0,1), new Point3D(6,2,5))));

        //TCO6: ray is orthogonal to the plane and starts below it
        assertEquals("invalid point for ray orthogonal to plane",
                null, p1.findIntersections(new Ray(new Vector(0,0,1), new Point3D(6,2,1))));

        //TC07: ray starts in the plane and is not orthogonal or parallel
        assertEquals("invalid point for ray starting in plane",
                null, p1.findIntersections(new Ray(new Vector(1,2,3), new Point3D(-5,3,2))));

        //TC08: ray begins in polygon's vertice
        assertEquals("invalid point for ray starting in polygon's vertice",
                null, p1.findIntersections(new Ray(new Vector(1,2,7), new Point3D(2,3,2))));

        //TC09: ray begins on triangle's edge
        assertEquals("invalid point for ray starting on polygon's edge",
                null, p1.findIntersections(new Ray(new Vector(1,2,7), new Point3D(3,2,2))));

        //TC10: ray intersects with triangle's edge
        assertEquals("invalid point for ray intersecting with polygon's edge",
                null, p1.findIntersections(new Ray(new Vector(0,0,1), new Point3D(3,2,-2))));


    }
}