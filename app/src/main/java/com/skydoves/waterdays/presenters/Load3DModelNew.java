package com.skydoves.waterdays.presenters;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import static com.skydoves.waterdays.common.ConstantManager.MAX_NUMBER_DETAILS;

public class Load3DModelNew {
    @SuppressLint("StaticFieldLeak")
    private  static Context context;
    private static String[] text;
    private final float[][] coordinatesArray = new float[MAX_NUMBER_DETAILS][];
    private final float[][] texturesArray = new float[MAX_NUMBER_DETAILS][];
    private final float[][] normalsArray = new float[MAX_NUMBER_DETAILS][];
    public volatile static float[][] verticesArray = new float[MAX_NUMBER_DETAILS][1];
    public static String[][] model = new String[MAX_NUMBER_DETAILS][];
    public volatile static int[][] indicesArrayVertices = new int[MAX_NUMBER_DETAILS][1];

    public Load3DModelNew(Context context) {
        com.skydoves.waterdays.presenters.Load3DModelNew.context = context;
    }

    //////////////////////////////////////////////////////////////////////////////
    /**                           Загрузка модели                              **/
    //////////////////////////////////////////////////////////////////////////////
    public String[] readData(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String line = new String(buffer);
            text = line.split("#");
        } catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }
    public void loadSTR2(final int i) {
        parserDataVertices(i);
        parserDataTextures(i);
        parserDataNormals(i);
        parserDataFacets(i);
    }


    //////////////////////////////////////////////////////////////////////////////
    /**                   Компановка необходимых массивов                      **/
    //////////////////////////////////////////////////////////////////////////////
    public void parserDataVertices(int number){
        String text = "";
        if      (number ==  0) {text = "#" + getStringBuffer1() [1];}
        else if (number ==  1) {text = "#" + getStringBuffer2() [1];}
        else if (number ==  2) {text = "#" + getStringBuffer3() [1];}
        else if (number ==  3) {text = "#" + getStringBuffer4() [1];}
        else if (number ==  4) {text = "#" + getStringBuffer5() [1];}
        else if (number ==  5) {text = "#" + getStringBuffer6() [1];}
        else if (number ==  6) {text = "#" + getStringBuffer7() [1];}
        else if (number ==  7) {text = "#" + getStringBuffer8() [1];}
        else if (number ==  8) {text = "#" + getStringBuffer9() [1];}
        else if (number ==  9) {text = "#" + getStringBuffer10()[1];}
        else if (number == 10) {text = "#" + getStringBuffer11()[1];}
        else if (number == 11) {text = "#" + getStringBuffer12()[1];}
        else if (number == 12) {text = "#" + getStringBuffer13()[1];}
        else if (number == 13) {text = "#" + getStringBuffer14()[1];}
        else if (number == 14) {text = "#" + getStringBuffer15()[1];}
        else if (number == 15) {text = "#" + getStringBuffer16()[1];}
        else if (number == 16) {text = "#" + getStringBuffer17()[1];}
        else if (number == 17) {text = "#" + getStringBuffer18()[1];}
        else if (number == 18) {text = "#" + getStringBuffer19()[1];}
        else if (number == 19) {text = "#" + getStringBuffer20()[1];}
        else if (number == 20) {text = "#" + getStringBuffer21()[1];}
        else if (number == 21) {text = "#" + getStringBuffer22()[1];}
        else if (number == 22) {text = "#" + getStringBuffer23()[1];}
        else if (number == 23) {text = "#" + getStringBuffer24()[1];}
        else if (number == 24) {text = "#" + getStringBuffer25()[1];}
        else if (number == 25) {text = "#" + getStringBuffer26()[1];}
        else if (number == 26) {text = "#" + getStringBuffer27()[1];}
        else if (number == 27) {text = "#" + getStringBuffer28()[1];}
        else if (number == 28) {text = "#" + getStringBuffer29()[1];}
        else if (number == 29) {text = "#" + getStringBuffer30()[1];}
        else if (number == 30) {text = "#" + getStringBuffer31()[1];}
        else if (number == 31) {text = "#" + getStringBuffer32()[1];}
        else if (number == 32) {text = "#" + getStringBuffer33()[1];}
        else if (number == 33) {text = "#" + getStringBuffer34()[1];}
        else if (number == 34) {text = "#" + getStringBuffer35()[1];}
        else if (number == 35) {text = "#" + getStringBuffer36()[1];}
        else if (number == 36) {text = "#" + getStringBuffer37()[1];}
        else if (number == 37) {text = "#" + getStringBuffer38()[1];}
        else if (number == 38) {text = "#" + getStringBuffer39()[1];}
        else if (number == 39) {text = "#" + getStringBuffer40()[1];}
        else if (number == 40) {text = "#" + getStringBuffer41()[1];}
        else if (number == 41) {text = "#" + getStringBuffer42()[1];}
        else if (number == 42) {text = "#" + getStringBuffer43()[1];}
        else if (number == 43) {text = "#" + getStringBuffer44()[1];}
        else if (number == 44) {text = "#" + getStringBuffer45()[1];}
        else if (number == 45) {text = "#" + getStringBuffer46()[1];}
        else if (number == 46) {text = "#" + getStringBuffer47()[1];}
        else if (number == 47) {text = "#" + getStringBuffer48()[1];}
        else if (number == 48) {text = "#" + getStringBuffer49()[1];}
        else if (number == 49) {text = "#" + getStringBuffer50()[1];}
        else if (number == 50) {text = "#" + getStringBuffer51()[1];}
        else if (number == 51) {text = "#" + getStringBuffer52()[1];}
        else if (number == 52) {text = "#" + getStringBuffer53()[1];}
        else if (number == 53) {text = "#" + getStringBuffer54()[1];}
        else if (number == 54) {text = "#" + getStringBuffer55()[1];}
        else if (number == 55) {text = "#" + getStringBuffer56()[1];}
        else if (number == 56) {text = "#" + getStringBuffer57()[1];}
        else if (number == 57) {text = "#" + getStringBuffer58()[1];}
        else if (number == 58) {text = "#" + getStringBuffer59()[1];}
        else if (number == 59) {text = "#" + getStringBuffer60()[1];}
        else if (number == 60) {text = "#" + getStringBuffer61()[1];}
        else if (number == 61) {text = "#" + getStringBuffer62()[1];}
        else if (number == 62) {text = "#" + getStringBuffer63()[1];}
        else if (number == 63) {text = "#" + getStringBuffer64()[1];}
        else if (number == 64) {text = "#" + getStringBuffer65()[1];}
        else if (number == 65) {text = "#" + getStringBuffer66()[1];}
        else if (number == 66) {text = "#" + getStringBuffer67()[1];}
        else if (number == 67) {text = "#" + getStringBuffer68()[1];}
        else if (number == 68) {text = "#" + getStringBuffer69()[1];}
        else if (number == 69) {text = "#" + getStringBuffer70()[1];}
        else if (number == 70) {text = "#" + getStringBuffer71()[1];}
        else if (number == 71) {text = "#" + getStringBuffer72()[1];}
        else if (number == 72) {text = "#" + getStringBuffer73()[1];}
        else if (number == 73) {text = "#" + getStringBuffer74()[1];}
        else if (number == 74) {text = "#" + getStringBuffer75()[1];}
        else if (number == 75) {text = "#" + getStringBuffer76()[1];}
        else if (number == 76) {text = "#" + getStringBuffer77()[1];}
        else if (number == 77) {text = "#" + getStringBuffer78()[1];}
        else if (number == 78) {text = "#" + getStringBuffer79()[1];}
        else if (number == 79) {text = "#" + getStringBuffer80()[1];}
        else if (number == 80) {text = "#" + getStringBuffer81()[1];}
        else if (number == 81) {text = "#" + getStringBuffer82()[1];}
        else if (number == 82) {text = "#" + getStringBuffer83()[1];}
        else if (number == 83) {text = "#" + getStringBuffer84()[1];}
        else if (number == 84) {text = "#" + getStringBuffer85()[1];}
        else if (number == 85) {text = "#" + getStringBuffer86()[1];}
        else if (number == 86) {text = "#" + getStringBuffer87()[1];}
        else if (number == 87) {text = "#" + getStringBuffer88()[1];}
        else if (number == 88) {text = "#" + getStringBuffer89()[1];}
        else if (number == 89) {text = "#" + getStringBuffer90()[1];}
        else if (number == 90) {text = "#" + getStringBuffer91()[1];}
        else if (number == 91) {text = "#" + getStringBuffer92()[1];}
        else if (number == 92) {text = "#" + getStringBuffer93()[1];}
        else if (number == 93) {text = "#" + getStringBuffer94()[1];}
        else if (number == 94) {text = "#" + getStringBuffer95()[1];}
        else if (number == 95) {text = "#" + getStringBuffer96()[1];}
        else if (number == 96) {text = "#" + getStringBuffer97()[1];}
        else if (number == 97) {text = "#" + getStringBuffer98()[1];}
        else if (number == 98) {text = "#" + getStringBuffer99()[1];}
        else if (number == 99) {text = "#" + getStringBuffer100()[1];}
        else if (number == 100) {text = "#" + getStringBuffer101()[1];}
        else if (number == 101) {text = "#" + getStringBuffer102()[1];}
        else if (number == 102) {text = "#" + getStringBuffer103()[1];}
        else if (number == 103) {text = "#" + getStringBuffer104()[1];}
        else if (number == 104) {text = "#" + getStringBuffer105()[1];}
        else if (number == 105) {text = "#" + getStringBuffer106()[1];}
        else if (number == 106) {text = "#" + getStringBuffer107()[1];}
        else if (number == 107) {text = "#" + getStringBuffer108()[1];}
        else if (number == 108) {text = "#" + getStringBuffer109()[1];}
        else if (number == 109) {text = "#" + getStringBuffer110()[1];}
        else if (number == 110) {text = "#" + getStringBuffer111()[1];}
        else if (number == 111) {text = "#" + getStringBuffer112()[1];}
        else if (number == 112) {text = "#" + getStringBuffer113()[1];}
        else if (number == 113) {text = "#" + getStringBuffer114()[1];}
        else if (number == 114) {text = "#" + getStringBuffer115()[1];}
        else if (number == 115) {text = "#" + getStringBuffer116()[1];}
        else if (number == 116) {text = "#" + getStringBuffer117()[1];}
        else if (number == 117) {text = "#" + getStringBuffer118()[1];}
        else if (number == 118) {text = "#" + getStringBuffer119()[1];}
        else if (number == 119) {text = "#" + getStringBuffer120()[1];}
        else if (number == 120) {text = "#" + getStringBuffer121()[1];}
        else if (number == 121) {text = "#" + getStringBuffer122()[1];}
        else if (number == 122) {text = "#" + getStringBuffer123()[1];}
        else if (number == 123) {text = "#" + getStringBuffer124()[1];}
        else if (number == 124) {text = "#" + getStringBuffer125()[1];}
        else if (number == 125) {text = "#" + getStringBuffer126()[1];}
        else if (number == 126) {text = "#" + getStringBuffer127()[1];}
        else if (number == 127) {text = "#" + getStringBuffer128()[1];}
        else if (number == 128) {text = "#" + getStringBuffer129()[1];}
        else if (number == 129) {text = "#" + getStringBuffer130()[1];}
        else if (number == 130) {text = "#" + getStringBuffer131()[1];}
        else if (number == 131) {text = "#" + getStringBuffer132()[1];}
        else if (number == 132) {text = "#" + getStringBuffer133()[1];}
        else if (number == 133) {text = "#" + getStringBuffer134()[1];}
        StringBuilder line = new StringBuilder();
        int coordinatesNumber = 0;
        for (char msg : text.toCharArray()) {
            line.append(msg);
            if (msg == 10) {
                String[] currentLine = line.toString().split(" ");
                if (line.toString().startsWith("# ")) {
                    if (currentLine[2].equals("vertices\r\n")) {//\r
                        coordinatesNumber = Integer.parseInt(currentLine[1]);
                        coordinatesArray[number] = new float[coordinatesNumber * 3];
//                        System.out.println("Количество вершин: " + coordinatesNumber);
                        coordinatesNumber = 0;
                    }
                } else if (line.toString().startsWith("v ")){
                    coordinatesArray[number][coordinatesNumber++] = Float.parseFloat(currentLine[1]);
                    coordinatesArray[number][coordinatesNumber++] = Float.parseFloat(currentLine[2]);
                    coordinatesArray[number][coordinatesNumber++] = Float.parseFloat(currentLine[3]);
                }
                line = new StringBuilder();
            }
        }
    }
    public void parserDataTextures(int number){
        String text = "";
        if      (number ==  0) {text = "#" + getStringBuffer1() [2];}
        else if (number ==  1) {text = "#" + getStringBuffer2() [2];}
        else if (number ==  2) {text = "#" + getStringBuffer3() [2];}
        else if (number ==  3) {text = "#" + getStringBuffer4() [2];}
        else if (number ==  4) {text = "#" + getStringBuffer5() [2];}
        else if (number ==  5) {text = "#" + getStringBuffer6() [2];}
        else if (number ==  6) {text = "#" + getStringBuffer7() [2];}
        else if (number ==  7) {text = "#" + getStringBuffer8() [2];}
        else if (number ==  8) {text = "#" + getStringBuffer9() [2];}
        else if (number ==  9) {text = "#" + getStringBuffer10()[2];}
        else if (number == 10) {text = "#" + getStringBuffer11()[2];}
        else if (number == 11) {text = "#" + getStringBuffer12()[2];}
        else if (number == 12) {text = "#" + getStringBuffer13()[2];}
        else if (number == 13) {text = "#" + getStringBuffer14()[2];}
        else if (number == 14) {text = "#" + getStringBuffer15()[2];}
        else if (number == 15) {text = "#" + getStringBuffer16()[2];}
        else if (number == 16) {text = "#" + getStringBuffer17()[2];}
        else if (number == 17) {text = "#" + getStringBuffer18()[2];}
        else if (number == 18) {text = "#" + getStringBuffer19()[2];}
        else if (number == 19) {text = "#" + getStringBuffer20()[2];}
        else if (number == 20) {text = "#" + getStringBuffer21()[2];}
        else if (number == 21) {text = "#" + getStringBuffer22()[2];}
        else if (number == 22) {text = "#" + getStringBuffer23()[2];}
        else if (number == 23) {text = "#" + getStringBuffer24()[2];}
        else if (number == 24) {text = "#" + getStringBuffer25()[2];}
        else if (number == 25) {text = "#" + getStringBuffer26()[2];}
        else if (number == 26) {text = "#" + getStringBuffer27()[2];}
        else if (number == 27) {text = "#" + getStringBuffer28()[2];}
        else if (number == 28) {text = "#" + getStringBuffer29()[2];}
        else if (number == 29) {text = "#" + getStringBuffer30()[2];}
        else if (number == 30) {text = "#" + getStringBuffer31()[2];}
        else if (number == 31) {text = "#" + getStringBuffer32()[2];}
        else if (number == 32) {text = "#" + getStringBuffer33()[2];}
        else if (number == 33) {text = "#" + getStringBuffer34()[2];}
        else if (number == 34) {text = "#" + getStringBuffer35()[2];}
        else if (number == 35) {text = "#" + getStringBuffer36()[2];}
        else if (number == 36) {text = "#" + getStringBuffer37()[2];}
        else if (number == 37) {text = "#" + getStringBuffer38()[2];}
        else if (number == 38) {text = "#" + getStringBuffer39()[2];}
        else if (number == 39) {text = "#" + getStringBuffer40()[2];}
        else if (number == 40) {text = "#" + getStringBuffer41()[2];}
        else if (number == 41) {text = "#" + getStringBuffer42()[2];}
        else if (number == 42) {text = "#" + getStringBuffer43()[2];}
        else if (number == 43) {text = "#" + getStringBuffer44()[2];}
        else if (number == 44) {text = "#" + getStringBuffer45()[2];}
        else if (number == 45) {text = "#" + getStringBuffer46()[2];}
        else if (number == 46) {text = "#" + getStringBuffer47()[2];}
        else if (number == 47) {text = "#" + getStringBuffer48()[2];}
        else if (number == 48) {text = "#" + getStringBuffer49()[2];}
        else if (number == 49) {text = "#" + getStringBuffer50()[2];}
        else if (number == 50) {text = "#" + getStringBuffer51()[2];}
        else if (number == 51) {text = "#" + getStringBuffer52()[2];}
        else if (number == 52) {text = "#" + getStringBuffer53()[2];}
        else if (number == 53) {text = "#" + getStringBuffer54()[2];}
        else if (number == 54) {text = "#" + getStringBuffer55()[2];}
        else if (number == 55) {text = "#" + getStringBuffer56()[2];}
        else if (number == 56) {text = "#" + getStringBuffer57()[2];}
        else if (number == 57) {text = "#" + getStringBuffer58()[2];}
        else if (number == 58) {text = "#" + getStringBuffer59()[2];}
        else if (number == 59) {text = "#" + getStringBuffer60()[2];}
        else if (number == 60) {text = "#" + getStringBuffer61()[2];}
        else if (number == 61) {text = "#" + getStringBuffer62()[2];}
        else if (number == 62) {text = "#" + getStringBuffer63()[2];}
        else if (number == 63) {text = "#" + getStringBuffer64()[2];}
        else if (number == 64) {text = "#" + getStringBuffer65()[2];}
        else if (number == 65) {text = "#" + getStringBuffer66()[2];}
        else if (number == 66) {text = "#" + getStringBuffer67()[2];}
        else if (number == 67) {text = "#" + getStringBuffer68()[2];}
        else if (number == 68) {text = "#" + getStringBuffer69()[2];}
        else if (number == 69) {text = "#" + getStringBuffer70()[2];}
        else if (number == 70) {text = "#" + getStringBuffer71()[2];}
        else if (number == 71) {text = "#" + getStringBuffer72()[2];}
        else if (number == 72) {text = "#" + getStringBuffer73()[2];}
        else if (number == 73) {text = "#" + getStringBuffer74()[2];}
        else if (number == 74) {text = "#" + getStringBuffer75()[2];}
        else if (number == 75) {text = "#" + getStringBuffer76()[2];}
        else if (number == 76) {text = "#" + getStringBuffer77()[2];}
        else if (number == 77) {text = "#" + getStringBuffer78()[2];}
        else if (number == 78) {text = "#" + getStringBuffer79()[2];}
        else if (number == 79) {text = "#" + getStringBuffer80()[2];}
        else if (number == 80) {text = "#" + getStringBuffer81()[2];}
        else if (number == 81) {text = "#" + getStringBuffer82()[2];}
        else if (number == 82) {text = "#" + getStringBuffer83()[2];}
        else if (number == 83) {text = "#" + getStringBuffer84()[2];}
        else if (number == 84) {text = "#" + getStringBuffer85()[2];}
        else if (number == 85) {text = "#" + getStringBuffer86()[2];}
        else if (number == 86) {text = "#" + getStringBuffer87()[2];}
        else if (number == 87) {text = "#" + getStringBuffer88()[2];}
        else if (number == 88) {text = "#" + getStringBuffer89()[2];}
        else if (number == 89) {text = "#" + getStringBuffer90()[2];}
        else if (number == 90) {text = "#" + getStringBuffer91()[2];}
        else if (number == 91) {text = "#" + getStringBuffer92()[2];}
        else if (number == 92) {text = "#" + getStringBuffer93()[2];}
        else if (number == 93) {text = "#" + getStringBuffer94()[2];}
        else if (number == 94) {text = "#" + getStringBuffer95()[2];}
        else if (number == 95) {text = "#" + getStringBuffer96()[2];}
        else if (number == 96) {text = "#" + getStringBuffer97()[2];}
        else if (number == 97) {text = "#" + getStringBuffer98()[2];}
        else if (number == 98) {text = "#" + getStringBuffer99()[2];}
        else if (number == 99) {text = "#" + getStringBuffer100()[2];}
        else if (number == 100) {text = "#" + getStringBuffer101()[2];}
        else if (number == 101) {text = "#" + getStringBuffer102()[2];}
        else if (number == 102) {text = "#" + getStringBuffer103()[2];}
        else if (number == 103) {text = "#" + getStringBuffer104()[2];}
        else if (number == 104) {text = "#" + getStringBuffer105()[2];}
        else if (number == 105) {text = "#" + getStringBuffer106()[2];}
        else if (number == 106) {text = "#" + getStringBuffer107()[2];}
        else if (number == 107) {text = "#" + getStringBuffer108()[2];}
        else if (number == 108) {text = "#" + getStringBuffer109()[2];}
        else if (number == 109) {text = "#" + getStringBuffer110()[2];}
        else if (number == 110) {text = "#" + getStringBuffer111()[2];}
        else if (number == 111) {text = "#" + getStringBuffer112()[2];}
        else if (number == 112) {text = "#" + getStringBuffer113()[2];}
        else if (number == 113) {text = "#" + getStringBuffer114()[2];}
        else if (number == 114) {text = "#" + getStringBuffer115()[2];}
        else if (number == 115) {text = "#" + getStringBuffer116()[2];}
        else if (number == 116) {text = "#" + getStringBuffer117()[2];}
        else if (number == 117) {text = "#" + getStringBuffer118()[2];}
        else if (number == 118) {text = "#" + getStringBuffer119()[2];}
        else if (number == 119) {text = "#" + getStringBuffer120()[2];}
        else if (number == 120) {text = "#" + getStringBuffer121()[2];}
        else if (number == 121) {text = "#" + getStringBuffer122()[2];}
        else if (number == 122) {text = "#" + getStringBuffer123()[2];}
        else if (number == 123) {text = "#" + getStringBuffer124()[2];}
        else if (number == 124) {text = "#" + getStringBuffer125()[2];}
        else if (number == 125) {text = "#" + getStringBuffer126()[2];}
        else if (number == 126) {text = "#" + getStringBuffer127()[2];}
        else if (number == 127) {text = "#" + getStringBuffer128()[2];}
        else if (number == 128) {text = "#" + getStringBuffer129()[2];}
        else if (number == 129) {text = "#" + getStringBuffer130()[2];}
        else if (number == 130) {text = "#" + getStringBuffer131()[2];}
        else if (number == 131) {text = "#" + getStringBuffer132()[2];}
        else if (number == 132) {text = "#" + getStringBuffer133()[2];}
        else if (number == 133) {text = "#" + getStringBuffer134()[2];}
        StringBuilder line = new StringBuilder();

        int texturesNumber = 0;
        for (char msg : text.toCharArray()){
            line.append(msg);
            if (msg == 10){
                String[] currentLine = line.toString().split(" ");
                if(line.toString().startsWith("# ")){
                    if(currentLine[2].equals("texture")){
                        texturesNumber = Integer.parseInt(currentLine[1]);
                        texturesArray[number] = new float[texturesNumber*2];
//                        System.out.println("Количество текстурных координат: " + texturesNumber);
                        texturesNumber = 0;
                    }
                }else if (line.toString().startsWith("vt ")){
                    texturesArray[number][texturesNumber] = Float.parseFloat(currentLine[1]);
                    texturesArray[number][texturesNumber + 1] = Float.parseFloat(currentLine[2]);
                    texturesNumber += 2;
                }
                line = new StringBuilder();
            }
        }
    }
    public void parserDataNormals(int number) {
        String text = "";
        if      (number ==  0) {text = "#" + getStringBuffer1() [3];}
        else if (number ==  1) {text = "#" + getStringBuffer2() [3];}
        else if (number ==  2) {text = "#" + getStringBuffer3() [3];}
        else if (number ==  3) {text = "#" + getStringBuffer4() [3];}
        else if (number ==  4) {text = "#" + getStringBuffer5() [3];}
        else if (number ==  5) {text = "#" + getStringBuffer6() [3];}
        else if (number ==  6) {text = "#" + getStringBuffer7() [3];}
        else if (number ==  7) {text = "#" + getStringBuffer8() [3];}
        else if (number ==  8) {text = "#" + getStringBuffer9() [3];}
        else if (number ==  9) {text = "#" + getStringBuffer10()[3];}
        else if (number == 10) {text = "#" + getStringBuffer11()[3];}
        else if (number == 11) {text = "#" + getStringBuffer12()[3];}
        else if (number == 12) {text = "#" + getStringBuffer13()[3];}
        else if (number == 13) {text = "#" + getStringBuffer14()[3];}
        else if (number == 14) {text = "#" + getStringBuffer15()[3];}
        else if (number == 15) {text = "#" + getStringBuffer16()[3];}
        else if (number == 16) {text = "#" + getStringBuffer17()[3];}
        else if (number == 17) {text = "#" + getStringBuffer18()[3];}
        else if (number == 18) {text = "#" + getStringBuffer19()[3];}
        else if (number == 19) {text = "#" + getStringBuffer20()[3];}
        else if (number == 20) {text = "#" + getStringBuffer21()[3];}
        else if (number == 21) {text = "#" + getStringBuffer22()[3];}
        else if (number == 22) {text = "#" + getStringBuffer23()[3];}
        else if (number == 23) {text = "#" + getStringBuffer24()[3];}
        else if (number == 24) {text = "#" + getStringBuffer25()[3];}
        else if (number == 25) {text = "#" + getStringBuffer26()[3];}
        else if (number == 26) {text = "#" + getStringBuffer27()[3];}
        else if (number == 27) {text = "#" + getStringBuffer28()[3];}
        else if (number == 28) {text = "#" + getStringBuffer29()[3];}
        else if (number == 29) {text = "#" + getStringBuffer30()[3];}
        else if (number == 30) {text = "#" + getStringBuffer31()[3];}
        else if (number == 31) {text = "#" + getStringBuffer32()[3];}
        else if (number == 32) {text = "#" + getStringBuffer33()[3];}
        else if (number == 33) {text = "#" + getStringBuffer34()[3];}
        else if (number == 34) {text = "#" + getStringBuffer35()[3];}
        else if (number == 35) {text = "#" + getStringBuffer36()[3];}
        else if (number == 36) {text = "#" + getStringBuffer37()[3];}
        else if (number == 37) {text = "#" + getStringBuffer38()[3];}
        else if (number == 38) {text = "#" + getStringBuffer39()[3];}
        else if (number == 39) {text = "#" + getStringBuffer40()[3];}
        else if (number == 40) {text = "#" + getStringBuffer41()[3];}
        else if (number == 41) {text = "#" + getStringBuffer42()[3];}
        else if (number == 42) {text = "#" + getStringBuffer43()[3];}
        else if (number == 43) {text = "#" + getStringBuffer44()[3];}
        else if (number == 44) {text = "#" + getStringBuffer45()[3];}
        else if (number == 45) {text = "#" + getStringBuffer46()[3];}
        else if (number == 46) {text = "#" + getStringBuffer47()[3];}
        else if (number == 47) {text = "#" + getStringBuffer48()[3];}
        else if (number == 48) {text = "#" + getStringBuffer49()[3];}
        else if (number == 49) {text = "#" + getStringBuffer50()[3];}
        else if (number == 50) {text = "#" + getStringBuffer51()[3];}
        else if (number == 51) {text = "#" + getStringBuffer52()[3];}
        else if (number == 52) {text = "#" + getStringBuffer53()[3];}
        else if (number == 53) {text = "#" + getStringBuffer54()[3];}
        else if (number == 54) {text = "#" + getStringBuffer55()[3];}
        else if (number == 55) {text = "#" + getStringBuffer56()[3];}
        else if (number == 56) {text = "#" + getStringBuffer57()[3];}
        else if (number == 57) {text = "#" + getStringBuffer58()[3];}
        else if (number == 58) {text = "#" + getStringBuffer59()[3];}
        else if (number == 59) {text = "#" + getStringBuffer60()[3];}
        else if (number == 60) {text = "#" + getStringBuffer61()[3];}
        else if (number == 61) {text = "#" + getStringBuffer62()[3];}
        else if (number == 62) {text = "#" + getStringBuffer63()[3];}
        else if (number == 63) {text = "#" + getStringBuffer64()[3];}
        else if (number == 64) {text = "#" + getStringBuffer65()[3];}
        else if (number == 65) {text = "#" + getStringBuffer66()[3];}
        else if (number == 66) {text = "#" + getStringBuffer67()[3];}
        else if (number == 67) {text = "#" + getStringBuffer68()[3];}
        else if (number == 68) {text = "#" + getStringBuffer69()[3];}
        else if (number == 69) {text = "#" + getStringBuffer70()[3];}
        else if (number == 70) {text = "#" + getStringBuffer71()[3];}
        else if (number == 71) {text = "#" + getStringBuffer72()[3];}
        else if (number == 72) {text = "#" + getStringBuffer73()[3];}
        else if (number == 73) {text = "#" + getStringBuffer74()[3];}
        else if (number == 74) {text = "#" + getStringBuffer75()[3];}
        else if (number == 75) {text = "#" + getStringBuffer76()[3];}
        else if (number == 76) {text = "#" + getStringBuffer77()[3];}
        else if (number == 77) {text = "#" + getStringBuffer78()[3];}
        else if (number == 78) {text = "#" + getStringBuffer79()[3];}
        else if (number == 79) {text = "#" + getStringBuffer80()[3];}
        else if (number == 80) {text = "#" + getStringBuffer81()[3];}
        else if (number == 81) {text = "#" + getStringBuffer82()[3];}
        else if (number == 82) {text = "#" + getStringBuffer83()[3];}
        else if (number == 83) {text = "#" + getStringBuffer84()[3];}
        else if (number == 84) {text = "#" + getStringBuffer85()[3];}
        else if (number == 85) {text = "#" + getStringBuffer86()[3];}
        else if (number == 86) {text = "#" + getStringBuffer87()[3];}
        else if (number == 87) {text = "#" + getStringBuffer88()[3];}
        else if (number == 88) {text = "#" + getStringBuffer89()[3];}
        else if (number == 89) {text = "#" + getStringBuffer90()[3];}
        else if (number == 90) {text = "#" + getStringBuffer91()[3];}
        else if (number == 91) {text = "#" + getStringBuffer92()[3];}
        else if (number == 92) {text = "#" + getStringBuffer93()[3];}
        else if (number == 93) {text = "#" + getStringBuffer94()[3];}
        else if (number == 94) {text = "#" + getStringBuffer95()[3];}
        else if (number == 95) {text = "#" + getStringBuffer96()[3];}
        else if (number == 96) {text = "#" + getStringBuffer97()[3];}
        else if (number == 97) {text = "#" + getStringBuffer98()[3];}
        else if (number == 98) {text = "#" + getStringBuffer99()[3];}
        else if (number == 99) {text = "#" + getStringBuffer100()[3];}
        else if (number == 100) {text = "#" + getStringBuffer101()[3];}
        else if (number == 101) {text = "#" + getStringBuffer102()[3];}
        else if (number == 102) {text = "#" + getStringBuffer103()[3];}
        else if (number == 103) {text = "#" + getStringBuffer104()[3];}
        else if (number == 104) {text = "#" + getStringBuffer105()[3];}
        else if (number == 105) {text = "#" + getStringBuffer106()[3];}
        else if (number == 106) {text = "#" + getStringBuffer107()[3];}
        else if (number == 107) {text = "#" + getStringBuffer108()[3];}
        else if (number == 108) {text = "#" + getStringBuffer109()[3];}
        else if (number == 109) {text = "#" + getStringBuffer110()[3];}
        else if (number == 110) {text = "#" + getStringBuffer111()[3];}
        else if (number == 111) {text = "#" + getStringBuffer112()[3];}
        else if (number == 112) {text = "#" + getStringBuffer113()[3];}
        else if (number == 113) {text = "#" + getStringBuffer114()[3];}
        else if (number == 114) {text = "#" + getStringBuffer115()[3];}
        else if (number == 115) {text = "#" + getStringBuffer116()[3];}
        else if (number == 116) {text = "#" + getStringBuffer117()[3];}
        else if (number == 117) {text = "#" + getStringBuffer118()[3];}
        else if (number == 118) {text = "#" + getStringBuffer119()[3];}
        else if (number == 119) {text = "#" + getStringBuffer120()[3];}
        else if (number == 120) {text = "#" + getStringBuffer121()[3];}
        else if (number == 121) {text = "#" + getStringBuffer122()[3];}
        else if (number == 122) {text = "#" + getStringBuffer123()[3];}
        else if (number == 123) {text = "#" + getStringBuffer124()[3];}
        else if (number == 124) {text = "#" + getStringBuffer125()[3];}
        else if (number == 125) {text = "#" + getStringBuffer126()[3];}
        else if (number == 126) {text = "#" + getStringBuffer127()[3];}
        else if (number == 127) {text = "#" + getStringBuffer128()[3];}
        else if (number == 128) {text = "#" + getStringBuffer129()[3];}
        else if (number == 129) {text = "#" + getStringBuffer130()[3];}
        else if (number == 130) {text = "#" + getStringBuffer131()[3];}
        else if (number == 131) {text = "#" + getStringBuffer132()[3];}
        else if (number == 132) {text = "#" + getStringBuffer133()[3];}
        else if (number == 133) {text = "#" + getStringBuffer134()[3];}
        StringBuilder line = new StringBuilder();

        int normalsNumber = 0;

        for (char msg : text.toCharArray()){
            line.append(msg);
            if (msg == 10) {
                String[] currentLine = line.toString().split(" ");
                if (line.toString().startsWith("# ")) {
                    if (currentLine[2].equals("vertex")) {
                        normalsNumber = Integer.parseInt(currentLine[1]);
                        normalsArray[number] = new float[normalsNumber * 3];
//                        System.out.println("Количество координат нормалей: " + normalsNumber);
                        normalsNumber = 0;
                    }
                } else if (line.toString().startsWith("vn ")) {
                    normalsArray[number][normalsNumber] = Float.parseFloat(currentLine[1]);
                    normalsArray[number][normalsNumber + 1] = Float.parseFloat(currentLine[2]);
                    normalsArray[number][normalsNumber + 2] = Float.parseFloat(currentLine[3]);
                    normalsNumber += 3;
                }
                line = new StringBuilder();
            }
        }
    }
    public void parserDataFacets (int number) {
        String text = "";
        if      (number ==  0) {text = "#" + getStringBuffer1() [4];}
        else if (number ==  1) {text = "#" + getStringBuffer2() [4];}
        else if (number ==  2) {text = "#" + getStringBuffer3() [4];}
        else if (number ==  3) {text = "#" + getStringBuffer4() [4];}
        else if (number ==  4) {text = "#" + getStringBuffer5() [4];}
        else if (number ==  5) {text = "#" + getStringBuffer6() [4];}
        else if (number ==  6) {text = "#" + getStringBuffer7() [4];}
        else if (number ==  7) {text = "#" + getStringBuffer8() [4];}
        else if (number ==  8) {text = "#" + getStringBuffer9() [4];}
        else if (number ==  9) {text = "#" + getStringBuffer10()[4];}
        else if (number == 10) {text = "#" + getStringBuffer11()[4];}
        else if (number == 11) {text = "#" + getStringBuffer12()[4];}
        else if (number == 12) {text = "#" + getStringBuffer13()[4];}
        else if (number == 13) {text = "#" + getStringBuffer14()[4];}
        else if (number == 14) {text = "#" + getStringBuffer15()[4];}
        else if (number == 15) {text = "#" + getStringBuffer16()[4];}
        else if (number == 16) {text = "#" + getStringBuffer17()[4];}
        else if (number == 17) {text = "#" + getStringBuffer18()[4];}
        else if (number == 18) {text = "#" + getStringBuffer19()[4];}
        else if (number == 19) {text = "#" + getStringBuffer20()[4];}
        else if (number == 20) {text = "#" + getStringBuffer21()[4];}
        else if (number == 21) {text = "#" + getStringBuffer22()[4];}
        else if (number == 22) {text = "#" + getStringBuffer23()[4];}
        else if (number == 23) {text = "#" + getStringBuffer24()[4];}
        else if (number == 24) {text = "#" + getStringBuffer25()[4];}
        else if (number == 25) {text = "#" + getStringBuffer26()[4];}
        else if (number == 26) {text = "#" + getStringBuffer27()[4];}
        else if (number == 27) {text = "#" + getStringBuffer28()[4];}
        else if (number == 28) {text = "#" + getStringBuffer29()[4];}
        else if (number == 29) {text = "#" + getStringBuffer30()[4];}
        else if (number == 30) {text = "#" + getStringBuffer31()[4];}
        else if (number == 31) {text = "#" + getStringBuffer32()[4];}
        else if (number == 32) {text = "#" + getStringBuffer33()[4];}
        else if (number == 33) {text = "#" + getStringBuffer34()[4];}
        else if (number == 34) {text = "#" + getStringBuffer35()[4];}
        else if (number == 35) {text = "#" + getStringBuffer36()[4];}
        else if (number == 36) {text = "#" + getStringBuffer37()[4];}
        else if (number == 37) {text = "#" + getStringBuffer38()[4];}
        else if (number == 38) {text = "#" + getStringBuffer39()[4];}
        else if (number == 39) {text = "#" + getStringBuffer40()[4];}
        else if (number == 40) {text = "#" + getStringBuffer41()[4];}
        else if (number == 41) {text = "#" + getStringBuffer42()[4];}
        else if (number == 42) {text = "#" + getStringBuffer43()[4];}
        else if (number == 43) {text = "#" + getStringBuffer44()[4];}
        else if (number == 44) {text = "#" + getStringBuffer45()[4];}
        else if (number == 45) {text = "#" + getStringBuffer46()[4];}
        else if (number == 46) {text = "#" + getStringBuffer47()[4];}
        else if (number == 47) {text = "#" + getStringBuffer48()[4];}
        else if (number == 48) {text = "#" + getStringBuffer49()[4];}
        else if (number == 49) {text = "#" + getStringBuffer50()[4];}
        else if (number == 50) {text = "#" + getStringBuffer51()[4];}
        else if (number == 51) {text = "#" + getStringBuffer52()[4];}
        else if (number == 52) {text = "#" + getStringBuffer53()[4];}
        else if (number == 53) {text = "#" + getStringBuffer54()[4];}
        else if (number == 54) {text = "#" + getStringBuffer55()[4];}
        else if (number == 55) {text = "#" + getStringBuffer56()[4];}
        else if (number == 56) {text = "#" + getStringBuffer57()[4];}
        else if (number == 57) {text = "#" + getStringBuffer58()[4];}
        else if (number == 58) {text = "#" + getStringBuffer59()[4];}
        else if (number == 59) {text = "#" + getStringBuffer60()[4];}
        else if (number == 60) {text = "#" + getStringBuffer61()[4];}
        else if (number == 61) {text = "#" + getStringBuffer62()[4];}
        else if (number == 62) {text = "#" + getStringBuffer63()[4];}
        else if (number == 63) {text = "#" + getStringBuffer64()[4];}
        else if (number == 64) {text = "#" + getStringBuffer65()[4];}
        else if (number == 65) {text = "#" + getStringBuffer66()[4];}
        else if (number == 66) {text = "#" + getStringBuffer67()[4];}
        else if (number == 67) {text = "#" + getStringBuffer68()[4];}
        else if (number == 68) {text = "#" + getStringBuffer69()[4];}
        else if (number == 69) {text = "#" + getStringBuffer70()[4];}
        else if (number == 70) {text = "#" + getStringBuffer71()[4];}
        else if (number == 71) {text = "#" + getStringBuffer72()[4];}
        else if (number == 72) {text = "#" + getStringBuffer73()[4];}
        else if (number == 73) {text = "#" + getStringBuffer74()[4];}
        else if (number == 74) {text = "#" + getStringBuffer75()[4];}
        else if (number == 75) {text = "#" + getStringBuffer76()[4];}
        else if (number == 76) {text = "#" + getStringBuffer77()[4];}
        else if (number == 77) {text = "#" + getStringBuffer78()[4];}
        else if (number == 78) {text = "#" + getStringBuffer79()[4];}
        else if (number == 79) {text = "#" + getStringBuffer80()[4];}
        else if (number == 80) {text = "#" + getStringBuffer81()[4];}
        else if (number == 81) {text = "#" + getStringBuffer82()[4];}
        else if (number == 82) {text = "#" + getStringBuffer83()[4];}
        else if (number == 83) {text = "#" + getStringBuffer84()[4];}
        else if (number == 84) {text = "#" + getStringBuffer85()[4];}
        else if (number == 85) {text = "#" + getStringBuffer86()[4];}
        else if (number == 86) {text = "#" + getStringBuffer87()[4];}
        else if (number == 87) {text = "#" + getStringBuffer88()[4];}
        else if (number == 88) {text = "#" + getStringBuffer89()[4];}
        else if (number == 89) {text = "#" + getStringBuffer90()[4];}
        else if (number == 90) {text = "#" + getStringBuffer91()[4];}
        else if (number == 91) {text = "#" + getStringBuffer92()[4];}
        else if (number == 92) {text = "#" + getStringBuffer93()[4];}
        else if (number == 93) {text = "#" + getStringBuffer94()[4];}
        else if (number == 94) {text = "#" + getStringBuffer95()[4];}
        else if (number == 95) {text = "#" + getStringBuffer96()[4];}
        else if (number == 96) {text = "#" + getStringBuffer97()[4];}
        else if (number == 97) {text = "#" + getStringBuffer98()[4];}
        else if (number == 98) {text = "#" + getStringBuffer99()[4];}
        else if (number == 99) {text = "#" + getStringBuffer100()[4];}
        else if (number == 100) {text = "#" + getStringBuffer101()[4];}
        else if (number == 101) {text = "#" + getStringBuffer102()[4];}
        else if (number == 102) {text = "#" + getStringBuffer103()[4];}
        else if (number == 103) {text = "#" + getStringBuffer104()[4];}
        else if (number == 104) {text = "#" + getStringBuffer105()[4];}
        else if (number == 105) {text = "#" + getStringBuffer106()[4];}
        else if (number == 106) {text = "#" + getStringBuffer107()[4];}
        else if (number == 107) {text = "#" + getStringBuffer108()[4];}
        else if (number == 108) {text = "#" + getStringBuffer109()[4];}
        else if (number == 109) {text = "#" + getStringBuffer110()[4];}
        else if (number == 110) {text = "#" + getStringBuffer111()[4];}
        else if (number == 111) {text = "#" + getStringBuffer112()[4];}
        else if (number == 112) {text = "#" + getStringBuffer113()[4];}
        else if (number == 113) {text = "#" + getStringBuffer114()[4];}
        else if (number == 114) {text = "#" + getStringBuffer115()[4];}
        else if (number == 115) {text = "#" + getStringBuffer116()[4];}
        else if (number == 116) {text = "#" + getStringBuffer117()[4];}
        else if (number == 117) {text = "#" + getStringBuffer118()[4];}
        else if (number == 118) {text = "#" + getStringBuffer119()[4];}
        else if (number == 119) {text = "#" + getStringBuffer120()[4];}
        else if (number == 120) {text = "#" + getStringBuffer121()[4];}
        else if (number == 121) {text = "#" + getStringBuffer122()[4];}
        else if (number == 122) {text = "#" + getStringBuffer123()[4];}
        else if (number == 123) {text = "#" + getStringBuffer124()[4];}
        else if (number == 124) {text = "#" + getStringBuffer125()[4];}
        else if (number == 125) {text = "#" + getStringBuffer126()[4];}
        else if (number == 126) {text = "#" + getStringBuffer127()[4];}
        else if (number == 127) {text = "#" + getStringBuffer128()[4];}
        else if (number == 128) {text = "#" + getStringBuffer129()[4];}
        else if (number == 129) {text = "#" + getStringBuffer130()[4];}
        else if (number == 130) {text = "#" + getStringBuffer131()[4];}
        else if (number == 131) {text = "#" + getStringBuffer132()[4];}
        else if (number == 132) {text = "#" + getStringBuffer133()[4];}
        else if (number == 133) {text = "#" + getStringBuffer134()[4];}

        StringBuilder line = new StringBuilder();

        int indicesVertices = 0;
        int indicesCoordinateV;
        int indicesNormalsV;
        int indicesTextureV;
        float[] v1 = new float[3];
        float[] v2 = new float[3];
        float[] v3 = new float[3];
        float[] uv1 = new float[2];
        float[] uv2 = new float[2];
        float[] uv3 = new float[2];
        float[] deltaPos1 = new float[3];
        float[] deltaPos2 = new float[3];
        float[] deltaUV1 = new float[2];
        float[] deltaUV2 = new float[2];
        float r = 0;
        float[] tangent = new float[3];
        float[] bitangent = new float[3];

        for (char msg : text.toCharArray()){
            line.append(msg);
            if (msg == 10){
                String[] currentLine = line.toString().split(" ");
                if(line.toString().startsWith("# ")){
                    if(currentLine[2].equals("facets\r\n")){//\r
                        indicesVertices = Integer.parseInt(currentLine[1]);
                        verticesArray[number] = new float[indicesVertices*18*3];
                        indicesArrayVertices[number] = new int [indicesVertices*3];
//                        System.out.println("Количество треугольников: " + indicesVertices);
                        indicesVertices = 0;
                    }
                } else if (line.toString().startsWith("f ")){
                    //первая тройка
                    //координаты вершины
                    indicesCoordinateV = (Integer.parseInt(currentLine[1].split("/")[0]) - 1);
                    verticesArray[number][indicesVertices * 18] = coordinatesArray[number][indicesCoordinateV * 3];
                    verticesArray[number][indicesVertices * 18 + 1] = coordinatesArray[number][indicesCoordinateV * 3 + 1];
                    verticesArray[number][indicesVertices * 18 + 2] = coordinatesArray[number][indicesCoordinateV * 3 + 2];
                    v1[0] = coordinatesArray[number][indicesCoordinateV * 3];
                    v1[1] = coordinatesArray[number][indicesCoordinateV * 3 + 1];
                    v1[2] = coordinatesArray[number][indicesCoordinateV * 3 + 2];
                    //нормали
                    indicesNormalsV = (Integer.parseInt(currentLine[1].split("/")[2]) - 1);
                    verticesArray[number][indicesVertices * 18 + 3] = normalsArray[number][indicesNormalsV * 3];
                    verticesArray[number][indicesVertices * 18 + 4] = normalsArray[number][indicesNormalsV * 3 + 1];
                    verticesArray[number][indicesVertices * 18 + 5] = normalsArray[number][indicesNormalsV * 3 + 2];
                    //цвета
                    verticesArray[number][indicesVertices * 18 + 6] = 1.0f;
                    verticesArray[number][indicesVertices * 18 + 7] = 1.0f;
                    verticesArray[number][indicesVertices * 18 + 8] = 0.0f;
                    verticesArray[number][indicesVertices * 18 + 9] = 0.0f;
                    //текстурные координаты
                    indicesTextureV = (Integer.parseInt(currentLine[1].split("/")[1]) - 1);
                    verticesArray[number][indicesVertices * 18 + 10] = texturesArray[number][indicesTextureV * 2];
                    verticesArray[number][indicesVertices * 18 + 11] = texturesArray[number][indicesTextureV * 2 + 1];
                    uv1[0] = texturesArray[number][indicesTextureV * 2];
                    uv1[1] = texturesArray[number][indicesTextureV * 2 + 1];

                    indicesArrayVertices[number][indicesVertices] = indicesVertices++;

                    //вторая тройка
                    //координаты вершины
                    indicesCoordinateV = (Integer.parseInt(currentLine[2].split("/")[0]) - 1);
                    verticesArray[number][indicesVertices * 18] = coordinatesArray[number][indicesCoordinateV * 3];
                    verticesArray[number][indicesVertices * 18 + 1] = coordinatesArray[number][indicesCoordinateV * 3 + 1];
                    verticesArray[number][indicesVertices * 18 + 2] = coordinatesArray[number][indicesCoordinateV * 3 + 2];
                    v2[0] = coordinatesArray[number][indicesCoordinateV * 3];
                    v2[1] = coordinatesArray[number][indicesCoordinateV * 3 + 1];
                    v2[2] = coordinatesArray[number][indicesCoordinateV * 3 + 2];
                    //нормали
                    verticesArray[number][indicesVertices * 18 + 3] = normalsArray[number][(Integer.parseInt(currentLine[2].split("/")[2]) - 1) * 3];
                    verticesArray[number][indicesVertices * 18 + 4] = normalsArray[number][(Integer.parseInt(currentLine[2].split("/")[2]) - 1) * 3 + 1];
                    verticesArray[number][indicesVertices * 18 + 5] = normalsArray[number][(Integer.parseInt(currentLine[2].split("/")[2]) - 1) * 3 + 2];
                    //цвета
                    verticesArray[number][indicesVertices * 18 + 6] = 1.0f;
                    verticesArray[number][indicesVertices * 18 + 7] = 1.0f;
                    verticesArray[number][indicesVertices * 18 + 8] = 0.0f;
                    verticesArray[number][indicesVertices * 18 + 9] = 0.0f;
                    //текстурные координаты
                    indicesTextureV = (Integer.parseInt(currentLine[2].split("/")[1]) - 1);
                    verticesArray[number][indicesVertices * 18 + 10] = texturesArray[number][indicesTextureV * 2];
                    verticesArray[number][indicesVertices * 18 + 11] = texturesArray[number][indicesTextureV * 2 + 1];
                    uv2[0] = texturesArray[number][indicesTextureV * 2];
                    uv2[1] = texturesArray[number][indicesTextureV * 2 + 1];

                    indicesArrayVertices[number][indicesVertices] = indicesVertices++;

                    //третья тройка
                    //координаты вершины
                    indicesCoordinateV = (Integer.parseInt(currentLine[3].split("/")[0]) - 1);
                    verticesArray[number][indicesVertices * 18] = coordinatesArray[number][indicesCoordinateV * 3];
                    verticesArray[number][indicesVertices * 18 + 1] = coordinatesArray[number][indicesCoordinateV * 3 + 1];
                    verticesArray[number][indicesVertices * 18 + 2] = coordinatesArray[number][indicesCoordinateV * 3 + 2];
                    v3[0] = coordinatesArray[number][indicesCoordinateV * 3];
                    v3[1] = coordinatesArray[number][indicesCoordinateV * 3 + 1];
                    v3[2] = coordinatesArray[number][indicesCoordinateV * 3 + 2];
                    //нормали
                    indicesNormalsV = (Integer.parseInt(currentLine[3].split("/")[2].split("\r")[0]) - 1);//.split("\r")[0]
                    verticesArray[number][indicesVertices * 18 + 3] = normalsArray[number][indicesNormalsV * 3];
                    verticesArray[number][indicesVertices * 18 + 4] = normalsArray[number][indicesNormalsV * 3 + 1];
                    verticesArray[number][indicesVertices * 18 + 5] = normalsArray[number][indicesNormalsV * 3 + 2];
                    //цвета
                    verticesArray[number][indicesVertices * 18 + 6] = 1.0f;
                    verticesArray[number][indicesVertices * 18 + 7] = 1.0f;
                    verticesArray[number][indicesVertices * 18 + 8] = 0.0f;
                    verticesArray[number][indicesVertices * 18 + 9] = 0.0f;
                    //текстурные координаты
                    indicesTextureV = (Integer.parseInt(currentLine[3].split("/")[1]) - 1);
                    verticesArray[number][indicesVertices * 18 + 10] = texturesArray[number][indicesTextureV * 2];
                    verticesArray[number][indicesVertices * 18 + 11] = texturesArray[number][indicesTextureV * 2 + 1];
                    uv3[0] = texturesArray[number][indicesTextureV * 2];
                    uv3[1] = texturesArray[number][indicesTextureV * 2 + 1];

                    //расчёт тангента и битангента
                    deltaPos1[0] = v2[0]-v1[0];
                    deltaPos1[1] = v2[1]-v1[1];
                    deltaPos1[2] = v2[2]-v1[2];

                    deltaPos2[0] = v3[0]-v1[0];
                    deltaPos2[1] = v3[1]-v1[1];
                    deltaPos2[2] = v3[2]-v1[2];

                    deltaUV1[0] = uv2[0]-uv1[0];
                    deltaUV1[1] = uv2[1]-uv1[1];

                    deltaUV2[0] = uv3[0]-uv1[0];
                    deltaUV2[1] = uv3[1]-uv1[1];

                    r = 1.0f / (deltaUV1[0] * deltaUV2[1] - deltaUV1[1] * deltaUV2[0]);
                    tangent[0] = (deltaPos1[0] * deltaUV2[1] -deltaPos2[0] * deltaUV1[1]) * r;
                    tangent[1] = (deltaPos1[1] * deltaUV2[1] -deltaPos2[1] * deltaUV1[1]) * r;
                    tangent[2] = (deltaPos1[2] * deltaUV2[1] -deltaPos2[2] * deltaUV1[1]) * r;
                    bitangent[0] = (deltaPos2[0] * deltaUV1[0] -deltaPos1[0] * deltaUV2[0]) * r;
                    bitangent[1] = (deltaPos2[1] * deltaUV1[0] -deltaPos1[1] * deltaUV2[0]) * r;
                    bitangent[2] = (deltaPos2[2] * deltaUV1[0] -deltaPos1[2] * deltaUV2[0]) * r;

                    //присваиваем значения тангента и битангента каждой вершино по порядку
                    //тангент 1
                    verticesArray[number][(indicesVertices - 2) * 18 + 12] = tangent[0];
                    verticesArray[number][(indicesVertices - 2) * 18 + 13] = tangent[1];
                    verticesArray[number][(indicesVertices - 2) * 18 + 14] = tangent[2];
                    //битангент 1
                    verticesArray[number][(indicesVertices - 2) * 18 + 15] = bitangent[0];
                    verticesArray[number][(indicesVertices - 2) * 18 + 16] = bitangent[1];
                    verticesArray[number][(indicesVertices - 2) * 18 + 17] = bitangent[2];
                    //тангент 2
                    verticesArray[number][(indicesVertices - 1) * 18 + 12] = tangent[0];
                    verticesArray[number][(indicesVertices - 1) * 18 + 13] = tangent[1];
                    verticesArray[number][(indicesVertices - 1) * 18 + 14] = tangent[2];
                    //битангент 2
                    verticesArray[number][(indicesVertices - 1) * 18 + 15] = bitangent[0];
                    verticesArray[number][(indicesVertices - 1) * 18 + 16] = bitangent[1];
                    verticesArray[number][(indicesVertices - 1) * 18 + 17] = bitangent[2];
                    //тангент 3
                    verticesArray[number][indicesVertices * 18 + 12] = tangent[0];
                    verticesArray[number][indicesVertices * 18 + 13] = tangent[1];
                    verticesArray[number][indicesVertices * 18 + 14] = tangent[2];
                    //битангент 3
                    verticesArray[number][indicesVertices * 18 + 15] = bitangent[0];
                    verticesArray[number][indicesVertices * 18 + 16] = bitangent[1];
                    verticesArray[number][indicesVertices * 18 + 17] = bitangent[2];

                    indicesArrayVertices[number][indicesVertices] = indicesVertices++;
                }
                line = new StringBuilder();
            }
        }
    }

    public static String[] getStringBuffer1()   { return model[0];   }
    public static String[] getStringBuffer2()   { return model[1];   }
    public static String[] getStringBuffer3()   { return model[2];   }
    public static String[] getStringBuffer4()   { return model[3];   }
    public static String[] getStringBuffer5()   { return model[4];   }
    public static String[] getStringBuffer6()   { return model[5];   }
    public static String[] getStringBuffer7()   { return model[6];   }
    public static String[] getStringBuffer8()   { return model[7];   }
    public static String[] getStringBuffer9()   { return model[8];   }
    public static String[] getStringBuffer10()  { return model[9];   }
    public static String[] getStringBuffer11()  { return model[10];  }
    public static String[] getStringBuffer12()  { return model[11];  }
    public static String[] getStringBuffer13()  { return model[12];  }
    public static String[] getStringBuffer14()  { return model[13];  }
    public static String[] getStringBuffer15()  { return model[14];  }
    public static String[] getStringBuffer16()  { return model[15];  }
    public static String[] getStringBuffer17()  { return model[16];  }
    public static String[] getStringBuffer18()  { return model[17];  }
    public static String[] getStringBuffer19()  { return model[18];  }
    public static String[] getStringBuffer20()  { return model[19];  }
    public static String[] getStringBuffer21()  { return model[20];  }
    public static String[] getStringBuffer22()  { return model[21];  }
    public static String[] getStringBuffer23()  { return model[22];  }
    public static String[] getStringBuffer24()  { return model[23];  }
    public static String[] getStringBuffer25()  { return model[24];  }
    public static String[] getStringBuffer26()  { return model[25];  }
    public static String[] getStringBuffer27()  { return model[26];  }
    public static String[] getStringBuffer28()  { return model[27];  }
    public static String[] getStringBuffer29()  { return model[28];  }
    public static String[] getStringBuffer30()  { return model[29];  }
    public static String[] getStringBuffer31()  { return model[30];  }
    public static String[] getStringBuffer32()  { return model[31];  }
    public static String[] getStringBuffer33()  { return model[32];  }
    public static String[] getStringBuffer34()  { return model[33];  }
    public static String[] getStringBuffer35()  { return model[34];  }
    public static String[] getStringBuffer36()  { return model[35];  }
    public static String[] getStringBuffer37()  { return model[36];  }
    public static String[] getStringBuffer38()  { return model[37];  }
    public static String[] getStringBuffer39()  { return model[38];  }
    public static String[] getStringBuffer40()  { return model[39];  }
    public static String[] getStringBuffer41()  { return model[40];  }
    public static String[] getStringBuffer42()  { return model[41];  }
    public static String[] getStringBuffer43()  { return model[42];  }
    public static String[] getStringBuffer44()  { return model[43];  }
    public static String[] getStringBuffer45()  { return model[44];  }
    public static String[] getStringBuffer46()  { return model[45];  }
    public static String[] getStringBuffer47()  { return model[46];  }
    public static String[] getStringBuffer48()  { return model[47];  }
    public static String[] getStringBuffer49()  { return model[48];  }
    public static String[] getStringBuffer50()  { return model[49];  }
    public static String[] getStringBuffer51()  { return model[50];  }
    public static String[] getStringBuffer52()  { return model[51];  }
    public static String[] getStringBuffer53()  { return model[52];  }
    public static String[] getStringBuffer54()  { return model[53];  }
    public static String[] getStringBuffer55()  { return model[54];  }
    public static String[] getStringBuffer56()  { return model[55];  }
    public static String[] getStringBuffer57()  { return model[56];  }
    public static String[] getStringBuffer58()  { return model[57];  }
    public static String[] getStringBuffer59()  { return model[58];  }
    public static String[] getStringBuffer60()  { return model[59];  }
    public static String[] getStringBuffer61()  { return model[60];  }
    public static String[] getStringBuffer62()  { return model[61];  }
    public static String[] getStringBuffer63()  { return model[62];  }
    public static String[] getStringBuffer64()  { return model[63];  }
    public static String[] getStringBuffer65()  { return model[64];  }
    public static String[] getStringBuffer66()  { return model[65];  }
    public static String[] getStringBuffer67()  { return model[66];  }
    public static String[] getStringBuffer68()  { return model[67];  }
    public static String[] getStringBuffer69()  { return model[68];  }
    public static String[] getStringBuffer70()  { return model[69];  }
    public static String[] getStringBuffer71()  { return model[70];  }
    public static String[] getStringBuffer72()  { return model[71];  }
    public static String[] getStringBuffer73()  { return model[72];  }
    public static String[] getStringBuffer74()  { return model[73];  }
    public static String[] getStringBuffer75()  { return model[74];  }
    public static String[] getStringBuffer76()  { return model[75];  }
    public static String[] getStringBuffer77()  { return model[76];  }
    public static String[] getStringBuffer78()  { return model[77];  }
    public static String[] getStringBuffer79()  { return model[78];  }
    public static String[] getStringBuffer80()  { return model[79];  }
    public static String[] getStringBuffer81()  { return model[80];  }
    public static String[] getStringBuffer82()  { return model[81];  }
    public static String[] getStringBuffer83()  { return model[82];  }
    public static String[] getStringBuffer84()  { return model[83];  }
    public static String[] getStringBuffer85()  { return model[84];  }
    public static String[] getStringBuffer86()  { return model[85];  }
    public static String[] getStringBuffer87()  { return model[86];  }
    public static String[] getStringBuffer88()  { return model[87];  }
    public static String[] getStringBuffer89()  { return model[88];  }
    public static String[] getStringBuffer90()  { return model[89];  }
    public static String[] getStringBuffer91()  { return model[90];  }
    public static String[] getStringBuffer92()  { return model[91];  }
    public static String[] getStringBuffer93()  { return model[92];  }
    public static String[] getStringBuffer94()  { return model[93];  }
    public static String[] getStringBuffer95()  { return model[94];  }
    public static String[] getStringBuffer96()  { return model[95];  }
    public static String[] getStringBuffer97()  { return model[96];  }
    public static String[] getStringBuffer98()  { return model[97];  }
    public static String[] getStringBuffer99()  { return model[98];  }
    public static String[] getStringBuffer100() { return model[99];  }
    public static String[] getStringBuffer101() { return model[100]; }
    public static String[] getStringBuffer102() { return model[101]; }
    public static String[] getStringBuffer103() { return model[102]; }
    public static String[] getStringBuffer104() { return model[103]; }
    public static String[] getStringBuffer105() { return model[104]; }
    public static String[] getStringBuffer106() { return model[105]; }
    public static String[] getStringBuffer107() { return model[106]; }
    public static String[] getStringBuffer108() { return model[107]; }
    public static String[] getStringBuffer109() { return model[108]; }
    public static String[] getStringBuffer110() { return model[109]; }
    public static String[] getStringBuffer111() { return model[110]; }
    public static String[] getStringBuffer112() { return model[111]; }
    public static String[] getStringBuffer113() { return model[112]; }
    public static String[] getStringBuffer114() { return model[113]; }
    public static String[] getStringBuffer115() { return model[114]; }
    public static String[] getStringBuffer116() { return model[115]; }
    public static String[] getStringBuffer117() { return model[116]; }
    public static String[] getStringBuffer118() { return model[117]; }
    public static String[] getStringBuffer119() { return model[118]; }
    public static String[] getStringBuffer120() { return model[119]; }
    public static String[] getStringBuffer121() { return model[120]; }
    public static String[] getStringBuffer122() { return model[121]; }
    public static String[] getStringBuffer123() { return model[122]; }
    public static String[] getStringBuffer124() { return model[123]; }
    public static String[] getStringBuffer125() { return model[124]; }
    public static String[] getStringBuffer126() { return model[125]; }
    public static String[] getStringBuffer127() { return model[126]; }
    public static String[] getStringBuffer128() { return model[127]; }
    public static String[] getStringBuffer129() { return model[128]; }
    public static String[] getStringBuffer130() { return model[129]; }
    public static String[] getStringBuffer131() { return model[130]; }
    public static String[] getStringBuffer132() { return model[131]; }
    public static String[] getStringBuffer133() { return model[132]; }
    public static String[] getStringBuffer134() { return model[133]; }

    //////////////////////////////////////////////////////////////////////////////
    /**                Передача готовых массивов на отрисовку                  **/
    //////////////////////////////////////////////////////////////////////////////
    public static float[] getVertexArray(int i){
        return verticesArray[i];
    }
    public static  int[] getIndicesArray(int i){
        return indicesArrayVertices[i];
    }
}
