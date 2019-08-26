package top.csattr;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event;


public final class Core {
	/**
	 * 插件实例
	 * */
	public static JavaPlugin plugin;
	/**
	 * 服务端后台对象
	 * */
	public static ConsoleCommandSender serverSender;
	/**
	 * 插件信息实例
	 * */
	public static PluginManager pluginManager;
	/**
	 * 插件中文名
	 * */
	public static String pluginName;
	/**
	 * 公告名字
	 * */
	public static String serverName;
	/**
	 * 命令名
	 * */
	public static String cmdName;
	
	/**
	 * BarAPI 开关
	 * */
	public static boolean barApi = false;

	/**
	 * Core框架接口 插件主类实现这个接口即可
	 * */
	public static abstract class CorePlugin extends JavaPlugin {

		/**
		 * 插件入口 启动提示 插件实例变量 等已经获取
		 * */
		public abstract void start();

		/**
		 * 插件中文名字 用于发送消息
		 * */
		public abstract String pluginName();

		/**
		 * 服务端中文名 用于发送公告
		 * */
		public abstract String serverName();

		/**
		 * 插件主命令名字 不用带斜杠
		 * */
		public abstract String pluginCommand();

		/**
		 * 事件注册 regListener(Listener listener)
		 * */
		public abstract void listenter();
		
		/**
		 * 注册命令 new E() extend Command ;
		 * */
		public abstract void command();

		public final void onEnable() {
			pluginName = "§a[" + pluginName() + "]§e";
			serverName = "§b[" + serverName() + "公告]§e";
			cmdName = "/" + pluginCommand();
			plugin = this;
			pluginManager = getServer().getPluginManager();
			serverSender = getServer().getConsoleSender();
			start();
			Msg.sendConPlugin("插件启动成功");
			listenter();
			Msg.sendConPlugin("注册监听成功");
			command();
			regListener(new Core.CmdListener());
			Msg.sendConPlugin("注册Core玩家命令成功");
			
		}

		public final void onDisable() {
			
		}

		/**
		 * 监听器注册
		 * */
		public static CorePlugin regListener(Listener listener) {
			pluginManager.registerEvents(listener, plugin);
			return null;
		}
	}

	/**
	 * 信息处理
	 * */
	public final static class Msg {

		/**
		 * 获取插件名字
		 * */
		public static String getPluginName() {
			return pluginName;
		}

		/**
		 * 获取服务器名字
		 * */
		public static String getServerName() {
			return serverName;
		}
		/**
		 * 发送更新消息给所有玩家
		 * */
		public static Msg sendTitleUpdataStart() {
			for(Player p : Bukkit.getOnlinePlayers()){
				if (p != null) {
					if(p.isOnline()){
						Title.sendTitle(p.getPlayer(), 20, 60, 20, 
								"§c服务器正在更新！", "§e如有异常,请等待更新结束,即可恢复正常!");
					}
				}
			}
			return null;
		}
		/**
		 * 发送更新完毕消息给所有玩家
		 * */
		public static Msg sendTitleUpdata() {
			for(Player p : Bukkit.getOnlinePlayers()){
				if (p != null) {
					if(p.isOnline()){
						Title.sendTitle(p.getPlayer(), 20, 60, 20, 
								"§c服务器更新完毕！", "§e如有异常,请切换所在世界,即可恢复正常!");
					}
				}
			}
			return null;
		}
		/**
		 * 发送大屏幕信息给玩家
		 * */
		public static Msg sendTitleMsgOcUpLevel(Player player) {
			if (player != null) {
				Title.sendTitle(player, 20, 30, 20, "§a职业等级提升", "§e请输入/asx 或者 /sx v查看职业信息。");
			}
			return null;
		}
		
		/**
		 * 发送大屏幕信息给玩家
		 * */
		public static Msg sendTitleMsg(Player player, String msg1, String msg2) {
			if (player != null) {
				Title.sendTitle(player, 20, 50, 20, msg1, msg2);
			}
			return null;
		}
		
		/**
		 * 给*发送白色系统提示
		 * */
		public static Msg sendMsg(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("§8[§a!§8] §f" + msg);
			}
			return null;
		}

