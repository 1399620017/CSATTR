package top.csattr.damage;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import top.csattr.Core.Msg;
import top.csattr.player.EntityFire;
import top.csattr.player.PlayerAttr;
import top.csattr.player.PlayerSwitch;

public class Damage {

	public static void pvp(final Player ael, Player del, double damage,
			boolean pro, EntityDamageByEntityEvent e) {
		PlayerAttr paa = PlayerAttr.getPlayer(ael);
		PlayerAttr pad = PlayerAttr.getPlayer(del);

		// paa为攻击者属性实例 pad为被攻击者属性实例
		/**
		 * 闪避处理开始
		 * */
		int sbl = pad.getAttrValue("闪避几率") - paa.getAttrValue("命中几率");
		if ((int) (Math.random() * 100) < sbl) {
			e.setCancelled(true);
			sendMessage(ael, "对方闪避了你的攻击！");
			sendMessage(del, "你闪避了对方的攻击！");
			return;
		}
		/**
		 * 闪避处理结束
		 * */

		/**
		 * 攻击者属性计算开始
		 * */
		damage += paa.getAttrValue("攻击力");
		/**
		 * 攻击者属性计算结束
		 * */

		/**
		 * 生命偷取计算开始
		 * */
		int xxl = paa.getAttrValue("生命偷取");
		int xxsz = paa.getAttrValue("攻击回血");
		if ((int) (Math.random() * 100) < xxl) {
			double maxHeal = ael.getMaxHealth();
			double heal = ael.getHealth();
			ael.setHealth(Math.min(maxHeal, heal + xxsz));
			sendMessage(ael, "你触发了生命偷取, 回复生命值:" + xxsz);
		}
		/**
		 * 生命偷取计算结束
		 * */

		/**
		 * 点燃计算开始
		 * */
		int drl = paa.getAttrValue("点燃几率");
		int drsh = paa.getAttrValue("点燃伤害");
		if ((int) (Math.random() * 100) < drl) {
			EntityFire.addEntityFire(del, drsh, 3);
			sendMessage(ael, "你触发了点燃特效,每秒伤害:" + drsh + ",持续3秒!");
			sendMessage(del, "你被点燃了, 每秒伤害:" + drsh + ",持续3秒!");
		}
		/**
		 * 点燃计算结束
		 * */

		/**
		 * 闪电计算开始
		 * */
		int sdl = paa.getAttrValue("闪电几率");
		int sdsh = paa.getAttrValue("闪电伤害");
		if ((int) (Math.random() * 100) < sdl) {
			damage += sdsh;
			del.getWorld().strikeLightningEffect(del.getLocation());
			sendMessage(ael, "你触发了闪电特效,造成伤害:" + sdsh);
			sendMessage(del, "你被闪电击中了, 受到伤害:" + sdsh);
		}
		/**
		 * 闪电计算结束
		 * */

		/**
		 * 爆炸计算开始
		 * */
		int bzl = paa.getAttrValue("爆炸几率");
		int bzsh = paa.getAttrValue("爆炸伤害");
		if ((int) (Math.random() * 100) < bzl) {
			Location loc = del.getLocation();
			del.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(),
					bzsh, false, false);
			sendMessage(ael, "你触发了爆炸特效,造成伤害:" + bzsh);
			sendMessage(del, "你被闪电击中了, 受到伤害:" + bzsh);
		}
		/**
		 * 爆炸计算结束
		 * */

