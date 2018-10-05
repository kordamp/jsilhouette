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
public class FClef extends AbstractSilhouette {

    private DoubleProperty width;
    private DoubleProperty height;

    public FClef(double width, double height) {
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
        s1.setContent("M 248.25999,536.80200 C 248.26766,537.17138 248.11044,537.54065 247.82878,537.78185 C 247.46853,538.11076 246.91933,538.17813 246.47048,538.01071 C 246.02563,537.83894 245.69678,537.39883 245.67145,536.92060 C 245.63767,536.54689 245.75685,536.15479 246.02747,535.88867 C 246.28257,535.61680 246.66244,535.48397 247.03147,535.50645 C 247.41131,535.51452 247.77805,535.70601 248.00489,536.01019 C 248.17962,536.23452 248.26238,536.51954 248.25999,536.80200 z ");

        SVGPath s2 = new SVGPath();
        s2.setContent("M 248.25999,542.64502 C 248.26772,543.01469 248.11076,543.38446 247.82878,543.62585 C 247.46853,543.95476 246.91933,544.02213 246.47048,543.85472 C 246.02537,543.68288 245.69655,543.24237 245.67145,542.76389 C 245.63651,542.38990 245.76354,542.00308 246.02700,541.73300 C 246.27663,541.45454 246.66060,541.32790 247.02845,541.34950 C 247.51230,541.36282 247.95159,541.69251 248.15162,542.12465 C 248.22565,542.28740 248.26043,542.46657 248.25999,542.64502 z ");
//        s2.setLayoutX(-1.8);
//        s2.setLayoutY(-1.4);

        SVGPath s3 = new SVGPath();
        s3.setContent("M 243.97900,540.86798 C 244.02398,543.69258 242.76360,546.43815 240.76469,548.40449 C 238.27527,550.89277 235.01791,552.47534 231.69762,553.53261 C 231.25590,553.77182 230.58970,553.45643 231.28550,553.13144 C 232.62346,552.52289 234.01319,552.00050 235.24564,551.18080 C 237.96799,549.49750 240.26523,546.84674 240.82279,543.61854 C 241.14771,541.65352 241.05724,539.60795 240.56484,537.67852 C 240.20352,536.25993 239.22033,534.79550 237.66352,534.58587 C 236.25068,534.36961 234.74885,534.85905 233.74057,535.88093 C 233.47541,536.14967 232.95916,536.89403 233.04435,537.74747 C 233.64637,537.27468 233.60528,537.32732 234.09900,537.10717 C 235.23573,536.60031 236.74349,537.32105 237.02700,538.57272 C 237.32909,539.72295 237.09551,541.18638 235.96036,541.79960 C 234.77512,542.44413 233.02612,542.17738 232.36450,540.90866 C 231.26916,538.95418 231.87147,536.28193 233.64202,534.92571 C 235.44514,533.42924 238.07609,533.37089 240.19963,534.13862 C 242.38419,534.95111 243.68629,537.21483 243.89691,539.45694 C 243.95419,539.92492 243.97896,540.39668 243.97900,540.86798 z ");

        //see https://stackoverflow.com/questions/48263331/manipulating-an-svgpath-in-javafx
//        Path path = (Path) (Shape.subtract(s, new Rectangle(0, 0)));
        Path path = (Path) Shape.union(s3, Shape.union(s1, s2));
        setShape(path);
        //and https://stackoverflow.com/questions/38953921/how-to-set-the-size-of-a-svgpath-in-javafx
        path.setScaleX(width.doubleValue() / 18);
        path.setScaleY(height.doubleValue() / 20);
        path.getStyleClass().addAll("silhouette", "silhoutte-clef");

    }

}
