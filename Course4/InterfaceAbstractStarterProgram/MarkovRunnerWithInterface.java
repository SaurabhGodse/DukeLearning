
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
/*        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 1);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 1);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 1);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 1);*/
        
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm, st, size, 531);

    }
    
    public void testHashMap(){
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        emm.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        emm.setRandom(42);
        String st = emm.getRandomText(50);
        printOut(st);
        
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
    System.out.println("\n----------------------------------");
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        long start1 = System.currentTimeMillis();
        MarkovModel mm = new MarkovModel(2);
        runModel(mm, st, size, seed);
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();

        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, size, seed);
        long end2 = System.currentTimeMillis();
        System.out.println("Time taken for markov model : " + (end1 - start1));
        System.out.println("Time taken for efficient model : " + (end2 - start2));
    }
}
