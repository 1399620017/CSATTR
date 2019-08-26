package top.csattr.vexview.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import top.csattr.player.PlayerAttr;

import lk.vexview.gui.components.VexText;

public class AttributeGui extends BaseGui{

	private static String[] attrList = {
		"攻击力", "防御力",
		"PVP攻击力", "PVP防御力",
		"PVE攻击力", "PVE防御力",
		"命中几率", "闪避几率",
		"穿透几率", "减速几率",
		"暴击几率", "暴击伤害",
		"生命偷取", "攻击回血",
		"点燃几率", "点燃伤害",
		"闪电几率", "闪电伤害",
		"爆炸几率", "爆炸伤害",
		"凋零几率", "凋零伤害",
		"施毒几率", "中毒伤害",
		"格挡几率", "格挡伤害",
		"反弹几率", "反弹伤害",
		"生命值", "生命回复",
		"移动速度", "经验加成",
		};
	private int textx = 50; // 正文x坐标
	private int texty = 30; // 正文y坐标
	private int flx = 100; // 分栏x坐标
	private int fly = 15; // 分栏y坐标
	private final PlayerAttr pa;
	
	public AttributeGui(Player player) {
		super(240, 300);
		setTitle("§1人物属性");
		pa = PlayerAttr.getPlayer(player);
		if(pa != null){
			int index = 0;
			for(String name : attrList){
				String color;
				int value = pa.getAttrValue(name);
				String cname = PlayerAttr.getCustomName(name);
				if(value > 40){
					color = "§6";
				}else if(value > 20){
					color = "§b";
				}else if(value > 0){
					color = "§a";
				}else if(value == 0){
					color = "§f";
				}else{
					color = "§c";
				}
				addText("§f" + cname + ": " + color + pa.getAttrValue(name), index++);
			}
		}
	}
	public void addText(String msg, int id){
		List<String> text = new ArrayList<>();
		text.add(msg);
		int x = textx + id % 2 * flx;
		int y = texty + id / 2 * fly;
		this.addComponent(new VexText(x, y, text, 1.1D));
	}
}
