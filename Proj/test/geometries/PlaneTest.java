package geometries;

import primitives.Point3D;
import primitives.Vector;

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
}
