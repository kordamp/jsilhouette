package org.kordamp.jsilhouette.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 *
 * @author ced04u
 */
public class CClef extends AbstractSilhouette {

    private DoubleProperty width;
    private DoubleProperty height;

    public CClef(double width, double height) {
        initializing = true;
        setWidth(width);
        setHeight(height);
        initializing = false;
        calculateShape();
    }

    public double getHeight() {
        return heightProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty heightProperty() {
        if (height == null) {
            height = new SimpleDoubleProperty(this, "height", 0);
            height.addListener(updateListener);
        }
        return height;
    }

    public void setHeight(double height) {
        heightProperty().set(height);
    }

    public double getWidth() {
        return widthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty widthProperty() {
        if (width == null) {
            width = new SimpleDoubleProperty(this, "width", 0);
            width.addListener(updateListener);
        }
        return width;
    }

    public void setWidth(double width) {
        widthProperty().set(width);
    }

    @Override
    protected void calculateShape() {

        SVGPath s1 = new SVGPath();
        s1.setContent("M 325.90302,546.76398 C 325.77565,548.73599 324.67385,550.74769 322.76364,551.48291 C 321.1258,552.0885 319.0617,552.18049 317.638,551.0277 C 316.57277,550.23951 316.16568,548.44596 317.27983,547.51996 C 318.34407,546.51189 320.39149,547.42114 320.26891,548.90426 C 320.68605,549.92222 318.52287,550.54748 319.66017,551.2611 C 320.96855,551.60188 322.30255,550.53315 322.63842,549.29104 C 323.1266,547.50862 323.10071,545.58499 322.67301,543.79274 C 322.44295,542.90899 321.89729,541.53645 320.73027,541.89426 C 319.20285,542.2489 318.34585,543.75774 317.865,545.13 C 317.58677,543.18531 316.36388,541.50362 314.958,540.192 C 314.958,544.09834 314.95801,548.00467 314.95801,551.91101 L 314.08301,551.91554 C 314.08301,543.88753 314.08301,535.6966 314.08301,527.66859 L 314.95801,527.66785 C 314.95801,531.51886 314.95801,535.529 314.95801,539.38 C 316.35227,538.08767 317.58034,536.43374 317.844,534.505 C 318.34775,535.94355 319.32246,537.53197 320.97,537.755 C 322.1809,537.82161 322.52686,536.34503 322.76844,535.42539 C 323.08591,533.68738 323.12057,531.8389 322.56968,530.14452 C 322.17561,528.95315 320.88042,527.97772 319.60389,528.29761 C 318.57267,529.04912 320.60708,529.59275 320.26318,530.5613 C 320.48284,531.95228 318.76106,533.04005 317.57439,532.3192 C 316.28904,531.61505 316.38273,529.72095 317.37337,528.80541 C 318.04771,528.10885 319.33076,527.65914 320.27066,527.66651 C 321.22932,527.67403 321.7886,527.74487 322.64672,528.04424 C 324.56799,528.72938 325.72882,530.70423 325.88682,532.67197 C 326.17716,534.90335 324.92877,537.33821 322.82569,538.21926 C 321.45806,538.74967 319.87609,538.29068 318.827,537.317 C 318.65552,538.29407 318.22016,539.2263 317.68074,539.9006 C 318.11632,540.61049 318.58743,541.75295 318.82791,542.14434 C 320.14125,540.94807 322.46143,540.80379 323.82848,541.98977 C 325.22049,543.13189 326.08868,544.95009 325.90302,546.76398 z ");

        SVGPath s2 = new SVGPath();
        s2.setContent("M 312.208,551.911 L 309.364,551.911 L 309.364,527.661 L 312.208,527.661 L 312.208,551.911 z ");
//        s2.setLayoutX(-1.8);
//        s2.setLayoutY(-1.4);

        //see https://stackoverflow.com/questions/48263331/manipulating-an-svgpath-in-javafx
//        Path path = (Path) (Shape.subtract(s, new Rectangle(0, 0)));
        Path path = (Path) Shape.union(s1, s2);
        setShape(path);
        //and https://stackoverflow.com/questions/38953921/how-to-set-the-size-of-a-svgpath-in-javafx
        path.setScaleX(width.doubleValue() / 18);
        path.setScaleY(height.doubleValue() / 25);
        path.getStyleClass().addAll("silhouette", "silhoutte-clef");

    }

}