package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere in a 3D Cartesian coordinate system
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Sphere extends RadialGeometry {

    private final Point3D _center;

    /**
     * constructor
     *
     * @param emission
     * @param material
     * @param _radius
     * @param _center
     */
    public Sphere(Color emission, Material material, double _radius, Point3D _center) {
        super(emission, material, _radius);
        this._center = _center;
    }

    /**
     * constructor
     *
     * @param emission
     * @param _radius
     * @param _center
     */
    public Sphere(Color emission, double _radius, Point3D _center) {
        this(emission, new Material(0, 0, 0), _radius, _center);
    }

    /**
     * builds Sphere with 3D center point and radius
     *
     * @param _center The center point of the sphere
     * @param _radius sphere's radius
     */
    public Sphere(Point3D _center, double _radius) {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _center);
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
    public List<GeoPoint> findIntersections(Ray ray) {
        if (ray.getStart().equals(_center)) {
            List<GeoPoint> intersections = new ArrayList();
            intersections.add(new GeoPoint(this, ray.getStart().add(ray.getDirection().scale(getRadius()))));
            return intersections;
        }
        Vector v = _center.subtract(ray.getStart());
        double tm = ray.getDirection().dotProduct(v);
        double d = Math.sqrt(v.lengthSquared() - tm * tm);
        if (d >= getRadius())
            return null;
        double th = Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));
        double t1 = tm + th;
        double t2 = tm - th;

        if (t1 <= 0 && t2 <= 0)
            return null;
        List<GeoPoint> intersections = new ArrayList();
        if (t1 > 0)
            intersections.add(new GeoPoint(this, ray.getIntersectionPoint(t1)));
        if (t2 > 0 && t2 != t1)
            intersections.add(new GeoPoint(this, ray.getIntersectionPoint(t2)));
        return intersections;
    }
}
