/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class Ray
{
    private Vector direction;
    private Point3D start;
    
    // <editor-fold defaultstate="collapsed" desc="ctor">
    /**
     * builds ray with start point and direction
     * @param direction
     * @param start 
     */
    public Ray(Vector direction, Point3D start) 
    {
        this.direction = direction.normalized();
        this.start = start;
    }
    
     /**
     * Builds a copy of the resulting ray
     * @param ray ray to copy
     */
    public Ray(Ray ray)
    {
        this.direction = ray.direction;
        this.start =  ray.start;
    }
    // </editor-fold>

    /**
     * @return direction of the ray as new vector 
     */
    public Vector getDirection()
    {
        return new Vector(direction);
    }

    /**
     * @return start point of the ray as new point 
     */
    public Point3D getStart()
    {
        return new Point3D(start);
    }

    /**
     * Checks whether the two rays are equal.
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
        if (getClass() != obj.getClass())
        {
            return false;
        }
        
        final Ray other = (Ray) obj;
        return direction.equals(other.direction) && start.equals(other.start);
    }

    /**
     * @return A string representing the specified Ray object
     */
    @Override
    public String toString()
    {
        return "direction=" + direction.toString() + ", start=" + start.toString();
    }
}
