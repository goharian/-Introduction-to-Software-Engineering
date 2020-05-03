package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @org.junit.Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: There is a simple single test here
        Sphere s = new Sphere(new Point3D(1, 1, 1), 2);
        Point3D p = new Point3D(3, 1, 1);

        assertEquals("Sphere.getNormal() result is wrong", s.getNormal(p), new Vector(1, 0, 0));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntsersections(primitives.Ray)}.
     */
    @org.junit.Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere",
                null, sphere.findIntsersections(new Ray(new Vector(1, 1, 0), new Point3D(-1, 0, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);

        List<Point3D> result = sphere.findIntsersections(new Ray(new Vector(3, 1, 0),
                new Point3D(-1, 0, 0)));

        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p = new Point3D(1, 0, 1);
        result = sphere.findIntsersections(new Ray(new Vector(0, 0, 2), new Point3D(1, 0, 0.5)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts inside sphere", p, result.get(0));

        // TC04: Ray starts after the sphere (0 points)
        //assertEquals( null,sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(2.5, 0, 0))),"Ray start after sphere");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        p = new Point3D(2, 0, 0);
        result = sphere.findIntsersections(new Ray(new Vector(2, 0, -2), new Point3D(1, 0, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts at sphere and goes inside", p, result.get(0));

        // TC12: Ray starts at sphere and goes outside (0 points)
        //assertEquals( null,sphere.findIntersections(new Ray(new Vector(-1, 0, 1), new Point3D(1, 0, 1))),"Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point3D(1, 0, -1);
        p2 = new Point3D(1, 0, 1);
        result = sphere.findIntsersections(new Ray(new Vector(0, 0, 3), new Point3D(1, 0, -1.5)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getZ().get() > result.get(1).getZ().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray starts before the sphere", List.of(p1, p2), result);
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntsersections(new Ray(new Vector(0, 0, 2.5), new Point3D(1, 0, -1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts at sphere and goes inside", p2, result.get(0));
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntsersections(new Ray(new Vector(0, 0, 2.5), new Point3D(1, 0, -0.5)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts inside", p2, result.get(0));
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntsersections(new Ray(new Vector(0, 0, 2.5), new Point3D(1, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts at the center", p2, result.get(0));
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray starts at sphere and goes outside",
                null, sphere.findIntsersections(new Ray(new Vector(0, 0, 2), new Point3D(1, 0, 1))));
        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray starts after sphere",
                null, sphere.findIntsersections(new Ray(new Vector(0, 0, 2), new Point3D(1, 0, 2))));


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertEquals("Ray starts before the tangent point",
                null, sphere.findIntsersections(new Ray(new Vector(2, 0, 0), new Point3D(0, 0, 1))));
        // TC20: Ray starts at the tangent point
        assertEquals("Ray starts at the tangent point",
                null, sphere.findIntsersections(new Ray(new Vector(2, 0, 0), new Point3D(1, 0, 1))));
        // TC21: Ray starts after the tangent point
        assertEquals("Ray starts after the tangent point",
                null, sphere.findIntsersections(new Ray(new Vector(2, 0, 0), new Point3D(1.5, 0, 1))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals("Ray starts at the tangent point",
                null, sphere.findIntsersections(new Ray(new Vector(0, 0, 5), new Point3D(3, 0, 0))));


    }

}