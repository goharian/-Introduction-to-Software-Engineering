package primitives;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class VectorTest {

    /**
     * Test method for
     * {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @org.junit.Test
    public void testConstructor() {
        // =============== Boundary Values Tests ==================
        //TC01: exception on zero vector
        try {
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @org.junit.Test
    public void testAdd() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -2.0, -1.5);

        // ============ Equivalence Partitions Tests ==============
        v1 = v1.add(v2);
        assertEquals(new Vector(0.0, -1.0, -0.5), v1);

        v2 = v2.add(v1);
        assertEquals(new Vector(-1.0, -3.0, -2.0), v2);
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @org.junit.Test
    public void scale() {
        Vector v = new Vector(2.0, 1.0, 1.0);

        // ============ Equivalence Partitions Tests ==============
        int num = 3;
        v = v.scale(num);
        assertEquals("ERROR: scale() does not multiply correctly", new Vector(6.0, 3.0, 3.0), v);
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @org.junit.Test
    public void testLengthSquared() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: value
        assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @org.junit.Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: value
        assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @org.junit.Test
    public void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();

        // ============ Equivalence Partitions Tests ==============
        // TC01: creates a new vector
        assertSame("ERROR: normalize() function creates a new vector", vCopy, vCopyNormalize);

        // TC02: result is unit vector
        assertTrue("ERROR: normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
    }

    /**
     * Test method for {@link primitives.Vector#normalized()}.
     */
    @org.junit.Test
    public void testNormalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();

        // ============ Equivalence Partitions Tests ==============
        // TC01: creates a new vector
        assertEquals("ERROR: normalized() function does not create a new vector", u, v);
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @org.junit.Test
    public void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: orthogonal vector
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));

        // TC02: test Dot-Product value for vectors
        assertTrue("ERROR: dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
    }

    /**
     * Test method for
     * {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @org.junit.Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
    }


}