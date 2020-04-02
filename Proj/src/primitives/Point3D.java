package primitives;

/**
 * A point with 3 coordinates.
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class Point3D
{

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Coordinate x;
    private Coordinate y;
    private Coordinate z;
    public final static Point3D ZERO = new Point3D(0, 0, 0);
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ctor">
    /**
     * builds Point3D with three coordinates
     *
     * @param x coordinate on x axel
     * @param y coordinate on y axel
     * @param z coordinate on z axel
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z)
    {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     * builds a Point3D with the coordinates
     *
     * @param x point on x axel
     * @param y point on y axel
     * @param z point on z axel
     */
    public Point3D(double x, double y, double z)
    {
        this(new Coordinate(x), new Coordinate(y), new Coordinate(z));
    }

    /**
     * builds a copy of the resulting Point3D
     *
     * @param p Point3D to copy
     */
    public Point3D(Point3D p)
    {
        x = p.getX();
        y = p.getY();
        z = p.getZ();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getter">
    public Coordinate getX()
    {
        return new Coordinate(x);
    }

    public Coordinate getY()
    {
        return new Coordinate(y);
    }

    public Coordinate getZ()
    {
        return new Coordinate(z);
    }
    // </editor-fold>

    /**
     * Vector subtraction
     *
     * @param point Second point
     * @return A vector from the second point to the point at which the action
     * is performed
     */
    public Vector subtract(Point3D point)
    {
        return new Vector(
                x.get() - point.x.get(),
                y.get() - point.y.get(),
                z.get() - point.z.get());
    }

    /**
     * Adding a vector to a point
     *
     * @param v Vector to add
     * @return A new point
     */
    public Point3D add(Vector v)
    {
        return new Point3D(
                x.get() + v.getHead().x.get(),
                y.get() + v.getHead().y.get(),
                z.get() + v.getHead().z.get());
    }

    /**
     * Square distance between 2 points
     *
     * @param point Second point for calculating distance
     * @return The distance between two points squared
     */
    public double distanceSquared(Point3D point)
    {
        return (this.x.get() - point.x.get()) * (this.x.get() - point.x.get())
                + (this.y.get() - point.y.get()) * (this.y.get() - point.y.get())
                + (this.z.get() - point.z.get()) * (this.z.get() - point.z.get());
    }

    /**
     * distance between 2 points
     *
     * @param point Second point for calculating distance
     * @return The distance between two points
     */
    public double distance(Point3D point)
    {
        return Util.alignZero(Math.sqrt(distanceSquared(point)));
    }

    /**
     * Checks whether the two points are equal.
     *
     * @param obj to check
     * @return true if they are equal, else false
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (!(obj instanceof Point3D))
        {
            return false;
        }

        final Point3D other = (Point3D) obj;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }

    /**
     * @return A string representing the specified Point3D object
     */
    @Override
    public String toString()
    {
        return "x=" + x.toString() + ", y=" + y.toString() + ", z=" + z.toString();
    }

}
