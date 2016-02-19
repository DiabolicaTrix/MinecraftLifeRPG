package me.diabolicatrix.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.util.ChatComponentText;

public class GuiKeychain extends GuiMinecraftLife
{
    public int guiWidth = 125;
    public int guiHeight = 150;
    public int xPos;
    public int yPos;

    public GuiKeychain()
    {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawMinecraftLifeBackground(this.xPos, this.yPos, this.xPos + this.guiWidth, this.yPos + this.guiHeight);
        
        this.drawBlackRect(this.xPos + 2, this.yPos + 13, this.xPos + this.guiWidth - 2, this.guiHeight - 15);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void initGui()
    {
        this.xPos = (this.width / 2) - (this.guiWidth / 2);
        this.yPos = ((this.height / 2) - (this.guiHeight / 2)) -16;
        this.buttonList.clear();
        
        this.buttonList.add(new GuiBlackButton(0, this.xPos, this.yPos + guiHeight + 1, 56, 15, "Close"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch(button.id)
        {
            case 0:
                this.mc.displayGuiScreen(null);
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiOptions(this.mc.currentScreen, this.mc.gameSettings));
                break;
        }
    }

}
