package com.example.davidbezalellaoli.lastmobilemikroskil.utils;

/**
 * Created by ASUS on 30/11/2017.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincent on 16/11/17.
 */

public class StringColorParser {
    public int r=0;
    public int g=0;
    public int b=0;

    private String s;

    public StringColorParser(String s){
        this.s = s.replace(" ","");
        //if(s)
    }

    public String getColorCode(){
        List<String> sectionedString = getSectionedString();
        for(int i=0;i<sectionedString.get(0).length();i++){
            r+=(int) sectionedString.get(0).charAt(i);
            //LogHelper.debug("R",sectionedString.get(0).charAt(i)+" = "+(int)sectionedString.get(0).charAt(i));
        }
        for(int i=0;i<sectionedString.get(1).length();i++){
            g+=(int) sectionedString.get(1).charAt(i);
            //LogHelper.debug("G",sectionedString.get(1).charAt(i)+" = "+(int)sectionedString.get(1).charAt(i));
        }
        for(int i=0;i<sectionedString.get(2).length();i++){
            b+=(int) sectionedString.get(2).charAt(i);
            //LogHelper.debug("B",sectionedString.get(2).charAt(i)+" = "+(int)sectionedString.get(2).charAt(i));
        }

        //LogHelper.debug("getColorCode","r = "+r+";g = "+g+";b = "+b);
        r = r % 255;
        g = g % 255;
        b = b % 255;


        return "#"+getHex(r)+getHex(g)+getHex(b);
    }

    private List<String> getSectionedString(){

        int mod = s.length()%3;
        int sectionSize = s.length()/3;

        int firstPos = sectionSize-1;
        int secondPos= firstPos + sectionSize;
        int thirdPos = secondPos + sectionSize;



        if(mod==1) {
            secondPos += 1;
            thirdPos +=1;
        }
        if(mod==2){
            secondPos+=1;
            thirdPos+=2;
        }
        /*LogHelper.debug("getSectionedString","mod="+mod+";sectionSize="+sectionSize+
                ";firstPos="+firstPos+";secondPos="+secondPos+";thirdPos="+thirdPos);*/
        int[] startingPos = new int[]{0,firstPos+1,secondPos+1};
        int[] endingPos = new int[]{firstPos,secondPos,thirdPos};

        List<String> sectionedList = new ArrayList<>();

        for(int i=0;i<3;i++){
            String res="";
            for(int j=startingPos[i];j<=endingPos[i];j++){
                res+= s.charAt(j);
            }
            sectionedList.add(res);
        }
        return sectionedList;
    }


    private String getHex(int i){
        if(i<10)
            return "0"+i;
        else
            return Integer.toHexString(i);
    }

}

