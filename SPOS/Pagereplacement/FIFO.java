import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class FIFOPageReplacement {
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

        // Queue to represent frame contents using FIFO
        Queue<Integer> frameQueue = new LinkedList<>();
        int pageFaults = 0;

        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];
            // If page not present in frame, page fault occurs
            if (!frameQueue.contains(currentPage)) {
                // If frame is full, remove the oldest page
                if (frameQueue.size() == frames) {
                    frameQueue.poll();
                }
                // Add new page
                frameQueue.add(currentPage);
                pageFaults++;
            }
            // Print current frame contents after each reference
            System.out.print("Frames: ");
            for (int page : frameQueue) {
                System.out.print(page + " ");
            }
            System.out.println();
        }
        System.out.println("Total Page Faults: " + pageFaults);
        sc.close();
    }
}
