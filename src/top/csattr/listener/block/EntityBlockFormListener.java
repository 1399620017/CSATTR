package top.csattr.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;

import top.csattr.world.WorldType;

public class EntityBlockFormListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void BlockFade(EntityBlockFormEvent e){
		// 方块搭建成实体
		if(WorldType.inPVPWorld(e.getBlock().getLocation())){
			e.setCancelled(true);
		}
	}
}
