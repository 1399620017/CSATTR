package top.csattr.vexview.listener;

import lk.vexview.event.gui.VexGuiCloseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import top.csattr.vexview.gui.MenuGui;

public class VexGuiClose implements Listener{

	@EventHandler
	public void _(VexGuiCloseEvent e){
		MenuGui.close(e.getPlayer());
	}
}
