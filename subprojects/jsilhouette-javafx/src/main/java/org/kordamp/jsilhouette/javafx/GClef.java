package org.kordamp.jsilhouette.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 *
 * @author ced04u
 */
public class GClef extends AbstractSilhouette {

    private Region region = new Region();

    public GClef(double width, double height) {
        initializing = true;

        region.minWidthProperty().addListener(updateListener);
        region.maxWidthProperty().addListener(updateListener);
        region.prefWidthProperty().addListener(updateListener);
        region.minHeightProperty().addListener(updateListener);
        region.maxHeightProperty().addListener(updateListener);
        region.prefHeightProperty().addListener(updateListener);
        setWidth(width);
        setHeight(height);

        fillProperty().addListener(updateListener);

        initializing = false;
        calculateShape();
    }

    public double getHeight() {
        return region.heightProperty().get();
    }

    public void setHeight(double height) {
        region.minHeightProperty().set(height);
        region.maxHeightProperty().set(height);
        region.prefHeightProperty().set(height);
    }

    public double getWidth() {
        return region.widthProperty().get();
    }

    public void setWidth(double width) {
        region.minWidthProperty().set(width);
        region.maxWidthProperty().set(width);
        region.prefWidthProperty().set(width);
    }

    @Override
    protected void calculateShape() {

        SVGPath s = new SVGPath();
        s.setContent("M 39.708934,63.678683 C 39.317094,65.77065 41.499606,70.115061 45.890584,70.256984 C 51.19892,70.428558 54.590321,66.367906 53.010333,59.740875 L 45.086538,23.171517 C 44.143281,18.81826 44.851281,16.457097 45.354941,15.049945 C 46.698676,11.295749 50.055822,9.7473042 50.873134,10.949208 C 51.339763,11.635413 52.468042,14.844006 49.256275,20.590821 C 46.751378,25.072835 35.096985,30.950138 34.2417,41.468011 C 33.501282,50.614249 43.075689,57.369301 51.339266,54.71374 C 56.825686,52.950639 59.653965,44.62402 56.258057,40.328987 C 47.29624,28.994371 32.923702,46.341263 46.846564,51.0935 C 45.332604,49.90238 44.300646,48.980054 44.1085,47.852721 C 42.237755,36.876941 58.741182,39.774741 54.294493,50.18735 C 52.466001,54.469045 45.080341,55.297323 40.874269,51.477433 C 37.350853,48.277521 35.787387,42.113231 39.708327,37.687888 C 45.018831,31.694223 51.288782,26.31366 52.954064,18.108736 C 54.923313,8.4061491 48.493821,0.84188926 44.429027,10.385835 C 43.065093,13.588288 42.557016,16.803074 43.863006,22.963534 L 51.780549,60.311215 C 52.347386,62.985028 51.967911,66.664419 49.472374,68.355474 C 48.236187,69.193154 43.861784,69.769668 42.791575,67.770092");
        setShape(s);
        region.setBackground(
                new Background(
                        new BackgroundFill(
                                fillProperty().get(), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    @Override
    public ObjectProperty<Shape> shapeProperty() {
        return region.shapeProperty();
    }

    public Node getNode() {
        return region;
    }

    @Override
    public StringProperty styleProperty() {
        return region.styleProperty();
    }

}
