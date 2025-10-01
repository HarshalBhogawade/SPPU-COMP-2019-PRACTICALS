import java.util.Scanner;

public class roundrobin {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of processes-");
        int n = scan.nextInt();

        int bt[] = new int[n];         // burst times
        int rem_bt[] = new int[n];     // remaining burst times
        int wt[] = new int[n];         // waiting times
        int tt[] = new int[n];         // turnaround times

        System.out.println("Enter burst times-");
        for (int i = 0; i < n; i++) {
            bt[i] = scan.nextInt();
            rem_bt[i] = bt[i];         // initialize all remaining times
        }

        System.out.println("Enter time quantum-");
        int qt = scan.nextInt();

        int t = 0; // Current time
        boolean done;

        // Loop until all processes are done
        do {
            done = true;
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > qt) {
                        // Run for quantum, update clock and remaining burst
                        t += qt;
                        rem_bt[i] -= qt;
                    } else {
                        // Run for rem_bt[i], then calculate times
                        t += rem_bt[i];
                        wt[i] = t - bt[i];
                        rem_bt[i] = 0;
                    }
                }
            }
        } while (!done);

        // Calculate turnaround times
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
