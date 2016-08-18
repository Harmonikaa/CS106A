// TODO: comment this file

import java.util.*;
import acm.graphics.*;

public class ImageAlgorithms implements ImageAlgorithmsInterface {
	public void grayscale(GImage source) {
		// TODO: implement this method
		int [][] pixels = source.getPixelArray();
		for(int i=0;i<pixels.length;++i){
			for(int j=0;j<pixels[0].length;++j){
				int r = GImage.getRed(pixels[i][j]);
				int g = GImage.getGreen(pixels[i][j]);
				int b = GImage.getBlue(pixels[i][j]);
				
				int gray = (r+g+b)/3;
				int pixel = GImage.createRGBPixel(gray, gray, gray);
				pixels[i][j] = pixel;
			}
		}
		source.setPixelArray(pixels);
		
	}

	public void negative(GImage source) {
		// TODO: implement this method
		int [][] pixels = source.getPixelArray();
		for(int i=0;i<pixels.length;++i){
			for(int j=0;j<pixels[0].length;++j){
				int r = GImage.getRed(pixels[i][j]);
				int g = GImage.getGreen(pixels[i][j]);
				int b = GImage.getBlue(pixels[i][j]);
				
				int pixel = GImage.createRGBPixel(255-r, 255-g, 255-b);
				pixels[i][j] = pixel;
			}
		}
		source.setPixelArray(pixels);
	}

	public void rotateLeft(GImage source) {
		// TODO: implement this method
		int [][] pixels_origin = source.getPixelArray();
		int height_origin = pixels_origin.length;
		int width_origin = pixels_origin[0].length;
		int [][] pixels_new = new int [width_origin][height_origin];
		
		for(int i=0; i<width_origin; ++i){
			for(int j=0; j<height_origin; ++j){
				pixels_new[i][j] = pixels_origin[j][width_origin-i-1];
			}
		}
		source.setPixelArray(pixels_new);
	}

	public void rotateRight(GImage source) {
		// TODO: implement this method
		int [][] pixels_origin = source.getPixelArray();
		int height_origin = pixels_origin.length;
		int width_origin = pixels_origin[0].length;
		int [][] pixels_new = new int [width_origin][height_origin];
		
		for(int i=0; i<width_origin; ++i){
			for(int j=0; j<height_origin; ++j){
				pixels_new[i][j] = pixels_origin[height_origin-j-1][i];
			}
		}
		source.setPixelArray(pixels_new);
	}
	
	public void translate(GImage source, int dx, int dy) {
		// TODO: implement this method
		int [][] pixels_origin = source.getPixelArray();
		int height_origin = pixels_origin.length;
		int width_origin = pixels_origin[0].length;
		int [][] pixels_new = new int [height_origin][width_origin];
		
		for(int i=0;i<height_origin;++i){
			for(int j=0;j<width_origin;++j){
				int i_new = (i + dy)%height_origin;
				while(i_new<0)
					i_new += height_origin;
				int j_new = (j + dx)%width_origin;
				while(j_new<0)
					j_new += width_origin;
				pixels_new[i_new][j_new] = pixels_origin[i][j];
			}
		}
		
		source.setPixelArray(pixels_new);
	}

	public void blur(GImage source) {
		// TODO: implement this method
		int [][] pixels_origin = source.getPixelArray();
		int height_origin = pixels_origin.length;
		int width_origin = pixels_origin[0].length;
		int [][] pixels_new = new int [height_origin][width_origin];
		
		for(int i=0;i<height_origin;++i){
			for(int j=0;j<width_origin;++j){
				blurPixel(i,j,pixels_origin,pixels_new);
			}
		}
		
		source.setPixelArray(pixels_new);
	}

	private void blurPixel(int i, int j, int [][]pixels_origin, int[][]pixels_new){
		int height = pixels_origin.length;
		int width = pixels_origin[0].length;
		int r=0, g=0, b=0;
		int pixel_cnt = 0;
		
		for(int row=i-1;row<=i+1;++row){			
			if(row < 0 || row >=height)
				continue;
			for(int col=j-1;col<=j+1;++col){
				if(col < 0 || col >= width)
					continue;
				++pixel_cnt;
				r += GImage.getRed(pixels_origin[row][col]);
				g += GImage.getGreen(pixels_origin[row][col]);
				b += GImage.getBlue(pixels_origin[row][col]);				
			}			
			
		}
		r /= pixel_cnt;
		g /= pixel_cnt;
		b /= pixel_cnt;
		int pixel = GImage.createRGBPixel(r, g, b);
		pixels_new[i][j] = pixel;
	}
    public void mystery(GImage source) {
        // TODO: (optional) have some fun!
    }
}