		/**
		 * 凋零计算开始
		 * */
		int dll = paa.getAttrValue("凋零几率");
		int dlsh = paa.getAttrValue("凋零伤害");
		if ((int) (Math.random() * 100) < dll) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60,
					dlsh), false);
			sendMessage(ael, "你触发了凋零特效,伤害等级:" + dlsh);
			sendMessage(del, "你中了凋零特效,伤害等级:" + dlsh);
		}
		/**
		 * 凋零计算结束
		 * */
		/**
		 * 中毒计算开始
		 * */
		int zdl = paa.getAttrValue("施毒几率");
		int zdsh = paa.getAttrValue("中毒伤害");
		if ((int) (Math.random() * 100) < zdl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60,
					zdsh), false);
			sendMessage(ael, "你触发了中毒特效,伤害等级:" + zdsh);
			sendMessage(del, "你中了中毒特效,伤害等级:" + zdsh);
		}
		/**
		 * 中毒计算结束
		 * */
		/**
		 * 减速计算开始
		 * */
		int jsl = paa.getAttrValue("减速几率");
		if ((int) (Math.random() * 100) < jsl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3),
					false);
			sendMessage(ael, "你触发了减速特效！");
			sendMessage(del, "你中了减速特效！");
		}
		/**
		 * 减速计算结束
		 * */

		int def = 0;
		damage += paa.getAttrValue("PVP攻击力");
		def += pad.getAttrValue("PVP防御力");
		def += pad.getAttrValue("防御力");
		/**
		 * 暴击攻击计算开始
		 * */
		int bjl = paa.getAttrValue("暴击几率");
		int bjsh = paa.getAttrValue("暴击伤害");
		if ((int) (Math.random() * 100) < bjl) {
			damage = damage * ((150 + bjsh) / 100D);
			sendMessage(ael, "你触发了暴击攻击！");
			sendMessage(del, "你被暴击了！");
		}
		/**
		 * 暴击攻击计算结束
		 * */
		/**
		 * 爆头攻击计算开始
		 * */
		if (pro){
			if(ael.getLocation().getY() > del.getLocation().getY()
					+ del.getEyeHeight()) {
				damage = damage * (150 / 100D);
				sendMessage(ael, "你触发了爆头攻击！");
				sendMessage(del, "你被爆头了！");
			}
		}
		/**
		 * 爆头攻击计算结束
		 * */

		/**
		 * 格挡计算开始
		 * */
		int gdl = pad.getAttrValue("格挡几率");
		int gdsh = pad.getAttrValue("格挡伤害");
		if ((int) (Math.random() * 100) < gdl) {
			sendMessage(ael, "对方格挡了你的伤害：" + gdsh);
			sendMessage(del, "你格挡了对方的伤害：" + gdsh);
			double heal = del.getHealth();
			del.setHealth(Math.max(0.1D, heal - 1D / gdsh));
			e.setCancelled(true);
			return;
		}
		/**
		 * 格挡计算结束
		 * */
		if(pad.hasWdzb()){
			del.setHealth(Math.max(0, Math.min(20.0, del.getHealth() - 5)));
			e.setDamage(1);
		}else{
			damage = damage - def;
			e.setDamage(Math.max(0.1D, damage));
		}
	}

	/**
	 * 发送战斗信息提示
	 * */
	private static void sendMessage(Player le, String message) {
		if (PlayerSwitch.getPlayerSwitch(le).getStu()) {
			Msg.sendMsgTrue(le, message);
		}
	}

	public static void pve(Player ael, LivingEntity del, double damage, boolean pro,
			EntityDamageByEntityEvent e) {
		PlayerAttr paa = PlayerAttr.getPlayer(ael);

		// paa为攻击者属性实例 pad为被攻击者属性实例

		/**
		 * 攻击者属性计算开始
		 * */
		damage += paa.getAttrValue("攻击力");
		/**
		 * 攻击者属性计算结束
		 * */

		/**
		 * 生命偷取计算开始
		 * */
		int xxl = paa.getAttrValue("生命偷取");
		int xxsz = paa.getAttrValue("攻击回血");
		if ((int) (Math.random() * 100) < xxl) {
			double maxHeal = ael.getMaxHealth();
			double heal = ael.getHealth();
			ael.setHealth(Math.min(maxHeal, heal + xxsz));
			sendMessage(ael, "你触发了生命偷取, 回复生命值:" + xxsz);
		}
		/**
		 * 生命偷取计算结束
		 * */

		/**
		 * 点燃计算开始
		 * */
		int drl = paa.getAttrValue("点燃几率");
		int drsh = paa.getAttrValue("点燃伤害");
		if ((int) (Math.random() * 100) < drl) {
			EntityFire.addEntityFire(del, drsh, 3);
			sendMessage(ael, "你触发了点燃特效,每秒伤害:" + drsh + ",持续3秒!");
		}
		/**
		 * 点燃计算结束
		 * */

		/**
		 * 闪电计算开始
		 * */
		int sdl = paa.getAttrValue("闪电几率");
		int sdsh = paa.getAttrValue("闪电伤害");
		if ((int) (Math.random() * 100) < sdl) {
			damage += sdsh;
			del.getWorld().strikeLightningEffect(del.getLocation());
			sendMessage(ael, "你触发了闪电特效,造成伤害:" + sdsh);
		}
		/**
		 * 闪电计算结束
		 * */

		/**
		 * 爆炸计算开始
		 * */
		int bzl = paa.getAttrValue("爆炸几率");
		int bzsh = paa.getAttrValue("爆炸伤害");
		if ((int) (Math.random() * 100) < bzl) {
			Location loc = del.getLocation();
			del.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(),
					bzsh, false, false);
			sendMessage(ael, "你触发了爆炸特效,造成伤害:" + bzsh);
		}
		/**
		 * 爆炸计算结束
		 * */

		/**
		 * 凋零计算开始
		 * */
		int dll = paa.getAttrValue("凋零几率");
		int dlsh = paa.getAttrValue("凋零伤害");
		if ((int) (Math.random() * 100) < dll) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60,
					dlsh), false);
			sendMessage(ael, "你触发了凋零特效,伤害等级:" + dlsh);
		}
		/**
		 * 凋零计算结束
		 * */
		/**
		 * 中毒计算开始
		 * */
		int zdl = paa.getAttrValue("施毒几率");
		int zdsh = paa.getAttrValue("中毒伤害");
		if ((int) (Math.random() * 100) < zdl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60,
					zdsh), false);
			sendMessage(ael, "你触发了中毒特效,伤害等级:" + zdsh);
		}
		/**
		 * 中毒计算结束
		 * */
		/**
		 * 减速计算开始
		 * */
		int jsl = paa.getAttrValue("减速几率");
		if ((int) (Math.random() * 100) < jsl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3),
					false);
			sendMessage(ael, "你触发了减速特效！");
		}
		/**
		 * 减速计算结束
		 * */

		damage += paa.getAttrValue("PVE攻击力");

		/**
		 * 暴击攻击计算开始
		 * */
		int bjl = paa.getAttrValue("暴击几率");
		int bjsh = paa.getAttrValue("暴击伤害");
		if ((int) (Math.random() * 100) < bjl) {
			damage = damage * ((150 + bjsh) / 100D);
			sendMessage(ael, "你触发了暴击攻击！");
		}
		/**
		 * 暴击攻击计算结束
		 * */
		/**
		 * 爆头攻击计算开始
		 * */
		if (pro
				&& (ael.getLocation().getY() > del.getLocation().getY()
						+ del.getEyeHeight())) {
			damage = damage * (150 / 100D);
			sendMessage(ael, "你触发了爆头攻击！");
		}
		/**
		 * 爆头攻击计算结束
		 * */
		e.setDamage(Math.max(1D, damage));
	}

	public static void evp(final LivingEntity ael, Player del, double damage,
			EntityDamageByEntityEvent e) {
		PlayerAttr pad = PlayerAttr.getPlayer(del);

		// paa为攻击者属性实例 pad为被攻击者属性实例
		/**
		 * 闪避处理开始
		 * */
		int sbl = pad.getAttrValue("闪避几率");
		if ((int) (Math.random() * 100) < sbl) {
			e.setCancelled(true);
			sendMessage(del, "你闪避了对方的攻击！");
			return;
		}
		/**
		 * 闪避处理结束
		 * */
		
		int def = 0;
		def += pad.getAttrValue("PVP防御力");
		def += pad.getAttrValue("防御力");

		/**
		 * 格挡计算开始
		 * */
		int gdl = pad.getAttrValue("格挡几率");
		int gdsh = pad.getAttrValue("格挡伤害");
		if ((int) (Math.random() * 100) < gdl) {
			sendMessage(del, "你格挡了对方的伤害：" + gdsh);
			double heal = del.getHealth();
			del.setHealth(Math.max(0.3D, heal - 1D / gdsh));
			e.setCancelled(true);
			return;
		}
		/**
		 * 格挡计算结束
		 * */
		
		if(pad.hasWdzb()){
			del.setHealth(Math.max(0, Math.min(20.0, del.getHealth() - 5)));
			e.setDamage(1);
		}else{
			damage = damage - def;
			e.setDamage(Math.max(0.1D, damage));
		}
	}
}
