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

		// paaΪ����������ʵ�� padΪ������������ʵ��
		/**
		 * ���ܴ���ʼ
		 * */
		int sbl = pad.getAttrValue("���ܼ���") - paa.getAttrValue("���м���");
		if ((int) (Math.random() * 100) < sbl) {
			e.setCancelled(true);
			sendMessage(ael, "�Է���������Ĺ�����");
			sendMessage(del, "�������˶Է��Ĺ�����");
			return;
		}
		/**
		 * ���ܴ������
		 * */

		/**
		 * ���������Լ��㿪ʼ
		 * */
		damage += paa.getAttrValue("������");
		/**
		 * ���������Լ������
		 * */

		/**
		 * ����͵ȡ���㿪ʼ
		 * */
		int xxl = paa.getAttrValue("����͵ȡ");
		int xxsz = paa.getAttrValue("������Ѫ");
		if ((int) (Math.random() * 100) < xxl) {
			double maxHeal = ael.getMaxHealth();
			double heal = ael.getHealth();
			ael.setHealth(Math.min(maxHeal, heal + xxsz));
			sendMessage(ael, "�㴥��������͵ȡ, �ظ�����ֵ:" + xxsz);
		}
		/**
		 * ����͵ȡ�������
		 * */

		/**
		 * ��ȼ���㿪ʼ
		 * */
		int drl = paa.getAttrValue("��ȼ����");
		int drsh = paa.getAttrValue("��ȼ�˺�");
		if ((int) (Math.random() * 100) < drl) {
			EntityFire.addEntityFire(del, drsh, 3);
			sendMessage(ael, "�㴥���˵�ȼ��Ч,ÿ���˺�:" + drsh + ",����3��!");
			sendMessage(del, "�㱻��ȼ��, ÿ���˺�:" + drsh + ",����3��!");
		}
		/**
		 * ��ȼ�������
		 * */

		/**
		 * ������㿪ʼ
		 * */
		int sdl = paa.getAttrValue("���缸��");
		int sdsh = paa.getAttrValue("�����˺�");
		if ((int) (Math.random() * 100) < sdl) {
			damage += sdsh;
			del.getWorld().strikeLightningEffect(del.getLocation());
			sendMessage(ael, "�㴥����������Ч,����˺�:" + sdsh);
			sendMessage(del, "�㱻���������, �ܵ��˺�:" + sdsh);
		}
		/**
		 * ����������
		 * */

		/**
		 * ��ը���㿪ʼ
		 * */
		int bzl = paa.getAttrValue("��ը����");
		int bzsh = paa.getAttrValue("��ը�˺�");
		if ((int) (Math.random() * 100) < bzl) {
			Location loc = del.getLocation();
			del.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(),
					bzsh, false, false);
			sendMessage(ael, "�㴥���˱�ը��Ч,����˺�:" + bzsh);
			sendMessage(del, "�㱻���������, �ܵ��˺�:" + bzsh);
		}
		/**
		 * ��ը�������
		 * */

		/**
		 * ������㿪ʼ
		 * */
		int dll = paa.getAttrValue("���㼸��");
		int dlsh = paa.getAttrValue("�����˺�");
		if ((int) (Math.random() * 100) < dll) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60,
					dlsh), false);
			sendMessage(ael, "�㴥���˵�����Ч,�˺��ȼ�:" + dlsh);
			sendMessage(del, "�����˵�����Ч,�˺��ȼ�:" + dlsh);
		}
		/**
		 * ����������
		 * */
		/**
		 * �ж����㿪ʼ
		 * */
		int zdl = paa.getAttrValue("ʩ������");
		int zdsh = paa.getAttrValue("�ж��˺�");
		if ((int) (Math.random() * 100) < zdl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60,
					zdsh), false);
			sendMessage(ael, "�㴥�����ж���Ч,�˺��ȼ�:" + zdsh);
			sendMessage(del, "�������ж���Ч,�˺��ȼ�:" + zdsh);
		}
		/**
		 * �ж��������
		 * */
		/**
		 * ���ټ��㿪ʼ
		 * */
		int jsl = paa.getAttrValue("���ټ���");
		if ((int) (Math.random() * 100) < jsl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3),
					false);
			sendMessage(ael, "�㴥���˼�����Ч��");
			sendMessage(del, "�����˼�����Ч��");
		}
		/**
		 * ���ټ������
		 * */

		int def = 0;
		damage += paa.getAttrValue("PVP������");
		def += pad.getAttrValue("PVP������");
		def += pad.getAttrValue("������");
		/**
		 * �����������㿪ʼ
		 * */
		int bjl = paa.getAttrValue("��������");
		int bjsh = paa.getAttrValue("�����˺�");
		if ((int) (Math.random() * 100) < bjl) {
			damage = damage * ((150 + bjsh) / 100D);
			sendMessage(ael, "�㴥���˱���������");
			sendMessage(del, "�㱻�����ˣ�");
		}
		/**
		 * ���������������
		 * */
		/**
		 * ��ͷ�������㿪ʼ
		 * */
		if (pro){
			if(ael.getLocation().getY() > del.getLocation().getY()
					+ del.getEyeHeight()) {
				damage = damage * (150 / 100D);
				sendMessage(ael, "�㴥���˱�ͷ������");
				sendMessage(del, "�㱻��ͷ�ˣ�");
			}
		}
		/**
		 * ��ͷ�����������
		 * */

		/**
		 * �񵲼��㿪ʼ
		 * */
		int gdl = pad.getAttrValue("�񵲼���");
		int gdsh = pad.getAttrValue("���˺�");
		if ((int) (Math.random() * 100) < gdl) {
			sendMessage(ael, "�Է���������˺���" + gdsh);
			sendMessage(del, "����˶Է����˺���" + gdsh);
			double heal = del.getHealth();
			del.setHealth(Math.max(0.1D, heal - 1D / gdsh));
			e.setCancelled(true);
			return;
		}
		/**
		 * �񵲼������
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
	 * ����ս����Ϣ��ʾ
	 * */
	private static void sendMessage(Player le, String message) {
		if (PlayerSwitch.getPlayerSwitch(le).getStu()) {
			Msg.sendMsgTrue(le, message);
		}
	}

	public static void pve(Player ael, LivingEntity del, double damage, boolean pro,
			EntityDamageByEntityEvent e) {
		PlayerAttr paa = PlayerAttr.getPlayer(ael);

		// paaΪ����������ʵ�� padΪ������������ʵ��

		/**
		 * ���������Լ��㿪ʼ
		 * */
		damage += paa.getAttrValue("������");
		/**
		 * ���������Լ������
		 * */

		/**
		 * ����͵ȡ���㿪ʼ
		 * */
		int xxl = paa.getAttrValue("����͵ȡ");
		int xxsz = paa.getAttrValue("������Ѫ");
		if ((int) (Math.random() * 100) < xxl) {
			double maxHeal = ael.getMaxHealth();
			double heal = ael.getHealth();
			ael.setHealth(Math.min(maxHeal, heal + xxsz));
			sendMessage(ael, "�㴥��������͵ȡ, �ظ�����ֵ:" + xxsz);
		}
		/**
		 * ����͵ȡ�������
		 * */

		/**
		 * ��ȼ���㿪ʼ
		 * */
		int drl = paa.getAttrValue("��ȼ����");
		int drsh = paa.getAttrValue("��ȼ�˺�");
		if ((int) (Math.random() * 100) < drl) {
			EntityFire.addEntityFire(del, drsh, 3);
			sendMessage(ael, "�㴥���˵�ȼ��Ч,ÿ���˺�:" + drsh + ",����3��!");
		}
		/**
		 * ��ȼ�������
		 * */

		/**
		 * ������㿪ʼ
		 * */
		int sdl = paa.getAttrValue("���缸��");
		int sdsh = paa.getAttrValue("�����˺�");
		if ((int) (Math.random() * 100) < sdl) {
			damage += sdsh;
			del.getWorld().strikeLightningEffect(del.getLocation());
			sendMessage(ael, "�㴥����������Ч,����˺�:" + sdsh);
		}
		/**
		 * ����������
		 * */

		/**
		 * ��ը���㿪ʼ
		 * */
		int bzl = paa.getAttrValue("��ը����");
		int bzsh = paa.getAttrValue("��ը�˺�");
		if ((int) (Math.random() * 100) < bzl) {
			Location loc = del.getLocation();
			del.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(),
					bzsh, false, false);
			sendMessage(ael, "�㴥���˱�ը��Ч,����˺�:" + bzsh);
		}
		/**
		 * ��ը�������
		 * */

		/**
		 * ������㿪ʼ
		 * */
		int dll = paa.getAttrValue("���㼸��");
		int dlsh = paa.getAttrValue("�����˺�");
		if ((int) (Math.random() * 100) < dll) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60,
					dlsh), false);
			sendMessage(ael, "�㴥���˵�����Ч,�˺��ȼ�:" + dlsh);
		}
		/**
		 * ����������
		 * */
		/**
		 * �ж����㿪ʼ
		 * */
		int zdl = paa.getAttrValue("ʩ������");
		int zdsh = paa.getAttrValue("�ж��˺�");
		if ((int) (Math.random() * 100) < zdl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60,
					zdsh), false);
			sendMessage(ael, "�㴥�����ж���Ч,�˺��ȼ�:" + zdsh);
		}
		/**
		 * �ж��������
		 * */
		/**
		 * ���ټ��㿪ʼ
		 * */
		int jsl = paa.getAttrValue("���ټ���");
		if ((int) (Math.random() * 100) < jsl) {
			del.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3),
					false);
			sendMessage(ael, "�㴥���˼�����Ч��");
		}
		/**
		 * ���ټ������
		 * */

		damage += paa.getAttrValue("PVE������");

		/**
		 * �����������㿪ʼ
		 * */
		int bjl = paa.getAttrValue("��������");
		int bjsh = paa.getAttrValue("�����˺�");
		if ((int) (Math.random() * 100) < bjl) {
			damage = damage * ((150 + bjsh) / 100D);
			sendMessage(ael, "�㴥���˱���������");
		}
		/**
		 * ���������������
		 * */
		/**
		 * ��ͷ�������㿪ʼ
		 * */
		if (pro
				&& (ael.getLocation().getY() > del.getLocation().getY()
						+ del.getEyeHeight())) {
			damage = damage * (150 / 100D);
			sendMessage(ael, "�㴥���˱�ͷ������");
		}
		/**
		 * ��ͷ�����������
		 * */
		e.setDamage(Math.max(1D, damage));
	}

	public static void evp(final LivingEntity ael, Player del, double damage,
			EntityDamageByEntityEvent e) {
		PlayerAttr pad = PlayerAttr.getPlayer(del);

		// paaΪ����������ʵ�� padΪ������������ʵ��
		/**
		 * ���ܴ���ʼ
		 * */
		int sbl = pad.getAttrValue("���ܼ���");
		if ((int) (Math.random() * 100) < sbl) {
			e.setCancelled(true);
			sendMessage(del, "�������˶Է��Ĺ�����");
			return;
		}
		/**
		 * ���ܴ������
		 * */
		
		int def = 0;
		def += pad.getAttrValue("PVP������");
		def += pad.getAttrValue("������");

		/**
		 * �񵲼��㿪ʼ
		 * */
		int gdl = pad.getAttrValue("�񵲼���");
		int gdsh = pad.getAttrValue("���˺�");
		if ((int) (Math.random() * 100) < gdl) {
			sendMessage(del, "����˶Է����˺���" + gdsh);
			double heal = del.getHealth();
			del.setHealth(Math.max(0.3D, heal - 1D / gdsh));
			e.setCancelled(true);
			return;
		}
		/**
		 * �񵲼������
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
