package top.csattr.world;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WorldType{
	private static Set<String> set = new HashSet<>(); // PVP ¿ΩÁ
	static{
		set.add("PVP_SMH");
	}
	public static boolean inPVPWorld(Player p){
		return set.contains(p.getWorld().getName());
	}
	public static boolean inPVPWorld(Entity e){
		return set.contains(e.getWorld().getName());
	}
	public static boolean inPVPWorld(World world){
		return set.contains(world.getName());
	}
	public static boolean inPVPWorld(Location loc) {
		return set.contains(loc.getWorld().getName());
	}
	public static boolean inPVPWorld(String worldName) {
		return set.contains(worldName);
	}
}
