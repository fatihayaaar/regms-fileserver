package com.fayardev.regmsfileserver.image.abstracts;

import java.io.File;
import java.io.IOException;

public interface ImageSource {

    File asFile() throws IOException;
}