package top.csattr.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import top.csattr.Main;

public class EntityFire {
	private static Map<LivingEntity, Integer> map = new HashMap<>();
	public static void addEntityFire(final LivingEntity e, int damage, int time){
		if(!e.isDead()){
			map.put(e, damage);
			e.setFireTicks(time * 20);
			new BukkitRunnable() {
				
				@Override
				public void run() {
					map.remove(e);
				}
			}.runTaskLaterAsynchronously(Main.plugin, time * 20);
		}
	}
	public static int getFireDamage(LivingEntity entity) {
		return map.getOrDefault(entity, 1);
	}
	public static boolean isFireTick(LivingEntity entity) {
		return map.containsKey(entity);
	}
}
