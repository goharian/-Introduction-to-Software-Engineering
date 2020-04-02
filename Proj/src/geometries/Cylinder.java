package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * cylinder in 3D Cartesian coordinate
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Cylinder extends Tube
{

    private double _height;

    /**
     * builds cylinder with height, ray and radius
     *
     * @param height cylinder's height
     * @param axisRay cylinder's ray
     * @param radius cylinder's radius
     */
    public Cylinder(double height, Ray axisRay, double radius)
    {
        super(axisRay, radius);
        this._height = height;
    }

    /**
     * @param point Point across the cylinder
     * @return Normal vector perpendicular to the cylinder
     */
    @Override
    public Vector getNormal(Point3D point)
    {
        return null;
    }

    /**
     * @return The height of the cylinder
     */
    public double getHeight()
    {
        return _height;
    }

    /**
     * @return A string representing the specified cylinder
     */
    @Override
    public String toString()
    {
        return "Cylinder{" + "_height=" + _height + '}';
    }
}
