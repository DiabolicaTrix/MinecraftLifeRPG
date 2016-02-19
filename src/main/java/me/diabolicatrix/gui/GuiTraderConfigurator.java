package me.diabolicatrix.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.diabolicatrix.entities.EntityLifeTrader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;

public class GuiTraderConfigurator extends GuiMinecraftLife
{
    private GuiListMenu dropdown;
    private EntityPlayer player;
    private EntityLifeTrader trader;
    
    public List<String> paramList;
    
    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        this.drawDefaultBackground();
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
        this.paramList = new ArrayList<String>();
        this.dropdown = new GuiListMenu(this.paramList, xPos + 5, yPos + 16, 120);
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
