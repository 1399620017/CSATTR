package top.csattr.listener.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import top.csattr.world.WorldType;


public class BlockPlaceListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void bc(BlockPlaceEvent e){
		// ∑ΩøÈ∑≈÷√
		if(e.getPlayer() != null){
			Player ep = e.getPlayer();
			if(!ep.isOp() && WorldType.inPVPWorld(ep)){
				e.setBuild(false);
				e.setCancelled(true);
			}
		}
	}
}
