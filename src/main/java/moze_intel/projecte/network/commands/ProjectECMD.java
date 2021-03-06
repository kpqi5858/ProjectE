package moze_intel.projecte.network.commands;

import moze_intel.projecte.config.CustomEMCParser;
import moze_intel.projecte.utils.ChatHelper;
import moze_intel.projecte.utils.MathUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class ProjectECMD extends ProjectEBaseCMD
{
	private static final List<String> commands = Lists.newArrayList("changelog", "clearKnowledge", "setEMC", "reloadEMC", "removeEMC", "resetEMC");

	ChangelogCMD changelogcmd = new ChangelogCMD();
	ReloadEmcCMD reloademccmd = new ReloadEmcCMD();
	SetEmcCMD setemccmd = new SetEmcCMD();
	RemoveEmcCMD removeemccmd = new RemoveEmcCMD();
	ResetEmcCMD resetemccmd = new ResetEmcCMD();
	ClearKnowledgeCMD clearknowledgecmd = new ClearKnowledgeCMD();

	@Override
	public String getCommandName() 
	{
		return "projecte";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "pe.command.main.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() 
	{
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] params)
	{
		if (params.length == 1)
		{
			return Lists.newArrayList(Iterables.filter(commands, new LowerCasePrefixPredicate(params[0])));
		}

		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params) 
	{
		if (params.length < 1)
		{
			sendError(sender, new ChatComponentTranslation("pe.command.main.usage"));
			return;
		}

		String[] relayparams = new String[0];

		if (params.length > 1)
		{
			relayparams = Arrays.copyOfRange(params, 1, params.length);
		}

		if (params[0].toLowerCase().equals("setemc"))
		{
			if (setemccmd.canCommandSenderUseCommand(sender))
			{
				setemccmd.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}
		else if (params[0].toLowerCase().equals("resetemc"))
		{
			if (resetemccmd.canCommandSenderUseCommand(sender))
			{
				resetemccmd.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}
		else if (params[0].toLowerCase().equals("removeemc"))
		{
			if (removeemccmd.canCommandSenderUseCommand(sender))
			{
				removeemccmd.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}
		else if (params[0].toLowerCase().equals("reloademc"))
		{
			if (reloademccmd.canCommandSenderUseCommand(sender))
			{
				reloademccmd.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}
		else if (params[0].toLowerCase().equals("clearknowledge"))
		{
			if (clearknowledgecmd.canCommandSenderUseCommand(sender))
			{
				clearknowledgecmd.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}
		else if (params[0].toLowerCase().equals("changelog"))
		{
			if (changelogcmd.canCommandSenderUseCommand(sender))
			{
				changelogcmd.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}

	}


	private static class LowerCasePrefixPredicate implements Predicate<String>
	{
		private final String prefix;
		public LowerCasePrefixPredicate(String prefix)
		{
			this.prefix = prefix;
		}

		@Override
		public boolean apply(String input)
		{
			return input.toLowerCase().startsWith(prefix.toLowerCase());
		}
	}
}
