package top.csattr.vexview.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import top.csattr.player.PlayerAttr;

import lk.vexview.gui.components.VexText;

public class AttributeGui extends BaseGui{

	private static String[] attrList = {
		"������", "������",
		"PVP������", "PVP������",
		"PVE������", "PVE������",
		"���м���", "���ܼ���",
		"��͸����", "���ټ���",
		"��������", "�����˺�",
		"����͵ȡ", "������Ѫ",
		"��ȼ����", "��ȼ�˺�",
		"���缸��", "�����˺�",
		"��ը����", "��ը�˺�",
		"���㼸��", "�����˺�",
		"ʩ������", "�ж��˺�",
		"�񵲼���", "���˺�",
		"��������", "�����˺�",
		"����ֵ", "�����ظ�",
		"�ƶ��ٶ�", "����ӳ�",
		};
	private int textx = 50; // ����x����
	private int texty = 30; // ����y����
	private int flx = 100; // ����x����
	private int fly = 15; // ����y����
	private final PlayerAttr pa;
	
	public AttributeGui(Player player) {
		super(240, 300);
		setTitle("��1��������");
		pa = PlayerAttr.getPlayer(player);
		if(pa != null){
			int index = 0;
			for(String name : attrList){
				String color;
				int value = pa.getAttrValue(name);
				String cname = PlayerAttr.getCustomName(name);
				if(value > 40){
					color = "��6";
				}else if(value > 20){
					color = "��b";
				}else if(value > 0){
					color = "��a";
				}else if(value == 0){
					color = "��f";
				}else{
					color = "��c";
				}
				addText("��f" + cname + ": " + color + pa.getAttrValue(name), index++);
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
