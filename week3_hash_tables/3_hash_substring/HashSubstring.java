
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }




    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        int p = 1000000019;
        int x = 31;

        List<Integer> occurrences = new ArrayList<Integer>();
        int pHash = polyHash(s, p, x);


        int[] H = preComputeHashes(t, m, p, x);

        for (int i = 0; i <= n-m; i++) {
            if(pHash != H[i])
                continue;
            if(areEqual(t.substring(i, i+m), s))
                occurrences.add(i);

        }
        return occurrences;
    }


    private static boolean areEqual(String s1, String s2) {
        if(s1.equals(s2))
            return true;
        return false;
    }

    private static int polyHash(String s , int p, int x) {
        int hash = 0;
        int i = s.length() - 1;
        while(i >= 0) {
            hash = (hash * x + s.charAt(i)) % p;
            i--;
        }
        return hash;
    }

    private static int[] preComputeHashes(String T, int patternLength,int p,int x) {
        int len = T.length();
        if(patternLength > T.length()) {
            int arr[] = new int[0];
            return arr;
        }
        int[] H = new int[len - patternLength + 1];
        String s = T.substring(len - patternLength , len);
        H[len - patternLength] = polyHash(s,p,x);
        int y = 1;
        for(int i = 1; i <= patternLength; i++) {
            y = (y * x) % p;
        }
        for(int i = len-patternLength-1; i >= 0; i--) {
            H[i] = (x * H[i+1] + (int)T.charAt(i) - y * (int)T.charAt(i + patternLength))% p;
        }

        return H;
    }
    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
