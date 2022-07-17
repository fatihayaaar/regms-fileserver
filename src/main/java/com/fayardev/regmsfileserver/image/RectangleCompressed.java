package com.fayardev.regmsfileserver.image;

import com.fayardev.regmsfileserver.image.abstracts.ImageFormat;

public record RectangleCompressed(int height, int width) implements ImageFormat {

    @Override
    public float compression() {
        return 0.5F;
    }
}
