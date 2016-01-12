package edge_detection;

public class CannyEdge {
    
    public static PPMImage nonMaxSuppression(PPMImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        
        Pixel[][] values = img.getValues();
        double[][] edgeDirection = img.getEdgeDirection();
        
        PPMImage edged = new PPMImage(width, height);
        
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                
                double currEdgeDir = edgeDirection[y][x];
                int currVal = values[y][x].getRed();
                
                int eeVal = values[y][x + 1].getRed();
                int neVal = values[y - 1][x + 1].getRed();
                int nnVal = values[y - 1][x].getRed();
                int nwVal = values[y - 1][x - 1].getRed();
                int wwVal = values[y][x - 1].getRed();   
                int swVal = values[y + 1][x - 1].getRed();
                int ssVal = values[y + 1][x].getRed();
                int seVal = values[y + 1][x + 1].getRed();
                
                double step = Math.PI / 4;
                
                double firstW;
                double secondW;
                
                double firstVal;
                double secondVal;                
                
                edged.drawPixel(currVal, currVal, currVal, x, y);
                
                if (currEdgeDir > -Math.PI / 2
                        && currEdgeDir < -Math.PI / 4) {
                    
                    firstW = (currEdgeDir + Math.PI / 2) / step;
                    secondW = (-Math.PI / 4 - currEdgeDir) / step;
                    firstVal = (firstW * seVal) + (secondW * ssVal);
                    
                    firstW = ((currEdgeDir + Math.PI) - Math.PI / 2) / step;
                    secondW = (3 * Math.PI / 4 - (currEdgeDir + Math.PI)) / step;
                    secondVal = (firstW * nwVal) + (secondW * nnVal);
                    
                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    } 
                } else if (currEdgeDir > -Math.PI / 4
                        && currEdgeDir < 0) {
                    
                    firstW = (currEdgeDir + Math.PI / 4) / step;
                    secondW = (-currEdgeDir) / step;
                    firstVal = (firstW * eeVal) + (secondW * seVal);
                    
                    firstW = ((currEdgeDir + Math.PI) - 3 * Math.PI / 4) / step;
                    secondW = (Math.PI - (currEdgeDir + Math.PI)) / step;
                    secondVal = (firstW * wwVal) + (secondW * nwVal);

                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                } else if (currEdgeDir > 0
                        && currEdgeDir < Math.PI / 4) {
                    
                    firstW = (currEdgeDir) / step;
                    secondW = (Math.PI / 4 - currEdgeDir) / step;
                    firstVal = (firstW * neVal) + (secondW * eeVal);
                    
                    firstW = ((currEdgeDir + Math.PI) - Math.PI) / step;
                    secondW = (5 * Math.PI / 4 - (currEdgeDir + Math.PI)) / step;
                    secondVal = (firstW * swVal) + (secondW * wwVal);

                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                } else if (currEdgeDir > Math.PI / 4
                        && currEdgeDir < Math.PI / 2) {
                    
                    firstW = (currEdgeDir - Math.PI / 4) / step;
                    secondW = (Math.PI / 2 - currEdgeDir) / step;
                    firstVal = (firstW * nnVal) + (secondW * neVal);
                    
                    firstW = ((currEdgeDir + Math.PI) - 5 * Math.PI / 4) / step;
                    secondW = (3 * Math.PI / 2 - (currEdgeDir + Math.PI)) / step;
                    secondVal = (firstW * ssVal) + (secondW * swVal);

                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                }
                
                
                /*if (Tools.areClose(currEdgeDir, -Math.PI / 2)
                        || Tools.areClose(currEdgeDir, Math.PI / 2)) {
                    if (currVal <= ssVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= nnVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                } else if (Tools.areClose(currEdgeDir, -Math.PI / 4)) {
                    if (currVal <= seVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= nwVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                } else if (Tools.areClose(currEdgeDir, 0)) {
                    if (currVal <= eeVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= wwVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                } else if (Tools.areClose(currEdgeDir, Math.PI / 4)) {
                    if (currVal <= neVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= swVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                } else if (currEdgeDir > -Math.PI / 2
                        && currEdgeDir < -Math.PI / 4) {
                    
                    double firstVal = (ssVal + seVal) / 2;
                    double secondVal = (nnVal + nwVal) / 2;
                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                } else if (currEdgeDir > -Math.PI / 4
                        && currEdgeDir < 0) {
                    
                    double firstVal = (seVal + eeVal) / 2;
                    double secondval = (nwVal + wwVal) / 2;
                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondval) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                } else if (currEdgeDir > 0
                        && currEdgeDir < Math.PI / 4) {
                    
                    double firstVal = (eeVal + neVal) / 2;
                    double secondval = (wwVal + swVal) / 2;
                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondval) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                } else if (currEdgeDir > Math.PI / 4
                        && currEdgeDir < Math.PI / 2) {
                    
                    double firstVal = (neVal + nnVal) / 2;
                    double secondval = (swVal + ssVal) / 2;
                    if (currVal <= firstVal) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    if (currVal <= secondval) {
                        edged.drawPixel(0, 0, 0, x, y);
                    }
                    
                }*/
            }
        }
        
        return edged;
    }
    
    public static PPMImage doubleThresholding(PPMImage img, double lowT, double highT) {
        int intLowT = (int) (lowT * 255);
        int intHighT = (int) (highT * 255);
        
        int width = img.getWidth();
        int height = img.getHeight();
        
        Pixel[][] values = img.getValues();
        
        PPMImage edged = new PPMImage(width, height);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                int currVal = values[y][x].getRed();
                
                if (currVal < intLowT) {
                    edged.drawPixel(0, 0, 0, x, y);
                } else if (currVal > intHighT) {
                    edged.drawPixel(255, 255, 255, x, y);
                } else {
                    edged.drawPixel(currVal, currVal, currVal, x, y);
                }
            }
        }
        return edged;
    }

}
