package com.exsoloscript.skriptlib.data.filtering;

import com.exsoloscript.skriptlib.data.FileData;
import com.google.common.base.Predicate;

import javax.annotation.Nullable;

public class FileDataWithName implements Predicate<FileData>
{
    private final String name;

    public FileDataWithName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean apply(@Nullable FileData data)
    {
        return data != null && data.getName().equalsIgnoreCase(name);
    }
}
