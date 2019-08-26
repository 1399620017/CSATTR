package top.csattr.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;
import org.bukkit.projectiles.ProjectileSource;
import top.csattr.damage.Damage;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void _(EntityDamageByEntityEvent e){
		Entity ae = e.getDamager();
		Entity de = e.getEntity();
		final LivingEntity ael;
		LivingEntity del;
		boolean pvp = false;
		boolean pro = false;
		if(ae instanceof Projectile){
			Projectile aepj = (Projectile) ae;
			ProjectileSource ale = aepj.getShooter();
			if(ale instanceof LivingEntity){
				ael = (LivingEntity) ale;
				pro = true;
			}else{
				return;
			}
		}else if(ae instanceof LivingEntity){
			ael = (LivingEntity) ae;
		}else {
			return;
		}
		if(de instanceof LivingEntity){
			del = (LivingEntity) de;
		}else{
			return;
		}
		
		if(ael instanceof Player && del instanceof Player){
			pvp = true;
		}
		
		// 伤害过程处理
		// ael 为攻击者 del 为被攻击者
		// 取出攻击者装备
		double damage = (ael instanceof Player)?e.getDamage() * 0.5D:e.getDamage();
		if(pvp){
			Damage.pvp((Player)ael, (Player)del, damage, pro, e);
		}else{
			if(ael instanceof Player){
				Damage.pve((Player)ael, del, damage, pro, e);
			}else if(del instanceof Player){
				Damage.evp(ael, (Player)del, damage, e);
			}
		}
	}

}
