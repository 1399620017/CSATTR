package top.csattr.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

import top.csattr.world.WorldType;


public class BlockIgniteListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void BlockFade(BlockIgniteEvent e){
		// ���鱻��ȼ
		if(WorldType.inPVPWorld(e.getBlock().getLocation())){
			e.setCancelled(true);
		}
	}
}
