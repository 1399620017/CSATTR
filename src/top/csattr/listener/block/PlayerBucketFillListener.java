package top.csattr.listener.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

import top.csattr.world.WorldType;


public class PlayerBucketFillListener implements Listener{
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void Fill(PlayerBucketFillEvent e){
		// Í°Ìî×°ÊÂ¼þ
		Player ep = e.getPlayer();
		if(!ep.isOp()&&WorldType.inPVPWorld(ep)){
			e.setCancelled(true);
		}
	}
}
