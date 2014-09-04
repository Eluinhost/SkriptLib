package com.exsoloscript.skriptlib.data;

import com.google.common.base.Optional;

import java.io.IOException;

public interface FileManager
{
    void update() throws IOException;

    /**
     * Downloads the given file to the harddrive
     *
     * @param name name of the file to fetch
     *
     * @throws java.lang.IllegalStateException if the file is not found in our list (via exists)
     * @throws java.lang.IllegalArgumentException if the file name being saved is unsupported
     */
    void downloadFile(String name) throws IOException;

    /**
     * Get the file data with the given name and version
     *
     * @param name the name to check
     * @return Optional with the filedata if we know about it
     */
    Optional<FileData> getFileData(String name);
}
