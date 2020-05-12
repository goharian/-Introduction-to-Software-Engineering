package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Shphere in a 3D Cartesian coordinate system
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Sphere extends RadialGeometry {

    private final Point3D _center;

    /**
     * builds Sphere with 3D center point and radius
     *
     * @param _center The center point of the sphere
     * @param _radius sphere's radius
     */
    public Sphere(Point3D _center, double _radius) {
        super(_radius);
        this._center = _center;
    }

    /**
     * @param point Point across the sphere
     * @return Normal vector perpendicular to the sphere
     */
    @Override
    public Vector getNormal(Point3D point) {
        return point.subtract(_center).normalize();
    }

    /**
     * @return The center of the sphere as a new point
     */
    public Point3D getCenter() {
        return new Point3D(_center);
    }

    /**
     * @return A string representing the specified sphere
     */
    @Override
    public String toString() {
        return "center=" + _center.toString();
    }

    /**
     * @param ray from camera
     * @return list of intersection points with the sphere
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector u;
        double tm, d, th, t1, t2;
        try {
            u = new Vector(_center.subtract(ray.getStart()));
            tm = alignZero(ray.getDirection().dotProduct(u));
            if (isZero(tm))
                d = Math.sqrt(u.length() * u.length());
            else
                d = Math.sqrt(u.length() * u.length() - tm * tm);
            if (d > getRadius()) {
                return null;
            }
            th = Math.sqrt(getRadius() * getRadius() - d * d);
            t1 = tm + th;
            t2 = tm - th;
            Point3D p1 = null;
            Point3D p2 = null;
            if (t1 > 0)
                p1 = ray.getPoint(t1);
            if (t2 > 0)
                p2 = ray.getPoint(t2);
            LinkedList<Point3D> result = new LinkedList<Point3D>();
            if (p1 != null && p2 != null) {
                result.add(p1);
                result.add(p2);
                return result;
            }
            if (p1 != null && p2 == null) {
                result.add(p1);
                return result;
            }
            return null;
        } catch (IllegalArgumentException e) {
            t1 = getRadius();
            Point3D p1 = ray.getPoint(t1);
            LinkedList<Point3D> result = new LinkedList<Point3D>();
            result.add(p1);
            return result;
        }
    }
}
