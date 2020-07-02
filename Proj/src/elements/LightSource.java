package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public interface LightSource {

    public Color getIntensity(Point3D p);

    public Vector getL(Point3D p);
}

