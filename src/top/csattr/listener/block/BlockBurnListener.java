package top.csattr.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

import top.csattr.world.WorldType;


public class BlockBurnListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void BlockFade(BlockBurnEvent e){
		// ·½¿é±»»ðÉÕµô
		if(WorldType.inPVPWorld(e.getBlock().getLocation())){
			e.setCancelled(true);
		}
	}
}
