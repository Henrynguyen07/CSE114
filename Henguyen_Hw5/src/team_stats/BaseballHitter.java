/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package team_stats;

/**
 *
 * Author: Henry Nguyen
 * SBU ID: 111484010
 * Net ID; Henguyen
 * CSE 114
 */

public class BaseballHitter {
    private String name;
    private int AB;
    private int R;
    private int H;
    private int HR;
    private int RBI;
    private double AVG;
    
    public BaseballHitter(String name, int ab, int r, int h, int hr, int rbi) {
        this.name = name;
        this.AB = ab;
        this.R = r;
        this.H = h;
        this.HR = hr;
        this.RBI = rbi;
        this.AVG = (double) H/AB;
    }
    
    public String getName() {
        return name;
    }
    public int getBats() {
        return AB;
    }
    public int getRunsScored() {
        return R;
    }
    public int getHits() {
        return H;
    }
    public int getHomeRuns() {
        return HR;
    }
    public int getRunsBatted() {
        return RBI;
    }
    public double getBattingAverage() {
        return AVG;
    }
 
    public String toString() {
        return name + '\t' +  AB + '\t' + R + '\t' + H + '\t' + HR + 't' + RBI;
    }
}