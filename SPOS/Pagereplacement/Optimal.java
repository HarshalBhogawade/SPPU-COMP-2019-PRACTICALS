import java.util.Scanner;
import java.util.Arrays;

public class OptimalPageReplacement {
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
        Arrays.fill(frame, -1);

        int pageFaults = 0;

        for (int i = 0; i < n; i++) {
            int current = pages[i];
            if (!isInFrame(frame, current)) {
                pageFaults++;
                int replaceIndex = findReplaceIndex(frame, pages, i + 1);
                frame[replaceIndex] = current;
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

    // Check if page in frame
    private static boolean isInFrame(int[] frame, int page) {
        for (int f : frame) {
            if (f == page) return true;
        }
        return false;
    }

    // Find frame to replace using optimal logic
    private static int findReplaceIndex(int[] frame, int[] pages, int start) {
        int replaceIndex = 0;
        int farthest = start;

        for (int i = 0; i < frame.length; i++) {
            int j;
            for (j = start; j < pages.length; j++) {
                if (frame[i] == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        replaceIndex = i;
                    }
                    break;
                }
            }
            if (j == pages.length) { // not found in future, best to replace this
                return i;
            }
        }
        return replaceIndex;
    }
}
