package net.gabrieleantonini.geometric.adapters;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.awt.*;
import java.util.ArrayList;

public class TestJSONAdapter {

    /**
     * Tests the correct format of JSON Adapter output
     */
    @Test
    public void testJSONFormat() {
        ArrayList<Rectangle> inList = this.generateInputRectangleList();
        ArrayList<Rectangle> outList = this.generateOutputRectangleList();
        String expectedJSON = "{\"numRects\":5,\"sourceRectangles\":[{\"x\":0,\"y\":150,\"height\":104,\"width\":58},{\"x\":58,\"y\":150,\"height\":125,\"width\":72},{\"x\":130,\"y\":150,\"height\":78,\"width\":53},{\"x\":183,\"y\":150,\"height\":103,\"width\":42},{\"x\":225,\"y\":150,\"height\":55,\"width\":57}],\"rectangles\":[{\"x\":0,\"y\":150,\"height\":55,\"width\":282},{\"x\":0,\"y\":95,\"height\":23,\"width\":225},{\"x\":0,\"y\":72,\"height\":26,\"width\":130},{\"x\":58,\"y\":46,\"height\":21,\"width\":72},{\"x\":183,\"y\":72,\"height\":25,\"width\":42}]}";
        RectangleListJSONAdapter adpt = new RectangleListJSONAdapter();
        String actualJSON = adpt.adaptToJSON(inList, outList);
        try {
            JSONAssert.assertEquals(expectedJSON, actualJSON, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * If empty rectangle lists are passed as parameter, JSON should be still valid
     */
    @Test
    public void emptyLsitsShouldCreateValidJSON() {
        ArrayList<Rectangle> inList = new ArrayList<Rectangle>();
        ArrayList<Rectangle> outList = new ArrayList<Rectangle>();
        String expectedResult = "{\"numRects\":0,\"sourceRectangles\":[],\"rectangles\":[]}\n";
        RectangleListJSONAdapter adpt = new RectangleListJSONAdapter();
        String actualJSON = adpt.adaptToJSON(inList, outList);
        try {
            JSONAssert.assertEquals(expectedResult, actualJSON, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
