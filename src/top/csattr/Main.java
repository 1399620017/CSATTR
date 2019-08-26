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
		return "������";
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
		regListener(new KeyListener()); // VexView��������
		regListener(new VexGuiClose()); // VexView����رռ���
		regListener(new EntityExplodeListener()); // ��ը����
		
		
		regListener(new Death()); // PVP��������
		regListener(new Login()); // ��ҵ�½�¼�
		regListener(new PlayerSpawn()); // �޸�ˢ����BUG

		/**
		 * �������ע��
		 * */
		regListener(new BlockBreakListener()); // ��������¼� ĩ��ˢ��
		regListener(new PlayerBucketFillListener()); // Ͱ��װ�¼�
		regListener(new PlayerBucketEmptyListener()); // Ͱʹ���¼�
		regListener(new LeavesDecayListener()); // ��Ҷ�����¼�
		regListener(new BlockFadeListener()); // ������Ȼ�仯�¼�
		regListener(new BlockFormListener()); // ������Ȼ�仯
		regListener(new BlockBurnListener()); // ���鱻���յ�
		regListener(new BlockGrowListener()); // ������Ȼ����
		regListener(new BlockIgniteListener()); // ���鱻��ȼ
		regListener(new BlockSpreadListener()); // ���������¼�
		regListener(new EntityBlockFormListener()); // ���齨��ʵ��
		regListener(new BlockPlaceListener()); // ��������¼�
		/**
		 * �������ע��
		 * */
	}
	@Override
	public void command() {
		new SwitchCommand("s", 0, "s", "�л���ǰս����Ϣ��ʾ״̬", false);
		new ShowCommand("v", 0, "v", "op����", true);
		new LookCommand("l", 1, "l <name>", "op����", true);
	}
}
