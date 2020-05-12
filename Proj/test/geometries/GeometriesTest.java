package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing list of geometries
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class GeometriesTest {

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}
     */
    @org.junit.Test
    public void findIntersections() {
        // ============ Boundary Value Analysis ==============
        // TC01: empty list
        Geometries g1 = new Geometries();
        assertEquals("Error! Geometries found intersections when there were no shapes",
                null, g1.findIntersections(new Ray(new Vector(1, 2, 3), new Point3D(4, 5, 4))));

        // TC02: no shape hits ray
        Geometries g2 = new Geometries(
                new Sphere(new Point3D(4, 4, 4), 1),
                new Plane(new Point3D(0, 6, 0), new Point3D(0, 0, 10), new Point3D(3, 0, 0)),
                new Polygon(new Point3D(3, 5, 0), new Point3D(9, 11, 0), new Point3D(2, 1, 0),
                        new Point3D(1, 0, 0)),
                new Triangle(new Point3D(1, 3, 5), new Point3D(1, 1, 1), new Point3D(2, -5, 3)));

        assertEquals("Error! Geometries found points when there were none",
                null, g2.findIntersections(new Ray(new Vector(0, 0, -1), new Point3D(-4, -3, -2))));

        //TCO3: one shape hits ray
        g2.add(new Sphere(new Point3D(-4, -3, -15), 15));
        try {
            assertEquals("Error! Geometries found invalid amount of intersections",
                    1, g2.findIntersections(new Ray(new Vector(0, 0, -1), new Point3D(-4, -3, -2))).size());
        } catch (NullPointerException e)//intersection list was null
        {
            fail("Error! Geometries didn't find the one intersection point");
        }

        //TCO4: all shapes hit ray
        Geometries g4 = new Geometries(
                new Sphere(new Point3D(0, 0, 4), 1),
                new Plane(new Point3D(0, 0, 6), new Vector(4, 5, 0)),
                new Triangle(new Point3D(-3, -2, 8), new Point3D(-1, 9, 8), new Point3D(2, -2, 8)),
                new Polygon(new Point3D(-3, -2, 10), new Point3D(-1, 9, 10), new Point3D(2, 3, 10),
                        new Point3D(2, -2, 10)));
        try {
            assertEquals("Error! Geometries didn't find all intersections",
                    g4.get_shapes().size(),
                    g4.findIntersections(new Ray(new Vector(0, 0, 1), new Point3D(0, 0, -2))).size());
        } catch (NullPointerException e)//intersection list was null
        {
            fail("Error! Geometries found no intersections when all intersect");
        }

        //TCO5: ray hits all shapes but one
        g4.add(new Sphere(new Point3D(-4, -3, -15), 1));//adds shape that ray doesn't hit
        assertEquals("Error! Geometries found invalid amount of intersections",
                g4.get_shapes().size() - 1,
                g4.findIntersections(new Ray(new Vector(0, 0, 1), new Point3D(0, 0, -2))).size());

        // ============ Equivalence Partitions Tests ==============
        //TCO6: some shapes hit ray and some don't
        g4.add(new Sphere(new Point3D(15, 6, 50), 1));
        try {
            assertEquals("Error! Geometries found invalid amount of intersections",
                    g4.get_shapes().size() - 2,
                    g4.findIntersections(new Ray(new Vector(0, 0, 1), new Point3D(0, 0, -2))).size());
        } catch (NullPointerException e)//intersection list was null
        {
            fail("Error! Geometries found invalid amount of intersections");
        }


    }
}