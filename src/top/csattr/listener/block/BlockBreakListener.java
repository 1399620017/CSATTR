package top.csattr.listener.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import top.csattr.world.WorldType;

public class BlockBreakListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void BreakBlock(BlockBreakEvent e){
		// ·½¿éÆÆ»µ
		Player ep = e.getPlayer();
		if(!ep.isOp()&&WorldType.inPVPWorld(ep)){
			e.setCancelled(true);
		}
	}
}
