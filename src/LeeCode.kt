package LeeCode

import kotlin.collections.HashMap
import kotlin.math.max

/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
class Solution {
    fun singleNumber(nums: IntArray): Int {
        var res = 0
        for (i in 0 until nums.size) {
            res = res xor nums[i]
        }
        return res
    }

    fun countPrimes(n: Int): Int {
        var count = 0
        for (i in 1 until n) {
            if (isPrime(i)) {
                count++
            }
        }
        return count
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 1) {
            return false
        }
        for (i in 2 until n) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }

    /*
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 */

    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }
        val map: HashMap<Char, Char> = hashMapOf()

        for (i in 1 until s.length) {
            if (!map.containsKey(s[i])) {
                if (map.containsValue(t[i])) {
                    return false
                }
                map[s[i]] = t[i]
            } else {
                if (map[s[i]] != t[i]) {
                    return false
                }
            }
        }
        return true
    }
}

//冒泡排序
fun bubbleSort(nums: IntArray): IntArray {
    for (i in 1..nums.size) {
        //保证每次循环的最后的一个值是本次循环的最大值
        for (j in 0..nums.size - 1 - i) {
            if (nums[j] > nums[j + 1]) {
                val temp = nums[j]
                nums[j] = nums[j + 1]
                nums[j + 1] = temp
            }
        }
    }
    return nums
}

//选择排序
fun selectionSort(nums: IntArray): IntArray {
    for (i in 0 until nums.size) {
        var a: Int = nums[i]
        //每次循环最小数的索引 每次开始时为第一个数的索引
        var sign: Int = i
        for (j in i + 1 until nums.size) {
            if (a > nums[j]) {
                a = nums[j]
                sign = j
            }
        }
        nums[sign] = nums[i]
        nums[i] = a
    }
    return nums
}

//插入排序
fun insertionSort(nums: IntArray): IntArray {
    for (i in 1 until nums.size) {
        val a = nums[i]
        if (a < nums[i - 1]) {
            for (p in i - 1 downTo 0) {
                if (a < nums[p]) {
                    val y = nums[p]
                    nums[p] = a
                    nums[p + 1] = y
                } else {
                    break
                }
            }
        }
    }
    return nums
}

//计数排序（Counting Sort）
fun countingSort(nums: IntArray): MutableList<Int> {
    val max = nums.sorted().get(nums.size - 1)
    val c = Array<MutableList<Int>>(max + 1) { mutableListOf() }
    for (i in nums) {
        c[i].add(i)
    }
    val list = mutableListOf<Int>()
    c.forEach {
        for (i in it) {
            list.add(i)
        }
    }
    return list
}

//快速排序
fun quickSortUnit(nums: IntArray, s: Int, e: Int): Int {
    val num = nums[s]
    var i = s
    var j = e
    while (i < j) {
        while (i < j) {
            if (nums[j] < num) {
                nums[i] = nums[j]
                break
            }
            j--
        }
        while (i < j) {
            if (nums[i] >= num) {
                nums[j] = nums[i]
                break
            }
            i++
        }
    }
    nums[i] = num
    return i
}

fun sort(nums: IntArray, s: Int, e: Int) {
    if (s < e) {
        val m = quickSortUnit(nums, s, e)
        sort(nums, s, m - 1)
        sort(nums, m + 1, e)
    }
}

//快乐数
fun isHappy(n: Int): Boolean {
    var b = n
    val nums = mutableListOf<Int>()
    while (b > 0) {
        val a = b % 10
        b = (b - a) / 10
        nums.add(a)
    }
    val c = nums.run { this.sumBy { it * it } }
    if (c == 1) {
        return true
    } else if (c in 2..4) {
        return false
    }
    return isHappy(c)
}

//Excel表名称
fun convertToTitle(n: Int): String? {
    if (n == 0) {
        return ""
    }
    var a = n
    var s = ""
    while (a > 0) {
        a--
        s = s.plus((a % 26 + 65).toChar())
        a /= 26
    }
    return s.toString().reversed()
}

