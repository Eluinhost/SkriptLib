package com.exsoloscript.skriptlib.data;

import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDataLineProcessor implements LineProcessor<List<FileData>>
{
    private final Logger logger;
    private final List<FileData> data = Lists.newArrayList();

    public FileDataLineProcessor(Logger logger)
    {
        this.logger = logger;
    }

    @Override
    public boolean processLine(String line) throws IOException
    {
        line = line.trim();
        String[] parts = line.split(" ");

        if(parts.length < 3) {
            logger.log(Level.SEVERE, "Failed to parse data from the server, invalid format for line: " + line);
            return false;
        }

        String file = parts[0];
        String author = parts[1];
        String version = parts[2];

        data.add(new FileData(file, version, author));
        return true;
    }

    @Override
    public List<FileData> getResult()
    {
        return data;
    }
}
