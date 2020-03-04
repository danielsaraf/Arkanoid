package levels;

import sprites.Block;
import sprites.Fill;
import sprites.GeneralBackground;
import sprites.Velocity;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * Instantiates a new Level specification reader.
     */
    public LevelSpecificationReader() {
        List<LevelInformation> lvIList = new ArrayList<>();
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> list = new ArrayList<>();
        String line = null;
        line = readNewLine((BufferedReader) reader);
        while (line != null) {
            GeneralLevel newLv = new GeneralLevel();
            int fieldToFill = 10;
            String bDefinitions = null;
            int startX = 0;
            int startY = 0;
            int heigt = 0;
            while (!line.contains("END_LEVEL")) {
                if (line.length() == 0 || line.charAt(0) == 35) {
                    line = readNewLine((BufferedReader) reader);
                    continue;
                }
                //Start building the level

                if (line.contains("level_name")) {
                    fieldToFill--;
                    newLv.setLevelName(line.substring(11));
                } else if (line.contains("ball_velocities")) {
                    fieldToFill--;
                    line = line.substring(16);
                    String a, s;
                    int numOfVelocities = 0;
                    List<Velocity> velocitiesList = new ArrayList<>();
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == 44) {
                            numOfVelocities++;
                        }
                    }
                    newLv.setNumberOfBalls(numOfVelocities);
                    for (int i = 0; i < numOfVelocities; i++) {
                        a = line.substring(0, line.indexOf(","));
                        line = line.substring(a.length() + 1);
                        if (i != numOfVelocities - 1) {
                            s = line.substring(0, line.indexOf(" "));
                            line = line.substring(s.length() + 1);
                        } else {
                            s = line;
                        }
                        velocitiesList.add(Velocity.fromAngleAndSpeed(Double.parseDouble(a), Double.parseDouble(s)));
                    }
                    newLv.setVelocities(velocitiesList);
                } else if (line.contains("background")) {
                    fieldToFill--;
                    Fill fill = null;
                    line = line.substring(11);

                    if (line.substring(0, 5).contains("color")) {
                        line = line.substring(6, line.length() - 1);
                        Color color = null;
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
                        fill = new Fill(color, 1);
                    } else {
                        fill = new Fill(line.substring(6, line.length() - 1), 1);
                    }
                    GeneralBackground background = new GeneralBackground(fill);
                    newLv.setBackground(background);

                } else if (line.contains("paddle_speed")) {
                    newLv.setPaddleSpeed(Integer.parseInt(line.substring(13)));
                    fieldToFill--;
                } else if (line.contains("paddle_width")) {
                    newLv.setPaddleWidth(Integer.parseInt(line.substring(13)));
                    fieldToFill--;
                } else if (line.contains("block_definitions")) {
                    bDefinitions = line.substring(18);
                    fieldToFill--;
                } else if (line.contains("blocks_start_x")) {
                    startX = Integer.parseInt(line.substring(15));
                    fieldToFill--;
                } else if (line.contains("blocks_start_y")) {
                    startY = Integer.parseInt(line.substring(15));
                    fieldToFill--;
                } else if (line.contains("row_height")) {
                    heigt = Integer.parseInt(line.substring(11));
                    fieldToFill--;
                } else if (line.contains("num_blocks")) {
                    newLv.setNumberOfBlocksToRemove(Integer.parseInt(line.substring(11)));
                    fieldToFill--;
                } else if (line.contains("START_BLOCKS")) {
                    newLv.setBlocks(createBlocksList(bDefinitions, startX, startY, heigt, (BufferedReader) reader));
                }
                line = readNewLine((BufferedReader) reader);
            }
            if (fieldToFill != 0) {
                System.out.println("Bad level definition file, missing data");
                System.exit(1);
            }
            list.add(newLv);
            line = readNewLine((BufferedReader) reader);
        }
        try {
            reader.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Create blocks list list.
     *
     * @param bDefinitions the block definitions file
     * @param xPos         the x position
     * @param yPos         the y position
     * @param height       the height
     * @param reader       the reader
     * @return list of blocks
     */
    private List<Block> createBlocksList(String bDefinitions, int xPos, int yPos, int height, BufferedReader reader) {
        String line = readNewLine(reader);
        List<Block> blocksList = new ArrayList<>();
        BufferedReader buffer = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(bDefinitions);
            assert is != null;
            buffer = new BufferedReader(new InputStreamReader(is));
        } catch (Exception e) {
            System.out.println("error");
        }
        BlocksFromSymbolsFactory blockFac = BlocksDefinitionReader.fromReader(buffer);
        while (!line.contains("END_BLOCKS")) {
            int x = xPos;
            if (line.length() > 0 && line.charAt(0) != 35) {
                for (int i = 0; i < line.length(); i++) {
                    if (blockFac.isBlockSymbol(line.substring(i, i + 1))) {
                        Block newBlock = blockFac.getBlock(line.substring(i, i + 1), x, yPos);
                        x += newBlock.getCollisionRectangle().getWidth();
                        blocksList.add(newBlock);
                    } else {
                        x += blockFac.getSpaceWidth(line.substring(i, i + 1));
                    }
                }
                yPos += height;
            }
            line = readNewLine(reader);
        }
        return blocksList;
    }

    /**
     * Read new line string.
     *
     * @param reader the reader
     * @return the string
     */
    private String readNewLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
