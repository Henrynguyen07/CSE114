package team_stats;
import java.util.*;
public class HW5Driver {
    private static BaseballTeam yankees;
    private static BaseballTeam mets;
    private static BaseballTeam currentTeam = null;

    public static void main(String[] args) {
        loadYankeesData();
        loadMetsData();
        
        // ADD THE REST OF YOUR PROGRAM HERE
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Baseball Statistics Sorter");
        boolean selectFlag = false;
        while (selectFlag == false) {
            System.out.println("***** Session Menu *****");
            System.out.println("Y) Play with Yankees Stats");
            System.out.println("M) Play with Mets Stats");
            System.out.println("Q) Quit");
            System.out.print("Selection: ");
            String selection = input.nextLine();
            selection = selection.toUpperCase();
            System.out.println();
            switch (selection) {
                case "Y":
                    System.out.println(yankees);
                    menu(yankees);
                    break;
                case "M":
                    System.out.println(mets);
                    menu(mets);
                    break;
                case "Q":
                    selectFlag = true;
                    System.out.println("Quitting Session Menu...");
                    break;
                default:
                    System.out.println("Invalid Selection!!");
                    break;
            }
        }
    }
    // ADD ANY HELPER METHODS YOU LIKE
    private static void loadYankeesData() {
        yankees = new BaseballTeam("New York", "Yankees");
        String[] yankeesHittersData = {
            "Luke Voit,132,28,44,14,33",
            "Jace Peterson,10,0,3,0,0",
            "Miguel Andujar,573,83,170,27,92",
            "Ronald Torreyes,100,9,28,0,7",
            "Aaron Judge,413,77,115,27,67",
            "Gleyber Torres,431,54,117,24,77",
            "Didi Gregorius,504,89,135,27,86",
            "Giancarlo Stanton,617,102,164,38,100",
            "Clint Frazier,34,9,9,0,1",
            "Andrew McCutchen,87,18,22,5,10",
            "Billy McKinney,4,0,1,0,0",
            "Aaron Hicks,480,90,119,27,79",
            "Austin Romine,242,30,59,10,42",
            "Brett Gardner,530,95,125,12,45",
            "Tyler Austin,121,16,27,8,23",
            "Neil Walker,347,48,76,11,46",
            "Greg Bird,272,23,54,11,38",
            "Adeiny Hechavarria,36,3,7,2,2",
            "Gary Sanchez,323,51,60,18,53",
            "Brandon Drury,51,2,9,1,7",
            "Kyle Higashioka,72,6,12,3,6",
            "Tyler Wade,66,8,11,1,5",
            "Shane Robinson,49,8,7,1,2",
            "Masahiro Tanaka,6,1,0,0,0",
            "Luis Severino,5,0,0,0,0",
            "Lance Lynn,2,0,0,0,0",
            "Sonny Gray,2,1,0,0,0",
            "Jonathan Loaisiga,2,0,0,0,0",
            "Domingo German,1,0,0,0,0",
            "Dellin Betances,1,0,0,0,0",
            "Luis Cessa,1,0,0,0,0",
            "Giovanny Gallegos,1,0,0,0,0"
        };
        for (int i = 0; i < yankeesHittersData.length; i++) {
            BaseballHitter bH = makeHitter(yankeesHittersData[i]);
            yankees.addHitter(bH);
        }
        String[] yankeesPitchersData = {
            "Luis Severino,19,8,76,3.39",
            "Masahiro Tanaka,12,6,68,3.75",
            "CC Sabathia,9,7,72,3.65",
            "Sonny Gray,11,9,73,4.90",
            "Domingo German,2,6,55,5.57",
            "J.A. Happ,7,0,20,2.69",
            "Aroldis Chapman,3,0,15,2.45",
            "Chad Green,8,3,22,2.50",
            "David Robertson,8,3,30,3.23",
            "Dellin Betances,4,6,22,2.70",
            "Jonathan Holder,1,3,27,3.14",
            "Lance Lynn,3,2,26,4.14",
            "Luis Cessa,1,4,27,5.24",
            "A.J. Cole,3,1,23,4.26",
            "Chasen Shreve,2,2,23,4.26",
            "Adam Warren,0,1,9,2.70",
            "Jordan Montgomery,2,0,11,3.62",
            "Zach Britton,1,0,10,2.88",
            "Jonathan Loaisiga,2,0,17,5.11",
            "Tommy Kahnle,2,0,22,6.56",
            "David Hale,0,0,3,2.53",
            "Giovanny Gallegos,0,0,5,4.50",
            "Stephen Tarpley,0,0,3,3.00",
            "Chance Adams,0,1,7,7.04",
            "Justus Sheffield,0,0,3,10.13",
            "George Kontos,0,0,0,0.00"
        };
        for (int i = 0; i < yankeesPitchersData.length; i++) {
            BaseballPitcher bP = makePitcher(yankeesPitchersData[i]);
            yankees.addPitcher(bP);
        }
    }
    private static void loadMetsData() {
        mets = new BaseballTeam("New York", "Mets");
        String[] metsHittersData = {
            "Jerry Blevins,2,0,1,0,1",
            "Juan Lagares,59,9,20,0,6",
            "P.J. Conlon,3,1,1,0,0",
            "Jeff McNeil,225,35,74,3,19",
            "Matt Harvey,7,0,2,0,0",
            "Asdrubal Cabrera,375,48,104,18,58",
            "Wilmer Flores,386,43,103,11,51",
            "Brandon Nimmo,433,77,114,17,47",
            "Yoenis Cespedes,141,20,37,9,29",
            "Amed Rosario,554,76,142,9,51",
            "Austin Jackson,198,17,49,3,19",
            "Michael Conforto,543,78,132,28,82",
            "Adrian Gonzalez,169,15,40,6,26",
            "Dominic Smith,143,14,32,5,11",
            "Jay Bruce,319,31,71,9,37",
            "Devin Mesoraco,203,23,45,10,30",
            "Todd Frazier,408,54,87,18,59",
            "Kevin Plawecki,238,33,50,7,30",
            "Luis Guillorme,67,4,14,0,5",
            "Jose Bautista,245,37,50,9,37",
            "Travis d'Arnaud,15,1,3,1,3",
            "Zack Wheeler,56,4,11,0,4",
            "Jose Reyes,228,30,43,4,16",
            "Tomas Nido,84,10,14,1,9",
            "Jack Reinheimer,30,4,5,0,0",
            "Jacob deGrom,67,1,11,0,5",
            "Jason Vargas,27,2,4,0,0",
            "Jose Lobaton,49,3,7,0,4",
            "Phillip Evans,21,1,3,0,1",
            "Noah Syndergaard,47,0,6,0,3",
            "Steven Matz,46,2,5,2,4",
            "Ty Kelly,11,1,1,0,0",
            "Seth Lugo,11,1,1,0,0",
            "Matt den Dekker,18,0,0,0,1",
            "Corey Oswalt,18,0,0,0,0",
            "Paul Sewald,5,0,0,0,0",
            "Kevin Kaczmarski,4,0,0,0,0",
            "Robert Gsellman,3,1,0,0,0",
            "David Wright,2,0,0,0,0",
            "Tim Peterson,2,0,0,0,0",
            "Drew Gagnon,1,0,0,0,1",
            "Buddy Baumann,1,0,0,0,0",
            "Hansel Robles,1,0,0,0,0",
            "Chris Beck,1,0,0,0,0",
            "Jacob Rhame,1,0,0,0,0",
            "Tyler Bashlor,1,0,0,0,0"
        };
        for (int i = 0; i < metsHittersData.length; i++) {
            BaseballHitter bH = makeHitter(metsHittersData[i]);
            mets.addHitter(bH);
        }
        String[] metsPitchersData = {
            "Jacob deGrom,10,9,48,1.70",
            "Zack Wheeler,12,7,69,3.31",
            "Noah Syndergaard,13,4,55,3.03",
            "Steven Matz,5,11,77,3.97",
            "Jason Vargas,7,9,60,5.77",
            "Corey Oswalt,3,3,43,5.85",
            "Jeurys Familia,4,4,13,2.88",
            "Seth Lugo,3,4,36,2.66",
            "Robert Gsellman,6,3,44,4.28",
            "Paul Sewald,0,7,39,6.07",
            "Jerry Blevins,3,2,24,4.85",
            "Jacob Rhame,1,2,21,5.85",
            "Tyler Bashlor,0,3,16,4.22",
            "Drew Smith,1,1,11,3.54",
            "Tim Peterson,2,2,19,6.18",
            "Matt Harvey,0,2,21,7.00",
            "Anthony Swarzak,0,2,18,6.15",
            "AJ Ramos,2,2,14,6.41",
            "Hansel Robles,2,2,11,5.03",
            "Drew Gagnon,2,1,11,5.25",
            "Chris Beck,0,0,6,5.23",
            "Daniel Zamora,1,0,3,3.00",
            "P.J. Conlon,0,0,7,8.22",
            "Chris Flexen,0,2,13,12.79",
            "Bobby Wahl,0,1,6,10.13",
            "Gerson Bautista,0,1,6,12.46",
            "Buddy Baumann,0,1,8,24.00",
            "Eric Hanhold,0,0,2,7.71",
            "Scott Copeland,0,0,0,0.00",
            "Jose Reyes,0,0,6,54.00"
        };
        for (int i = 0; i < metsPitchersData.length; i++) {
            BaseballPitcher bP = makePitcher(metsPitchersData[i]);
            mets.addPitcher(bP);
        }
    }
    private static BaseballHitter makeHitter(String hitterData) {
        String[] hitterTextData = hitterData.split(",");
        String name = hitterTextData[0];
        int ab = Integer.parseInt(hitterTextData[1]);
        int r = Integer.parseInt(hitterTextData[2]);
        int h = Integer.parseInt(hitterTextData[3]);
        int hr = Integer.parseInt(hitterTextData[4]);
        int rbi = Integer.parseInt(hitterTextData[5]);
        return new BaseballHitter(name, ab, r, h, hr, rbi);
    }
    private static BaseballPitcher makePitcher(String pitcherData) {
        String[] pitcherTextData = pitcherData.split(",");
        String name = pitcherTextData[0];
        int w = Integer.parseInt(pitcherTextData[1]);
        int l = Integer.parseInt(pitcherTextData[2]);
        int r = Integer.parseInt(pitcherTextData[3]);
        double era = Double.parseDouble(pitcherTextData[4]);
        return new BaseballPitcher(name, w, l, r, era);
    }
    public static void menu(BaseballTeam team) {
        boolean quitFlag = false;
        while (quitFlag == false) {
            Scanner input1 = new Scanner(System.in);
            System.out.println();
            System.out.println("***** Team Stats Menu *****");
            System.out.println("a) Sort Hitters by name");
            System.out.println("b) Sort hitters by at bats");
            System.out.println("c) Sort hitters by runs");
            System.out.println("d) Sort hitters by hits");
            System.out.println("e) Sort hitters by home runs");
            System.out.println("f) Sort hitters by runs batted in");
            System.out.println("g) Sort hitters by batting average");
            System.out.println("h) Sort pitchers by name");
            System.out.println("i) Sort pitchers by wins");
            System.out.println("j) Sort pitchers by losses");
            System.out.println("k) Sort pitchers by runs");
            System.out.println("l) Sort pitchers by era");
            System.out.println("q) Return to Session Menu");
            System.out.print("Selection: ");
            
            String menuSelect = input1.nextLine();
            menuSelect = menuSelect.toLowerCase();
            switch (menuSelect) {
                case "a":
                    team.sortHitters("a");
                    System.out.println(team.toString());
                    break;
                case "b":
                    team.sortHitters("b");
                    System.out.println(team.toString());
                    break;
                case "c":
                    team.sortHitters("c");
                    System.out.println(team.toString());
                    break;
                case "d":
                    team.sortHitters("d");
                    System.out.println(team.toString());
                    break;
                case "e":
                    team.sortHitters("e");
                    System.out.println(team.toString());
                    break;
                case "f":
                    team.sortHitters("f");
                    System.out.println(team.toString());
                    break;
                case "g":
                    team.sortHitters("g");
                    System.out.println(team.toString());
                    break;
                case "h":
                    team.sortPitchers("h");
                    System.out.println(team.toString());
                    break;
                case "i":
                    team.sortPitchers("i");
                    System.out.println(team.toString());
                    break;
                case "j":
                    team.sortPitchers("j");
                    System.out.println(team.toString());
                    break;
                case "k":
                    team.sortPitchers("k");
                    System.out.println(team.toString());
                    break;
                case "l":
                    team.sortPitchers("l");
                    System.out.println(team.toString());
                    break;
                case "q":
                    quitFlag = true;
                    System.out.println("Quitting Team Stats Menu...");
                    break;
                default:
                    System.out.println("Invalid Selection!!");
                    break;
            }
            System.out.println();
        }
    }
}
