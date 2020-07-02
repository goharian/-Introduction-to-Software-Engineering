package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Geometric plane in 3D cartesian coordinates
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Plane extends Geometry {
    private Point3D _point;
    private Vector _normal;

    /**
     * constructor
     *
     * @param emission
     * @param material
     * @param _point
     * @param _normal
     */
    public Plane(Color emission, Material material, Point3D _point, Vector _normal) {
        super(emission, material);
        this._point = _point;
        this._normal = _normal.normalize();
    }

    /**
     * constructor
     *
     * @param emission
     * @param _point
     * @param _normal
     */
    public Plane(Color emission, Point3D _point, Vector _normal) {
        this(emission, new Material(0, 0, 0), _point, _normal);
    }

    /**
     * builds plane with 3D point and normal vector
     *
     * @param _point  point on the plane
     * @param _normal Normal vector perpendicular to the plane
     */
    public Plane(Point3D _point, Vector _normal) {
        this(Color.BLACK, new Material(0, 0, 0), _point, _normal);
    }


    /**
     * builds plane with 3 point
     *
     * @param _point1 first point on the plane
     * @param _point2 second point on the plane
     * @param _point3 third point on the plane
     */
    public Plane(Point3D _point1, Point3D _point2, Point3D _point3) {
        //Vector v1 = _point2.subtract(_point1);
        //Vector v2 = _point3.subtract(_point1);
        this(Color.BLACK, new Material(0, 0, 0), _point1, _point2.subtract(_point1).crossProduct(_point3.subtract(_point1)));
    }

    /**
     * parameter constructors
     *
     * @param emission
     * @param material
     * @param _point1
     * @param _point2
     * @param _point3  calculate the normal to the plane
     */
    public Plane(Color emission, Material material, Point3D _point1, Point3D _point2, Point3D _point3) {
        this(emission, material, _point1, _point2.subtract(_point1).crossProduct(_point3.subtract(_point1)));
    }

    /**
     * parameter constructors
     *
     * @param emission
     * @param _point1
     * @param _point2
     * @param _point3  calculate the normal to the plane
     */
    public Plane(Color emission, Point3D _point1, Point3D _point2, Point3D _point3) {
        this(emission, new Material(0, 0, 0), _point1, _point2.subtract(_point1).crossProduct(_point3.subtract(_point1)));
    }

    /**
     * @param point Point across the plane
     * @return Normal vector perpendicular to the plane
     */
    @Override
    public Vector getNormal(Point3D point) {

        return get_normal();
    }

    /**
     * @return Normal vector perpendicular to the plane
     */
    public Vector get_normal() {
        return _normal;
    }

    /**
     * @return The point on the plane as a new point
     */
    public Point3D get_point() {
        return _point;
    }


    /**
     * @param ray ray to check intersection with
     * @return returns intersection point.
     * if ray doesn't intersect or ray's head is on the plane returns null
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        if (_point.equals(ray.getStart()))
            return null;
        double t = alignZero(_normal.dotProduct(_point.subtract(ray.getStart())) / _normal.dotProduct(ray.getDirection()));
        if (t <= 0)
            return null;
        List<GeoPoint> intersections = new ArrayList();
        intersections.add(new GeoPoint(this, ray.getIntersectionPoint(t)));
        return intersections;
    }

    /**
     * @return A string representing the specified plane
     */
    @Override
    public String toString() {
        return "point=" + _point.toString() + ", normal=" + _normal.toString();
    }
}
