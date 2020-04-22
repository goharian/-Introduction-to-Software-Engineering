package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @org.junit.Test
    public void testGetNormal() {
        Ray r = new Ray(new Vector(0, 1, 0), new Point3D(1, 0, 0));
        Tube t = new Tube(r, 1);

        // ============ Equivalence Partitions Tests ==============
        //TC01: result
        assertEquals("getNormal() result is wrong", new Vector(-1, 0, 0),
                t.getNormal(new Point3D(2, 0, 0)));
    }
}