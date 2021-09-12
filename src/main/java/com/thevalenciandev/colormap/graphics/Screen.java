package com.thevalenciandev.colormap.graphics;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    private static final int MAP_SIZE = 8; // 8x8 tiles
    private static final int MAP_SIZE_MASK = MAP_SIZE - 1;

    private final int width;
    private final int height;
    private final int[] pixels; // use a 1 dimensional array
    private final int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        Random random = new Random();
        for (int i = 0; i < tiles.length; i++) {
            // Pick any color from 0 (black) to white (0xFFFFFF)
            tiles[i] = random.nextInt(0xFFFFFF);
            tiles[0] = 0;
        }
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yy = y + yOffset;
            for (int x = 0; x < width; x++) {
                int xx = x + xOffset;
                // Below we do the equivalent of (x / TILE_SIZE) + (y / TILE_SIZE) * MAP_SIZE;
                // basically the same as x + y * width but scaled to the tile size
                int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + (((yy >> 4) & MAP_SIZE_MASK) << 3);
                // now render the pixel
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }

    public void copyPixelsOnto(int[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = pixels[i];
        }
    }
}
