import java.util.Arrays;

/**
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * myMethodSecondHalf([-1,0,3,5,9,12])
 **/

class Solution {
  public static int search1(int[] nums, int target) {
    /*
     * search1 is my first trial of the binary search, aimed to use it without
     * recursion this time.
     * After code being completed, I searched for a bit and concluded that some of
     * the conditional checks in this code are not necessary and code could be
     * written leaner.
     */
    if (nums.length == 0) {
      return -1;
    }

    int start = 0;
    int end = nums.length - 1;

    if (!(nums[start] <= target && target <= nums[end])) {
      return -1;
    }

    while (true) {
      if (Math.abs(end - start) == 1) {
        if (nums[end] == target) {
          return end;
        } else if (nums[start] == target) {
          return start;
        } else
          return -1;
      }
      int mid = (end + start) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] <= target && target <= nums[end]) {
        start = mid;
      } else if (nums[start] <= target && target <= nums[mid]) {
        end = mid;
      }
    }
  }

  public static int search2(int[] nums, int target) {
    /*
     * Improved version of search1 after looking up on the internet.
     * Biggest difference is; instead of declaring new start/end as the mid,
     * (which is already checked by the algorithm if its equal to target or not)
     * declaring new start/end as mid +/-1 would allow;
     * 1) Preventing while for infinite looping,
     * 2) Edge cases can be solved this time without additional checking (start and
     * end would converge on top of each other, hence the start = end check on
     * while),
     * 3) If in any case a non existant number is searched for, else-break case
     * would take care of it.
     */
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
      int mid = (end + start) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] <= target && target <= nums[end]) {
        start = mid + 1;
      } else if (nums[start] <= target && target <= nums[mid]) {
        end = mid - 1;
      } else
        break;
    }
    return -1;

  }

  public static void main(String[] args) {
    int[] arr = { -1, 0, 3, 5, 9, 12 };
    int myTarget = 5;
    System.out
        .println("First version of code search results for target " + myTarget + "     :" + search1(arr, myTarget));
    System.out
        .println("Second version of code search results for  target " + myTarget + "   :" + search2(arr, myTarget));
  }
}