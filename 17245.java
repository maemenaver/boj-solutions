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

        System.out.println("총합 : " + sum);

        br.close();

        for (int i = 1; i <= max; i++) {
            if (hash[i]) {
                hashMap[previous] = i;
                previous = i;
            }
        }

        for (int i = hashMap[0]; i <= max; i++) {
            System.out.println(i + "의 개수 : " + offset[i]);
        }

        for (int i = hashMap[0]; i <= max && sum > coolingCount * 2; i = hashMap[i]) {
            cycleCount++;
            for (int j = hashMap[i]; j <= max && sum > coolingCount * 2; j = hashMap[j]) {
                coolingCount += offset[j];
                System.out.println(coolingCount);
            }
        }

        bw.write(Integer.toString(cycleCount));

        bw.flush();
        bw.close();
    }
}