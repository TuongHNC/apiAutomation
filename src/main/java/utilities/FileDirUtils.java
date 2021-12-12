package utilities;

import java.io.File;

public class FileDirUtils {

    public static void checkIfDirectoryExist(String directoryName) {
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
}
