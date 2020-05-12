package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Document   : Camera
 * Author     : BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Camera {
    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;

    /**
     * @param p0  camera location
     * @param vUp camera coordinates in 3D Cartesian coordinate system
     * @param vTo camera coordinates in 3D Cartesian coordinate system
     * @throws IllegalArgumentException for none-orthogonal direction vectors
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        if (vUp.dotProduct(vTo) != 0) // if the two vectors aren't orthogonal
            throw new IllegalArgumentException("vectors much be orthogonal");

        this._p0 = p0;
        this._vUp = vUp.normalized(); //normalizes all vectors
        this._vTo = vTo.normalized();
        this._vRight = vTo.crossProduct(vUp);//finds orthogonal vector to both

    }

    /**
     * according to pg. 11 powerpoint 4
     * @param nX             x coordinate of pixel
     * @param nY             y coordinate of pixel
     * @param j              pixel column
     * @param i              pixel row in matrix of viewplane
     * @param screenDistance screen distance from camera
     * @param screenWidth    in pixels
     * @param screenHeight   in pixels
     * @return ray constructed from information received
     */
    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        //Ratio (pixel width & height)
        double rY = screenHeight / nY,
                rX = screenWidth / nX;

        //Image center
        Point3D Pc = _p0.add(_vTo.scale(screenDistance));//Pc = P0 + d*Vto

        //Pixel[i,j] center
        Point3D Pij = Pc;//pixel center
        double xj = (j - nX / 2.0) * rX + rX / 2.0;
        double yi = (i - nY / 2.0) * rY + rY / 2.0;
        if (xj != 0)
            Pij = Pij.add(_vRight.scale(xj));
        if (yi != 0)
            Pij = Pij.add(_vUp.scale(-yi));


        Vector Vij = Pij.subtract(_p0);
        return new Ray(Vij.normalize(), _p0);//returns p0 and normalized vij
    }

    /**
     * @return camera's location
     */
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    /**
     * @return vector up
     */
    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    /**
     * @return vector to camera
     */
    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    /**
     * @return camera right vector
     */
    public Vector get_vRight() {
        return new Vector(_vRight);
    }
}


