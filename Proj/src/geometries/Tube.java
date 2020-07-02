package geometries;

import primitives.*;

import java.util.List;

/**
 * tube in a 3D Cartesian coordinate wth a radius and ray
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Tube extends RadialGeometry {

    Ray _axisRay;

    /**
     * constructor
     *
     * @param emission
     * @param material
     * @param _radius
     * @param _ray
     */
    public Tube(Color emission, Material material, double _radius, Ray _ray) {
        super(emission, material, _radius);
        this._axisRay = _ray;
    }

    /**
     * constructor
     *
     * @param emission
     * @param _radius
     * @param _ray
     */
    public Tube(Color emission, double _radius, Ray _ray) {

        this(emission, new Material(0, 0, 0), _radius, _ray);
    }

    /**
     * parameter constructor
     *
     * @param _radius
     * @param _ray
     */
    public Tube(double _radius, Ray _ray) {

        this(Color.BLACK, new Material(0, 0, 0), _radius, _ray);
    }



    /**
     * builds tube with radius and ray
     *
     * @param axisRay tube's ray
     * @param radius  tube's radius
     */
    public Tube(Ray axisRay, double radius) {
        this(Color.BLACK, new Material(0,0,0), radius, axisRay);
    }

    /**
     * copy constructor
     *
     * @param _radialGeometry to copy
     */
    public Tube(RadialGeometry _radialGeometry, Ray _ray) {
        super(_radialGeometry);
        this._axisRay = _ray;
    }


    /**
     * @param point Point across the plane
     * @return Normal vector perpendicular to the tube
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector v = new Vector(point);
        Point3D p = new Point3D(point);
        double t = v.dotProduct(new Vector(p));
        Point3D o = new Point3D(v.scale(t).getHead());
        return (p.subtract(o)).normalize();
    }

    /**
     * @return Ray of the tube
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     * @return A string representing the specified tube
     */
    @Override
    public String toString() {
        return "Tube{" + "_axisRay=" + _axisRay + '}';
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}
