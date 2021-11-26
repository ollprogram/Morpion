package fr.ollprogram.morpion.main;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private enum Case{
        X, O, VIDE
    }
    public static void main(String[] args){
        Case[][] grille = initGrille();
        afficheGrille(grille);
        Scanner sc = new Scanner(System.in);
        while(align(grille).equals(Case.VIDE) && ! estRemplie(grille)){
            System.out.println("Circle player: \n line =");
            int y = Integer.parseInt(sc.nextLine());
            System.out.println("col = ");
            int x = Integer.parseInt(sc.nextLine());
            if(grille[y][x].equals(Case.VIDE)) {
                grille[y][x] = Case.O;
            }
            afficheGrille(grille);
            if(align(grille).equals(Case.VIDE)) {
                System.out.println("Cross player: \n line =");
                y = Integer.parseInt(sc.nextLine());
                System.out.println("col =");
                x = Integer.parseInt(sc.nextLine());
                if(grille[y][x].equals(Case.VIDE)) {
                    grille[y][x] = Case.X;
                }
                afficheGrille(grille);
            }
        }
        Case winner = align(grille);
        System.out.println("\033[H\033[2J");
        System.out.flush();
        if(winner.equals(Case.VIDE)) System.out.println("NO WINNER");
        else System.out.println("WINNER IS '"+winner+"'");
    }
    public static Case[][] initGrille(){
        Case[][] grille = new Case[3][3];
        for (Case[] cases : grille) {
            Arrays.fill(cases, Case.VIDE);
        }
        return grille;
    }

    public static Case alignVert(Case[][] grille){
        for(int j = 0; j < grille[0].length; j++){
            int xnum = 0;
            int onum = 0;
            for (Case[] cases : grille) {
                if (cases[j].equals(Case.X)) xnum++;
                else if (cases[j].equals(Case.O)) onum++;
            }
            if(xnum >= 3)return Case.X;
            else if(onum >= 3)return Case.O;
        }
        return Case.VIDE;
    }

    public static Case alignHorz(Case[][] grille){
        for(Case[] line : grille){
            int xnum = 0, onum = 0;
            for(Case c : line){
                if (c.equals(Case.X)) xnum++;
                else if (c.equals(Case.O)) onum++;
            }
            if(xnum >= 3)return Case.X;
            else if(onum >= 3)return Case.O;
        }
        return Case.VIDE;
    }

    public static Case alignDiagG(Case[][] grille){
        int xnum = 0;
        int onum = 0;
        for(int i = 0; i < grille.length; i++){
            if (grille[i][i].equals(Case.X)) xnum++;
            else if (grille[i][i].equals(Case.O)) onum++;
        }
        if(xnum < 3 && onum < 3){
            return Case.VIDE;
        }
        else{
            return (xnum == 3)?Case.X:Case.O;
        }
    }

    public static Case alignDiagD(Case[][] grille){
        int xnum = 0;
        int onum = 0;
        for(int i = 0; i < grille.length ; i++){
            if (grille[i][grille.length-i-1].equals(Case.X)) xnum++;
            else if (grille[i][grille.length-1-i].equals(Case.O)) onum++;
        }
        if(xnum < 3 && onum < 3){
            return Case.VIDE;
        }
        else{
            return (xnum == 3)?Case.X:Case.O;
        }
    }

    public static void afficheGrille(Case[][] grille){
        System.out.println("\033[H\033[2J");
        System.out.flush();
        for (Case[] cases : grille) {
            System.out.print("|");
            for (Case c : cases) {
                if (c.equals(Case.VIDE)) {
                    System.out.print(" " + "|");
                } else {
                    System.out.print(c + "|");
                }
            }
            System.out.println();
        }
    }

    public static Case align(Case[][] grille){
        Case res;
        res = alignDiagD(grille);
        if(!res.equals(Case.VIDE)) return res;
        res = alignDiagG(grille);
        if(!res.equals(Case.VIDE)) return res;
        res = alignVert(grille);
        if(!res.equals(Case.VIDE)) return res;
        res = alignHorz(grille);
        if(!res.equals(Case.VIDE)) return res;
        return Case.VIDE;
    }

    public static boolean estRemplie(Case[][] grille){
        for(Case[] cases : grille){
            for(Case c : cases){
                if(c.equals(Case.VIDE))return false;
            }
        }
        return true;
    }
}
