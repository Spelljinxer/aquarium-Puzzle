
/**
 * Aquarium represents a single problem in the game Aquarium.
 *
 * @author (Reiden Rufin 22986337) (Aditi Malu 22526301)
 * @version 1.06 2020
 */
public class Aquarium
{
    private int   size;         // the board is size x size
    private int[] columnTotals; // the totals at the top of the columns, left to right
    private int[] rowTotals;    // the totals at the left of the rows, top to bottom 

    // the board divided into aquariums, numbered from 1,2,3,...
    // spaces with the same number are part of the same aquarium
    private int[][] aquariums;
    // the board divided into spaces, each empty, water, or air
    private Space[][] spaces;
    private FileIO file;

    /**
     * Constructor for objects of class Aquarium. 
     * Creates, initialises, and populates all of the fields.
     */
    public Aquarium(String filename)
    {
        FileIO file = new FileIO(filename);
        columnTotals = parseLine (file.getLines().get(0));
        rowTotals = parseLine (file.getLines().get(1));
        size = columnTotals.length;
        aquariums = new int [size][size];
        for (int i = 0; i < size; i++)
        {
            aquariums [i] = parseLine (file.getLines().get(i + 3));
        }
        spaces = new Space [size][size];
        clear();
    }

    /**
     * Uses the provided example file on the LMS page.
     */
    public Aquarium()
    {
        this("Examples/a6_1.txt");
    }

    /**
     * Returns an array containing the ints in s, 
     * each of which is separated by one space. 
     * e.g. if s = "1 299 34 5", it will return {1,299,34,5} 
     */
    public static int[] parseLine(String s)
    {
        String [] strArray = s.split(" ");
        int [] numbers = new int [strArray.length];
        for (int i = 0; i < strArray.length; i++)
        {
            numbers [i] = Integer.parseInt(strArray[i]);
        }
        return numbers;
    }

    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Returns the column totals.
     */
    public int[] getColumnTotals()
    {
        return columnTotals;
    }

    /**
     * Returns the row totals.
     */
    public int[] getRowTotals()
    {
        return rowTotals;
    }

    /**
     * Returns the board in aquariums.
     */
    public int[][] getAquariums()
    {
        return aquariums;
    }

    /**
     * Returns the board in spaces.
     */
    public Space[][] getSpaces()
    {
        return spaces;
    }

    /**
     * Performs a left click on Square r,c if the indices are legal, o/w does nothing. 
     * A water space becomes empty; other spaces become water. 
     */
    public void leftClick(int r, int c)
    {
        if ( r < size && c < size)
        {
            if (spaces[r][c] == Space.EMPTY)
            {
                spaces[r][c] = Space.WATER;
            }
            else if (spaces[r][c] == Space.WATER)
            {
                spaces[r][c] = Space.EMPTY;
            }
            else if (spaces[r][c] == Space.AIR)
            {
                spaces[r][c] = Space.WATER;
            }
        }
    }

    /**
     * Performs a right click on Square r,c if the indices are legal, o/w does nothing. 
     * An air space becomes empty; other spaces become air. 
     */
    public void rightClick(int r, int c)
    {
        if ( r < size && c < size)
        {
            if (spaces[r][c] == Space.EMPTY)
            {
                spaces[r][c] = Space.AIR;
            }
            else if (spaces[r][c] == Space.AIR)
            {
                spaces[r][c] = Space.EMPTY;
            }
            else if (spaces[r][c] == Space.WATER)
            {
                spaces[r][c] = Space.AIR;
            }
        }
    }
    
    /**
     * Empties all of the spaces.
     */
    public void clear()
    {
        for (int r = 0; r < spaces.length; r++)
            for (int c = 0; c < spaces.length; c++)
            {
                spaces[r][c] = Space.EMPTY;
            }
    }
}
