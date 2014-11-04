Rectangle Rotation
=========

This application is a possible implementation of the "rectangle rotation" geometrical problem.

After generating n rectangles of random width and height using a topleft origin coordinate system, this application performs an algorithm on the generated shape to regenerate the same shape using the minimum number of horizontal  rectangles.

The output of the program is a json format file with this structure:

```json
{
    "numRects": 5,
    "sourceRectangles": [
        {
            "x": 0,
            "y": 150,
            "height": 150,
            "width": 29
        },
        {
            "x": 29,
            "y": 150,
            "height": 83,
            "width": 48
        },
        {
            "x": 77,
            "y": 150,
            "height": 93,
            "width": 63
        },
        {
            "x": 140,
            "y": 150,
            "height": 56,
            "width": 45
        },
        {
            "x": 185,
            "y": 150,
            "height": 87,
            "width": 26
        }
    ],
    "rectangles": [
        {
            "x": 0,
            "y": 150,
            "height": 56,
            "width": 211
        },
        {
            "x": 0,
            "y": 94,
            "height": 27,
            "width": 140
        },
        {
            "x": 0,
            "y": 67,
            "height": 67,
            "width": 29
        },
        {
            "x": 77,
            "y": 67,
            "height": 10,
            "width": 63
        },
        {
            "x": 185,
            "y": 94,
            "height": 31,
            "width": 26
        }
    ]
}
```


Version
----

1.1

Usage (using Maven)
--------------

```sh
mvn exec:java -Dexec.mainClass="net.gabrieleantonini.geometric.Main" -Dexec.classpathScope=runtime
```

Output
----
The project generates a JSON file in the output folder, if the optional parameter (```-html```) is provided, outputs also an HTML file containing a graphic representation
of the test result.