package top.csattr.listener;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener{

	@EventHandler
	public void _(EntityExplodeEvent e){
		if(e.getEntityType() == EntityType.PRIMED_TNT){
			e.setCancelled(true);
			Location loc = e.getLocation();
			loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 
					3, false, false);
		}
	}
}
