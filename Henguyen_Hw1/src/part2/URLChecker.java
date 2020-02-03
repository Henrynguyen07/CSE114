package part2;
import java.util.*;

/**
 *
 * @author henguyen
 * SBU ID: 111484010
 */

public class URLChecker {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("****************************************************");
        System.out.println(" ***** WELCOME TO HENRY NGUYEN'S URL CHECKER *****");
        System.out.println("****************************************************");
        System.out.print("Enter a Url: ");
        String URL = sc.nextLine();
        int endProtocol = URL.indexOf("://")+3;
        System.out.println("Your URL: " + URL);
        
        int startHost;
        int endHost;
        
        if (URL.contains("www.")){
            startHost = endProtocol + 4;
        } else { 
            startHost = endProtocol;
        }
        
        if (!(URL.contains("://"))) {
            System.out.println("Error - the URL must use an accepted protocol (http://, https://, ftp://, file://");
            System.exit(0);
        }
        
        if (!(URL.contains(".com") || URL.contains(".net") || URL.contains(".edu") || URL.contains(".org") || URL.contains(".gov"))) {
            System.out.println("Error - the URL must use a host name with an accepted extension (.com, .edu, .gov, .net, .org)");
            System.exit(0);
        } else {
            System.out.println("Protocol: " + URL.substring(0, endProtocol));
            if (URL.contains(".com")) {
                endHost = URL.indexOf(".com") + 4;
                System.out.println("Host Name: " + URL.substring(startHost,endHost));
                String startPath = URL.substring(endHost);
                if (startPath.length() >= 1){
                    System.out.println("Path: " + URL.substring(URL.indexOf(startPath) + 1,URL.lastIndexOf("/")));
                    String startFile = startPath.substring(startPath.lastIndexOf("/"));
                    if (startFile.length() > 1) {
                        System.out.println("File Name: " + startPath.substring(startPath.lastIndexOf("/") + 1));
                    } else {
                        System.out.println("File Name: index.html");
                    }
                } else {
                    System.out.println("Path: None");
                    System.out.println("File Name: index.html");
                }
                
            } else if (URL.contains(".net")) {
                endHost = URL.indexOf(".net") + 4;
                System.out.println("Host Name: " + URL.substring(startHost, endHost));
                String startPath = URL.substring(endHost);
                if (startPath.length() >= 1) {
                    System.out.println("Path: " + URL.substring(URL.indexOf(startPath) + 1, URL.lastIndexOf("/")));
                    String startFile = startPath.substring(startPath.lastIndexOf("/"));
                    if (startFile.length() > 1) {
                        System.out.println("File Name: " + startPath.substring(startPath.lastIndexOf("/") + 1));
                    } else {
                        System.out.println("File Name: index.html");
                    }
                } else {
                    System.out.println("Path: NONE");
                    System.out.println("File Name: index.html");
                }
            } else if (URL.contains(".edu")) {
                endHost = URL.indexOf(".edu") + 4;
                System.out.println("Host Name: " + URL.substring(startHost, endHost));
                String startPath = URL.substring(endHost);
                if (startPath.length() >= 1) {
                    System.out.println("Path: " + URL.substring(URL.indexOf(startPath) + 1, URL.lastIndexOf("/")));
                    String startFile = startPath.substring(startPath.lastIndexOf("/"));
                    if (startFile.length() > 1) {
                        System.out.println("File Name: " + startPath.substring(startPath.lastIndexOf("/") + 1));
                    } else {
                        System.out.println("File Name: index.html");
                    }
                } else {
                    System.out.println("Path: None");
                    System.out.println("File Name: index.html");
                }
            } else if (URL.contains(".gov")) {
                endHost = URL.indexOf(".gov") + 4;
                System.out.println("Host Name: " + URL.substring(startHost, endHost));
                String startPath = URL.substring(endHost);
                if (startPath.length() >= 1) {
                    System.out.println("Path: " + URL.substring(URL.indexOf(startPath) + 1, URL.lastIndexOf("/")));
                    String startFile = startPath.substring(startPath.lastIndexOf("/"));
                    if (startFile.length() > 1) {
                        System.out.println("File Name: " + startPath.substring(startPath.lastIndexOf("/") + 1));
                    } else {
                        System.out.println("File Name: index.html");
                    }
                } else {
                    System.out.println("Path: None");
                    System.out.println("File Name: index.html");
                }
            } else {
                endHost = URL.indexOf(".org") + 4;
                System.out.println("Host Name: " + URL.substring(startHost, endHost));
                String startPath = URL.substring(endHost);
                if (startPath.length() >= 1) {
                    System.out.println("Path: " + URL.substring(URL.indexOf(startPath) + 1, URL.lastIndexOf("/")));
                    String startFile = startPath.substring(startPath.lastIndexOf("/"));
                    if (startFile.length() > 1) {
                        System.out.println("File Name: " + startPath.substring(startPath.lastIndexOf("/") + 1));
                    } else {
                        System.out.println("File Name: index.html");
                    }
                } else {
                    System.out.println("Path: None");
                    System.out.println("File Name: index.html");
                }
            }
         }
    }
}