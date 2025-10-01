import java.util.Scanner;
import java.util.HashMap;

public class LRUPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        int[] frame = new int[frames];
        int[] lastUsed = new int[frames]; 
        int time = 0;
        int pageFaults = 0;

        // Initialize frame with -1 indicating empty frames
        for (int i = 0; i < frames; i++) frame[i] = -1;

        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];
            time++;
            boolean hit = false;

            // Check if page in frame (hit)
            for (int j = 0; j < frames; j++) {
                if (frame[j] == currentPage) {
                    lastUsed[j] = time; // Update last used time
                    hit = true;
                    break;
                }
            }

            if (!hit) {
                pageFaults++;
                // Find least recently used page index
                int lruIndex = 0;
                int minTime = lastUsed[0];
                for (int j = 1; j < frames; j++) {
                    if (lastUsed[j] < minTime) {
                        minTime = lastUsed[j];
                        lruIndex = j;
                    }
                }
                frame[lruIndex] = currentPage;
                lastUsed[lruIndex] = time;
            }
            // Print current frames after each reference
            System.out.print("Frames: ");
            for (int f : frame) {
                if (f != -1) System.out.print(f + " ");
            }
            System.out.println();
        }
        System.out.println("Total Page Faults: " + pageFaults);
        sc.close();
    }
}
