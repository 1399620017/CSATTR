package top.csattr.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

import top.csattr.world.WorldType;


public class BlockFadeListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void BlockFade(BlockFadeEvent e){
		// ������Ȼ�仯
		if(WorldType.inPVPWorld(e.getBlock().getLocation())){
			e.setCancelled(true);
		}
	}
}
