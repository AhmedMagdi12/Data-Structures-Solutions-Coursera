import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    Contact table[] = new Contact[10000000];

//    public int hashInt(int x) {
//
//        int p = 10000019;
//        int a = 34;
//        int b = 2;
//        int m = 1000;
//        int result = (a * x + b) % p;
//        return result % m;
//
//    }
//    public int hashStr(String s) {
//        int hash = 0;
//        int x = 31;
//        int i = s.length() - 1;
//        while(i >= 0){
//            hash = hash * 31 + s.charAt(i);
//            i--;
//        }
//        return hash % 32;
//    }
    private void processQuery(Query query) {

        if (query.type.equals("add")) {

        if(table[query.number] == null) {
            table[query.number] = new Contact(query.name, query.number);
        } else {
            table[query.number].name = query.name;
        }

        } else if (query.type.equals("del")) {

            table[query.number] = null;

        } else {

            String response = "not found";
            if(table[query.number] != null)
                response = table[query.number].name;
            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
