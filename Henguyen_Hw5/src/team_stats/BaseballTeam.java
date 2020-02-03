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
public class BaseballTeam {
    // Constructor Class
    private BaseballPitcher[] pitchers;
    private BaseballHitter[] hitters;
    private String state;
    private String team;
    private int PitcherCounter;
    private int HitterCounter;
    private int wins;
    private int losses;
    private double PCT;
    private int RS;
    private int RA;
    private double winPercentage;
    
    // Initalize Method
    public BaseballTeam(String state, String team) {
        this.state = state;
        this.team = team;
        this.pitchers = new BaseballPitcher[50];
        this.hitters = new BaseballHitter[50];
    }
    
    public void addPitcher(BaseballPitcher BallPitcher) {
        this.pitchers[this.PitcherCounter] = BallPitcher;
        wins += BallPitcher.getWins();
        losses += BallPitcher.getLosses();
        RA += BallPitcher.getRunsAllowed();
        PitcherCounter ++;
    }
    public void addHitter(BaseballHitter BallHitter) {
        this.hitters[this.HitterCounter] = BallHitter;
        RS += BallHitter.getRunsScored();
        HitterCounter++;
    }
    public String getState() {
        return state;
    }
    public String getTeam() {
        return team;
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public double getWinningPercentage() {
        PCT = ((double)(wins)/(wins + losses));
        return PCT;
    }
    public int getRunsScored() {
        return RS;
    }
    public int getRunsAllowed() {
        return RA;
    }
    public double getWinPercentage() {
        return (((double)(RS * RS)) / ((RS * RS) + (RA * RA)));
    }
    public String toString() {
        String Team_Label = state + " " + team;
        String Team_data = String.format("%-15s %-15s %-15s %-15s %-15s", "W", "L", "PCT", "RS", "RA");
        String Team_dataresult = String.format("%-15d %-15d %-15.3f %-15d %-15d", 
                this.getWins(), this.getLosses(), this.getWinningPercentage(), this.getRunsScored(), this.getRunsAllowed());
        String Pythagorean_Label = String.format("Pythagorean Winning Percentage: %.3f \nPythagorean Wins: %-3d \nPythagorean Losses: %-3d", this.getWinPercentage(), this.getWins(), this.getLosses());
        String HitterArrayLabel = String.format("%-25s %-20s %-20s %-20s %-20s %-20s %-20s", "Hitter", "AB", "R", "H", "HR", "RBI", "AVG");
        String HitterArray = "";
        String pitcherArrayLabel = String.format("%-25s %-20s %-20s %-20s %-20s" , "Pitcher", "W", "L", "R", "ERA");
        String pitcherArray = "";
        for (int i = 0; i < this.HitterCounter; i++) {
            HitterArray += String.format("%-25s %-20d %-20d %-20d %-20d %-20d %-20.3f \n",
                this.hitters[i].getName(), this.hitters[i].getBats(), this.hitters[i].getRunsScored(), this.hitters[i].getHits(), this.hitters[i].getHomeRuns(), this.hitters[i].getRunsBatted(), this.hitters[i].getBattingAverage());
        }
        for (int i = 0; i < this.PitcherCounter; i++) {
            pitcherArray += String.format("%-25s %-20d %-20d %-20d %-20.3f \n",
                    this.pitchers[i].getName(), this.pitchers[i].getWins(), this.pitchers[i].getLosses(), this.pitchers[i].getRunsAllowed(), this.pitchers[i].getRunAverage());
        }
        return Team_Label + "\n" + Team_data + "\n" + Team_dataresult + "\n\n" + Pythagorean_Label + "\n\n" + HitterArrayLabel + "\n" + HitterArray + "\n" + pitcherArrayLabel + "\n" + pitcherArray;
    }
 
    public void sortHitters(String criteria) {
        int n = this.HitterCounter;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (criteria.equals("a")) {
                    if ((this.hitters[j].getName().compareTo(this.hitters[j+1].getName()) > 0 )) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j+1] = tempHitter;
                        }
                    }   
                if (criteria.equals("b")) {
                    if ((this.hitters[j].getBats() < this.hitters[j+1].getBats())) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j + 1] = tempHitter;
                        }
                    }
                if (criteria.equals("c")) {
                    if ((this.hitters[j].getRunsScored() < this.hitters[j + 1].getRunsScored())) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j + 1] = tempHitter;
                        }
                    }
                if (criteria.equals("d")) {
                    if ((this.hitters[j].getHits() < this.hitters[j + 1].getHits())) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j + 1] = tempHitter;
                        }
                    }
                if (criteria.equals("e")) {
                    if ((this.hitters[j].getHomeRuns() < this.hitters[j + 1].getHomeRuns())) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j + 1] = tempHitter;
                        }
                    }
                if (criteria.equals("f")) {
                    if ((this.hitters[j].getRunsBatted() < this.hitters[j + 1].getRunsBatted())) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j + 1] = tempHitter;
                        }
                    }
                if (criteria.equals("g")) {
                    if ((this.hitters[j].getBattingAverage() < this.hitters[j + 1].getBattingAverage())) {
                        BaseballHitter tempHitter = this.hitters[j];
                        this.hitters[j] = this.hitters[j + 1];
                        this.hitters[j + 1] = tempHitter;
                    }
                }
            }
        }
    }
    public void sortPitchers(String criteria) {
        int n = PitcherCounter;
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array 
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (criteria.equals("h")) {
                    if ((this.pitchers[j].getName().compareTo(this.pitchers[min_idx].getName()) < 0)) {
                        min_idx = j;
                    }
                }
                if (criteria.equals("i")) {
                    if ((this.pitchers[j].getWins() > this.pitchers[min_idx].getWins())) {
                        min_idx = j;
                    }
                }
                if (criteria.equals("j")) {
                    if ((this.pitchers[j].getLosses() > this.pitchers[min_idx].getLosses())) {
                        min_idx = j;
                    }
                }
                if (criteria.equals("k")) {
                    if ((this.pitchers[j].getRunsAllowed() > this.pitchers[min_idx].getRunsAllowed())) {
                        min_idx = j;
                    }
                }
                if (criteria.equals("l")) {
                    if ((this.pitchers[j].getRunAverage() > this.pitchers[min_idx].getRunAverage())) {
                        min_idx = j;
                    }
                }
            }
            // Swap the found minimum element with the first 
            // element 
            BaseballPitcher tempPitcher = pitchers[min_idx];
            pitchers[min_idx] = pitchers[i];
            pitchers[i] = tempPitcher;
        } 
    }
}
