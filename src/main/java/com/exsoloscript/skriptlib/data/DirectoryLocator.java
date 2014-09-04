package com.exsoloscript.skriptlib.data;

import java.io.File;

public interface DirectoryLocator
{
    /**
     * @param data the data to find directory for
     * @return the directory to put the file into
     * @throws java.lang.IllegalArgumentException if no directory can be found
     */
    File getDirectoryForFile(FileData data);
}
