import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: suanfa
 * @description: 经典排序算法
 * @author: ZakL
 * @create: 2019-09-07 15:57
 **/
public class Sort {
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
        }
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

    
    public static void main(String[] args) {
        int[] a = {6, 3, 1, 5, 6, 6, 3, 5, 3, 26, 7, 23, 3};
        qsort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.print(i + "-");
        }
    }
}
