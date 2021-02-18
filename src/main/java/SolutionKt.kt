import kotlin.math.max

class SolutionKt {
    fun minimumOperations(leaves: String): Int {
        var ret = 0
        if (leaves[0] == 'y') ret++
        if (leaves[leaves.length - 1] == 'y') ret++
        var cnt = 0
        for (i in 1 until leaves.length - 1) if (leaves[i] == 'y') cnt++
        if (cnt == 0) return 1 + ret
        var cur = 0
        var mx = 0
        for (i in 1 until leaves.length - 1) {
            if (leaves[i] == 'y') {
                cur++
            } else {
                if (cur > 0) cur--
            }
            mx = max(cur, mx)
        }
        return cnt - mx + ret
    }

    //给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
    //输入: [1,2,3,4,5,6,7] 和 k = 3
    //输出: [5,6,7,1,2,3,4]
    //向右旋转 1 步: [7,1,2,3,4,5,6]
    //向右旋转 2 步: [6,7,1,2,3,4,5]
    //向右旋转 3 步: [5,6,7,1,2,3,4]
    //
    //输入: [-1,-100,3,99] 和 k = 2
    //输出: [3,99,-1,-100]
    //向右旋转 1 步: [99,-1,-100,3]
    //向右旋转 2 步: [3,99,-1,-100]

    //    public void rotate_1(int[] nums, int k) {
    //        int n = nums.length;
    //        k %= n;
    //        for (int i = 0; i < k; i++) {
    //            int temp = nums[n - 1];
    //            for (int j = n - 1; j > 0; j--) {
    //                nums[j] = nums[j - 1];
    //            }
    //            nums[0] = temp;
    //        }
    //    }

    fun rotate(nums: IntArray, k: Int) {
        val n = nums.size
        val K = k % n;
        for (i in 0 until K) {
            val tmp = nums[n - 1]
            for (j in n - 1 downTo 1) {
                nums[j] = nums[j - 1]
            }
            nums[0] = tmp;
        }
    }


}

fun main() {
    val intArrayOf = intArrayOf(-1, -100, 3, 99);
    SolutionKt().rotate(intArrayOf, 2);
    for (i in intArrayOf) {
        println(i)
    }


}