package top.csattr.vexview.listener;

import lk.vexview.api.VexViewAPI;
import lk.vexview.event.KeyBoardPressEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.csattr.vexview.gui.MenuGui;

public class KeyListener implements Listener{
	@EventHandler
	public void _(KeyBoardPressEvent e){
		if(e.getEventKeyState()){
			Player ep = e.getPlayer(); // °´¼üÕß I:23 L:38 Z:44
			switch (e.getKey()) {
			case 20: // 23:I
				MenuGui mg = new MenuGui(ep);
				VexViewAPI.openGui(ep, mg);
				MenuGui.open(ep);
				break;
			case 37: // K:37
				break;

			default:
				break;
			}
			
		}
	}
}
