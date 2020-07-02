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
        Plane plane = new Plane(_ray.getStart(), _ray.getDirection());
        Vector v1 = _ray.getStart().subtract(p);
        if((v1.dotProduct(_ray.getDirection()))==0) //the vectors are orthogonal
            return (_ray.getDirection().scale(-1)).normalize();
        Point3D p1 = _ray.getStart().add(_ray.getDirection().normalized().scale(_height));
        v1 = p1.subtract(p);
        if((v1.dotProduct(_ray.getDirection()))==0) //the vectors are orthogonal
            return (_ray.getDirection()).normalize();
        Vector v = p.subtract((_ray.getStart()));
        double t = _ray.getDirection().dotProduct(v);
        Point3D o = _ray.getStart().add(_ray.getDirection().scale(t));
        Vector n = (p.subtract(o)).normalize();
        return n;
    }

    /**
     * @return The height of the cylinder
     */
    public double get_height() { //getter
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
