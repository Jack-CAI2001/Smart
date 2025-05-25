package com.smart.smartApi.utils;

import com.smart.smartApi.controller.FileUploadController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;

public final class FileUtils {
    private FileUtils() {
        throw new AssertionError("Cette classe utilitaire ne doit pas être instanciée");
    }

    public static String buildFileAccessUrl(Path path) {
        return MvcUriComponentsBuilder.fromMethodName(
                        FileUploadController.class,
                        "serveFile",
                        path.getFileName().toString())
                .build()
                .toUri()
                .toString();
    }
}
