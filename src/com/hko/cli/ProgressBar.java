package com.hko.cli;
//based on https://stackoverflow.com/a/1001340
public class ProgressBar {
    public static void updateProgress(double progressPercentage) {
        final int width = 50; // progress bar width in chars
        System.out.print("\rProgress: [");
        int i = 0;
        for (; i <= (int) (progressPercentage * width); i++) {
            System.out.print(".");
        }
        for (; i < width; i++) {
            System.out.print(" ");
        }
        if(progressPercentage*100<99){
            System.out.print("] %"+Math.round(progressPercentage*100));
        }
        else{
            System.out.print("] done %100");
        }
    }
}
