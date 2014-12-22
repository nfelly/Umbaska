/*
 * ImgInChat.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.nfell2009.umbaska.API;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import uk.nfell2009.umbaska.ImageManager.ImageChar;
import uk.nfell2009.umbaska.ImageManager.ImageMessage;

public class ImgInChat extends JavaPlugin implements Listener {
	
	public static void ShowImg(Player player, String path, String line1, String line2, String line3) {
  		BufferedImage imageToSend = null;
		try {
			imageToSend = ImageIO.read(new File(path));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
  		new ImageMessage(imageToSend, 8, ImageChar.MEDIUM_SHADE.getChar()).appendText("", "", "", line1, line2, line3).sendToPlayer(player);
    }
	
	public static void ShowImgFromURL(Player player, String path, String line1, String line2, String line3) {
  		BufferedImage imageToSend = null;
		try {
			imageToSend = ImageIO.read(new URL(path));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
  		new ImageMessage(imageToSend, 8, ImageChar.MEDIUM_SHADE.getChar()).appendText("", "", "", line1, line2, line3).sendToPlayer(player);
    }
}
