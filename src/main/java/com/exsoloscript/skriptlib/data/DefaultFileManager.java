package com.exsoloscript.skriptlib.data;

import com.exsoloscript.skriptlib.data.filtering.FileDataWithName;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DefaultFileManager implements FileManager
{
    private final URLFormatter formatter;
    private final DirectoryLocator locator;
    private final LineProcessor<List<FileData>> processor;

    private List<FileData> data = Lists.newArrayList();

    public DefaultFileManager(LineProcessor<List<FileData>> processor, URLFormatter formatter, DirectoryLocator locator)
    {
        this.formatter = formatter;
        this.processor = processor;
        this.locator = locator;
    }

    @Override
    public void update() throws IOException
    {
        data = Resources.readLines(formatter.getFetchURL(), Charsets.UTF_8, processor);
    }

    @Override
    public void downloadFile(String name) throws IOException
    {
        Optional<FileData> dataOptional = getFileData(name);
        Preconditions.checkState(dataOptional.isPresent(), "Unknown file requested");

        FileData data = dataOptional.get();

        URL requestURL = formatter.getFileDataURL(data);
        File dir = locator.getDirectoryForFile(data);

        ByteStreams.copy(
                Resources.newInputStreamSupplier(requestURL),
                Files.newOutputStreamSupplier(new File(dir, name))
        );
    }

    @Override
    public Optional<FileData> getFileData(String name)
    {
        return Optional.fromNullable(Iterables.find(data, new FileDataWithName(name), null));
    }
}
