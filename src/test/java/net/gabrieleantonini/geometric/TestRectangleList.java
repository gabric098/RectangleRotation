package net.gabrieleantonini.geometric;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for RectangleReshape
 */
public class TestRectangleList {

    @Test
    public void shapeAreasShoudlMatch() {
        RectangleList rectangleList = new RectangleList();
        rectangleList.generateSourceList(5);
        ArrayList<Rectangle> inputRectList = rectangleList.getInputRectList();
        rectangleList.rearrangeRectangles();
        ArrayList<Rectangle> outputRectList = rectangleList.getOutputRectList();
        int inputArea = calculateShapeArea(inputRectList);
        int outputArea = calculateShapeArea(outputRectList);
        assertEquals("Input and output shape area should be equals", inputArea, outputArea);
    }

    @Test
    public void generatedListShouldMatchGivenNumber() {
        int itemsCount = 10;
        RectangleList rectangleList = new RectangleList();
        rectangleList.generateSourceList(itemsCount);
        assertEquals("Generated rectangle list length should match the specified parameter", itemsCount, rectangleList.getInputRectList().size());
    }

    @Test
    public void rearrangeShouldPopulateOutputList() {
        RectangleList rectangleList = new RectangleList();
        rectangleList.generateSourceList(5);
        rectangleList.rearrangeRectangles();
        assertNotEquals("Rearrage method should populate the output list", 0, rectangleList.getOutputRectList().size());
    }

    private int calculateShapeArea(ArrayList<Rectangle> rectList) {
        int area = 0;
        for (Rectangle rect : rectList) {
            area += (rect.getWidth() * rect.getHeight());
        }
        return area;
    }


}
