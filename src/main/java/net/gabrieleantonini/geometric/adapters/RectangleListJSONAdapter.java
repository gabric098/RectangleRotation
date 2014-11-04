package net.gabrieleantonini.geometric.adapters;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

/**
 * Adapts the rectangles list for being displayed through json viewer
 */
public class RectangleListJSONAdapter {

    /**
     * Returns a String containing the JSON representation of the rectangle lists.
     * @param srcRectList Source rectangle list.
     * @param rectList Destination rectangle list.
     * @return the JSON representation of the rectangle lists
     */
    @SuppressWarnings("unchecked")
    public String adaptToJSON(ArrayList<Rectangle> srcRectList, ArrayList<Rectangle> rectList) {
        Map obj=new LinkedHashMap<String, Integer>();
        obj.put("numRects", srcRectList.size());
        obj.put("sourceRectangles", this.getRectListAsJSONArray(srcRectList));
        obj.put("rectangles", this.getRectListAsJSONArray(rectList));
        return JSONValue.toJSONString(obj);
    }

    /**
     * Given a rectangle list, generates the corresponding JSONArray object
     * @param rectList the rectangle list
     * @return The JSONArray object
     */
    @SuppressWarnings("unchecked")
    private JSONArray getRectListAsJSONArray(ArrayList<Rectangle> rectList) {
        JSONArray jsonArray = new JSONArray();
        for (Rectangle rect : rectList) {
            Map<String, Integer> jsonMap = new LinkedHashMap<String, Integer>();
            jsonMap.put("x", (int) rect.getX());
            jsonMap.put("y", (int) rect.getY());
            jsonMap.put("height", (int) rect.getHeight());
            jsonMap.put("width", (int) rect.getWidth());
            jsonArray.add(jsonMap);
        }
        return jsonArray;
    }
}
