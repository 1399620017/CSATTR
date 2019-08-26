package top.csattr.listener;

import lk.vexview.api.VexViewAPI;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import top.csattr.Core;
import top.csattr.player.PlayerAttr;
import top.csattr.vexview.hud.MainHud;

public class EntityRegainHealthListener implements Listener{

	@EventHandler
	public void _(EntityRegainHealthEvent e){
		Entity ee = e.getEntity();
		if(ee instanceof Player){
			Player eep = (Player) ee;
			PlayerAttr pa = PlayerAttr.getPlayer(eep);
			if(pa != null){
				int sz = pa.getAttrValue("生命回复");
				e.setAmount(e.getAmount() + sz);
			}
		}
	}
	@EventHandler
	public void __(PlayerExpChangeEvent e){
		if(e.getAmount() > 0){
			int exp = e.getAmount();
			PlayerAttr pa = PlayerAttr.getPlayer(e.getPlayer());
			int jc = 100 + pa.getAttrValue("经验加成");
			e.setAmount((int) (exp * (jc / 100D)));
		}
	}
	@EventHandler
	public void ___(InventoryCloseEvent e){
		HumanEntity eh = e.getPlayer();
		if(eh instanceof Player){
			Player ep = (Player) eh;
			PlayerAttr pa = PlayerAttr.getPlayer(ep);
			if(pa != null){
				pa.readAttr();
			}
		}
	}
	@EventHandler
	public void ___(PlayerJoinEvent e){
		
		final Player ep = e.getPlayer();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				PlayerAttr pa = PlayerAttr.getPlayer(ep);
				if(pa != null){
					pa.readAttr();
					VexViewAPI.sendHUD(ep, MainHud.menuHud, 0, 0.4);
				}
			}
		}.runTaskLater(Core.plugin, 1);
	}
	@EventHandler
	public void ___(PlayerItemHeldEvent e){
		final Player ep = e.getPlayer();
		ItemStack nStack = ep.getInventory().getItem(e.getNewSlot());
		ItemStack rStack = ep.getInventory().getItem(e.getPreviousSlot());
		boolean bn = false;
		boolean br = false;
		if(nStack != null){
			bn = nStack.hasItemMeta();
		}
		if(rStack != null){
			br = rStack.hasItemMeta();
		}
		if(!br&&!bn){
			return;
		}
		boolean rl = false;
		boolean nl = false;
		if(br){
			rl = rStack.getItemMeta().hasLore();
		}
		if(bn){
			nl = nStack.getItemMeta().hasLore();
		}
		if(!rl && !nl){
			return;
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				PlayerAttr pa = PlayerAttr.getPlayer(ep);
				if(pa != null){
					pa.readAttr();
				}
			}
		}.runTaskLater(Core.plugin, 1);
	}
	@EventHandler
	public void ___(PlayerMoveEvent e){
		
	}
	@EventHandler
	public void ___(PlayerQuitEvent e){
		final Player ep = e.getPlayer();
		PlayerAttr.remove(ep);
	}
	
}
