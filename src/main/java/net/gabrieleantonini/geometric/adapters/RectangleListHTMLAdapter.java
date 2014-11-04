package net.gabrieleantonini.geometric.adapters;

import java.awt.*;
import java.util.ArrayList;

/**
 * Adapts the rectangles list for being displayed on an SVG HTML canvas
 */
public class RectangleListHTMLAdapter {
    /**
     * The SVG coord system is different from the one required for JSON.
     * This method makes some changes in order to display the content accordingly
     * @param srcRectList Source rectangle list
     * @param rectList Destination rectangle list
     * @return the HTML
     */
    public String adaptToHTML(ArrayList<Rectangle> srcRectList, ArrayList<Rectangle> rectList) {
        String theHTML = "<html><body>";
        theHTML += plotSVGElement(srcRectList, "src");
        theHTML += plotSVGElement(rectList, "dst");
        theHTML += "</body></html>";
        return theHTML;
    }

    /**
     * Plot the single SVG element for a given shape (lsit of rectangles)
     * @param rectList rectangle list
     * @param svgId svg element id
     * @return the HTML
     */
    private String plotSVGElement(ArrayList<Rectangle> rectList, String svgId) {
        String theHTML = "<svg id=" + svgId + " width=\"1125\" height=\"200\">";
        for (Rectangle rect : rectList) {
            theHTML += "<rect x=\""+ (int) rect.getX() +"\" y=\"" + ((int) rect.getY() - (int) rect.getHeight())   + "\" width=\"" + (int) rect.getWidth() + "\" height=\"" + (int) rect.getHeight() + "\"" +
                    "  style=\"fill:blue;stroke:pink;fill-opacity:0.1;\" />";
        }
        theHTML += "</svg>";
        return theHTML;
    }
}
