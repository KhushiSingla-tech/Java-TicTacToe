package com.mycompany.tictactoe;

import java.util.HashSet;
import java.util.Scanner;

public class GameBoard 
{
    
    static HashSet<Integer> ur_set = new HashSet<Integer>();
    static HashSet<Integer> comp_set = new HashSet<Integer>();
    
    public static void main(String ar[])
    {
        char [][] gboard = {
            {' ','|',' ','|',' '},
            {'-','|','-','|','-'},
            {' ','|',' ','|',' '},
            {'-','|','-','|','-'},
            {' ','|',' ','|',' '},
        };
        
        printboard(gboard);
        
        Scanner in = new Scanner(System.in);
        
        while(true)
        {
            System.out.println("Enter numbers between 1-9");
            int user_pos = in.nextInt();
            
            while(ur_set.contains(user_pos) || comp_set.contains(user_pos))   // used to check whether position is already filled or not
            {
                System.out.println("Position already filled");
                System.out.println("Retry, Enter numbers between 1-9");
                user_pos = in.nextInt();
            }
            pholder(gboard,user_pos,"You");
            
            String res = getResult();     // get result of game 
            if(res.length() > 0)
            {
                System.out.println(res);
                break;
            }
            

            int cpu_pos = getRandom();
            
            while(ur_set.contains(cpu_pos) || comp_set.contains(cpu_pos))
            {
                cpu_pos = getRandom();
            }
            pholder(gboard,cpu_pos,"Computer");
            
            res = getResult();     // again get result of game after computer has played its part
            if(res.length() > 0)
            {
                System.out.println(res);
                break;
            }
        }
        
    }

    private static void printboard(char[][] gboard)      // used to print board on screen
    {
        for(int r=0;r<gboard.length;r++)
        {
            for(int c=0;c<gboard[0].length;c++)
            {
                System.out.print(gboard[r][c]);
            }
            System.out.println();
        }
    }
    
    private static void pholder(char [][]gboard,int pos,String user)   // handle who is playing and at what position to put symbol
    {
        char c = 'X';
        if(user.equals("You"))
        {
            c = 'X';
            ur_set.add(pos);
        }
        else if(user.equals("Computer"))
        {
            c = 'O';
            comp_set.add(pos);
        }
        
        switch(pos)
        {
            case 1:
            {
                gboard[0][0] = c;
                break;
            }
            case 2:
            {
                gboard[0][2] = c;
                break;
            }
            case 3:
            {
                gboard[0][4] = c;
                break;
            }
            case 4:
            {
                gboard[2][0] = c;
                break;
            }
            case 5:
            {
                gboard[2][2] = c;
                break;
            }
            case 6:
            {
                gboard[2][4] = c;
                break;
            }
            case 7:
            {
                gboard[4][0] = c;
                break;
            }
            case 8:
            {
                gboard[4][2] = c;
                break;
            }
            case 9:
            {
                gboard[4][4] = c;
                break;
            }
            default:
            {
                System.out.println("Invalid input");
            }
        }
        
        printboard(gboard);
        System.out.println("---------------------------------");
    }
    
    static int getRandom()    // generate random number for cpu
    {
        int max = 9;
        int min = 1;
        
        int range = max-min+1;
        int res = (int)(Math.random() * range) + min;
        return res;
    }
    
    static String getResult()   //calculate result of game
    {
        HashSet<Integer> r1 = new HashSet<Integer>();
        r1.add(1);
        r1.add(2);
        r1.add(3);
        
        HashSet<Integer> r2 = new HashSet<Integer>();
        r2.add(4);
        r2.add(5);
        r2.add(6);
        
        HashSet<Integer> r3 = new HashSet<Integer>();
        r3.add(7);
        r3.add(8);
        r3.add(9);
        
        HashSet<Integer> c1 = new HashSet<Integer>();
        c1.add(1);
        c1.add(4);
        c1.add(7);
        
        HashSet<Integer> c2 = new HashSet<Integer>();
        c2.add(2);
        c2.add(5);
        c2.add(8);
        
        HashSet<Integer> c3 = new HashSet<Integer>();
        c3.add(3);
        c3.add(6);
        c3.add(9);
        
        HashSet<Integer> d1 = new HashSet<Integer>();
        d1.add(1);
        d1.add(5);
        d1.add(9);
        
        HashSet<Integer> d2 = new HashSet<Integer>();
        d2.add(3);
        d2.add(5);
        d2.add(7);
        
        HashSet<HashSet> set = new HashSet<HashSet>();
        set.add(r1);
        set.add(r2);
        set.add(r3);
        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(d1);
        set.add(d2);
        
        for(HashSet c : set)
        {
            if(ur_set.containsAll(c))
            {
                return "YOU WON";
            }
            else if(comp_set.containsAll(c))
            {
                return "YOU LOST";
            }
        }
        if(ur_set.size()+comp_set.size() == 9)
        {
            return "DRAW";
        }
        return "";
    }
    
}
