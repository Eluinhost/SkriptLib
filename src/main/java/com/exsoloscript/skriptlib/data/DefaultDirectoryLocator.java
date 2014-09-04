package com.exsoloscript.skriptlib.data;

import com.google.common.base.Preconditions;

import java.io.File;

public class DefaultDirectoryLocator implements DirectoryLocator
{
    private final File skriptDir;
    private final File pluginDir;

    public DefaultDirectoryLocator(File skriptDir, File pluginDir)
    {
        Preconditions.checkArgument(skriptDir.isDirectory());
        Preconditions.checkArgument(pluginDir.isDirectory());

        this.skriptDir = skriptDir;
        this.pluginDir = pluginDir;
    }

    @Override
    public File getDirectoryForFile(FileData data)
    {
        String fileName = data.getName();

        if(fileName.endsWith(".jar")) {
            return pluginDir;
        }

        if(fileName.endsWith(".sk")) {
            return skriptDir;
        }

        throw new IllegalArgumentException("File must end in .jar or .sk");
    }
}
