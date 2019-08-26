package top.csattr;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import top.csattr.Core.Command;
import top.csattr.Core.Msg;
import top.csattr.player.PlayerAttr;

public class LookCommand extends Command{

	public LookCommand(String name, int len, String usage, String desc,
			boolean op) {
		super(name, len, usage, desc, op);
	}

	@Override
	public boolean send(Player player, String[] args) {
		Player p = Bukkit.getPlayer(args[0]);
		if(p != null && p.isOnline()){
			PlayerAttr pa = PlayerAttr.getPlayer(p);
			pa.readAttr(false);
		}else{
			Msg.sendMsgFalse(player, "玩家不在线!");
		}
		return true;
	}

}
