package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        Vector v = new Vector(point);
        Point3D p = new Point3D(point);
        double t = v.dotProduct(new Vector(p));
        Point3D o = new Point3D(v.scale(t).getHead());
        return (p.subtract(o)).normalize();
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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
