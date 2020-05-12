/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import java.util.function.DoubleToIntFunction;

/**
 * A direction and size, defined by its end point (where the starting point - at
 * the origin).
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov <matanya.goharian@gmail.com >
 */
public class Vector {

    private Point3D head;

    // <editor-fold defaultstate="collapsed" desc="ctor">

    /**
     * builds vector with three coordinates
     *
     * @param x coordinate on x axel
     * @param y coordinate on y axel
     * @param z coordinate on z axel
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if (new Point3D(x, y, z).equals(Point3D.ZERO)) {
            throw new IllegalArgumentException();
        }
        head = new Point3D(x, y, z);
    }

    /**
     * builds a vector's head with the coordinates
     *
     * @param x point on x axel
     * @param y point on y axel
     * @param z point on z axel
     */
    public Vector(double x, double y, double z) {
        if (new Point3D(x, y, z).equals(Point3D.ZERO)) {
            throw new IllegalArgumentException();
        }
        head = new Point3D(new Coordinate(x), new Coordinate(y), new Coordinate(z));
    }

    /**
     * builds a vector's head with point
     *
     * @param p vector's head is set to head received
     */
    public Vector(Point3D p) {
        if (p.equals(new Point3D(Point3D.ZERO))) {
            throw new IllegalArgumentException();
        }
        head = p;
    }

    /**
     * builds a copy of the resulting vector
     *
     * @param v vector to copy
     */
    public Vector(Vector v) {
        head = new Point3D(v.getHead());
    }
    // </editor-fold>

    /**
     * @return head of vector as new point
     */
    public Point3D getHead() {
        return new Point3D(head);
    }

    /**
     * Vector subtraction
     *
     * @param v vector to subtract with the current vector
     * @return new vector that equals to (vector - v)
     */
    public Vector subtract(Vector v) {
        return new Vector(
                head.getX().get() - v.head.getX().get(),
                head.getY().get() - v.head.getY().get(),
                head.getZ().get() - v.head.getZ().get());
    }

    /**
     * add two vector
     *
     * @param v vector to add to current vector
     * @return new vector with addition of two vectors
     */
    public Vector add(Vector v) {
        return new Vector(head.add(v));
    }

    /**
     * Cross product by scalar
     *
     * @param num The scalar that doubles the vector
     * @return vector that equals to (vector * num)
     */
    public Vector scale(double num) {
        return new Vector(
                head.getX().get() * num,
                head.getY().get() * num,
                head.getZ().get() * num);
    }

    /**
     * Calculate the vector's length squared
     *
     * @return vector's length squared
     */
    public double lengthSquared() {
        return (head.getX().get()) * (head.getX().get())
                + (head.getY().get()) * (head.getY().get())
                + (head.getZ().get()) * (head.getZ().get());
    }

    /**
     * Calculate the vector's length
     *
     * @return vector's length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Vector normalization
     *
     * @return The current vector after normalisation
     */
    public Vector normalize() {
        double length = this.length();

        //normalizes vector head
        head = new Point3D(
                head.getX().get() / length,
                head.getY().get() / length,
                head.getZ().get() / length);

        return this;
    }

    /**
     * Vector normalization
     *
     * @return A copy of the current vector after normalisation
     */
    public Vector normalized() {
        return new Vector(this.normalize());
    }

    /**
     * Dot product between a two vectors.
     *
     * @param v Second vector for calculation
     * @return Result of the dot product between a two vectors.
     */
    public double dotProduct(Vector v) {
        return head.getX().get() * v.head.getX().get()
                + head.getY().get() * v.head.getY().get()
                + head.getZ().get() * v.head.getZ().get();
    }

    /**
     * Cross product between a two vectors.
     *
     * @param v Second vector for calculation
     * @return Result of the cross product between a two vectors.
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                head.getY().get() * v.head.getZ().get() - head.getZ().get() * v.head.getY().get(),
                head.getZ().get() * v.head.getX().get() - head.getX().get() * v.head.getZ().get(),
                head.getX().get() * v.head.getY().get() - head.getY().get() * v.head.getX().get());
    }

    /**
     * Checks whether the two vector are equal.
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
        final Vector other = (Vector) obj;

        return head.equals(other.head);
    }

    /**
     * @return A string representing the specified Vector object
     */
    @Override
    public String toString() {
        return "head=" + head.toString();
    }

}
