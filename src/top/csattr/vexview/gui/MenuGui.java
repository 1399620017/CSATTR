package top.csattr.vexview.gui;

import java.util.HashSet;
import java.util.Set;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.components.ButtonFunction;
import lk.vexview.gui.components.VexButton;
import org.bukkit.entity.Player;

import top.csattr.plotme.gui.PlotMeGui;

public class MenuGui extends BaseGui {
	private static Set<String> set = new HashSet<>();
	public static boolean isOpen(Player player){
		return set.contains(player.getName());
	}
	public static void open(Player player){
		set.add(player.getName());
	}
	public static void close(Player player){
		set.remove(player.getName());
	}
	private int textx = 25; // 正文x坐标
	private int texty = 85; // 正文y坐标
	private int flx = 60; // 分栏x坐标
	private int fly = 40; // 分栏y坐标

	public MenuGui(final Player player) {
		super(550, 300);
		setTitle("§d§n§l天界菜单");
		addButton("attr", "个人属性", 1, new ButtonFunction() {
			
			@Override
			public void run(Player arg0) {
				AttributeGui mg = new AttributeGui(player);
				VexViewAPI.openGui(player, mg);
				close(player);
			}
		});
		addButton("plot", "地皮系统", 2, new ButtonFunction() {
			
			@Override
			public void run(Player arg0) {
				PlotMeGui mg = new PlotMeGui(player);
				VexViewAPI.openGui(player, mg);
			}
		});
		addButton("spawn", "回到主城", 3, new ButtonFunction() {
			
			@Override
			public void run(Player arg0) {
				player.closeInventory();
				player.chat("/spawn");
			}
		});
	}

	public void addButton(String buttonId, String name, int id, ButtonFunction bf) {
		int x = textx + (id % 7 - 1) * flx;
		int y = texty + (id / 6 - 1) * fly;
		this.addComponent(new VexButton(buttonId, name, "[local]button.png",
				"[local]button_.png", x, y, 50, 30, bf)); // new VexHoverText(Arrays.asList(desc))
	}
}
