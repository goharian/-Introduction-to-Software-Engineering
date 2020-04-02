package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Geometric plane in 3D cartesian coordinates
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Plane implements Geometry
{

    private Point3D _point;
    private Vector _normal;

    /**
     * builds plane with 3D point and normal vector
     *
     * @param point point on the plane
     * @param normal Normal vector perpendicular to the plane
     */
    public Plane(Point3D point, Vector normal)
    {
        this._normal = new Vector(normal);
        this._point = new Point3D(point);
    }

    /**
     * builds plane with 3 point
     *
     * @param vertice1 first point on the plane
     * @param vertice2 second point on the plane
     * @param vertice3 third point on the plane
     */
    Plane(Point3D vertice1, Point3D vertice2, Point3D vertice3)
    {
        _point = new Point3D(vertice1);
        _normal = null;
    }

    /**
     * @param point Point across the plane
     * @return Normal vector perpendicular to the plane
     */
    @Override
    public Vector getNormal(Point3D point)
    {
        return null;
    }

    /**
     * @return Normal vector perpendicular to the plane
     */
    Vector getNormal()
    {
        return new Vector(_normal);
    }

    /**
     * @return The point on the plane as a new point
     */
    public Point3D getPoint()
    {
        return _point;
    }

    /**
     * @return A string representing the specified plane
     */
    @Override
    public String toString()
    {
        return "point=" + _point.toString() + ", normal=" + _normal.toString();
    }
}
