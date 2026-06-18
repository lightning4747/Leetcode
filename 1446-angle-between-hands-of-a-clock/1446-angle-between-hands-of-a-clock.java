class Solution {
    public double angleClock(int hour, int minutes) {
        double x = hour + minutes/60.0;
        double diff1 = (11 * x) %12;
        double diff2 = 12- diff1;
        return 30.0 * Math.min(diff1, diff2);
    }
}