/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class Ray {
    private Vector direction;
    private Point3D start;
    private static final double DELTA = 0.1; //First moving rays size for shading rays, transparency and reflection


    // <editor-fold defaultstate="collapsed" desc="ctor">

    /**
     * parameter constructor with delta
     * @param head
     * @param direction
     * @param normal
     */
    public Ray(Point3D head, Vector direction, Vector normal) {
        int sign = 1;
        if (direction.dotProduct(normal) < 0)
            sign = -1;
        this.start = head.add(normal.scale(sign * DELTA));
        this.direction = direction.normalize();
    }

    /**
     * builds ray with start point and direction
     *
     * @param direction
     * @param start
     */
    public Ray(Point3D start, Vector direction) {
        if(direction.length()!=1)
            direction.normalize();
        this.direction = direction.normalized();
        this.start = start;
    }

    /**
     * Builds a copy of the resulting ray
     *
     * @param ray ray to copy
     */
    public Ray(Ray ray) {
        this.direction = ray.direction;
        this.start = ray.start;
    }
    // </editor-fold>

    /**
     * @return direction of the ray as new vector
     */
    public Vector getDirection() {
        return new Vector(direction);
    }

    /**
     * @return start point of the ray as new point
     */
    public Point3D getStart() {
        return new Point3D(start);
    }

    /**
     * @param t scale for v
     * @return point in ray
     */
    public Point3D getPoint(double t) {
        return new Point3D(start.add(direction.scale(t)));
    }

    public Point3D getIntersectionPoint(double t) {
        return (start.add(direction.scale(t)));
    }

    /**
     * Checks whether the two rays are equal.
     *
     * @param obj to check
     * @return true if they are equal, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Ray other = (Ray) obj;
        return direction.equals(other.direction) && start.equals(other.start);
    }

    /**
     * @return A string representing the specified Ray object
     */
    @Override
    public String toString() {
        return "direction=" + direction.toString() + ", start=" + start.toString();
    }
}
