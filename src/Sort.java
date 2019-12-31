import java.util.Arrays;
import java.util.Random;
import java.util.jar.JarEntry;

/**
 * @program: suanfa
 * @description: 经典排序算法
 * @author: ZakL
 * @create: 2019-09-07 15:57
 **/
public class Sort {
    private static Random random = new Random();

    /**
     * 冒泡排序
     *
     * @param nums
     */
    private static int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    nums[j + 1] = nums[j] + nums[j + 1];
                    nums[j] = nums[j + 1] - nums[j];
                    nums[j + 1] = nums[j + 1] - nums[j];
                }
            }
        }//
        return nums;
    }

    /**
     * 选择排序
     *
     * @param nums
     */
    private static void selecttionSort(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (a > nums[j]) {
                    a = nums[j];
                    minIndex = j;
                }
            }
            nums[minIndex] = nums[i];
            nums[i] = a;
        }
    }

    /**
     * 插入排序
     *
     * @param nums
     */
    private static void InsertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            if (temp < nums[i - 1]) {
                for (int j = i; j >= 0; j--) {
                    if (temp > nums[j] || j == 0) {
                        //循环后移一位
                        if (j != 0) {
                            if (i - j >= 0) System.arraycopy(nums, j, nums, j + 1, i - j);
                            nums[j + 1] = temp;
                        } else {
                            System.arraycopy(nums, 0, nums, 1, i);
                            nums[0] = temp;
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 快速排序算法
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int[] qsort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) j--;
            while ((i < j) && (arr[i] < pivot)) i++;
            if ((arr[i] == arr[j]) && (i < j)) i++;
            else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) arr = qsort(arr, start, i - 1);
        if (j + 1 < end) arr = qsort(arr, j + 1, end);
        return arr;
    }

    /**
     * 优化版的快排,引入荷兰国旗解决
     *
     * @param arr
     */
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            //随机快排
            Utils.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                Utils.swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                Utils.swap(arr, --more, L);
            } else {
                L++;
            }
        }
        //因为取的是最右边的值为划分位,所以在结束后需要将基准值放在中间,与最接近基准位的较大值交换即可
        Utils.swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        process(arr, 0, arr.length - 1);
    }

    /**
     * 对lr范围内进行左右分别排序，使中点左右两边都有序
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void process(int[] arr, int l, int r) {
        if (l == r) return;
        int mid = (l + r) / 2;
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, r);
    }

    /**
     * 使用辅助数组对已经左右拍好序的数组进行整体排序
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void merge(int[] arr, int l, int r) {
        int[] helpArr = new int[r - l + 1];
        int mid = (r + l) / 2;
        int p = l;
        int q = mid + 1;
        int index = 0;
        while (p <= mid && q <= r) {
            helpArr[index++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        }
        //左右一定有一个越界
        while (p <= mid) {
            helpArr[index++] = arr[p++];
        }
        while (q <= r) {
            helpArr[index++] = arr[q++];
        }
        if (helpArr.length >= 0) System.arraycopy(helpArr, 0, arr, l, helpArr.length);
    }


    /**
     * 二叉树堆排序，使用数组表示
     *
     * @param ints
     */
    public static void binarytreeheapsort(int[] ints) {
        int end = ints.length;
        while (end >= 3) { //意味着数组中至少还有一个父节点一个子节点，任然需要比较

            //数组的第一个数为0，不表示任何数，方便计算
            //循环次数为父节点个数=(数组长度L-1)/2
            for (int i = (end - 1) / 2; i >= 1; i--) {

                //假设最大儿子为左节点(是为了避免无右节点的情况，超出数组边界）
                int maxindex = i * 2;
                //如果右儿子存在，且右儿子的值大于左儿子，则最大儿子索引变成maxindex+1
                if (maxindex + 1 < end && ints[maxindex + 1] > ints[maxindex]) {
                    maxindex++;
                }
                //判断最大儿子和父节点谁大
                if (ints[maxindex] > ints[i]) {
                    int temp = ints[maxindex];
                    ints[maxindex] = ints[i];
                    ints[i] = temp;
                }
            }
            //将顶级父节点与最后一个节点替换，并剔除最后一个节点继续循环
            int a = ints[1];
            ints[1] = ints[end - 1];
            ints[end - 1] = a;
            end--;
        }
    }


    public static int[] getRandomArr(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(range);
        }
        return arr;
    }

    public static boolean isArrEq(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }


    public static void test() {
        int[] randomArr = getRandomArr(20, 56);
        int[] clone = randomArr.clone();

        for (int i : randomArr) {
            System.out.print(i + "\t");
        }
        System.out.println();
        mergeSort(randomArr);

        for (int i : randomArr) {
            System.out.print(i + "\t");
        }
        System.out.println();

        Arrays.sort(clone);
        if (isArrEq(randomArr, clone)) {
            System.out.println("Nice Job");
        }

    }

    public static void main(String[] args) {
        test();
    }
}
