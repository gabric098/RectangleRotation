package net.gabrieleantonini.geometric;

import net.gabrieleantonini.geometric.adapters.RectangleListHTMLAdapter;
import net.gabrieleantonini.geometric.adapters.RectangleListJSONAdapter;
import net.gabrieleantonini.geometric.geometry.RectangleReshape;
import net.gabrieleantonini.geometric.providers.HTMLProvider;
import net.gabrieleantonini.geometric.providers.JSONProvider;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class manipulates a generated list of vertical rectangles in order to provide the same
 * shape using the minimum number of horizontal rectangles.
 */
public class RectangleList implements JSONProvider, HTMLProvider {
    /**
     * minimum width for rectangle generation
     */
    final int MIN_WIDTH = 25;
    /**
     * maximum width for rectangle generation
     */
    final int MAX_WIDTH = 75;
    /**
     * minimum height for rectangle generation
     */
    final int MIN_HEIGHT = 50;
    /**
     * maximum height for rectangle generation
     */
    final int MAX_HEIGHT = 150;
    /**
     * The unprocessed list of vertical rectangles.
     */
    private ArrayList<Rectangle> inputRectList;
    /**
     * The processed list of horizontal rectangles.
     */
    private ArrayList<Rectangle> outputRectList;

    /**
     * RectangleList constructor.
     */
    public RectangleList() {
        // just initialize source and destination rectangle list
        this.inputRectList = new ArrayList<Rectangle>(15);
        this.outputRectList = new ArrayList<Rectangle>(15);
    }

    /**
     * Generates a given number of random sized rectangles.
     * @param rectNumber number of rectangles to generate
     */
    public void generateSourceList(int rectNumber) {
        int xOffset = 0;
        int y = MAX_HEIGHT;
        for (int i=0; i<rectNumber; i++) {
            Random rand = new Random();
            int width = rand.nextInt(MAX_WIDTH - MIN_WIDTH + 1) + MIN_WIDTH;
            int height = rand.nextInt(MAX_HEIGHT - MIN_HEIGHT + 1) + MIN_HEIGHT;
            int x = xOffset;
            Rectangle rect = new Rectangle(x, y, width, height);
            this.inputRectList.add(rect);
            xOffset += width;
        }
    }

    /**
     * The method performs an algorithm on the generated rectangles to re­generate the exact
     * shape with the minimum number of horizontal rectangles
     */
    public void rearrangeRectangles() {
        RectangleReshape rr = new RectangleReshape(this.inputRectList);
        this.outputRectList = rr.process(MAX_HEIGHT);
    }

    /**
     * Generate a JSON representation of the rectangle lists.
     * @return The JSON content
     */
    @Override
    public String getJSONData() {
        RectangleListJSONAdapter adpt = new RectangleListJSONAdapter();
        return adpt.adaptToJSON(this.inputRectList, this.outputRectList);
    }

    /**
     * Generate an SVG representation of the rectangle lists.
     * Just for debug purpose.
     * @return The HTML with the SVG content
     */
    @Override
    public String getHTMLData() {
        RectangleListHTMLAdapter adpt = new RectangleListHTMLAdapter();
        return adpt.adaptToHTML(this.inputRectList, this.outputRectList);
    }

    /**
     * Getter for inputRectList
     * @return
     */
    ArrayList<Rectangle> getInputRectList() {
        return inputRectList;
    }

    /**
     * Getter for outputRectList property
     * @return
     */
    ArrayList<Rectangle> getOutputRectList() {
        return outputRectList;
    }
}
