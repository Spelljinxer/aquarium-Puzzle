{\rtf1\ansi\ansicpg1252\deff0\nouicompat\deflang3081{\fonttbl{\f0\fnil\fcharset0 Calibri;}}
{\*\generator Riched20 10.0.18362}\viewkind4\uc1 
\pard\sa200\sl276\slmult1\f0\fs22\lang9\par
/**\par
 * CheckSolution is a utility class which can check if\par
 * a board position in an Aquarium puzzle is a solution.\par
 *\par
 * @author (Reiden Rufin 22986337) (Aditi Malu 22526301)\par
 * @version 1.06 2020\par
 */\par
import java.util.Arrays; \par
\par
public class CheckSolution\par
\{\par
    /**\par
     * Non-constructor for objects of class CheckSolution\par
     */\par
    private CheckSolution()\{\}\par
\par
    /**\par
     * Returns the number of water squares in each row of Aquarium puzzle p, top down.\par
     */\par
    public static int[] rowCounts(Aquarium p)\par
    \{\par
        int [] rowCounts = new int [p.getSize()];\par
        for (int i = 0; i < rowCounts.length; i++)\par
            for (int j = 0; j < rowCounts.length; j++)\par
\par
                if (p.getSpaces()[i][j] == Space.WATER)\par
                \{\par
                    rowCounts[i] += 1;\par
                \}\par
        return rowCounts;\par
    \}\par
\par
    /**\par
     * Returns the number of water squares in each column of Aquarium puzzle p, left to right.\par
     *\par
     */\par
    public static int[] columnCounts(Aquarium p)\par
    \{\par
        int [] columnCounts = new int [p.getSize()];\par
        for (int i = 0; i < columnCounts.length; i++)\par
            for (int j = 0; j < columnCounts.length; j++)\par
\par
                if (p.getSpaces()[i][j] == Space.WATER)\par
                \{\par
                    columnCounts[j] += 1;\par
                \}\par
        return columnCounts;\par
    \}\par
\par
    /**\par
     * Returns a 2-int array denoting the collective status of the spaces \par
     * in the aquarium numbered t on Row r of Aquarium puzzle p. \par
     * The second element will be the column index c of any space r,c which is in t, or -1 if there is none. \par
     * The first element will be: \par
     * 0 if there are no spaces in t on Row r; \par
     * 1 if they're all water; \par
     * 2 if they're all not-water; or \par
     * 3 if they're a mixture of water and not-water. \par
     */\par
    public static int[] rowStatus(Aquarium p, int t, int r)\par
    \{\par
        int[] status = new int[2];\par
        status[0] = 0;\par
        status[1] = -1;\par
        boolean allWater = true, allNonWater = true;\par
        for (int c =0;c<p.getSize();c++) \par
        \{\par
            if (p.getAquariums()[r][c] == t)                                                                                                \par
            \{\par
                if (p.getSpaces()[r][c] == Space.WATER) \par
                \{\par
                    allWater = false;\par
                \}\par
                if (p.getSpaces()[r][c] != Space.WATER) \par
                \{\par
                    allNonWater = false;\par
                \}\par
                status[1] = c;\par
            \}\par
        \}\par
        if (status[1] != -1) \par
        \{\par
            if (!allWater && allNonWater) \par
            \{\par
                status[0] = 1;\par
            \}\par
            else if (allWater && !allNonWater) \par
            \{\par
                status[0] = 2;\par
            \}\par
            else \par
            \{\par
                status[0] = 3;\par
            \}\par
        \}\par
        return status;\par
    \}\par
\par
    /**\par
     * Returns a statement on whether the aquarium numbered t in Aquarium puzzle p is OK. \par
     * Every row must be either all water or all not-water, \par
     * and all water must be below all not-water. \par
     * Returns "" if the aquarium is ok; otherwise \par
     * returns the indices of any square in the aquarium, in the format "r,c". \par
     */\par
    public static String isAquariumOK(Aquarium p, int t)\par
    \{\par
        for (int r = 0; r <p .getSize(); r++) \par
        \{\par
            int[] status = rowStatus(p, t, r);\par
            if (status[0] == 2 && r-1 >= 0)\par
            \{\par
                int[] status2 = rowStatus (p, t, r-1);\par
                if(status2[0] == 1) \par
                \{\par
                    return r + "," + status2[1];\par
                \}\par
            \}else if (status[0] == 3) \par
            \{\par
                return r + "," + status[1];\par
            \}\par
        \}\par
        return "";\par
    \}\par
\par
    /**\par
     * Returns a statement on whether we have a correct solution to Aquarium puzzle p. \par
     * Every row and column must have the correct number of water squares, \par
     * and all aquariums must be OK. \par
     * Returns three ticks if the solution is correct; \par
     * otherwise see the LMS page for the expected results. \par
     */\par
    public static String isSolution(Aquarium p)\par
    \{\par
        for (int i = 0; i < p.getSize(); i++) \par
        \{\par
            if (p.getRowTotals()[i] != rowCounts(p)[i]) \par
            \{\par
                return "Row "+ i +" is wrong";\par
            \}\par
        \}\par
        for (int i = 0; i < p.getSize(); i++) \par
        \{ \par
            if(p.getColumnTotals()[i] != columnCounts(p)[i]) \par
            \{\par
                return "Column "+ i +" is wrong";\par
            \}\par
        \}\par
        int max = p.getAquariums()[0][0];\par
        //finding max aquarium number\par
        for(int r = 0; r < p.getSize(); r++) \par
        \{\par
            for(int c = 0; c < p.getSize(); c++)\par
            \{\par
                if (max < p.getAquariums()[r][c])\par
                \{\par
                    max = p.getAquariums()[r][c];\par
                \}\par
            \}\par
        \}\par
        for(int t = 1; t <= max; t++)\par
        \{\par
            if (!isAquariumOK(p, t).equals("")) \par
            \{\par
                return "The aquarium at " + isAquariumOK(p, t) + " is wrong";\par
            \}\par
        \}\par
        return "\\u2713\\u2713\\u2713";\par
    \}\par
\}\par
}
 