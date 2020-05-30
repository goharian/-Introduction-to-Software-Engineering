package elements;

import primitives.Color;

/**
 * class describing ambient lighting on a scene
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class AmbientLight {
    private Color _intensity;//ambient light intensity Ka*Ia

    /**
     *
     * @param intensity intensity of light
     * @param kA color fading
     */
    public AmbientLight(Color intensity, double kA) {
        this._intensity = intensity.scale(kA);
    }

    /**
     *
     * @return intensity of light
     */
    public Color getIntensity() {
        return new Color(_intensity);
    }
}