package commandParser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class PropertiesFileReader {

    protected ResourceBundle getBundle (File file) throws MalformedURLException {
        File directory = file.getParentFile();
        URL[] urls = {directory.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        String fileName = getFileNameWithoutExtension(file);
        ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.getDefault(), loader);
        return rb;
    }

    protected String getFileNameWithoutExtension (File file) {
        String fullFileName = file.getName();
        int pos = fullFileName.lastIndexOf(".");
        String fileNameWithoutExtension = "";
        if (pos > 0) {
            fileNameWithoutExtension = fullFileName.substring(0, pos);
        }
        return fileNameWithoutExtension;
    }
}
