package top.csattr.vexview.gui;

import java.util.ArrayList;
import java.util.List;

import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexText;

public class BaseGui extends VexGui{
	protected int titlex; // 标题x坐标
	protected int titley = 15; // 标题y坐标
	protected int x;
	protected int y;
	public BaseGui(int x, int y) {
		super("[local]gui.png", -1, -1, x, y, x, y);
		titlex = (x - 50) / 2 - 2;
		this.x = x;
		this.y = y;
	}
	protected void setTitle(String msg){
		List<String> text = new ArrayList<>();
		text.add(msg);
		this.addComponent(new VexText(titlex, titley, text, 1.4D));
	}
}
