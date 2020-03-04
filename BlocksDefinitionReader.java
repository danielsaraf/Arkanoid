package levels;

import sprites.Fill;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Blocks definition reader - get reader of block def file and return BlocksFromSymbolsFactory.
 */
public class BlocksDefinitionReader {

    /**
     * get reader of block def file and return BlocksFromSymbolsFactory.
     *
     * @param reader the reader of the block def file
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, BlockCreator> blockCreatorMap = new TreeMap<String, BlockCreator>();
        Map<String, String> spaceMap = new TreeMap<String, String>();
        String line = readNewLine((BufferedReader) reader);
        String defaultLine = null;
        while (line != null) {
            // ignored lines
            if (line.length() == 0 || line.charAt(0) == 35) {
                line = readNewLine((BufferedReader) reader);
                continue;
            }
            // default line
            if (line.substring(0, 8).contains("default")) {
                defaultLine = line.substring(8);
                // block define line
            } else if (line.substring(0, 4).contains("bdef")) {
                String key = line.substring(line.indexOf("symbol:") + 7, line.indexOf(":") + 2);
                blockCreatorMap.put(key, blockReader(line, defaultLine));
                // space define line
            } else if (line.substring(0, 4).contains("sdef")) {
                String key = line.substring(line.indexOf(":") + 1, line.indexOf(":") + 2);
                String value = null;
                if (line.length() > 14) {
                    value = line.substring(line.indexOf("width:") + 6);
                } else if (defaultLine != null && defaultLine.contains("width:")) {
                    String[] parts = defaultLine.split(" ");
                    for (String s : parts) {
                        if (s.contains("width")) {
                            value = s.substring(s.indexOf("width:") + 6);
                            break;
                        }
                    }
                } else {
                    System.out.println("no space width include");
                    System.exit(1);
                }
                if (Integer.parseInt(value) < 0) {
                    System.out.println("invalid space value!");
                    System.exit(1);
                }
                spaceMap.put(key, value);
            }
            line = readNewLine((BufferedReader) reader);
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        return new BlocksFromSymbolsFactory(blockCreatorMap, spaceMap);
    }

    /**
     * read the line of the block def and return appropriate block creator.
     *
     * @param line        the line represent the block
     * @param defaultLine the default line
     * @return the block creator
     */
    private static BlockCreator blockReader(String line, String defaultLine) {
        int blockPower = 0;
        int width = 0;
        int height = 0;
        int fP = 0;
        List<Fill> fills = new ArrayList<>();
        Color stroke = null;
        int fieldToFill = 4;
        String[] parts = line.split(" ");
        for (int i = 0; i < parts.length; i++) {
            // dont forget ot take care about whats happen when no all the bar is their!
            if (parts[i].contains("height")) {
                height = Integer.parseInt(parts[i].substring(parts[i].indexOf(":") + 1));
                fieldToFill--;
            }
            if (parts[i].contains("width")) {
                width = Integer.parseInt(parts[i].substring(parts[i].indexOf(":") + 1));
                fieldToFill--;
            }
            if (parts[i].contains("hit_points")) {
                blockPower = Integer.parseInt(parts[i].substring(parts[i].indexOf(":") + 1));
                fieldToFill--;
            }
            if (parts[i].contains("fill")) {
                if (fills.isEmpty()) {
                    fieldToFill--;
                }
                fills.add(getFill(parts[i]));
            }
            if (parts[i].contains("stroke")) {
                line = parts[i].substring(parts[i].indexOf("stroke") + 7);
                if (line.contains("color")) {
                    stroke = colorFromString(line);
                } else {
                    System.out.println("Bad Stroke");
                    System.exit(1);
                }
            }
        }
        if (fieldToFill != 0) {
            parts = defaultLine.split(" ");
            for (String s : parts) {
                if (blockPower == 0) {
                    if (s.contains("hit_points")) {
                        blockPower = Integer.parseInt(s.substring(s.indexOf(":") + 1));
                        fieldToFill--;
                    }
                }
                if (width == 0) {
                    if (s.contains("width")) {
                        width = Integer.parseInt(s.substring(s.indexOf(":") + 1));
                        fieldToFill--;
                    }
                }
                if (height == 0) {
                    if (s.contains("height")) {
                        height = Integer.parseInt(s.substring(s.indexOf(":") + 1));
                        fieldToFill--;
                    }
                }
                if (s.contains("fill")) {
                    if (fills.isEmpty()) {
                        fieldToFill--;
                    }
                    fills.add(getFill(s));
                }
                if (stroke == null) {
                    if (s.contains("stroke")) {
                        s = s.substring(s.indexOf("stroke") + 7);
                        if (s.contains("color")) {
                            stroke = colorFromString(s);
                        }
                    }
                }
            }
        }
        if (fieldToFill != 0) {
            System.out.println("Not enough data of block definition.");
            System.exit(1);
        }

        return new BlockMaker(blockPower, width, height, fills, stroke);
    }

    /**
     * create fill out of string.
     *
     * @param s the string
     * @return the fill
     */
    private static Fill getFill(String s) {
        String line;
        Fill fill = null;
        int fP;
        if (s.contains("-")) {
            fP = Integer.parseInt(s.substring(s.indexOf("-") + 1, s.indexOf(":")));
        } else {
            fP = 1;
        }
        line = s.substring(s.indexOf("fill") + 5);
        if (line.contains("color")) {
            fill = new Fill(colorFromString(line), fP);
        } else {
            fill = new Fill(line.substring(line.indexOf("image") + 6, line.length() - 1), fP);
        }
        return fill;
    }

    /**
     * Color from string color.
     *
     * @param line the string
     * @return the color
     */
    private static Color colorFromString(String line) {
        Color color = null;
        line = line.substring(line.indexOf("color") + 6, line.length() - 1);
        if (line.contains("RGB")) {
            Pattern c = Pattern.compile("RGB *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Matcher m = c.matcher(line);
            if (m.matches()) {
                color = new Color(Integer.valueOf(m.group(1)),
                        Integer.valueOf(m.group(2)),
                        Integer.valueOf(m.group(3)));
            }
        } else {
            try {
                Field field = Class.forName("java.awt.Color").getField(line);
                color = (Color) field.get(null);
            } catch (Exception e) {
                System.out.println("Bad color!");
                System.exit(1);
            }
        }
        return color;
    }

    /**
     * Read new line string.
     *
     * @param reader the reader
     * @return the string
     */
    private static String readNewLine(BufferedReader reader) {
        if (reader != null) {
            try {
                return reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}