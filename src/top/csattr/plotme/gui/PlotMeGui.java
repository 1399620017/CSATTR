package top.csattr.plotme.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMapInfo;
import com.worldcretornica.plotme.PlotMe;

import lk.vexview.gui.components.ButtonFunction;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexText;
import top.csattr.vexview.gui.BaseGui;

public class PlotMeGui extends BaseGui {
	private final ChatColor RESET = ChatColor.RESET;
	private final ChatColor AQUA = ChatColor.AQUA;
	private final ChatColor GREEN = ChatColor.GREEN;
	private int textx = 30; // 正文x坐标
	private int texty = 30; // 正文y坐标
	private int fly = 15; // 分栏y坐标
	private Player player;
	private int id = 1;
	
	public PlotMeGui(final Player player) {
		super(360, 300);
		setTitle("§c地皮系统");
		this.player = player;
		int ops = PlotManager.getNbOwnedPlot(player);
		if(ops == 0){
			addText("你还没有领地！", "");
			if(PlotManager.isPlotWorld(player)){
				addButton("plotget", "点击领取地皮", new ButtonFunction() {
					
					@Override
					public void run(Player arg0) {
						player.closeInventory();
						player.chat("/plot auto");
					}
				});
			}else{
				addText(1, "你还没有领地！", "§c在主城才可使用地皮系统！");
				addButton("plotget", "点击进入主城", new ButtonFunction() {
					
					@Override
					public void run(Player arg0) {
						player.closeInventory();
						player.chat("/spawn");
					}
				});
			}
		}else{
			String name = player.getName();
			int index = 1;
			for(Plot plot : PlotManager.getPlots(player).values()){
				if(plot.expireddate != null && plot.owner.equalsIgnoreCase(name)){
					addText("领地" + index++ + " 所有者:" + plot.owner + " 位置:" + plot.id + 
							" 有效期:" + plot.expireddate.toString());
				}
			}
		}
	}


	public void addButton(String buttonId, String name, ButtonFunction bf) {
		this.addComponent(new VexButton(buttonId, name, "[local]button.png",
				"[local]button_.png", x / 2 - 38 , y / 2 + 10, 75, 30, bf));
	}
	
	public void addText(String msg) {
		List<String> text = new ArrayList<>();
		text.add(msg);
		int x = textx;
		int y = texty + id++ * fly;
		this.addComponent(new VexText(x, y, text, 1.1D));
	}
	public void addText(String msg,String wz) {
		List<String> text = new ArrayList<>();
		text.add(msg);
		this.addComponent(new VexText(x / 2 - 20, y / 2 - 30, text, 1.1D));
	}
	public void addText(int wz, String... msg) {
		this.addComponent(new VexText(x / 2 - 20, y / 2 - 30, Arrays.asList(msg), 1.1D));
	}

