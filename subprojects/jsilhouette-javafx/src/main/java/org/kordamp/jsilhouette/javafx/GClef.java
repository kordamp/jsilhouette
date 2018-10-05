package org.kordamp.jsilhouette.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 *
 * @author ced04u
 */
public class GClef extends AbstractSilhouette {

    private DoubleProperty width;
    private DoubleProperty height;

    public GClef(double width, double height) {
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

        SVGPath s = new SVGPath();
        s.setContent("M165.156,176.583c-16.007,20.073-24.81,40.457-26.128,60.599c-1.475,22.705,5.003,44.281,19.273,64.099\n"
                + " c15.118,21.119,34.22,32.572,56.759,34.047c7.574,0.497,14.7,0.288,21.276-0.597c1.35,7.649,2.709,15.252,3.971,22.781\n"
                + " c1.567,10.176,2.124,19.008,1.644,26.241c-0.661,9.998-3.779,18.178-9.294,24.321c-5.963,6.628-13.396,9.533-22.826,8.92\n"
                + " c-4.105-0.269-7.722-1.326-10.904-3.201c3.745-0.862,7.145-2.469,10.119-4.785c5.761-4.469,8.989-10.965,9.521-19.28\n"
                + " c0.503-7.999-1.529-14.995-6.067-20.778c-4.827-6.332-11.389-9.818-19.5-10.355c-8.283-0.545-15.797,2.806-21.957,9.807\n"
                + " c-5.474,6.199-8.54,13.493-9.061,21.68c-0.797,12.227,3.847,22.67,13.85,31.026c8.771,7.321,19.272,11.421,31.22,12.202\n"
                + " c6.792,0.445,13.354-0.337,19.466-2.328c5.021-1.635,9.842-4.12,14.366-7.37c10.94-7.878,16.88-18.182,17.692-30.656\n"
                + " c0.63-9.506,0.148-20.767-1.454-33.603l-4.464-28.918c11.425-3.814,21.007-10.707,28.473-20.462\n"
                + " c7.926-10.303,12.391-22.47,13.296-36.167c1.103-16.823-2.913-32.079-11.962-45.34c-9.918-14.543-23.52-22.478-40.406-23.582\n"
                + " c-1.948-0.126-3.988-0.112-6.12,0.046l-4.744-35.516c13.168-11.257,23.832-25.409,31.723-42.092\n"
                + " c7.798-16.574,12.383-34.514,13.608-53.305c0.718-11.046-0.797-24.754-4.508-40.761c-5.086-21.718-12.335-32.586-22.201-33.238\n"
                + " c-3.49-0.218-7.33,1.557-12.015,5.677c-11.377,10.285-19.656,22.728-24.609,36.96c-3.833,10.87-6.368,25.403-7.548,43.208\n"
                + " c-0.579,8.869,0.826,23.942,4.328,46.059C189.015,150.517,173.957,165.542,165.156,176.583z M263.455,274.041\n"
                + " c0,11.63-5.743,31.162-13.754,35.931l-8.264-62.556C253.949,249.816,263.455,260.833,263.455,274.041z M222.484,104.798\n"
                + " c0.739-11.339,3.589-23.646,8.478-36.562c6.196-16.16,12.071-21.043,15.895-22.293c1.05-0.336,2.079-0.471,3.157-0.399\n"
                + " c5.001,0.331,9.982,2.447,9.125,15.659c-0.722,11.197-5.17,23.064-13.188,35.281c-6.452,9.851-13.95,18.074-22.31,24.501\n"
                + " C222.471,116.385,222.072,110.961,222.484,104.798z M216.199,181.34l3.266,26.908c-9.077,3.75-16.99,9.682-23.568,17.665\n"
                + " c-7.578,9.289-11.79,19.532-12.499,30.429c-0.735,11.305,1.797,21.664,7.514,30.741c2.974,4.837,7.063,9.137,11.76,12.643\n"
                + " c7.7,5.727,16.575,5.294,17.985,2.177c1.429-3.106-3.845-7.991-7.993-14.515c-2.36-3.688-3.506-8.123-3.506-13.337\n"
                + " c0-11.073,6.672-20.578,16.186-24.802l8.909,69.388c-4.745,0.982-10.055,1.295-15.824,0.922\n"
                + " c-14.815-0.974-28.044-7.069-39.347-18.153c-11.806-11.51-17.1-24.197-16.149-38.78\n"
                + " C164.754,235.014,182.669,207.692,216.199,181.34z");
        //see https://stackoverflow.com/questions/48263331/manipulating-an-svgpath-in-javafx        
        s.setScaleX(width.doubleValue() / 433.43);
        s.setScaleY(height.doubleValue() / 433.43);
        Path path = (Path) (Shape.subtract(s, new Rectangle(0, 0)));        
        setShape(path);        
//        path.setScaleX(width.doubleValue() / 433.43);
//        path.setScaleY(height.doubleValue() / 433.43);
        path.getStyleClass().addAll("silhouette", "silhoutte-clef");

    }

}