		/**
		 * 给*发送绿色系统提示
		 * */
		public static Msg sendMsgTrue(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("§8[§a!§8] §a§n" + msg);
			}
			return null;
		}

		/**
		 * 给*发送红色系统提示
		 * */
		public static Msg sendMsgFalse(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("§8[§a!§8] §c§n" + msg);
			}
			return null;
		}

		/**
		 * 给*发送测试信息
		 * */
		public static Msg sendCs(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("[测试]" + msg);
			}
			return null;
		}

		/**
		 * 给*发送普通信息
		 * */
		public static Msg sendMessage(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("§a" + msg);
			}
			return null;
		}

		/**
		 * 以插件名义发送信息
		 * */
		public static Msg sendPluginMsg(CommandSender sender, String msg) {
			sender.sendMessage(pluginName + msg);
			return null;
		}
		
		/**
		 * 以插件名义发送后台信息
		 * */
		public static Msg sendConPlugin(String msg) {
			Core.serverSender.sendMessage(pluginName + msg);
			return null;
		}

		/**
		 * 以服务器名义发送公告
		 * */
		public static Msg sendServerBroad(String msg) {
			Bukkit.broadcastMessage(serverName + msg);
			return null;
		}

		/**
		 * 以插件名义发送公告
		 * */
		public static Msg sendPluginBroad(String msg) {
			Bukkit.broadcastMessage(pluginName + msg);
			return null;
		}

		/**
		 * 给后台发送绿色消息
		 * */
		public static Msg sendConMsgTrue(String msg) {
			serverSender.sendMessage("§8[§a!§8] §a" + msg);
			return null;
		}

		/**
		 * 给后台发送红色消息
		 * */
		public static Msg sendConMsgFalse(String msg) {
			serverSender.sendMessage("§8[§a!§8] §c" + msg);
			return null;
		}
	}
	/**
	 * Title类
	 * */
	public static class Title {

		public void Server_broadcast(Player player, Integer fadeIn,
				Integer stay, Integer fadeOut, String title, String subtitle) {
			/*
			 * Bukkit.broadcastMessage(ChatColor.DARK_RED + broadcast); 发送全服广播信息
			 * Bukkit.getPlayer("name") 玩家String name Ftime 淡出时间 TimeS 停留在屏幕的时间
			 * FsTime 淡出时间 title 要发送的title subtitle 要发送的子title
			 * 
			 * Player player, Integer fadeIn, Integer stay, Integer fadeOut,
			 * String title, String subtitle
			 */
			sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
		}

		public static void sendTitle(Player player, Integer fadeIn,
				Integer stay, Integer fadeOut, String title, String subtitle) {
			try {
				if (title != null) { // 要发送的title
					title = title.replaceAll("%player%",
							player.getDisplayName());
					Object enumTitle = getNMSClass("PacketPlayOutTitle")
							.getDeclaredClasses()[0].getField("TITLE")
							.get(null);
					Object chatTitle = getNMSClass("IChatBaseComponent")
							.getDeclaredClasses()[0].getMethod("a",
							new Class[] { String.class }).invoke(null,
							new Object[] { "{\"text\":\"" + title + "\"}" });
					Constructor<?> titleConstructor = getNMSClass(
							"PacketPlayOutTitle").getConstructor(
							new Class[] {
									getNMSClass("PacketPlayOutTitle")
											.getDeclaredClasses()[0],
									getNMSClass("IChatBaseComponent"),
									Integer.TYPE, Integer.TYPE, Integer.TYPE });
					Object titlePacket = titleConstructor
							.newInstance(new Object[] { enumTitle, chatTitle,
									fadeIn, stay, fadeOut });
					sendPacket(player, titlePacket);
				}

				if (subtitle != null) { // 要发送的子title
					subtitle = subtitle.replaceAll("%player%",
							player.getDisplayName());
					Object enumSubtitle = getNMSClass("PacketPlayOutTitle")
							.getDeclaredClasses()[0].getField("SUBTITLE").get(
							null);
					Object chatSubtitle = getNMSClass("IChatBaseComponent")
							.getDeclaredClasses()[0].getMethod("a",
							new Class[] { String.class }).invoke(null,
							new Object[] { "{\"text\":\"" + subtitle + "\"}" });
					Constructor<?> subtitleConstructor = getNMSClass(
							"PacketPlayOutTitle").getConstructor(
							new Class[] {
									getNMSClass("PacketPlayOutTitle")
											.getDeclaredClasses()[0],
									getNMSClass("IChatBaseComponent"),
									Integer.TYPE, Integer.TYPE, Integer.TYPE });
					Object subtitlePacket = subtitleConstructor
							.newInstance(new Object[] { enumSubtitle,
									chatSubtitle, fadeIn, stay, fadeOut });
					sendPacket(player, subtitlePacket);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static Class<?> getNMSClass(String name) {
			String version = org.bukkit.Bukkit.getServer().getClass()
					.getPackage().getName().split("\\.")[3];
			try {
				return Class.forName("net.minecraft.server." + version + "."
						+ name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}

		public static void sendPacket(Player player, Object packet) {
			try {
				Object handle = player.getClass()
						.getMethod("getHandle", new Class[0])
						.invoke(player, new Object[0]);
				Object playerConnection = handle.getClass()
						.getField("playerConnection").get(handle);
				playerConnection
						.getClass()
						.getMethod("sendPacket",
								new Class[] { getNMSClass("Packet") })
						.invoke(playerConnection, new Object[] { packet });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 命令接口事件
	 * */
	public final static class CmdListener implements Listener {

		@EventHandler(priority = EventPriority.LOWEST)
		public void _(PlayerCommandPreprocessEvent e) {
			String cmd = e.getMessage();
			String[] cmdargs = cmd.toLowerCase().split(" ");
			if (!cmdargs[0].equals(cmdName)) {
				return;
			}
			Player ePlayer = e.getPlayer();
			if (cmdargs.length == 1) {
				Msg.sendMsgTrue(ePlayer, "可用命令如下:");
				Command.sendCmdShow(ePlayer);
				e.setCancelled(true);
				return;
			}
			if (!Command.commandList.containsKey(cmdargs[1])) {
				Command.sendCmdShow(ePlayer);
				e.setCancelled(true);
				return;
			}
			Command command = Command.commandList.get(cmdargs[1]);
			if (command.op && !ePlayer.isOp()){
				return;
			}
			if (!(command.len == cmdargs.length - 2)) {
				command.cmdUsage(ePlayer);
				e.setCancelled(true);
				return;
			}
			if (command.len == 0) {
				if(!command.send(ePlayer, null)){
					command.cmdUsage(ePlayer);
				}
				e.setCancelled(true);
				return;
			}
			if(!command.send(ePlayer, Arrays.copyOfRange(
					cmdargs, 2, cmdargs.length))){
				command.cmdUsage(ePlayer);	
			}
			e.setCancelled(true);
			return;
		}
	}

	/**
	 * 命令抽象类
	 * */
	public abstract static class Command {
		private static Map<String, Command> commandList = new HashMap<String, Command>();

		/**
		 * 添加命令
		 * */
		public final static void addCommand(Command command) {
			commandList.put(command.getName(), command);
		}

		/**
		 * 获取命令列表
		 * */
		public final static Map<String, Command> getCommands() {
			return commandList;
		}

		/**
		 * 发送可用命令列表
		 * */
		public final static void sendCmdShow(Player player) {
			for (Command command : Command.commandList.values()) {
				if(command.op&&!player.isOp()){
					continue;
				}
				command.cmdUsage(player);
			}
		}
		
		/**
		 * 发送当前命令使用方式
		 * */
		public final void cmdUsage(Player player) {
			Msg.sendMessage(player, new StringBuilder(cmdName)
				.append(" ")
				.append(this.usage)
				.append("  ")
				.append(this.desc)
				.toString()
			);
		}
		/**
		 * 命令名字
		 * */
		protected String name;
		/**
		 * 命令参数长度
		 * */
		protected int len;
		/**
		 * 命令使用方法
		 * */
		protected String usage;
		/**
		 * 命令介绍
		 * */
		protected String desc;
		
		/**
		 * 命令介绍
		 * */
		protected boolean op;

		/**
		 * 创建命令类 name 命令字符串 len 命令参数长度
		 * */
		public Command(String name, int len, String usage, String desc,
				boolean op) {
			this.name = name;
			this.len = len;
			this.usage = usage;
			this.desc = desc;
			this.op = op;
			addCommand(this);
		}

		/**
		 * 获取命令名
		 * */
		public final String getName() {
			return name;
		}

		/**
		 * 获取命令介绍
		 * */
		public final String getDesc() {
			return desc;
		}

		/**
		 * 获取命令使用方法
		 * */
		public final String getUsage() {
			return usage;
		}
		
		/**
		 * 获取是否op可用
		 * */
		public final boolean getOp() {
			return op;
		}

		/**
		 * 获取命令参数长度
		 * */
		public final int getLen() {
			return len;
		}

		/**
		 * 玩家使用命令 args 命令参数  返回false为命令使用方式错误
		 * */
		public abstract boolean send(Player player, String[] args);
	}
	
	/**
	 * 玩家Core类
	 * */
	public final static class CorePlayer {
		
		/**
		 * 判断玩家是否在线
		 * */
		public static boolean isOnline(Player p){
			if(p != null){
				return p.isOnline();
			}
			return false;
		}
		
		/**
		 * 判断玩家是否在线
		 * */
		public static boolean isOnline(String name){
			Player p = Bukkit.getPlayer(name);
			return isOnline(p);
		}
		
		/**
		 * 消耗玩家手中名字包含指定字符的物品n个
		 * */
		public static boolean consumeItemOnHandByName(Player p, String itemName, int n){
			if(isOnline(p)){
				ItemStack item = p.getItemInHand();
				if(!item.hasItemMeta()){
					return false;
				}
				ItemMeta meta;
				if(!(meta = item.getItemMeta()).hasDisplayName()){
					return false;
				}
				if(!meta.getDisplayName().contains(itemName)){
					return false;
				}
				int itemsl;
				if((itemsl = item.getAmount()) < n){
					return false;
				}
				item.setAmount(itemsl - n);
				return true;
			}
			return false;
		}
	}
	
	/**
	 * 配置文件类
	 * */
	public static abstract class Config {
		private Filep filep;
		/**
		 * 文件对象 
		 * */
		public YamlConfiguration config;
		private File configdir;
		/**
		 * 创建文件， filep 是文件夹名 name 是文件名 不用带后缀 
		 * */
		public Config(String filep, String name){
			this.filep = Filep.get(filep);
			configdir = new File(this.filep.getDirStr().toString() + "/" + name + ".yml");
			if(hasConfig()){
				config = get();
			}else{
				config = get();
				defaultConfig();
				save();
			}
		}
		/**
		 * 实现默认保存格式
		 * */
		public abstract void defaultConfig();
		/**
		 * 实现保存格式
		 * */
		public abstract void saveConfig();
		/**
		 * 判断是否有这个配置
		 * */
		public boolean hasConfig(){
			// 判断是否有这个文件没有则创建空配置
			if(configdir.exists()){
				get();
				return true;
			}else{
				// 不存在则创建文件
				try
				{
					configdir.createNewFile();
				}catch (IOException localIOException) {
					
				}	
				return false;
			}
		}
		/**
		 * 获取配置文件 请先查看是否存在配置文件
		 * */
		public YamlConfiguration get(){
			config = Filep.getConfig(configdir);
			return config;
		}
		/**
		 * 删除配置文件自身
		 * */
		public void remove(){
			if(configdir.exists()){
				Filep.removeFile(configdir);
			}
		}
		/**
		 * 保存配置文件
		 * */
		public void save(){
			saveConfig();
			try {
				config.save(configdir);
			} catch (IOException e) {
				
			}
		}
		public String getDirStr(){
			return configdir.toString();
		}
	}
	/**
	 * 独立配置文件
	 * */
	public static class Configure extends Config{
		private static Map<String, Configure> map = new HashMap<>();
		
		public static Configure getConfigure(String name){
			if(map.containsKey(name)){
				return map.get(name);
			}
			Configure newConfigure = new Configure(name);
			map.put(name, newConfigure);
			return newConfigure;
		}

		public Configure(String name) {
			super("config", name);
			
		}
		
		public int getIntItem(String itemName, int defaultValue){
			if(config.contains(itemName)){
				return config.getInt(itemName);
			}
			config.set(itemName, defaultValue);
			save();
			return defaultValue;
		}
		public double getDoubleItem(String itemName, double defaultValue){
			if(config.contains(itemName)){
				return config.getDouble(itemName);
			}
			config.set(itemName, defaultValue);
			save();
			return defaultValue;
		}
		public long getLongItem(String itemName, long defaultValue){
			if(config.contains(itemName)){
				return config.getLong(itemName);
			}
			config.set(itemName, defaultValue);
			save();
			return defaultValue;
		}
		public String getStringItem(String itemName, String defaultValue){
			if(config.contains(itemName)){
				return config.getString(itemName);
			}
			config.set(itemName, defaultValue);
			save();
			return defaultValue;
		}
		public List<String> getStringListItem(String itemName, List<String> defaultValue){
			if(config.contains(itemName)){
				return config.getStringList(itemName);
			}
			config.set(itemName, defaultValue);
			save();
			return defaultValue;
		}

		@Override
		public void defaultConfig() {
			
		}

		@Override
		public void saveConfig() {
			
		}
		
	}
	/**
	 * 配置文件对象
	 * */
	public static class Filep {
		public static Map<String, Filep> wjjlist = new HashMap<String, Filep>();
		/**
		 * 获取配置文件对象的文件夹
		 * */
		public static Filep get(String name){
			wjjlist.put(name, wjjlist.getOrDefault(name, new Filep(name)));
			return wjjlist.get(name);
		}
		private File filedir;
		public Filep(String dir){
			filedir = new File(Core.plugin.getDataFolder().toString()+"/"+dir);
			if(!filedir.exists()){
				filedir.mkdir();
			}else {

			}
		}
		/**
		 * 获取文件夹地址
		 * */
		public String getDirStr() {
			return filedir.toString();
		}
		/**
		 * 移除某个文件
		 * */
		public static boolean removeFile(File file){
			return file.delete();
		}
		/**
		 * 获取完整 YamlConfiguration 实例
		 * */
		public static YamlConfiguration getConfig(File file){
			/*
			 * 获取配置文件
			 * 输入 file 完整路径名
			 * */
			return YamlConfiguration.loadConfiguration(file);
		}
	}
	/**
	 * DateTool
	 * */
	public final static class DateTool {
		
		private final static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		private final static SimpleDateFormat hour = new SimpleDateFormat("hh");
		/**
		 * 获取当前小时字符串
		 * */
		public static String getHourString(){
			Date date = new Date();
			return hour.format(date);
		}
		/**
		 * 获取当前时间对象
		 * */
		public static Date getDate(){
			return new Date();
		}
		/**
		 * 获取当前日期字符串
		 * */
		public static String getDateString(){
			Date date = new Date();
			return format.format(date);
		}
		/**
		 * 获取当前时间戳
		 * */
		public static long getTime(){
			return new Date().getTime();
		}
	}
	
	/**
	 * Cui类
	 * */
	public static abstract class Gui {	
		protected static Map<Inventory,Gui> invtab = new HashMap<Inventory, Gui>();
		/**
		 * gui所用的背包实体
		 * */
		protected Inventory i;
		/**
		 * gui的拥有者 玩家
		 * */
		private Player p;	
		protected int index = 0;	
		/**
		 * gui的大小等级
		 * */
		protected int level;	
		/**
		 * gui的所有者名字
		 * */
		private String playerName;
		/**
		 * 创建gui过程
		 * */
		public Gui(Player player,String title,int lv){		
			setP(player);		
			setPlayerName(player.getName());
			level = lv*9;		
			i = Bukkit.createInventory(null, level, title);	
			invtab.put(i, this);
			drawWindow();	
		}	
		/**
		 * 获取gui大小等级
		 * */
		public int getLevel(){
			return level;
		}
		/**
		 * 窗口更新方法 每次打开窗口都会调用
		 * */
		public abstract void updateWindow();	
		/**
		 * 窗口绘制方法 用于绘制边框等不变项目 只在窗口创建后调用一次
		 * */
		public abstract void drawWindow();
		/**
		 * 窗口所有者左键点击事件 返回true则允许事件
		 * */
		public abstract boolean leftClickEvent(int solt, InventoryClickEvent e);	
		/**
		 * 窗口所有者右键点击事件 返回true则允许事件
		 * */
		public abstract boolean rightClickEvent(int solt, InventoryClickEvent e);
		/**
		 * 窗口所有者SHIFT加左键点击事件 返回true则允许事件
		 * */
		public abstract boolean leftShiftClickEvent(int solt, InventoryClickEvent e);	
		/**
		 * 窗口所有者SHIFT加右键点击事件 返回true则允许事件
		 * */
		public abstract boolean rightShiftClickEvent(int solt, InventoryClickEvent e);
		/**
		 * 窗口所有者左键点击窗口内的背包部分事件 返回true则允许事件
		 * */
		public abstract boolean leftClickPlayerEvent(int solt, InventoryClickEvent e);	
		/**
		 * 窗口所有者右键点击窗口内的背包部分事件 返回true则允许事件
		 * */
		public abstract boolean rightClickPlayerEvent(int solt, InventoryClickEvent e);
		/**
		 * 窗口所有者SHIFT加左键点击窗口内的背包部分事件 返回true则允许事件
		 * */
		public abstract boolean leftShiftClickPlayerEvent(int solt, InventoryClickEvent e);	
		/**
		 * 窗口所有者SHIFT加右键点击窗口内的背包部分事件 返回true则允许事件
		 * */
		public abstract boolean rightShiftClickPlayerEvent(int solt, InventoryClickEvent e);
		/**
		 * 窗口其他玩家左键点击事件 返回true则允许事件
		 * */
		public abstract boolean leftPlayerClickEvent(Player player, int solt, InventoryClickEvent e);	
		/**
		 * 窗口其他玩家右键点击事件 返回true则允许事件
		 * */
		public abstract boolean rightPlayerClickEvent(Player player, int solt, InventoryClickEvent e);
		/**
		 * 窗口其他玩家左键点击自身背包部分事件 返回true则允许事件
		 * */
		public abstract boolean leftPlayerClickPlayerEvent(Player player, int solt, InventoryClickEvent e);	
		/**
		 * 窗口其他玩家右键点击自身背包部分事件 返回true则允许事件
		 * */
		public abstract boolean rightPlayerClickPlayerEvent(Player player, int solt, InventoryClickEvent e);
		/**
		 * 窗口关闭事件 返回true则清除次窗口实例 返回true则允许事件
		 * */
		public abstract boolean closeEvent();
		public void open(){
			getP().closeInventory();
			getP().openInventory(i);	
		}	
		@SuppressWarnings("deprecation")
		public ItemStack drawButton(int wz,int iid,int num,int fid,String name,String[] lore){		
			ItemStack item = new ItemStack(iid,num,(short)fid);    	
			ItemMeta itm = item.getItemMeta();    	
			itm.setDisplayName(name);    	
			List<String> lores = Arrays.asList(lore);    	
			itm.setLore(lores);    	
			item.setItemMeta(itm);    	
			i.setItem(wz, item);    	
			return item;	
		}
		public ItemStack drawButton(int x,int y,int iid,int num,int fid,String name,String[] lore){	
			return drawButton((y -1) * 9 + x -1, iid, num, fid,name,lore);	
		}
		@SuppressWarnings("deprecation")
		public void drawButton(int wz,int iid,int num,int fid,String name,List<String> lore){		
			ItemStack item = new ItemStack(iid,num,(short)fid);    	
			ItemMeta itm = item.getItemMeta();    	
			itm.setDisplayName(name);    	
			itm.setLore(lore);    	
			item.setItemMeta(itm);    	
			i.setItem(wz, item); 	
		}	
		public void drawButton(int x,int y,int iid, int num,int fid,String name,List<String> lore){
			drawButton((y -1) * 9 + x -1, iid, num, fid,name,lore);
		}
		@SuppressWarnings("deprecation")
		public void drawButton(int wz,int iid,int num,int fid,String name){		
			ItemStack item = new ItemStack(iid,num,(short)fid);    	
			ItemMeta itm = item.getItemMeta();    	
			itm.setDisplayName(name);  	
			item.setItemMeta(itm);    	
			i.setItem(wz, item);   
		}	
		@SuppressWarnings("deprecation")
		public void drawButton(int x,int y,int iid,int num,int fid,String name){		
			ItemStack item = new ItemStack(iid,num,(short)fid);    	
			ItemMeta itm = item.getItemMeta();    	
			itm.setDisplayName(name);  	
			item.setItemMeta(itm);    	
			i.setItem((y -1) * 9 + x -1, item);   
		}
		public void drawButton(int wz,ItemStack item){		
			i.setItem(wz, item);	
		}
		public void drawButton(int x,int y,ItemStack item){		
			i.setItem((y -1) * 9 + x -1, item);	
		}
		public void drawBackButton(int wz){
			drawButton(wz, 399, 1, 0, "§a§l[返回上页]");
		}
		public void drawBackButton(int x,int y){
			drawButton((y -1) * 9 + x -1, 399, 1, 0, "§a§l[返回上页]");
		}
		public void drawCloseButton(int wz){
			drawButton(wz, 399, 1, 0, "§a§l[关闭菜单]");
		}
		public void drawCloseButton(int x,int y){
			drawButton((y -1) * 9 + x -1, 399, 1, 0, "§a§l[关闭菜单]");
		}
		public static Gui getGui(Inventory inv){
			return invtab.get(inv);
		}
		public static boolean isGui(Inventory inv){
			return invtab.containsKey(inv);
		}
		public Gui getThis(){
			return this;
		}
		/**
		 * 当前gui主人
		 * */
		public Player getP() {
			return p;
		}
		public void setP(Player p) {
			this.p = p;
		}
		public String getPlayerName() {
			return playerName;
		}
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}
	}
	/**
	 * Gui监听类
	 * */
	public abstract static class Guis implements Listener{
		private static Map<Player,Gui> windowlist = new HashMap<Player,Gui>();
		public static void openWindow(Player player, Gui gui){
			Gui guik = getWindow(player,gui);
			guik.updateWindow();
			guik.open();
		}
		public static Gui getWindow(Player player, Gui gui){
			windowlist.put(player, gui);
			return windowlist.get(player);
		}
		public static void closeWindow(Player player){
			player.closeInventory();
		}
		public static void closeCraftWindow(Player player){
			player.closeInventory();
		}
		@EventHandler(priority=EventPriority.HIGHEST)
		public void test(InventoryClickEvent e){
			if(e.getSlot() == -999){
				return;
			}
			if(Gui.isGui(e.getInventory())){
				boolean iscan = true;
				Gui gui = Gui.getGui(e.getInventory());
				int rslot = e.getRawSlot();
				if(gui.getP() == null){
					Player p = (Player) e.getWhoClicked();
					if(rslot < gui.getLevel()){
						if(e.isLeftClick()){
							iscan = !gui.leftPlayerClickEvent(p,rslot, e);
						}else if(e.isRightClick()){
							iscan = !gui.rightPlayerClickEvent(p,rslot, e);
						}
						
					}else {
						if(e.isLeftClick()){
							iscan = !gui.leftPlayerClickPlayerEvent(p,rslot, e);
						}else if(e.isRightClick()){
							iscan = !gui.rightPlayerClickPlayerEvent(p,rslot, e);
						}
					}
				}else {
					if(rslot < gui.getLevel()){
						if(e.isShiftClick()){
							if(e.isLeftClick()){
								iscan = !gui.leftShiftClickEvent(rslot, e);
							}else if(e.isRightClick()){
								iscan = !gui.rightShiftClickEvent(rslot, e);
							}
						}else{
							if(e.isLeftClick()){
								iscan = !gui.leftClickEvent(rslot, e);
							}else if(e.isRightClick()){
								iscan = !gui.rightClickEvent(rslot, e);
							}
						}
					}else {
						if(e.isShiftClick()){
							if(e.isLeftClick()){
								iscan = !gui.leftShiftClickPlayerEvent(rslot, e);
							}else if(e.isRightClick()){
								iscan = !gui.rightShiftClickPlayerEvent(rslot, e);
							}
						}else{
							if(e.isLeftClick()){
								iscan = !gui.leftClickPlayerEvent(rslot, e);
							}else if(e.isRightClick()){
								iscan = !gui.rightClickPlayerEvent(rslot, e);
							}
						}
					}		
				}
				e.setCancelled(iscan);
			}
		}
		@EventHandler(priority=EventPriority.HIGHEST)
		public void test(InventoryCloseEvent e){
			if(Gui.isGui(e.getInventory())){
				Gui gui = Gui.getGui(e.getInventory());
				if(gui.closeEvent()){
					windowlist.remove(gui.getPlayerName());
				}
			}
		}
	}
	/**
	 * ASX事件接口
	 * */
	private static interface ASXIEvent{
		/**
		 * 提供事件触发和处理
		 * 使用Core.callEvent(ASXEvent)触发事件
		 * 
		 * */
		public void call();
	}
	/**
	 * ASX事件
	 * */
	public static abstract class ASXEvent extends Event implements ASXIEvent{
		private static final HandlerList handlers = new HandlerList();
		private boolean cancelled;
		
		@Override
		public HandlerList getHandlers() {
			
			return handlers;
		}
		public boolean isCancelled() {
			return this.cancelled;
		}

		public void setCancelled(boolean cancel) {
			this.cancelled = cancel;
		}

		public static HandlerList getHandlerList() {
			return handlers;
		}
	}
	/**
	 * 提交ASX事件包括后续执行
	 * */
	public final static void callEvent(ASXEvent aEvent){
		pluginManager.callEvent(aEvent);
		aEvent.call();
	}
}
