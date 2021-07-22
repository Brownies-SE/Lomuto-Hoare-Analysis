import java.util.Arrays;

class Quicksort {

static void Swap(int[] array, int position1, int position2) { 
    
    int temp = array[position1];  
      
    array[position1] = array[position2];  
      
    array[position2] = temp;  
} 
static int hoarepartition(int[]arr, int low, int high){

    int pivot = arr[low]; 
    int i = low - 1, j = high + 1; 
  
    while (true) { 
        
        do { 
            i++; 
        } while (arr[i] < pivot); 

        do { 
            j--; 
        } while (arr[j] > pivot); 
     
        if (i >= j) 
            return j; 
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp;  
    } 
}

static int lomotupartition(int []arr, int low, int high) { 
    int pivot = arr[high]; 
      
     
    int i = (low - 1); 
  
    for (int j = low; j <= high- 1; j++) { 
        
        if (arr[j] <= pivot) {

            i++; 
            Swap(arr, i, j); 
        } 
    } 
    Swap(arr, i + 1, high); 
    return (i + 1); 
} 
  
public static int medianPivot(int median[], int low, int high) {

    int first = median[low];
    int last = median[median.length - 1];
    int mid = (high) / 2;

    int[] sortingArr = { median[low], median[mid], median[high] };

    Arrays.sort(sortingArr);

    int middleValue = sortingArr[1];

    // swap with the last to serve as pivot
    int temp = median[high];
    median[high] = middleValue;
    if (middleValue == median[low]) {
        median[low] = temp;
    } else if (middleValue == median[mid]) {
        median[mid] = temp;
    }

    return lomotupartition(median, low, high);

}

// ----------------------------------------------------------------------
/*  method for medianQuicksort */
public static void medianQuickSort(int median[], int low, int high) {
    if (low >= high)
        return;

    if (low < high) {

        int pi = medianPivot(median, low, high);

            quickSortLomotu(median, low, high);

    }
}

static void quickSortLomotu(int []arr, int low,  int high) { 
    if (low < high) {

        int pi = lomotupartition(arr, low, high);
   
        quickSortLomotu(arr, low, pi - 1); 
        quickSortLomotu(arr, pi + 1, high); 
    } 
} 
  
static void quickSortHoare(int []duplicate, int low,  int high) { 
    if (low < high) {

        int hi = hoarepartition(duplicate, low, high);
   
        quickSortHoare(duplicate, low, hi);
        quickSortHoare(duplicate, hi + 1, high);
    } 
} 

static void insertionSort(int[] insert_dup, int low, int n) {

    for (int i = low + 1; i <= n; i++)
		{
			int value = insert_dup[i];
            int j = i;

            while (j > low && insert_dup[j - 1] > value) {
                insert_dup[j] = insert_dup[j - 1];
                j--;
            }

            insert_dup[j] = value;
		}
}

static void insert_quickhoare(int[] insert_dup, int low, int high) {

    while (low < high)
		{
			// do insertion sort if 10 or smaller
			if(high - low < 500)
			{
				insertionSort(insert_dup, low, high);
                break;
            } else {
                int pivot = hoarepartition(insert_dup, low, high);

                // tail call optimizations - recur on smaller sub-array
                if (pivot - low < high - pivot) {
                    insert_quickhoare(insert_dup, low, pivot - 1);
                    low = pivot + 1;
                } else {
                    insert_quickhoare(insert_dup, pivot + 1, high);
					high = pivot - 1;
				}
			}
		}

}

static void insert_quicklomotu(int[] IQL, int low, int high) {

    while (low < high)
    {
        // do insertion sort if 10 or smaller
        if(high - low < 500)
        {
            insertionSort(IQL, low, high);
                break;
            } else {
                int pivot = lomotupartition(IQL, low, high);

                // tail call optimizations - recur on smaller sub-array
                if (pivot - low < high - pivot) {
                    insert_quicklomotu(IQL, low, pivot - 1);
                    low = pivot + 1;
                } else {
                    insert_quicklomotu(IQL, pivot + 1, high);
                high = pivot - 1;
            }
        }
    }

}


static void printArray(int []arr, int size) {

    int i; 
        for (i = 0; i < size; i++) 
        System.out.print(" " + arr[i]); 
        System.out.println(); 
} 
  
// Main 
public static void main (String[] args) {

    long start;
    long end;
    long lomotuTime;
    long hoareTime;
    //long insert_quick_Time;
    long insert_IQL_Time;
    long medianQS;

    int [] arr = new int [1000000];

    for (int j = 0; j < 5; j++) {

        for(int i = 0; i < arr.length; i++) {
        arr[i] = (int) (Math.random() * 10000);
        }
 
    int n = arr.length;
    int duplicate[] = Arrays.copyOf(arr, n);
    //int insert_dup[] = Arrays.copyOf(arr, n);
    int IQL[] = Arrays.copyOf(arr, n);
    int median[] = Arrays.copyOf(arr, n);

    start = System.nanoTime();
    quickSortLomotu(arr, 0, n-1);
    end = System.nanoTime(); 
    lomotuTime = (end - start);
    double lomotuSeconds = (double) (lomotuTime / 1000000000.0);


    start = System.nanoTime();
    quickSortHoare(duplicate, 0, n-1);
    end = System.nanoTime();
    hoareTime = (end - start); 
    double hoareSeconds = (double) (hoareTime / 1000000000.0); 

    /*start = System.nanoTime();
    insert_quickhoare(insert_dup, 0, n-1);
    end = System.nanoTime();
    insert_quick_Time = (end - start);
    double inserthoareSeconds = (double) (insert_quick_Time / 1000000000.0); */

    start = System.nanoTime();
    insert_quicklomotu(IQL, 0, n-1);
    end = System.nanoTime();
    insert_IQL_Time = (end - start);
    double insertlomotuSeconds = (double) (insert_IQL_Time / 1000000000.0);

    start = System.nanoTime();
    medianQuickSort(median, 0, n - 1);
    end = System.nanoTime();
    medianQS = (end - start);
    double medianquicksort = (double) (medianQS / 1000000000.0);


    System.out.println("Lomotu Quicksort:  "+ lomotuSeconds +       " seconds ");
    System.out.println("Hoare Quicksort:   "+ hoareSeconds +        " seconds ");
    //System.out.println("IQH partition:     "+ inserthoareSeconds +  " seconds ");
    System.out.println("IQL partition:     "+ insertlomotuSeconds + " seconds " );
    System.out.println("Median of 3:       "+ medianquicksort +     " seconds  " + " \n");

    //printArray(arr, n); 
    //printArray(duplicate, n);
    //printArray(insert_dup, n);

    }   

    
} 
} 