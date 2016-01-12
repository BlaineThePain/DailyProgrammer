package paint;

import java.util.Arrays;

public class PPMImage {

    private final int width;
    private final int height;

    private Pixel[][] values;

    private String header;

    public PPMImage(int width, int height) {
        this.width = width;
        this.height = height;

        values = new Pixel[height][width];

        header = "P3\n" + width + " " + height + "\n255\n";
    }

    public void drawPixel(int red, int green, int blue, int x, int y) {
        values[y][x] = new Pixel(red, green, blue);
    }

    public void drawRectangle(int red, int green, int blue, int startX, int startY,
            int rectWidth, int rectHeight) {
        int endX = startX + rectWidth;
        int endY = startY + rectHeight;
        for (int j = startY; j < endY; j++) {
            for (int i = startX; i < endX; i++) {
                drawPixel(red, green, blue, i, j);
            }
        }
    }

    public void drawLine(int red, int green, int blue, int startX, int startY,
            int endX, int endY) {

        int x1 = startX;
        int x2 = endX;
        int y1 = startY;
        int y2 = endY;

        if (x1 == x2) {
            for (int j = y1; j < y2; j++) {
                drawPixel(red, green, blue, startX, j);
            }
        } else if (y1 == y2) {
            for (int i = x1; i < x2; i++) {
                drawPixel(red, green, blue, i, y1);
            }
        } else {
            int d = 0;

            int dy = Math.abs(y2 - y1);
            int dx = Math.abs(x2 - x1);

            int dy2 = (dy << 1); // slope scaling factors to avoid floating
            int dx2 = (dx << 1); // point

            int ix = x1 < x2 ? 1 : -1; // increment direction
            int iy = y1 < y2 ? 1 : -1;

            if (dy <= dx) {
                while (true) {
                    drawPixel(red, green, blue, x1, y1);
                    if (x1 == x2) {
                        break;
                    }
                    x1 += ix;
                    d += dy2;
                    if (d > dx) {
                        y1 += iy;
                        d -= dx2;
                    }
                }
            } else {
                while (true) {
                    drawPixel(red, green, blue, x1, y1);
                    if (y1 == y2) {
                        break;
                    }
                    y1 += iy;
                    d += dx2;
                    if (d > dy) {
                        x1 += ix;
                        d -= dy2;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(header);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (values[j][i] == null) {
                    values[j][i] = new Pixel();
                }
                sb.append(values[j][i]).append("  ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
