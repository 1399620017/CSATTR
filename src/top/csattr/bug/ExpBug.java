package top.csattr.bug;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ExpBug {
	private static Map<String, Integer> map = new HashMap<>();
	public static void setExp(String name, int exp){
		System.out.println("��ȡ���" + name + "����:" + exp);
		map.put(name, exp);
	}
	public static void getExp(String name){
		Player ep = Bukkit.getPlayer(name);
		if(ep != null && ep.isOnline()){
			int exp = map.getOrDefault(name, 0);
			System.out.println("�ָ����" + ep.getName() + "����:" + exp);
			if(ep.getTotalExperience() != exp){
				ep.setLevel(0);
				ep.setExp(0F);
				ep.giveExp(exp);
			}
		}
	}
}
