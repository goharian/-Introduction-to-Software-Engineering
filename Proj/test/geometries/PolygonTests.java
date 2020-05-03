package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing Polygons
 *
 * @author Dan
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(Point3D...)}.
     */
    @org.junit.Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {
        }

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {
        }

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {
        }

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @org.junit.Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * Test Method for {@link Polygon#findIntsersections(Ray)}
     */
    @org.junit.Test
    public void testFindIntersections() {

        Polygon p1 = new Polygon(new Point3D(2, 3, 2), new Point3D(4, 3, 2), new Point3D(4, 1, 2), new Point3D(2, 1, 2));

        // ============ Equivalence Partitions Tests ==============

        //TCO1: ray intersects with polygon
        List<Point3D> intersects = p1.findIntsersections(new Ray(new Vector(0, 0, 1), new Point3D(3, 2, 1)));
        //checks amount of points returned
        if (intersects == null || intersects.size() != 1)
            fail("invalid amount of points returned");
        //checks that points are correct
        assertEquals("Error! Function does not find ray intersection",
                new Point3D(3, 2, 2), intersects.get(0));

        //TCO2: ray doesn't intersect with polygon
        assertEquals("Error! Function finds intersection when there is none", null,
                p1.findIntsersections(new Ray(new Vector(0, 0, -1), new Point3D(3, 0, 2))));

        // ============ Boundary Value Tests ==============
        //TCO3: ray is parallel to the polygon, in plane but not in polygon
        assertEquals("invalid point for ray parallel to plane", null,
                p1.findIntsersections(new Ray(new Vector(1, 0, 0), new Point3D(4, 4, 2))));

        //TCO4: ray is parallel to the polygon, hits plane and polygon
        assertEquals("invalid point for ray parallel to plane", null,
                p1.findIntsersections(new Ray(new Vector(1, 0, 0), new Point3D(1, 2, 2))));

        //TCO5: ray is parallel to the polygon and out of the plane
        assertEquals("invalid point for ray parallel to plane", null,
                p1.findIntsersections(new Ray(new Vector(1, 5, 0), new Point3D(1, 1, -2))));

        //TCO6: ray is orthogonal to the plane and starts in the polygon
        assertEquals("invalid point for ray orthogonal to plane", null,
                p1.findIntsersections(new Ray(new Vector(0, 0, 4), new Point3D(3, 1, 2))));

        //TCO7: ray is orthogonal to the plane, starts above it and hits the polygon
        try {
            assertEquals("invalid point for ray orthogonal to plane", new Point3D(3, 2, 2),
                    p1.findIntsersections(new Ray(new Vector(0, 0, -1), new Point3D(3, 2, 5))).get(0));
        } catch (NullPointerException e) //if no point was returned
        {
            fail("invalid point for ray orthogonal to plane");
        }
        //TCO8: ray is orthogonal to the plane, starts above it and doesn't hit the polygon
        assertEquals("invalid point for ray orthogonal to plane",
                null, p1.findIntsersections(new Ray(new Vector(0, 0, 1), new Point3D(6, 2, 5))));

        //TCO9: ray is orthogonal to the plane and starts below it
        assertEquals("invalid point for ray orthogonal to plane",
                null, p1.findIntsersections(new Ray(new Vector(0, 0, 1), new Point3D(6, 2, 1))));

        //TC10: ray starts in the plane and is not orthogonal or parallel
        assertEquals("invalid point for ray starting in plane",
                null, p1.findIntsersections(new Ray(new Vector(1, 2, 3), new Point3D(-5, 3, 2))));

        //TC11: ray starts in the polygon and is not orthogonal or parallel to the plane
        assertEquals("invalid point for ray starting in polygon",
                null, p1.findIntsersections(new Ray(new Vector(1, 2, 3), new Point3D(3, 3, 2))));

        //TC12: ray begins in polygon's vertice
        assertEquals("invalid point for ray starting in polygon's vernice",
                null, p1.findIntsersections(new Ray(new Vector(1, 2, 7), new Point3D(2, 3, 2))));

        //TC13: ray begins on polygon's edge
        assertEquals("invalid point for ray starting on polygon's edge",
                null, p1.findIntsersections(new Ray(new Vector(1, 2, 7), new Point3D(3, 3, 2))));
        //another such point: new Ray(new Vector(2,3,2), new Point3D(0,1,0)

        //TC14: ray intersects with polygon's edge
        assertEquals("invalid point for ray intersecting with polygon's edge",
                null, p1.findIntsersections(new Ray(new Vector(0, 0, 1), new Point3D(4, 2, -2))));


    }

}
