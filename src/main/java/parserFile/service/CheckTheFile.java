package parserFile.service;

import java.nio.file.Path;

//TODO: Переименовать файл. FileChecker
//TODO: модификаторы доступов
public interface CheckTheFile {

    //Общий смысл: Два метода фактически делают одно и тоже, но называются по разному и вызываются в разных местах.
    // Проблема либо в архитектуре решения, либо в нейминге

    //TODO: Не нравится название
    public void processFilesInFolder(Path folderPath);

    //TODO:
    public void scanFolderForFiles(Path path);
}
