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

    private ArrayList<Image> images = new ArrayList<Image>();
    static ArrayList<Feature> features = new ArrayList<Feature>();
    private double[] weights;

    public void loadFile(String file){
        System.out.println("loading");
        boolean[][] newimage = null;
        try{
            java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
            //System.out.println(file);
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
                //System.out.println("added image");
            }
            f.close() ;
        }
        catch(IOException e){System.out.println("Load from file failed"); }
        if (newimage!=null) {
            image=newimage;
        }
    }

    private void intialiseFeatures() {
        System.out.println("initFeatures");
        Random random = new Random();
        for (int i =0; i<54;i++){
            int[] rs = new int[4];
            int[] cs = new int[4];
            boolean[] ss = new boolean[4];

            for(int j=0;i<4;i++){
                int k = random.nextInt(10);
                rs[j] = k;
            }

            for(int j=0;i<4;i++){
                int k = random.nextInt(10);
                cs[j] = k;
            }

            for(int j=0;i<4;i++){
                boolean k = random.nextBoolean();
                ss[j] = k;
            }
            features.add(new Feature(rs,cs,ss));
        }
        System.out.println("features.size()"+features.size());
    }

    private double[] initialiseWeights(){
        System.out.println("initWeights");
        weights = new double [51];
        Random random = new Random();

        for(int i=1;i<51;i++) {
            weights[i] = random.nextDouble();
        }

        weights[0] = 1;
        return weights;
    }

    private void predict(){
        System.out.println("predict");
        for (Image i: images){
            double prediction = 0;
            for (int j=0;j<i.featureValues.length;j++){
                prediction += (weights[j]*i.featureValues[j]);
            }

            if (prediction>0){
                i.setPrediction("Yes");
            }
            else{
                i.setPrediction("other");
            }

        }
    }

    private void algorithm(){
        System.out.println("algorithm");
        for (int epoch=0;epoch<100;epoch++){
            int correct=0;
            predict();
            for (Image image:images){
                image.calcFeatureValues();
                if(image.getCat().equals(image.getPrediction())){
                    correct++;
                }
                else{
                    if (image.getCat().equals("other") && image.getPrediction().equals("Yes")){
                        for (int neg=0;neg<51;neg++){
                            weights[neg] -= image.featureValues[neg];
                        }
                    }
                    else if (image.getCat().equals("Yes") && image.getPrediction().equals("other")){
                        for (int pos=0;pos<51;pos++){
                            weights[pos] += image.featureValues[pos];
                        }
                    }
                }
                System.out.println("correct" + correct + " total"+images.size());
                System.out.println("Accuracy: "+((correct/images.size())*100)+"%");
            }
        }
    }



    public static void main(String[] args){
        Main main = new Main();
        main.loadFile("image.data");
        main.initialiseWeights();
        main.intialiseFeatures();
        main.algorithm();
    }
}
