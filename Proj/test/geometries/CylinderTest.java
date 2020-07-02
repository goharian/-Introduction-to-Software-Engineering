package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class CylinderTest {

    /**
     * Test of getNormal method, of class Cylinder.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Ray r = new Ray(new Point3D(1,0,0), new Vector(0,1,0));
        Cylinder c = new Cylinder(1, r,5);
        assertEquals("Cylinder.getNormal() result is wrong 1", new Vector(1, 0, 0), c.getNormal(new Point3D(2, 1, 0)));
        assertEquals("Cylinder.getNormal() result is wrong 2", new Vector(0, -1, 0), c.getNormal(new Point3D(1.5, 0, 0)));
        assertEquals("Cylinder.getNormal() result is wrong 3", new Vector(0, 1, 0), c.getNormal(new Point3D(1.5, 5, 0)));


        // ============ Boundary Tests ==============
        assertEquals("Cylinder.getNormal() result is wrong 4", new Vector(0, -1, 0), c.getNormal(new Point3D(2, 0, 0)));
        assertEquals("Cylinder.getNormal() result is wrong 5", new Vector(0, 1, 0), c.getNormal(new Point3D(2, 5, 0)));
    }
}
