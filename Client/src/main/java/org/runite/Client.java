package org.runite;

import org.runite.jagex.GameShell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Handles the launching of our Game Client.
 * @author Keldagrim Development Team
 *
 */

/*

NOTICE: THIS IS THE LIVESERVER CLIENT. For development purposes, use GameLaunch.java instead!!!

 */
public class Client {

	public static final String CONF_FILE="client.conf";

	public static String PUBLIC_IP_ADDRESS;

	/**
	 * The game settings.
	 */
	public static GameSetting SETTINGS = new GameSetting("2009Scape", PUBLIC_IP_ADDRESS, 1, "live", false);
	
	/**
	 * The main method.
	 r @param args the arguments casted on runtime.
     r_game

	 */
	public static void main(String[]args) {
		try {
			InputStream inp = ClassLoader.getSystemClassLoader().getResourceAsStream("client.conf");
			BufferedReader rd = new BufferedReader(new InputStreamReader(inp));
			String s = rd.readLine();
			if(s.contains("target_ip_addr:")){
				PUBLIC_IP_ADDRESS = s.replace("target_ip_addr:","");
			}
		} catch (Exception e){
			System.out.println("Can't find config file " + CONF_FILE + " defaulting to IP 127.0.0.1");
			PUBLIC_IP_ADDRESS = "127.0.0.1";
		}
		System.out.println("Running liveserver client");
		Configurations.LOCAL_SERVER = false;
		Configurations.LOCAL_MS = false;
		Configurations.MS_IP = Configurations.LOCAL_MS ? "127.0.0.1" : PUBLIC_IP_ADDRESS; //Needs to be done because of order it's otherwise set

		for (int i = 0; i < args.length; i++) {
			String[] cmd = args[i].split("=");
			switch (cmd[0]) {
			case "ip":
				SETTINGS.setIp(cmd[1]);
				break;
			case "world":
				SETTINGS.setWorld(Integer.parseInt(cmd[1]));
				break;
			}
		}
		/**
		 * Launches the client
		 */
		GameShell.launchDesktop();
	}
	
}
