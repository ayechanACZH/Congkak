
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azizi
 */

public class UpdateSeed extends Thread  {
    //Declaration only
    JButton thisBtn[];
    JLabel p1House , p2House;
    int thisBtnIndex, thisSeed;
    
    public UpdateSeed(JButton btn[], int btnIndex, int seed, JLabel p1,JLabel p2) {
        //Initializing the declaration to the parameters you enter when calling this method from congkakForm
        //You should understand this one la. Hahahaha
        thisBtn = btn;
        p1House = p1;
        p2House = p2;
        thisBtnIndex = btnIndex;
        thisSeed = seed;
    }
    
    //This override will run the animation looping
    //Why we need this class is because this class extends Thread as you can see above
    //Before this your program run on only 1 thread, that's why it cannot animate instead, it will just jump to the end
    //Inside this override method also you will see Thread.sleep(500)
    //Means it will sleep for 500 milliseconds and then continue with the code below it
    //Extending thread means, this class will run on a new Thread
    //https://caveofprogramming.com/javatutorial/java-tutorial-8-starting-threads-and-using-anonymous-classes.html
    //Read the link above for more details, I learn from there also
    @Override
    public void run() {
        //First disable all the buttons
        //If this line don't have, while the animation running, you can still click another hole, which leads to interruptionException
        //We don't want that
        for (JButton b: thisBtn) 
            b.setEnabled(false);
        
        //Add 1 to the index since we will start on the next index, not the current index
        int index = thisBtnIndex + 1;
        //Put the seed from the hold in holdingSeed
        int holdingSeed = thisSeed;
        
        //This boolean will determine whether the seeds in the hole need to be taken or just drop in
        //You will understand in the for loop
        boolean zero = true;
        
        for (int i = index; i <= congkakAlgorithm.holes.length; i++) {            
            try {
                Thread.sleep(25); //Sleep for 500 milliseconds | Change 500 to anything you like if you don't want 500, 1000 = 1 second
                
                //If i == 14, then set i to 0(first hole) in order to avoid outofboundexception
                if (i == congkakAlgorithm.holes.length)
                    i = 0;
                
                //If zero is true
                if (zero) {
                    //Get the hole before
                    int before = i - 1;
                    
                    //If before is less than 0, set it to the last hole which is 13
                    if (before == -1)
                        before = 13;
                    
                    //Then set the number of seeds becomes 0 since we take from this hole, so this hole is empty
                    congkakAlgorithm.holes[before] = 0;
                    //The set the text of the button to the number of seed
                    thisBtn[before].setText(String.valueOf(congkakAlgorithm.holes[before]));
                }
                
                //Always set 0 to false, since the next time it loops, it will not take the seeds. Instead it will just drop in 1 extra seed
                zero = false;
                
                //As long as you have seeds in your hand, run this if
                if (holdingSeed != 0) {
                    //Just add the number of seed in the hole by 1
                    congkakAlgorithm.holes[i]++;
                    
                    //Set the text of the button
                    thisBtn[i].setText(String.valueOf(congkakAlgorithm.holes[i]));
                    
                    //Then minus the seed in your hand since you have drop it in the hole
                    holdingSeed--;
                }
                //If you have no seeds left in your hand run this else to see whether player continues, or die
                else {
                    //If the hole is not empty, the player continue playing by taking the seeds in the hole and put it in his hands
                    //And set zero to true, because next time it loop, it must empty the hole you take the seeds from
                    if (congkakAlgorithm.holes[i] != 0) {
                        holdingSeed = congkakAlgorithm.holes[i];
                        zero = true;
                    }
                    //If the hole it empty, then the player dies
                    //Again, inside here, if i == 14, set it to become 0
                    //Then add to p1StoreHouse, empty the hole since you took the seeds from the hole and put it in storehouse
                    //And lastly break the for loop
                    else {
                        i++;
                        
                        if (i == congkakAlgorithm.holes.length)
                        {
                            i = 0;
                        }
                        
                        if ( congkakForm.player % 2 != 0 )
                        {
                        congkakAlgorithm.storeHouseP2 += congkakAlgorithm.holes[i];
                        congkakAlgorithm.holes[i] = 0;
                        thisBtn[i].setText(String.valueOf(congkakAlgorithm.holes[i]));
                        
                        p2House.setText("StoreHouseP2: \n"+congkakAlgorithm.storeHouseP2);
                        
                          
                        break;
                        }
                        else
                        {
                        congkakAlgorithm.storeHouseP1 += congkakAlgorithm.holes[i];
                        congkakAlgorithm.holes[i] = 0;
                        thisBtn[i].setText(String.valueOf(congkakAlgorithm.holes[i]));
                        
                        p1House.setText("StoreHouseP1: \n"+congkakAlgorithm.storeHouseP1);
                        break;
                        
                        }
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateSeed.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //This for is to check if the button has no seeds, then disable it
        JButton p1[] = new JButton[7];
        
        JButton p2[] = new JButton[7];
                
        p2[0]=thisBtn[7];
        p2[1]=thisBtn[8];
        p2[2]=thisBtn[9];
        p2[3]=thisBtn[10];
        p2[4]=thisBtn[11];
        p2[5]=thisBtn[12];
        p2[6]=thisBtn[13];
        

        if (congkakForm.player % 2 == 0 ) {
            congkakForm.player ++;
            for(int i=0; i<7; i++){
            
            if (p2[i].getText().equals("0"))
            p2[i].setEnabled(false);
            else
            p2[i].setEnabled(true);
            
            
            
           }
        }
        else {
            congkakForm.player ++;
            for(int i=0; i<7; i++){
                p1[i]= thisBtn[i];
            if (p1[i].getText().equals("0"))
            p1[i].setEnabled(false);
            else
            p1[i].setEnabled(true);
                
            }
           
        }
        
        
        
        
    }
}

