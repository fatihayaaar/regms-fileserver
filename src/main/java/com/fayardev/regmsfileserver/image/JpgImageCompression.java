package com.fayardev.regmsfileserver.image;

import com.fayardev.regmsfileserver.image.abstracts.ImageCompression;
import com.fayardev.regmsfileserver.image.abstracts.ImageFormat;
import com.fayardev.regmsfileserver.image.abstracts.ImageSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class JpgImageCompression implements ImageCompression {

    private String imagesPathAsString;

    @Value("${upload.user.avatar.path}")
    public void setImagesPathAsString(String imagesPathAsString) {
        this.imagesPathAsString = imagesPathAsString;
    }

    @Override
    public void compress(ImageSource imageSource, String imageName) {
        try {
            Path imagesPath = Files.createDirectories(Paths.get(imagesPathAsString));
            String compressedImageFileName = imageName + "." + JpgImage.EXTENSION;
            File compressedImageFile = imagesPath.resolve(compressedImageFileName)
                    .toFile();

            File imageSourceFile = imageSource.asFile();

            ImageFormat imageFormat = new SquareCompressed(400);

            new JpgImage(imageSourceFile, imageFormat)
                    .compressTo(compressedImageFile);

            imageSourceFile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
