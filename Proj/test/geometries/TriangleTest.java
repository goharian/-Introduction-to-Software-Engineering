package geometries;

import primitives.Point3D;
import primitives.Vector;

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

}