import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Pri on 2/04/2017.
 */
public class Main {
    private int rows = 10;
    private int cols = 10;
    private boolean[][] image = new boolean[rows][cols];
    private String category = "other";
    private String categoryName;

    private ArrayList<Image> images;
    private ArrayList<Feature> features;

    public Main(String args){
        load(args);
        intialiseFeatures();
        initialiseWeights();
        algorithm();
    }

    private void intialiseFeatures() {
        features = new ArrayList<Feature>();
        Random random = new Random();
        for (int i =0; i<50;i++){

        }
    }

    private double[] initialiseWeights(){
        double[] weights = new double [51];
        Random random = new Random();

        for(int i=1;i<51;i++) {
            weights[i] = random.nextDouble();
        }

        weights[0] = 1;
        return weights;
    }

    public void load(String file){
        boolean[][] newimage = null;
        try{
            java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
            Scanner f = new Scanner(new File(file));
            if (!f.next().equals("P1")) System.out.println("Not a P1 PBM file" );
            category = f.next().substring(1);
            if (!category.equals("other")) categoryName=category;
            rows = f.nextInt();
            cols = f.nextInt();

            newimage = new boolean[rows][cols];
            for (int r=0; r<rows; r++){
                for (int c=0; c<cols; c++){
                    newimage[r][c] = (f.findWithinHorizon(bit,0).equals("1"));
                }
                images.add(new Image(newimage,category));
            }
            f.close() ;
        }
        catch(IOException e){System.out.println("Load from file failed"); }
        if (newimage!=null) {
            image=newimage;
        }
    }

    private void algorithm(){

    }
}
