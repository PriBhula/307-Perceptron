import java.util.ArrayList;

/**
 * Created by Pri on 2/04/2017.
 */
public class Image {

    private boolean[][] image;
    public String category;
    public String prediction;
    public int[] featureValues = new int[51];

    public Image (boolean[][] image, String category){
        this.image=image;
        this.category=category;
    }

    public void calcFeatureValues(){
        System.out.println("IMAGEcalcFV");
        ArrayList<Feature> f = Main.features;
        featureValues[0] = 1;
        int count =0;
        for(int i = 1; i < 50; i++){
            int sum = 0;
            for(int j = 0 ; j < 4;j++){
                if(image[f.get(i).rows[j]][f.get(i).cols[j]] == f.get(i).signs[j]){
                    sum++;
                }
            }
            if(sum >= 3){
                featureValues[i] = 1;
            }else{
                featureValues[i]= 0;
            }
            count++;
            System.out.println("count: "+count);
        }
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String p){
        prediction = p;
    }

    public String getCat (){
        return category;
    }

    public void setCategory(String c) {
        this.category = category;
    }
}
