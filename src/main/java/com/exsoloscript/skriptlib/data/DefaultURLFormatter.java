package com.exsoloscript.skriptlib.data;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultURLFormatter implements URLFormatter
{
    private String baseURL;
    private URL fetchURL;

    public DefaultURLFormatter(String baseURL) throws MalformedURLException
    {
        this.baseURL = baseURL;
        this.fetchURL = new URL(baseURL + "files.php");
    }

    @Override
    public URL getFetchURL()
    {
        return fetchURL;
    }

    @Override
    public URL getFileDataURL(FileData data) throws MalformedURLException
    {
        return new URL(baseURL + "files/" + data.getName() + "/" + data.getName());
    }
}
