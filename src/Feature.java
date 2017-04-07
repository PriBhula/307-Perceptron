/**
 * Created by Pri on 2/04/2017.
 */
public class Feature {

    int[] rows;
    int[] cols;
    boolean[] signs;
    int value;


    public Feature(int[] row, int[] col, boolean[] sign){
        this.rows=row;
        this.cols=col;
        this.signs=sign;
    }

    public int[] getRows(){
        return rows;
    }

    public int[] getCols(){
        return cols;
    }

    public boolean[] getSigns(){
        return signs;
    }


    public String toString(){
        return "Feature: rows"+rows.length+" cols"+cols.length+" signs"+signs.length;
    }
}
