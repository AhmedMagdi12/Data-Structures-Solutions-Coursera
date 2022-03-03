
import java.util.*;
import java.io.*;

public class substring_equality {
    public class Solver {
        private String s;
        public Solver(String s) {
            this.s = s;
        }
        int m1 = 1000000007;
        int m2 = 1000000009;
        int x = 263;



        public boolean ask(int a, int b, int l,int[] hashTable1, int[] hashTable2) {
            int a1Hash1 = getHashValue(hashTable1,x,m1,a , l);
            int a1Hash2 = getHashValue(hashTable2,x,m2,a , l);
            int a2Hash1 = getHashValue(hashTable1,x,m1,b , l);
            int a2Hash2 = getHashValue(hashTable2,x,m2,b , l);

            if(a1Hash1 == a2Hash1 && a1Hash2 == a2Hash2)
                return true;
            else
                return false;
        }

        public int[] hashTable(String s, int p, int x) {
            int[] hashes = new int[s.length()+1];
            hashes[0] = 0;
            for(int i = 1; i < s.length() + 1; i++) {
                hashes[i] = (hashes[i-1] * x + (int)s.charAt(i-1)) % p;
            }
            return hashes;
        }

        public int getHashValue(int hashTable[], int x, int p, int start, int len) {
            int y = (int)(Math.pow(x,len) % p);
            int hashValue = (hashTable[start+len] - y * hashTable[start]) % p;
            return hashValue;
        }


    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String s = in.next();
        int q = in.nextInt();
        int m1 = 1000000007;
        int m2 = 1000000009;
        int x = 263;
        Solver solver = new Solver(s);
        int hashTable1[] = solver.hashTable(s, m1,x);
        int hashTable2[] = solver.hashTable(s, m2,x);

        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int l = in.nextInt();
            out.println(solver.ask(a, b, l, hashTable1,hashTable2) ? "Yes" : "No");
        }
        out.close();
    }

    static public void main(String[] args) throws IOException {
        new substring_equality().run();
    }

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
}
