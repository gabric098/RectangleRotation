package net.gabrieleantonini.geometric;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // minimum number of rectangles
        final int MIN_RECT_NUMBER = 5;
        // maximum number of rectangles
        final int MAX_RECT_NUMBER = 15;
        // output file for json format
        final String OUTPUT_JSON_FILE = "./output/rectangles.json";
        // output file for html format
        final String OUTPUT_HTML_FILE = "./output/rectangles.html";
        // html output mode (debug feature)
        boolean html = false;

        // check input arguments
        if (args.length > 1) {
            // maximum of agrs expected: 1
            System.out.println("Invalid number of agruments, expected: 1");
            System.exit(0);
        } else if (args.length == 1) {
            //check arg value
            if (args[0].equals("-html")) {
                html = true;
            } else {
                System.out.println("Invalid argument: " + args[0]);
                System.exit(0);
            }
        }

        // cleanup output directory
        String[] filesToRemove = {OUTPUT_JSON_FILE, OUTPUT_HTML_FILE};
        cleanup(filesToRemove);

        int rectNumber = Integer.MIN_VALUE;
        while (rectNumber < MIN_RECT_NUMBER || rectNumber > MAX_RECT_NUMBER) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the number of rectangles you want to draw. (between 5 and 15):");

            // read and validate the input
            try {
                String rectNumberText = br.readLine();
                rectNumber = Integer.parseInt(rectNumberText);
            } catch (IOException e) {
                System.out.println("Sorry, I can't read the input. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("This doesn't look like an integer. Try again.");
            }
        }

        RectangleList rg = new RectangleList();
        // generate the random rectangle list
        rg.generateSourceList(rectNumber);
        // rearrange the rectangles
        rg.rearrangeRectangles();

        try {
            // write json file
            PrintWriter out = new PrintWriter(OUTPUT_JSON_FILE);
            out.println(rg.getJSONData());
            out.close();
            System.out.println("JSON output successfully written to file: " + new File(OUTPUT_JSON_FILE).getCanonicalPath());
        } catch (Exception e) {
            System.out.println("Error creating JSON output file: " + OUTPUT_HTML_FILE);
        }

        // if html output is enabled, writes the HTML file
        if (html) {
            try {
                PrintWriter out = new PrintWriter(OUTPUT_HTML_FILE);
                out.println(rg.getHTMLData());
                out.close();
                System.out.println("HTML output successfully written to file: " + new File(OUTPUT_HTML_FILE).getCanonicalPath());
            } catch (Exception e) {
                System.out.println("Error creating HTML output file: " + OUTPUT_HTML_FILE);
            }
        }
    }

    /**
     * Cleans the output folder deleting the existing files created by previous run
     * @param filesToRemove list of files to remove
     */
    private static void cleanup(String[] filesToRemove) {
        for (String fileToRemove : filesToRemove) {
            File f = new File(fileToRemove);
            if (f.exists()) {
                f.delete();
            }
        }
    }
}