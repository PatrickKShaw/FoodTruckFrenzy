package foodtruckfrenzy.Helper;

import java.io.InputStream;
import java.util.Scanner;

import foodtruckfrenzy.GameFramework.Grid;

/**
 * This class reads the layout file at runtime to store the data to be used in map creation
 * The static functon runs the reads layout.csv storing them in a 2d array of BoardElementEnum
 * They can later be accessed to create the map
 */
public class MapLayout {

    private static LayoutEnum[][] layout;

    static {
        InputStream inputStream = MapLayout.class.getResourceAsStream("layout.csv");
        Scanner scanner = new Scanner(inputStream);
        layout = new LayoutEnum[Grid.ROWS][Grid.COLS];
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items = line.split(",");
            for (int col = 0; col < items.length; col++) {
                layout[row][col] = LayoutEnum.valueOf(items[col]);
            }
            row++;
        }
        scanner.close();
    }

    /**
     * Gets the specified BoardElementEnum to be used in map creation
     * @param row row of specified BoardElementEnumt to return
     * @param col column of specified BoardElementEnumt to return
     * @return BoardElementEnum that was specified is returned
     */
    public static LayoutEnum getElementAt(int row, int col) {
        return layout[row][col];
    }
}
