import java.util.Random;
public class SortingBenchmark {
    static void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    static void merge(int arr[], int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }
        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }
    static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    static void heapSort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    static void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
    static void benchmark(int size) {
        Random rand = new Random();
        int original[] = new int[size];
        for (int i = 0; i < size; i++)
            original[i] = rand.nextInt(100000);
        int mergeArr[] = original.clone();
        int quickArr[] = original.clone();
        int heapArr[] = original.clone();
        long start, end;
        start = System.nanoTime();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        end = System.nanoTime();
        System.out.println("Merge Sort Time : "
                + (end - start) / 1000000.0 + " ms");
        start = System.nanoTime();
        quickSort(quickArr, 0, quickArr.length - 1);
        end = System.nanoTime();
        System.out.println("Quick Sort Time : "
                + (end - start) / 1000000.0 + " ms");
        start = System.nanoTime();
        heapSort(heapArr);
        end = System.nanoTime();
        System.out.println("Heap Sort Time  : "
                + (end - start) / 1000000.0 + " ms");
    }
    public static void main(String[] args) {
        System.out.println("=== Sorting Benchmark ===");
        System.out.println("\nDataset Size = 1000");
        benchmark(1000);
        System.out.println("\nDataset Size = 10000");
        benchmark(10000);
        System.out.println("\nDataset Size = 100000");
        benchmark(100000);
    }
}