package top.csattr.listener.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import top.csattr.bug.ExpBug;
import top.csattr.world.WorldType;

public class Death implements Listener {
	@SuppressWarnings("deprecation")
	private static ItemStack item = new ItemStack(26973);
	@EventHandler(priority = EventPriority.LOWEST)
	public void drop(EntityDeathEvent e) {
		LivingEntity ede = e.getEntity(); // 死亡的实体
		if(!(ede instanceof Player)){
			return;
		}
		Player edp = (Player) ede;
		ExpBug.setExp(edp.getName(), edp.getTotalExperience());
		if(WorldType.inPVPWorld(edp)){
			// rpg世界死亡处理
			Location loc = edp.getLocation().clone();
			loc.getWorld().dropItem(loc, item.clone());
		}else{
			// 修仙世界死亡处理
			
		}
		
	}
}
