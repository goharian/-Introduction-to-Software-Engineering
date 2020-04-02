package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * tube in a 3D Cartesian coordinate wth a radius and ray
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Tube extends RadialGeometry
{

    Ray _axisRay;

    /**
     * builds tube with radius and ray
     *
     * @param axisRay tube's ray
     * @param radius tube's radius
     */
    public Tube(Ray axisRay, double radius)
    {
        super(radius);
        this._axisRay = axisRay;
    }

    /**
     * @param point Point across the plane
     * @return Normal vector perpendicular to the tube
     */
    @Override
    public Vector getNormal(Point3D point)
    {
        return null;
    }

    /**
     * @return Ray of the tube
     */
    public Ray getAxisRay()
    {
        return _axisRay;
    }

    /**
     * @return A string representing the specified tube
     */
    @Override
    public String toString()
    {
        return "Tube{" + "_axisRay=" + _axisRay + '}';
    }
}
