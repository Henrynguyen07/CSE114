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
public class BaseballPitcher {
    private String name;
    private int wins;
    private int losses;
    private int runs;
    private double ERA;
    
    public BaseballPitcher(String name, int W, int L, int R, double ERA) {
        this.name = name;
        this.wins = W;
        this.losses = L;
        this.runs = R;
        this.ERA = ERA;
    }
    
    public String getName() {
        return name;
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public int getRunsAllowed() {
        return runs;
    }
    public double getRunAverage() {
        return ERA;
    }
    public String toString() {
        return name + '\t' + wins + '\t' + losses + '\t' + runs + '\t' + ERA;
    }
}
