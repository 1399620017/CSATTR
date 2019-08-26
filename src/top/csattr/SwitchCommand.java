package top.csattr;

import org.bukkit.entity.Player;

import top.csattr.Core.Command;
import top.csattr.Core.Msg;
import top.csattr.player.PlayerSwitch;

public class SwitchCommand extends Command{

	public SwitchCommand(String name, int len, String usage, String desc,
			boolean op) {
		super(name, len, usage, desc, op);
	}

	@Override
	public boolean send(Player player, String[] args) {
		PlayerSwitch ps = PlayerSwitch.getPlayerSwitch(player);
		if(ps.getStu()){
			ps.close();
		}else{
			ps.open();
		}
		ps.save();
		Msg.sendMsgTrue(player, "战斗信息显示开关:" + ps.getStu());
		return true;
	}

}
