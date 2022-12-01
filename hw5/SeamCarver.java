import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private Picture pic;
    private int height;
    private int width;

    public SeamCarver(Picture picture) {
        pic = new Picture(picture);
        height = picture.height();
        width = picture.width();
    }

    public Picture picture() {
        return new Picture(pic);
    }                      // current picture
    
    public int width() {                         // width of current picture
        return width;
    }

    public int height() {                        // height of current picture
        return height;
    }

    private int leftX(int x) {
        int leftX = x - 1;
        if (leftX < 0) {
            leftX = width - 1;
        }
        return leftX;
    }

    private int rightX(int x) {
        int rightX = x + 1;
        if (rightX >= width) {
            rightX = 0;
        }
        return rightX;
    }

    private int upY(int y) {
        int upY = y + 1;
        if (upY >= height) {
            upY = 0;
        }
        return upY;
    }

    private int downY(int y) {
        int downY = y - 1;
        if (downY < 0) {
            downY = height - 1;
        }
        return downY;
    }

    public double energy(int x, int y) {            // energy of pixel at column x and row y
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException();
        } 
        Color l = pic.get(leftX(x), y);
        Color r = pic.get(rightX(x), y);
        Color u = pic.get(x, upY(y));
        Color d = pic.get(x, downY(y));
        return Math.pow(l.getRed() - r.getRed(), 2) + Math.pow(l.getGreen() - r.getGreen(), 2) + Math.pow(l.getBlue() - r.getBlue(), 2)
                + Math.pow(u.getRed() - d.getRed(), 2) + Math.pow(u.getGreen() - d.getGreen(), 2) + Math.pow(u.getBlue() - d.getBlue(), 2);
    }

    public void transpose() {
        Picture temp = new Picture(height, width);
        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                temp.set(i, j, pic.get(j, i));
            }
        }
        pic = temp;
        int t = width;
        width = height;
        height = t;
    }

    public int[] findHorizontalSeam() {
        transpose();
        int[] result = findVerticalSeam();
        transpose();
        return result;
    }            // sequence of indices for horizontal seam

    public int[] findVerticalSeam() {
        int[][] path = new int[width][height];
        double[][] cost = new double[width][height];
        for (int i = 0; i < width; i++) {
            cost[i][0] = energy(i, 0);
            path[i][height - 1] = i;
        }

        for (int j = 1; j < height; j++) {
            for (int i = 0; i < width; i++) {
                double e = energy(i, j);
                double[] c = new double[3];
                c[0] = i > 0 ? cost[i-1][j-1] : Double.MAX_VALUE;
                c[1] = cost[i][j-1];
                c[2] = i < width - 1 ? cost[i+1][j-1] : Double.MAX_VALUE;
                double getmax = Double.MAX_VALUE;
                int pos = 0;
                for (int t=0; t<3; t++) {
                    if(c[t] < getmax) {
                       pos = t;
                       getmax = c[t];
                    }
                }
                cost[i][j] = e + getmax;
                path[i][j-1] = i - 1 + pos;
            }
        }

        int[] ret = new int[height];
        double min = Double.MAX_VALUE;
        int minPos = 0;
        for (int i = 0; i < width; i++) {
            if (cost[i][height - 1] < min) {
                min = cost[i][height - 1];
                minPos = i;
            }
        }
        //find path
        for (int j = height - 1; j >= 0; j--) {
            ret[j] = path[minPos][j];
            minPos = ret[j];
        }
        return ret;
    }              // sequence of indices for vertical seam

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(pic, seam);
    }  // remove horizontal seam from picture

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeVerticalSeam(pic, seam);
    }    // remove vertical seam from picture

    private boolean isValidSeam(int[] seam) {
        for (int i = 0, j = 1; j < seam.length; i++, j++) {
            if (Math.abs(seam[i] - seam[j]) > 1) {
                return false;
            }
        }
        return true;
    }
}