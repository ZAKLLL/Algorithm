/**
 * @Classname LeetCode
 * @Description TODO
 * @Date 2020/4/25 18:29
 * @Created by Administrator
 */
fun isHappy(n: Int): Boolean {
    print(n)
    var num = n
    val nums = mutableListOf<Int>()
    while (num > 0) {
        nums.add(num % 10)
        num /= 10
    }
    val c = nums.sumBy { it * it }
    if (c == 1) return true
    if (c in 2..4) return false
    return isHappy(c)
}

fun main() {
    val happy = isHappy(5)
    print(happy)
}