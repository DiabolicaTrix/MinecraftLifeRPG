package me.diabolicatrix.gui;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import me.diabolicatrix.entities.EntityLifeTrader;
import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.SideEEP;
import me.diabolicatrix.packets.PacketRequestPlayerList;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;

public class GuiTraderConfigurator extends GuiMinecraftLife
{
    private GuiListMenu dropdown;
    private EntityPlayer player;
    private EntityLifeTrader trader;

    
    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiWidth = 130;
        int guiHeight = 130;
        int xPos = (this.width / 2) - (guiWidth / 2);
        int yPos = (this.height / 2) - (guiHeight / 2);
        this.drawMinecraftLifeBackground(xPos, yPos, xPos + guiWidth, yPos + guiHeight);
        this.drawString(fontRendererObj, "NPC Settings", xPos + 2, yPos + 2, 0xFFFFFFFF);
        this.dropdown.drawList(this.mc, x, y);
        super.drawScreen(x, y, ticks);
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        this.dropdown.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void initGui()
    {
        int guiWidth = 130;
        int guiHeight = 130;
        int xPos = (this.width / 2) - (guiWidth / 2);
        int yPos = (this.height / 2) - (guiHeight / 2);
        this.buttonList.clear();
        this.buttonList.add(new GuiBlackButton(1, (xPos + guiWidth) - 40, (yPos + guiHeight) + 1, 40, 15, "OK"));
        this.buttonList.add(new GuiBlackButton(2, xPos, (yPos + guiHeight) + 1, 40, 15, "CANCEL"));
        this.dropdown = new GuiListMenu(xPos + 5, yPos + 16, 120);
    }

    public GuiTraderConfigurator(EntityPlayer player, EntityLifeTrader trader)
    {
        this.player = player;
        this.trader = trader;
    }

    @Override
    protected void actionPerformed(GuiButton btn)
    {
        switch(btn.id)
        {
            case 1:
                DataWatcher dw = trader.getDataWatcher();
                break;
            case 2:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
        }
    }
}
