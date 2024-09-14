package basic.knowledge.henry.ThreadRelevant._26threadSafe.treadSafeandHIghPerformance;

public class ImmutablePoint {
    private final int x, y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ImmutablePoint(MutablePoint point) {
        if (point == null) {
            throw new IllegalArgumentException("param is null");
        }
        int[] pointArray = point.getPointArray();
        x = pointArray[0];
        y = pointArray[1];
    }

    public int[] getPointArray() {
        int[] ret = new int[2];
        ret[0] = x;
        ret[1] = y;
        return ret;
    }
}
