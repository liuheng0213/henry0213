package basic.knowledge.henry.algorithm.InterviewExperience.ama;


/**
 * The cost is a sum of absolute values so it turns out to be a median question.
 *
 *
 * One solution is to use binary search.
 *
 *
 * if the twice the sum of distances to the first and last center is less than d then we can compute the answer in O(1) with math;
 * each unit distance we go past the first and last costs another 2n
 * otherwise we can do binary search; we know the left and right most locations (if any) that are valid are in (firstCenter, lastCenter)
 * the distance will decrease monotonically as we get closer to the median
 * so get the median, check if any solution exists, then binary search for as far as we can go left and right of the median
 * The fastest solution I know if is to use quickselect on the centers.
 * First do the check above to see if the first and last valid location are outside the range of center locations.
 * Then quickselect the median and check if any solution exists.
 * Then quickselect again to find the first and last index center where the sum of distances is at most d.
 * Then use a variant of the math in the prior solution to find the first and last location between centers - we know how the total distance changes between centers because of L1 norm / median properties,
 * it's just the difference between the number of centers we head away from minus the number we're heading toward.
 */
public class Test9 {
}
