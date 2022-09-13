package com.vmo.training.demo.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertySettingStoreUtil {
    private static String PROPERTY_FILE_EXTENSION=".properties";
    private static String DF_CHARSET="UTF-8";
    private static String SETTING_ROOT_FOLDER_NAME="settings";

    private static Properties getSettings(String filePath) throws IOException{
        File settingFile= new File(filePath);
        if(!settingFile.exists()){
            settingFile.createNewFile();
        }

        FileInputStream f=null;

        try{
            f=new FileInputStream(settingFile);
            Properties settings= new Properties();
            settings.load(new InputStreamReader(f, Charset.forName(DF_CHARSET)));
            return settings;
        }finally {
            if(f!=null){
                f.close();
            }
        }
    }

    public static Properties getSettings(String projectFolderLocation, String settingName) throws IOException{
        return getSettings(projectFolderLocation+File.separator+SETTING_ROOT_FOLDER_NAME
                +File.separator+settingName+PROPERTY_FILE_EXTENSION);
    }
}
