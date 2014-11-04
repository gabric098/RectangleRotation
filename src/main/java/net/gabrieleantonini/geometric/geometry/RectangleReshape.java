package net.gabrieleantonini.geometric.geometry;

import java.awt.*;
import java.util.ArrayList;

/**
 * The class solve the geometric problem of manipulating a generated list of vertical rectangles
 * in order to provide the same shape using the minimum number of horizontal rectangles.
 */
public class RectangleReshape {
    /**
     * The unprocessed list of vertical rectangles.
     */
    private ArrayList<Rectangle> inputRectList;
    /**
     * The processed list of horizontal rectangles.
     */
    private ArrayList<Rectangle> outputRectList;

    public RectangleReshape(ArrayList<Rectangle> rectangleList) {

        this.inputRectList = rectangleList;
        this.outputRectList = new ArrayList<Rectangle>(rectangleList.size());
    }

    /**
     * The method performs an algorithm on the generated rectangles to re­generate the exact
     * shape with the minimum number of horizontal rectangles
     * @return the rearranged rectangle list
     * @param maxHeight max shape height
     */
    public ArrayList<Rectangle> process(int maxHeight) {
        if (this.inputRectList.size() > 0) {
            processAdjacentArea(this.inputRectList, 0, maxHeight);
        }
        return (this.outputRectList);
    }

    /**
     * This method subtracts the area of the horizontal rectangle from a given list of vertical ones.
     * It returns the resulting vertical rectangle list.
     * @param horRect The horizontal rectangle to remove from the list
     * @param rectList The vertical rectangles list
     * @return the update vertical rectangles list
     */
    private ArrayList<Rectangle> removeRectangle(Rectangle horRect, ArrayList<Rectangle> rectList) {
        ArrayList<Rectangle> updatedList = new ArrayList<Rectangle>(rectList.size());
        for (Rectangle rect : rectList) {
            updatedList.add(new Rectangle((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) (rect.getHeight() - horRect.getHeight())));
        }
        return updatedList;
    }

    /**
     * Recursive method that:
     * - Finds the common horizonal rectangle area
     * - Subtracts it from the vertical rectangles list
     * - Looks for other adjacent areas (and process them)
     *
     * @param rectList A list of adjacent vertical rectangles
     * @param xOffset X axis offset
     * @param yOffset Y axis offset
     */
    private void processAdjacentArea(ArrayList<Rectangle> rectList, int xOffset, int yOffset) {
        // let's clone the object to avoid reference problems.
        ArrayList<Rectangle> rectListCopy = new ArrayList<Rectangle>(rectList);
        // ok, we have a adjacent area, let's find the common horizontal rectangle
        Rectangle horRect = getCommonAreaRectangle(rectListCopy, xOffset, yOffset);
        // add the horizontal rectangle found to the destination rectangle list
        this.outputRectList.add(horRect);
        // and remove the area from the list
        ArrayList<Rectangle> subtractedList = removeRectangle(horRect, rectListCopy);
        // keep track of the Y axis offset while I'm moving on
        yOffset = yOffset - (int) horRect.getHeight();
        // now let's try to find the next adjacent vertical rectangle list(s)
        ArrayList<Rectangle> currRectList = new ArrayList<Rectangle>();
        int idx = 1;
        for (Rectangle rect : subtractedList) {
            if (rect.getHeight() > 0 && idx < subtractedList.size()) {
                currRectList.add(rect);
            } else{
                if (rect.getHeight() > 0) {
                    // if height > 0 I'm processing last item, just add it to the rectangle list
                    currRectList.add(rect);
                }
                if (currRectList.size() > 0) {
                    // calls the method recursively
                    processAdjacentArea(currRectList, xOffset, yOffset);
                    currRectList.clear();
                }
            }
            idx++;
        }
    }

    /**
     * Given a list of adjacent rectangles find the common rectangle area
     * @param rectList The list of vertical rectangles to process
     * @param xOffset X axis offset
     * @param yOffset Y axis offset
     * @return Rectangle the common area rectangle
     */
    private Rectangle getCommonAreaRectangle(ArrayList<Rectangle> rectList, int xOffset, int yOffset) {
        // get the minimum common height
        int minHeight = getMinHeight(rectList);
        int width = 0;
        int xValue = -1;
        for (Rectangle rect : rectList) {
            if (rect.getHeight() >= minHeight) {
                // if the height is equals or less than minimum height, add the width
                width += rect.getWidth();
                if (xValue < 0) {
                    // keep track of X position
                    xValue = (int) rect.getX();
                }
            }
        }
        // now generate the common rectangle object
        return new Rectangle((xValue + xOffset), yOffset, width, minHeight);
    }

    /**
     * Given a list of adjacent rectangles find the minimum common height
     * @param rectList The rectangle list
     * @return int the minimum common height
     */
    private int getMinHeight(ArrayList<Rectangle> rectList) {
        int actMinHeight = Integer.MAX_VALUE;
        for (Rectangle rect : rectList) {
            if (actMinHeight > (int) rect.getHeight()) {
                actMinHeight = (int) rect.getHeight();
            }
        }
        return actMinHeight;
    }
}
