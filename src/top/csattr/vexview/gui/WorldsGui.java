package top.csattr.vexview.gui;

import lk.vexview.gui.components.ButtonFunction;
import lk.vexview.gui.components.VexButton;

import org.bukkit.entity.Player;

public class WorldsGui extends BaseGui{
	private int textx = 25; // ����x����
	private int texty = 85; // ����y����
	private int flx = 60; // ����x����
	private int fly = 40; // ����y����
	public WorldsGui(Player player) {
		super(550, 300);
		
	}
	public void addButton(String buttonId, String name, int id, ButtonFunction bf) {
		int x = textx + (id % 7 - 1) * flx;
		int y = texty + (id / 6 - 1) * fly;
		this.addComponent(new VexButton(buttonId, name, "[local]button.png",
				"[local]button_.png", x, y, 50, 30, bf)); // new VexHoverText(Arrays.asList(desc))
	}
}
