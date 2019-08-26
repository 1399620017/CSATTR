package top.csattr.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import top.csattr.player.EntityFire;

public class FireDamageListener implements Listener{
	
	@EventHandler()
	public void _(EntityDamageEvent e){
		if(e.getCause() == DamageCause.FIRE_TICK){
			Entity ee = e.getEntity();
			if(ee instanceof LivingEntity){
				LivingEntity ele = (LivingEntity) ee;
				if(EntityFire.isFireTick(ele)){
					e.setDamage(EntityFire.getFireDamage(ele));
				}
			}
		}
	}
}
