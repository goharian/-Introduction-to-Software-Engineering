package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Shphere in a 3D Cartesian coordinate system
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Sphere extends RadialGeometry
{

    private final Point3D _center;

    /**
     * builds Sphere with 3D center point and radius
     *
     * @param _center The center point of the sphere
     * @param _radius sphere's radius
     */
    public Sphere(Point3D _center, double _radius)
    {
        super(_radius);
        this._center = _center;
    }

    /**
     * @param point Point across the sphere
     * @return Normal vector perpendicular to the sphere
     */
    @Override
    public Vector getNormal(Point3D point)
    {
        return null;
    }

    /**
     * @return The center of the sphere as a new point
     */
    public Point3D getCenter()
    {
        return new Point3D(_center);
    }

    /**
     * @return A string representing the specified sphere
     */
    @Override
    public String toString()
    {
        return "center=" + _center.toString();
    }
}
