import java.io.*;
import java.util.*;

class FileRead {

    class Node {
        public int value;
        public int fileName;
        public int position;

        public Node (int value, int fileName, int position) {
            this.value = value;
            this.position = position;
            this.fileName = fileName;
        }
    }

    public static void main(String[] args) {

            HashMap<Integer, ArrayList<Integer>> store = new HashMap<Integer, ArrayList<Integer>>();
        //array: file name | file name | ... | position | position | ... 
            String fname = args[0];
            int fnum = Integer.parseInt(args[0].substring(args[0].length() - 1, args[0].length()));
            int position = 0;
            try {
                FileInputStream fileReader = new FileInputStream(fname);
                while (fileReader.available() > 0)
                {
                    int curr = fileReader.read();
                    System.out.println(curr);
                    ArrayList<Integer> currPos = new ArrayList<Integer>();
                    currPos.add(fnum);
                    currPos.add(position);
                    store.put(curr, currPos);
                    position ++;
                
                }
                fileReader.close();
            }  catch (FileNotFoundException ffe) {
                System.err.println("Error: the file was not found!");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } 
            // make pairings << hashmap for each pair (WAY MORE SPACE)
                // as you make one pairing, store the largest value of the pairing
                // as you make your next pairings, keep checking if they meet the previous pairing. 
                //if one member does, add the one member to the largest value dictionary type thing
                // if both do, add both members 
                // discard if you have a number pairing that is bigger 
                //the pairings (hashmap) will only have the full first array and the matching second array items
            // cehck which hashmap has largest values
            // then add on other 
            //traverse through first and second array
            // add each other array to the first and second array pairings 
            // a b c >> ab bc ca >> abc 
            // a b c d e >> ab ac ad ae bc bd be cd ce de abc, etc

            //sort each array using max priority queue
            //pull from each pq 
            //nodes in pq 


        

    }

  
    


}