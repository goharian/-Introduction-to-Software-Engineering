package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
class SphereTest {
    // ============ Equivalence Partitions Tests ==============

    /**
     * Test method for {@link.geometries.Sphere#getNormal(geometries.Sphere)}.
     */
    @Test
    void getNormal() {
        Sphere s = new Sphere(2, new Point3D(1, 1, 1));
        Point3D p = new Point3D(3, 1, 1);
        assertEquals("Sphere.getNormal() result is wrong", s.getNormal(p), new Vector(1, 0, 0));
    }

    /**
     * Test method for {@link Sphere#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Intersectable.GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).point.getX().get() > result.get(1).point.getX().get())
            result = Arrays.asList(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", Arrays.asList(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point3D(1.8, 0.6, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 0.6, 0),
                new Vector(1, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Wrong Intersection point", p1, result.get(0));

        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(-1, -1, 0)));
        assertEquals("Wrong number of points", null, result);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        p1 = new Point3D(1.6, 0.8, 0);
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(1, 0.5, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Wrong Intersection point", p1, result.get(0));

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(-1, -1, 0)));
        assertEquals("Wrong number of points", null, result);


        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point3D(0, 0, 0);
        p2 = new Point3D(2, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(2.5, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).point.getX().get() > result.get(1).point.getX().get())
            result = Arrays.asList(result.get(1), result.get(0));
        assertEquals("Wrong Intersection point", Arrays.asList(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        p1 = new Point3D(2, 0, 0);
        p2 = new Point3D(0, 0, 0);
        result = sphere.findIntersections(new Ray(p1,
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points",1, result.size());
        assertEquals("Wrong Intersection point",p2, result.get(0));

        // TC15: Ray starts inside (1 points)
        p1 = new Point3D(0, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points",1, result.size());
        assertEquals("Wrong Intersection point",p1, result.get(0));

        // TC16: Ray starts at the center (1 points)
        p1 = new Point3D(1.7071067811865475, 0.7071067811865475, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points",1, result.size());
        assertEquals("Wrong Intersection point",p1, result.get(0));

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points",null, result);

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points",null, result);


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(0, -1, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points",null, result);

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points",null, result);

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points",null, result);

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points",null, result);
    }
}