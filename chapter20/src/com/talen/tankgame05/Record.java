package com.talen.tankgame05;

import java.io.*;
import java.util.Vector;

public class Record {
    static int totalTanksYouDestroy = 0;
    static int localTanksYouDestroy = 0;
    static int totalTimesOfDestroyed = 0;
    static int localTimesOfDestroyed = 0;

    // 用来从记录中读取保存的坦克类型和状态
    static Vector<Node> nodes = new Vector<>();

    private static String recordFilePath = "e:/Java_practice/HSP_practice/chapter20/tankGameRecord.txt";
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;

    // 这里的tanksCollection就是MyPanel中的，含义相同，都指的是包括hero和敌方坦克的一个集合
    // 游戏结束时，要将每个存活坦克的状态保留下来
    private static Vector<Tank> tanksCollection = new Vector<>();

    public static void setTanksCollection(Vector<Tank> tanksCollection) {
        Record.tanksCollection = tanksCollection;
    }

    public static void getNodeAndTankRec() {

        try {
            String line = "";
            br = new BufferedReader(new FileReader(recordFilePath));
            totalTanksYouDestroy = Integer.parseInt(br.readLine());
            totalTimesOfDestroyed = Integer.parseInt(br.readLine());
            while((line = br.readLine()) != null) {
                String[] status = line.split(" ");
                int x_ = Integer.parseInt(status[1]);
                int y_ = Integer.parseInt(status[2]);
                int dir_ = Integer.parseInt(status[3]);
                nodes.add(new Node(status[0], x_, y_, dir_));
            }
            System.out.println("读取游戏记录成功...");
        } catch (IOException e) {
            System.out.println("游戏记录文件不存在，使用默认值");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFilePath));
            bw.write(totalTanksYouDestroy + "");
            bw.newLine();
            bw.write(totalTimesOfDestroyed + "");
            bw.newLine();
            for(Tank tank: tanksCollection) {
                if (tank.isAlive) {
                    bw.write(tank.getClass().getSimpleName() + " " + tank.getX() + " " + tank.getY() + " " + tank.getDir());
                    bw.newLine();
                }
            }
            System.out.println("保存游戏记录成功...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
