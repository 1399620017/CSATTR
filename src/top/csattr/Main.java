package top.csattr;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import top.csattr.Core.CorePlugin;
import top.csattr.listener.EntityDamageByEntityListener;
import top.csattr.listener.EntityExplodeListener;
import top.csattr.listener.EntityRegainHealthListener;
import top.csattr.listener.FireDamageListener;
import top.csattr.listener.authme.Login;
import top.csattr.listener.block.BlockBreakListener;
import top.csattr.listener.block.BlockBurnListener;
import top.csattr.listener.block.BlockFadeListener;
import top.csattr.listener.block.BlockFormListener;
import top.csattr.listener.block.BlockGrowListener;
import top.csattr.listener.block.BlockIgniteListener;
import top.csattr.listener.block.BlockPlaceListener;
import top.csattr.listener.block.BlockSpreadListener;
import top.csattr.listener.block.EntityBlockFormListener;
import top.csattr.listener.block.LeavesDecayListener;
import top.csattr.listener.block.PlayerBucketEmptyListener;
import top.csattr.listener.block.PlayerBucketFillListener;
import top.csattr.listener.entity.Death;
import top.csattr.listener.entity.PlayerSpawn;
import top.csattr.player.PlayerAttr;
import top.csattr.vexview.listener.KeyListener;
import top.csattr.vexview.listener.VexGuiClose;

public class Main extends CorePlugin{
	private static FileConfiguration config;
	public static Plugin plugin;
	@Override
	public void start() {
		plugin = this;
		saveDefaultConfig();
		config = getConfig();
		PlayerAttr.getAttrName(config);
	}
	@Override
	public String pluginName() {
		return "csattr";
	}
	@Override
	public String serverName() {
		return "服务器";
	}
	@Override
	public String pluginCommand() {
		return "csa";
	}
	@Override
	public void listenter() {
		regListener(new EntityDamageByEntityListener());
		regListener(new FireDamageListener());
		regListener(new EntityRegainHealthListener());
		regListener(new KeyListener()); // VexView按键监听
		regListener(new VexGuiClose()); // VexView界面关闭监听
		regListener(new EntityExplodeListener()); // 爆炸监听
		
		
		regListener(new Death()); // PVP死亡掉落
		regListener(new Login()); // 玩家登陆事件
		regListener(new PlayerSpawn()); // 修复刷经验BUG

		/**
		 * 方块监听注册
		 * */
		regListener(new BlockBreakListener()); // 方块打破事件 末地刷矿
		regListener(new PlayerBucketFillListener()); // 桶填装事件
		regListener(new PlayerBucketEmptyListener()); // 桶使用事件
		regListener(new LeavesDecayListener()); // 树叶腐烂事件
		regListener(new BlockFadeListener()); // 方块自然变化事件
		regListener(new BlockFormListener()); // 方块自然变化
		regListener(new BlockBurnListener()); // 方块被火烧掉
		regListener(new BlockGrowListener()); // 方块自然生长
		regListener(new BlockIgniteListener()); // 方块被点燃
		regListener(new BlockSpreadListener()); // 方块蔓延事件
		regListener(new EntityBlockFormListener()); // 方块建成实体
		regListener(new BlockPlaceListener()); // 方块放置事件
		/**
		 * 方块监听注册
		 * */
	}
	@Override
	public void command() {
		new SwitchCommand("s", 0, "s", "切换当前战斗信息显示状态", false);
		new ShowCommand("v", 0, "v", "op命令", true);
		new LookCommand("l", 1, "l <name>", "op命令", true);
	}
}
