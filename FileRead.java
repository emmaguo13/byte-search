import java.io.*;
import java.util.*;

class Node {
    private int fileName;
    private int position;

    public Node(int fileName, int position) {
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
    private HashMap<ArrayList<Integer>, ArrayList<Node>> store;
    private String[] files;

    public FullMax(String[] files) {
        this.store = new HashMap<ArrayList<Integer>, ArrayList<Node>>();
        this.files = files;
        for (int i = 0; i < files.length; i++) {
            addToMap(i);
        }
    }

    public HashMap<ArrayList<Integer>, ArrayList<Node>> completedHashMap() {
        return this.store;
    }

    private void addToMap(int num) {
        // takes in file name, sets the name as an integer
        String fname = this.files[num];
        int fnum = Integer.parseInt(fname.substring(fname.length() - 1, fname.length()));
        if (fnum == 0) {
            fnum = 10;
        }
        int position = 0;

        // reads from the file
        try {
            FileInputStream fileReader = new FileInputStream(fname);
            ArrayList<Integer> arrayBytes = new ArrayList<Integer>();
            while (fileReader.available() > 0) {
                int curr = fileReader.read();

                // checks to see if there is a whitespace. If there is, add the ArrayList of
                // bytes to the hash map, increment position.
                if (curr == 32) {
                    position++;
                    System.out.println(arrayBytes.size() + " " + fnum);
                    if (store.containsKey(arrayBytes)) {
                        store.get(arrayBytes).add(new Node(fnum, position));
                    } else {
                        ArrayList<Node> currPos = new ArrayList<Node>();
                        currPos.add(new Node(fnum, position));
                        store.put(arrayBytes, currPos);
                    }
                    arrayBytes = new ArrayList<Integer>();

                } else {
                    // if not a white space, add the byte to an array
                    arrayBytes.add(curr);
                }

            }
            fileReader.close();
        } catch (FileNotFoundException ffe) {
            System.err.println("Error: the file was not found!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

class FileRead {
    public static void main(String[] args) {

        FullMax data = new FullMax(args);
        HashMap<ArrayList<Integer>, ArrayList<Node>> cleanHashMap = data.completedHashMap();
        int max = 0;
        ArrayList<Integer> arrayResult = new ArrayList<Integer>();

        // traverses hashmap keys, checks if the key is in more than one file
        for (ArrayList<Integer> key : cleanHashMap.keySet()) {
            if (cleanHashMap.get(key).size() > 1) {

                // set max according to key size
                if (key.size() > max) {
                    max = key.size();
                    arrayResult = key;
                }
            }
        }

        // prints out result
        System.out.println("Maximum shared value: " + max);
        ArrayList result = cleanHashMap.get(arrayResult);
        System.out.println("In files:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(((Node) result.get(i)).getFileName());
        }
        System.out.println("Position relative to the first file:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(((Node) result.get(i)).getPos() - ((Node) result.get(0)).getPos());
        }
    }

}
