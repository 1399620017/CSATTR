package top.csattr.player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerAttr {
	
	private static final Map<String, String> map = new HashMap<>();
	private static final Map<String, String> map2 = new HashMap<>();
	private static final Set<Integer> wdSet = new HashSet<Integer>();
	private static String FGF;
	static {
		wdSet.add(4666);
		wdSet.add(4705);
		wdSet.add(4683);
		wdSet.add(4675);
	}
	public static Map<String, String> getMap(){
		return map;
	}
	public static Map<String, String> getMap2(){
		return map2;
	}
	public static Set<Entry<String, PlayerAttr>> getMap1() {
		return map1.entrySet();
	}
	public static void getAttrName(FileConfiguration config){
		for(String key : config.getKeys(false)){
			map.put(config.getString(key), key);
			map2.put(key, config.getString(key));
		}
		setFGF(config.getString("�ָ�����"));
	}
	/**
	 * ��ȡ����ԭ��
	 * */
	public static String getRawName(String name){
		return map.get(name);
	}
	/**
	 * ��ȡ�����Զ�����
	 * */
	public static String getCustomName(String name){
		return map2.get(name);
	}
	
	private static final Map<String, PlayerAttr> map1 = new HashMap<>();
	/**
	 * ��ȡ���
	 * */
	public static PlayerAttr getPlayer(Player player){
		if(player != null && player.isOnline()){
			if(map1.containsKey(player.getName())){
				return map1.get(player.getName());
			}else{
				PlayerAttr pa = new PlayerAttr(player);
				pa.readAttr();
				map1.put(player.getName(), pa);
				return pa;
			}
		}
		return null;
	}
	
	private EntityEquipment equ;
	private ItemStack inHand;
	private ItemStack[] armor;
	private Map<String, Integer> attrs = new HashMap<>();
	private String name;
	private int wdzb = 0;
	private PlayerAttr(Player le){
		this.name = le.getName();
	}
	
	public boolean hasWdzb(){
		return wdzb > 0;
	}
	/**
	 * ��ȡ��ɫ����
	 * */
	@SuppressWarnings("deprecation")
	public PlayerAttr readAttr(boolean b){
		attrs.clear();
		Player le = Bukkit.getPlayer(name);
		System.out.println("��ʼ��ȡ");
		System.out.println("���ʵ��le:" + le);
		equ = le.getEquipment();
		System.out.println("���equ:" + equ);
		wdzb = 0;
		if(equ != null){
			armor = equ.getArmorContents();
			System.out.println("���armor:" + armor);
			inHand = equ.getItemInHand();
			System.out.println("���inHand:" + inHand);
			/**
			 * ����������ȡ
			 * */
			ItemMeta meta;
			List<String> lore;
			if(inHand != null && inHand.hasItemMeta() &&
					(meta = inHand.getItemMeta()).hasLore()){
				System.out.println("����meta:" + meta);
				lore = meta.getLore();
				System.out.println("����lore:");
				for(String line : lore){
					System.out.println(":" + line);
					praAttr(ChatColor.stripColor(line), true);
				}
			}
			if(armor != null){
				for(ItemStack item : armor){
					if(item != null){
						if(wdSet.contains(item.getTypeId())){
							wdzb++;
							continue;
						}
						if(item.hasItemMeta()){
							if((meta = item.getItemMeta()).hasLore()){
								System.out.println("����meta:" + meta);
								lore = meta.getLore();
								System.out.println("����lore:");
								for(String line : lore){
									System.out.println(":" + line);
									praAttr(ChatColor.stripColor(line), true);
								}
							}
						}
					}
				}
			}
		}
		return this;
	}
	
	/**
	 * ���Խ���
	 * */
	public void praAttr(String line, boolean b){
		if(line.contains(FGF)){
			String[] strs = line.split(FGF);
			if(strs.length == 2){
				String attrName = getRawName(strs[0]);
				if(attrName != null){
					strs[1] = strs[1].replace("%", "");
					System.out.println("����line:" + line + " ���:" + strs[1]);
					if(strs[1].contains("-")){
						strs[1] = strs[1].replace("-", "");
						reduceValue(attrName, Integer.parseInt(strs[1]));
					}else{
						addValue(attrName, Integer.parseInt(strs[1]));
					}
				}
			}
		}
	}
	
	/**
	 * ��ȡ��ɫ����
	 * */
	@SuppressWarnings("deprecation")
	public PlayerAttr readAttr(){
		attrs.clear();
		Player le = Bukkit.getPlayer(name);
		equ = le.getEquipment();
		wdzb = 0;
		if(equ != null){
			armor = equ.getArmorContents();
			inHand = equ.getItemInHand();
			
			/**
			 * ����������ȡ
			 * */
			ItemMeta meta;
			List<String> lore;
			if(inHand != null && inHand.hasItemMeta() &&
					(meta = inHand.getItemMeta()).hasLore()){
				lore = meta.getLore();
				for(String line : lore){
					praAttr(ChatColor.stripColor(line));
				}
			}
			if(armor != null){
				for(ItemStack item : armor){
					if(item != null){
						if(wdSet.contains(item.getTypeId())){
							wdzb++;
							continue;
						}
						if(item.hasItemMeta()){
							if((meta = item.getItemMeta()).hasLore()){
								lore = meta.getLore();
								for(String line : lore){
									praAttr(ChatColor.stripColor(line));
								}
							}
						}
					}
				}
			}
			/**
			 * �������Ե���ɫ
			 * */
			le.setWalkSpeed((float) (0.2 + 
					Math.min(0.8F, getAttrValue("�ƶ��ٶ�") / 100F)));
		}
		return this;
	}
	
	/**
	 * ���Խ���
	 * */
	public void praAttr(String line){
		if(line.contains(FGF)){
			String[] strs = line.split(FGF);
			if(strs.length == 2){
				String attrName = getRawName(strs[0]);
				if(attrName != null){
					strs[1] = strs[1].replace("%", "");
					if(strs[1].contains("-")){
						strs[1] = strs[1].replace("-", "");
						reduceValue(attrName, Integer.parseInt(strs[1]));
					}else{
						addValue(attrName, Integer.parseInt(strs[1]));
					}
				}
			}
		}
	}
	/**
	 * ��������ֵ
	 * */
	private void addValue(String name, int value){
		if(attrs.containsKey(name)){
			attrs.put(name, attrs.get(name) + value);
		}else{
			attrs.put(name, value);
		}
	}
	/**
	 * ��������ֵ
	 * */
	private void reduceValue(String name, int value){
		if(attrs.containsKey(name)){
			attrs.put(name, attrs.get(name) - value);
		}else{
			attrs.put(name, - value);
		}
	}
	
	/**
	 * ��ȡ����ֵ
	 * */
	public int getAttrValue(String name){
		return attrs.getOrDefault(name, 0);
	}
	
	/**
	 * ��ȡ�ָ���
	 * */
	public String getFGF(){
		return FGF;
	}
	
	/**
	 * ��ȡȫ������
	 * */
	public Map<String, Integer> getAttrs(){
		return attrs;
	}
	

	private final static void setFGF(String fgf){
		FGF = fgf + " ";
	}
	public static void remove(Player ep) {
		map1.remove(ep.getName());
	}

}
