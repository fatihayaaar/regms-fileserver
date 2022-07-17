package com.fayardev.regmsfileserver.image;

import com.fayardev.regmsfileserver.image.abstracts.ImageFormat;

public record SquareCompressed(int size) implements ImageFormat {

    @Override
    public int width() {
        return size;
    }

    @Override
    public int height() {
        return size;
    }

    @Override
    public float compression() {
        return 0.50f;
    }
}