fun reverseString(s: CharArray) {
    var i = 0
    var j = s.size - 1
    while (i < j) {
        val a = s[i]
        s[i] = s[j]
        s[j] = a
        i++
        j--
    }
}

fun sumRange(i: Int, j: Int): Int {
    val nums = intArrayOf(-2, 0, 3, -5, 2, -1)
    var n1 = 0
    var n2 = 0
    if (i > 0) {
        for (a in 0 until i) {
            n1 += nums[a]
        }
    }
    for (b in 0..j) {
        n2 += nums[b]
    }
    return n2 - n1
}

fun canWinNim(n: Int): Boolean {
    return n % 4 != 0
}

//给定一个整数，写一个函数来判断它是否是 3 的幂次方。
//当一个数是是 3 的幂次方的时候，那么3一定是这个数的质因子，而1162261467是int型
//数里面的3的最大幂次方的值，这个数一定是3的n次方的m次方
fun isPowerOfThree(n: Int): Boolean {
    return n > 0 && 1162261467 % n == 0
}

fun longestPalindrome(s: String): String {
    if (s.isEmpty()) {
        return ""
    }
    var start = 0
    var end = 0
    for (i in 0 until s.length) {
        val len1 = expandAroundCenter(s, i, i)
        val len2 = expandAroundCenter(s, i, i + 1)
        var len = max(len1, len2)
        if (len > end - start) {
            start = 1 - (len - 1) / 2
            end = i + len / 2
        }
    }
    return s.substring(start, end + 1)
}

fun expandAroundCenter(s: String, left: Int, right: Int): Int {
    var L = left
    var R = right
    while (L > 0 && R < s.length && s[L] == s[R]) {
        L--
        R++
    }
    return R - L - 1
}

/*
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
注意你不能在买入股票前卖出股票。
示例 1:
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
fun maxiProfit(prices: IntArray): Int {
    if (prices.size <= 1) {
        return 0
    }
    var min = prices[0]
    var max = 0
    for (i in 1 until prices.size) {
        max = max(max, prices[i] - min)
        min = kotlin.math.min(min, prices[i])
    }
    return max
}

/*
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */
fun generate(numRows: Int): List<Array<Int>> {
    val result = mutableListOf<Array<Int>>()
    if (numRows == 0) {
        return result
    }
    val firstArray = arrayOf(1)
    result.add(firstArray)
    for (i in 1 until numRows) {
        val tempArray = Array(i + 1) { 0 }
        tempArray[0] = 1
        tempArray[tempArray.size - 1] = 1
        for (j in 1 until tempArray.size - 1) {
            if (result[i - 1].size > 1) {
                tempArray[j] = result[i - 1][j - 1] + result[i - 1][j]
            }
        }
        result.add(tempArray)
    }
    return result
}

/**
 * 获取杨辉三角的指定行
 */
fun getRow(rowIndex: Int): Array<Int> {
    var firstArray = arrayOf(1)
    for (i in 1 until rowIndex + 1) {
        val tempArray = Array(i + 1) { 0 }
        tempArray[0] = 1
        tempArray[tempArray.size - 1] = 1
        for (j in 1 until tempArray.size - 1) {
            tempArray[j] = firstArray[j - 1] + firstArray[j]
        }
        firstArray = tempArray
    }
    return firstArray
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    val wrapList = mutableListOf<MutableList<Int>>()
    levelMaker(wrapList, root, 0)
    return wrapList.reversed()
}

fun levelMaker(list: MutableList<MutableList<Int>>, root: TreeNode?, level: Int) {
    if (root == null) return
    //当层级大于总list的长度的时候说明,还有新的层级,进行添加
    if (level >= list.size) {
        list.add(level, mutableListOf())
    }
    levelMaker(list, root.left, level + 1)
    levelMaker(list, root.right, level + 1)
    list[level].add(root.`val`)
}

fun main() {
//    var nums = arrayListOf(2, 3, 1, 5, 6, 6, 3, 5, 3, 26, 7, 23, 3)

    insertionSort(intArrayOf(2, 3, 1, 5, 6, 6, 3, 5, 3, 26, 7, 23, 3)).forEach { print("$it ") }


}

