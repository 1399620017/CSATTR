package top.csattr.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import top.csattr.Core.Config;


public class PlayerSwitch extends Config{

	public static Map<String, PlayerSwitch> map = new HashMap<>();
	
	public static PlayerSwitch getPlayerSwitch(Player player){
		if(player != null && player.isOnline()){
			String name = player.getName();
			if(!map.containsKey(name)){
				map.put(name, new PlayerSwitch(name));
			}
			return map.get(name);
		}
		return null;
		
	}
	private PlayerSwitch(String name) {
		super("switch", name);
	}
	public PlayerSwitch(Player player){
		this(player.getName());
	}

	public void open(){
		config.set("switch", true);
	}
	public void close(){
		config.set("switch", false);
	}
	public boolean getStu(){
		return config.getBoolean("switch", true);
	}
	@Override
	public void defaultConfig() {
		config.set("switch", true);
	}

	@Override
	public void saveConfig() {
		
	}
}
