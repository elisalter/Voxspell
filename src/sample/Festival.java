package sample;

import java.io.*;

/**
 * Created by zihao123yang on 21/09/16.
 */
public class Festival {

    private static String _currentVoice = "(voice_kal_diphone)";

    /*
    public static void callFestival(String sayThis) {
        String cmd = "echo " + sayThis + " | festival --tts";
        ProcessBuilder speakWord = new ProcessBuilder("/bin/bash", "-c", cmd);
        try{
            @SuppressWarnings("unused")
            Process speakWordProcess = speakWord.start();
        } catch (Exception e){}
    }
    */


    public static void setVoice(String voice) {
        _currentVoice = voice;
    }


    public static void callFestival(String sayThis) {
        String cmd = "festival -b festival.scm";

        writeSayThis(sayThis);

        ProcessBuilder speakWord = new ProcessBuilder("/bin/bash", "-c", cmd);

        try {
            Process speakWordProcess = speakWord.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void writeSayThis(String sayThis) {

        try {

            File file = new File("festival.scm");

            deleteFile();
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(_currentVoice);
            bw.newLine();
            bw.write("(Parameter.set 'Duration_Stretch 1.2)");
            bw.newLine();
            bw.write("(" + "SayText \""+ sayThis + "\")");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void deleteFile() {

        File file = new File("festival.scm");
        file.delete();
    }








}