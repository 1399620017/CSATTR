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
	 * ���ʵ��
	 * */
	public static JavaPlugin plugin;
	/**
	 * ����˺�̨����
	 * */
	public static ConsoleCommandSender serverSender;
	/**
	 * �����Ϣʵ��
	 * */
	public static PluginManager pluginManager;
	/**
	 * ���������
	 * */
	public static String pluginName;
	/**
	 * ��������
	 * */
	public static String serverName;
	/**
	 * ������
	 * */
	public static String cmdName;
	
	/**
	 * BarAPI ����
	 * */
	public static boolean barApi = false;

	/**
	 * Core��ܽӿ� �������ʵ������ӿڼ���
	 * */
	public static abstract class CorePlugin extends JavaPlugin {

		/**
		 * ������ ������ʾ ���ʵ������ ���Ѿ���ȡ
		 * */
		public abstract void start();

		/**
		 * ����������� ���ڷ�����Ϣ
		 * */
		public abstract String pluginName();

		/**
		 * ����������� ���ڷ��͹���
		 * */
		public abstract String serverName();

		/**
		 * ������������� ���ô�б��
		 * */
		public abstract String pluginCommand();

		/**
		 * �¼�ע�� regListener(Listener listener)
		 * */
		public abstract void listenter();
		
		/**
		 * ע������ new E() extend Command ;
		 * */
		public abstract void command();

		public final void onEnable() {
			pluginName = "��a[" + pluginName() + "]��e";
			serverName = "��b[" + serverName() + "����]��e";
			cmdName = "/" + pluginCommand();
			plugin = this;
			pluginManager = getServer().getPluginManager();
			serverSender = getServer().getConsoleSender();
			start();
			Msg.sendConPlugin("��������ɹ�");
			listenter();
			Msg.sendConPlugin("ע������ɹ�");
			command();
			regListener(new Core.CmdListener());
			Msg.sendConPlugin("ע��Core�������ɹ�");
			
		}

		public final void onDisable() {
			
		}

		/**
		 * ������ע��
		 * */
		public static CorePlugin regListener(Listener listener) {
			pluginManager.registerEvents(listener, plugin);
			return null;
		}
	}

	/**
	 * ��Ϣ����
	 * */
	public final static class Msg {

		/**
		 * ��ȡ�������
		 * */
		public static String getPluginName() {
			return pluginName;
		}

		/**
		 * ��ȡ����������
		 * */
		public static String getServerName() {
			return serverName;
		}
		/**
		 * ���͸�����Ϣ���������
		 * */
		public static Msg sendTitleUpdataStart() {
			for(Player p : Bukkit.getOnlinePlayers()){
				if (p != null) {
					if(p.isOnline()){
						Title.sendTitle(p.getPlayer(), 20, 60, 20, 
								"��c���������ڸ��£�", "��e�����쳣,��ȴ����½���,���ɻָ�����!");
					}
				}
			}
			return null;
		}
		/**
		 * ���͸��������Ϣ���������
		 * */
		public static Msg sendTitleUpdata() {
			for(Player p : Bukkit.getOnlinePlayers()){
				if (p != null) {
					if(p.isOnline()){
						Title.sendTitle(p.getPlayer(), 20, 60, 20, 
								"��c������������ϣ�", "��e�����쳣,���л���������,���ɻָ�����!");
					}
				}
			}
			return null;
		}
		/**
		 * ���ʹ���Ļ��Ϣ�����
		 * */
		public static Msg sendTitleMsgOcUpLevel(Player player) {
			if (player != null) {
				Title.sendTitle(player, 20, 30, 20, "��aְҵ�ȼ�����", "��e������/asx ���� /sx v�鿴ְҵ��Ϣ��");
			}
			return null;
		}
		
		/**
		 * ���ʹ���Ļ��Ϣ�����
		 * */
		public static Msg sendTitleMsg(Player player, String msg1, String msg2) {
			if (player != null) {
				Title.sendTitle(player, 20, 50, 20, msg1, msg2);
			}
			return null;
		}
		
		/**
		 * ��*���Ͱ�ɫϵͳ��ʾ
		 * */
		public static Msg sendMsg(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("��8[��a!��8] ��f" + msg);
			}
			return null;
		}

		/**
		 * ��*������ɫϵͳ��ʾ
		 * */
		public static Msg sendMsgTrue(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("��8[��a!��8] ��a��n" + msg);
			}
			return null;
		}

		/**
		 * ��*���ͺ�ɫϵͳ��ʾ
		 * */
		public static Msg sendMsgFalse(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("��8[��a!��8] ��c��n" + msg);
			}
			return null;
		}

		/**
		 * ��*���Ͳ�����Ϣ
		 * */
		public static Msg sendCs(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("[����]" + msg);
			}
			return null;
		}

		/**
		 * ��*������ͨ��Ϣ
		 * */
		public static Msg sendMessage(CommandSender sender, String msg) {
			if (sender != null) {
				sender.sendMessage("��a" + msg);
			}
			return null;
		}

		/**
		 * �Բ�����巢����Ϣ
		 * */
		public static Msg sendPluginMsg(CommandSender sender, String msg) {
			sender.sendMessage(pluginName + msg);
			return null;
		}
		
		/**
		 * �Բ�����巢�ͺ�̨��Ϣ
		 * */
		public static Msg sendConPlugin(String msg) {
			Core.serverSender.sendMessage(pluginName + msg);
			return null;
		}

		/**
		 * �Է��������巢�͹���
		 * */
		public static Msg sendServerBroad(String msg) {
			Bukkit.broadcastMessage(serverName + msg);
			return null;
		}

		/**
		 * �Բ�����巢�͹���
		 * */
		public static Msg sendPluginBroad(String msg) {
			Bukkit.broadcastMessage(pluginName + msg);
			return null;
		}

		/**
		 * ����̨������ɫ��Ϣ
		 * */
		public static Msg sendConMsgTrue(String msg) {
			serverSender.sendMessage("��8[��a!��8] ��a" + msg);
			return null;
		}

		/**
		 * ����̨���ͺ�ɫ��Ϣ
		 * */
		public static Msg sendConMsgFalse(String msg) {
			serverSender.sendMessage("��8[��a!��8] ��c" + msg);
			return null;
		}
	}
	/**
	 * Title��
	 * */
	public static class Title {

		public void Server_broadcast(Player player, Integer fadeIn,
				Integer stay, Integer fadeOut, String title, String subtitle) {
			/*
			 * Bukkit.broadcastMessage(ChatColor.DARK_RED + broadcast); ����ȫ���㲥��Ϣ
			 * Bukkit.getPlayer("name") ���String name Ftime ����ʱ�� TimeS ͣ������Ļ��ʱ��
			 * FsTime ����ʱ�� title Ҫ���͵�title subtitle Ҫ���͵���title
			 * 
			 * Player player, Integer fadeIn, Integer stay, Integer fadeOut,
			 * String title, String subtitle
			 */
			sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
		}

		public static void sendTitle(Player player, Integer fadeIn,
				Integer stay, Integer fadeOut, String title, String subtitle) {
			try {
				if (title != null) { // Ҫ���͵�title
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

				if (subtitle != null) { // Ҫ���͵���title
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
	 * ����ӿ��¼�
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
				Msg.sendMsgTrue(ePlayer, "������������:");
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
	 * ���������
	 * */
	public abstract static class Command {
		private static Map<String, Command> commandList = new HashMap<String, Command>();

		/**
		 * �������
		 * */
		public final static void addCommand(Command command) {
			commandList.put(command.getName(), command);
		}

		/**
		 * ��ȡ�����б�
		 * */
		public final static Map<String, Command> getCommands() {
			return commandList;
		}

		/**
		 * ���Ϳ��������б�
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
		 * ���͵�ǰ����ʹ�÷�ʽ
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
		 * ��������
		 * */
		protected String name;
		/**
		 * �����������
		 * */
		protected int len;
		/**
		 * ����ʹ�÷���
		 * */
		protected String usage;
		/**
		 * �������
		 * */
		protected String desc;
		
		/**
		 * �������
		 * */
		protected boolean op;

		/**
		 * ���������� name �����ַ��� len �����������
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
		 * ��ȡ������
		 * */
		public final String getName() {
			return name;
		}

		/**
		 * ��ȡ�������
		 * */
		public final String getDesc() {
			return desc;
		}

		/**
		 * ��ȡ����ʹ�÷���
		 * */
		public final String getUsage() {
			return usage;
		}
		
		/**
		 * ��ȡ�Ƿ�op����
		 * */
		public final boolean getOp() {
			return op;
		}

		/**
		 * ��ȡ�����������
		 * */
		public final int getLen() {
			return len;
		}

		/**
		 * ���ʹ������ args �������  ����falseΪ����ʹ�÷�ʽ����
		 * */
		public abstract boolean send(Player player, String[] args);
	}
	
	/**
	 * ���Core��
	 * */
	public final static class CorePlayer {
		
		/**
		 * �ж�����Ƿ�����
		 * */
		public static boolean isOnline(Player p){
			if(p != null){
				return p.isOnline();
			}
			return false;
		}
		
		/**
		 * �ж�����Ƿ�����
		 * */
		public static boolean isOnline(String name){
			Player p = Bukkit.getPlayer(name);
			return isOnline(p);
		}
		
		/**
		 * ��������������ְ���ָ���ַ�����Ʒn��
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
	 * �����ļ���
	 * */
	public static abstract class Config {
		private Filep filep;
		/**
		 * �ļ����� 
		 * */
		public YamlConfiguration config;
		private File configdir;
		/**
		 * �����ļ��� filep ���ļ����� name ���ļ��� ���ô���׺ 
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
		 * ʵ��Ĭ�ϱ����ʽ
		 * */
		public abstract void defaultConfig();
		/**
		 * ʵ�ֱ����ʽ
		 * */
		public abstract void saveConfig();
		/**
		 * �ж��Ƿ����������
		 * */
		public boolean hasConfig(){
			// �ж��Ƿ�������ļ�û���򴴽�������
			if(configdir.exists()){
				get();
				return true;
			}else{
				// �������򴴽��ļ�
				try
				{
					configdir.createNewFile();
				}catch (IOException localIOException) {
					
				}	
				return false;
			}
		}
		/**
		 * ��ȡ�����ļ� ���Ȳ鿴�Ƿ���������ļ�
		 * */
		public YamlConfiguration get(){
			config = Filep.getConfig(configdir);
			return config;
		}
		/**
		 * ɾ�������ļ�����
		 * */
		public void remove(){
			if(configdir.exists()){
				Filep.removeFile(configdir);
			}
		}
		/**
		 * ���������ļ�
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
	 * ���������ļ�
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
	 * �����ļ�����
	 * */
	public static class Filep {
		public static Map<String, Filep> wjjlist = new HashMap<String, Filep>();
		/**
		 * ��ȡ�����ļ�������ļ���
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
		 * ��ȡ�ļ��е�ַ
		 * */
		public String getDirStr() {
			return filedir.toString();
		}
		/**
		 * �Ƴ�ĳ���ļ�
		 * */
		public static boolean removeFile(File file){
			return file.delete();
		}
		/**
		 * ��ȡ���� YamlConfiguration ʵ��
		 * */
		public static YamlConfiguration getConfig(File file){
			/*
			 * ��ȡ�����ļ�
			 * ���� file ����·����
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
		 * ��ȡ��ǰСʱ�ַ���
		 * */
		public static String getHourString(){
			Date date = new Date();
			return hour.format(date);
		}
		/**
		 * ��ȡ��ǰʱ�����
		 * */
		public static Date getDate(){
			return new Date();
		}
		/**
		 * ��ȡ��ǰ�����ַ���
		 * */
		public static String getDateString(){
			Date date = new Date();
			return format.format(date);
		}
		/**
		 * ��ȡ��ǰʱ���
		 * */
		public static long getTime(){
			return new Date().getTime();
		}
	}
	
	/**
	 * Cui��
	 * */
	public static abstract class Gui {	
		protected static Map<Inventory,Gui> invtab = new HashMap<Inventory, Gui>();
		/**
		 * gui���õı���ʵ��
		 * */
		protected Inventory i;
		/**
		 * gui��ӵ���� ���
		 * */
		private Player p;	
		protected int index = 0;	
		/**
		 * gui�Ĵ�С�ȼ�
		 * */
		protected int level;	
		/**
		 * gui������������
		 * */
		private String playerName;
		/**
		 * ����gui����
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
		 * ��ȡgui��С�ȼ�
		 * */
		public int getLevel(){
			return level;
		}
		/**
		 * ���ڸ��·��� ÿ�δ򿪴��ڶ������
		 * */
		public abstract void updateWindow();	
		/**
		 * ���ڻ��Ʒ��� ���ڻ��Ʊ߿�Ȳ�����Ŀ ֻ�ڴ��ڴ��������һ��
		 * */
		public abstract void drawWindow();
		/**
		 * �����������������¼� ����true�������¼�
		 * */
		public abstract boolean leftClickEvent(int solt, InventoryClickEvent e);	
		/**
		 * �����������Ҽ�����¼� ����true�������¼�
		 * */
		public abstract boolean rightClickEvent(int solt, InventoryClickEvent e);
		/**
		 * ����������SHIFT���������¼� ����true�������¼�
		 * */
		public abstract boolean leftShiftClickEvent(int solt, InventoryClickEvent e);	
		/**
		 * ����������SHIFT���Ҽ�����¼� ����true�������¼�
		 * */
		public abstract boolean rightShiftClickEvent(int solt, InventoryClickEvent e);
		/**
		 * ���������������������ڵı��������¼� ����true�������¼�
		 * */
		public abstract boolean leftClickPlayerEvent(int solt, InventoryClickEvent e);	
		/**
		 * �����������Ҽ���������ڵı��������¼� ����true�������¼�
		 * */
		public abstract boolean rightClickPlayerEvent(int solt, InventoryClickEvent e);
		/**
		 * ����������SHIFT�������������ڵı��������¼� ����true�������¼�
		 * */
		public abstract boolean leftShiftClickPlayerEvent(int solt, InventoryClickEvent e);	
		/**
		 * ����������SHIFT���Ҽ���������ڵı��������¼� ����true�������¼�
		 * */
		public abstract boolean rightShiftClickPlayerEvent(int solt, InventoryClickEvent e);
		/**
		 * ������������������¼� ����true�������¼�
		 * */
		public abstract boolean leftPlayerClickEvent(Player player, int solt, InventoryClickEvent e);	
		/**
		 * ������������Ҽ�����¼� ����true�������¼�
		 * */
		public abstract boolean rightPlayerClickEvent(Player player, int solt, InventoryClickEvent e);
		/**
		 * ����������������������������¼� ����true�������¼�
		 * */
		public abstract boolean leftPlayerClickPlayerEvent(Player player, int solt, InventoryClickEvent e);	
		/**
		 * ������������Ҽ���������������¼� ����true�������¼�
		 * */
		public abstract boolean rightPlayerClickPlayerEvent(Player player, int solt, InventoryClickEvent e);
		/**
		 * ���ڹر��¼� ����true������δ���ʵ�� ����true�������¼�
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
			drawButton(wz, 399, 1, 0, "��a��l[������ҳ]");
		}
		public void drawBackButton(int x,int y){
			drawButton((y -1) * 9 + x -1, 399, 1, 0, "��a��l[������ҳ]");
		}
		public void drawCloseButton(int wz){
			drawButton(wz, 399, 1, 0, "��a��l[�رղ˵�]");
		}
		public void drawCloseButton(int x,int y){
			drawButton((y -1) * 9 + x -1, 399, 1, 0, "��a��l[�رղ˵�]");
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
		 * ��ǰgui����
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
	 * Gui������
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
	 * ASX�¼��ӿ�
	 * */
	private static interface ASXIEvent{
		/**
		 * �ṩ�¼������ʹ���
		 * ʹ��Core.callEvent(ASXEvent)�����¼�
		 * 
		 * */
		public void call();
	}
	/**
	 * ASX�¼�
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
	 * �ύASX�¼���������ִ��
	 * */
	public final static void callEvent(ASXEvent aEvent){
		pluginManager.callEvent(aEvent);
		aEvent.call();
	}
}
