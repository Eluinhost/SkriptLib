package com.exsoloscript.skriptlib.data;

import java.net.MalformedURLException;
import java.net.URL;

public interface URLFormatter
{
    /**
     * @return the URL that points to the data list
     */
    URL getFetchURL();

    /**
     * @param data the file meta to generate the URL from
     * @return the URL to the actual resource
     */
    URL getFileDataURL(FileData data) throws MalformedURLException;
}
