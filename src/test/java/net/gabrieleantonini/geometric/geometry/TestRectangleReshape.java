package net.gabrieleantonini.geometric.geometry;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestRectangleReshape {

    @Test
    public void testProcessResult() {
        RectangleReshape rr = new RectangleReshape(this.generateInputRectangleList());
        ArrayList<Rectangle> actualResult = rr.process(150);
        assertEquals("Processed result should be equals to expected one", this.generateOutputRectangleList(), actualResult);
    }

    @Test
    public void processEmptyListShouldProduceEmptyList() {
        RectangleReshape rr = new RectangleReshape(new ArrayList<Rectangle>());
        ArrayList<Rectangle> actualResult = rr.process(150);
        assertEquals("Processed an empty list should produce an empty list", 0, actualResult.size());
    }

    /**
     * fixture method
     * @return rectangle list
     */
    private ArrayList<Rectangle> generateInputRectangleList() {
        ArrayList<Rectangle> rectList = new ArrayList<Rectangle>(5);
        Rectangle r = new Rectangle(0, 150, 58, 104);
        rectList.add(r);
        r = new Rectangle(58, 150, 72, 125);
        rectList.add(r);
        r = new Rectangle(130, 150, 53, 78);
        rectList.add(r);
        r = new Rectangle(183, 150, 42, 103);
        rectList.add(r);
        r = new Rectangle(225, 150, 57, 55);
        rectList.add(r);
        return rectList;
    }

    /**
     * fixture method
     * @return rectangle list
     */
    private ArrayList<Rectangle> generateOutputRectangleList() {
        ArrayList<Rectangle> rectList = new ArrayList<Rectangle>(5);
        Rectangle r = new Rectangle(0, 150, 282, 55);
        rectList.add(r);
        r = new Rectangle(0, 95, 225, 23);
        rectList.add(r);
        r = new Rectangle(0, 72, 130, 26);
        rectList.add(r);
        r = new Rectangle(58, 46, 72, 21);
        rectList.add(r);
        r = new Rectangle(183, 72, 42, 25);
        rectList.add(r);
        return rectList;
    }
}
