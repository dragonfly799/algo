package timus.acm;

import java.io.*;

public class Main_1026 {

    private static final int SIZE = 5000;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] db = new int[SIZE];
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(in.readLine());
            db[num - 1]++;
        }

        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            int sumBefore = sum;
            sum += db[i];
            db[i] += sumBefore;
        }

        in.readLine(); // skip ###

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(in.readLine());
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(in.readLine());
            int res = find(db, num);
            out.println(res + 1);
        }
        out.flush();
    }

    private static int find(int[] db, int target) {
        int l = 0;
        int r = db.length - 1;
        while (l <= r) {
            int i = (l + r) / 2;
            if (db[i] >= target && (i == 0 || db[i - 1] < target)) {
                return i;
            }
            if (db[i] >= target) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
        return -1;
    }
}
