import java.util.Scanner;

public class priority {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of processes-");
        int n = scan.nextInt();

        int bt[] = new int[n];
        int pr[] = new int[n];
        int wt[] = new int[n];
        int tt[] = new int[n];

        // Input burst times and priorities
        System.out.println("Enter burst times-");
        for (int i = 0; i < n; i++) {
            bt[i] = scan.nextInt();
        }
        System.out.println("Enter priorities (lower value = higher priority)-");
        for (int i = 0; i < n; i++) {
            pr[i] = scan.nextInt();
        }

        // Sort by priority (ascending)
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (pr[i] > pr[j]) {
                    int temp = pr[i]; pr[i] = pr[j]; pr[j] = temp;
                    temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                }
            }
        }

        wt[0] = 0; // first process always has zero waiting
        
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i-1] + bt[i-1];
        }

        for (int i = 0; i < n; i++) {
            tt[i] = wt[i] + bt[i];
        }

        // Output averages
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
