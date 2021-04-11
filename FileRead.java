import java.io.*;
import java.util.*;

class Node {
    private int fileName;
    private int position;

    public Node (int fileName, int position) {
        this.position = position;
        this.fileName = fileName;
    }

    public int getFileName() {
        return this.fileName;
    }

    public int getPos() {
        return this.position;
    } 


}

class FullMax {
    private HashMap <ArrayList<Integer>, ArrayList<Node>> store;
    private String[] files;

    public FullMax(String[] files) {
        this.store = new HashMap<ArrayList<Integer>, ArrayList<Node>>();
        this.files = files;
        for (int i = 0; i < files.length; i++ ) {
            addToMap(i);
        }
    }

    public HashMap <ArrayList<Integer>, ArrayList<Node>> completedHashMap() {
        return this.store;
    }

    private void addToMap(int num) {
            //first file 
            //String fname = args[0];
            String fname = this.files[num];
            int fnum = Integer.parseInt(fname.substring(fname.length() - 1, fname.length()));
            int position = 0;
            try {
                FileInputStream fileReader = new FileInputStream(fname);  
                ArrayList<Integer> arrayBytes = new ArrayList<Integer>();
                while (fileReader.available() > 0)
                {
                    int curr = fileReader.read();
                    
                    if (curr == 32) {
                        position ++;
                        System.out.println(arrayBytes.size() + " " + fnum);
                        if (store.containsKey(arrayBytes)) {
                            store.get(arrayBytes).add(new Node(fnum, position));
                        } else {
                            ArrayList<Node> currPos = new ArrayList<Node>();
                            currPos.add(new Node (fnum, position));
                            store.put(arrayBytes, currPos);
                        }
                        arrayBytes = new ArrayList<Integer>();

                    } else {
                        arrayBytes.add(curr);
                    }
                    
                }
                    fileReader.close();
                    
                }  catch (FileNotFoundException ffe) {
                    System.err.println("Error: the file was not found!");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } 
                
    }
}

class FileRead {

    public static void main(String[] args) {
        //PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
        //HashMap<Integer, ArrayList<Node>> store = new HashMap<Integer, ArrayList<Node>>();
        //array: file name | file name | ... | position | position | ... 
        FullMax data = new FullMax(args);
        HashMap <ArrayList<Integer>, ArrayList<Node>> cleanHashMap = data.completedHashMap();
        int max = 0;
        ArrayList<Integer> arrayResult = new ArrayList<Integer>();
        for (ArrayList<Integer> key : cleanHashMap.keySet()) {
            if (cleanHashMap.get(key).size() > 1) {
                if (key.size() > max) {
                    max = key.size();
                    arrayResult = key;
                } 
            }
        }

        System.out.println("Maximum shared value: " + max);
        ArrayList result = cleanHashMap.get(arrayResult);
        System.out.println("In files:");
        for (int i = 0; i < result.size(); i++ ) {
            System.out.println(((Node)result.get(i)).getFileName()); 
        }
        System.out.println("Position:") ;
        for (int i = 0; i < result.size(); i++ ) {
            System.out.println(((Node)result.get(i)).getPos()); 
        }
    }

        

    }

  
    


