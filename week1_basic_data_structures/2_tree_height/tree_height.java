import java.util.*;
import java.io.*;

public class tree_height {
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

    public class TreeHeight {
        int n;
        int parent[];

        int heights[];
        ArrayList<Integer>[] tree;
        int root;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];

            // build the tree
            tree = new ArrayList[n];
            heights = new int[n];

            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                int parent_index = in.nextInt();
                parent[i] = parent_index;

                if(parent_index == -1) {
                    root = i;
                    heights[i] = 1;
                }
                else
                    tree[parent_index].add(i);
            }
        }

        int computeHeight() {
            Queue<Integer> q = new LinkedList<>();
            q.add(root);

            while(!q.isEmpty()) {
                int node = q.poll();
                for(int child : tree[node]) {
                    q.add(child);
                    heights[child] = heights[node] + 1;
                }
            }
            int maxHeight = 0;
            for(int i : heights) {
                if(i > maxHeight)
                    maxHeight = i;
            }

            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
