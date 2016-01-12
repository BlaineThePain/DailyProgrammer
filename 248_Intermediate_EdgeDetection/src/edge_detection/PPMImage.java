package edge_detection;

public class PPMImage {

    private final int width;
    private final int height;

    private Pixel[][] pixels;

    private String header;
    private double[][] edgeDirections;

    public PPMImage(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new Pixel[height][width];

        header = "P3\n" + width + " " + height + "\n255\n";

        edgeDirections = new double[height][width]; //SUPER MESSY
    }

    public void drawPixel(int red, int green, int blue, int x, int y) {
        pixels[y][x] = new Pixel(red, green, blue);
    }
    
    public PPMImage toGreyScale() {
        PPMImage greyScale = new PPMImage(width, height);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                greyScale.pixels[y][x] = pixels[y][x].toGreyScale();
            }
        }
        
        return greyScale;
    }

    //Works properly only if the image is grey-scale
    public PPMImage avgBlur(int radius, int iterations) {
        PPMImage blurred = new PPMImage(width, height);

        for (int h = 0; h < iterations; h++) {

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int count = 0;
                    int sum = 0;
                    for (int j = -radius; j <= radius; j++) {
                        for (int i = -radius; i <= radius; i++) {
                            if (((x + i) >= 0 && (y + j) >= 0)
                                    && ((x + i) < width && (y + j) < height)) {
                                sum += pixels[y + j][x + i].getRed();
                                count++;
                            }
                        }
                    }
                    sum /= count;
                    blurred.drawPixel(sum, sum, sum, x, y);
                }
            }
        }
        return blurred;
    }

    //Works properly only if the image is grey-scale
    public PPMImage edgeSobel() {
        PPMImage edged = new PPMImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int gY = 0;
                if ((y - 1) >= 0) {
                    if ((x - 1) >= 0) {
                        gY += pixels[y - 1][x - 1].getRed();
                    }
                    if ((x + 1) < width) {
                        gY += pixels[y - 1][x + 1].getRed();
                    }
                    gY += (2 * pixels[y - 1][x].getRed());
                }
                if ((y + 1) < height) {
                    if ((x - 1) >= 0) {
                        gY -= pixels[y + 1][x - 1].getRed();
                    }
                    if ((x + 1) < width) {
                        gY -= pixels[y + 1][x + 1].getRed();
                    }
                    gY -= (2 * pixels[y + 1][x].getRed());
                }

                int gX = 0;
                if ((x - 1) >= 0) {
                    if ((y - 1) >= 0) {
                        gX += pixels[y - 1][x - 1].getRed();
                    }
                    if ((y + 1) < height) {
                        gX += pixels[y + 1][x - 1].getRed();
                    }
                    gX += (2 * pixels[y][x - 1].getRed());
                }
                if ((x + 1) < width) {
                    if ((y - 1) >= 0) {
                        gX -= pixels[y - 1][x + 1].getRed();
                    }
                    if ((y + 1) < height) {
                        gX -= pixels[y + 1][x + 1].getRed();
                    }
                    gX -= (2 * pixels[y][x + 1].getRed());
                }

                int grad = (int) Math.sqrt((gY * gY) + (gX * gX));
                edged.drawPixel(grad, grad, grad, x, y);

                if (gX != 0) {
                    edged.setEdgeDirectionVal(x, y, Math.atan((double) gY / gX));
                } else {
                    edged.setEdgeDirectionVal(x, y, Math.PI / 2);
                }
            }
        }
        return edged;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public double[][] getEdgeDirection() {
        return edgeDirections;
    }

    public void setEdgeDirectionVal(int x, int y, double value) { //SUPER MESSY
        edgeDirections[y][x] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(header);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (pixels[j][i] == null) {
                    pixels[j][i] = new Pixel();
                }
                sb.append(pixels[j][i]).append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
