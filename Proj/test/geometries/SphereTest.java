package geometries;

import primitives.Point3D;
import primitives.Vector;

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

}