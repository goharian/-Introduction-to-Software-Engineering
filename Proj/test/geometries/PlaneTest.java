package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;


/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
class PlaneTest {
    /**
     * Test method for {@link.geometries.Plane#getNormal(geometries.Plane)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        Plane p = new Plane(new Point3D(1, 1, 1), new Vector(2, 0, 0));
        assertEquals("Plane.getNormal() result is wrong", p.getNormal(new Point3D(1, 1, 1)), new Vector(1, 0, 0));
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point3D(0, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane
        Point3D p = new Point3D(0, 1, 0);
        List<Intersectable.GeoPoint> result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points",1, result.size());
        assertEquals("Wrong Intersection point", p, result.get(0));


        // TC02: Ray does not intersect the plane
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(-1, 1, 0)));
        assertEquals("Wrong number of points",0, result.size());


        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC03: the ray included in the plane
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points",0, result.size());

        // TC04: the ray is not included in the plane
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points",0, result.size());

        // **** Group: Ray is orthogonal to the plane
        // TC05: – the ray starts before the plane
        p = new Point3D(0, 0, 0);
        result = plane.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals("Wrong number of points",1, result.size());
        assertEquals("Wrong Intersection point",p, result.get(0));

        // TC06: – the ray starts after the plane
        result = plane.findIntersections(new Ray(new Point3D(1, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals("Wrong number of points",0, result.size());

        // TC07: – the ray starts at the plane
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals("Wrong number of points",0, result.size());

        // **** Special cases
        // TC08: – Ray starts at the plane
        result = plane.findIntersections(new Ray(new Point3D(0, 1, 1),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points",0, result.size());

        // TC09: – Ray starts at the reference point of the plane
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points",0, result.size());
    }
}
