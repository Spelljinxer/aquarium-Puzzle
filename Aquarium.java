{\rtf1\ansi\ansicpg1252\deff0\nouicompat{\fonttbl{\f0\fswiss\fcharset0 Helvetica;}}
{\*\generator Riched20 10.0.18362}\viewkind4\uc1 
\pard\sa200\sl276\slmult1\f0\fs24\lang9\par
/**\par
 * Aquarium represents a single problem in the game Aquarium.\par
 *\par
 * @author (Reiden Rufin 22986337) (Aditi Malu 22526301)\par
 * @version 1.06 2020\par
 */\par
public class Aquarium\par
\{\par
    private int   size;         // the board is size x size\par
    private int[] columnTotals; // the totals at the top of the columns, left to right\par
    private int[] rowTotals;    // the totals at the left of the rows, top to bottom \par
\par
    // the board divided into aquariums, numbered from 1,2,3,...\par
    // spaces with the same number are part of the same aquarium\par
    private int[][] aquariums;\par
    // the board divided into spaces, each empty, water, or air\par
    private Space[][] spaces;\par
    private FileIO file;\par
\par
    /**\par
     * Constructor for objects of class Aquarium. \par
     * Creates, initialises, and populates all of the fields.\par
     */\par
    public Aquarium(String filename)\par
    \{\par
        FileIO file = new FileIO(filename);\par
        columnTotals = parseLine (file.getLines().get(0));\par
        rowTotals = parseLine (file.getLines().get(1));\par
        size = columnTotals.length;\par
        aquariums = new int [size][size];\par
        for (int i = 0; i < size; i++)\par
        \{\par
            aquariums [i] = parseLine (file.getLines().get(i + 3));\par
        \}\par
        spaces = new Space [size][size];\par
        clear();\par
    \}\par
\par
    /**\par
     * Uses the provided example file on the LMS page.\par
     */\par
    public Aquarium()\par
    \{\par
        this("Examples/a6_1.txt");\par
    \}\par
\par
    /**\par
     * Returns an array containing the ints in s, \par
     * each of which is separated by one space. \par
     * e.g. if s = "1 299 34 5", it will return \{1,299,34,5\} \par
     */\par
    public static int[] parseLine(String s)\par
    \{\par
        String [] strArray = s.split(" ");\par
        int [] numbers = new int [strArray.length];\par
        for (int i = 0; i < strArray.length; i++)\par
        \{\par
            numbers [i] = Integer.parseInt(strArray[i]);\par
        \}\par
        return numbers;\par
    \}\par
\par
    /**\par
     * Returns the size of the puzzle.\par
     */\par
    public int getSize()\par
    \{\par
        return size;\par
    \}\par
\par
    /**\par
     * Returns the column totals.\par
     */\par
    public int[] getColumnTotals()\par
    \{\par
        return columnTotals;\par
    \}\par
\par
    /**\par
     * Returns the row totals.\par
     */\par
    public int[] getRowTotals()\par
    \{\par
        return rowTotals;\par
    \}\par
\par
    /**\par
     * Returns the board in aquariums.\par
     */\par
    public int[][] getAquariums()\par
    \{\par
        return aquariums;\par
    \}\par
\par
    /**\par
     * Returns the board in spaces.\par
     */\par
    public Space[][] getSpaces()\par
    \{\par
        return spaces;\par
    \}\par
\par
    /**\par
     * Performs a left click on Square r,c if the indices are legal, o/w does nothing. \par
     * A water space becomes empty; other spaces become water. \par
     */\par
    public void leftClick(int r, int c)\par
    \{\par
        if ( r < size && c < size)\par
        \{\par
            if (spaces[r][c] == Space.EMPTY)\par
            \{\par
                spaces[r][c] = Space.WATER;\par
            \}\par
            else if (spaces[r][c] == Space.WATER)\par
            \{\par
                spaces[r][c] = Space.EMPTY;\par
            \}\par
            else if (spaces[r][c] == Space.AIR)\par
            \{\par
                spaces[r][c] = Space.WATER;\par
            \}\par
        \}\par
    \}\par
\par
    /**\par
     * Performs a right click on Square r,c if the indices are legal, o/w does nothing. \par
     * An air space becomes empty; other spaces become air. \par
     */\par
    public void rightClick(int r, int c)\par
    \{\par
        if ( r < size && c < size)\par
        \{\par
            if (spaces[r][c] == Space.EMPTY)\par
            \{\par
                spaces[r][c] = Space.AIR;\par
            \}\par
            else if (spaces[r][c] == Space.AIR)\par
            \{\par
                spaces[r][c] = Space.EMPTY;\par
            \}\par
            else if (spaces[r][c] == Space.WATER)\par
            \{\par
                spaces[r][c] = Space.AIR;\par
            \}\par
        \}\par
    \}\par
    \par
    /**\par
     * Empties all of the spaces.\par
     */\par
    public void clear()\par
    \{\par
        for (int r = 0; r < spaces.length; r++)\par
            for (int c = 0; c < spaces.length; c++)\par
            \{\par
                spaces[r][c] = Space.EMPTY;\par
            \}\par
    \}\par
\}\par
}
 