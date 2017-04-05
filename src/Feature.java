/**
 * Created by Pri on 2/04/2017.
 */
public class Feature {

    int[] rows;
    int[] cols;
    boolean[] signs;

    public Feature(int[] row, int[] col, boolean[] sign){
        this.rows=row;
        this.cols=col;
        this.signs=sign;
    }

    public int[] getRows(){
        return rows;
    }

    public void setRows (int[] r){
        this.rows = r;
    }

    public int[] getCols(){
        return cols;
    }

    public void setCols(int[] c){
        this.cols = c;
    }

    public boolean[] getSigns(){
        return signs;
    }

    public void setSigns(boolean[] s){
        this.signs =    s;
    }


}
