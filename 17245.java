import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long sum = 0;
        short limit = Short.parseShort(br.readLine());
        int[] offset = new int[10000001];
        boolean[] hash = new boolean[10000001];

        int[] hashMap = new int[10000001];

        int previous = 0;
        int max = 0;

        int coolingCount = 0;
        int cycleCount = 0;

        for (short i = 0; i < limit; i++) {
            String s = new String(br.readLine());
            StringTokenizer st = new StringTokenizer(s);
            for (short j = 0; j < limit; j++) {
                int input = Integer.parseInt(st.nextToken());
                offset[input]++;
                hash[input] = true;

                max = Math.max(max, input);
                sum += input;
            }
        }

        br.close();

        for (int i = 1; i <= max; i++) {
            if (hash[i]) {
                if (i == max) {
                    hashMap[i] = -1;
                }
                hashMap[previous] = i;
                previous = i;
            }
        }

        while (max > 0 && sum > coolingCount * 2) {
            cycleCount++;

            for (int j = hashMap[0], prev = 0; j > -1 && sum > coolingCount * 2; j = hashMap[j - 1]) {
                if (j == 0) {
                    break;
                } else if (j == max) {
                    max--;
                }
                // System.out.println(j + "의 hashMap : " + hashMap[j]);

                coolingCount += offset[j];
                offset[j - 1] = offset[j];
                offset[j] = 0;
                hashMap[j - 1] = hashMap[j];
                hashMap[j] = 0;

                if (j - 1 != 0)
                    hashMap[prev] = j - 1;

                prev = j - 1;

                // System.out.println(prev + "의 hashMap : " + hashMap[prev]);
                // System.out.println(j + " " + coolingCount);

                if (hashMap[j - 1] == -1)
                    break;
            }
        }

        bw.write(Integer.toString(cycleCount));

        bw.flush();
        bw.close();
    }
}