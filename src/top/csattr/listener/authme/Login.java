package top.csattr.listener.authme;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import top.csattr.Core;
import top.csattr.player.PlayerAttr;

import fr.xephi.authme.events.LoginEvent;

public class Login implements Listener{

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void _(LoginEvent e){
		final Player ep = e.getPlayer();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				System.out.println("ÊôÐÔ¸üÐÂ");
				PlayerAttr pa = PlayerAttr.getPlayer(ep);
				if(pa != null){
					pa.readAttr();
				}
			}
		}.runTaskLater(Core.plugin, 1);
	}
}
