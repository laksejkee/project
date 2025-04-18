package parserFile.service.chekingDirectoryOnTheFile;

import java.nio.file.Path;

public interface CheckTheFile {
    public void processFilesInFolder(Path folderPath);

    public void scanFolderForFiles(Path path);
}