	protected boolean showHelp() {
		boolean ecoon = PlotManager.isEconomyEnabled(this.player);

		List<String> allowed_commands = new ArrayList<String>();

		allowed_commands.add("limit");
		if (PlotMe.cPerms(this.player, "PlotMe.use.claim")) {
			allowed_commands.add("claim");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.claim.other")) {
			allowed_commands.add("claim.other");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.auto")) {
			allowed_commands.add("auto");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.home")) {
			allowed_commands.add("home");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.home.other")) {
			allowed_commands.add("home.other");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.info")) {
			allowed_commands.add("info");
			allowed_commands.add("biomeinfo");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.comment")) {
			allowed_commands.add("comment");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.comments")) {
			allowed_commands.add("comments");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.list")) {
			allowed_commands.add("list");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.use.biome")) {
			allowed_commands.add("biome");
			allowed_commands.add("biomelist");
		}
		if ((PlotMe.cPerms(this.player, "PlotMe.use.done"))
				|| (PlotMe.cPerms(this.player, "PlotMe.admin.done"))) {
			allowed_commands.add("done");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.done")) {
			allowed_commands.add("donelist");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.tp")) {
			allowed_commands.add("tp");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.id")) {
			allowed_commands.add("id");
		}
		if ((PlotMe.cPerms(this.player, "PlotMe.use.clear"))
				|| (PlotMe.cPerms(this.player, "PlotMe.admin.clear"))) {
			allowed_commands.add("clear");
		}
		if ((PlotMe.cPerms(this.player, "PlotMe.admin.dispose"))
				|| (PlotMe.cPerms(this.player, "PlotMe.use.dispose"))) {
			allowed_commands.add("dispose");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.reset")) {
			allowed_commands.add("reset");
		}
		if ((PlotMe.cPerms(this.player, "PlotMe.use.add"))
				|| (PlotMe.cPerms(this.player, "PlotMe.admin.add"))) {
			allowed_commands.add("add");
		}
		if ((PlotMe.cPerms(this.player, "PlotMe.use.remove"))
				|| (PlotMe.cPerms(this.player, "PlotMe.admin.remove"))) {
			allowed_commands.add("remove");
		}
		if (PlotMe.allowToDeny.booleanValue()) {
			if ((PlotMe.cPerms(this.player, "PlotMe.use.deny"))
					|| (PlotMe.cPerms(this.player, "PlotMe.admin.deny"))) {
				allowed_commands.add("deny");
			}
			if ((PlotMe.cPerms(this.player, "PlotMe.use.undeny"))
					|| (PlotMe.cPerms(this.player, "PlotMe.admin.undeny"))) {
				allowed_commands.add("undeny");
			}
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.setowner")) {
			allowed_commands.add("setowner");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.move")) {
			allowed_commands.add("move");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.weanywhere")) {
			allowed_commands.add("weanywhere");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.reload")) {
			allowed_commands.add("reload");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.list")) {
			allowed_commands.add("listother");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.expired")) {
			allowed_commands.add("expired");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.addtime")) {
			allowed_commands.add("addtime");
		}
		if (PlotMe.cPerms(this.player, "PlotMe.admin.resetexpired")) {
			allowed_commands.add("resetexpired");
		}
		PlotMapInfo pmi = PlotManager.getMap(this.player);
		if ((PlotManager.isPlotWorld(this.player)) && (ecoon)) {
			if (PlotMe.cPerms(this.player, "PlotMe.use.buy")) {
				allowed_commands.add("buy");
			}
			if (PlotMe.cPerms(this.player, "PlotMe.use.sell")) {
				allowed_commands.add("sell");
				if (pmi.CanSellToBank) {
					allowed_commands.add("sellbank");
				}
			}
			if (PlotMe.cPerms(this.player, "PlotMe.use.auction")) {
				allowed_commands.add("auction");
			}
			if (PlotMe.cPerms(this.player, "PlotMe.use.bid")) {
				allowed_commands.add("bid");
			}
		}
		// 测试代码

		// 绘制开始
		
		for (int ctr = 0; ctr < allowed_commands.size(); ctr++) {
			String allowedcmd = (String) allowed_commands.get(ctr);
			if (allowedcmd.equalsIgnoreCase("limit")) {
				if ((PlotManager.isPlotWorld(this.player))
						|| (PlotMe.allowWorldTeleport.booleanValue())) {
					World w = null;
					if (PlotManager.isPlotWorld(this.player)) {
						w = this.player.getWorld();
					} else if (PlotMe.allowWorldTeleport.booleanValue()) {
						w = PlotManager.getFirstWorld();
					}
					int maxplots = PlotMe.getPlotLimit(this.player);
					int ownedplots = PlotManager.getNbOwnedPlot(this.player, w);
					if (maxplots == -1) {
						addText(ChatColor.GREEN
								+ C("HelpYourPlotLimitWorld") + " : "
								+ ChatColor.AQUA + ownedplots + ChatColor.GREEN
								+ " " + C("HelpUsedOf") + " " + ChatColor.AQUA
								+ C("WordInfinite"));
					} else {
						addText(ChatColor.GREEN
								+ C("HelpYourPlotLimitWorld") + " : "
								+ ChatColor.AQUA + ownedplots + ChatColor.GREEN
								+ " " + C("HelpUsedOf") + " " + ChatColor.AQUA
								+ maxplots);
					}
				} else {
					addText(ChatColor.GREEN + C("HelpYourPlotLimitWorld")
							+ " : " + ChatColor.AQUA + C("MsgNotPlotWorld"));
				}
			} else if (allowedcmd.equalsIgnoreCase("claim")) {
				if ((ecoon) && (pmi != null) && (pmi.ClaimPrice != 0.0D)) {
					addText(ChatColor.GREEN + " /plotme " + C("CommandClaim") + " " + 
							ChatColor.AQUA + " " + C("HelpClaim") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.ClaimPrice);
				} else {
					addText(ChatColor.GREEN + " /plotme " + C("CommandClaim") + " " + 
							ChatColor.AQUA + " " + C("HelpClaim"));
				}
			} else if (allowedcmd.equalsIgnoreCase("claim.other")) {
				if ((ecoon) && (pmi != null) && (pmi.ClaimPrice != 0.0D)) {
					addText(ChatColor.GREEN + " /plotme " + C("CommandClaim")
							+ " <" + C("WordPlayer") + "> " + 
							ChatColor.AQUA + " " + C("HelpClaimOther")
							+ " " + C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.ClaimPrice);
				} else {
					addText(ChatColor.GREEN + " /plotme " + C("CommandClaim")
							+ " <" + C("WordPlayer") + "> " + 
							ChatColor.AQUA + " " + C("HelpClaimOther"));
				}
			} else if (allowedcmd.equalsIgnoreCase("auto")) {
				String str = "";
				if (PlotMe.allowWorldTeleport.booleanValue()) {
					str += ChatColor.GREEN + " /plotme "
							+ C("CommandAuto") + " [" + C("WordWorld") + "] ";
				} else {
					str += ChatColor.GREEN + " /plotme "
							+ C("CommandAuto") + " ";
				}
				if ((ecoon) && (pmi != null) && (pmi.ClaimPrice != 0.0D)) {
					str += ChatColor.AQUA + " " + C("HelpAuto") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.ClaimPrice;
				} else {
					str += ChatColor.AQUA + " " + C("HelpAuto");
				}
				addText(str);
			} else if (allowedcmd.equalsIgnoreCase("home")) {
				String str = "";
				if (PlotMe.allowWorldTeleport.booleanValue()) {
					str += ChatColor.GREEN + " /plotme "
							+ C("CommandHome") + "[:#] [" + C("WordWorld")
							+ "] ";
				} else {
					str +=  ChatColor.GREEN + " /plotme "
							+ C("CommandHome") + "[:#] ";
				}
				if ((ecoon) && (pmi != null) && (pmi.PlotHomePrice != 0.0D)) {
					str += ChatColor.AQUA + " " + C("HelpHome") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.PlotHomePrice;
				} else {
					str += ChatColor.AQUA + " " + C("HelpHome");
				}
				addText(str);
			} else if (allowedcmd.equalsIgnoreCase("home.other")) {
				String str = "";
				if (PlotMe.allowWorldTeleport.booleanValue()) {
					str +=  ChatColor.GREEN + " /plotme "
							+ C("CommandHome") + "[:#] <" + C("WordPlayer")
							+ "> [" + C("WordWorld") + "] ";
				} else {
					str += ChatColor.GREEN + " /plotme "
							+ C("CommandHome") + "[:#] <" + C("WordPlayer")
							+ "> ";
				}
				if ((ecoon) && (pmi != null) && (pmi.PlotHomePrice != 0.0D)) {
					str += ChatColor.AQUA + " " + C("HelpHomeOther")
							+ " " + C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.PlotHomePrice;
				} else {
					str += ChatColor.AQUA + " " + C("HelpHomeOther");
				}
				addText(str);
			} else if (allowedcmd.equalsIgnoreCase("info")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandInfo") + " " + 
						ChatColor.AQUA + " " + C("HelpInfo"));
			} else if (allowedcmd.equalsIgnoreCase("comment")) {
				if ((ecoon) && (pmi != null) && (pmi.AddCommentPrice != 0.0D)) {
					addText(ChatColor.GREEN + " /plotme "
							+ C("CommandComment") + " <" + C("WordComment") + "> " +
							ChatColor.AQUA + " " + C("HelpComment") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.AddCommentPrice);
				} else {
					addText(ChatColor.GREEN + " /plotme "
							+ C("CommandComment") + " <" + C("WordComment") + "> " +
							ChatColor.AQUA + " " + C("HelpComment"));
				}
			} else if (allowedcmd.equalsIgnoreCase("comments")) {
				addText(ChatColor.GREEN + " /plotme "
						+ C("CommandComments") + " " + 
						ChatColor.AQUA + " " + C("HelpComments"));
			} else if (allowedcmd.equalsIgnoreCase("list")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandList") + " " +
						ChatColor.AQUA + " " + C("HelpList"));
			} else if (allowedcmd.equalsIgnoreCase("listother")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandList")
						+ " <" + C("WordPlayer") + "> " +
						ChatColor.AQUA + " " + C("HelpListOther"));
			} else if (allowedcmd.equalsIgnoreCase("biomeinfo")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandBiome") + " " +
						ChatColor.AQUA + " " + C("HelpBiomeInfo"));
			} else if (allowedcmd.equalsIgnoreCase("biome")) {
				if ((ecoon) && (pmi != null) && (pmi.BiomeChangePrice != 0.0D)) {
					addText(ChatColor.GREEN + " /plotme " + C("CommandBiome")
							+ " <" + C("WordBiome") + "> " +
							ChatColor.AQUA + " " + C("HelpBiome") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.BiomeChangePrice);
				} else {
					addText(ChatColor.GREEN + " /plotme " + C("CommandBiome")
							+ " <" + C("WordBiome") + "> " +
							ChatColor.AQUA + " " + C("HelpBiome"));
				}
			} else if (allowedcmd.equalsIgnoreCase("biomelist")) {
				addText(ChatColor.GREEN + " /plotme "
						+ C("CommandBiomelist") + " " + 
						ChatColor.AQUA + " " + C("HelpBiomeList"));
			} else if (allowedcmd.equalsIgnoreCase("done")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandDone") + " " +
						ChatColor.AQUA + " " + C("HelpDone"));
			} else if (allowedcmd.equalsIgnoreCase("tp")) {
				if (PlotMe.allowWorldTeleport.booleanValue()) {
					addText(ChatColor.GREEN + " /plotme "
							+ C("CommandTp") + " <" + C("WordId") + "> ["
							+ C("WordWorld") + "] " + ChatColor.AQUA + " " + C("HelpTp"));
				} else {
					addText(ChatColor.GREEN + " /plotme "
							+ C("CommandTp") + " <" + C("WordId") + "> " + 
							ChatColor.AQUA + " " + C("HelpTp"));
				}
			} else if (allowedcmd.equalsIgnoreCase("id")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandId") + " " +
						ChatColor.AQUA + " " + C("HelpId"));
			} else if (allowedcmd.equalsIgnoreCase("clear")) {
				if ((ecoon) && (pmi != null) && (pmi.ClearPrice != 0.0D)) {
					addText(ChatColor.GREEN + " /plotme " + C("CommandClear") + " " +
							ChatColor.AQUA + " " + C("HelpId") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ pmi.ClearPrice);
				} else {
					addText(ChatColor.GREEN + " /plotme " + C("CommandClear") + " " +
							ChatColor.AQUA + " " + C("HelpClear"));
				}
			} else if (allowedcmd.equalsIgnoreCase("reset")) {
				addText(ChatColor.GREEN + " /plotme " + C("CommandReset") + 
						ChatColor.AQUA + " " + C("HelpReset"));
			} else if (allowedcmd.equalsIgnoreCase("add")) {
				if ((ecoon) && (pmi != null) && (pmi.AddPlayerPrice != 0.0D)) {
					addText(ChatColor.GREEN + " /plotme " + C("CommandAdd")
							+ " <" + C("WordPlayer") + "> " +
							ChatColor.AQUA + " " + C("HelpAdd") + " "
							+ C("WordPrice") + " : " + ChatColor.RESET
							+ round(pmi.AddPlayerPrice));
				} else {
					addText(ChatColor.GREEN + " /plotme " + C("CommandAdd")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpAdd"));
				}
			} else if (allowedcmd.equalsIgnoreCase("deny")) {
				if ((ecoon) && (pmi != null) && (pmi.DenyPlayerPrice != 0.0D)) {
					addText(this.GREEN + " /plotme " + C("CommandDeny")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpDeny") + " "
							+ C("WordPrice") + " : " + this.RESET
							+ round(pmi.DenyPlayerPrice));
				} else {
					addText(this.GREEN + " /plotme " + C("CommandDeny")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpDeny"));
				}
			} else if (allowedcmd.equalsIgnoreCase("remove")) {
				if ((ecoon) && (pmi != null) && (pmi.RemovePlayerPrice != 0.0D)) {
					addText(this.GREEN + " /plotme " + C("CommandRemove")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpRemove") + " "
							+ C("WordPrice") + " : " + this.RESET
							+ round(pmi.RemovePlayerPrice));
				} else {
					addText(this.GREEN + " /plotme " + C("CommandRemove")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpRemove"));
				}
			} else if (allowedcmd.equalsIgnoreCase("undeny")) {
				if ((ecoon) && (pmi != null) && (pmi.UndenyPlayerPrice != 0.0D)) {
					addText(this.GREEN + " /plotme " + C("CommandUndeny")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpUndeny") + " "
							+ C("WordPrice") + " : " + this.RESET
							+ round(pmi.UndenyPlayerPrice));
				} else {
					addText(this.GREEN + " /plotme " + C("CommandUndeny")
							+ " <" + C("WordPlayer") + "> " +
							this.AQUA + " " + C("HelpUndeny"));
				}
			} else if (allowedcmd.equalsIgnoreCase("setowner")) {
				addText(this.GREEN + " /plotme " + C("CommandSetowner")
						+ " <" + C("WordPlayer") + "> " +
						this.AQUA + " " + C("HelpSetowner"));
			} else if (allowedcmd.equalsIgnoreCase("move")) {
				addText(this.GREEN + " /plotme " + C("CommandMove")
						+ " <" + C("WordIdFrom") + "> <" + C("WordIdTo") + "> " + 
						this.AQUA + " " + C("HelpMove"));
			} else if (allowedcmd.equalsIgnoreCase("weanywhere")) {
				addText(this.GREEN + " /plotme " + C("CommandWEAnywhere") + " " +
						this.AQUA + " " + C("HelpWEAnywhere"));
			} else if (allowedcmd.equalsIgnoreCase("expired")) {
				addText(this.GREEN + " /plotme " + C("CommandExpired")
						+ " [page] " + this.AQUA + " " + C("HelpExpired"));
			} else if (allowedcmd.equalsIgnoreCase("donelist")) {
				addText(this.GREEN + " /plotme " + C("CommandDoneList")
						+ " [page] " + this.AQUA + " " + C("HelpDoneList"));
			} else if (allowedcmd.equalsIgnoreCase("addtime")) {
				int days = pmi == null ? 0 : pmi.DaysToExpiration;
				if (days == 0) {
					addText(this.GREEN + " /plotme " + C("CommandAddtime") + " " +
							this.AQUA + " " + C("HelpAddTime1") + " "
							+ this.RESET + C("WordNever"));
				} else {
					addText(this.GREEN + " /plotme " + C("CommandAddtime") + " " +
							this.AQUA + " " + C("HelpAddTime1") + " "
							+ this.RESET + days + this.AQUA + " "
							+ C("HelpAddTime2"));
				}
			} else if (allowedcmd.equalsIgnoreCase("reload")) {
				addText(this.GREEN + " /plotme reload " + 
						this.AQUA + " " + C("HelpReload"));
			} else if (allowedcmd.equalsIgnoreCase("dispose")) {
				if ((ecoon) && (pmi != null) && (pmi.DisposePrice != 0.0D)) {
					addText(this.GREEN + " /plotme " + C("CommandDispose") + " " +
							this.AQUA + " " + C("HelpDispose") + " "
							+ C("WordPrice") + " : " + this.RESET
							+ round(pmi.DisposePrice));
				} else {
					addText(this.GREEN + " /plotme " + C("CommandDispose") + " " + 
							this.AQUA + " " + C("HelpDispose"));
				}
			} else if (allowedcmd.equalsIgnoreCase("buy")) {
				addText(this.GREEN + " /plotme " + C("CommandBuy") + 
						this.AQUA + " " + C("HelpBuy"));
			} else if (allowedcmd.equalsIgnoreCase("sell")) {
				addText(this.GREEN + " /plotme " + C("CommandSell")
						+ " [" + C("WordAmount") + "] " + 
						this.AQUA + " " + C("HelpSell") + " "
						+ C("WordDefault") + " : " + this.RESET
						+ round(pmi.SellToPlayerPrice));
			} else if (allowedcmd.equalsIgnoreCase("sellbank")) {
				addText(this.GREEN + " /plotme " + C("CommandSellBank") +
						this.AQUA + " " + C("HelpSellBank") + " "
						+ this.RESET + round(pmi.SellToBankPrice));
			} else if (allowedcmd.equalsIgnoreCase("auction")) {
				addText(this.GREEN + " /plotme " + C("CommandAuction")
						+ " [" + C("WordAmount") + "] " + 
						this.AQUA + " " + C("HelpAuction") + " "
						+ C("WordDefault") + " : " + this.RESET + "1");
			} else if (allowedcmd.equalsIgnoreCase("resetexpired")) {
				addText(this.GREEN + " /plotme "
						+ C("CommandResetExpired") + " <" + C("WordWorld")
						+ "> " + this.AQUA + " " + C("HelpResetExpired"));
			} else if (allowedcmd.equalsIgnoreCase("bid")) {
				addText(this.GREEN + " /plotme " + C("CommandBid") + " <"
						+ C("WordAmount") + "> " +
						this.AQUA + " " + C("HelpBid"));
			}
		}
		return true;
	}

	private String C(String caption) {
		return PlotMe.caption(caption);
	}

	private String round(double money) {
		return money + "";
	}
}
