package geometries;

import primitives.*;

import java.util.List;

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
     * constructor
     * @param emission
     * @param material
     * @param _radius
     * @param _ray
     * @param _height
     */
    public Cylinder(Color emission, Material material, double _radius, Ray _ray, double _height) {
        super(emission, material, _radius, _ray);
        this._height = _height;
    }

    /**
     * constructor
     * @param emission
     * @param _radius
     * @param _ray
     * @param _height
     */
    public Cylinder(Color emission, double _radius, Ray _ray, double _height) {
        this(emission, new Material(0,0,0), _radius, _ray, _height);
    }

    /**
     * builds cylinder with height, ray and radius
     *
     * @param _height cylinder's height
     * @param _ray cylinder's ray
     * @param _radius cylinder's radius
     */
    public Cylinder(double _radius, Ray _ray, double _height) {
        this(Color.BLACK, new Material(0,0,0), _radius, _ray, _height);
    }

    /**
     * @param p Point across the cylinder
     * @return Normal vector perpendicular to the cylinder
     */
    @Override
    public Vector getNormal(Point3D p)
    {
        Plane plane = new Plane(_axisRay.getStart(), _axisRay.getDirection());
        Vector v1 = _axisRay.getStart().subtract(p);
        if((v1.dotProduct(_axisRay.getDirection()))==0) //the vectors are orthogonal
            return (_axisRay.getDirection().scale(-1)).normalize();
        Point3D p1 = _axisRay.getStart().add(_axisRay.getDirection().normalized().scale(_height));
        v1 = p1.subtract(p);
        if((v1.dotProduct(_axisRay.getDirection()))==0) //the vectors are orthogonal
            return (_axisRay.getDirection()).normalize();
        Vector v = p.subtract((_axisRay.getStart()));
        double t = _axisRay.getDirection().dotProduct(v);
        Point3D o = _axisRay.getStart().add(_axisRay.getDirection().scale(t));
        Vector n = (p.subtract(o)).normalize();
        return n;
    }
    /**
     * @return The height of the cylinder
     */
    public double getHeight()
    {
        return _height;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
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
