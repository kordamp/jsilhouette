/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andres Almiray
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.jsilhouette.generator;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.jsilhouette.javafx.Almond;
import org.kordamp.jsilhouette.javafx.Arrow;
import org.kordamp.jsilhouette.javafx.Asterisk;
import org.kordamp.jsilhouette.javafx.Astroid;
import org.kordamp.jsilhouette.javafx.Cross;
import org.kordamp.jsilhouette.javafx.Donut;
import org.kordamp.jsilhouette.javafx.Lauburu;
import org.kordamp.jsilhouette.javafx.MultiRoundRectangle;
import org.kordamp.jsilhouette.javafx.Rays;
import org.kordamp.jsilhouette.javafx.RegularPolygon;
import org.kordamp.jsilhouette.javafx.RoundPin;
import org.kordamp.jsilhouette.javafx.Star;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Andres Almiray
 */
public class Generator extends Application {
    public static void main(String[] args) {
        launch(Generator.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Image Generator");
        primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> generateImages(primaryStage));
        primaryStage.show();
    }

    private void generateImages(Stage stage) {
        Map<String, Pane> nodes = new LinkedHashMap<>();
        nodes.put("almond", almond(stage));
        nodes.put("arrow", arrow(stage));
        nodes.put("asterisk", asterisk(stage));
        nodes.put("astroid", astroid(stage));
        nodes.put("cross", cross(stage));
        nodes.put("donut", donut(stage));
        nodes.put("lauburu", lauburu(stage));
        nodes.put("multiround_rectangle", multiround_rectangle(stage));
        nodes.put("rays", rays(stage));
        nodes.put("regular_polygon", regular_polygon(stage));
        nodes.put("roundpin", roundpin(stage));
        nodes.put("star", star(stage));

        nodes.forEach((name, grid) -> {
            Scene scene = new Scene(grid);
            scene.setFill(Color.WHITE);
            scene.getStylesheets().add("org/kordamp/jsilhouette/generator.css");
            stage.setScene(scene);

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            WritableImage snapshot = grid.snapshot(params, null);

            File projectBuildDir = new File(System.getProperty("user.dir"), "build/images");
            projectBuildDir.mkdirs();
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(projectBuildDir, "shape_" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.exit(0);
    }

    private GridPane almond(Stage stage) {
        stage.setTitle("Almond");
        GridPane grid = grid();
        grid.add(new Almond(50, 50, 50).getShape(), 0, 0);
        return grid;
    }

    private GridPane arrow(Stage stage) {
        stage.setTitle("Arrow");
        GridPane grid = grid();
        grid.add(new Arrow(0, 0, 100, 100).getShape(), 0, 0);
        grid.add(new Arrow(0, 0, 100, 100, 0.1, 0.5).getShape(), 1, 0);
        grid.add(new Arrow(0, 0, 100, 100, 0.5, 0.2).getShape(), 2, 0);
        return grid;
    }

    private GridPane asterisk(Stage stage) {
        stage.setTitle("Asterisk");
        GridPane grid = grid();
        grid.add(new Asterisk(50, 50, 50, 20, 2, 0).getShape(), 0, 0);
        grid.add(new Asterisk(50, 50, 50, 20, 3, 0).getShape(), 1, 0);
        grid.add(new Asterisk(50, 50, 50, 20, 4, 0).getShape(), 2, 0);
        grid.add(new Asterisk(50, 50, 50, 20, 5, 0).getShape(), 3, 0);
        grid.add(new Asterisk(50, 50, 50, 20, 6, 0).getShape(), 4, 0);

        grid.add(new Asterisk(50, 50, 50, 20, 2, 1).getShape(), 0, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 3, 1).getShape(), 1, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 4, 1).getShape(), 2, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 5, 1).getShape(), 3, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 6, 1).getShape(), 4, 1);
        return grid;
    }

    private GridPane astroid(Stage stage) {
        stage.setTitle("Astroid");
        GridPane grid = grid();
        grid.add(new Astroid(50, 50, 50).getShape(), 0, 0);
        return grid;
    }

    private GridPane cross(Stage stage) {
        stage.setTitle("Cross");
        GridPane grid = grid();
        grid.add(new Cross(50, 50, 50, 20, 0).getShape(), 0, 0);
        grid.add(new Cross(50, 50, 50, 20, 0.5).getShape(), 1, 0);
        grid.add(new Cross(50, 50, 50, 20, 1).getShape(), 2, 0);
        return grid;
    }

    private GridPane donut(Stage stage) {
        stage.setTitle("Donut");
        GridPane grid = grid();
        grid.add(new Donut(50, 50, 50, 20, 0).getShape(), 0, 0);
        grid.add(new Donut(50, 50, 50, 20, 3).getShape(), 1, 0);
        grid.add(new Donut(50, 50, 50, 20, 4).getShape(), 2, 0);
        grid.add(new Donut(50, 50, 50, 20, 5).getShape(), 3, 0);
        grid.add(new Donut(50, 50, 50, 20, 6).getShape(), 4, 0);
        grid.add(new Donut(50, 50, 50, 20, 7).getShape(), 5, 0);
        return grid;
    }

    private GridPane lauburu(Stage stage) {
        stage.setTitle("Lauburu");
        GridPane grid = grid();
        grid.add(new Lauburu(50, 50, 50).getShape(), 0, 0);
        grid.add(new Lauburu(50, 50, 50, Lauburu.Direction.ANTICLOCKWISE).getShape(), 1, 0);

        grid.add(new Text("CLOCKWISE"), 0, 1);
        grid.add(new Text("ANTICLOCKWISE"), 1, 1);
        return grid;
    }

    private GridPane multiround_rectangle(Stage stage) {
        stage.setTitle("MultiRoundRectangle");
        GridPane grid = grid();
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0).getShape(), 0, 0);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 10).getShape(), 1, 0);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 10, 0, 0, 0).getShape(), 2, 0);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0, 10, 0, 0).getShape(), 3, 0);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0, 0, 10, 0).getShape(), 4, 0);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0, 0, 0, 10).getShape(), 5, 0);

        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 10, 10, 0, 0).getShape(), 0, 1);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0, 0, 10, 10).getShape(), 1, 1);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0, 10, 10, 0).getShape(), 2, 1);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 10, 0, 0, 10).getShape(), 3, 1);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 10, 0, 10, 0).getShape(), 4, 1);
        grid.add(new MultiRoundRectangle(50, 50, 50, 50, 0, 10, 0, 10).getShape(), 5, 1);

        return grid;
    }

    private GridPane rays(Stage stage) {
        stage.setTitle("Rays");
        GridPane grid = grid();

        grid.add(new Text("extent = 0.25"), 0, 0);
        grid.add(new Rays(50, 50, 50, 2, 0.25).getShape(), 1, 0);
        grid.add(new Rays(50, 50, 50, 3, 0.25).getShape(), 2, 0);
        grid.add(new Rays(50, 50, 50, 4, 0.25).getShape(), 3, 0);
        grid.add(new Rays(50, 50, 50, 5, 0.25).getShape(), 4, 0);
        grid.add(new Rays(50, 50, 50, 6, 0.25).getShape(), 5, 0);

        grid.add(new Text("extent = 0.5"), 0, 1);
        grid.add(new Rays(50, 50, 50, 2, 0.5).getShape(), 1, 1);
        grid.add(new Rays(50, 50, 50, 3, 0.5).getShape(), 2, 1);
        grid.add(new Rays(50, 50, 50, 4, 0.5).getShape(), 3, 1);
        grid.add(new Rays(50, 50, 50, 5, 0.5).getShape(), 4, 1);
        grid.add(new Rays(50, 50, 50, 6, 0.5).getShape(), 5, 1);

        grid.add(new Text("extent = 0.75"), 0, 2);
        grid.add(new Rays(50, 50, 50, 2, 0.75).getShape(), 1, 2);
        grid.add(new Rays(50, 50, 50, 3, 0.75).getShape(), 2, 2);
        grid.add(new Rays(50, 50, 50, 4, 0.75).getShape(), 3, 2);
        grid.add(new Rays(50, 50, 50, 5, 0.75).getShape(), 4, 2);
        grid.add(new Rays(50, 50, 50, 6, 0.75).getShape(), 5, 2);

        grid.add(new Text("extent = 0.25\nrounded = true"), 0, 3);
        grid.add(new Rays(50, 50, 50, 2, 0.25, true).getShape(), 1, 3);
        grid.add(new Rays(50, 50, 50, 3, 0.25, true).getShape(), 2, 3);
        grid.add(new Rays(50, 50, 50, 4, 0.25, true).getShape(), 3, 3);
        grid.add(new Rays(50, 50, 50, 5, 0.25, true).getShape(), 4, 3);
        grid.add(new Rays(50, 50, 50, 6, 0.25, true).getShape(), 5, 3);

        grid.add(new Text("extent = 0.5\nrounded = true"), 0, 4);
        grid.add(new Rays(50, 50, 50, 2, 0.5, true).getShape(), 1, 4);
        grid.add(new Rays(50, 50, 50, 3, 0.5, true).getShape(), 2, 4);
        grid.add(new Rays(50, 50, 50, 4, 0.5, true).getShape(), 3, 4);
        grid.add(new Rays(50, 50, 50, 5, 0.5, true).getShape(), 4, 4);
        grid.add(new Rays(50, 50, 50, 6, 0.5, true).getShape(), 5, 4);

        grid.add(new Text("extent = 0.75\nrounded = true"), 0, 5);
        grid.add(new Rays(50, 50, 50, 2, 0.75, true).getShape(), 1, 5);
        grid.add(new Rays(50, 50, 50, 3, 0.75, true).getShape(), 2, 5);
        grid.add(new Rays(50, 50, 50, 4, 0.75, true).getShape(), 3, 5);
        grid.add(new Rays(50, 50, 50, 5, 0.75, true).getShape(), 4, 5);
        grid.add(new Rays(50, 50, 50, 6, 0.75, true).getShape(), 5, 5);

        return grid;
    }

    private GridPane regular_polygon(Stage stage) {
        stage.setTitle("RegularPolygon");
        GridPane grid = grid();
        grid.add(new RegularPolygon(50, 50, 50, 3).getShape(), 0, 0);
        grid.add(new RegularPolygon(50, 50, 50, 4).getShape(), 1, 0);
        grid.add(new RegularPolygon(50, 50, 50, 5).getShape(), 2, 0);
        grid.add(new RegularPolygon(50, 50, 50, 6).getShape(), 3, 0);
        grid.add(new RegularPolygon(50, 50, 50, 7).getShape(), 4, 0);
        grid.add(new RegularPolygon(50, 50, 50, 8).getShape(), 5, 0);
        return grid;
    }

    private GridPane roundpin(Stage stage) {
        stage.setTitle("RoundPin");
        GridPane grid = grid();
        grid.add(new RoundPin(50, 50, 50, 100).getShape(), 0, 0);
        grid.add(new RoundPin(50, 50, 50, 70).getShape(), 1, 0);
        grid.add(new RoundPin(50, 50, 50, 40).getShape(), 2, 0);
        return grid;
    }

    private GridPane star(Stage stage) {
        stage.setTitle("Star");
        GridPane grid = grid();
        grid.add(new Star(50, 50, 50, 20, 2).getShape(), 0, 0);
        grid.add(new Star(50, 50, 50, 20, 3).getShape(), 1, 0);
        grid.add(new Star(50, 50, 50, 20, 4).getShape(), 2, 0);
        grid.add(new Star(50, 50, 50, 20, 5).getShape(), 3, 0);
        grid.add(new Star(50, 50, 50, 20, 6).getShape(), 4, 0);
        grid.add(new Star(50, 50, 50, 20, 7).getShape(), 5, 0);
        return grid;
    }

    private GridPane grid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setHgap(20);
        grid.setVgap(20);
        return grid;
    }
}