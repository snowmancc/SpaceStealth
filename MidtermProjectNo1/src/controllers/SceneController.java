package controllers;

import io.CommandSolver.CommandWrapper;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import scene.EndScene;
import scene.MainSceneX;
import scene.MenuScene;
import scene.Scene;

// State Pattern 狀態模式
public class SceneController {

    private MusicController musicController, mainSceneMusic;
    private Scene currentScene;
    private PlayerRecord playerRecord;
    private ArrayList<PlayerRecord>list;
   

    public SceneController() {
        musicController = new MusicController("/resources/BGM/manuMusic.wav");
        mainSceneMusic = new MusicController("/resources/BGM/gameBGM.wav");
        musicController.play();
        list = new ArrayList();
        loadFile();
    }

    public void changeScene(Scene scene) {
        if (currentScene != null) {
            currentScene.sceneEnd();

        }
        currentScene = scene;
        currentScene.sceneBegin();
        
        if (currentScene instanceof MainSceneX) {
            musicController.stop();
            mainSceneMusic.play();
        }
        if (currentScene instanceof EndScene) {
            musicController.play();
            mainSceneMusic.stop();
        }


    }

    public void sceneUpdate(CommandWrapper commands) {
        if (commands != null && currentScene.getKeyCommandListener() != null) {
            commands.actionCommand(currentScene.getKeyCommandListener());
        }
        if (commands != null && currentScene.getMouseCommandListener() != null) {
            commands.actionCommand(currentScene.getMouseCommandListener());
        }
        if (commands != null && currentScene.getTypedListener() != null) {
            commands.actionCommand(currentScene.getTypedListener());
        }
        currentScene.sceneUpdate();
    }

    public void saveCurrentPlayerMessage(String name) { // startTime and 1playerName
        playerRecord = new PlayerRecord(name);

    }
    
    public PlayerRecord getPlayerRecord(){
        return playerRecord;
    }

    public void saveCurrentPlayerRecord(String levelAndSecond) { // finishGate and finishSecond 
        String[] strs = levelAndSecond.split(",");
        playerRecord.setLevel(strs[0]);
        playerRecord.setScore(strs[1]);
        
        updateRecord();
    }
    

    public void updateRecord() { // sort  rank of file and write record to file
        
        list = sortRank(list);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("gamerecord.txt"));
            for(int i = 0; i < list.size(); i++){
                bw.write(list.get(i).getRecord());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Not find file");
        }
    }

    private ArrayList sortRank(ArrayList<PlayerRecord> list) {
        PlayerRecord min = playerRecord;
        for(int i = 0; i < 5; i++){
            if(min.isBetter(list.get(i))){
                PlayerRecord tmp = list.get(i);
                list.set(i, min);
                min = tmp;
            }
        }
        return list;
    }
    
    private void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("gamerecord.txt"));
            while(br.ready()){
                String[] strs = br.readLine().split(",");
                PlayerRecord pr = new PlayerRecord(strs[0],strs[1],strs[2],strs[3]);
                list.add(pr);
            }
            br.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    public ArrayList<PlayerRecord> loadRankRecords() { // read file message for  RankScene
        return list;
    }
    
    public void paint(Graphics g) {
        currentScene.paint(g);
    }

}
