package top.csattr.listener.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import top.csattr.Core;
import top.csattr.bug.ExpBug;

public class PlayerSpawn implements Listener{
	@EventHandler
	public void _(PlayerRespawnEvent e){
		final Player ep = e.getPlayer();
		final String name = ep.getName();
		new BukkitRunnable() {
			@Override
			public void run() {
				ExpBug.getExp(name);
			}
		}.runTaskLater(Core.plugin, 1);
	}
}
