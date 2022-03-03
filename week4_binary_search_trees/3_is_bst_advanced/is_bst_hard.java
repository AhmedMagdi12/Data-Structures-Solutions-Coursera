import java.util.*;
import java.io.*;

public class is_bst_hard {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }
        boolean res = true;
        long  min = (long)Integer.MIN_VALUE - 1;
        long max =  (long)Integer.MAX_VALUE +1;
        boolean isBinarySearchTree() {
          // Implement correct algorithm here
          if(tree.length == 0)
              return res;
          traverse(tree[0],min,max);
          return res;
        }
;
        void traverse(Node curr,long min, long max) {
            if(curr.left != -1){
                traverse(tree[curr.left],min,curr.key);
            }
            
            if(curr.key < min || curr.key >= max) {
                res = false;
//                System.out.println(curr.key+ 
//                       " max "+ max + " min " + min );
            }
            if(curr.right != -1) {
                traverse(tree[curr.right],curr.key,max);
            }   
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
