import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {

    private int numWorkers;
    private int[] jobs;
    Thread[] workers;

    // the solution
    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        workers = new Thread[numWorkers];
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    class Thread {
        int index;
        int curr;
        public Thread(int index, int curr) {
            this.index = index;
            this.curr = curr;
        }
    }

    private void shiftDown(int i) {
        int minIndex = i;

        int l = leftChild(i);
        if(l < numWorkers && workers[l] != null && workers[l].curr < workers[minIndex].curr) {
            minIndex = l;
        }
        int r = rightChild(i);
        if(r < numWorkers && workers[r] != null && workers[r].curr < workers[minIndex].curr) {
            minIndex = r;
        }

        if(r < numWorkers && workers[l] != null && workers[r] != null && l < numWorkers && workers[r].curr == workers[l].curr) {
            if(workers[r].index < workers[l].index)
                minIndex = r;
            else
                minIndex = l;
        }
        if(i != minIndex) {
            Thread tmp = workers[i];
            workers[i] = workers[minIndex];
            workers[minIndex] = tmp;
            shiftDown(minIndex);
        }
    }
    private void shiftUp(int i) {
        while((i > 0 && workers[parent(i)].curr > workers[i].curr ) ||
                (workers[parent(i)].curr == workers[i].curr && workers[parent(i)].index > workers[i].index)) {
            Thread tmp = workers[i];
            workers[i] = workers[parent(i)];
            workers[parent(i)] = tmp;
            i = parent(i);
        }
    }

    private int leftChild(int i) {
        return i*2+1;
    }

    private int rightChild(int i) {
        return 2*i+2;
    }

    private int parent(int i) {
        return i/2;
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        // priority queue --- min heap
        for(int i = 0; i < numWorkers; i++) {
            workers[i] = new Thread(i,0);
        }

        for(int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Thread bestWorker = workers[0];

            assignedWorker[i] = bestWorker.index;
            startTime[i] = bestWorker.curr;
            bestWorker.curr += duration;
            // Extract min
            workers[0] = workers[workers.length-1];
            workers[workers.length-1] = null;
            shiftDown(0);
            // Insert
            workers[workers.length-1] = bestWorker;
            shiftUp(workers.length-1);

        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
