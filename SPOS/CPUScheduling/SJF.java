import java.util.Scanner;

public class sjf {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of processes-");
        int n = scan.nextInt();

        int bt[] = new int[n]; // burst times
        int wt[] = new int[n]; // waiting times
        int tt[] = new int[n]; // turnaround times

        System.out.println("Enter burst times-");
        for (int i = 0; i < n; i++) {
            bt[i] = scan.nextInt();
        }

        // Sort burst times in ascending order for SJF
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (bt[i] > bt[j]) {
                    int temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }

        wt[0] = 0; // first process always has zero waiting

        // Calculate waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i-1] + bt[i-1];
        }

        // Calculate turnaround time
        for (int i = 0; i < n; i++) {
            tt[i] = wt[i] + bt[i];
        }

        // Calculate averages
        float total_wt = 0, total_tt = 0;
        for (int i = 0; i < n; i++) {
            total_wt += wt[i];
            total_tt += tt[i];
        }
        System.out.println("Average waiting time and Average turnaround time respectively-");
        System.out.println(total_wt/n);
        System.out.println(total_tt/n);

        scan.close();
    }
}
