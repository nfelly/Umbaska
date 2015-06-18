package uk.co.umbaska.GattSk.Effects.SimpleScoreboards;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import uk.co.umbaska.GattSk.Extras.ScoreboardManagers;

import java.util.HashMap;

/**
 * Created by Zachary on 12/25/2014.
 */
public class SimpleScoreboard {
	//private HashMap<String, HashMap<Integer, String>> = new HashMap<String, HashMap<Integer, String>>();
	private static HashMap<Scoreboard, HashMap<Integer, String>> ScoreboardTracker = new HashMap<>();
	private static HashMap<String, Scoreboard> SimpleScoreboards = new HashMap<String, Scoreboard>();

	private static ScoreboardManager sbm = Bukkit.getScoreboardManager();

	public static void debug(String board){
		Scoreboard targetBoard = SimpleScoreboards.get(board);
		Bukkit.broadcastMessage("Scoreboard '" + board + "': " + targetBoard.toString());
		Bukkit.broadcastMessage("Objectives: " + targetBoard.getObjectives().toString());
		Bukkit.broadcastMessage("Title: " + targetBoard.getObjective("SimpleScoreboard").getDisplayName());
		Bukkit.broadcastMessage("HashMap 'ScoreboardTracker': " + ScoreboardTracker.toString());
		Bukkit.broadcastMessage("HashMap 'SimpleScoreboards': " + SimpleScoreboards.toString());
		Bukkit.broadcastMessage("HashMap 'ScoreboardTracker.get': " + ScoreboardTracker.get(targetBoard).toString());
	}

	public static void newSimpleScoreboard(String name){
		Scoreboard newBoard = sbm.getNewScoreboard();
		newBoard.registerNewObjective("SimpleScoreboard", "dummy");
		newBoard.getObjective("SimpleScoreboard").setDisplaySlot(DisplaySlot.SIDEBAR);
		SimpleScoreboards.put(name, newBoard);
		ScoreboardManagers.boardList.put(name, newBoard);
		HashMap<Integer, String> newMap = new HashMap<Integer, String>();
		newMap.put(1, "&1");
		newMap.put(2, "&2");
		newMap.put(3, "&3");
		newMap.put(4, "&4");
		newMap.put(5, "&5");
		newMap.put(6, "&6");
		newMap.put(7, "&7");
		newMap.put(8, "&8");
		newMap.put(9, "&9");
		newMap.put(10, "&0");
		newMap.put(11, "&a");
		newMap.put(12, "&b");
		newMap.put(13, "&c");
		newMap.put(14, "&d");
		newMap.put(15, "&e");
		ScoreboardTracker.put(newBoard, newMap);

	}

	public static void showSimpleBoard(Player targetPlayer, String scoreboardName){
		Scoreboard targetBoard = SimpleScoreboards.get(scoreboardName);
		targetPlayer.setScoreboard(targetBoard);
	}

	public static void setScoreboardTitle(String scoreboardName, String title){
		Scoreboard targetBoard = SimpleScoreboards.get(scoreboardName);
		targetBoard.getObjective("SimpleScoreboard").setDisplayName(title);
	}

    public static void deleteScoreboard(String scoreboardName){
        if (ScoreboardManagers.boardList.containsKey(scoreboardName)) {
            ScoreboardManagers.boardList.remove(scoreboardName);
        }
        if (SimpleScoreboards.containsKey(scoreboardName)) {
            SimpleScoreboards.remove(scoreboardName);
        }
    }

	public static void clearScore(String scoreboardName, Integer slot){
		Scoreboard targetBoard = SimpleScoreboards.get(scoreboardName);
		if (slot <= 15){
			if (slot > 0){
		    	HashMap<Integer, String> hashMap = ScoreboardTracker.get(targetBoard);
				String score2reset = hashMap.get(slot);
				//Bukkit.broadcastMessage("Score to Reset: " + score2reset);
				targetBoard.resetScores(score2reset);
                if (slot < 10) {
					targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&" + slot)).setScore(slot);
                    hashMap.put(slot, "&" + slot);
				}
				else{
					if (slot == 10){
						targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&0")).setScore(slot);
                        hashMap.put(slot, "&0");
					}
					if (slot == 11){
						targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&a")).setScore(slot);
                        hashMap.put(slot, "&a");
					}
					if (slot == 12){
						targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&b")).setScore(slot);
                        hashMap.put(slot, "&b");
					}
					if (slot == 13){
						targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&c")).setScore(slot);
                        hashMap.put(slot, "&c");
					}
					if (slot == 14){
						targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&d")).setScore(slot);
                        hashMap.put(slot, "&d");
					}
					if (slot == 15){
						targetBoard.getObjective("SimpleScoreboard").getScore(ChatColor.translateAlternateColorCodes('&', "&e")).setScore(slot);
                        hashMap.put(slot, "&e");
					}
				}
                ScoreboardTracker.put(targetBoard, hashMap);
			}
			else{
				Skript.error(Skript.SKRIPT_PREFIX + "SimpleScoreboard Score cannot below 0");
			}
		}
		else{
			Skript.error(Skript.SKRIPT_PREFIX + "SimpleScoreboard Score cannot above 15");
		}
	}

	public static String colorString(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static void setScore(String scoreboardName, Integer slot, String value){
        Scoreboard targetBoard;
        if (SimpleScoreboards.containsKey(scoreboardName)) {
            targetBoard = SimpleScoreboards.get(scoreboardName);
        }
        else{
            return;
        }
		if (slot <= 15){
			if (slot > 0){

                HashMap<Integer, String> hashMap;
                hashMap = ScoreboardTracker.get(targetBoard);
                if (!hashMap.get(slot).equals(value)){
                    String score2reset = hashMap.get(slot);
                    targetBoard.resetScores(score2reset);
                    hashMap.remove(slot);
                }
                targetBoard.getObjective("SimpleScoreboard").getScore(value).setScore(slot);
                hashMap.put(slot, value);
                ScoreboardTracker.put(targetBoard, hashMap);

			}
			else{
				Skript.error(Skript.SKRIPT_PREFIX + "SimpleScoreboard Score cannot below 0");
			}
		}
		else{
			Skript.error(Skript.SKRIPT_PREFIX + "SimpleScoreboard Score cannot above 15");
		}
	}
}

