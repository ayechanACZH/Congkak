
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AyeChan
 */
public class congkakAlgorithm {

    static int holes[] = new int[14];
    static int storeHouseP1 = 0;
    static int storeHouseP2 = 0;
   
    
    
    public static void initializeHoles() {
        for (int x = 0; x < holes.length; x++) {
            holes[x] = 4;
        }
    }
    
       public static void initializeHolesChangeSeed(int seed) {
        for (int x = 0; x < holes.length; x++) {
            holes[x] = seed;
        }
    }
    
        public void algo(int a,int counter) {
        
        congkakForm cF = new congkakForm();
        int takeSeed=0;    
        
        JButton button[] = new JButton[14];
        button[0] = cF.hole1;
        button[1] = cF.hole2;
        button[2] = cF.hole3;
        button[3] = cF.hole4;
        button[4] = cF.hole5;
        button[5] = cF.hole6;
        button[6] = cF.hole7;
        button[7] = cF.hole8;
        button[8] = cF.hole9;
        button[9] = cF.hole10;
        button[10] = cF.hole11;
        button[11] = cF.hole12;
        button[12] = cF.hole13;
        button[13] = cF.hole14;
 
        for (int i = 0; i < a; i++) {
            
            try{
                
            counter++;
            if(counter ==14){
                counter=0;
            }
            congkakAlgorithm.holes[counter] += 1;
            button[counter].setText(String.valueOf(congkakAlgorithm.holes[counter]));
            }catch(Exception ex){
                
            }
        }
            
            congkakAlgorithm.holes[a]=0;
            button[a].setText(String.valueOf(congkakAlgorithm.holes[a]));
           
            
            takeSeed=counter+1;
            button[takeSeed].doClick();
    }

    public static void main(String[] args) {
        
        
        congkakAlgorithm congkakAlgo = new congkakAlgorithm();
        congkakAlgo.initializeHoles();

        congkakForm form = new congkakForm();
        form.setVisible(true);
    }
    
    

}
