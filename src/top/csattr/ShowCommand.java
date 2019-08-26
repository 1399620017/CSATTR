package top.csattr;

import java.util.Map.Entry;

import org.bukkit.entity.Player;

import top.csattr.Core.Command;
import top.csattr.Core.Msg;
import top.csattr.player.PlayerAttr;

public class ShowCommand  extends Command{

	public ShowCommand(String name, int len, String usage, String desc,
			boolean op) {
		super(name, len, usage, desc, op);
	}

	@Override
	public boolean send(Player player, String[] args) {
		Msg.sendMessage(player, "map！！！！！！！！！！！！！！！！！！！！！！！！！！");
		for(Entry<String, String> er : PlayerAttr.getMap().entrySet()){
			Msg.sendMessage(player, "key:" + er.getKey() + " value:" + er.getValue());
		}
		Msg.sendMessage(player, "map2！！！！！！！！！！！！！！！！！！！！！！！！！！");
		for(Entry<String, String> er : PlayerAttr.getMap2().entrySet()){
			Msg.sendMessage(player, "key:" + er.getKey() + " value:" + er.getValue());
		}
		Msg.sendMessage(player, "playerAttr！！！！！！！！！！！！！！！！！！！！！！！！！！");
		for(Entry<String, PlayerAttr> er : PlayerAttr.getMap1()){
			Msg.sendMessage(player, er.getKey() + ":" + er.getValue());
		}
		return false;
	}

}
